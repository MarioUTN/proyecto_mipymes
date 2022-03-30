package proyect_mipymes.model.facturas.managers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.DetalleAbono;
import proyecto_mipymes.model.entities.EstadoPedido;
import proyecto_mipymes.model.entities.Factura;
import proyecto_mipymes.model.entities.FormaPago;
import proyecto_mipymes.model.entities.TipoFactura;
import proyecto_mipymes.model.entities.Vendedor;

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
		listaFacturas = entityManager.createQuery("select f from Factura f order by f.idFactura", Factura.class).getResultList();
		return listaFacturas;
	}

	public List<TipoFactura> findAllTipoFacturas() {
		return entityManager.createNamedQuery("TipoFactura.findAll", TipoFactura.class).getResultList();
	}

	public List<Vendedor> findAllVendedors() {
		return entityManager.createNamedQuery("Vendedor.findAll", Vendedor.class).getResultList();
	}

	public List<FormaPago> findAllFormaPagoFacturas() {
		return entityManager.createNamedQuery("FormaPago.findAll", FormaPago.class).getResultList();
	}

	public List<Factura> findAllFacturaByFormaPago(int id_forma_pago) {
		Query query = entityManager.createQuery("select f from Factura f where f.formaPago.idFormaPago=:id_forma_pago order by f.idFactura",
				Factura.class);
		query.setParameter("id_forma_pago", id_forma_pago);
		return query.getResultList();
	}

	public List<Factura> findAllFacturaByVendedors(int id_vendedor) {
		Query query = entityManager.createQuery(
				"select f from Factura f where f.cabeceraFactura.vendedor.idVendedor=:id_vendedor order by f.idFactura", Factura.class);
		query.setParameter("id_vendedor", id_vendedor);
		return query.getResultList();
	}
	
	public List<Factura> findAllFacturaByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String fecha=sdf.format(date);
		Query query = entityManager.createQuery(
				"select f from Factura f where f.factFechaEmision=:fecha order by f.idFactura", Factura.class);
		query.setParameter("fecha", date);
		return query.getResultList();
	}

	public boolean buscarFacturaAnticipos(int tipo_factura) {
		boolean resp = false;
		if (tipo_factura == 4) {
			resp = true;
		}
		return resp;
	}

	public List<Factura> findAllFacturaByTipo(int id_tipo_factura) {
		Query query = entityManager.createQuery(
				"select f from Factura f where f.tipoFactura.idTipoFactura=:id_tipo_factura order by f.idFactura", Factura.class);
		query.setParameter("id_tipo_factura", id_tipo_factura);
		return query.getResultList();
	}

	public List<Factura> findAllFacturaByCliente(String cedula_ruc) {
		Query query = entityManager.createQuery(
				"select f from Factura f where f.cabeceraFactura.cliente.cliRucCedula=:cedula_ruc order by f.idFactura", Factura.class);
		query.setParameter("cedula_ruc", cedula_ruc);
		return query.getResultList();
	}

	public Factura findFacturaById(int id_factura) {
		return entityManager.find(Factura.class, id_factura);
	}

	public List<DetalleAbono> findAllDetalleAbonosByIdFactura(int id_factura) {
		Query query = entityManager.createQuery(
				"select da from DetalleAbono da where da.estadoPedido.factura.idFactura=:id_factura",
				DetalleAbono.class);
		query.setParameter("id_factura", id_factura);
		return query.getResultList();
	}

	public boolean verificarValorAbonar(double saldo_actual, double valor_abono) {
		double var = valor_abono - valor_abono;
		return (valor_abono >= 0 && valor_abono <= saldo_actual && var >= 0);
	}

	public BigDecimal calcularSaldoActual(BigDecimal valor_total, double valor_abono) {
		double sa = valor_total.doubleValue() - valor_abono;
		return new BigDecimal(sa).round(new MathContext(5));
	}

	public BigDecimal calcularSaldoAnterior(BigDecimal saldo, double valor_abono) {
		double saldo_anterior = (saldo.doubleValue() - valor_abono) + valor_abono;
		return new BigDecimal(saldo_anterior).round(new MathContext(5));
	}

	public EstadoPedido findEstdoPedido(int id_factura) {
		Query query = entityManager.createQuery(
				"select ep from EstadoPedido ep where ep.factura.idFactura=" + id_factura + "", EstadoPedido.class);
		EstadoPedido estado_pedido = new EstadoPedido();
		try {
			estado_pedido = (EstadoPedido) query.getSingleResult();
			if (estado_pedido != null) {
				return estado_pedido;
			} else {
				estado_pedido = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return estado_pedido;
	}

	public List<DetalleAbono> agregarAbonoFactura(List<DetalleAbono> listaDetalleAbonos, List<DetalleAbono> auxiliar,
			Factura factura, Cliente cliente, int id_vendedor, double valor_abono) {
		EstadoPedido estadoPedido=findEstdoPedido(factura.getIdFactura());
		if (verificarValorAbonar(estadoPedido.getEstSaldo().doubleValue(), valor_abono)) {
			Vendedor vendedor = entityManager.find(Vendedor.class, id_vendedor);
			DetalleAbono detalleAbono = new DetalleAbono();
			detalleAbono.setEstadoPedido(estadoPedido);
			detalleAbono.setDetabAbono(new BigDecimal(valor_abono));
			detalleAbono
					.setDetabSaldoAnterior(listaDetalleAbonos.get(listaDetalleAbonos.size() - 1).getDetabSaldoActual());
			detalleAbono.setDetabSaldoActual(calcularSaldoActual(
					listaDetalleAbonos.get(listaDetalleAbonos.size() - 1).getDetabSaldoActual(), valor_abono));
			detalleAbono.setDetabFechaAbono(new Date());
			detalleAbono.setCliente(cliente);
			detalleAbono.setVendedor(vendedor);
			listaDetalleAbonos.add(detalleAbono);
			auxiliar.add(detalleAbono);
		}
		return auxiliar;
	}

	public boolean DeliverProduct(int id_factura) {
		Factura factura = findFacturaById(id_factura);
		EstadoPedido estadoPedido=findEstdoPedido(id_factura);
		if (estadoPedido.getEstSaldo().doubleValue()==0) {
			factura.setFactEntregado(true);
		}
		entityManager.merge(factura);
		return factura.getFactEntregado();
	}

	public EstadoPedido actualizarEstadoPedido(List<DetalleAbono> listDetalleAbonos) {
		EstadoPedido estadoPedido = entityManager.find(EstadoPedido.class,
				listDetalleAbonos.get(0).getEstadoPedido().getIdEstadoPedido());
		Factura factura = entityManager.find(Factura.class, estadoPedido.getFactura().getIdFactura());
		for (DetalleAbono detalleAbono : listDetalleAbonos) {
			entityManager.persist(detalleAbono);
		}
		estadoPedido.setEstSaldo(listDetalleAbonos.get(listDetalleAbonos.size() - 1).getDetabSaldoActual());
		entityManager.merge(estadoPedido);
		if (estadoPedido.getEstSaldo().doubleValue() == 0) {
			factura.setFactEstado(true);
			entityManager.merge(factura);
		}
		return estadoPedido;
	}
}
