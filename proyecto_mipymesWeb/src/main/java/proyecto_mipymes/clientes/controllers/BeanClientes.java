package proyecto_mipymes.clientes.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.clientes.managers.ManagerClientes;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.dtos.ClienteDTO;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.Producto;

@Named
@SessionScoped
public class BeanClientes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerClientes managerClientes;

	private List<Cliente> listaClientes;

	private Cliente cliente;
	private Cliente clienteSeleccionado;
	private Producto productoSeleccionado;
	private ClienteDTO clienteDTO;

	private String cedula_ruc;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;

	private int id_producto;

	public BeanClientes() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		listaClientes = managerClientes.findAllClientes();
		clienteDTO = new ClienteDTO();

	}

	public void actualizarListaClientes() {
		listaClientes = managerClientes.findAllClientes();
	}
	
	public void actionListenerSeleccionarCliente(int id_cliente) {
		clienteSeleccionado = new Cliente();
		clienteSeleccionado = managerClientes.findClienteById(id_cliente);
	}

	public void actionListenerCrearCliente() {
		cliente = managerClientes.crearCliente(cedula_ruc, nombres, apellidos, telefono, email, direccion);
		JSFUtil.crearMensajeError("Error no aaunnnnn!");
		if (cliente != null) {
			listaClientes = managerClientes.findAllClientes();
			JSFUtil.crearMensajeInfo("Cliente creado con exito!");
		} else {
			JSFUtil.crearMensajeError("Error al crear el cliente!");
		}
	}

	public void actionListenerRegistrarCliente() {
		cliente = managerClientes.crearCliente(clienteDTO);
		if (cliente != null) {
			listaClientes = managerClientes.findAllClientes();
			JSFUtil.crearMensajeInfo("Cliente registrado con exito!");
		} else {
			JSFUtil.crearMensajeError("Error al crear el cliente!");
		}
	}

	public void actionListenerSeleccionarCliente(String cedula_ruc) {
		clienteSeleccionado = managerClientes.findAllClienteByCedulaRuc(cedula_ruc);
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

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

}
