package proyecto_mipymes.model.vendedor;

public class VendedorDTO {
	private int idVendedor;
	private String nombresV;
	private String apellidosV;
	private String emailV;
	private String contrasenaV;
	private String telefonoV;
	private String cedulaV;
	private String direccionV;
	private String idUsuario;

	public VendedorDTO(int id,String idUsuario, String nombresV, String apellidosV, String emailV, String contrasenaV, String telefonoV,
			String cedulaV, String direccionV) {
		this.idVendedor = id;
		this.idUsuario=idUsuario;
		this.nombresV = nombresV;
		this.apellidosV = apellidosV;
		this.emailV = emailV;
		this.contrasenaV = contrasenaV;
		this.telefonoV = telefonoV;
		this.cedulaV = cedulaV;
		this.direccionV = direccionV;
	}

	public VendedorDTO() {
//Aqui
		this.nombresV = "";
		this.apellidosV = "";
		this.emailV = "";
		this.contrasenaV = "";
		this.telefonoV = "";
		this.cedulaV = "";
		this.direccionV = "";
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
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

	public String getContrasenaV() {
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

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
