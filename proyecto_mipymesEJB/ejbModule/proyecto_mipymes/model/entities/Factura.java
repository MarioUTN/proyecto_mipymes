package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name="factura")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_factura", unique=true, nullable=false)
	private Integer idFactura;

	@Column(name="fact_descuento", precision=4, scale=2)
	private BigDecimal factDescuento;

	@Column(name="fact_entregado", nullable=false)
	private Boolean factEntregado;

	@Column(name="fact_estado", nullable=false)
	private Boolean factEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="fact_fecha_autorizacion", nullable=false)
	private Date factFechaAutorizacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fact_fecha_emision", nullable=false)
	private Date factFechaEmision;

	@Temporal(TemporalType.DATE)
	@Column(name="fact_fecha_remision", nullable=false)
	private Date factFechaRemision;

	@Column(name="fact_iva", precision=8, scale=2)
	private BigDecimal factIva;

	@Column(name="fact_numero_factura", nullable=false, length=20)
	private String factNumeroFactura;

	@Column(name="fact_subtotal", precision=8, scale=2)
	private BigDecimal factSubtotal;

	@Column(name="fact_total", precision=8, scale=2)
	private BigDecimal factTotal;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="factura")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to EstadoPedido
	@OneToMany(mappedBy="factura")
	private List<EstadoPedido> estadoPedidos;

	//bi-directional many-to-one association to CabeceraFactura
	@ManyToOne
	@JoinColumn(name="fact_cabecera_factura", nullable=false)
	private CabeceraFactura cabeceraFactura;

	//bi-directional many-to-one association to FormaPago
	@ManyToOne
	@JoinColumn(name="fact_forma_pago", nullable=false)
	private FormaPago formaPago;

	//bi-directional many-to-one association to TipoFactura
	@ManyToOne
	@JoinColumn(name="fact_tipo_factura", nullable=false)
	private TipoFactura tipoFactura;

	public Factura() {
	}

	public Integer getIdFactura() {
		return this.idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public BigDecimal getFactDescuento() {
		return this.factDescuento;
	}

	public void setFactDescuento(BigDecimal factDescuento) {
		this.factDescuento = factDescuento;
	}

	public Boolean getFactEntregado() {
		return this.factEntregado;
	}

	public void setFactEntregado(Boolean factEntregado) {
		this.factEntregado = factEntregado;
	}

	public Boolean getFactEstado() {
		return this.factEstado;
	}

	public void setFactEstado(Boolean factEstado) {
		this.factEstado = factEstado;
	}

	public Date getFactFechaAutorizacion() {
		return this.factFechaAutorizacion;
	}

	public void setFactFechaAutorizacion(Date factFechaAutorizacion) {
		this.factFechaAutorizacion = factFechaAutorizacion;
	}

	public Date getFactFechaEmision() {
		return this.factFechaEmision;
	}

	public void setFactFechaEmision(Date factFechaEmision) {
		this.factFechaEmision = factFechaEmision;
	}

	public Date getFactFechaRemision() {
		return this.factFechaRemision;
	}

	public void setFactFechaRemision(Date factFechaRemision) {
		this.factFechaRemision = factFechaRemision;
	}

	public BigDecimal getFactIva() {
		return this.factIva;
	}

	public void setFactIva(BigDecimal factIva) {
		this.factIva = factIva;
	}

	public String getFactNumeroFactura() {
		return this.factNumeroFactura;
	}

	public void setFactNumeroFactura(String factNumeroFactura) {
		this.factNumeroFactura = factNumeroFactura;
	}

	public BigDecimal getFactSubtotal() {
		return this.factSubtotal;
	}

	public void setFactSubtotal(BigDecimal factSubtotal) {
		this.factSubtotal = factSubtotal;
	}

	public BigDecimal getFactTotal() {
		return this.factTotal;
	}

	public void setFactTotal(BigDecimal factTotal) {
		this.factTotal = factTotal;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setFactura(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setFactura(null);

		return detalleFactura;
	}

	public List<EstadoPedido> getEstadoPedidos() {
		return this.estadoPedidos;
	}

	public void setEstadoPedidos(List<EstadoPedido> estadoPedidos) {
		this.estadoPedidos = estadoPedidos;
	}

	public EstadoPedido addEstadoPedido(EstadoPedido estadoPedido) {
		getEstadoPedidos().add(estadoPedido);
		estadoPedido.setFactura(this);

		return estadoPedido;
	}

	public EstadoPedido removeEstadoPedido(EstadoPedido estadoPedido) {
		getEstadoPedidos().remove(estadoPedido);
		estadoPedido.setFactura(null);

		return estadoPedido;
	}

	public CabeceraFactura getCabeceraFactura() {
		return this.cabeceraFactura;
	}

	public void setCabeceraFactura(CabeceraFactura cabeceraFactura) {
		this.cabeceraFactura = cabeceraFactura;
	}

	public FormaPago getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public TipoFactura getTipoFactura() {
		return this.tipoFactura;
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

}