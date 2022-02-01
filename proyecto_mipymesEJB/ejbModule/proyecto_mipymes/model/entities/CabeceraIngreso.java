package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cabecera_ingreso database table.
 * 
 */
@Entity
@Table(name="cabecera_ingreso")
@NamedQuery(name="CabeceraIngreso.findAll", query="SELECT c FROM CabeceraIngreso c")
public class CabeceraIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cabecera_ingreso", unique=true, nullable=false)
	private Integer idCabeceraIngreso;

	@Column(name="cabing_autorizacion", nullable=false, length=15)
	private String cabingAutorizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="cabing_fecha_caducacion", nullable=false)
	private Date cabingFechaCaducacion;

	@Temporal(TemporalType.DATE)
	@Column(name="cabing_fecha_emision", nullable=false)
	private Date cabingFechaEmision;

	@Temporal(TemporalType.DATE)
	@Column(name="cabing_fecha_ingreso", nullable=false)
	private Date cabingFechaIngreso;

	@Column(name="cabing_numero_factura", nullable=false, length=15)
	private String cabingNumeroFactura;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="cabing_proveedor", nullable=false)
	private Empresa empresa;

	//bi-directional many-to-one association to VendedorDTO
	@ManyToOne
	@JoinColumn(name="cabing_vendedor", nullable=false)
	private Vendedor vendedor;

	//bi-directional many-to-one association to FacturaIngreso
	@OneToMany(mappedBy="cabeceraIngreso")
	private List<FacturaIngreso> facturaIngresos;

	public CabeceraIngreso() {
	}

	public Integer getIdCabeceraIngreso() {
		return this.idCabeceraIngreso;
	}

	public void setIdCabeceraIngreso(Integer idCabeceraIngreso) {
		this.idCabeceraIngreso = idCabeceraIngreso;
	}

	public String getCabingAutorizacion() {
		return this.cabingAutorizacion;
	}

	public void setCabingAutorizacion(String cabingAutorizacion) {
		this.cabingAutorizacion = cabingAutorizacion;
	}

	public Date getCabingFechaCaducacion() {
		return this.cabingFechaCaducacion;
	}

	public void setCabingFechaCaducacion(Date cabingFechaCaducacion) {
		this.cabingFechaCaducacion = cabingFechaCaducacion;
	}

	public Date getCabingFechaEmision() {
		return this.cabingFechaEmision;
	}

	public void setCabingFechaEmision(Date cabingFechaEmision) {
		this.cabingFechaEmision = cabingFechaEmision;
	}

	public Date getCabingFechaIngreso() {
		return this.cabingFechaIngreso;
	}

	public void setCabingFechaIngreso(Date cabingFechaIngreso) {
		this.cabingFechaIngreso = cabingFechaIngreso;
	}

	public String getCabingNumeroFactura() {
		return this.cabingNumeroFactura;
	}

	public void setCabingNumeroFactura(String cabingNumeroFactura) {
		this.cabingNumeroFactura = cabingNumeroFactura;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<FacturaIngreso> getFacturaIngresos() {
		return this.facturaIngresos;
	}

	public void setFacturaIngresos(List<FacturaIngreso> facturaIngresos) {
		this.facturaIngresos = facturaIngresos;
	}

	public FacturaIngreso addFacturaIngreso(FacturaIngreso facturaIngreso) {
		getFacturaIngresos().add(facturaIngreso);
		facturaIngreso.setCabeceraIngreso(this);

		return facturaIngreso;
	}

	public FacturaIngreso removeFacturaIngreso(FacturaIngreso facturaIngreso) {
		getFacturaIngresos().remove(facturaIngreso);
		facturaIngreso.setCabeceraIngreso(null);

		return facturaIngreso;
	}

}