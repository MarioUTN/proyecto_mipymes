package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the compra_producto database table.
 * 
 */
@Entity
@Table(name="compra_producto")
@NamedQuery(name="CompraProducto.findAll", query="SELECT c FROM CompraProducto c")
public class CompraProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compra_producto", unique=true, nullable=false)
	private Integer idCompraProducto;

	@Column(name="comprod_aprobado")
	private Boolean comprodAprobado;

	@Temporal(TemporalType.DATE)
	@Column(name="comprod_fecha", nullable=false)
	private Date comprodFecha;

	@Column(name="comprod_iva", precision=8, scale=2)
	private BigDecimal comprodIva;

	@Column(name="comprod_subtotal", precision=8, scale=2)
	private BigDecimal comprodSubtotal;

	@Column(name="comprod_total", precision=8, scale=2)
	private BigDecimal comprodTotal;

	//bi-directional many-to-one association to CabeceraCompra
	@ManyToOne
	@JoinColumn(name="comprod_cabecera_compra", nullable=false)
	private CabeceraCompra cabeceraCompra;

	//bi-directional many-to-one association to DetalleCompra
	@OneToMany(mappedBy="compraProducto")
	private List<DetalleCompra> detalleCompras;

	public CompraProducto() {
	}

	public Integer getIdCompraProducto() {
		return this.idCompraProducto;
	}

	public void setIdCompraProducto(Integer idCompraProducto) {
		this.idCompraProducto = idCompraProducto;
	}

	public Boolean getComprodAprobado() {
		return this.comprodAprobado;
	}

	public void setComprodAprobado(Boolean comprodAprobado) {
		this.comprodAprobado = comprodAprobado;
	}

	public Date getComprodFecha() {
		return this.comprodFecha;
	}

	public void setComprodFecha(Date comprodFecha) {
		this.comprodFecha = comprodFecha;
	}

	public BigDecimal getComprodIva() {
		return this.comprodIva;
	}

	public void setComprodIva(BigDecimal comprodIva) {
		this.comprodIva = comprodIva;
	}

	public BigDecimal getComprodSubtotal() {
		return this.comprodSubtotal;
	}

	public void setComprodSubtotal(BigDecimal comprodSubtotal) {
		this.comprodSubtotal = comprodSubtotal;
	}

	public BigDecimal getComprodTotal() {
		return this.comprodTotal;
	}

	public void setComprodTotal(BigDecimal comprodTotal) {
		this.comprodTotal = comprodTotal;
	}

	public CabeceraCompra getCabeceraCompra() {
		return this.cabeceraCompra;
	}

	public void setCabeceraCompra(CabeceraCompra cabeceraCompra) {
		this.cabeceraCompra = cabeceraCompra;
	}

	public List<DetalleCompra> getDetalleCompras() {
		return this.detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public DetalleCompra addDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().add(detalleCompra);
		detalleCompra.setCompraProducto(this);

		return detalleCompra;
	}

	public DetalleCompra removeDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().remove(detalleCompra);
		detalleCompra.setCompraProducto(null);

		return detalleCompra;
	}

}