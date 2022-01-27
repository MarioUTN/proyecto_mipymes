package proyecto_mipymes.vendedor.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;

import proyecto_mipymes.model.vendedor.ManagerVendedor;

@Alternative
@SessionScoped
public class BeanVendedor implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerVendedor managerVendedor;

	private String nombresV;
	private String apellidosV;
	private String emailV;
	private String contraseñaV;
	private String telefonoV;
	private String cedulaV;
	private String direccionV;

	public BeanVendedor() {
		// TODO Auto-generated constructor stub
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

	
}
