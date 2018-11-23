package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Usuario;


@Stateless
public class UsuarioDao {

	@PersistenceContext
	private EntityManager em;
	
	
	public void createOrUpdate (Usuario usuario) {
		if (usuario.getId() != null) {
			em.merge(usuario);
		} else {
			em.persist(usuario);
		}
		/*em.refresh(usuario);*/
	}
	public List<Usuario> getListUsers(String parameter){

		TypedQuery<Usuario> query = null;
		query = em.createQuery("SELECT u FROM Usuario u WHERE u.name = :name OR u.username = :username OR u.email = :email", Usuario.class);
		query.setParameter("name", parameter);
		query.setParameter("username", parameter);
		query.setParameter("email", parameter);
		
		try {
			return  query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public List<Usuario> listAll(){
		
		return (List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();

	}
	
	public Usuario findById(Integer usuarioId) {
		return em.find(Usuario.class, usuarioId);
	}
	
	public void delete(Integer usuarioId) {
		Usuario usuario = findById(usuarioId);
		em.remove(usuario);		
	}
	
	
}
