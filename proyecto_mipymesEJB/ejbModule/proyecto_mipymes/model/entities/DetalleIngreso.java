package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_ingreso database table.
 * 
 */
@Entity
@Table(name="detalle_ingreso")
@NamedQuery(name="DetalleIngreso.findAll", query="SELECT d FROM DetalleIngreso d")
public class DetalleIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_ingreso", unique=true, nullable=false)
	private Integer idDetalleIngreso;

	@Column(name="deting_cantidad", nullable=false)
	private Integer detingCantidad;

	@Column(name="deting_iva", nullable=false, precision=8, scale=2)
	private BigDecimal detingIva;

	@Column(name="deting_precio_total", nullable=false, precision=8, scale=2)
	private BigDecimal detingPrecioTotal;

	@Column(name="deting_precio_unitario", nullable=false, precision=8, scale=2)
	private BigDecimal detingPrecioUnitario;

	@Column(name="deting_subtotal", precision=8, scale=2)
	private BigDecimal detingSubtotal;

	//bi-directional many-to-one association to FacturaIngreso
	@ManyToOne
	@JoinColumn(name="deting_factura_ingreso", nullable=false)
	private FacturaIngreso facturaIngreso;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="deting_producto", nullable=false)
	private Producto producto;

	public DetalleIngreso() {
	}

	public Integer getIdDetalleIngreso() {
		return this.idDetalleIngreso;
	}

	public void setIdDetalleIngreso(Integer idDetalleIngreso) {
		this.idDetalleIngreso = idDetalleIngreso;
	}

	public Integer getDetingCantidad() {
		return this.detingCantidad;
	}

	public void setDetingCantidad(Integer detingCantidad) {
		this.detingCantidad = detingCantidad;
	}

	public BigDecimal getDetingIva() {
		return this.detingIva;
	}

	public void setDetingIva(BigDecimal detingIva) {
		this.detingIva = detingIva;
	}

	public BigDecimal getDetingPrecioTotal() {
		return this.detingPrecioTotal;
	}

	public void setDetingPrecioTotal(BigDecimal detingPrecioTotal) {
		this.detingPrecioTotal = detingPrecioTotal;
	}

	public BigDecimal getDetingPrecioUnitario() {
		return this.detingPrecioUnitario;
	}

	public void setDetingPrecioUnitario(BigDecimal detingPrecioUnitario) {
		this.detingPrecioUnitario = detingPrecioUnitario;
	}

	public BigDecimal getDetingSubtotal() {
		return this.detingSubtotal;
	}

	public void setDetingSubtotal(BigDecimal detingSubtotal) {
		this.detingSubtotal = detingSubtotal;
	}

	public FacturaIngreso getFacturaIngreso() {
		return this.facturaIngreso;
	}

	public void setFacturaIngreso(FacturaIngreso facturaIngreso) {
		this.facturaIngreso = facturaIngreso;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}