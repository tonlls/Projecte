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
@Table(name = "estat_operatiu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatOperatiu.findAll", query = "SELECT e FROM EstatOperatiu e"),
    @NamedQuery(name = "EstatOperatiu.findById", query = "SELECT e FROM EstatOperatiu e WHERE e.id = :id"),
    @NamedQuery(name = "EstatOperatiu.findByNom", query = "SELECT e FROM EstatOperatiu e WHERE e.nom = :nom")})
public class EstatOperatiu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "estatOperatiuId")
    private List<Incidencia> incidenciaList;
    @OneToMany(mappedBy = "estatActualId")
    private List<Atraccio> atraccioList;

    public EstatOperatiu() {
    }

    public EstatOperatiu(Integer id) {
        this.id = id;
    }

    public EstatOperatiu(Integer id, String nom) {
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
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatOperatiu)) {
            return false;
        }
        EstatOperatiu other = (EstatOperatiu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNom();
        //return "incidencies.app.EstatOperatiu[ id=" + id + " ]";
    }
    
}
