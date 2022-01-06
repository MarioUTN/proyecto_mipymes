package proyecto_mipymes.model.usuarios.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	/**
	 * Default constructor.
	 */
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

	public int loginUsuarios(String id_usuario, String password) {
		int resp = -1;
		Usuario usuario = entityManager.find(Usuario.class, id_usuario);
		if (usuario != null && usuario.getUsPassword().equals(Encriptar.encriptar(password)) && usuario.getUsActivo()) {
			resp = 0;
		}
		if (verUsuarioPassword(id_usuario, password)) {
			resp = 1;
		}
		return resp;
	}

	public boolean verUsuarioPassword(String id_usuario, String password) {
		return (id_usuario.equals("") && password.equals(""));
	}
}
