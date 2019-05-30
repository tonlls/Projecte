/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tonll
 */
@Entity
@Table(name = "preu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preu.findAll", query = "SELECT p FROM Preu p"),
    @NamedQuery(name = "Preu.findById", query = "SELECT p FROM Preu p WHERE p.id = :id"),
    @NamedQuery(name = "Preu.findByDies", query = "SELECT p FROM Preu p WHERE p.dies = :dies"),
    @NamedQuery(name = "Preu.findByPreuAdult", query = "SELECT p FROM Preu p WHERE p.preuAdult = :preuAdult"),
    @NamedQuery(name = "Preu.findByPreuNenSenior", query = "SELECT p FROM Preu p WHERE p.preuNenSenior = :preuNenSenior"),
    @NamedQuery(name = "Preu.findByPreuDiscapacitat", query = "SELECT p FROM Preu p WHERE p.preuDiscapacitat = :preuDiscapacitat")})
public class Preu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dies")
    private int dies;
    @Basic(optional = false)
    @Column(name = "preu_adult")
    private int preuAdult;
    @Basic(optional = false)
    @Column(name = "preu_nen_senior")
    private int preuNenSenior;
    @Basic(optional = false)
    @Column(name = "preu_discapacitat")
    private int preuDiscapacitat;
    @JoinTable(name = "preu_parc", joinColumns = {
        @JoinColumn(name = "preu_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "parc_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Parc> parcList;
    @OneToMany(mappedBy = "preuId")
    private List<Entrada> entradaList;

    public Preu() {
    }

    public Preu(Integer id) {
        this.id = id;
    }

    public Preu(Integer id, int dies, int preuAdult, int preuNenSenior, int preuDiscapacitat) {
        this.id = id;
        this.dies = dies;
        this.preuAdult = preuAdult;
        this.preuNenSenior = preuNenSenior;
        this.preuDiscapacitat = preuDiscapacitat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDies() {
        return dies;
    }

    public void setDies(int dies) {
        this.dies = dies;
    }

    public int getPreuAdult() {
        return preuAdult;
    }

    public void setPreuAdult(int preuAdult) {
        this.preuAdult = preuAdult;
    }

    public int getPreuNenSenior() {
        return preuNenSenior;
    }

    public void setPreuNenSenior(int preuNenSenior) {
        this.preuNenSenior = preuNenSenior;
    }

    public int getPreuDiscapacitat() {
        return preuDiscapacitat;
    }

    public void setPreuDiscapacitat(int preuDiscapacitat) {
        this.preuDiscapacitat = preuDiscapacitat;
    }

    @XmlTransient
    public List<Parc> getParcList() {
        return parcList;
    }

    public void setParcList(List<Parc> parcList) {
        this.parcList = parcList;
    }

    @XmlTransient
    public List<Entrada> getEntradaList() {
        return entradaList;
    }

    public void setEntradaList(List<Entrada> entradaList) {
        this.entradaList = entradaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preu)) {
            return false;
        }
        Preu other = (Preu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Preu[ id=" + id + " ]";
    }
    
}
