package proyecto_mipymes.ventas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import proyect_mipymes.model.ventas.managers.ManagerVentas;
import proyecto_mipymes.model.entities.Empresa;

@Named
@SessionScoped
public class BeanVentas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerVentas managerVentas;
	
	private List<Empresa> listaEmpresas;
	private Empresa empresa;
	
	public BeanVentas() {
		// TODO Auto-generated constructor stub
		empresa=managerVentas.findAllEmpresaByRuc("1003938477001");
		listaEmpresas=managerVentas.findAllEmpresas();
	}
	
	

}
