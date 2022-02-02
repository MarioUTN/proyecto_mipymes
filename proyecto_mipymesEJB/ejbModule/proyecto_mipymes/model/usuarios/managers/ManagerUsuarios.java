package proyecto_mipymes.model.usuarios.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyect_mipymes.model.seguridades.dtos.LoginDTO;
import proyecto_mipymes.model.entities.*;
import proyecto_mipymes.model.utils.Encriptar;

/**
 * Session Bean implementation class ManagerUsuarios
 */
@Stateless
@LocalBean
public class ManagerUsuarios {
	@PersistenceContext
	private EntityManager entityManager;

	public ManagerUsuarios() {
		// TODO Auto-generated constructor stub
	}

	public List<Usuario> findAllUsuarios() {
		return entityManager.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}

	public List<Vendedor> findAllVendedores() {
		return entityManager.createNamedQuery("Vendedor.findAll", Vendedor.class).getResultList();
	}

	public Usuario findUsuarioByIdUsuario(String id_usuario) {
		return entityManager.find(Usuario.class, id_usuario);
	}

	public Usuario findUsuarioByEmail(String email) {
		Query query = entityManager.createQuery("select u from Usuario u where u.usEmail=:email", Usuario.class);
		query.setParameter("email", email);
		return (Usuario) query.getSingleResult();
	}

	public Gerente findGerenteByUsuario(String ger_usuario) {
		Query query = entityManager.createQuery("select g from Gerente g where g.usuario.idUsuario=:ger_usuario",
				Gerente.class);
		query.setParameter("ger_usuario", ger_usuario);
		return (Gerente) query.getSingleResult();
	}

	public Vendedor findVendedorByCedula(String cedula) {
		Query query = entityManager.createQuery("select v from Vendedor v where v.venCedula=:cedula", Vendedor.class);
		query.setParameter("cedula", cedula);
		return (Vendedor) query.getSingleResult();
	}

	public Cliente findClienteByUsuario(String cli_usuario) {
		Query query = entityManager.createQuery("select c from Cliente c where c.usuario.idUsuario=:cli_usuario",
				Cliente.class);
		query.setParameter("cli_usuario", cli_usuario);
		return (Cliente) query.getSingleResult();
	}

	public Vendedor findVendedorByUsuario(String ven_usuario) {
		Query query = entityManager.createQuery("select v from Vendedor v where v.usuario.idUsuario=:ven_usuario",
				Vendedor.class);
		query.setParameter("ven_usuario", ven_usuario);
		return (Vendedor) query.getSingleResult();
	}

	public int loginUsuarios(String id_usuario, String password, String direccionIP) {
		int resp = -1;
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		if (usuario != null && usuario.getUsPassword().equals(Encriptar.encriptar(password)) && usuario.getUsActivo()) {
			resp = usuario.getTipoUsuario().getIdTipoUsuario();
		}
		if (verUsuarioPassword(id_usuario, password)) {
			resp = 0;
		}
		return resp;
	}

	public boolean verUsuarioPassword(String id_usuario, String password) {
		return (id_usuario.equals("") && password.equals(""));
	}
}
