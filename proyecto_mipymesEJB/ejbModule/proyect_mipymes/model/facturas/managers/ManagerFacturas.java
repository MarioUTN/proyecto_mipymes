package proyect_mipymes.model.facturas.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Factura;
import proyecto_mipymes.model.entities.FormaPago;
import proyecto_mipymes.model.entities.TipoFactura;

/**
 * Session Bean implementation class ManagerFacturas
 */
@Stateless
@LocalBean
public class ManagerFacturas {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerFacturas() {
		// TODO Auto-generated constructor stub
	}

	public List<Factura> findAllFacturas() {
		List<Factura> listaFacturas;
		listaFacturas = entityManager.createNamedQuery("Factura.findAll", Factura.class).getResultList();
		return listaFacturas;
	}

	public List<TipoFactura> findAllTipoFacturas() {
		return entityManager.createNamedQuery("TipoFactura.findAll", TipoFactura.class).getResultList();
	}

	public List<FormaPago> findAllFormaPagoFacturas() {
		return entityManager.createNamedQuery("FormaPago.findAll", FormaPago.class).getResultList();
	}

	public List<Factura> findAllFacturaByFormaPago(int id_forma_pago) {
		Query query = entityManager.createQuery("select f from Factura f where f.formaPago.idFormaPago=:id_forma_pago",
				Factura.class);
		query.setParameter("id_forma_pago", id_forma_pago);
		return query.getResultList();
	}

	public List<Factura> findAllFacturaByTipo(int id_tipo_factura) {
		Query query = entityManager.createQuery(
				"select f from Factura f where f.tipoFactura.idTipoFactura=:id_tipo_factura", Factura.class);
		query.setParameter("id_tipo_factura", id_tipo_factura);
		return query.getResultList();
	}

	public List<Factura> findAllFacturaByCliente(String cedula_ruc) {
		Query query = entityManager.createQuery(
				"select f from Factura f where f.cabeceraFactura.cliente.cliRucCedula=:cedula_ruc", Factura.class);
		query.setParameter("cedula_ruc", cedula_ruc);
		return query.getResultList();
	}

	public Factura findFacturaById(int id_factura) {
		return entityManager.find(Factura.class, id_factura);
	}

}
