package proyect_mipymes.model.ventas.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TipoUsuario;
import proyecto_mipymes.model.entities.Usuario;
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

	public Producto findProductoById(int id_producto) {
		return entityManager.find(Producto.class, id_producto);
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

}
