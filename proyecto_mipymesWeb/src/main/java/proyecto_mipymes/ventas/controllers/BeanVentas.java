package proyecto_mipymes.ventas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import proyect_mipymes.model.ventas.managers.ManagerVentas;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.DetalleAbono;
import proyecto_mipymes.model.entities.DetalleFactura;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.EstadoPedido;
import proyecto_mipymes.model.entities.Factura;
import proyecto_mipymes.model.entities.FormaPago;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TipoFactura;
import proyecto_mipymes.productos.controllers.BeanProductos;

@Named
@SessionScoped
public class BeanVentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerVentas managerVentas;

	private List<Empresa> listaEmpresas;
	private List<Cliente> listaClientes;
	private List<DetalleFactura> listaDetalleFacturas;
	private List<FormaPago> listaFormaPagos;
	private List<TipoFactura> listaTipoFacturas;
	private List<DetalleAbono> listaDetalleAbonos;

	private DetalleFactura detalleFacturaSeleccionado;
	private Cliente cliente;
	private Cliente clienteSeleccionado;
	private Producto productoSeleccionado;
	private Empresa empresa;
	private Factura factura;
	private Factura facturaAnticipo;
	private EstadoPedido estadoPedido;
	private DetalleAbono detalleAbono;

	private String cedula_ruc;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;

	private int id_producto;
	private int cantidad;
	private double valorTotal;
	private double iva;
	private double subTotal;
	private double valor_abono;
	private double saldo_anterior;
	private double saldo_actual;
	private int index;
	private int stock;

	private int id_tipo_factura;
	private int id_forma_pago;
	private int numero_factura;

	@Inject
	private BeanProductos p;
	
	public BeanVentas() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		id_producto=1;
		empresa = managerVentas.findAllEmpresaByRuc("1003938477001");
		listaEmpresas = managerVentas.findAllEmpresas();
		listaClientes = managerVentas.findAllClientes();
		productoSeleccionado = new Producto();
		listaFormaPagos = managerVentas.findAllFormaPagos();
		listaTipoFacturas = managerVentas.findAllTipoFacturas();
		listaDetalleFacturas = new ArrayList<DetalleFactura>();
		listaDetalleAbonos = new ArrayList<DetalleAbono>();
	}

	public void actionListenerCrearCliente() {
		clienteSeleccionado = new Cliente();
		clienteSeleccionado = managerVentas.crearCliente(cedula_ruc, nombres, apellidos, telefono, email, direccion);
		if(managerVentas.findAllClienteByCedulaRuc(cedula_ruc)!=null) {
			JSFUtil.crearMensajeError("Cliente con cedula o RUC: "+cedula_ruc+" ya existe!");
			actionListenerSeleccionarCliente(cedula_ruc);
		}
		if (clienteSeleccionado != null) {
			JSFUtil.crearMensajeInfo("Cliente creado con Éxito!");
		} else {
			JSFUtil.crearMensajeError("Error al crear el cliente!"+clienteSeleccionado.getCliCodigo());
		}
	}

	public void actionListenerSeleccionarProducto(int id_producto) {
		productoSeleccionado = managerVentas.findProductoById(id_producto);
		JSFUtil.crearMensajeInfo("Producto seleccionado: " + productoSeleccionado.getProdCodigo());
	}

	public void actionListenerSeleccionarCliente(String cedula_ruc) {
		clienteSeleccionado = managerVentas.findAllClienteByCedulaRuc(cedula_ruc);
		if (clienteSeleccionado.getCliRucCedula() != null) {
			this.cedula_ruc = clienteSeleccionado.getCliRucCedula();
			this.nombres = clienteSeleccionado.getUsuario().getUsNombres();
			this.apellidos = clienteSeleccionado.getUsuario().getUsApellidos();
			this.telefono = clienteSeleccionado.getCliTelefono();
			this.email = clienteSeleccionado.getCliEmail();
			this.direccion = clienteSeleccionado.getCliDireccion();
			//JSFUtil.crearMensajeInfo("Cliente encontrado: " + clienteSeleccionado.getCliRucCedula());
		} else {
			clienteSeleccionado=new Cliente();
			this.cedula_ruc = "";
			this.nombres = "";
			this.apellidos = "";
			this.telefono = "";
			this.email = "";
			this.direccion = "";
			JSFUtil.crearMensajeError("No existe el ciente con C.I. o RUC: " + cedula_ruc);
		}
	}
	
	public void stockProduct() {
		this.stock=managerVentas.findProductoById(id_producto).getProdCantidad();
		JSFUtil.crearMensajeWarning("Stock: "+stock);
	}

	public void actionListenerAgregarProductos() {
		if (!managerVentas.findDetalleFacturaByCodProducto(listaDetalleFacturas, id_producto)) {
			listaDetalleFacturas = managerVentas.agregarProductosFactura(listaDetalleFacturas, id_producto, cantidad);
			valorTotal = managerVentas.valorTotalPagar(listaDetalleFacturas);
			iva = managerVentas.valorIva(listaDetalleFacturas);
			subTotal = managerVentas.valorSubTotal(listaDetalleFacturas);
			stockProduct();
			this.cantidad = 1;
		} else {
			JSFUtil.crearMensajeError(
					"Produto ya existe en el detalle factura, tiene la opción de eliminar o editar la cantidad!");
		}

	}

	public void actionListenerSeleccionarDetalleFactura(int index) {
		detalleFacturaSeleccionado = listaDetalleFacturas.get(index);
		JSFUtil.crearMensajeWarning("Detalle seleccionado: " + detalleFacturaSeleccionado.getIdDetalleFactura() + " "
				+ index + " " + listaDetalleFacturas.size());
	}

	public void actionListenerEditarCantidad(int index) {
		listaDetalleFacturas = managerVentas.editarCantidadProductoListaDetalle(listaDetalleFacturas, cantidad, index);
		valorTotal = managerVentas.valorTotalPagar(listaDetalleFacturas);
		iva = managerVentas.valorIva(listaDetalleFacturas);
		subTotal = managerVentas.valorSubTotal(listaDetalleFacturas);
		this.cantidad = 1;
		JSFUtil.crearMensajeWarning("Cantidad: " + index);

	}

	public void actionListenerEliminarProductoDetalleFactura(int index) {
		// listaDetalleFacturas =
		if (index >= 0) {
			JSFUtil.crearMensajeInfo("Producto eliminado del detalle factura!" + index + " "
					+ listaDetalleFacturas.get(index).getDetCantidad());
			listaDetalleFacturas = managerVentas.eliminarProductoListaDetalle(listaDetalleFacturas, index);
			valorTotal = managerVentas.valorTotalPagar(listaDetalleFacturas);
			iva = managerVentas.valorIva(listaDetalleFacturas);
			subTotal = managerVentas.valorSubTotal(listaDetalleFacturas);
		} else {
			JSFUtil.crearMensajeError("Error de index: " + index);
		}
	}

	public void actionInsertarFactura(int id_vendedor, int id_empresa) {
		factura = managerVentas.insertarFactura(clienteSeleccionado, id_vendedor, id_empresa, listaDetalleFacturas,
				id_forma_pago, id_tipo_factura);
		if (factura != null) {
			this.numero_factura=factura.getIdFactura();
			p.actualizarListaProductos();
			JSFUtil.crearMensajeInfo("Factura creada con Éxito!");
			actionListenerLimpiarCampos();
		} else {
			JSFUtil.crearMensajeError("Error de facturacion!");
		}
	}

	public void actionListenerAgregarDetalleAbono(int id_vendedor) {
		estadoPedido = managerVentas.insertarEstadoPedidoAnticipo(facturaAnticipo, valor_abono);
		if (estadoPedido != null) {
			// JSFUtil.crearMensajeInfo("Facturacion por anticipos creada con
			// exito!"+estadoPedido.getEstSaldo());
			detalleAbono = managerVentas.agregarAbonoFacturaAnticipo(estadoPedido, clienteSeleccionado, id_vendedor,
					valor_abono);
			JSFUtil.crearMensajeInfo("Facturacion por anticipos creada con Éxito!" + estadoPedido.getEstSaldo());
			clienteSeleccionado = new Cliente();
		} else {
			JSFUtil.crearMensajeInfo("Error al crear Facturación por anticipo!");
		}

	}

	public void actionInsertarFacturaAnticipos(int id_vendedor, int id_empresa) {
		facturaAnticipo = managerVentas.insertarFacturaAnticipos(clienteSeleccionado, id_vendedor, id_empresa,
				listaDetalleFacturas, 5, 4);
		
		if (facturaAnticipo != null) {
			this.numero_factura=facturaAnticipo.getIdFactura();
			//JSFUtil.crearMensajeInfo("Factura creada con Éxito!");
			actionListenerLimpiarCampos();
		} else {
			JSFUtil.crearMensajeError("Error de facturación!");
		}
	}

	public int actionSeleccionarIndex(int index) {
		this.index = index;
		return this.index;
	}

	public void actionListenerCancelarFacturacion() {
		actionListenerLimpiarCampos();
		JSFUtil.crearMensajeWarning("Facturción Cancelada!");
	}

	public void actionListenerLimpiarCampos() {
		listaDetalleFacturas = new ArrayList<DetalleFactura>();
		// clienteSeleccionado = new Cliente();
		this.cedula_ruc = "";
		this.nombres = "";
		this.apellidos = "";
		this.telefono = "";
		this.email = "";
		this.direccion = "";
		valorTotal = 0;
		subTotal = 0;
		iva = 0;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<DetalleFactura> getListaDetalleFacturas() {
		return listaDetalleFacturas;
	}

	public void setListaDetalleFacturas(List<DetalleFactura> listaDetalleFacturas) {
		this.listaDetalleFacturas = listaDetalleFacturas;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public void setClienteSeleccionado(Cliente clienteSeleccionado) {
		this.clienteSeleccionado = clienteSeleccionado;
	}

	public Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCedula_ruc() {
		return cedula_ruc;
	}

	public void setCedula_ruc(String cedula_ruc) {
		this.cedula_ruc = cedula_ruc;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public void setDetalleFacturaSeleccionado(DetalleFactura detalleFacturaSeleccionado) {
		this.detalleFacturaSeleccionado = detalleFacturaSeleccionado;
	}

	public DetalleFactura getDetalleFacturaSeleccionado() {
		return detalleFacturaSeleccionado;
	}

	public int getId_forma_pago() {
		return id_forma_pago;
	}

	public void setId_forma_pago(int id_forma_pago) {
		this.id_forma_pago = id_forma_pago;
	}

	public int getId_tipo_factura() {
		return id_tipo_factura;
	}

	public void setId_tipo_factura(int id_tipo_factura) {
		this.id_tipo_factura = id_tipo_factura;
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

	public void setListaTipoFacturas(List<TipoFactura> listaTipoFacturas) {
		this.listaTipoFacturas = listaTipoFacturas;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public List<DetalleAbono> getListaDetalleAbonos() {
		return listaDetalleAbonos;
	}

	public void setListaDetalleAbonos(List<DetalleAbono> listaDetalleAbonos) {
		this.listaDetalleAbonos = listaDetalleAbonos;
	}

	public Factura getFacturaAnticipo() {
		return facturaAnticipo;
	}

	public void setFacturaAnticipo(Factura facturaAnticipo) {
		this.facturaAnticipo = facturaAnticipo;
	}

	public EstadoPedido getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
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

	public double getSaldo_anterior() {
		return saldo_anterior;
	}

	public void setSaldo_anterior(double saldo_anterior) {
		this.saldo_anterior = saldo_anterior;
	}

	public double getSaldo_actual() {
		return saldo_actual;
	}

	public void setSaldo_actual(double saldo_actual) {
		this.saldo_actual = saldo_actual;
	}

	public void setNumero_factura(int numero_factura) {
		this.numero_factura = numero_factura;
	}
	
	public int getNumero_factura() {
		return numero_factura;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	
}
