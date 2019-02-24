/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yusuke
 */
@Entity
@Table(name = "fisico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fisico.findAll", query = "SELECT f FROM Fisico f")
    , @NamedQuery(name = "Fisico.findByIdFisico", query = "SELECT f FROM Fisico f WHERE f.idFisico = :idFisico")
    , @NamedQuery(name = "Fisico.findByCpf", query = "SELECT f FROM Fisico f WHERE f.cpf = :cpf")})
public class Fisico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFisico")
    private Integer idFisico;
    @Size(max = 45)
    @Column(name = "cpf", unique = true)
    private String cpf;
    @OneToOne(cascade = CascadeType.ALL, 
            mappedBy = "idFisico", 
            fetch = FetchType.EAGER, 
            orphanRemoval = true)
    private Locatario locatario;

    public Fisico() {
    }

    public Fisico(Integer idFisico) {
        this.idFisico = idFisico;
    }

    public Integer getIdFisico() {
        return idFisico;
    }

    public void setIdFisico(Integer idFisico) {
        this.idFisico = idFisico;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @XmlTransient
    public Locatario getLocatario() {
        if(locatario == null){
        locatario = new Locatario();
        }
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFisico != null ? idFisico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fisico)) {
            return false;
        }
        Fisico other = (Fisico) object;
        if ((this.idFisico == null && other.idFisico != null) || (this.idFisico != null && !this.idFisico.equals(other.idFisico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //Eu nunca vi na minha vida um toString converter errado um Int...
        //return "modelo.Fisico[ idFisico=" + idFisico + " ]";
        return ""+idFisico;
    }
    
}
