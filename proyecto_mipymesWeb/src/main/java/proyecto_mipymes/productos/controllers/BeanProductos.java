package proyecto_mipymes.productos.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.productos.managers.ManagerProductos;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TallaProducto;
import proyecto_mipymes.model.entities.TipoProducto;

@Named
@SessionScoped
public class BeanProductos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerProductos managerProductos;

	private List<Producto> listaProductos;
	private List<TallaProducto> listaTallaProductos;
	private List<TipoProducto> listaTipoProductos;
	private List<Empresa> listaEmpresas;

	private Producto productoNuevo;
	private Producto productoEditar;
	private Producto productoSeleccionado;

	private int id_proveedor;
	private int id_talla_producto;
	private int id_tipo_producto;
	private String codigo_producto;

	public BeanProductos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		productoSeleccionado = new Producto();
		productoEditar = new Producto();
		productoNuevo = new Producto();
		listaProductos = managerProductos.findAllProductos();
		listaTallaProductos = managerProductos.findAllTallaProductos();
		listaTipoProductos = managerProductos.findAllTipoProductos();
		listaEmpresas = managerProductos.findAllEmpresas();

	}

	public void actionListenerInsertarProducto() {
		if (managerProductos.ingresarNuevoProducto(productoNuevo, id_proveedor, id_talla_producto,
				id_tipo_producto) != null) {
			JSFUtil.crearMensajeInfo("Producto Ingresado con exito!");
		} else {
			JSFUtil.crearMensajeError("Error al ingresar el producto!");
		}
	}

	public void actionListenerSeleccionarProducto(String codigo_producto) {
		try {
			productoSeleccionado = managerProductos.findAllProductosByCodigoProducto(codigo_producto);
			JSFUtil.crearMensajeWarning("Producto COD Seleccionado: " + productoSeleccionado.getProdCodigo() + ""
					+ "Talla: " + productoSeleccionado.getTallaProducto().getTallaNombre() + "" + "Talla: "
					+ productoSeleccionado.getTallaProducto().getTallaNombre() + "" + "Talla: "
					+ productoSeleccionado.getTallaProducto().getTallaNombre() + "" + codigo_producto);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void actionListenerEditarProducto() throws Exception {
			int r = managerProductos.editarProducto(productoSeleccionado, productoEditar, id_talla_producto,
					id_tipo_producto, id_proveedor);
			JSFUtil.crearMensajeError("r " + r);
			if (r == 0) {
				Inicializar();
				JSFUtil.crearMensajeInfo("Producto se actualizo correctamente!");
			}
			

	}

	public void actionListenerCancelarEdicionProducto() {
		JSFUtil.crearMensajeInfo("Edicion de Producto cancelada!");
	}

	public String actionListenerEliminarProducto(String codigo_producto) {
		if (managerProductos.eliminarProducto(codigo_producto)) {
			Inicializar();
			JSFUtil.crearMensajeInfo("Producto eliminado exitosamente!!");
			return "PF('editarProductDialog').show()";
		} else {
			JSFUtil.crearMensajeError("Error al eliminar un producto!");
			return "PF('editarProductDialog').hide()";
		}

	}

	public String actionVerTodosLosProductos() {
		Inicializar();
		return "viewproductos";
	}

	public void actionListenerValidarDatosEdicion() {
		JSFUtil.crearMensajeError("" + managerProductos.findAllProductosByCodigoProducto("s").getProdCodigo());
	}

	public void actionListenerMostrarProductosByTalla() {
		listaProductos = managerProductos.findAllProductosByTalla(id_talla_producto);
	}

	public void actionListenerMostrarProductosByTipo() {
		listaProductos = managerProductos.findAllProductosByTipo(id_tipo_producto);
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Producto getProductoNuevo() {
		return productoNuevo;
	}

	public void setProductoNuevo(Producto productoNuevo) {
		this.productoNuevo = productoNuevo;
	}

	public Producto getProductoEditar() {
		return productoEditar;
	}

	public void setProductoEditar(Producto productoEditar) {
		this.productoEditar = productoEditar;
	}

	public int getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(int id_proveedor) {
		this.id_proveedor = id_proveedor;
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

	public String getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public List<TallaProducto> getListaTallaProductos() {
		return listaTallaProductos;
	}

	public void setListaTallaProductos(List<TallaProducto> listaTallaProductos) {
		this.listaTallaProductos = listaTallaProductos;
	}

	public List<TipoProducto> getListaTipoProductos() {
		return listaTipoProductos;
	}

	public void setListaTipoProductos(List<TipoProducto> listaTipoProductos) {
		this.listaTipoProductos = listaTipoProductos;
	}

	public Producto getProductoSeleccionado() {
		if (productoSeleccionado == null) {
			productoSeleccionado = new Producto();
		}
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

}
