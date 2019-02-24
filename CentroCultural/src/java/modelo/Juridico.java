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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yusuke
 */
@Entity
@Table(name = "juridico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juridico.findAll", query = "SELECT j FROM Juridico j")
    , @NamedQuery(name = "Juridico.findByIdJuridico", query = "SELECT j FROM Juridico j WHERE j.idJuridico = :idJuridico")
    , @NamedQuery(name = "Juridico.findByCnpj", query = "SELECT j FROM Juridico j WHERE j.cnpj = :cnpj")})
public class Juridico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idJuridico")
    private Integer idJuridico;
    @Size(max = 45)
    @Column(name = "cnpj")
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idJuridico",
            fetch = FetchType.EAGER, 
            orphanRemoval = true)
    private Locatario locatario;

    public Juridico() {
    }

    public Juridico(Integer idJuridico) {
        this.idJuridico = idJuridico;
    }

    public Integer getIdJuridico() {
        return idJuridico;
    }

    public void setIdJuridico(Integer idJuridico) {
        this.idJuridico = idJuridico;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
        hash += (idJuridico != null ? idJuridico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juridico)) {
            return false;
        }
        Juridico other = (Juridico) object;
        if ((this.idJuridico == null && other.idJuridico != null) || (this.idJuridico != null && !this.idJuridico.equals(other.idJuridico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + idJuridico;
    }
    
}
