package proyect_mipymes.model.ventas.managers;

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
import proyecto_mipymes.model.entities.CabeceraFactura;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.DetalleFactura;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Factura;
import proyecto_mipymes.model.entities.FormaPago;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TipoFactura;
import proyecto_mipymes.model.entities.TipoUsuario;
import proyecto_mipymes.model.entities.Usuario;
import proyecto_mipymes.model.entities.Vendedor;
import proyecto_mipymes.model.utils.Encriptar;

/**
 * Session Bean implementation class ManagerVentas
 */
@Stateless
@LocalBean
public class ManagerVentas {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerVentas() {
		// TODO Auto-generated constructor stub
	}

	public List<Empresa> findAllEmpresas() {
		return entityManager.createNamedQuery("Empresa.findAll", Empresa.class).getResultList();
	}

	public List<Factura> findAllFacturas() {
		return entityManager.createNamedQuery("Factura.findAll", Factura.class).getResultList();
	}

	public Producto findProductoById(int id_producto) {
		return entityManager.find(Producto.class, id_producto);
	}

	public TipoFactura findTipoFacturaById(int id_tipo_factura) {
		return entityManager.find(TipoFactura.class, id_tipo_factura);
	}

	public FormaPago findFormaPagoById(int id_forma_pago) {
		return entityManager.find(FormaPago.class, id_forma_pago);
	}

	public List<TipoFactura> findAllTipoFacturas() {
		return entityManager.createNamedQuery("TipoFactura.findAll", TipoFactura.class).getResultList();
	}

	public List<FormaPago> findAllFormaPagos() {
		return entityManager.createNamedQuery("FormaPago.findAll", FormaPago.class).getResultList();
	}

