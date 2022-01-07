package proyecto_mipymes.usuarios.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.*;
import proyecto_mipymes.model.usuarios.managers.ManagerUsuarios;

@Named
@SessionScoped
public class BeanUsuarios implements Serializable {
	@EJB
	private ManagerUsuarios managerUsuarios;

	private String id_usuario;
	private String password;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	private List<Vendedor> listaVendedores;

	public BeanUsuarios() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
	}

	public String actionLogin() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 0) {
			return "inventario/compras/agregar_proveedor";
		}
		
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}
		
		if (managerUsuarios.loginUsuarios(id_usuario, password) == -1) {
			JSFUtil.crearMensajeError("Error de las credenciales!");
		}
		
		return "";
	}

	public void actionListenerCancelarLogin() {
			JSFUtil.crearMensajeInfo("Proceso de login cancelada!");
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(List<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

}
