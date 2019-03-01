/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Webservice.CepWebServiceBean;
import DAO.EventoDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Evento;
import modelo.Locatario;

/**
 *
 * @author Yusuke
 */
@ManagedBean
@SessionScoped
public class EventoBean {

    private Locatario locatario;
    private Evento evento;
    private Evento eventoSelecionado;
    private List<Evento> listaEvento;
    private List<Evento> listaEventoFiltrado;
    private Utilidade utilidade = new Utilidade();
    private Date dataHoje = new Date(); //Objeto já traz a data atual.

    public void salvarEvento() {
        if (evento.getNome().length() > 1
                && evento.getDataInicio() != null
                && evento.getDataFim() != null
                && evento.getPreco().length() > 1
                && evento.getIdEvento() == null
                ) {
//        if(evento.getIdEvento() == null){
            //evento.setIdLocatario e getEvento faz a mesma coisa.
            /*Aqui é recuperado o idLocatario que estava na tela anterior,
        e é salva na entidade de Evento.
        Essa operação é necessária pra referenciar a FK que está na Entidade
        Evento!
             */
            //evento.setIdLocatario(locatario);
            getEvento().setIdLocatario(locatario);
            /*Como a entidade locatario possui uma Lista de Eventos,
        é necessário salvar nessa lista, o evento que será criado!
             */
            locatario.getEventoList().add(getEvento());
            EventoDAO eDAO = new EventoDAO();
            eDAO.salvar(evento);
            utilidade.mensagemAviso(1);
            limpar();
        } else {
            if (evento.getIdLocatario() != null
                    && evento.getIdEvento() != null
                    && evento.getNome().length() > 1
                    && evento.getDataInicio().before(evento.getDataFim()) 
                    && evento.getPreco().length() > 1
                    ) {
                EventoDAO eDAO = new EventoDAO();
                eDAO.alterar(evento);
                utilidade.mensagemAviso(2);
                limpar();
            } else {
                utilidade.mensagemAviso(0);
            }
        }
//É necessário limpar a lista de Eventos, pois
//toda vez que algo for digitado nos campos, tudo vai ser sobrescrito na lista
//Então, limpar a lista, força pegar uma nova lista do banco.
        setListaEvento(null);
    }

    public void confirmarEvento() {
        setEvento(eventoSelecionado);
        evento.setStatus("Confirmado");
        EventoDAO eDAO = new EventoDAO();
        eDAO.alterar(evento);
        limpar();
        setListaEvento(null);
        utilidade.mensagemAviso(4);
    }

    public void removerEvento() {
        EventoDAO eDAO = new EventoDAO();
        eDAO.remover(eventoSelecionado);
        limpar();
        setListaEvento(null);
        utilidade.mensagemAviso(3);
    }//removerEvento

    public void carregarEvento() {
        setEvento(eventoSelecionado);
        setLocatario(eventoSelecionado.getIdLocatario());
    }//carregarEvento

    public Date getDataHoje() {
        return dataHoje;
    }

    public Date ajustarDataMin() {
        //ajusta a data minima de cadastro baseada na data do primeiro calendario
        return evento.getDataInicio();
    }

    public void limpar() {
        setEvento(null);
        setLocatario(null);
    }

    public Locatario getLocatario() {
        if (locatario == null) {
            locatario = new Locatario();
        }
        return locatario;
    }

    /**
     * @param locatario the locatario to set
     */
    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    /**
     * @return the evento
     */
    public Evento getEvento() {
        if (evento == null) {
            evento = new Evento();
        }
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * @return the eventoSelecionado
     */
    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    /**
     * @param eventoSelecionado the eventoSelecionado to set
     */
    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }

    /**
     * @return the listaEvento
     */
    public List<Evento> getListaEvento() {
        if (listaEvento == null) {
            EventoDAO eDAO = new EventoDAO();
            listaEvento = eDAO.listarEventos();
        }
        return listaEvento;
    }

    /**
     * @param listaEvento the listaEvento to set
     */
    public void setListaEvento(List<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

//    public CepWebServiceBean getCep() {
//        if(cep == null){
//        cep = new CepWebServiceBean();
//        }
//        return cep;
//    }
//
//    public void setCep(CepWebServiceBean cep) {
//        this.cep = cep;
//    }
    /**
     * @return the listaEventoFiltrado
     */
    public List<Evento> getListaEventoFiltrado() {
        return listaEventoFiltrado;
    }

    /**
     * @param listaEventoFiltrado the listaEventoFiltrado to set
     */
    public void setListaEventoFiltrado(List<Evento> listaEventoFiltrado) {
        this.listaEventoFiltrado = listaEventoFiltrado;
    }

}
