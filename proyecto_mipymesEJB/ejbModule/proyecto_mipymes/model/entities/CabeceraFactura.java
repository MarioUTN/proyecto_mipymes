package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cabecera_factura database table.
 * 
 */
@Entity
@Table(name="cabecera_factura")
@NamedQuery(name="CabeceraFactura.findAll", query="SELECT c FROM CabeceraFactura c")
public class CabeceraFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cabecera_factura", unique=true, nullable=false)
	private Integer idCabeceraFactura;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cab_cliente", nullable=false)
	private Cliente cliente;

	//bi-directional many-to-one association to Empresa
	@ManyToOne
	@JoinColumn(name="cab_empresa", nullable=false)
	private Empresa empresa;

	//bi-directional many-to-one association to VendedorDTO
	@ManyToOne
	@JoinColumn(name="cab_vendedor", nullable=false)
	private Vendedor vendedor;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="cabeceraFactura")
	private List<Factura> facturas;

	public CabeceraFactura() {
	}

	public Integer getIdCabeceraFactura() {
		return this.idCabeceraFactura;
	}

	public void setIdCabeceraFactura(Integer idCabeceraFactura) {
		this.idCabeceraFactura = idCabeceraFactura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setCabeceraFactura(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setCabeceraFactura(null);

		return factura;
	}

}