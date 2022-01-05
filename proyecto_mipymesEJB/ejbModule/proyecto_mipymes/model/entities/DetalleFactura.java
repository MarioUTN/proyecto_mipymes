package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_factura", unique=true, nullable=false)
	private Integer idDetalleFactura;

	@Column(name="det_cantidad", nullable=false)
	private Integer detCantidad;

	@Column(name="det_iva", nullable=false, precision=8, scale=2)
	private BigDecimal detIva;

	@Column(name="det_precio_total", nullable=false, precision=8, scale=2)
	private BigDecimal detPrecioTotal;

	@Column(name="det_precio_unitario", nullable=false, precision=8, scale=2)
	private BigDecimal detPrecioUnitario;

	@Column(name="det_subtotal", precision=8, scale=2)
	private BigDecimal detSubtotal;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="det_factura", nullable=false)
	private Factura factura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="det_producto", nullable=false)
	private Producto producto;

	public DetalleFactura() {
	}

	public Integer getIdDetalleFactura() {
		return this.idDetalleFactura;
	}

	public void setIdDetalleFactura(Integer idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}

	public Integer getDetCantidad() {
		return this.detCantidad;
	}

	public void setDetCantidad(Integer detCantidad) {
		this.detCantidad = detCantidad;
	}

	public BigDecimal getDetIva() {
		return this.detIva;
	}

	public void setDetIva(BigDecimal detIva) {
		this.detIva = detIva;
	}

	public BigDecimal getDetPrecioTotal() {
		return this.detPrecioTotal;
	}

	public void setDetPrecioTotal(BigDecimal detPrecioTotal) {
		this.detPrecioTotal = detPrecioTotal;
	}

	public BigDecimal getDetPrecioUnitario() {
		return this.detPrecioUnitario;
	}

	public void setDetPrecioUnitario(BigDecimal detPrecioUnitario) {
		this.detPrecioUnitario = detPrecioUnitario;
	}

	public BigDecimal getDetSubtotal() {
		return this.detSubtotal;
	}

	public void setDetSubtotal(BigDecimal detSubtotal) {
		this.detSubtotal = detSubtotal;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}