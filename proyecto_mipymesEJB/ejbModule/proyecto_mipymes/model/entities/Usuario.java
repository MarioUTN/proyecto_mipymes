package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario", unique=true, nullable=false, length=15)
	private String idUsuario;

	@Column(name="us_activo", nullable=false)
	private Boolean usActivo;

	@Column(name="us_apellidos", nullable=false, length=60)
	private String usApellidos;

	@Column(name="us_email", nullable=false, length=60)
	private String usEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="us_fecha_registro", nullable=false)
	private Date usFechaRegistro;

	@Column(name="us_nombres", nullable=false, length=60)
	private String usNombres;

	@Column(name="us_password", nullable=false, length=100)
	private String usPassword;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Gerente
	@OneToMany(mappedBy="usuario")
	private List<Gerente> gerentes;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="us_tipousuario", nullable=false)
	private TipoUsuario tipoUsuario;

	//bi-directional many-to-one association to Vendedor
	@OneToMany(mappedBy="usuario")
	private List<Vendedor> vendedors;

	public Usuario() {
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getUsActivo() {
		return this.usActivo;
	}

	public void setUsActivo(Boolean usActivo) {
		this.usActivo = usActivo;
	}

	public String getUsApellidos() {
		return this.usApellidos;
	}

	public void setUsApellidos(String usApellidos) {
		this.usApellidos = usApellidos;
	}

	public String getUsEmail() {
		return this.usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

	public Date getUsFechaRegistro() {
		return this.usFechaRegistro;
	}

	public void setUsFechaRegistro(Date usFechaRegistro) {
		this.usFechaRegistro = usFechaRegistro;
	}

	public String getUsNombres() {
		return this.usNombres;
	}

	public void setUsNombres(String usNombres) {
		this.usNombres = usNombres;
	}

	public String getUsPassword() {
		return this.usPassword;
	}

	public void setUsPassword(String usPassword) {
		this.usPassword = usPassword;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setUsuario(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setUsuario(null);

		return cliente;
	}

	public List<Gerente> getGerentes() {
		return this.gerentes;
	}

	public void setGerentes(List<Gerente> gerentes) {
		this.gerentes = gerentes;
	}

	public Gerente addGerente(Gerente gerente) {
		getGerentes().add(gerente);
		gerente.setUsuario(this);

		return gerente;
	}

	public Gerente removeGerente(Gerente gerente) {
		getGerentes().remove(gerente);
		gerente.setUsuario(null);

		return gerente;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Vendedor> getVendedors() {
		return this.vendedors;
	}

	public void setVendedors(List<Vendedor> vendedors) {
		this.vendedors = vendedors;
	}

	public Vendedor addVendedor(Vendedor vendedor) {
		getVendedors().add(vendedor);
		vendedor.setUsuario(this);

		return vendedor;
	}

	public Vendedor removeVendedor(Vendedor vendedor) {
		getVendedors().remove(vendedor);
		vendedor.setUsuario(null);

		return vendedor;
	}

}