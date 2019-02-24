/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Yusuke
 */
public class EventoDAO {
    
    public void salvar(Evento evento){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.save(evento);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//salvar
    
    public void remover(Evento evento){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.delete(evento);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//salvar
    
    public void alterar(Evento evento){
    
        //inicia uma sessão com conexão com BD
        Session s = HibernateUtil.getSessionFactory().openSession();
        //inicia um Transaction ao banco
        Transaction t = s.beginTransaction();
        //persista o obj(cliente) ao BD!
        s.update(evento);
        //commit da transação
        t.commit();
        //Termine a sessão
        s.close();
        
    }//salvar
    
    public List<Evento> listarEventos(){
        
        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Evento";
        List<Evento> listaEvento = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();   
        listaEvento = s.createQuery(queryObj).list();
        s.close();
        
        return listaEvento;
        
    }//listarEventos
    
}
