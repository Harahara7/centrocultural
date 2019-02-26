package Bean;

import DAO.UsuarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {
    
   private Usuario usuario;
   private List<Usuario> listaUsuario;
   private Utilidade utilidade = new Utilidade();
    
    public void fazerLogin(){
    //falta implementar
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario == null){
        usuario = new Usuario();
        }
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        if(listaUsuario == null){
            UsuarioDAO uDAO = new UsuarioDAO();
            uDAO.listarUsuarios();
        }
        this.listaUsuario = listaUsuario;
    }
    
}
