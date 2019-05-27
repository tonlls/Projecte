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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "zona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z"),
    @NamedQuery(name = "Zona.findById", query = "SELECT z FROM Zona z WHERE z.zonaPK.id = :id"),
    @NamedQuery(name = "Zona.findByParcId", query = "SELECT z FROM Zona z WHERE z.zonaPK.parcId = :parcId"),
    @NamedQuery(name = "Zona.findByNom", query = "SELECT z FROM Zona z WHERE z.nom = :nom")})
public class Zona implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ZonaPK zonaPK;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "parc_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parc parc;
    @OneToMany(mappedBy = "zona")
    private List<Atraccio> atraccioList;

    public Zona() {
    }

    public Zona(ZonaPK zonaPK) {
        this.zonaPK = zonaPK;
    }

    public Zona(ZonaPK zonaPK, String nom) {
        this.zonaPK = zonaPK;
        this.nom = nom;
    }

    public Zona(int id, int parcId) {
        this.zonaPK = new ZonaPK(id, parcId);
    }

    public ZonaPK getZonaPK() {
        return zonaPK;
    }

    public void setZonaPK(ZonaPK zonaPK) {
        this.zonaPK = zonaPK;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Parc getParc() {
        return parc;
    }

    public void setParc(Parc parc) {
        this.parc = parc;
    }

    @XmlTransient
    public List<Atraccio> getAtraccioList() {
        return atraccioList;
    }

    public void setAtraccioList(List<Atraccio> atraccioList) {
        this.atraccioList = atraccioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zonaPK != null ? zonaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.zonaPK == null && other.zonaPK != null) || (this.zonaPK != null && !this.zonaPK.equals(other.zonaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Zona[ zonaPK=" + zonaPK + " ]";
    }
    
}
