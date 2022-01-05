package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_compra database table.
 * 
 */
@Entity
@Table(name="detalle_compra")
@NamedQuery(name="DetalleCompra.findAll", query="SELECT d FROM DetalleCompra d")
public class DetalleCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_compra", unique=true, nullable=false)
	private Integer idDetalleCompra;

	@Column(name="detcomp_cantidad", nullable=false)
	private Integer detcompCantidad;

	@Column(name="detcomp_codigo_producto", length=15)
	private String detcompCodigoProducto;

	@Column(name="detcomp_descripcion", length=100)
	private String detcompDescripcion;

	@Column(name="detcomp_nombre_producto", nullable=false, length=60)
	private String detcompNombreProducto;

	@Column(name="detcomp_precio_total", nullable=false, precision=7, scale=2)
	private BigDecimal detcompPrecioTotal;

	@Column(name="detcomp_precio_unit", nullable=false, precision=7, scale=2)
	private BigDecimal detcompPrecioUnit;

	//bi-directional many-to-one association to CompraProducto
	@ManyToOne
	@JoinColumn(name="detcomp_compra_producto", nullable=false)
	private CompraProducto compraProducto;

	public DetalleCompra() {
	}

	public Integer getIdDetalleCompra() {
		return this.idDetalleCompra;
	}

	public void setIdDetalleCompra(Integer idDetalleCompra) {
		this.idDetalleCompra = idDetalleCompra;
	}

	public Integer getDetcompCantidad() {
		return this.detcompCantidad;
	}

	public void setDetcompCantidad(Integer detcompCantidad) {
		this.detcompCantidad = detcompCantidad;
	}

	public String getDetcompCodigoProducto() {
		return this.detcompCodigoProducto;
	}

	public void setDetcompCodigoProducto(String detcompCodigoProducto) {
		this.detcompCodigoProducto = detcompCodigoProducto;
	}

	public String getDetcompDescripcion() {
		return this.detcompDescripcion;
	}

	public void setDetcompDescripcion(String detcompDescripcion) {
		this.detcompDescripcion = detcompDescripcion;
	}

	public String getDetcompNombreProducto() {
		return this.detcompNombreProducto;
	}

	public void setDetcompNombreProducto(String detcompNombreProducto) {
		this.detcompNombreProducto = detcompNombreProducto;
	}

	public BigDecimal getDetcompPrecioTotal() {
		return this.detcompPrecioTotal;
	}

	public void setDetcompPrecioTotal(BigDecimal detcompPrecioTotal) {
		this.detcompPrecioTotal = detcompPrecioTotal;
	}

	public BigDecimal getDetcompPrecioUnit() {
		return this.detcompPrecioUnit;
	}

	public void setDetcompPrecioUnit(BigDecimal detcompPrecioUnit) {
		this.detcompPrecioUnit = detcompPrecioUnit;
	}

	public CompraProducto getCompraProducto() {
		return this.compraProducto;
	}

	public void setCompraProducto(CompraProducto compraProducto) {
		this.compraProducto = compraProducto;
	}

}