package web;

import java.io.Serializable;
import java.util.List;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import dao.UserDao;
import model.Usuario;


@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -3364269171126880756L;
		@EJB
		private UserDao userDao;
		
		private Usuario usuario = new Usuario();
		
		private String filtro;
		
		public String inserir() {
			userDao.createOrUpdate(usuario);
			usuario = new Usuario();
//			return "user?faces-redirect=true";
			return "user";
		}
		
		public List<Usuario> getUsers() {
			
			return userDao.listAll();
		}
		
		public List<Usuario> listUsers(String parameter){
			if (parameter == null || parameter == "") {
				return userDao.listAll();
			}
			return userDao.getListUsers(parameter);
		}
		
		public String excluir(Integer userId) {
			userDao.delete(userId);
			this.usuario = new Usuario();
			return "user?faces-redirect=true";
//			return "user";
		}
		
		public String editar(Usuario user) {
			this.usuario = new Usuario();
			return null;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		public String getFiltro() {
			return filtro;
		}

		public void setFiltro(String filtro) {
			if(filtro==null) {
				this.filtro = "";
			}
			this.filtro = filtro;
		}
		
}
