package proyecto_mipymes.model.vendedor;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import proyecto_mipymes.controller.util.JSFUtil;
import proyecto_mipymes.model.entities.Empresa;
import proyecto_mipymes.model.entities.Gerente;
import proyecto_mipymes.model.entities.TipoUsuario;
import proyecto_mipymes.model.entities.Usuario;
import proyecto_mipymes.model.entities.Vendedor;
import proyecto_mipymes.model.utils.Encriptar;

/**
 * Session Bean implementation class ManagerVendedor
 */
@Stateless
@LocalBean
public class ManagerVendedor {
	@PersistenceContext
	EntityManager entityManager;

	public ManagerVendedor() {
		// TODO Auto-generated constructor stub
	}

	public Vendedor findAllVendedorByCedulaRuc(String cli_ruc_cedula) {
		Query query = entityManager.createQuery("select c from Vendedor c where c.venCedula='" + cli_ruc_cedula + "'",
				Vendedor.class);
		Vendedor vendedor = new Vendedor();
		try {
			vendedor = (Vendedor) query.getSingleResult();
			if (vendedor != null) {
				return vendedor;
			} else {
				vendedor = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vendedor;
	}

	public TipoUsuario findTipoUsuarioById(int id_tipousuario) {
		return entityManager.find(TipoUsuario.class, id_tipousuario);
	}

	public Vendedor agregarVendedor(VendedorDTO vendedorDTO) {

		Vendedor vendedor = findAllVendedorByCedulaRuc(vendedorDTO.getCedulaV());
		if (vendedor.getIdVendedor() == null) {

			TipoUsuario tipoUsuario = findTipoUsuarioById(2);

			Usuario usuario = new Usuario();
			usuario.setIdUsuario("VEN-" + vendedorDTO.getCedulaV());
			usuario.setUsEmail(vendedorDTO.getEmailV());
			usuario.setUsPassword(Encriptar.encriptar(vendedorDTO.getContraseñaV()));
			usuario.setUsActivo(true);
			usuario.setUsNombres(vendedorDTO.getNombresV());
			usuario.setUsApellidos(vendedorDTO.getApellidosV());
			usuario.setUsFechaRegistro(new Date());
			usuario.setTipoUsuario(tipoUsuario);
			entityManager.persist(usuario);
			vendedor.setUsuario(usuario);
			vendedor.setVenCedula(vendedorDTO.getCedulaV());
			vendedor.setVenDireccion(vendedorDTO.getDireccionV());
			vendedor.setVenEmail(vendedorDTO.getEmailV());
			vendedor.setVenFechaInicio(new Date());
			vendedor.setVenTelefono(vendedorDTO.getTelefonoV());
			vendedor.setVenSueldo(new BigDecimal(425));
			entityManager.persist(vendedor);

			return vendedor;

		} else {
			return null;
		}
	}

	public void actualizaVendedor(VendedorDTO edicionVendedor) throws Exception {

		Vendedor vendedor = entityManager.find(Vendedor.class, edicionVendedor.getIdVendedor());
		Usuario usuario = entityManager.find(Usuario.class, edicionVendedor.getIdUsuario());

//		proveedor.setEmpRuc(edicionProveedor.getEmpRuc());
//		proveedor.setEmpCiudad(edicionProveedor.getEmpCiudad());
//		proveedor.setEmpEmail(edicionProveedor.getEmpEmail());
//		proveedor.setEmpMatriz(edicionProveedor.getEmpMatriz());
		usuario.setUsNombres(edicionVendedor.getNombresV());
		usuario.setUsApellidos(edicionVendedor.getApellidosV());
		usuario.setUsEmail(edicionVendedor.getEmailV());
		entityManager.merge(usuario);
		vendedor.setVenCedula(edicionVendedor.getCedulaV());
		vendedor.setVenEmail(edicionVendedor.getEmailV());
		vendedor.setVenDireccion(edicionVendedor.getDireccionV());
		vendedor.setVenTelefono(edicionVendedor.getTelefonoV());
//		proveedor.setEmpPais(edicionProveedor.getEmpPais());
//		proveedor.setEmpNombreEmpresa(edicionProveedor.getEmpNombreEmpresa());
//		proveedor.setEmpProvincia(edicionProveedor.getEmpProvincia());
//		proveedor.setEmpSucursal(edicionProveedor.getEmpSucursal());
//		proveedor.setEmpTelefono(edicionProveedor.getEmpTelefono());
//		proveedor.setGerente(gerente);
//
		entityManager.merge(vendedor);

	}

	public void actualizarEstadoEmpleado(Vendedor v) {
		Vendedor vendedor = entityManager.find(Vendedor.class, v.getIdVendedor());
		Usuario usuario = entityManager.find(Usuario.class, vendedor.getUsuario().getIdUsuario());
		if (usuario.getUsActivo() == true) {
			usuario.setUsActivo(false);
		} else {
			usuario.setUsActivo(true);
		}
		entityManager.merge(usuario);

	}

}
