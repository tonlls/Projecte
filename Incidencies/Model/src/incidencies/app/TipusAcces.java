/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipus_acces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipusAcces.findAll", query = "SELECT t FROM TipusAcces t"),
    @NamedQuery(name = "TipusAcces.findById", query = "SELECT t FROM TipusAcces t WHERE t.id = :id"),
    @NamedQuery(name = "TipusAcces.findByNom", query = "SELECT t FROM TipusAcces t WHERE t.nom = :nom")})
public class TipusAcces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipusAccesId")
    private List<TipusAccesAtraccio> tipusAccesAtraccioList;

    public TipusAcces() {
    }

    public TipusAcces(Integer id) {
        this.id = id;
    }

    public TipusAcces(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<TipusAccesAtraccio> getTipusAccesAtraccioList() {
        return tipusAccesAtraccioList;
    }

    public void setTipusAccesAtraccioList(List<TipusAccesAtraccio> tipusAccesAtraccioList) {
        this.tipusAccesAtraccioList = tipusAccesAtraccioList;
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
        if (!(object instanceof TipusAcces)) {
            return false;
        }
        TipusAcces other = (TipusAcces) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.TipusAcces[ id=" + id + " ]";
    }
    
}
