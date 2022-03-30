package proyecto_mipymes.facturas.controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;

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
import proyecto_mipymes.model.entities.Vendedor;
import proyecto_mipymes.model.utils.Encriptar;

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
	private List<Vendedor> listVendedors;

	private String cedula_ruc;
	private String password;
	private String usuario;
	private String connection_url;
	private int id_tipo_factura;
	private int id_forma_pago;
	private int id_vendedor;

	private Factura facturaSeleccionada;
	private DetalleAbono detalleAbono;
	private EstadoPedido estadoPedido;
	private double valor_abono;
	private double saldo_actual;
	private Date date;

	public BeanFacturas() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		connection_url = "jdbc:postgresql://10.0.4.106:5432/proyecto";
		password = "Ofrn8mXdeBbjdBwSoUTgG1HtxUzuEVuz";
		usuario = "+C907bUeVrzYFLXb/mdoMg==";
		listaFacturas = managerFacturas.findAllFacturas();
		listaTipoFacturas = managerFacturas.findAllTipoFacturas();
		listaFormaPagos = managerFacturas.findAllFormaPagoFacturas();
		listVendedors = managerFacturas.findAllVendedors();
		auxiliar = new ArrayList<DetalleAbono>();
		id_forma_pago = 0;
		id_tipo_factura = 0;
		cedula_ruc = null;
		id_vendedor = 0;
		date = null;
	}

	public void actionListenerBuscarFacturaByTipo() {
		listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public void actionListenerBuscarFacturaByFormaPago() {
		listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public void actionListenerBuscarFacturaByCliente() {
		listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
		if (listaFacturas == null) {
			JSFUtil.crearMensajeError("No se encontraron registros de facturas!");
		} else {
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " facturas!");
		}
	}

	public String actionListenerGenerarReporteFactura() {
		String filename = "factura_" + facturaSeleccionada.getFactNumeroFactura();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_factura", facturaSeleccionada.getIdFactura());
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/factura.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=" + filename + ".pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url, Encriptar.descryp(usuario),
					Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Factura generada correctamenet en formato PDF");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	public String actionListenerGenerarReporteAbonos() {
		String filename = "abonos_factura_" + facturaSeleccionada.getFactNumeroFactura();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_factura", facturaSeleccionada.getIdFactura());
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/abonos.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=" + filename + ".pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url, Encriptar.descryp(usuario),
					Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Abonos a Factura generada correctamenet en formato PDF");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	public String actionListenerGenerarFacturaPdf(int id_factura) {
		if (managerFacturas.findFacturaById(id_factura) == null) {
			JSFUtil.crearMensajeError("No existe la factura para generar el reporte!");
		} else {

			String filename = "factura_00" + id_factura;
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("id_factura", id_factura);
			FacesContext context = FacesContext.getCurrentInstance();
			ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
			String ruta = servletContext.getRealPath("reportes/factura.jasper");
			System.out.println(ruta);
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment;filename=" + filename + ".pdf");
			response.setContentType("application/pdf");
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection = null;
				connection = DriverManager.getConnection(connection_url, Encriptar.descryp(usuario),
						Encriptar.descryp(password));
				JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
				JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
				context.getApplication().getStateManager().saveView(context);

				context.responseComplete();
				JSFUtil.crearMensajeInfo("Factura generada correctamenet en formto PDF");
			} catch (Exception e) {
//				JSFUtil.crearMensajeERROR(e.getMessage());
				System.out.println(e);
				e.printStackTrace();
			}
			JSFUtil.crearMensajeInfo("Factura generada correctamenet en formto PDF");
		}
		return "";
	}

	public String actionListenerGenerarAbonosPdf(int id_factura) {
		String filename = "abonos_factura_00" + id_factura;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_factura", id_factura);
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/abonos.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=" + filename + ".pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url, Encriptar.descryp(usuario),
					Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Factura generada correctamenet en formto PDF");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	public String actionReporte() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		/*
		 * parametros.put("p_titulo_principal",p_titulo_principal);
		 * parametros.put("p_titulo",p_titulo);
		 */ FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("inventario/facturas/ReporteProductos.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporte.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url, Encriptar.descryp(usuario),
					Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			System.out.println("reporte generado.");
			context.responseComplete();
		} catch (Exception e) {
			JSFUtil.crearMensajeError("" + e);
			e.printStackTrace();
		}
		return "";
	}

	public void actionListenerVerFacturasByCliente() {
		listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
		if (listaFacturas != null) {
			id_forma_pago = 0;
			id_tipo_factura = 0;
			id_vendedor = 0;
			date=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}
	
	public void actionListenerVerFacturasByDate() {
		listaFacturas = managerFacturas.findAllFacturaByDate(date);
		if (listaFacturas != null) {
			id_forma_pago = 0;
			id_tipo_factura = 0;
			id_vendedor = 0;
			cedula_ruc=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados! "+date);
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturasByTipoFactura() {
		listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
		if (listaFacturas != null) {
			id_forma_pago = 0;
			id_vendedor = 0;
			cedula_ruc = null;
			date=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturas() {
		listaFacturas = managerFacturas.findAllFacturas();
		if (listaFacturas != null) {
			id_forma_pago = 0;
			id_tipo_factura = 0;
			id_vendedor = 0;
			cedula_ruc = null;
			date=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturasByFormaPago() {
		listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
		if (listaFacturas != null) {
			id_vendedor = 0;
			id_tipo_factura = 0;
			cedula_ruc = null;
			date=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerVerFacturasByVendedors() {
		listaFacturas = managerFacturas.findAllFacturaByVendedors(id_vendedor);
		if (listaFacturas != null) {
			id_forma_pago = 0;
			id_tipo_factura = 0;
			cedula_ruc = null;
			date=null;
			JSFUtil.crearMensajeInfo("Se encontro " + listaFacturas.size() + " resultados!");
		} else {
			JSFUtil.crearMensajeError("No se encontraron resultados!");
		}
	}

	public void actionListenerSeleccionarFactura(int id_factura) {
		facturaSeleccionada = managerFacturas.findFacturaById(id_factura);
		estadoPedido = managerFacturas.findEstdoPedido(facturaSeleccionada.getIdFactura());
		if (estadoPedido.getEstSaldo() == null) {
			saldo_actual = 0;
		} else {
			saldo_actual = estadoPedido.getEstSaldo().doubleValue();
		}
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
		valor_abono = managerFacturas.findEstdoPedido(facturaSeleccionada.getIdFactura()).getEstSaldo().doubleValue();
		saldo_actual = listaDetalleAbonos.get(listaDetalleAbonos.size() - 1).getDetabSaldoActual().doubleValue();
		if (saldo_actual == 0) {
			JSFUtil.crearMensajeInfo("The Invoice was canceled!");
		} else {
			JSFUtil.crearMensajeWarning("The invoice has a currently balance of: " + saldo_actual);
		}
	}

	public void actionListenerAgregarAbonoFactura(int id_vendedor) {
		estadoPedido = managerFacturas.findEstdoPedido(facturaSeleccionada.getIdFactura());
		if (valor_abono > saldo_actual) {
			JSFUtil.crearMensajeError("Error, unable to add a value greater than the current invoice balance! "
					+ listaDetalleAbonos.get(listaDetalleAbonos.size() - 1).getDetabSaldoActual().doubleValue());
		} else {
			JSFUtil.crearMensajeInfo("A value of " + valor_abono + " was successfully added to the invoice!");
			auxiliar = managerFacturas.agregarAbonoFactura(listaDetalleAbonos, auxiliar, facturaSeleccionada,
					facturaSeleccionada.getCabeceraFactura().getCliente(), id_vendedor, valor_abono);
			listaFacturas = managerFacturas.findAllFacturas();
		}
		if (estadoPedido == null) {
			saldo_actual = 0;
		} else {
			saldo_actual = listaDetalleAbonos.get(listaDetalleAbonos.size() - 1).getDetabSaldoActual().doubleValue();
			valor_abono = saldo_actual;
		}

	}

	public void actionListenerCancel() {
		JSFUtil.crearMensajeWarning("Procees Cancel!");
	}

	public void actionListenerGuardarAbonosFacturas() {
		if (auxiliar.get(auxiliar.size() - 1).getVendedor() == null) {
			JSFUtil.crearMensajeError("Failed to changes saved!");
		} else {
			estadoPedido = managerFacturas.actualizarEstadoPedido(auxiliar);
			if (id_tipo_factura != 0) {
				listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
			} else if (id_forma_pago != 0) {
				listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
			} else if (id_vendedor != 0) {
				listaFacturas = managerFacturas.findAllFacturaByVendedors(id_vendedor);
			} else if (cedula_ruc != null) {
				listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
			} else if (date != null) {
				listaFacturas = managerFacturas.findAllFacturaByDate(date);
			} else {
				listaFacturas = managerFacturas.findAllFacturas();
			}
			JSFUtil.crearMensajeInfo("Changes saved successfull!");
			auxiliar = new ArrayList<DetalleAbono>();
		}
	}

	public boolean verAbonosFactura(int id_factura) {
		return managerFacturas.buscarFacturaAnticipos(id_factura);
	}

	public String iconoDeliver(boolean vendedor) {
		if (vendedor != false) {
			return "fa fa-close";

		} else {
			return "fa fa-check";
		}

	}

	public String styleDeliver(boolean vendedor) {
		if (vendedor != false) {
			return "rounded-button ui-button-danger";

		} else {
			return "rounded-button ui-button-success";
		}
	}

	public void actionListenerDeliverProduct(int id_factura) {
		EstadoPedido estadoPedido = managerFacturas.findEstdoPedido(id_factura);
		Factura factura = managerFacturas.findFacturaById(id_factura);
		boolean deliverProduct = managerFacturas.DeliverProduct(id_factura);
		if (estadoPedido.getEstSaldo().doubleValue() == 0) {
			if (id_tipo_factura != 0 && !factura.getFactEntregado()) {
				listaFacturas = managerFacturas.findAllFacturaByTipo(id_tipo_factura);
				JSFUtil.crearMensajeInfo("Deliver Product Successfull! " + listaFacturas.size());
			} else if (id_forma_pago != 0 && !factura.getFactEntregado()) {
				listaFacturas = managerFacturas.findAllFacturaByFormaPago(id_forma_pago);
				JSFUtil.crearMensajeInfo("Deliver Product Successfull! " + listaFacturas.size());
			} else if (id_vendedor != 0 && !factura.getFactEntregado()) {
				listaFacturas = managerFacturas.findAllFacturaByVendedors(id_vendedor);
				JSFUtil.crearMensajeInfo("Deliver Product Successfull! " + listaFacturas.size());
			} else if (cedula_ruc != null && !factura.getFactEntregado()) {
				listaFacturas = managerFacturas.findAllFacturaByCliente(cedula_ruc);
				JSFUtil.crearMensajeInfo("Deliver Product Successfull! " + listaFacturas.size());
			} else if (factura.getFactEntregado()) {
				JSFUtil.crearMensajeWarning("The product was previously delivered!");
			} else if (date != null && !factura.getFactEntregado()) {
				listaFacturas = managerFacturas.findAllFacturaByDate(date);
				JSFUtil.crearMensajeInfo("Deliver Product Successfull! " + listaFacturas.size());
			} else {
				listaFacturas = managerFacturas.findAllFacturas();
				JSFUtil.crearMensajeError("Deliver Product Successfull!");
			}
		} else {
			JSFUtil.crearMensajeError("Do not can deliver the product to a invoice that not canceled!");
		}
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

	public int getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	public List<Vendedor> getListVendedors() {
		return listVendedors;
	}

	public void setListVendedors(List<Vendedor> listVendedors) {
		this.listVendedors = listVendedors;
	}

	public ManagerFacturas getManagerFacturas() {
		return managerFacturas;
	}

	public void setManagerFacturas(ManagerFacturas managerFacturas) {
		this.managerFacturas = managerFacturas;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public double getSaldo_actual() {
		return saldo_actual;
	}

	public void setSaldo_actual(double saldo_actual) {
		this.saldo_actual = saldo_actual;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
}
