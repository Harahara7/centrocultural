package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class UsuarioDAO {
    
           
      public void salvar(Usuario usuario){
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.save(usuario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//salvar
    
    public void remover(Usuario usuario){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.delete(usuario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//remover
    
    public void atualizar(Usuario usuario){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.update(usuario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//atualizar
    
    public List<Usuario> listarUsuarios(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Usuario";
        List<Usuario> listaUsuario = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaUsuario = s.createQuery(queryObj).list();
        s.close();
        
        return listaUsuario;
        
    }//listarClientes
    
    public Usuario buscarLogin(Usuario usuario) {
        Usuario us = null;
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stmt = "FROM Usuario WHERE usuario = '" + usuario.getUsuario()
                    + "' and senha = '" + usuario.getSenha() + "'";
            Query query = s.createQuery(stmt);
            if (!query.list().isEmpty()) {
                us = (Usuario) query.list().get(0);
            } else {
            us = null;
            }
        } catch (HibernateException e) {
            throw e;
        }
        return us;
    }

}
