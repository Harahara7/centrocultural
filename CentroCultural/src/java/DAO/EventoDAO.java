/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.EventoBean;
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
//     SELECT * FROM evento where setor = 'Casa das Artes' 
// AND `STATUS` = "Confirmado"
// AND ((dataInicio BETWEEN '2019-03-18' AND '2019-03-22') 
// OR (dataFim BETWEEN '2019-03-18' AND '2019-03-22'))
// UNION
// SELECT * FROM evento where setor = 'Casa das Artes' 
// AND `STATUS` = "Confirmado"
// AND (('2019-03-18' BETWEEN dataInicio AND dataFim) 
// OR ('2019-03-22' BETWEEN dataInicio AND dataFim))

    public boolean verificarData(Evento evento) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = evento.getDataInicio();
        String dataInicioFormatada = fmt.format(data);
        Date datafim = evento.getDataFim();
        String dataFimFormatada = fmt.format(datafim);
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stmt = "FROM Evento where setor = '" + evento.getSetor() + "' "
                    + "AND status = \'Confirmado\' "
                    + "AND (('" + dataInicioFormatada + "' BETWEEN dataInicio AND dataFim) "
                    + "OR ('" + dataFimFormatada + "' BETWEEN dataInicio AND dataFim)) ";
            String stmt2 = "FROM Evento where setor = '" + evento.getSetor() + "' "
                    + "AND status = \'Confirmado\' "
                    + "AND ((dataInicio BETWEEN '" + dataInicioFormatada + "' AND '" + dataFimFormatada + "') "
                    + "OR (dataFim BETWEEN '" + dataInicioFormatada + "' AND '" + dataFimFormatada + "')) ";
            Query query = s.createQuery(stmt);
            Query query2 = s.createQuery(stmt2);
            if (query.list().isEmpty() && query2.list().isEmpty()) {
                return true; //retorna que a lista ta vazia
            } else {
                return false; //retorna que tem algo na lista
            }
        } catch (HibernateException e) {
            throw e;
        }
    }

// SELECT * FROM evento where setor = 'Teatro Margarida Schivasappa' 
// AND ((dataInicio BETWEEN '2019-03-01' AND '2019-03-30') 
// OR (dataFim BETWEEN '2019-03-01' AND '2019-03-30'))
// AND idEvento != 5
// UNION
// SELECT * FROM evento where setor = 'Teatro Margarida Schivasappa' 
// AND (('2019-03-01' BETWEEN dataInicio AND dataFim) 
// OR ('2019-03-30' BETWEEN dataInicio AND dataFim))
// AND idEvento != 5
    public boolean verificarData(Evento evento, Evento eventoselecionado) throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date data = evento.getDataInicio();
        String dataInicioFormatada = fmt.format(data);
        Date datafim = evento.getDataFim();
        String dataFimFormatada = fmt.format(datafim);
        int ev = eventoselecionado.getIdEvento();
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            String stmt = "FROM Evento where setor = '" + evento.getSetor() + "' "
                    + "AND (('" + dataInicioFormatada + "' BETWEEN dataInicio AND dataFim) "
                    + "OR ('" + dataFimFormatada + "' BETWEEN dataInicio AND dataFim)) "
                    + "AND idEvento != '" + ev + "' ";
            String stmt2 = "FROM Evento where setor = '" + evento.getSetor() + "' "
                    + "AND ((dataInicio BETWEEN '" + dataInicioFormatada + "' AND '" + dataFimFormatada + "') "
                    + "OR (dataFim BETWEEN '" + dataInicioFormatada + "' AND '" + dataFimFormatada + "')) "
                    + "AND idEvento != '" + ev + "' ";
            Query query = s.createQuery(stmt);
            Query query2 = s.createQuery(stmt2);

            if (query.list().isEmpty() && query2.list().isEmpty()) {
                return true; //retorna que a lista ta vazia
            } else {
                return false; //retorna que tem algo na lista
            }
        } catch (HibernateException e) {
            throw e;
        }
    }

    public void salvar(Evento evento) {

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

    public void remover(Evento evento) {

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

    public void alterar(Evento evento) {

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

    public List<Evento> listarEventos() {

        //Query em cima dos Objetos! (Classes)
        String queryObj = "from Evento where status != \'Encerrado\' ORDER BY dataInicio DESC";
        List<Evento> listaEvento = new ArrayList<>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        listaEvento = s.createQuery(queryObj).list();
        s.close();

        return listaEvento;

    }//listarEventos

    public Long qtdStatusAgendado() {

        //Query em cima dos Objetos! (Classes)
        String queryObj = "select count(*) from Evento login where status = \'Agendado\'";      
        Session s = HibernateUtil.getSessionFactory().openSession();            
        Query query = s.createQuery(queryObj);
        Long count = (Long)query.uniqueResult();      
        s.close();

        return count;

    }//quantidade de status agendado
    
        public Long qtdStatusConfirmado() {

        //Query em cima dos Objetos! (Classes)
        String queryObj = "select count(*) from Evento login where status = \'Confirmado\'";      
        Session s = HibernateUtil.getSessionFactory().openSession();            
        Query query = s.createQuery(queryObj);
        Long count = (Long)query.uniqueResult();      
        s.close();

        return count;

    }//quantidade de status confirmado
        
        public Long qtdStatusCancelado() {

        //Query em cima dos Objetos! (Classes)
        String queryObj = "select count(*) from Evento login where status = \'Cancelado\'";      
        Session s = HibernateUtil.getSessionFactory().openSession();            
        Query query = s.createQuery(queryObj);
        Long count = (Long)query.uniqueResult();      
        s.close();

        return count;

    }//quantidade de status cancelado

}
