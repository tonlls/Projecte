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
@Table(name = "parc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parc.findAll", query = "SELECT p FROM Parc p"),
    @NamedQuery(name = "Parc.findById", query = "SELECT p FROM Parc p WHERE p.id = :id"),
    @NamedQuery(name = "Parc.findByNom", query = "SELECT p FROM Parc p WHERE p.nom = :nom"),
    @NamedQuery(name = "Parc.findByUrlFoto", query = "SELECT p FROM Parc p WHERE p.urlFoto = :urlFoto")})
public class Parc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "url_foto")
    private String urlFoto;
    @ManyToMany(mappedBy = "parcList")
    private List<Preu> preuList;
    @ManyToMany(mappedBy = "parcList")
    private List<Entrada> entradaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parc")
    private List<Zona> zonaList;

    public Parc() {
    }

    public Parc(Integer id) {
        this.id = id;
    }

    public Parc(Integer id, String nom, String urlFoto) {
        this.id = id;
        this.nom = nom;
        this.urlFoto = urlFoto;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @XmlTransient
    public List<Preu> getPreuList() {
        return preuList;
    }

    public void setPreuList(List<Preu> preuList) {
        this.preuList = preuList;
    }

    @XmlTransient
    public List<Entrada> getEntradaList() {
        return entradaList;
    }

    public void setEntradaList(List<Entrada> entradaList) {
        this.entradaList = entradaList;
    }

    @XmlTransient
    public List<Zona> getZonaList() {
        return zonaList;
    }

    public void setZonaList(List<Zona> zonaList) {
        this.zonaList = zonaList;
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
        if (!(object instanceof Parc)) {
            return false;
        }
        Parc other = (Parc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Parc[ id=" + id + " ]";
    }
    
}
