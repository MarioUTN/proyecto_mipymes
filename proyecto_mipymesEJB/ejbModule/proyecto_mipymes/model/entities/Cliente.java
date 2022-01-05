package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cliente", unique=true, nullable=false)
	private Integer idCliente;

	@Column(name="cli_codigo", nullable=false, length=5)
	private String cliCodigo;

	@Column(name="cli_direccion", nullable=false, length=100)
	private String cliDireccion;

	@Column(name="cli_email", nullable=false, length=60)
	private String cliEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="cli_fecha_registro", nullable=false)
	private Date cliFechaRegistro;

	@Column(name="cli_ruc_cedula", nullable=false, length=13)
	private String cliRucCedula;

	@Column(name="cli_telefono", nullable=false, length=15)
	private String cliTelefono;

	//bi-directional many-to-one association to CabeceraFactura
	@OneToMany(mappedBy="cliente")
	private List<CabeceraFactura> cabeceraFacturas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="cli_usuario", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to DetalleAbono
	@OneToMany(mappedBy="cliente")
	private List<DetalleAbono> detalleAbonos;

	public Cliente() {
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliCodigo() {
		return this.cliCodigo;
	}

	public void setCliCodigo(String cliCodigo) {
		this.cliCodigo = cliCodigo;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliEmail() {
		return this.cliEmail;
	}

	public void setCliEmail(String cliEmail) {
		this.cliEmail = cliEmail;
	}

	public Date getCliFechaRegistro() {
		return this.cliFechaRegistro;
	}

	public void setCliFechaRegistro(Date cliFechaRegistro) {
		this.cliFechaRegistro = cliFechaRegistro;
	}

	public String getCliRucCedula() {
		return this.cliRucCedula;
	}

	public void setCliRucCedula(String cliRucCedula) {
		this.cliRucCedula = cliRucCedula;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public List<CabeceraFactura> getCabeceraFacturas() {
		return this.cabeceraFacturas;
	}

	public void setCabeceraFacturas(List<CabeceraFactura> cabeceraFacturas) {
		this.cabeceraFacturas = cabeceraFacturas;
	}

	public CabeceraFactura addCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().add(cabeceraFactura);
		cabeceraFactura.setCliente(this);

		return cabeceraFactura;
	}

	public CabeceraFactura removeCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().remove(cabeceraFactura);
		cabeceraFactura.setCliente(null);

		return cabeceraFactura;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetalleAbono> getDetalleAbonos() {
		return this.detalleAbonos;
	}

	public void setDetalleAbonos(List<DetalleAbono> detalleAbonos) {
		this.detalleAbonos = detalleAbonos;
	}

	public DetalleAbono addDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().add(detalleAbono);
		detalleAbono.setCliente(this);

		return detalleAbono;
	}

	public DetalleAbono removeDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().remove(detalleAbono);
		detalleAbono.setCliente(null);

		return detalleAbono;
	}

}