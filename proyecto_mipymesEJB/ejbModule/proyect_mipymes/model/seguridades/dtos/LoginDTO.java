package proyect_mipymes.model.seguridades.dtos;

import java.util.ArrayList;
import java.util.List;

import proyecto_mipymes.model.entities.Usuario;

public class LoginDTO {
	private String idSegUsuario;
	private String correo;
	private String direccionIP;
	private List<Usuario> listaPerfiles;
	
	public LoginDTO() {
		listaPerfiles=new ArrayList<Usuario>();
	}
	public String getIdSegUsuario() {
		return idSegUsuario;
	}
	public void setIdSegUsuario(String idSegUsuario) {
		this.idSegUsuario = idSegUsuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public List<Usuario> getListaPerfiles() {
		return listaPerfiles;
	}
	public void setListaPerfiles(List<Usuario> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
	public String getDireccionIP() {
		return direccionIP;
	}
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}
	
}

