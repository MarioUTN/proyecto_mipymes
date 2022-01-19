package proyecto_mipymes.facturas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.facturas.managers.ManagerFacturas;
import proyecto_mipymes.controller.util.JSFUtil;
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

	private String cedula_ruc;
	private int id_tipo_factura;
	private int id_forma_pago;

	private Factura facturaSeleccionada;

	public BeanFacturas() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		listaFacturas = managerFacturas.findAllFacturas();
		listaTipoFacturas = managerFacturas.findAllTipoFacturas();
		listaFormaPagos = managerFacturas.findAllFormaPagoFacturas();
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
		facturaSeleccionada=managerFacturas.findFacturaById(id_facura);
		JSFUtil.crearMensajeWarning("Factura seleccionada: "+facturaSeleccionada.getIdFactura());
	}
	
	public String estado_EntregadoProductos() {
		String resp="";
		if(facturaSeleccionada!=null) {
			if(facturaSeleccionada.getFactEntregado() && facturaSeleccionada.getFactEstado()) {
				resp="Factura cancelada y entregado los productos!";
			}
		}
		else {
			return "Factura no cancelada y no entregado los productos!";
		}
		return resp;
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

}
