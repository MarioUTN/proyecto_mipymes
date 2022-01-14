package proyecto_mipymes.ventas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.ventas.managers.ManagerVentas;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Producto;

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

	private Cliente cliente;
	private Cliente clienteSeleccionado;
	private Producto productoSeleccionado;
	private Empresa empresa;

	private String cedula_ruc;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;

	private int id_producto;

	public BeanVentas() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		empresa = managerVentas.findAllEmpresaByRuc("1003938477001");
		listaEmpresas = managerVentas.findAllEmpresas();
		listaClientes = managerVentas.findAllClientes();
		productoSeleccionado=new Producto();
	}

	public void actionListenerCrearCliente() {
		cliente = managerVentas.crearCliente(cedula_ruc, nombres, apellidos, telefono, email, direccion);
		JSFUtil.crearMensajeError("Error no aaunnnnn!");
		if (cliente != null) {
			JSFUtil.crearMensajeInfo("Cliente creado con exito!");
		} else {
			JSFUtil.crearMensajeError("Error al crear el cliente!");
		}
	}

	public void actionListenerSeleccionarProducto(int id_producto) {
		productoSeleccionado = managerVentas.findProductoById(id_producto);
		JSFUtil.crearMensajeInfo("Producto seleccionado: "+productoSeleccionado.getProdCodigo());
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
			JSFUtil.crearMensajeInfo("Cliente encontrado: " + clienteSeleccionado.getCliRucCedula());
		} else {
			JSFUtil.crearMensajeError("No existe el ciente con C.I. o RUC: " + cedula_ruc);
		}
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
	
}
