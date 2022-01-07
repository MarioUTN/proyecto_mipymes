package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name="producto")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto", unique=true, nullable=false)
	private Integer idProducto;

	@Column(name="prod_cantidad", nullable=false)
	private Integer prodCantidad;

	@Column(name="prod_codigo", nullable=false, length=15)
	private String prodCodigo;

	@Column(name="prod_descripcion", nullable=false, length=100)
	private String prodDescripcion;

	@Column(name="prod_imagen", length=100)
	private String prodImagen;

	@Column(name="prod_iva", nullable=false, precision=4, scale=2)
	private BigDecimal prodIva;

	@Column(name="prod_nombre", nullable=false, length=60)
	private String prodNombre;

	@Column(name="prod_pvproveedor", nullable=false, precision=7, scale=2)
	private BigDecimal prodPvproveedor;

	@Column(name="prod_pvpublico", nullable=false, precision=7, scale=2)
	private BigDecimal prodPvpublico;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="producto")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to DetalleIngreso
	@OneToMany(mappedBy="producto")
	private List<DetalleIngreso> detalleIngresos;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="prod_proveedor", nullable=false)
	private Empresa empresa;

	//bi-directional many-to-one association to TallaProducto
	@ManyToOne
	@JoinColumn(name="prod_talla_producto", nullable=false)
	private TallaProducto tallaProducto;

	//bi-directional many-to-one association to TipoProducto
	@ManyToOne
	@JoinColumn(name="prod_tipo_producto", nullable=false)
	private TipoProducto tipoProducto;

	public Producto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Integer getProdCantidad() {
		return this.prodCantidad;
	}

	public void setProdCantidad(Integer prodCantidad) {
		this.prodCantidad = prodCantidad;
	}

	public String getProdCodigo() {
		return this.prodCodigo;
	}

	public void setProdCodigo(String prodCodigo) {
		this.prodCodigo = prodCodigo;
	}

	public String getProdDescripcion() {
		return this.prodDescripcion;
	}

	public void setProdDescripcion(String prodDescripcion) {
		this.prodDescripcion = prodDescripcion;
	}

	public String getProdImagen() {
		return this.prodImagen;
	}

	public void setProdImagen(String prodImagen) {
		this.prodImagen = prodImagen;
	}

	public BigDecimal getProdIva() {
		return this.prodIva;
	}

	public void setProdIva(BigDecimal prodIva) {
		this.prodIva = prodIva;
	}

	public String getProdNombre() {
		return this.prodNombre;
	}

	public void setProdNombre(String prodNombre) {
		this.prodNombre = prodNombre;
	}

	public BigDecimal getProdPvproveedor() {
		return this.prodPvproveedor;
	}

	public void setProdPvproveedor(BigDecimal prodPvproveedor) {
		this.prodPvproveedor = prodPvproveedor;
	}

	public BigDecimal getProdPvpublico() {
		return this.prodPvpublico;
	}

	public void setProdPvpublico(BigDecimal prodPvpublico) {
		this.prodPvpublico = prodPvpublico;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setProducto(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setProducto(null);

		return detalleFactura;
	}

	public List<DetalleIngreso> getDetalleIngresos() {
		return this.detalleIngresos;
	}

	public void setDetalleIngresos(List<DetalleIngreso> detalleIngresos) {
		this.detalleIngresos = detalleIngresos;
	}

	public DetalleIngreso addDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().add(detalleIngreso);
		detalleIngreso.setProducto(this);

		return detalleIngreso;
	}

	public DetalleIngreso removeDetalleIngreso(DetalleIngreso detalleIngreso) {
		getDetalleIngresos().remove(detalleIngreso);
		detalleIngreso.setProducto(null);

		return detalleIngreso;
	}

	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TallaProducto getTallaProducto() {
		return this.tallaProducto;
	}

	public void setTallaProducto(TallaProducto tallaProducto) {
		this.tallaProducto = tallaProducto;
	}

	public TipoProducto getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}