	public Empresa findAllEmpresaByRuc(String emp_ruc) {
		Query query = entityManager.createQuery("select e from Empresa e where e.empRuc='" + emp_ruc + "'",
				Empresa.class);
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

	public List<Cliente> findAllClientes() {
		return entityManager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
	}

	public Cliente findAllClienteByCedulaRuc(String cli_ruc_cedula) {
		Query query = entityManager.createQuery("select c from Cliente c where c.cliRucCedula='" + cli_ruc_cedula + "'",
				Cliente.class);
		Cliente cliente = new Cliente();
		try {
			cliente = (Cliente) query.getSingleResult();
			if (cliente != null) {
				return cliente;
			} else {
				cliente = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cliente;
	}

	public DetalleFactura findDetalleFacturaById(int id_detalle) {
		return entityManager.find(DetalleFactura.class, id_detalle);
	}

	public TipoUsuario findTipoUsuarioById(int id_tipousuario) {
		return entityManager.find(TipoUsuario.class, id_tipousuario);
	}

	public Cliente crearCliente(String cedula_ruc, String nombres, String apellidos, String telefono, String email,
			String direccion) {
		Cliente cliente = findAllClienteByCedulaRuc(cedula_ruc);
		if (cliente.getCliCodigo() == null) {
			JSFUtil.crearMensajeInfo("Entre aqui!!!!!!!!!!!");
			TipoUsuario tipoUsuario = findTipoUsuarioById(3);

			Usuario usuario = new Usuario();
			usuario.setIdUsuario("CLI-" + cedula_ruc);
			usuario.setUsEmail(email);
			usuario.setUsPassword(Encriptar.encriptar("CLI-" + cedula_ruc));
			usuario.setUsActivo(true);
			usuario.setUsNombres(nombres);
			usuario.setUsApellidos(apellidos);
			usuario.setUsFechaRegistro(new Date());
			usuario.setTipoUsuario(tipoUsuario);
			entityManager.persist(usuario);

			cliente.setUsuario(usuario);
			cliente.setCliTelefono(telefono);
			cliente.setCliRucCedula(cedula_ruc);
			cliente.setCliEmail(email);
			cliente.setCliDireccion(direccion);
			cliente.setCliRucCedula(cedula_ruc);
			cliente.setCliFechaRegistro(new Date());
			cliente.setCliCodigo("CLI-" + (findAllClientes().size() + 1));
			entityManager.persist(cliente);
			return cliente;
		} else {
			return null;
		}
	}

	public CabeceraFactura insertarCabeceraFactura(Cliente cliente, int id_vendedor, int id_empresa) {
		Empresa empresa = entityManager.find(Empresa.class, id_empresa);
		Vendedor vendedor = entityManager.find(Vendedor.class, id_vendedor);
		CabeceraFactura cabeceraFactura = new CabeceraFactura();
		cabeceraFactura.setCliente(cliente);
		cabeceraFactura.setEmpresa(empresa);
		cabeceraFactura.setVendedor(vendedor);
		entityManager.persist(cabeceraFactura);
		return cabeceraFactura;
	}

	public Factura insertarFactura(Cliente cliente, int id_vendedor, int id_empresa,
			List<DetalleFactura> listaDetalleFacturas, int id_forma_pago, int id_tipo_factura) {
		if (listaDetalleFacturas != null) {
			Factura factura = new Factura();
			FormaPago formaPago = entityManager.find(FormaPago.class, id_forma_pago);
			TipoFactura tipoFactura = entityManager.find(TipoFactura.class, id_tipo_factura);
			factura.setCabeceraFactura(insertarCabeceraFactura(cliente, id_vendedor, id_empresa));
			factura.setDetalleFacturas(listaDetalleFacturas);
			factura.setFactNumeroFactura("00" + findAllFacturas().size() + 1);
			factura.setFactFechaEmision(new Date());
			factura.setFactFechaRemision(new Date());
			factura.setFactFechaAutorizacion(new Date());
			factura.setFormaPago(formaPago);
			factura.setTipoFactura(tipoFactura);
			factura.setFactEstado(true);
			factura.setFactEntregado(true);
			factura.setFactDescuento(new BigDecimal(0));
			factura.setFactSubtotal(new BigDecimal(valorSubTotal(listaDetalleFacturas)));
			factura.setFactIva(new BigDecimal(valorIva(listaDetalleFacturas)));
			factura.setFactTotal(new BigDecimal(valorTotalPagar(listaDetalleFacturas)));
			entityManager.persist(factura);
			for (DetalleFactura detalleFactura : listaDetalleFacturas) {
				detalleFactura.setFactura(factura);
				entityManager.persist(detalleFactura);
			}
			return factura;
		} else {
			return null;
		}
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

	public boolean verificarCantidadVentaProductos(Producto producto, int cantidad) {
		int var = producto.getProdCantidad() - cantidad;
		return (cantidad <= producto.getProdCantidad() && var >= 0);
	}

	public double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
	}

	public double valorSubTotal(List<DetalleFactura> listaDetalleFacturas) {
		double subTotal = 0;
		for (DetalleFactura detalleFactura : listaDetalleFacturas) {
			subTotal += detalleFactura.getDetSubtotal().doubleValue();
		}
		return formatearDecimales(subTotal, 2);
	}

	public double valorIva(List<DetalleFactura> listaDetalleFacturas) {
		double iva = 0;
		for (DetalleFactura detalleFactura : listaDetalleFacturas) {
			iva += detalleFactura.getDetIva().doubleValue();
		}
		return formatearDecimales(iva, 2);
	}

	public double valorTotalPagar(List<DetalleFactura> listaDetalleFacturas) {
		double valorTotal = 0;
		for (DetalleFactura detalleFactura : listaDetalleFacturas) {
			valorTotal += detalleFactura.getDetPrecioTotal().doubleValue();
		}
		return formatearDecimales(valorTotal, 2);
	}

	public List<DetalleFactura> eliminarProductoListaDetalle(List<DetalleFactura> listaDetalleFacturas, int index) {
		listaDetalleFacturas.remove(index);
		return listaDetalleFacturas;
	}

	public List<DetalleFactura> editarCantidadProductoListaDetalle(List<DetalleFactura> listaDetalleFacturas,
			int cantidad, int index) {
		listaDetalleFacturas.get(index).setDetCantidad(cantidad);
		listaDetalleFacturas = calcularCamposDetalleFactura(listaDetalleFacturas, cantidad, index);
		return listaDetalleFacturas;
	}

	public List<DetalleFactura> calcularCamposDetalleFactura(List<DetalleFactura> listaDetalleFacturas, int cantidad,
			int index) {
		listaDetalleFacturas.get(index)
				.setDetSubtotal(calcularSubtotal(cantidad, listaDetalleFacturas.get(index).getDetPrecioUnitario()));
		listaDetalleFacturas.get(index).setDetIva(
				CalcularIva(calcularSubtotal(cantidad, listaDetalleFacturas.get(index).getDetPrecioUnitario())));
		listaDetalleFacturas.get(index).setDetPrecioTotal(CalcularTotal(
				calcularSubtotal(cantidad, listaDetalleFacturas.get(index).getDetPrecioUnitario()),
				CalcularIva(calcularSubtotal(cantidad, listaDetalleFacturas.get(index).getDetPrecioUnitario()))));
		return listaDetalleFacturas;
	}

	public List<DetalleFactura> agregarProductosFactura(List<DetalleFactura> listaDetalleFacturas, int id_producto,
			int cantidad) {
		Producto producto = findProductoById(id_producto);
		if (verificarCantidadVentaProductos(producto, cantidad)) {
			DetalleFactura detalleFactura = new DetalleFactura();
			detalleFactura.setDetCantidad(cantidad);
			detalleFactura.setProducto(producto);
			detalleFactura.setDetPrecioUnitario(producto.getProdPvpublico());
			detalleFactura.setDetSubtotal(calcularSubtotal(cantidad, producto.getProdPvpublico()));
			detalleFactura.setDetIva(CalcularIva(calcularSubtotal(cantidad, producto.getProdPvpublico())));
			detalleFactura.setDetPrecioTotal(CalcularTotal(calcularSubtotal(cantidad, producto.getProdPvpublico()),
					CalcularIva(calcularSubtotal(cantidad, producto.getProdPvpublico()))));
			listaDetalleFacturas.add(detalleFactura);
		}
		return listaDetalleFacturas;
	}

}
