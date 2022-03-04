package proyecto_mipymes.compras.controllers;

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
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.comprasproveedor.managers.ManagerCompras;
import proyecto_mipymes.model.entities.*;
import proyecto_mipymes.model.utils.Encriptar;

@Named
@SessionScoped
public class BeanCompras implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerCompras managerCompras;

	private String connection_url;
	private Producto producto;
	private Producto productoNuevo;
	private CompraProducto compraProducto;
	private CompraProducto compraProductoSeleccionado;
	private CabeceraCompra cabeceraCompra;
	private DetalleCompra detalleCompra;
	private Vendedor vendedor;
	private Empresa empresa;
	private Empresa proveedor;
	private Empresa editarEmpresa;
	private int idEditarGerente;
	private Empresa emp;

	private double precio;
	private String codigo_producto;
	private int cantidad;
	private String nombre_producto;
	private String descripcion_producto;
	private String ico_aprovado;
	private String stilo_aprobado;
	private double precio_unitario;
	private int id_empresaSeleccionada;
	private int idGerente;
	private int idproducto;
	private int idproveedor;

	private double valorTotal;
	private double iva;
	private double subTotal;

	private List<Producto> listaProductos;
	private List<Empresa> listaEmpresas;
	private List<DetalleCompra> listaDetalleCompras;
	private List<Gerente> listaGerentes;
	private List<CompraProducto> listaCompraProductos;
	private List<FacturaIngreso> listaFacturaIngresos;

	private String password;
	private String usuario;
	
	public BeanCompras() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		password = "Ofrn8mXdeBbjdBwSoUTgG1HtxUzuEVuz";
		usuario = "+C907bUeVrzYFLXb/mdoMg==";
		connection_url = "jdbc:postgresql://10.0.4.106:5432/proyecto";
		empresa = new Empresa();
		listaFacturaIngresos = managerCompras.findAllFacturasIngresos();
		listaProductos = managerCompras.findAllProductos();
		listaEmpresas = managerCompras.findAllEmpresas();
		listaGerentes = managerCompras.findAllGerente();
		listaCompraProductos = managerCompras.findAllCompraProductos();
		for (CompraProducto compraProducto : listaCompraProductos) {
			if (compraProducto.getComprodAprobado()) {
				this.ico_aprovado = "fa fa-close";
				this.stilo_aprobado = "rounded-button ui-button-danger";
			} else {
				this.stilo_aprobado = "rounded-button ui-button-success";
				this.ico_aprovado = "fa fa-check";
			}
		}
		listaDetalleCompras = new ArrayList<DetalleCompra>();

	}

	public void setListaGerentes(List<Gerente> listaGerentes) {
		this.listaGerentes = listaGerentes;
	}

	public void actionListenerAgregarGerente() {
		proveedor = managerCompras.agregarProveedor(empresa, idGerente);
		if (proveedor != null) {
			JSFUtil.crearMensajeInfo("Empresa agregada con Éxito! " + proveedor.getEmpCiudad());
			proveedor = new Empresa();
			empresa = new Empresa();
			listaEmpresas = managerCompras.findAllEmpresas();
		} else {
			JSFUtil.crearMensajeError("Error al agregar empresa! ");
		}
	}
	/*
	 * 
	 * public void actionListenerAgregarGerente() { emp =
	 * managerCompras.agregarProveedor(empresa, idGerente); if (emp != null) {
	 * JSFUtil.crearMensajeInfo("Empresa agregada con exito! " +
	 * emp.getEmpCiudad()); emp = new Empresa(); empresa = new Empresa();
	 * listaEmpresas = managerCompras.findAllEmpresas(); } else {
	 * JSFUtil.crearMensajeError("Error al agregar empresa! "); } }
	 */

	public void actionListenerAgregarProducto() {
		listaDetalleCompras = managerCompras.agregarProducto(listaDetalleCompras, idproducto, cantidad);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		// JSFUtil.crearMensajeInfo("Si Agrego :v");

	}

	public void actionListenerAgregarInexistenteProducto() {
		listaDetalleCompras = managerCompras.agregarNuevoProducto(listaDetalleCompras, codigo_producto, nombre_producto,
				descripcion_producto, precio_unitario, cantidad);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		// JSFUtil.crearMensajeInfo("Valio Nuevo producto inexitente");
	}

	public void actionListenerEditarCantidad(int index) {
		listaDetalleCompras = managerCompras.editarCantidadProductoListaDetalle(listaDetalleCompras, cantidad, index);
		valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
		iva = managerCompras.valorIva(valorTotal);
		subTotal = managerCompras.valorSubTotal(valorTotal);
		this.cantidad = 1;
		// JSFUtil.crearMensajeWarning("Cantidad: " + index);

	}

	public void actionListenerEliminarProductoDetalleCompra(int index) {
		// listaDetalleFacturas =
		if (index >= 0) {
			JSFUtil.crearMensajeInfo(
					"Producto eliminado del detalle factura!" + listaDetalleCompras.get(index).getDetcompCantidad());
			listaDetalleCompras = managerCompras.eliminarProductoListaDetalle(listaDetalleCompras, index);
			valorTotal = managerCompras.valorTotalPagar(listaDetalleCompras);
			iva = managerCompras.valorIva(valorTotal);
			subTotal = managerCompras.valorSubTotal(valorTotal);
		} else {
			JSFUtil.crearMensajeError("Error de index: " + index);
		}
	}

	public void actionActualizar() {
		listaFacturaIngresos = managerCompras.findAllFacturasIngresos();
		listaCompraProductos = managerCompras.findAllCompraProductos();

	}

	public void actionListenerInsertarPedido(int id_vendedor) {
		compraProducto = managerCompras.insertarPedido(listaDetalleCompras, idproveedor, id_vendedor);
		listaFacturaIngresos = managerCompras.findAllFacturasIngresos();
		if (compraProducto != null) {
			JSFUtil.crearMensajeInfo("Pedido generado con Éxito!");
			listaDetalleCompras = new ArrayList<DetalleCompra>();
			valorTotal = 0;
			iva = 0;
			subTotal = 0;
		} else {
			JSFUtil.crearMensajeError("Error al generar pedido!");
		}
	}

	public void actionListenerSeleccionarPedido(int id_pedido) {
		compraProductoSeleccionado = managerCompras.findCompraProductoById(id_pedido);
		JSFUtil.crearMensajeInfo("Pedido seleccionado: " + id_pedido);
	}

	public String actionListenerGenerarReportePedido() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idcp", compraProductoSeleccionado.getIdCompraProducto());
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/reportepedidos.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=reporteF.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url,
					Encriptar.descryp(usuario), Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Reporte de pedido generado con Éxito!");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	public String actionListenerGenerarReportePedido(int id_compra) {
		String filename="pedido_00"+id_compra;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idcp", id_compra);
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/reportepedidos.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename="+filename+".pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url,
					Encriptar.descryp(usuario), Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Reporte de pedidos generada correctamenet en formto PDF");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	
	public String actionListenerGenerarReporteIngresos(int id_facting) {
		String filename="reporte_ingresos_00"+id_facting;
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_factura_ingreso", id_facting);
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("reportes/ingresosmario.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename="+filename+".pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;
			connection = DriverManager.getConnection(connection_url,
					Encriptar.descryp(usuario), Encriptar.descryp(password));
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);

			context.responseComplete();
			JSFUtil.crearMensajeInfo("Reporte de pedido generado con Éxito!");
		} catch (Exception e) {
//			JSFUtil.crearMensajeERROR(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return "";
	}

	
	public void actionListenerAprovarPedido(int id_compra_producto) {
		if (managerCompras.aprobarPedidoProductos(id_compra_producto)) {
			//listaDetalleCompras = managerCompras.findAllDetallesCompras();
			listaCompraProductos = managerCompras.findAllCompraProductos();
			listaFacturaIngresos = managerCompras.findAllFacturasIngresos();
			this.stilo_aprobado = style(id_compra_producto);
			this.ico_aprovado=icono(id_compra_producto);
			JSFUtil.crearMensajeInfo("Pedido aprovado con Éxito!");
		} else {
			//listaDetalleCompras = managerCompras.findAllDetallesCompras();
			listaCompraProductos = managerCompras.findAllCompraProductos();
			this.stilo_aprobado = style(id_compra_producto);
			this.ico_aprovado=icono(id_compra_producto);
			JSFUtil.crearMensajeInfo("Pedido no aprovado con Éxito!");
		}

	}

	public String icono(int idcompra) {
		if (managerCompras.findCompraProductoById(idcompra).getComprodAprobado() != false) {
			return "fa fa-close";

		} else {
			return "fa fa-check";
		}
		
	}
	

	public String style(int idcompra) {
		if (managerCompras.findCompraProductoById(idcompra).getComprodAprobado() != false) {
			return "rounded-button ui-button-danger";

		} else {
			return "rounded-button ui-button-success";
		}
	}
	
	public String estado(int idcompra) {
		if (managerCompras.findCompraProductoById(idcompra).getComprodAprobado() != false) {
			return "Inactive";

		} else {
			return "Active";
		}
	}

	public void actionListenercargarEmpresa(Empresa empresae) {
		editarEmpresa = empresae;
	}

	public void actionListenerActualizarEmpresa() throws Exception {
		try {
			// JSFUtil.crearMensajeInfo("Actualizado" + idEditarGerente);
			managerCompras.actualizaProveedor(editarEmpresa, idEditarGerente);
			JSFUtil.crearMensajeInfo("Empresa Actualizado correctamente!");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("No se pudo actualizar");
		}

	}

	public void actionListenerEliminarProveedor(int idProveedor) {

		try {
			managerCompras.EliminarEmpresa(idProveedor);
			listaEmpresas = managerCompras.findAllEmpresas();
			JSFUtil.crearMensajeInfo("Proveedor Eliminado!");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Error al eliminar proveedor!");
			// TODO: handle exception
		}
	}

	public List<Gerente> getListaGerentes() {
		return listaGerentes;
	}

	public String actionSeleccionarEmpresa() {
		// JSFUtil.crearMensajeInfo("Empresa seleccionada: " + id_empresaSeleccionada);
		return "agregar_productos";
	}

	public void setStilo_aprobado(String stilo_aprobado) {
		this.stilo_aprobado = stilo_aprobado;
	}

	public String getStilo_aprobado() {
		return stilo_aprobado;
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

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public Producto getProductoNuevo() {
		return productoNuevo;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setCompraProductoSeleccionado(CompraProducto compraProductoSeleccionado) {
		this.compraProductoSeleccionado = compraProductoSeleccionado;
	}

	public CompraProducto getCompraProductoSeleccionado() {
		return compraProductoSeleccionado;
	}

	public void setIco_aprovado(String ico_aprovado) {
		this.ico_aprovado = ico_aprovado;
	}

	public String getIco_aprovado() {
		return ico_aprovado;
	}

	public Empresa getEditarEmpresa() {
		return editarEmpresa;
	}

	public void setEditarEmpresa(Empresa editarEmpresa) {
		this.editarEmpresa = editarEmpresa;
	}

	public void setIdEditarGerente(int idEditarGerente) {
		this.idEditarGerente = idEditarGerente;
	}

	public int getIdEditarGerente() {
		return idEditarGerente;
	}

	public void setEmp(Empresa emp) {
		this.emp = emp;
	}

	public Empresa getEmp() {
		return emp;
	}

	public List<FacturaIngreso> getListaFacturaIngresos() {
		return listaFacturaIngresos;
	}

	public void setListaFacturaIngresos(List<FacturaIngreso> listaFacturaIngresos) {
		this.listaFacturaIngresos = listaFacturaIngresos;
	}

}
