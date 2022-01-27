package proyecto_mipymes.facturas.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import proyect_mipymes.model.facturas.managers.ManagerFacturas;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.DetalleAbono;
import proyecto_mipymes.model.entities.EstadoPedido;
import proyecto_mipymes.model.entities.Factura;
import proyecto_mipymes.model.entities.FormaPago;
import proyecto_mipymes.model.entities.TipoFactura;

@Named
@SessionScoped
public class BeanFacturas implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerFacturas managerFacturas;

	private List<Factura> listaFacturas;
	private List<TipoFactura> listaTipoFacturas;
	private List<FormaPago> listaFormaPagos;
	private List<DetalleAbono> listaDetalleAbonos;
	private List<DetalleAbono> auxiliar;

	private String cedula_ruc;
	private int id_tipo_factura;
	private int id_forma_pago;

	private Factura facturaSeleccionada;
	private DetalleAbono detalleAbono;
	private EstadoPedido estadoPedido;
	private double valor_abono;

	public BeanFacturas() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		listaFacturas = managerFacturas.findAllFacturas();
		listaTipoFacturas = managerFacturas.findAllTipoFacturas();
		listaFormaPagos = managerFacturas.findAllFormaPagoFacturas();
		auxiliar = new ArrayList<DetalleAbono>();
	}

	public void actionListenerNuscarFacturaByTipo() {
		listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public void actionListenerNuscarFacturaByFormaPago() {
		listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public void actionListenerNuscarFacturaByCliente() {
		listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public String actionListenerGenerarReporte() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idFact", facturaSeleccionada.getIdFactura());
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("inventario/facturas/reporteF.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporteF.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proyecto", "alexander", "12345");
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			System.out.println("reporte generado.");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	public void actionListenerVerFacturasByCliente() {
		listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
		if (listaFacturas != null) {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturasByTipoFactura() {
		listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
		if (listaFacturas != null) {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturas() {
		listaFacturas = managerFacturas.findAllFacturas();
		if (listaFacturas != null) {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturasByFormaPago() {
		listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
		if (listaFacturas != null) {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerSeleccionarFactura(int id_facura) {
		facturaSeleccionada = managerFacturas.findFacturaById(id_facura);
		JSFUtil.crearMensajeWarning("Factura seleccionada: " + facturaSeleccionada.getIdFactura());
	}

	public String estado_EntregadoProductos() {
		String resp = "";
		if (facturaSeleccionada != null) {
			if (facturaSeleccionada.getFactEntregado() && facturaSeleccionada.getFactEstado()) {
				resp = "Factura cancelada y entregado los productos!";
			}
		} else {
			return "Factura no cancelada y no entregado los productos!";
		}
		return resp;
	}

	public void actionListenerSeleccionarDetalleAbono(int id_factura) {
		facturaSeleccionada = managerFacturas.findFacturaById(id_factura);
		listaDetalleAbonos = managerFacturas.findAllDetalleAbonosByIdFactura(id_factura);
		if (listaDetalleAbonos.size() > 0) {
			JSFUtil.crearMensajeInfo("Lista encontrada " + listaDetalleAbonos.size() + " id factura: " + id_factura);
		} else {
			JSFUtil.crearMensajeError("Error! no se encontraron resultados!");
		}
	}

	public void actionListenerAgregarAbonoFactura(int id_vendedor) {
		auxiliar = managerFacturas.agregarAbonoFactura(listaDetalleAbonos, auxiliar, facturaSeleccionada,
				facturaSeleccionada.getCabeceraFactura().getCliente(), id_vendedor, valor_abono);
		JSFUtil.crearMensajeInfo("Lista encontrada " + listaDetalleAbonos.size() + " id factura: ");

	}

	public void actionListenerGuardarAbonosFacturas() {
		estadoPedido = managerFacturas.actualizarEstadoPedido(auxiliar);
		JSFUtil.crearMensajeInfo("Cambios guardados con exito!");
		auxiliar = new ArrayList<DetalleAbono>();
	}

	public DetalleAbono getDetalleAbono() {
		return detalleAbono;
	}

	public void setDetalleAbono(DetalleAbono detalleAbono) {
		this.detalleAbono = detalleAbono;
	}

	public double getValor_abono() {
		return valor_abono;
	}

	public void setValor_abono(double valor_abono) {
		this.valor_abono = valor_abono;
	}

	public List<DetalleAbono> getListaDetalleAbonos() {
		return listaDetalleAbonos;
	}

	public void setListaDetalleAbonos(List<DetalleAbono> listaDetalleAbonos) {
		this.listaDetalleAbonos = listaDetalleAbonos;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public String getCedula_ruc() {
		return cedula_ruc;
	}

	public void setCedula_ruc(String cedula_ruc) {
		this.cedula_ruc = cedula_ruc;
	}

	public int getId_tipo_factura() {
		return id_tipo_factura;
	}

	public void setId_tipo_factura(int id_tipo_factura) {
		this.id_tipo_factura = id_tipo_factura;
	}

	public int getId_forma_pago() {
		return id_forma_pago;
	}

	public void setId_forma_pago(int id_forma_pago) {
		this.id_forma_pago = id_forma_pago;
	}

	public void setListaTipoFacturas(List<TipoFactura> listaTipoFacturas) {
		this.listaTipoFacturas = listaTipoFacturas;
	}

	public List<FormaPago> getListaFormaPagos() {
		return listaFormaPagos;
	}

	public void setListaFormaPagos(List<FormaPago> listaFormaPagos) {
		this.listaFormaPagos = listaFormaPagos;
	}

	public List<TipoFactura> getListaTipoFacturas() {
		return listaTipoFacturas;
	}

	public Factura getFacturaSeleccionada() {
		return facturaSeleccionada;
	}

	public void setFacturaSeleccionada(Factura facturaSeleccionada) {
		this.facturaSeleccionada = facturaSeleccionada;
	}

	public List<DetalleAbono> getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(List<DetalleAbono> auxiliar) {
		this.auxiliar = auxiliar;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}
}
