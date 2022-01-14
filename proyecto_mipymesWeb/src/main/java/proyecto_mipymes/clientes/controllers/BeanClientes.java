package proyecto_mipymes.clientes.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.clientes.managers.ManagerClientes;
import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Cliente;

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
	
	private String cedula_ruc;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String email;
	private String direccion;

	public BeanClientes() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		listaClientes=managerClientes.findAllClientes();
	}
	
	public void actionListenerCrearCliente() {
		JSFUtil.crearMensajeError("Error no aaunnnnn!");
		if(managerClientes.crearCliente(cedula_ruc, nombres, apellidos, telefono, email, direccion)!=null) {
			JSFUtil.crearMensajeInfo("Cliente creado con exito!");
		}
		else
		{
			JSFUtil.crearMensajeError("Error al crear el cliente!");
		}
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
	
	
}
