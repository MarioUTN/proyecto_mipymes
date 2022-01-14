package proyect_mipymes.model.ventas.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Producto;

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

    
    public List<Empresa> findAllEmpresas(){
    	return entityManager.createNamedQuery("Empresa.findAll", Empresa.class).getResultList();
    }
    
    
    public Empresa findAllEmpresaByRuc(String emp_ruc) {
		Query query = entityManager.createQuery("select e from Empresa e where e.empRuc='" + emp_ruc + "'",
				Empresa.class);
		Empresa empresa=new Empresa();
		try {
			empresa = (Empresa) query.getSingleResult();
			if (empresa != null) {
				return empresa;
			} else {
				empresa=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return empresa;
		
	}
    
}
