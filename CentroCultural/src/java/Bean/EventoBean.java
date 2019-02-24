/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

//import Webservice.CepWebServiceBean;
import DAO.EventoDAO;
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
//    private CepWebServiceBean cep;
    private Utilidade utilidade = new Utilidade();
    
    
    
    public void salvarEvento(){
    
        if(evento.getIdEvento() == null){
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
        }else{
        EventoDAO eDAO = new EventoDAO();
        eDAO.alterar(evento);
        utilidade.mensagemAviso(2);
        }
        limpar();
    }

    public void confirmarEvento(){
        setEvento(eventoSelecionado);
        evento.setStatus("Confirmado");
        EventoDAO eDAO = new EventoDAO();
        eDAO.alterar(evento);
        limpar();
        utilidade.mensagemAviso(4);
    }
    
    public void removerEvento(){
        EventoDAO eDAO = new EventoDAO();
        eDAO.remover(eventoSelecionado);
        limpar();
        utilidade.mensagemAviso(3);
    }//removerEvento
    
    public void carregarEvento(){
        setEvento(eventoSelecionado);
        setLocatario(eventoSelecionado.getIdLocatario());
    }//carregarEvento
    
//    public void carregarSetorBox(){
//        getEventoSelecionado().getSetor();
//    }
//    
    public void limpar(){
        setEvento(null);
        setListaEvento(null);
    }
    
    public Locatario getLocatario() {
        if(locatario == null){
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
        if(evento == null){
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
        if(listaEvento == null){
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
