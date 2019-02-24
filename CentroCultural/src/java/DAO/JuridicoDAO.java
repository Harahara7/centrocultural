/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Juridico;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@ManagedBean
@SessionScoped
public class JuridicoDAO {
    
    public void salvar(Juridico juridico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.save(juridico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//salvar
    
    public void atualizar(Juridico juridico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.update(juridico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//salvar
    
    public void remover(Juridico juridico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.delete(juridico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//remover
    
    public List<Juridico> listarJuridicos(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Juridico";
        List<Juridico> listaJuridico = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaJuridico = s.createQuery(queryObj).list();
        s.close();
        
        return listaJuridico;
        
    }//listarClientes
    
}
