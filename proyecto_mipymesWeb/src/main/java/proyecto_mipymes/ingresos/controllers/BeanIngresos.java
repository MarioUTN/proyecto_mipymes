package proyecto_mipymes.ingresos.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.CabeceraIngreso;
import proyecto_mipymes.model.entities.DetalleIngreso;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.FacturaIngreso;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.ingresosproductos.managers.ManagerIngresos;

@Named
@SessionScoped
public class BeanIngresos implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerIngresos managerIngresos;

	private Producto producto;
	private Producto productoNuevo;
	private CabeceraIngreso cabeceraIngreso;
	private FacturaIngreso facturaIngreso;
	private DetalleIngreso detalleIngreso;
	private Empresa proveedor;

	private List<DetalleIngreso> listaDetalleIngresos;

	private int cantidad;
	private int id_producto;
	private int id_proveedor;

	private int id_talla_producto;
	private int id_tipo_producto;

	private String autorizacion;
	private Date fecha_emision;
	private Date fecha_caducacion;
	private String numero_factura;

	public BeanIngresos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		producto = new Producto();
		productoNuevo = new Producto();
		cabeceraIngreso = new CabeceraIngreso();
		facturaIngreso = new FacturaIngreso();
		detalleIngreso = new DetalleIngreso();
		proveedor = new Empresa();
		listaDetalleIngresos = new ArrayList<DetalleIngreso>();
	}

	public String actionLlenarCabeceraIngreso(int idVendedor) {
		cabeceraIngreso = managerIngresos.insertarCabeceraIngreso(id_proveedor, idVendedor, autorizacion, fecha_emision,
				fecha_caducacion, numero_factura);
		if (cabeceraIngreso != null) {
			JSFUtil.crearMensajeInfo("Cabecera ingreso seleccionado con exito!");
			return "agregar_productos";
		} else {
			JSFUtil.crearMensajeError("Error al ingresar la cabecera!");
			return "";
		}
	}

	public void actionListenerAgregarProductos() {
		listaDetalleIngresos = managerIngresos.agregarProductoDetalleIngreso(listaDetalleIngresos, id_producto,
				cantidad);
		JSFUtil.crearMensajeInfo("Producto agregado con exito!");
	}

	public void actionListenerAgregarNuevoProducto() {
		listaDetalleIngresos = managerIngresos.agregarNuevoProductoDetalleIngreso(listaDetalleIngresos, productoNuevo,
				cantidad, id_talla_producto, id_tipo_producto, id_proveedor);
		//producto=managerIngresos.crearNuevoProducto(productoNuevo, id_talla_producto, id_tipo_producto, id_proveedor)
		JSFUtil.crearMensajeInfo("Producto nuevo agregado!");
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProductoNuevo() {
		return productoNuevo;
	}

	public void setProductoNuevo(Producto productoNuevo) {
		this.productoNuevo = productoNuevo;
	}

	public CabeceraIngreso getCabeceraIngreso() {
		return cabeceraIngreso;
	}

	public void setCabeceraIngreso(CabeceraIngreso cabeceraIngreso) {
		this.cabeceraIngreso = cabeceraIngreso;
	}

	public FacturaIngreso getFacturaIngreso() {
		return facturaIngreso;
	}

	public void setFacturaIngreso(FacturaIngreso facturaIngreso) {
		this.facturaIngreso = facturaIngreso;
	}

	public DetalleIngreso getDetalleIngreso() {
		return detalleIngreso;
	}

	public void setDetalleIngreso(DetalleIngreso detalleIngreso) {
		this.detalleIngreso = detalleIngreso;
	}

	public Empresa getProveedor() {
		return proveedor;
	}

	public void setProveedor(Empresa proveedor) {
		this.proveedor = proveedor;
	}

	public List<DetalleIngreso> getListaDetalleIngresos() {
		return listaDetalleIngresos;
	}

	public void setListaDetalleIngresos(List<DetalleIngreso> listaDetalleIngresos) {
		this.listaDetalleIngresos = listaDetalleIngresos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getAutorizacion() {
		return autorizacion;
	}

	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}

	public Date getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public Date getFecha_caducacion() {
		return fecha_caducacion;
	}

	public void setFecha_caducacion(Date fecha_caducacion) {
		this.fecha_caducacion = fecha_caducacion;
	}

	public String getNumero_factura() {
		return numero_factura;
	}

	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public int getId_talla_producto() {
		return id_talla_producto;
	}

	public void setId_talla_producto(int id_talla_producto) {
		this.id_talla_producto = id_talla_producto;
	}

	public int getId_tipo_producto() {
		return id_tipo_producto;
	}

	public void setId_tipo_producto(int id_tipo_producto) {
		this.id_tipo_producto = id_tipo_producto;
	}

}
