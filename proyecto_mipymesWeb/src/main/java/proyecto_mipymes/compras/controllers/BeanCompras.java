package proyecto_mipymes.compras.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.comprasproveedor.managers.ManagerCompras;
import proyecto_mipymes.model.entities.*;

@Named
@SessionScoped
public class BeanCompras implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCompras managerCompras;
	
	private Producto producto;
	private Producto productoNuevo;
	private CompraProducto compraProducto;
	private CabeceraCompra cabeceraCompra;
	private DetalleCompra detalleCompra;
	private Vendedor vendedor;
	private Empresa empresa;
	
	private double precio;
	private String codigo_producto;
	private int cantidad;
	private String nombre_producto;
	private String descripcion_producto;
	private double precio_unitario;
	private int id_empresaSeleccionada;
	
	private List<Producto> listaProductos;
	private List<Empresa> listaEmpresas;
	private List<DetalleCompra> listaDetalleCompras;
	
	public BeanCompras() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void Inicializar() {
		listaProductos=managerCompras.findAllProductos();
		listaEmpresas=managerCompras.findAllEmpresas();
	}

	public void actionSeleccionarEmpresa() {
		JSFUtil.crearMensajeInfo("Empresa seleccionada: "+id_empresaSeleccionada);
	}
	
	public void setId_empresaSeleccionada(int id_empresaSeleccionada) {
		this.id_empresaSeleccionada = id_empresaSeleccionada;
	}
	
	public int getId_empresaSeleccionada() {
		return id_empresaSeleccionada;
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

	public CompraProducto getCompraProducto() {
		return compraProducto;
	}

	public void setCompraProducto(CompraProducto compraProducto) {
		this.compraProducto = compraProducto;
	}

	public CabeceraCompra getCabeceraCompra() {
		return cabeceraCompra;
	}

	public void setCabeceraCompra(CabeceraCompra cabeceraCompra) {
		this.cabeceraCompra = cabeceraCompra;
	}

	public DetalleCompra getDetalleCompra() {
		return detalleCompra;
	}

	public void setDetalleCompra(DetalleCompra detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public String getDescripcion_producto() {
		return descripcion_producto;
	}

	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public List<DetalleCompra> getListaDetalleCompras() {
		return listaDetalleCompras;
	}

	public void setListaDetalleCompras(List<DetalleCompra> listaDetalleCompras) {
		this.listaDetalleCompras = listaDetalleCompras;
	}
	
	

}
