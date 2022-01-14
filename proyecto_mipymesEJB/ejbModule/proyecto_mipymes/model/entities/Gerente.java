package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gerente database table.
 * 
 */
@Entity
@Table(name="gerente")
@NamedQuery(name="Gerente.findAll", query="SELECT g FROM Gerente g")
public class Gerente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_gerente", unique=true, nullable=false)
	private Integer idGerente;

	@Column(name="ger_cedula", nullable=false, length=10)
	private String gerCedula;

	@Column(name="ger_direccion", nullable=false, length=100)
	private String gerDireccion;

	@Column(name="ger_email", nullable=false, length=60)
	private String gerEmail;

	@Column(name="ger_telefono", nullable=false, length=15)
	private String gerTelefono;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="gerente")
	private List<Empresa> empresas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ger_usuario", nullable=false)
	private Usuario usuario;

	public Gerente() {
	}

	public Integer getIdGerente() {
		return this.idGerente;
	}

	public void setIdGerente(Integer idGerente) {
		this.idGerente = idGerente;
	}

	public String getGerCedula() {
		return this.gerCedula;
	}

	public void setGerCedula(String gerCedula) {
		this.gerCedula = gerCedula;
	}

	public String getGerDireccion() {
		return this.gerDireccion;
	}

	public void setGerDireccion(String gerDireccion) {
		this.gerDireccion = gerDireccion;
	}

	public String getGerEmail() {
		return this.gerEmail;
	}

	public void setGerEmail(String gerEmail) {
		this.gerEmail = gerEmail;
	}

	public String getGerTelefono() {
		return this.gerTelefono;
	}

	public void setGerTelefono(String gerTelefono) {
		this.gerTelefono = gerTelefono;
	}

	public List<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa addEmpresa(Empresa empresa) {
		getEmpresas().add(empresa);
		empresa.setGerente(this);

		return empresa;
	}

	public Empresa removeEmpresa(Empresa empresa) {
		getEmpresas().remove(empresa);
		empresa.setGerente(null);

		return empresa;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}