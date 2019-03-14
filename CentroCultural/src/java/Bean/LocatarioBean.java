package Bean;

import DAO.FisicoDAO;
import DAO.JuridicoDAO;
import DAO.LocatarioDAO;
import Webservice.CepWebServiceBean;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Evento;
import modelo.Fisico;
import modelo.Juridico;
import modelo.Locatario;

/**
 *
 * @author Yusuke
 */
@SessionScoped
@ManagedBean
public class LocatarioBean {

    private List<Fisico> listaLocatarioFisico;
    private List<Juridico> listaLocatarioJuridico;
    private Fisico fisico;
    private Juridico juridico;
    private Locatario locatario;
//    private EventoBean eventoBean;
    private Locatario locatarioSelecionado;
    private List<Locatario> listaLocatario;
    private List<Locatario> listaLocatarioFiltrado;
    private CepWebServiceBean cepWS;
    private Utilidade utilidade = new Utilidade();

    public void salvarLocatario() {

        if (locatario.getIdLocatario() == null
                && juridico.getCnpj() == null
                && utilidade.isCPF(fisico.getCpf())
                && locatario.getNome().length() > 1
                && utilidade.isEmail(locatario.getEmail())) {
            getLocatario().setIdFisico(fisico);
//            fisico.setLocatario(getLocatario());
            salvarFisicoDAO();
            utilidade.mensagemAviso(1);
            limpar();
        } else {
            if (locatario.getIdLocatario() == null
                    && fisico.getCpf() == null
                    && utilidade.isCNPJ(juridico.getCnpj())
                    && locatario.getNome().length() > 1
                    && utilidade.isEmail(locatario.getEmail())) {
                getLocatario().setIdJuridico(juridico);
//            juridico.setLocatario(getLocatario());
                salvarJuridicoDAO();
                utilidade.mensagemAviso(1);
                limpar();
            } else {
                if (locatario.getIdLocatario() != null
                        && fisico.getCpf() != null
                        && utilidade.isCPF(fisico.getCpf())
                        && locatario.getNome().length() > 1
                        && utilidade.isEmail(locatario.getEmail())) {
                    LocatarioDAO lDAO = new LocatarioDAO();
                    FisicoDAO fDAO = new FisicoDAO();
                    fDAO.atualizar(fisico);
                    lDAO.atualizar(locatario);
                    utilidade.mensagemAviso(2);
                    limpar();
                } else {
                    if (locatario.getIdLocatario() != null
                            && juridico.getCnpj() != null
                            && utilidade.isCNPJ(juridico.getCnpj())
                            && locatario.getNome().length() > 1
                            && utilidade.isEmail(locatario.getEmail())) {
                        LocatarioDAO lDAO = new LocatarioDAO();
                        JuridicoDAO jDAO = new JuridicoDAO();
                        jDAO.atualizar(juridico);
                        lDAO.atualizar(locatario);
                        utilidade.mensagemAviso(2);
                        limpar();
                    } else {
                        utilidade.mensagemAviso(0);
                    }
                }
            }
        }
        setListaLocatario(null);
    }//salvar/alterarLocatario

    public void salvarFisicoDAO() {
        LocatarioDAO lDAO = new LocatarioDAO();
        FisicoDAO fDAO = new FisicoDAO();
        fDAO.salvar(fisico);
        lDAO.salvar(locatario);
    }

    public void salvarJuridicoDAO() {
        LocatarioDAO lDAO = new LocatarioDAO();
        JuridicoDAO jDAO = new JuridicoDAO();
        jDAO.salvar(juridico);
        lDAO.salvar(locatario);
    }

    public void removerLocatario() {
        LocatarioDAO lDAO = new LocatarioDAO();
        lDAO.remover(locatarioSelecionado);
        limpar();
        setListaLocatario(null);
//        eventoBean.setListaEvento(null);
        utilidade.mensagemAviso(3);
    }

    public void carregarLocatario() {
        setLocatario(locatarioSelecionado);
        setFisico(locatarioSelecionado.getIdFisico());
        setJuridico(locatarioSelecionado.getIdJuridico());
    }

    public void carregarCep() throws IOException {
        cepWS.buscarCEP();
        locatario.setCep(cepWS.getCep().getCep());
        locatario.setLogradouro(cepWS.getCep().getLogradouro());
        locatario.setBairro(cepWS.getCep().getBairro());
    }

    public void limpar() {
        setLocatario(null);
        setFisico(null);
        setJuridico(null);
    }

    /**
     * @return the fisico
     */
    public Fisico getFisico() {
        if (fisico == null) {
            fisico = new Fisico();
        }
        return fisico;
    }

    /**
     * @param fisico the fisico to set
     */
    public void setFisico(Fisico fisico) {
        this.fisico = fisico;
    }

    /**
     * @return the juridico
     */
    public Juridico getJuridico() {
        if (juridico == null) {
            juridico = new Juridico();
        }
        return juridico;
    }

    /**
     * @param juridico the juridico to set
     */
    public void setJuridico(Juridico juridico) {
        this.juridico = juridico;
    }

    /**
     * @return the locatario
     */
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
     * @return the locatarioSelecionado
     */
    public Locatario getLocatarioSelecionado() {
        return locatarioSelecionado;
    }

    /**
     * @param locatarioSelecionado the locatarioSelecionado to set
     */
    public void setLocatarioSelecionado(Locatario locatarioSelecionado) {
        this.locatarioSelecionado = locatarioSelecionado;
    }

    /**
     * @return the listaLocatario
     */
    public List<Locatario> getListaLocatario() {
        if (listaLocatario == null) {
            LocatarioDAO lDAO = new LocatarioDAO();
            listaLocatario = lDAO.listarLocatarios();
        }
        return listaLocatario;
    }

    /**
     * @param listaLocatario the listaLocatario to set
     */
    public void setListaLocatario(List<Locatario> listaLocatario) {
        this.listaLocatario = listaLocatario;
    }

    /**
     * @return the cepWS
     */
    public CepWebServiceBean getCepWS() {
        if (cepWS == null) {
            cepWS = new CepWebServiceBean();
        }
        return cepWS;
    }

    /**
     * @param cepWS the cepWS to set
     */
    public void setCepWS(CepWebServiceBean cepWS) {
        this.cepWS = cepWS;
    }

    /**
     * @return the listaLocatarioFiltrado
     */
    public List<Locatario> getListaLocatarioFiltrado() {
        return listaLocatarioFiltrado;
    }

    /**
     * @param listaLocatarioFiltrado the listaLocatarioFiltrado to set
     */
    public void setListaLocatarioFiltrado(List<Locatario> listaLocatarioFiltrado) {
        this.listaLocatarioFiltrado = listaLocatarioFiltrado;
    }
    
        public int quantidadeLocatarioFisico() {
        if (listaLocatarioFisico == null) {
            LocatarioDAO lDAO = new LocatarioDAO();
            listaLocatarioFisico = lDAO.listarLocatariosFisicos();
        }
        return listaLocatarioFisico.size();
    }//retorna a quantidade de locatarios do tipo PF
    
    public int quantidadeLocatarioJuridico() {
        if (listaLocatarioJuridico == null) {
            LocatarioDAO lDAO = new LocatarioDAO();
            listaLocatarioJuridico = lDAO.listarLocatariosJuridicos();
        }
        return listaLocatarioJuridico.size();
    }//retorna a quantidade de locatarios do tipo PJ

}//class
