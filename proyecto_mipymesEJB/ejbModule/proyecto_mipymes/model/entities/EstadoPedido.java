package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the estado_pedido database table.
 * 
 */
@Entity
@Table(name="estado_pedido")
@NamedQuery(name="EstadoPedido.findAll", query="SELECT e FROM EstadoPedido e")
public class EstadoPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado_pedido", unique=true, nullable=false)
	private Integer idEstadoPedido;

	@Temporal(TemporalType.DATE)
	@Column(name="est_fecha_emision", nullable=false)
	private Date estFechaEmision;

	@Column(name="est_saldo", nullable=false, precision=8, scale=2)
	private BigDecimal estSaldo;

	@Column(name="est_valor_total", nullable=false, precision=8, scale=2)
	private BigDecimal estValorTotal;

	//bi-directional many-to-one association to DetalleAbono
	@OneToMany(mappedBy="estadoPedido")
	private List<DetalleAbono> detalleAbonos;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="est_factura", nullable=false)
	private Factura factura;

	public EstadoPedido() {
	}

	public Integer getIdEstadoPedido() {
		return this.idEstadoPedido;
	}

	public void setIdEstadoPedido(Integer idEstadoPedido) {
		this.idEstadoPedido = idEstadoPedido;
	}

	public Date getEstFechaEmision() {
		return this.estFechaEmision;
	}

	public void setEstFechaEmision(Date estFechaEmision) {
		this.estFechaEmision = estFechaEmision;
	}

	public BigDecimal getEstSaldo() {
		return this.estSaldo;
	}

	public void setEstSaldo(BigDecimal estSaldo) {
		this.estSaldo = estSaldo;
	}

	public BigDecimal getEstValorTotal() {
		return this.estValorTotal;
	}

	public void setEstValorTotal(BigDecimal estValorTotal) {
		this.estValorTotal = estValorTotal;
	}

	public List<DetalleAbono> getDetalleAbonos() {
		return this.detalleAbonos;
	}

	public void setDetalleAbonos(List<DetalleAbono> detalleAbonos) {
		this.detalleAbonos = detalleAbonos;
	}

	public DetalleAbono addDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().add(detalleAbono);
		detalleAbono.setEstadoPedido(this);

		return detalleAbono;
	}

	public DetalleAbono removeDetalleAbono(DetalleAbono detalleAbono) {
		getDetalleAbonos().remove(detalleAbono);
		detalleAbono.setEstadoPedido(null);

		return detalleAbono;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

}