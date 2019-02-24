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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yusuke
 */
@Entity
@Table(name = "locatario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locatario.findAll", query = "SELECT l FROM Locatario l")
    , @NamedQuery(name = "Locatario.findByIdLocatario", query = "SELECT l FROM Locatario l WHERE l.idLocatario = :idLocatario")
    , @NamedQuery(name = "Locatario.findByNome", query = "SELECT l FROM Locatario l WHERE l.nome = :nome")
    , @NamedQuery(name = "Locatario.findByContato", query = "SELECT l FROM Locatario l WHERE l.contato = :contato")})
public class Locatario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLocatario")
    private Integer idLocatario;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Size(max = 100)
    @Column(name = "contato")
    private String contato;
    //@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 9)
    @Column(name = "cep")
    private String cep;
    @Size(max = 150)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 60)
    @Column(name = "bairro")
    private String bairro;
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "idLocatario",
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Evento> eventoList;
    @JoinColumn(name = "idFisico", referencedColumnName = "idFisico")
    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)//mexi, era false nos dois
    private Fisico idFisico;
    @JoinColumn(name = "idJuridico", referencedColumnName = "idJuridico")
    @ManyToOne(optional = true, cascade = CascadeType.REMOVE)
    private Juridico idJuridico;

    public Locatario() {
    }

    public Locatario(Integer idLocatario) {
        this.idLocatario = idLocatario;
    }

    public Integer getIdLocatario() {
        return idLocatario;
    }

    public void setIdLocatario(Integer idLocatario) {
        this.idLocatario = idLocatario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        if(eventoList == null){
        eventoList = new ArrayList<>();
        }
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public Fisico getIdFisico() {
        return idFisico;
    }

    public void setIdFisico(Fisico idFisico) {
        this.idFisico = idFisico;
    }

    public Juridico getIdJuridico() {
        return idJuridico;
    }

    public void setIdJuridico(Juridico idJuridico) {
        this.idJuridico = idJuridico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocatario != null ? idLocatario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locatario)) {
            return false;
        }
        Locatario other = (Locatario) object;
        if ((this.idLocatario == null && other.idLocatario != null) || (this.idLocatario != null && !this.idLocatario.equals(other.idLocatario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+idLocatario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
}
