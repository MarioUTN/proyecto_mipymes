package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cabecera_compra database table.
 * 
 */
@Entity
@Table(name="cabecera_compra")
@NamedQuery(name="CabeceraCompra.findAll", query="SELECT c FROM CabeceraCompra c")
public class CabeceraCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cabecera_compra", unique=true, nullable=false)
	private Integer idCabeceraCompra;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="cabcomp_proveedor", nullable=false)
	private Empresa empresa;

	//bi-directional many-to-one association to VendedorDTO
	@ManyToOne
	@JoinColumn(name="cabcomp_vendedor", nullable=false)
	private Vendedor vendedor;

	//bi-directional many-to-one association to CompraProducto
	@OneToMany(mappedBy="cabeceraCompra")
	private List<CompraProducto> compraProductos;

	public CabeceraCompra() {
	}

	public Integer getIdCabeceraCompra() {
		return this.idCabeceraCompra;
	}

	public void setIdCabeceraCompra(Integer idCabeceraCompra) {
		this.idCabeceraCompra = idCabeceraCompra;
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

	public List<CompraProducto> getCompraProductos() {
		return this.compraProductos;
	}

	public void setCompraProductos(List<CompraProducto> compraProductos) {
		this.compraProductos = compraProductos;
	}

	public CompraProducto addCompraProducto(CompraProducto compraProducto) {
		getCompraProductos().add(compraProducto);
		compraProducto.setCabeceraCompra(this);

		return compraProducto;
	}

	public CompraProducto removeCompraProducto(CompraProducto compraProducto) {
		getCompraProductos().remove(compraProducto);
		compraProducto.setCabeceraCompra(null);

		return compraProducto;
	}

}