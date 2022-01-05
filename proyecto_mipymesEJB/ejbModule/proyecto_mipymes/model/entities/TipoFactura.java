package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_factura database table.
 * 
 */
@Entity
@Table(name="tipo_factura")
@NamedQuery(name="TipoFactura.findAll", query="SELECT t FROM TipoFactura t")
public class TipoFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo_factura", unique=true, nullable=false)
	private Integer idTipoFactura;

	@Column(name="tf_nombre", nullable=false, length=50)
	private String tfNombre;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="tipoFactura")
	private List<Factura> facturas;

	public TipoFactura() {
	}

	public Integer getIdTipoFactura() {
		return this.idTipoFactura;
	}

	public void setIdTipoFactura(Integer idTipoFactura) {
		this.idTipoFactura = idTipoFactura;
	}

	public String getTfNombre() {
		return this.tfNombre;
	}

	public void setTfNombre(String tfNombre) {
		this.tfNombre = tfNombre;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setTipoFactura(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setTipoFactura(null);

		return factura;
	}

}