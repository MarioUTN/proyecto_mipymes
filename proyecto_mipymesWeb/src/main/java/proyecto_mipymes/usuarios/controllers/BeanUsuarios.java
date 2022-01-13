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

	private Gerente gerente;
	private Vendedor vendedor;
	private Cliente cliente;

	public BeanUsuarios() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		id_usuario = "ADM-1003938477";
		password = "adminmario";
		gerente = new Gerente();
		vendedor = new Vendedor();
		cliente = new Cliente();
		usuario=new Usuario();
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
	}

	public String actionLoginVendedor() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1 || managerUsuarios.loginUsuarios(id_usuario, password)==3) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Vendedor!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 2) {
			vendedor = managerUsuarios.findVendedorByUsuario(id_usuario);
			usuario=managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			JSFUtil.crearMensajeWarning("Usuario: " + vendedor.getUsuario().getIdUsuario());
			return "talentohumano/vendedores/menu_vendedores";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 0) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == -1) {
			JSFUtil.crearMensajeError("Error de las credenciales!");
		}

		return "";
	}
	
	public String actionLoginCliente() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1 || managerUsuarios.loginUsuarios(id_usuario, password)==2) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Cliente!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 3) {
			cliente = managerUsuarios.findClienteByUsuario(id_usuario);
			usuario=managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			JSFUtil.crearMensajeWarning("Usuario: " + cliente.getUsuario().getIdUsuario());
			return "talentohumano/clientes/menu_clientes";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 0) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == -1) {
			JSFUtil.crearMensajeError("Error de las credenciales!");
		}

		return "";
	}
	
	public String actionLoginGerente() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1) {
			gerente = managerUsuarios.findGerenteByUsuario(id_usuario);
			usuario=managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			JSFUtil.crearMensajeWarning("Usuario: " + gerente.getUsuario().getIdUsuario());
			return "seguridades/administrador/menu_administrador";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 2 || managerUsuarios.loginUsuarios(id_usuario, password)==3) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Gerente!!");
			return "";
		}


		if (managerUsuarios.loginUsuarios(id_usuario, password) == 0) {
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

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	//Cambio Cambio Cambio
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
