/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yusuke
 */
@Entity
@Table(name = "convidado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Convidado.findAll", query = "SELECT c FROM Convidado c")
    , @NamedQuery(name = "Convidado.findByIdConvidado", query = "SELECT c FROM Convidado c WHERE c.idConvidado = :idConvidado")
    , @NamedQuery(name = "Convidado.findByNome", query = "SELECT c FROM Convidado c WHERE c.nome = :nome")
    , @NamedQuery(name = "Convidado.findByCpf", query = "SELECT c FROM Convidado c WHERE c.cpf = :cpf")})
public class Convidado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConvidado")
    private Integer idConvidado;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "cpf")
    private String cpf;
    @OneToMany(mappedBy = "idConvidado")
    private List<Evento> eventoList;

    public Convidado() {
    }

    public Convidado(Integer idConvidado) {
        this.idConvidado = idConvidado;
    }

    public Integer getIdConvidado() {
        return idConvidado;
    }

    public void setIdConvidado(Integer idConvidado) {
        this.idConvidado = idConvidado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConvidado != null ? idConvidado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convidado)) {
            return false;
        }
        Convidado other = (Convidado) object;
        if ((this.idConvidado == null && other.idConvidado != null) || (this.idConvidado != null && !this.idConvidado.equals(other.idConvidado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Convidado[ idConvidado=" + idConvidado + " ]";
    }
    
}
