package proyecto_mipymes.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Table(name = "empresa")
@NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", unique = true, nullable = false)
	private Integer idEmpresa;

	@Column(name = "emp_ciudad", nullable = false, length = 50)
	private String empCiudad;

	@Column(name = "emp_email", nullable = false, length = 60)
	private String empEmail;

	@Temporal(TemporalType.DATE)
	@Column(name = "emp_fecha_inicio", nullable = false)
	private Date empFechaInicio;

	@Column(name = "emp_matriz", nullable = false, length = 100)
	private String empMatriz;

	@Column(name = "emp_nombre_empresa", nullable = false, length = 100)
	private String empNombreEmpresa;

	@Column(name = "emp_pais", nullable = false, length = 50)
	private String empPais;

	@Column(name = "emp_provincia", nullable = false, length = 50)
	private String empProvincia;

	@Column(name = "emp_ruc", nullable = false, length = 13)
	private String empRuc;

	@Column(name = "emp_sucursal", length = 100)
	private String empSucursal;

	@Column(name = "emp_telefono", nullable = false, length = 15)
	private String empTelefono;

	// bi-directional many-to-one association to CabeceraCompra
	@OneToMany(mappedBy = "empresa")
	private List<CabeceraCompra> cabeceraCompras;

	// bi-directional many-to-one association to CabeceraFactura
	@OneToMany(mappedBy = "empresa")
	private List<CabeceraFactura> cabeceraFacturas;

	// bi-directional many-to-one association to CabeceraIngreso
	@OneToMany(mappedBy = "proveedor")
	private List<CabeceraIngreso> cabeceraIngresosProveedors;

	// bi-directional many-to-one association to CabeceraIngreso
	@OneToMany(mappedBy = "empresa")
	private List<CabeceraIngreso> cabeceraIngresosEmpresas;

	// bi-directional many-to-one association to Gerente
	@ManyToOne
	@JoinColumn(name = "emp_gerente", nullable = false)
	private Gerente gerente;

	// bi-directional many-to-one association to Producto
	@OneToMany(mappedBy = "empresa")
	private List<Producto> productos;

	public Empresa() {
	}

	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getEmpCiudad() {
		return this.empCiudad;
	}

	public void setEmpCiudad(String empCiudad) {
		this.empCiudad = empCiudad;
	}

	public String getEmpEmail() {
		return this.empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public Date getEmpFechaInicio() {
		return this.empFechaInicio;
	}

	public void setEmpFechaInicio(Date empFechaInicio) {
		this.empFechaInicio = empFechaInicio;
	}

	public String getEmpMatriz() {
		return this.empMatriz;
	}

	public void setEmpMatriz(String empMatriz) {
		this.empMatriz = empMatriz;
	}

	public String getEmpNombreEmpresa() {
		return this.empNombreEmpresa;
	}

	public void setEmpNombreEmpresa(String empNombreEmpresa) {
		this.empNombreEmpresa = empNombreEmpresa;
	}

	public String getEmpPais() {
		return this.empPais;
	}

	public void setEmpPais(String empPais) {
		this.empPais = empPais;
	}

	public String getEmpProvincia() {
		return this.empProvincia;
	}

	public void setEmpProvincia(String empProvincia) {
		this.empProvincia = empProvincia;
	}

	public String getEmpRuc() {
		return this.empRuc;
	}

	public void setEmpRuc(String empRuc) {
		this.empRuc = empRuc;
	}

	public String getEmpSucursal() {
		return this.empSucursal;
	}

	public void setEmpSucursal(String empSucursal) {
		this.empSucursal = empSucursal;
	}

	public String getEmpTelefono() {
		return this.empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}

	public List<CabeceraCompra> getCabeceraCompras() {
		return this.cabeceraCompras;
	}

	public void setCabeceraCompras(List<CabeceraCompra> cabeceraCompras) {
		this.cabeceraCompras = cabeceraCompras;
	}

	public CabeceraCompra addCabeceraCompra(CabeceraCompra cabeceraCompra) {
		getCabeceraCompras().add(cabeceraCompra);
		cabeceraCompra.setEmpresa(this);

		return cabeceraCompra;
	}

	public CabeceraCompra removeCabeceraCompra(CabeceraCompra cabeceraCompra) {
		getCabeceraCompras().remove(cabeceraCompra);
		cabeceraCompra.setEmpresa(null);

		return cabeceraCompra;
	}

	public List<CabeceraFactura> getCabeceraFacturas() {
		return this.cabeceraFacturas;
	}

	public void setCabeceraFacturas(List<CabeceraFactura> cabeceraFacturas) {
		this.cabeceraFacturas = cabeceraFacturas;
	}

	public CabeceraFactura addCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().add(cabeceraFactura);
		cabeceraFactura.setEmpresa(this);

		return cabeceraFactura;
	}

	public CabeceraFactura removeCabeceraFactura(CabeceraFactura cabeceraFactura) {
		getCabeceraFacturas().remove(cabeceraFactura);
		cabeceraFactura.setEmpresa(null);

		return cabeceraFactura;
	}

	public List<CabeceraIngreso> getCabeceraIngresosProveedors() {
		return this.cabeceraIngresosProveedors;
	}

	public void setCabeceraIngresosProveedors(List<CabeceraIngreso> cabeceraIngresosProveedors) {
		this.cabeceraIngresosProveedors = cabeceraIngresosProveedors;
	}

	public CabeceraIngreso addCabeceraIngresosProveedors(CabeceraIngreso cabeceraIngresosProveedors) {
		getCabeceraIngresosProveedors().add(cabeceraIngresosProveedors);
		cabeceraIngresosProveedors.setProveedor(this);

		return cabeceraIngresosProveedors;
	}

	public CabeceraIngreso removeCabeceraIngresosProveedors(CabeceraIngreso cabeceraIngresosProveedors) {
		getCabeceraIngresosProveedors().remove(cabeceraIngresosProveedors);
		cabeceraIngresosProveedors.setProveedor(null);

		return cabeceraIngresosProveedors;
	}

	public List<CabeceraIngreso> getCabeceraIngresosEmpresas() {
		return this.cabeceraIngresosEmpresas;
	}

	public void setCabeceraIngresosEmpresas(List<CabeceraIngreso> cabeceraIngresosEmpresas) {
		this.cabeceraIngresosEmpresas = cabeceraIngresosEmpresas;
	}

	public CabeceraIngreso addCabeceraIngresosEmpresas(CabeceraIngreso cabeceraIngresosEmpresas) {
		getCabeceraIngresosEmpresas().add(cabeceraIngresosEmpresas);
		cabeceraIngresosEmpresas.setProveedor(this);

		return cabeceraIngresosEmpresas;
	}

	public CabeceraIngreso removeCabeceraIngresosEmpresas(CabeceraIngreso cabeceraIngresosEmpresas) {
		getCabeceraIngresosEmpresas().remove(cabeceraIngresosEmpresas);
		cabeceraIngresosEmpresas.setProveedor(null);

		return cabeceraIngresosEmpresas;
	}

	public Gerente getGerente() {
		return this.gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setEmpresa(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setEmpresa(null);

		return producto;
	}

}