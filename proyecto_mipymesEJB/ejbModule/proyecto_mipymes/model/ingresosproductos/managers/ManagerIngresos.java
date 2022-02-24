package proyecto_mipymes.model.ingresosproductos.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.CabeceraIngreso;
import proyecto_mipymes.model.entities.DetalleCompra;
import proyecto_mipymes.model.entities.DetalleFactura;
import proyecto_mipymes.model.entities.DetalleIngreso;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.FacturaIngreso;
import proyecto_mipymes.model.entities.Gerente;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TallaProducto;
import proyecto_mipymes.model.entities.TipoProducto;
import proyecto_mipymes.model.entities.Vendedor;

/**
 * Session Bean implementation class ManagerIngresos
 */
@Stateless
@LocalBean
public class ManagerIngresos {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerIngresos() {
		// TODO Auto-generated constructor stub
	}

	public List<DetalleIngreso> findAllDetallesIngresos() {
		return entityManager.createNamedQuery("DetalleIngreso.findAll", DetalleIngreso.class).getResultList();
	}

	public List<Producto> findAllProductos() {
		return entityManager.createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}

	public Producto findProductoByCodigo(String codigo) {
		Query query = entityManager.createQuery("select p from Producto p where p.prodCodigo='" + codigo + "'",
				Producto.class);
		Producto producto = new Producto();
		try {
			producto = (Producto) query.getSingleResult();
			if (producto != null) {
				return producto;
			} else {
				producto = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return producto;
	}

	public Empresa agregarProveedor(Empresa empresaNueva, int idGerente) {
		Gerente gerente = entityManager.find(Gerente.class, idGerente);
		//JSFUtil.crearMensajeInfo(" Hola " + findEmpresaByRuc(empresaNueva.getEmpRuc()).getEmpCiudad());
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

	public CabeceraIngreso insertarCabeceraIngreso(int id_proveedor, int id_vendedor, String autorizacion,
			Date fecha_emision, Date fecha_caducacion, String numero_factura) {
		Empresa proveedor = entityManager.find(Empresa.class, id_proveedor);
		Vendedor vendedor = entityManager.find(Vendedor.class, id_vendedor);
		CabeceraIngreso cabeceraIngreso = new CabeceraIngreso();
		cabeceraIngreso.setProveedor(proveedor);
		cabeceraIngreso.setVendedor(vendedor);
		cabeceraIngreso.setCabingAutorizacion(autorizacion);
		cabeceraIngreso.setCabingFechaIngreso(new Date());
		cabeceraIngreso.setCabingFechaEmision(fecha_emision);
		cabeceraIngreso.setCabingFechaCaducacion(fecha_caducacion);
		cabeceraIngreso.setCabingNumeroFactura(numero_factura);
		return cabeceraIngreso;
	}

	public BigDecimal calcularSubtotal(int cant, BigDecimal precio, double iva) {
		BigDecimal f = precio.multiply(new BigDecimal(cant));
		double e = (f).doubleValue() / ((100 + iva) / 100);
		return new BigDecimal(e).round(new MathContext(5));
	}

	public BigDecimal calcularPrecioTotal(int cant, double precio) {
		double e = cant * precio;
		return new BigDecimal(e).round(new MathContext(5));
	}

	public List<DetalleIngreso> agregarProductoDetalleIngreso(List<DetalleIngreso> listaDetalleIngresos,
			int id_producto, int cantidad) {
		Producto producto = entityManager.find(Producto.class, id_producto);
		DetalleIngreso detalleIngreso = new DetalleIngreso();
		detalleIngreso.setProducto(producto);
		detalleIngreso.setDetingCantidad(cantidad);
		detalleIngreso.setDetingPrecioUnitario(producto.getProdPvproveedor());
		detalleIngreso.setDetingIva(producto.getProdIva());
		detalleIngreso.setDetingSubtotal(calcularSubtotal(cantidad, detalleIngreso.getDetingPrecioUnitario(),
				detalleIngreso.getDetingIva().doubleValue()));
		detalleIngreso.setDetingPrecioTotal(
				calcularPrecioTotal(cantidad, detalleIngreso.getDetingPrecioUnitario().doubleValue()));
		listaDetalleIngresos.add(detalleIngreso);
		return listaDetalleIngresos;

	}

	public double valorSubTotal(double total) {
		double subTotal = total / 1.12;

		return formatearDecimales(subTotal, 2);
	}

	public double calcularSubTotalIngreso(double total) {

		return formatearDecimales(total / 1.12, 3);
	}

	public double valorIva(double total) {
		double iva = total - calcularSubTotalIngreso(total);
		return formatearDecimales(iva, 2);
	}

	public double valorTotalPagar(List<DetalleIngreso> listaDetalleIngresos) {
		double valorTotal = 0;
		for (DetalleIngreso detalleFactura : listaDetalleIngresos) {
			valorTotal += detalleFactura.getDetingPrecioTotal().doubleValue();
		}
		return formatearDecimales(valorTotal, 2);
	}

	public Producto crearNuevoProducto(Producto productoNuevo, int id_talla_producto, int id_tipo_producto,
			int id_proveedor) {
		Producto producto = findProductoByCodigo(productoNuevo.getProdCodigo());
		if (producto.getProdCodigo() == null) {
			Empresa empresa = entityManager.find(Empresa.class, id_proveedor);
			TallaProducto tallaProducto = entityManager.find(TallaProducto.class, id_talla_producto);
			TipoProducto tipoProducto = entityManager.find(TipoProducto.class, id_tipo_producto);
			producto.setEmpresa(empresa);
			producto.setTallaProducto(tallaProducto);
			producto.setTipoProducto(tipoProducto);
			producto.setProdCodigo(productoNuevo.getProdCodigo());
			producto.setProdCantidad(0);
			producto.setProdNombre(productoNuevo.getProdNombre());
			producto.setProdDescripcion(productoNuevo.getProdDescripcion());
			producto.setProdPvproveedor(productoNuevo.getProdPvproveedor());
			producto.setProdIva(productoNuevo.getProdIva());
			producto.setProdPvpublico(productoNuevo.getProdPvpublico());
			entityManager.persist(producto);
		}
		return producto;
	}

	public List<DetalleIngreso> agregarNuevoProductoDetalleIngreso(List<DetalleIngreso> listaDetalleIngresos,
			Producto producto, int cantidad, int id_talla_producto, int id_tipo_producto, int id_proveedor) {
		producto = crearNuevoProducto(producto, id_talla_producto, id_tipo_producto, id_proveedor);
		DetalleIngreso detalleIngreso = new DetalleIngreso();
		detalleIngreso.setProducto(producto);
		detalleIngreso.setDetingCantidad(cantidad);
		detalleIngreso.setDetingPrecioUnitario(producto.getProdPvproveedor());
		detalleIngreso.setDetingIva(producto.getProdIva());
		detalleIngreso.setDetingSubtotal(calcularSubtotal(cantidad, detalleIngreso.getDetingPrecioUnitario(),
				detalleIngreso.getDetingIva().doubleValue()));
		detalleIngreso.setDetingPrecioTotal(
				calcularPrecioTotal(cantidad, detalleIngreso.getDetingPrecioUnitario().doubleValue()));
		listaDetalleIngresos.add(detalleIngreso);
		return listaDetalleIngresos;

	}

	public double TotalIngreso(List<DetalleIngreso> listaDetalleIngresos) {
		double total = 0;
		for (DetalleIngreso detalleIngreso : listaDetalleIngresos) {
			total += detalleIngreso.getDetingPrecioTotal().doubleValue();
		}
		return formatearDecimales(total, 3);
	}

	public double SubTotalIngreso(List<DetalleIngreso> listaDetalleIngresos) {
		double subtotal = 0;
		for (DetalleIngreso detalleIngreso : listaDetalleIngresos) {
			subtotal += detalleIngreso.getDetingSubtotal().doubleValue();
		}
		return formatearDecimales(subtotal, 3);
	}

	public double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

	public FacturaIngreso insertarFacturaIngreso(CabeceraIngreso cabeceraIngreso,
			List<DetalleIngreso> listaDetalleIngresos, int id_vendedor) {
		cabeceraIngreso.setEmpresa(entityManager.find(Empresa.class, 1));
		entityManager.persist(cabeceraIngreso);
		FacturaIngreso facturaIngreso = new FacturaIngreso();
		facturaIngreso.setCabeceraIngreso(cabeceraIngreso);
		facturaIngreso.setFactingSubtotal(new BigDecimal(SubTotalIngreso(listaDetalleIngresos)));
		facturaIngreso.setFactingIva(
				new BigDecimal(TotalIngreso(listaDetalleIngresos) - SubTotalIngreso(listaDetalleIngresos)));
		facturaIngreso.setFactingTotal(new BigDecimal(TotalIngreso(listaDetalleIngresos)));
		entityManager.persist(facturaIngreso);
		for (DetalleIngreso detalleIngreso : listaDetalleIngresos) {
			detalleIngreso.setFacturaIngreso(facturaIngreso);
			Producto producto=entityManager.find(Producto.class, detalleIngreso.getProducto().getIdProducto());
			producto.setProdCantidad(producto.getProdCantidad()+detalleIngreso.getDetingCantidad());
			entityManager.merge(producto);
			entityManager.persist(detalleIngreso);
		}
		return facturaIngreso;
	}

	public List<DetalleIngreso> eliminarProductoListaDetalle(List<DetalleIngreso> listaDetalleIngresos, int index) {
		listaDetalleIngresos.remove(index);
		return listaDetalleIngresos;
	}

	public List<DetalleIngreso> editarCantidadProductoListaDetalle(List<DetalleIngreso> listaDetalleIngresos,
			int cantidad, int index) {
		listaDetalleIngresos.get(index).setDetingCantidad(cantidad);
		listaDetalleIngresos = calcularCamposDetalleFactura(listaDetalleIngresos, cantidad, index);
		return listaDetalleIngresos;
	}

	public List<DetalleIngreso> calcularCamposDetalleFactura(List<DetalleIngreso> listaDetalleIngresos, int cantidad,
			int index) {
		listaDetalleIngresos.get(index).setDetingSubtotal(
				(calcularSubtotal(cantidad, listaDetalleIngresos.get(index).getDetingPrecioUnitario(),
						listaDetalleIngresos.get(index).getDetingIva().doubleValue())));
		listaDetalleIngresos.get(index).setDetingPrecioTotal(calcularPrecioTotal(cantidad, listaDetalleIngresos.get(index).getDetingPrecioUnitario().doubleValue()));

		return listaDetalleIngresos;
	}

}
