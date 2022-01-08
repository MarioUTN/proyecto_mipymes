package proyecto_mipymes.model.comprasproveedor.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.model.entities.*;

/**
 * Session Bean implementation class ManagerCompras
 */
@Stateless
@LocalBean
public class ManagerCompras {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerCompras() {
		// TODO Auto-generated constructor stub
	}

	public List<Vendedor> findAllVendedores() {
		return entityManager.createNamedQuery("Vendedor.findAll", Vendedor.class).getResultList();
	}

	public List<Empresa> findAllEmpresas() {
		return entityManager.createNamedQuery("Empresa.findAll", Empresa.class).getResultList();
	}

	public List<Producto> findAllProductos() {
		return entityManager.createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}

	public List<Gerente> findAllGerente() {
		return entityManager.createNamedQuery("Gerente.findAll", Gerente.class).getResultList();
	}

	public List<CompraProducto> findAllCompraProductos() {
		return entityManager.createNamedQuery("CompraProducto.findAll", CompraProducto.class).getResultList();
	}

	public List<DetalleCompra> findAllDetallesCompras() {
		return entityManager.createNamedQuery("DetalleCompra.findAll", DetalleCompra.class).getResultList();
	}

	
	
	public Producto findProductoByCodigo(String codigo) {
		Query query = entityManager.createQuery("select p from Producto p where p.prodCodigo=:codigo", Producto.class);
		query.setParameter("codigo", codigo);
		return (Producto) query.getSingleResult();
	}
	
	public Empresa findEmpresaByRuc(String ruc) {
		Query query = entityManager.createQuery("select p from Producto p where p.prodCodigo=:codigo", Producto.class);
		query.setParameter("codigo", ruc);
		return (Empresa) query.getSingleResult();
	}

	public Producto crearNuevoProducto(Producto producto) {
		if (entityManager.find(Producto.class, producto.getIdProducto()) == null
				|| findProductoByCodigo(producto.getProdCodigo()) == null) {
			entityManager.persist(producto);
		}
		return producto;
	}

	public CabeceraCompra ingresarCabeceraCompra(CabeceraCompra cabeceraCompra) {
		entityManager.persist(cabeceraCompra);
		return cabeceraCompra;
	}

	public BigDecimal calcularSubtotal(int cant, BigDecimal precio) {
		BigDecimal f = precio.multiply(new BigDecimal(cant));
		double e = (f).doubleValue() / 1.12;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public BigDecimal CalcularIva(BigDecimal subtotal) {
		double e = (subtotal).doubleValue() * 0.12;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public BigDecimal CalcularTotal(BigDecimal subtotal, BigDecimal iva) {
		double e = (subtotal).doubleValue() + iva.doubleValue();
		return new BigDecimal(e).round(new MathContext(5));
	}

	public CompraProducto agregarProductos(CabeceraCompra cabeceraCompra, CompraProducto compraProducto,
			Vendedor vendedor, Empresa empresa, String codigo_producto, int cantidad, String nombre_producto,
			String descripcion_producto, double precio_unitario) {
		if (compraProducto == null) {
			compraProducto = new CompraProducto();
			compraProducto.setDetalleCompras(new ArrayList<DetalleCompra>());
			compraProducto.setCabeceraCompra(cabeceraCompra);
			compraProducto.setComprodAprobado(false);
			compraProducto.setComprodFecha(new Date());
			compraProducto.setComprodSubtotal(new BigDecimal(0));
			compraProducto.setComprodIva(new BigDecimal(0));
			compraProducto.setComprodTotal(new BigDecimal(0));
		}
		Producto producto = entityManager.find(Producto.class, codigo_producto);
		compraProducto.setComprodSubtotal(
				compraProducto.getComprodSubtotal().add(calcularSubtotal(cantidad, producto.getProdPvpublico())));
		compraProducto
				.setComprodIva(compraProducto.getComprodIva().add(CalcularIva(compraProducto.getComprodSubtotal())));
		compraProducto.setComprodTotal(compraProducto.getComprodTotal()
				.add(CalcularTotal(compraProducto.getComprodSubtotal(), compraProducto.getComprodIva())));
		DetalleCompra detalleCompra = new DetalleCompra();
		if (producto != null) {
			detalleCompra.setCompraProducto(compraProducto);
			detalleCompra.setDetcompCodigoProducto(producto.getProdCodigo());
			detalleCompra.setDetcompCantidad(cantidad);
			detalleCompra.setDetcompNombreProducto(producto.getProdNombre());
			detalleCompra.setDetcompDescripcion(producto.getProdDescripcion());
			detalleCompra.setDetcompPrecioUnit(producto.getProdPvproveedor());
			detalleCompra.setDetcompPrecioTotal(new BigDecimal(cantidad * producto.getProdPvproveedor().doubleValue()));
			compraProducto.getDetalleCompras().add(detalleCompra);
		}
		detalleCompra.setCompraProducto(compraProducto);
		detalleCompra.setDetcompCodigoProducto(codigo_producto);
		detalleCompra.setDetcompCantidad(cantidad);
		detalleCompra.setDetcompNombreProducto(nombre_producto);
		detalleCompra.setDetcompDescripcion(descripcion_producto);
		detalleCompra.setDetcompPrecioUnit(new BigDecimal(precio_unitario));
		detalleCompra.setDetcompPrecioTotal(new BigDecimal(cantidad * precio_unitario));
		compraProducto.getDetalleCompras().add(detalleCompra);
		return compraProducto;
	}

	public Empresa agregarProveedor(Empresa empresaNueva) {
		if(entityManager.find(Empresa.class, empresaNueva.getIdEmpresa())==null) {
			entityManager.persist(empresaNueva);
		}
		return empresaNueva;
	}
	
	public boolean registrarCompraProductos(CompraProducto compraProducto) {
		boolean resp;
		if (compraProducto == null || compraProducto.getDetalleCompras().size() == 0
				|| compraProducto.getDetalleCompras() == null) {
			resp = false;
		} else {
			entityManager.persist(compraProducto);
			resp = true;
		}
		return resp;
	}
}
