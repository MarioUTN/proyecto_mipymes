package proyecto_mipymes.model.anticipos.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.model.entities.*;

/**
 * Session Bean implementation class ManagerAnticipos
 */
@Stateless
@LocalBean
public class ManagerAnticipos {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerAnticipos() {
		// TODO Auto-generated constructor stub
	}

	public List<Cliente> findAllClientes() {
		return entityManager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
	}

	public List<Producto> findAllProductos() {
		return entityManager.createNamedQuery("Producto.findAll", Producto.class).getResultList();
	}

	public List<TipoProducto> findAllTipoProductos() {
		return entityManager.createNamedQuery("TipoProducto.findAll", TipoProducto.class).getResultList();
	}

	public List<TallaProducto> findAllTallaProductos() {
		return entityManager.createNamedQuery("TallaProducto.findAll", TallaProducto.class).getResultList();
	}

	public List<Vendedor> findAllVendedores() {
		return entityManager.createNamedQuery("Vendedor.findAll", Vendedor.class).getResultList();
	}

	public List<FormaPago> findAllFormaPagos() {
		return entityManager.createNamedQuery("FormaPago.findAll", FormaPago.class).getResultList();
	}

	public TipoFactura findAllTipoFacturas() {
		return entityManager.find(TipoFactura.class, 4);
	}

	public CabeceraFactura registrarCabeceraFactura(CabeceraFactura cabeceraFactura) {
		if (entityManager.find(CabeceraFactura.class, cabeceraFactura.getIdCabeceraFactura()) != null) {
			entityManager.persist(cabeceraFactura);
		}
		return cabeceraFactura;
	}
	
	

}
