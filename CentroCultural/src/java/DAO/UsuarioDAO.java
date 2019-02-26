package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
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
    
}
