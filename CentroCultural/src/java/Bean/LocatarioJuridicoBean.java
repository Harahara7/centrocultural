/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import DAO.JuridicoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Juridico;

@ManagedBean
@SessionScoped
public class LocatarioJuridicoBean {
    
    private Juridico juridico;
    private Juridico juridicoSelecionado;
    private List<Juridico> listaJuridico;
    private Utilidade utilidade = new Utilidade();

    public void salvarJuridico(){
    
        if(juridico.getIdJuridico()== null){
            JuridicoDAO jDAO = new JuridicoDAO();
            jDAO.salvar(juridico);
            utilidade.mensagemAviso(1);
        }else{
            JuridicoDAO jDAO = new JuridicoDAO();
            jDAO.atualizar(juridico);
            utilidade.mensagemAviso(2);
        }
        limpar();
    }
    
    public void carregarJuridico(){
        setJuridico(juridicoSelecionado);
    }
        
    public void removerJuridico(){
        JuridicoDAO jDAO = new JuridicoDAO();
        jDAO.remover(juridicoSelecionado);
        limpar();
        utilidade.mensagemAviso(3);
    }
    
    public void limpar(){
        setJuridico(null);
        setListaJuridico(null);
    }
    
    public Juridico getJuridico() {
        if(juridico == null){
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
     * @return the juridicoSelecionado
     */
    public Juridico getJuridicoSelecionado() {
        return juridicoSelecionado;
    }

    /**
     * @param juridicoSelecionado the juridicoSelecionado to set
     */
    public void setJuridicoSelecionado(Juridico juridicoSelecionado) {
        this.juridicoSelecionado = juridicoSelecionado;
    }

    /**
     * @return the listaJuridico
     */
    public List<Juridico> getListaJuridico() {
        if(listaJuridico == null){
            JuridicoDAO jDAO = new JuridicoDAO();
            listaJuridico = jDAO.listarJuridicos();
        }
        return listaJuridico;
    }

    /**
     * @param listaJuridico the listaJuridico to set
     */
    public void setListaJuridico(List<Juridico> listaJuridico) {
        this.listaJuridico = listaJuridico;
    }
    
}
