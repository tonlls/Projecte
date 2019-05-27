/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tonll
 */
@Entity
@Table(name = "info_utilitzacio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoUtilitzacio.findAll", query = "SELECT i FROM InfoUtilitzacio i"),
    @NamedQuery(name = "InfoUtilitzacio.findById", query = "SELECT i FROM InfoUtilitzacio i WHERE i.id = :id"),
    @NamedQuery(name = "InfoUtilitzacio.findByNumUsos", query = "SELECT i FROM InfoUtilitzacio i WHERE i.numUsos = :numUsos")})
public class InfoUtilitzacio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "num_usos")
    private int numUsos;
    @JoinColumn(name = "pasi_id", referencedColumnName = "id")
    @ManyToOne
    private PasiExpres pasiId;
    @JoinColumn(name = "atraccio_id", referencedColumnName = "id")
    @ManyToOne
    private Atraccio atraccioId;

    public InfoUtilitzacio() {
    }

    public InfoUtilitzacio(Integer id) {
        this.id = id;
    }

    public InfoUtilitzacio(Integer id, int numUsos) {
        this.id = id;
        this.numUsos = numUsos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumUsos() {
        return numUsos;
    }

    public void setNumUsos(int numUsos) {
        this.numUsos = numUsos;
    }

    public PasiExpres getPasiId() {
        return pasiId;
    }

    public void setPasiId(PasiExpres pasiId) {
        this.pasiId = pasiId;
    }

    public Atraccio getAtraccioId() {
        return atraccioId;
    }

    public void setAtraccioId(Atraccio atraccioId) {
        this.atraccioId = atraccioId;
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
        if (!(object instanceof InfoUtilitzacio)) {
            return false;
        }
        InfoUtilitzacio other = (InfoUtilitzacio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.InfoUtilitzacio[ id=" + id + " ]";
    }
    
}
