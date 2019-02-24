package Bean;

import DAO.FisicoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Fisico;

@SessionScoped
@ManagedBean
public class LocatarioFisicoBean {
    
    private Fisico fisico;
    private Fisico fisicoSelecionado;
    private List<Fisico> listaFisico;
    private Utilidade utilidade = new Utilidade();
    
    public void salvarFisico(){
        if(fisico.getIdFisico() == null){
            if(fisico.getCpf() == null){ //como verificar se o cpf contem 15 caracteres
                utilidade.mensagemAviso(0);
            } else {
                FisicoDAO fDAO = new FisicoDAO();
                fDAO.salvar(fisico);
                utilidade.mensagemAviso(1);
            }
        }else{
            FisicoDAO fDAO = new FisicoDAO();
            fDAO.atualizar(fisico);
            utilidade.mensagemAviso(2);
        }
        limpar();
        setListaFisico(null);
    }
    
    public void removerFisico(){
    
        FisicoDAO fDAO = new FisicoDAO();
        fDAO.deletar(fisicoSelecionado);
        limpar();
        setListaFisico(null);
        utilidade.mensagemAviso(3);
        
    }
    
    public void limpar(){
        setFisico(null);
    }

    public void carregarFisico(){
        setFisico(fisicoSelecionado);
    }
    
    public Fisico getFisico() {
        if(fisico == null){
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
     * @return the fisicoSelecionado
     */
    public Fisico getFisicoSelecionado() {
        return fisicoSelecionado;
    }

    /**
     * @param fisicoSelecionado the fisicoSelecionado to set
     */
    public void setFisicoSelecionado(Fisico fisicoSelecionado) {
        this.fisicoSelecionado = fisicoSelecionado;
    }

    /**
     * @return the listaFisico
     */
    public List<Fisico> getListaFisico() {
        if(listaFisico == null){
            FisicoDAO fDAO = new FisicoDAO();
            listaFisico = fDAO.listarFisicos();
        }
        return listaFisico;
    }

    /**
     * @param listaFisico the listaFisico to set
     */
    public void setListaFisico(List<Fisico> listaFisico) {
        this.listaFisico = listaFisico;
    }
    
}
