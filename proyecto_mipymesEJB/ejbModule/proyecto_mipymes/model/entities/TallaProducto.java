package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the talla_producto database table.
 * 
 */
@Entity
@Table(name="talla_producto")
@NamedQuery(name="TallaProducto.findAll", query="SELECT t FROM TallaProducto t")
public class TallaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_talla_producto", unique=true, nullable=false)
	private Integer idTallaProducto;

	@Column(name="talla_nombre", nullable=false, length=15)
	private String tallaNombre;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="tallaProducto")
	private List<Producto> productos;

	public TallaProducto() {
	}

	public Integer getIdTallaProducto() {
		return this.idTallaProducto;
	}

	public void setIdTallaProducto(Integer idTallaProducto) {
		this.idTallaProducto = idTallaProducto;
	}

	public String getTallaNombre() {
		return this.tallaNombre;
	}

	public void setTallaNombre(String tallaNombre) {
		this.tallaNombre = tallaNombre;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setTallaProducto(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setTallaProducto(null);

		return producto;
	}

}