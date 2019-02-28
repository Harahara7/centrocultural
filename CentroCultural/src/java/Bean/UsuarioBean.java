package Bean;

import DAO.UsuarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;
import org.hibernate.HibernateException;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private Utilidade utilidade = new Utilidade();
    private Paginacao pag = new Paginacao();

    public String login() throws HibernateException {
        UsuarioDAO uDAO = new UsuarioDAO();
        try {
            usuario = uDAO.buscarLogin(usuario);
            if (usuario != null) {
                utilidade.mensagemAviso(5);
                return pag.index();
            } else {
                utilidade.mensagemAviso(-1);
            }//else
        } catch (Exception e) {
            throw e;
        }//catch
        return null;
    }//metodo

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        if (listaUsuario == null) {
            UsuarioDAO uDAO = new UsuarioDAO();
            listaUsuario = uDAO.listarUsuarios();
        }
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

}
