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
@Table(name = "tipus_pasi_expres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipusPasiExpres.findAll", query = "SELECT t FROM TipusPasiExpres t"),
    @NamedQuery(name = "TipusPasiExpres.findById", query = "SELECT t FROM TipusPasiExpres t WHERE t.id = :id"),
    @NamedQuery(name = "TipusPasiExpres.findByNom", query = "SELECT t FROM TipusPasiExpres t WHERE t.nom = :nom"),
    @NamedQuery(name = "TipusPasiExpres.findByPreuDia", query = "SELECT t FROM TipusPasiExpres t WHERE t.preuDia = :preuDia")})
public class TipusPasiExpres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nom")
    private String nom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preu_dia")
    private Float preuDia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipusPasiExpres")
    private List<TipusAccesAtraccio> tipusAccesAtraccioList;
    @OneToMany(mappedBy = "tipusId")
    private List<PasiExpres> pasiExpresList;

    public TipusPasiExpres() {
    }

    public TipusPasiExpres(Integer id) {
        this.id = id;
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

    public Float getPreuDia() {
        return preuDia;
    }

    public void setPreuDia(Float preuDia) {
        this.preuDia = preuDia;
    }

    @XmlTransient
    public List<TipusAccesAtraccio> getTipusAccesAtraccioList() {
        return tipusAccesAtraccioList;
    }

    public void setTipusAccesAtraccioList(List<TipusAccesAtraccio> tipusAccesAtraccioList) {
        this.tipusAccesAtraccioList = tipusAccesAtraccioList;
    }

    @XmlTransient
    public List<PasiExpres> getPasiExpresList() {
        return pasiExpresList;
    }

    public void setPasiExpresList(List<PasiExpres> pasiExpresList) {
        this.pasiExpresList = pasiExpresList;
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
        if (!(object instanceof TipusPasiExpres)) {
            return false;
        }
        TipusPasiExpres other = (TipusPasiExpres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.TipusPasiExpres[ id=" + id + " ]";
    }
    
}
