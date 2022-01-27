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
import proyecto_mipymes.model.vendedor.ManagerVendedor;
import proyecto_mipymes.model.vendedor.VendedorDTO;

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
	private Vendedor auxVendedor;

	private Cliente cliente;

	@EJB
	private ManagerVendedor managerVendedor;

	private String nombresV;
	private String apellidosV;
	private String emailV;
	private String contraseñaV;
	private String telefonoV;
	private String cedulaV;
	private String direccionV;
	private VendedorDTO vendedorDTO;
	private VendedorDTO editarVendedorDTO;

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
		usuario = new Usuario();
		vendedorDTO = new VendedorDTO();
		editarVendedorDTO = new VendedorDTO();
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
	}

	public String actionLoginVendedor() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1
				|| managerUsuarios.loginUsuarios(id_usuario, password) == 3) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Vendedor!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 2) {
			vendedor = managerUsuarios.findVendedorByUsuario(id_usuario);
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
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

	public void cargarVendedor(Vendedor vendedorDTOs) {

		editarVendedorDTO.setCedulaV(vendedorDTOs.getVenCedula());
		editarVendedorDTO.setNombresV(vendedorDTOs.getUsuario().getUsNombres());
		editarVendedorDTO.setApellidosV(vendedorDTOs.getUsuario().getUsApellidos());
		editarVendedorDTO.setDireccionV(vendedorDTOs.getVenDireccion());
		editarVendedorDTO.setTelefonoV(vendedorDTOs.getVenTelefono());
		editarVendedorDTO.setEmailV(vendedorDTOs.getVenEmail());
		editarVendedorDTO.setIdVendedor(vendedorDTOs.getIdVendedor());
		editarVendedorDTO.setIdUsuario(vendedorDTOs.getUsuario().getIdUsuario());

//		JSFUtil.crearMensajeInfo("Id vendedor: " + editarVendedorDTO.getIdVendedor());
//		JSFUtil.crearMensajeInfo("Id  Usuario: " + vendedorDTOs.getUsuario().getIdUsuario());

	}

	public void actionListenerAgregarVendedor() {
		try {
			JSFUtil.crearMensajeInfo("Ingresado correctamente");
			auxVendedor = new Vendedor();
			auxVendedor = managerVendedor.agregarVendedor(vendedorDTO);
			vendedorDTO = new VendedorDTO();
			listaUsuarios = managerUsuarios.findAllUsuarios();
			listaVendedores = managerUsuarios.findAllVendedores();
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Error: " + e);
		}
	}

	public void actionListenerActualizarEmpleado() throws Exception {
		managerVendedor.actualizaVendedor(editarVendedorDTO);
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
		JSFUtil.crearMensajeInfo("Actualizado");
	}

	public void actionListenerActualizarEstadoEmpleado(Vendedor ven) {
		managerVendedor.actualizarEstadoEmpleado(ven);
		JSFUtil.crearMensajeInfo("Correcto");
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
	}

	public String actionLoginCliente() {
		if (managerUsuarios.loginUsuarios(id_usuario, password) == 1
				|| managerUsuarios.loginUsuarios(id_usuario, password) == 2) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Cliente!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 3) {
			cliente = managerUsuarios.findClienteByUsuario(id_usuario);
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
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
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			vendedor = managerUsuarios.findVendedorByCedula(gerente.getGerCedula());
			JSFUtil.crearMensajeWarning("Usuario: " + gerente.getUsuario().getIdUsuario());
			return "seguridades/administrador/menu_administrador";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password) == 2
				|| managerUsuarios.loginUsuarios(id_usuario, password) == 3) {
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

	// Cambio Cambio Cambio
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

	public String getNombresV() {
		return nombresV;
	}

	public void setNombresV(String nombresV) {
		this.nombresV = nombresV;
	}

	public String getApellidosV() {
		return apellidosV;
	}

	public void setApellidosV(String apellidosV) {
		this.apellidosV = apellidosV;
	}

	public String getEmailV() {
		return emailV;
	}

	public void setEmailV(String emailV) {
		this.emailV = emailV;
	}

	public String getContraseñaV() {
		return contraseñaV;
	}

	public void setContraseñaV(String contraseñaV) {
		this.contraseñaV = contraseñaV;
	}

	public String getTelefonoV() {
		return telefonoV;
	}

	public void setTelefonoV(String telefonoV) {
		this.telefonoV = telefonoV;
	}

	public String getCedulaV() {
		return cedulaV;
	}

	public void setCedulaV(String cedulaV) {
		this.cedulaV = cedulaV;
	}

	public String getDireccionV() {
		return direccionV;
	}

	public void setDireccionV(String direccionV) {
		this.direccionV = direccionV;
	}

	public Vendedor getAuxVendedor() {
		return auxVendedor;
	}

	public void setAuxVendedor(Vendedor auxVendedor) {
		this.auxVendedor = auxVendedor;
	}

	public VendedorDTO getVendedorDTO() {
		return vendedorDTO;
	}

	public void setVendedorDTO(VendedorDTO vendedorDTO) {
		this.vendedorDTO = vendedorDTO;
	}

	public VendedorDTO getEditarVendedorDTO() {
		return editarVendedorDTO;
	}

	public void setEditarVendedorDTO(VendedorDTO editarVendedorDTO) {
		this.editarVendedorDTO = editarVendedorDTO;
	}

}
