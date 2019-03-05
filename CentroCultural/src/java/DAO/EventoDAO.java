/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Evento;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Yusuke
 */
public class EventoDAO {
    
    //SELECT * FROM evento where setor = 'Teatro Margarida Schivasappa' AND ('2019-03-08' BETWEEN dataInicio AND dataFim) 
        //OR ('2019-03-08' BETWEEN dataInicio AND dataFim)
    
    public boolean verificarData(Evento evento) throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
        Date data = evento.getDataInicio(); 
        String dataInicioFormatada = fmt.format(data);
        Date datafim = evento.getDataFim(); 
        String dataFimFormatada = fmt.format(datafim);
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stmt = "FROM Evento where setor = '" + evento.getSetor() 
                    + "' AND ('" + dataInicioFormatada + "' BETWEEN dataInicio AND dataFim)"
                    + " OR ('" + dataFimFormatada + "' BETWEEN dataInicio AND dataFim)";
            Query query = s.createQuery(stmt);
            if (query.list().isEmpty()) {
                return true; //retorna que a lista ta vazia
            } else {
                return false; //retorna que tem algo na lista
            }
        } catch (HibernateException e) {
            throw e;
        }
    }
    
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
