package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the forma_pago database table.
 * 
 */
@Entity
@Table(name="forma_pago")
@NamedQuery(name="FormaPago.findAll", query="SELECT f FROM FormaPago f")
public class FormaPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_forma_pago", unique=true, nullable=false)
	private Integer idFormaPago;

	@Column(name="fp_nombre", nullable=false, length=50)
	private String fpNombre;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="formaPago")
	private List<Factura> facturas;

	public FormaPago() {
	}

	public Integer getIdFormaPago() {
		return this.idFormaPago;
	}

	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public String getFpNombre() {
		return this.fpNombre;
	}

	public void setFpNombre(String fpNombre) {
		this.fpNombre = fpNombre;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setFormaPago(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setFormaPago(null);

		return factura;
	}

}