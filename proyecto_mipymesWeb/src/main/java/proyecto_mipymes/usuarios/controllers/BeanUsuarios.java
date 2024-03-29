package proyecto_mipymes.usuarios.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import proyect_mipymes.model.seguridades.dtos.LoginDTO;
import proyecto_mipymes.clientes.controllers.BeanClientes;
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
	private String confpassword;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	private List<Vendedor> listaVendedores;

	private LoginDTO loginDTO;
	private String direccionIP;

	private Gerente gerente;
	private Vendedor vendedor;
	private Vendedor auxVendedor;

	private Cliente cliente;

	@EJB
	private ManagerVendedor managerVendedor;

	private String nombresV;
	private String apellidosV;
	private String emailV;
	private String contrasenaV;
	private String telefonoV;
	private String cedulaV;
	private String direccionV;
	private VendedorDTO vendedorDTO;
	private VendedorDTO editarVendedorDTO;

	@Inject
	private BeanClientes beanClientes;
	
	public BeanUsuarios() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void Inicializar() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String agente = req.getHeader("user-agent");
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}
		direccionIP = ipAddress;
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
		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 1
				|| managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 3) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un VendedorDTO!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 2) {
			vendedor = managerUsuarios.findVendedorByUsuario(id_usuario);
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			// JSFUtil.crearMensajeWarning("Usuario: " +
			// vendedor.getUsuario().getIdUsuario()+" "+direccionIP);
			return "talentohumano/vendedores/menu_vendedores?faces-redirect=true";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 0) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == -1) {
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
	
	public String iconoVendedor(boolean vendedor) {
		if (vendedor != false) {
			return "fa fa-close";

		} else {
			return "fa fa-check";
		}
		
	}
	
	public String style(boolean vendedor) {
		if (vendedor != false) {
			return "rounded-button ui-button-danger";

		} else {
			return "rounded-button ui-button-success";
		}
	}
	
	public String estado(boolean vendedor) {
		if (vendedor != false) {
			return "Inactive";

		} else {
			return "Active";
		}
	}
	
	public void actionListenerActualizarEmpleado() throws Exception {
		if (managerVendedor.actualizaVendedor(editarVendedorDTO, password, confpassword) == null) {
			JSFUtil.crearMensajeError("Error confirmar la contraseña!");
		} else {
			listaUsuarios = managerUsuarios.findAllUsuarios();
			listaVendedores = managerUsuarios.findAllVendedores();
			JSFUtil.crearMensajeInfo("Vendedor Actualizado correctamente!");
		}
	}

	public void actionListenerActualizarEstadoEmpleado(Vendedor ven) {
		managerVendedor.actualizarEstadoVendedor(ven);
		listaUsuarios = managerUsuarios.findAllUsuarios();
		listaVendedores = managerUsuarios.findAllVendedores();
	}
	
	public void actionListenerActualizarEstadoCliente(Cliente ven) {
		managerVendedor.actualizarEstadoCliente(ven);
		listaUsuarios = managerUsuarios.findAllUsuarios();
		beanClientes.actualizarListaClientes();
	}

	public String actionLoginCliente() {
		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 1
				|| managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 2) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Cliente!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 3) {
			cliente = managerUsuarios.findClienteByUsuario(id_usuario);
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			JSFUtil.crearMensajeWarning("Usuario: " + cliente.getUsuario().getIdUsuario() + " " + direccionIP);
			return "talentohumano/clientes/menu_clientes?faces-redirect=true";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 0) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == -1) {
			JSFUtil.crearMensajeError("Error de las credenciales!");
		}

		return "";
	}

	public String actionLoginGerente() {
		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 1) {
			gerente = managerUsuarios.findGerenteByUsuario(id_usuario);
			usuario = managerUsuarios.findUsuarioByIdUsuario(id_usuario);
			vendedor = managerUsuarios.findVendedorByCedula(gerente.getGerCedula());
			JSFUtil.crearMensajeWarning("Usuario: " + gerente.getUsuario().getIdUsuario());
			return "seguridades/administrador/menu_administrador?faces-redirect=true";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 2
				|| managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 3) {
			JSFUtil.crearMensajeWarning("Su usuario no pertenece a la de un Gerente!!");
			return "";
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == 0) {
			JSFUtil.crearMensajeError("Debe ingresar el usuario y su contrasena \nVuelva a intentar nuevamente!");
		}

		if (managerUsuarios.loginUsuarios(id_usuario, password, direccionIP) == -1) {
			JSFUtil.crearMensajeError("Error de las credenciales!");
		}

		return "";
	}

	public void actionListenerCancelarLogin() {
		this.password="";
		this.confpassword="";
		this.id_usuario="";
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

	public String getContrasnnaV() {
		return contrasenaV;
	}

	public void setContrasenaV(String contrasenaV) {
		this.contrasenaV = contrasenaV;
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

	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	public String getDireccionIP() {
		return direccionIP;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public String getContrasenaV() {
		return contrasenaV;
	}

	public String getConfpassword() {
		return confpassword;
	}
	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}
}
