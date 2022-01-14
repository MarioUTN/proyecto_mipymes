package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vendedor database table.
 * 
 */
@Entity
@Table(name="vendedor")
@NamedQuery(name="Vendedor.findAll", query="SELECT v FROM Vendedor v")
public class Vendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vendedor", unique=true, nullable=false)
	private Integer idVendedor;

	@Column(name="ven_cedula", nullable=false, length=10)
	private String venCedula;

	@Column(name="ven_direccion", nullable=false, length=100)
	private String venDireccion;

	@Column(name="ven_email", nullable=false, length=60)
	private String venEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="ven_fecha_inicio", nullable=false)
	private Date venFechaInicio;

	@Column(name="ven_sueldo", nullable=false, precision=7, scale=2)
	private BigDecimal venSueldo;

	@Column(name="ven_telefono", nullable=false, length=15)
	private String venTelefono;

	//bi-directional many-to-one association to CabeceraCompra
	@OneToMany(mappedBy="vendedor")
	private List<CabeceraCompra> cabeceraCompras;

	//bi-directional many-to-one association to CabeceraFactura
	@OneToMany(mappedBy="vendedor")
	private List<CabeceraFactura> cabeceraFacturas;

	//bi-directional many-to-one association to CabeceraIngreso
	@OneToMany(mappedBy="vendedor")
	private List<CabeceraIngreso> cabeceraIngresos;

	//bi-directional many-to-one association to DetalleAbono
	@OneToMany(mappedBy="vendedor")
	private List<DetalleAbono> detalleAbonos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ven_usuario", nullable=false)
	private Usuario usuario;

	public Vendedor() {
	}

	public Integer getIdVendedor() {
		return this.idVendedor;
	}

	public void setIdVendedor(Integer idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getVenCedula() {
		return this.venCedula;
	}

	public void setVenCedula(String venCedula) {
		this.venCedula = venCedula;
	}

	public String getVenDireccion() {
		return this.venDireccion;
	}

	public void setVenDireccion(String venDireccion) {
		this.venDireccion = venDireccion;
	}

	public String getVenEmail() {
		return this.venEmail;
	}

	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}

	public Date getVenFechaInicio() {
		return this.venFechaInicio;
	}

	public void setVenFechaInicio(Date venFechaInicio) {
		this.venFechaInicio = venFechaInicio;
	}

	public BigDecimal getVenSueldo() {
		return this.venSueldo;
	}

	public void setVenSueldo(BigDecimal venSueldo) {
		this.venSueldo = venSueldo;
	}

	public String getVenTelefono() {
		return this.venTelefono;
	}

	public void setVenTelefono(String venTelefono) {
		this.venTelefono = venTelefono;
	}

	public List<CabeceraCompra> getCabeceraCompras() {
		return this.cabeceraCompras;
	}

	public void setCabeceraCompras(List<CabeceraCompra> cabeceraCompras) {
		this.cabeceraCompras = cabeceraCompras;
	}

	public CabeceraCompra addCabeceraCompra(CabeceraCompra cabeceraCompra) {
		getCabeceraCompras().add(cabeceraCompra);
		cabeceraCompra.setVendedor(this);

		return cabeceraCompra;
	}

	public CabeceraCompra removeCabeceraCompra(CabeceraCompra cabeceraCompra) {
		getCabeceraCompras().remove(cabeceraCompra);
		cabeceraCompra.setVendedor(null);

		return cabeceraCompra;
	}

	public List<CabeceraFactura> getCabeceraFacturas() {
		return this.cabeceraFacturas;
	}

	public void setCabeceraFacturas(List<CabeceraFactura> cabeceraFacturas) {
		this.cabeceraFacturas = cabeceraFacturas;
	}

	public CabeceraFactura addCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().add(cabeceraFactura);
		cabeceraFactura.setVendedor(this);

		return cabeceraFactura;
	}

	public CabeceraFactura removeCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().remove(cabeceraFactura);
		cabeceraFactura.setVendedor(null);

		return cabeceraFactura;
	}

	public List<CabeceraIngreso> getCabeceraIngresos() {
		return this.cabeceraIngresos;
	}

	public void setCabeceraIngresos(List<CabeceraIngreso> cabeceraIngresos) {
		this.cabeceraIngresos = cabeceraIngresos;
	}

	public CabeceraIngreso addCabeceraIngreso(CabeceraIngreso cabeceraIngreso) {
		getCabeceraIngresos().add(cabeceraIngreso);
		cabeceraIngreso.setVendedor(this);

		return cabeceraIngreso;
	}

	public CabeceraIngreso removeCabeceraIngreso(CabeceraIngreso cabeceraIngreso) {
		getCabeceraIngresos().remove(cabeceraIngreso);
		cabeceraIngreso.setVendedor(null);

		return cabeceraIngreso;
	}

	public List<DetalleAbono> getDetalleAbonos() {
		return this.detalleAbonos;
	}

	public void setDetalleAbonos(List<DetalleAbono> detalleAbonos) {
		this.detalleAbonos = detalleAbonos;
	}

	public DetalleAbono addDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().add(detalleAbono);
		detalleAbono.setVendedor(this);

		return detalleAbono;
	}

	public DetalleAbono removeDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().remove(detalleAbono);
		detalleAbono.setVendedor(null);

		return detalleAbono;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}