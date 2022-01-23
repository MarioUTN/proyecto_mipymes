package proyecto_mipymes.compras.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Empresa proveedor;

	private double precio;
	private String codigo_producto;
	private int cantidad;
	private String nombre_producto;
	private String descripcion_producto;
	private double precio_unitario;
	private int id_empresaSeleccionada;
	private int idGerente;
	private int idproducto;
	
	private double valorTotal;
	private double iva;
	private double subTotal;

	private List<Producto> listaProductos;
	private List<Empresa> listaEmpresas;
	private List<DetalleCompra> listaDetalleCompras;
	private List<Gerente> listaGerentes;
	private List<CompraProducto> listaCompraProductos;

	public BeanCompras() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		empresa = new Empresa();
		listaProductos = managerCompras.findAllProductos();
		listaEmpresas = managerCompras.findAllEmpresas();
		listaGerentes = managerCompras.findAllGerente();
		listaCompraProductos = managerCompras.findAllCompraProductos();
		listaDetalleCompras = new ArrayList<DetalleCompra>();

	}

	public void setListaGerentes(List<Gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public void actionListenerAgregarGerente() {
		proveedor = managerCompras.agregarProveedor(empresa, idGerente);
		if (proveedor != null) {
			JSFUtil.crearMensajeInfo("Empresa agregada con exito! " + proveedor.getEmpCiudad());
			proveedor = new Empresa();
			empresa = new Empresa();
			listaEmpresas = managerCompras.findAllEmpresas();
		} else {
			JSFUtil.crearMensajeError("Error al agregar empresa! ");
		}
	}

	public void actionListenerAgregarProducto() {
		listaDetalleCompras = managerCompras.agregarProducto(listaDetalleCompras, idproducto, cantidad);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		JSFUtil.crearMensajeInfo("Si Agrego :v");

	}

	public void actionListenerAgregarInexistenteProducto() {
		listaDetalleCompras = managerCompras.agregarNuevoProducto(listaDetalleCompras, codigo_producto, nombre_producto,
				descripcion_producto, precio_unitario, cantidad);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		JSFUtil.crearMensajeInfo("Valio Nuevo producto inexitente");
	}

	
	public void actionListenerEditarCantidad(int index) {
		listaDetalleCompras = managerCompras.editarCantidadProductoListaDetalle(listaDetalleCompras, cantidad, index);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		this.cantidad=1;
		JSFUtil.crearMensajeWarning("Cantidad: " + index);

	}

	public void actionListenerEliminarProductoDetalleCompra(int index) {
		// listaDetalleFacturas =
		if (index >= 0) {
			JSFUtil.crearMensajeInfo("Producto eliminado del detalle factura!" + index + " "
					+ listaDetalleCompras.get(index).getDetcompCantidad());
			listaDetalleCompras = managerCompras.eliminarProductoListaDetalle(listaDetalleCompras, index);
			valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
			iva = managerCompras.valorIva(valorTotal);
			subTotal = managerCompras.valorSubTotal(valorTotal);
		} else {
			JSFUtil.crearMensajeError("Error de index: " + index);
		}
	}
	public List<Gerente> getListaGerentes() {
		return listaGerentes;
	}

	public String actionSeleccionarEmpresa() {
		JSFUtil.crearMensajeInfo("Empresa seleccionada: " + id_empresaSeleccionada);
		return "agregar_productos";
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



	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
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

	public void setListaCompraProductos(List<CompraProducto> listaCompraProductos) {
		this.listaCompraProductos = listaCompraProductos;
	}

	public List<CompraProducto> getListaCompraProductos() {
		return listaCompraProductos;
	}

	public int getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(int idGerente) {
		this.idGerente = idGerente;
	}



	
	public Empresa getProveedor() {
		return proveedor;
	}

	public void setProveedor(Empresa proveedor) {
		this.proveedor = proveedor;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	


}
