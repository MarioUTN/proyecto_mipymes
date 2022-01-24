package proyecto_mipymes.model.comprasproveedor.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.metamodel.ListAttribute;

import proyecto_mipymes.controller.util.JSFUtil;
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

	public Producto crearNuevoProducto(Producto producto) {
		if (entityManager.find(Producto.class, producto.getIdProducto()) == null
				|| findProductoByCodigo(producto.getProdCodigo()) == null) {
			entityManager.persist(producto);
		}
		return producto;
	}

	public BigDecimal calcularSubtotal(int cant, BigDecimal precio) {
		BigDecimal f = precio.multiply(new BigDecimal(cant));
		double e = (f).doubleValue() / 1.12;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public BigDecimal CalcularTotal(BigDecimal subtotal, BigDecimal iva) {
		double e = (subtotal).doubleValue() + iva.doubleValue();
		return new BigDecimal(e).round(new MathContext(5));
	}

	public List<DetalleCompra> agregarProducto(List<DetalleCompra> detalleCompra, int idProducto, int cantidad) {

		Producto producto = entityManager.find(Producto.class, idProducto);
		DetalleCompra detalle = new DetalleCompra();
		detalle.setDetcompCodigoProducto(producto.getProdCodigo());
		detalle.setDetcompNombreProducto(producto.getProdNombre());
		detalle.setDetcompDescripcion(producto.getProdDescripcion());
		detalle.setDetcompCantidad(cantidad);
		detalle.setDetcompPrecioUnit(producto.getProdPvproveedor());
		detalle.setDetcompPrecioTotal(producto.getProdPvproveedor().multiply(new BigDecimal(cantidad)));
		detalleCompra.add(detalle);
		return detalleCompra;
	}

	public List<DetalleCompra> agregarNuevoProducto(List<DetalleCompra> detalleCompra, String codico_producto,
			String nombreProducto, String descripcionProducto, double precio, int cantidad) {
		DetalleCompra detalle = new DetalleCompra();
		detalle.setDetcompNombreProducto(nombreProducto);
		detalle.setDetcompCantidad(cantidad);
		detalle.setDetcompDescripcion(descripcionProducto);
		detalle.setDetcompCodigoProducto(codico_producto);
		detalle.setDetcompPrecioUnit(new BigDecimal(precio));
		detalle.setDetcompPrecioTotal(calcularPrecioTotal(cantidad, precio));
		detalle.setDetcompPrecioTotal((CalcularTotal(new BigDecimal(cantidad), new BigDecimal(precio))));
		detalle.setDetcompPrecioUnit(new BigDecimal(precio));
		detalleCompra.add(detalle);
		return detalleCompra;

	}

	public BigDecimal calcularPrecioTotal(int cant, double precio) {
		double e = cant * precio;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public double calcularTotal(List<DetalleCompra> detalleCompra) {
		double total = 0;
		for (DetalleCompra detalle : detalleCompra) {
			total += detalle.getDetcompPrecioTotal().doubleValue();
		}
		return total;
	}

	public BigDecimal CalcularIva(BigDecimal subtotal) {
		double e = (subtotal).doubleValue() * 0.12;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public double calcularSubTotalCompra(double total) {

		return formatearDecimales(total / 1.12, 3);
	}

	public double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

	public double valorSubTotal(double total) {
		double subTotal = total / 1.12;

		return formatearDecimales(subTotal, 2);
	}

	public double valorIva(double total) {
		double iva = total - calcularSubTotalCompra(total);
		return formatearDecimales(iva, 2);
	}

	public double valorTotalPagar(List<DetalleCompra> listaDetalleCompras) {
		double valorTotal = 0;
		for (DetalleCompra detalleFactura : listaDetalleCompras) {
			valorTotal += detalleFactura.getDetcompPrecioTotal().doubleValue();
		}
		return formatearDecimales(valorTotal, 2);
	}

	public List<DetalleCompra> eliminarProductoListaDetalle(List<DetalleCompra> listaDetallecompras, int index) {
		listaDetallecompras.remove(index);
		return listaDetallecompras;
	}

	public List<DetalleCompra> editarCantidadProductoListaDetalle(List<DetalleCompra> listaDetallecompras, int cantidad,
			int index) {

		listaDetallecompras.get(index).setDetcompCantidad(cantidad);
		listaDetallecompras.get(index).setDetcompPrecioTotal(
				calcularPrecioTotal(cantidad, listaDetallecompras.get(index).getDetcompPrecioUnit().doubleValue()));

		return listaDetallecompras;
	}

	public CompraProducto insertarPedido(List<DetalleCompra> detalleCompra, int proveedor, int idVendedor) {
		CompraProducto comprapro = new CompraProducto();
		CabeceraCompra cabeceraCompra = ingresarCabeceraCompra(idVendedor, proveedor);
		entityManager.persist(cabeceraCompra);
		comprapro.setCabeceraCompra(cabeceraCompra);
		comprapro.setComprodAprobado(false);
		comprapro.setComprodFecha(new Date());
		comprapro.setComprodSubtotal(new BigDecimal(calcularSubTotalCompra(calcularTotal(detalleCompra))));
		comprapro.setComprodTotal(new BigDecimal(calcularTotal(detalleCompra)));
		comprapro.setComprodIva(CalcularIva(comprapro.getComprodSubtotal()));
		entityManager.persist(comprapro);
		for (DetalleCompra detalle : detalleCompra) {
			detalle.setCompraProducto(comprapro);
			entityManager.persist(detalle);
		}
		return comprapro;
	}

	public CabeceraCompra ingresarCabeceraCompra(int idVendedor, int idprovedor) {
		Empresa proveedor=entityManager.find(Empresa.class, idprovedor);
		CabeceraCompra cabeceraCompra = new CabeceraCompra();
		cabeceraCompra.setEmpresa(proveedor);
		cabeceraCompra.setVendedor(entityManager.find(Vendedor.class, idVendedor));
//		entityManager.persist(cabeceraCompra);
		return cabeceraCompra;
	}

	public Empresa agregarProveedor(Empresa empresaNueva, int idGerente) {
		Gerente gerente = entityManager.find(Gerente.class, idGerente);
		JSFUtil.crearMensajeInfo(" Hola " + findEmpresaByRuc(empresaNueva.getEmpRuc()).getEmpCiudad());
		if (findEmpresaByRuc(empresaNueva.getEmpRuc()).getEmpRuc() == null) {
			empresaNueva.setGerente(gerente);
			empresaNueva.setEmpFechaInicio(new Date());
			entityManager.persist(empresaNueva);
			return empresaNueva;
		} else {
			return null;
		}

	}

	public Empresa findEmpresaByRuc(String ruc) {
		Query query = entityManager.createQuery("select e from Empresa e where e.empRuc='" + ruc + "'", Empresa.class);
		Empresa empresa = new Empresa();
		try {
			empresa = (Empresa) query.getSingleResult();
			if (empresa != null) {
				return empresa;
			} else {
				empresa = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return empresa;
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