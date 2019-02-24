/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Fisico;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class FisicoDAO {
    
    public void salvar(Fisico fisico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.save(fisico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//salvar
    
    public void deletar(Fisico fisico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.delete(fisico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//deletar
    
    public void atualizar(Fisico fisico) {

        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.update(fisico);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();

    }//Atualizar
    
public List<Fisico> listarFisicos(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Fisico";
        List<Fisico> listaFisico = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaFisico = s.createQuery(queryObj).list();
        s.close();
        
        return listaFisico;
        
    }//listarClientes
    
}//class
