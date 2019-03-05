/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Fisico;
import modelo.Juridico;
import modelo.Locatario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class LocatarioDAO {
    
    public void salvar(Locatario locatario){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.save(locatario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//salvar
    
    public void remover(Locatario locatario){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.delete(locatario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//remover
    
    public void atualizar(Locatario locatario){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.update(locatario);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//atualizar
    
    public List<Locatario> listarLocatarios(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Locatario";
        List<Locatario> listaLocatario = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaLocatario = s.createQuery(queryObj).list();
        s.close();
        
        return listaLocatario;
        
    }//listarClientes
    
    public List<Fisico> listarLocatariosFisicos(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Fisico";
        List<Fisico> listaLocatarioFisico = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaLocatarioFisico = s.createQuery(queryObj).list();
        s.close();
   
        return listaLocatarioFisico;
               
    }//listarQuantidadedePessoaFisica
    
        public List<Juridico> listarLocatariosJuridicos(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Juridico";
        List<Juridico> listaLocatarioJuridico = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaLocatarioJuridico = s.createQuery(queryObj).list();
        s.close();
 
        return listaLocatarioJuridico;
               
    }//listarQuantidadedePessoaJuridica
    
}//class
