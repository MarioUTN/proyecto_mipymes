package proyect_mipymes.model.clientes.managers;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.dtos.ClienteDTO;
import proyecto_mipymes.model.entities.Cliente;
import proyecto_mipymes.model.entities.Producto;
import proyecto_mipymes.model.entities.TipoUsuario;
import proyecto_mipymes.model.entities.Usuario;
import proyecto_mipymes.model.utils.Encriptar;

/**
 * Session Bean implementation class ManagerClientes
 */
@Stateless
@LocalBean
public class ManagerClientes {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ManagerClientes() {
		// TODO Auto-generated constructor stub
	}

	public List<Cliente> findAllClientes() {
		Query query = entityManager.createQuery(
				"select c from Cliente c order by c.idCliente", Cliente.class);
		return query.getResultList();
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
	
	public Cliente findClienteById(int id_cliente) {
		return entityManager.find(Cliente.class, id_cliente);
	}

	public TipoUsuario findTipoUsuarioById(int id_tipousuario) {
		return entityManager.find(TipoUsuario.class, id_tipousuario);
	}

	public Cliente crearCliente(String cedula_ruc, String nombres, String apellidos, String telefono, String email,
			String direccion) {
		Cliente cliente = findAllClienteByCedulaRuc(cedula_ruc);
		if (cliente.getCliCodigo() == null) {
			//JSFUtil.crearMensajeInfo("Entre aqui!!!!!!!!!!!");
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

	public Cliente crearCliente(ClienteDTO clienteDTO) {
		Cliente cliente = findAllClienteByCedulaRuc(clienteDTO.getCedulaC());
		if (cliente.getCliCodigo() == null) {
			//JSFUtil.crearMensajeInfo("Entre aqui!!!!!!!!!!!");
			TipoUsuario tipoUsuario = findTipoUsuarioById(3);

			Usuario usuario = new Usuario();
			usuario.setIdUsuario("CLI-" + clienteDTO.getCedulaC());
			usuario.setUsEmail(clienteDTO.getEmailC());
			usuario.setUsPassword(Encriptar.encriptar("CLI-" + clienteDTO.getCedulaC()));
			usuario.setUsActivo(true);
			usuario.setUsNombres(clienteDTO.getNombresC());
			usuario.setUsApellidos(clienteDTO.getApellidosC());
			usuario.setUsFechaRegistro(new Date());
			usuario.setTipoUsuario(tipoUsuario);
			entityManager.persist(usuario);

			cliente.setUsuario(usuario);
			cliente.setCliTelefono(clienteDTO.getTelefonoC());
			cliente.setCliRucCedula(clienteDTO.getCedulaC());
			cliente.setCliEmail(clienteDTO.getEmailC());
			cliente.setCliDireccion(clienteDTO.getDireccionC());
			cliente.setCliRucCedula(clienteDTO.getCedulaC());
			cliente.setCliFechaRegistro(new Date());
			cliente.setCliCodigo("CLI-" + (findAllClientes().size() + 1));
			entityManager.persist(cliente);
			return cliente;
		} else {
			return null;
		}
	}

}
