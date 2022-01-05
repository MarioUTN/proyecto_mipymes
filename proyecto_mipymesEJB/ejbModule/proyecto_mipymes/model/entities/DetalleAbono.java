package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the detalle_abono database table.
 * 
 */
@Entity
@Table(name="detalle_abono")
@NamedQuery(name="DetalleAbono.findAll", query="SELECT d FROM DetalleAbono d")
public class DetalleAbono implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_detalle_abono", unique=true, nullable=false)
	private Integer idDetalleAbono;

	@Column(name="detab_abono", nullable=false, precision=8, scale=2)
	private BigDecimal detabAbono;

	@Temporal(TemporalType.DATE)
	@Column(name="detab_fecha_abono", nullable=false)
	private Date detabFechaAbono;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="detab_cliente", nullable=false)
	private Cliente cliente;

	//bi-directional many-to-one association to EstadoPedido
	@ManyToOne
	@JoinColumn(name="detab_estado_pedido", nullable=false)
	private EstadoPedido estadoPedido;

	//bi-directional many-to-one association to Vendedor
	@ManyToOne
	@JoinColumn(name="detab_vendedor", nullable=false)
	private Vendedor vendedor;

	public DetalleAbono() {
	}

	public Integer getIdDetalleAbono() {
		return this.idDetalleAbono;
	}

	public void setIdDetalleAbono(Integer idDetalleAbono) {
		this.idDetalleAbono = idDetalleAbono;
	}

	public BigDecimal getDetabAbono() {
		return this.detabAbono;
	}

	public void setDetabAbono(BigDecimal detabAbono) {
		this.detabAbono = detabAbono;
	}

	public Date getDetabFechaAbono() {
		return this.detabFechaAbono;
	}

	public void setDetabFechaAbono(Date detabFechaAbono) {
		this.detabFechaAbono = detabFechaAbono;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoPedido getEstadoPedido() {
		return this.estadoPedido;
	}

	public void setEstadoPedido(EstadoPedido estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public Vendedor getVendedor() {
		return this.vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

}