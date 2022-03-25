package proyect_mipymes.model.productos.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TallaProducto;
import proyecto_mipymes.model.entities.TipoProducto;

/**
 * Session Bean implementation class ManagerProductos
 */
@Stateless
@LocalBean
public class ManagerProductos {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerProductos() {
		// TODO Auto-generated constructor stub
	}

	public List<TallaProducto> findAllTallaProductos() {
		return entityManager.createNamedQuery("TallaProducto.findAll", TallaProducto.class).getResultList();
	}

	public List<TipoProducto> findAllTipoProductos() {
		return entityManager.createNamedQuery("TipoProducto.findAll", TipoProducto.class).getResultList();
	}

	public List<Producto> findAllProductosTodos() {
		return entityManager.createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}

	public List<Producto> findAllProductos() {
		Query query = entityManager.createQuery(
				"select p from Producto p order by p.idProducto", Producto.class);
		return query.getResultList();
	}
	
	public List<Producto> findAllProductosInStock() {
		Query query = entityManager.createQuery(
				"select p from Producto p where p.prodCantidad>0 order by p.idProducto", Producto.class);
		return query.getResultList();
	}

	public List<Empresa> findAllEmpresas() {
		return entityManager.createNamedQuery("Empresa.findAll", Empresa.class).getResultList();
	}

	public List<Producto> findAllProductosByTalla(int id_talla_producto) {
		Query query = entityManager
				.createQuery("select p from Producto p where p.tallaProducto.idTallaProducto=:id_talla_producto "
						+ "order by p.idProducto", Producto.class);
		query.setParameter("id_talla_producto", id_talla_producto);
		return query.getResultList();
	}

	public List<Producto> findAllProductosByTipo(int id_tipo_producto) {
		Query query = entityManager
				.createQuery("select p from Producto p where p.tipoProducto.idTipoProducto=:id_tipo_producto "
						+ "order by p.idProducto", Producto.class);
		query.setParameter("id_tipo_producto", id_tipo_producto);
		return query.getResultList();
	}

	public Producto findAllProductosByCodigoProducto(String codigo_producto) {
		Query query = entityManager.createQuery("select p from Producto p where p.prodCodigo='" + codigo_producto + "'",
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

	public BigDecimal calcularPercioVentaPublico(double iva, BigDecimal precio) {
		double e = (precio).doubleValue() * ((100 + iva) / 100) * (1.45);
		return new BigDecimal(e).round(new MathContext(5));
	}

	public boolean eliminarProducto(String codigo_producto) {
		boolean resp = false;
		if (findAllProductosByCodigoProducto(codigo_producto) != null) {
			entityManager.remove(findAllProductosByCodigoProducto(codigo_producto));
			resp = true;
		}
		return resp;
	}

	public int editarProducto(Producto producto, Producto productoEditar, int id_talla_producto, int id_tipo_producto,
			int id_proveedor) throws Exception {
		int resp = -1;
		if (producto.getProdCodigo().equals(productoEditar.getProdCodigo())) {
			resp = 1;
		}
		if (!producto.getProdCodigo().equals(productoEditar.getProdCodigo())) {
			Empresa proveedor = entityManager.find(Empresa.class, id_proveedor);
			TallaProducto tallaProducto = entityManager.find(TallaProducto.class, id_talla_producto);
			TipoProducto tipoProducto = entityManager.find(TipoProducto.class, id_tipo_producto);
			producto.setProdCodigo(productoEditar.getProdCodigo());
			producto.setProdCantidad(productoEditar.getProdCantidad());
			producto.setProdNombre(productoEditar.getProdNombre());
			producto.setProdDescripcion(productoEditar.getProdDescripcion());
			producto.setEmpresa(proveedor);
			producto.setTipoProducto(tipoProducto);
			producto.setTallaProducto(tallaProducto);
			producto.setProdPvproveedor(productoEditar.getProdPvproveedor());
			producto.setProdIva(productoEditar.getProdIva());
			producto.setProdPvpublico(calcularPercioVentaPublico(productoEditar.getProdIva().doubleValue(),
					productoEditar.getProdPvproveedor()));
			entityManager.merge(producto);
			resp = 0;

		}

		if (findAllProductosByCodigoProducto(productoEditar.getProdCodigo()) != null) {
			resp = 2;
		}

		else {
			throw new Exception("Error al actualizar, debe llenar todos los campos!!");
		}
		return resp;
	}

	public Producto ingresarNuevoProducto(Producto productoNuevo, int id_proveedor, int id_talla_producto,
			int id_tipo_producto) {
		if (findAllProductosByCodigoProducto(productoNuevo.getProdCodigo()) == null) {
			Empresa proveedor = entityManager.find(Empresa.class, id_proveedor);
			TallaProducto tallaProducto = entityManager.find(TallaProducto.class, id_talla_producto);
			TipoProducto tipoProducto = entityManager.find(TipoProducto.class, id_tipo_producto);
			productoNuevo.setProdCodigo(productoNuevo.getProdCodigo());
			productoNuevo.setProdCantidad(productoNuevo.getProdCantidad());
			productoNuevo.setProdNombre(productoNuevo.getProdNombre());
			productoNuevo.setProdDescripcion(productoNuevo.getProdDescripcion());
			productoNuevo.setEmpresa(proveedor);
			productoNuevo.setTipoProducto(tipoProducto);
			productoNuevo.setTallaProducto(tallaProducto);
			productoNuevo.setProdPvproveedor(productoNuevo.getProdPvproveedor());
			productoNuevo.setProdIva(productoNuevo.getProdIva());
			productoNuevo.setProdPvpublico(calcularPercioVentaPublico(productoNuevo.getProdIva().doubleValue(),
					productoNuevo.getProdPvproveedor()));
			entityManager.persist(productoNuevo);
		}
		return productoNuevo;
	}

}
