package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the factura_ingreso database table.
 * 
 */
@Entity
@Table(name="factura_ingreso")
@NamedQuery(name="FacturaIngreso.findAll", query="SELECT f FROM FacturaIngreso f")
public class FacturaIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura_ingreso", unique=true, nullable=false)
	private Integer idFacturaIngreso;

	@Column(name="facting_iva", precision=8, scale=2)
	private BigDecimal factingIva;

	@Column(name="facting_subtotal", precision=8, scale=2)
	private BigDecimal factingSubtotal;

	@Column(name="facting_total", precision=8, scale=2)
	private BigDecimal factingTotal;

	//bi-directional many-to-one association to DetalleIngreso
	@OneToMany(mappedBy="facturaIngreso")
	private List<DetalleIngreso> detalleIngresos;

	//bi-directional many-to-one association to CabeceraIngreso
	@ManyToOne
	@JoinColumn(name="facting_cabecera_ingreso", nullable=false)
	private CabeceraIngreso cabeceraIngreso;

	public FacturaIngreso() {
	}

	public Integer getIdFacturaIngreso() {
		return this.idFacturaIngreso;
	}

	public void setIdFacturaIngreso(Integer idFacturaIngreso) {
		this.idFacturaIngreso = idFacturaIngreso;
	}

	public BigDecimal getFactingIva() {
		return this.factingIva;
	}

	public void setFactingIva(BigDecimal factingIva) {
		this.factingIva = factingIva;
	}

	public BigDecimal getFactingSubtotal() {
		return this.factingSubtotal;
	}

	public void setFactingSubtotal(BigDecimal factingSubtotal) {
		this.factingSubtotal = factingSubtotal;
	}

	public BigDecimal getFactingTotal() {
		return this.factingTotal;
	}

	public void setFactingTotal(BigDecimal factingTotal) {
		this.factingTotal = factingTotal;
	}

	public List<DetalleIngreso> getDetalleIngresos() {
		return this.detalleIngresos;
	}

	public void setDetalleIngresos(List<DetalleIngreso> detalleIngresos) {
		this.detalleIngresos = detalleIngresos;
	}

	public DetalleIngreso addDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().add(detalleIngreso);
		detalleIngreso.setFacturaIngreso(this);

		return detalleIngreso;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().remove(detalleIngreso);
		detalleIngreso.setFacturaIngreso(null);

		return detalleIngreso;
	}

	public CabeceraIngreso getCabeceraIngreso() {
		return this.cabeceraIngreso;
	}

	public void setCabeceraIngreso(CabeceraIngreso cabeceraIngreso) {
		this.cabeceraIngreso = cabeceraIngreso;
	}

}