package proyecto_mipymes.model.dtos;

public class ClienteDTO {
	private String cedulaC;
	private String nombresC;
	private String apellidosC;
	private String emailC;
	private String telefonoC;
	private String direccionC;

	public ClienteDTO(String cedulaC, String nombresC, String apellidosC, String emailC, String telefonoC,
			String direccionC) {
		super();
		this.cedulaC = cedulaC;
		this.nombresC = nombresC;
		this.apellidosC = apellidosC;
		this.emailC = emailC;
		this.telefonoC = telefonoC;
		this.direccionC = direccionC;
	}

	public ClienteDTO() {
		super();
		this.cedulaC = "";
		this.nombresC = "";
		this.apellidosC = "";
		this.emailC = "";
		this.telefonoC = "";
		this.direccionC = "";
	}

	public String getCedulaC() {
		return cedulaC;
	}

	public void setCedulaC(String cedulaC) {
		this.cedulaC = cedulaC;
	}

	public String getNombresC() {
		return nombresC;
	}

	public void setNombresC(String nombresC) {
		this.nombresC = nombresC;
	}

	public String getApellidosC() {
		return apellidosC;
	}

	public void setApellidosC(String apellidosC) {
		this.apellidosC = apellidosC;
	}

	public String getEmailC() {
		return emailC;
	}

	public void setEmailC(String emailC) {
		this.emailC = emailC;
	}

	public String getTelefonoC() {
		return telefonoC;
	}

	public void setTelefonoC(String telefonoC) {
		this.telefonoC = telefonoC;
	}

	public String getDireccionC() {
		return direccionC;
	}

	public void setDireccionC(String direccionC) {
		this.direccionC = direccionC;
	}

}
