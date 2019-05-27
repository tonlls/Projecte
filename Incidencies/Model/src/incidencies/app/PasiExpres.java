/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tonll
 */
@Entity
@Table(name = "pasi_expres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PasiExpres.findAll", query = "SELECT p FROM PasiExpres p"),
    @NamedQuery(name = "PasiExpres.findById", query = "SELECT p FROM PasiExpres p WHERE p.id = :id"),
    @NamedQuery(name = "PasiExpres.findByData", query = "SELECT p FROM PasiExpres p WHERE p.data = :data")})
public class PasiExpres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne
    private Client clientId;
    @JoinColumn(name = "tipus_id", referencedColumnName = "id")
    @ManyToOne
    private TipusPasiExpres tipusId;
    @OneToMany(mappedBy = "pasiId")
    private List<InfoUtilitzacio> infoUtilitzacioList;

    public PasiExpres() {
    }

    public PasiExpres(Integer id) {
        this.id = id;
    }

    public PasiExpres(Integer id, Date data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public TipusPasiExpres getTipusId() {
        return tipusId;
    }

    public void setTipusId(TipusPasiExpres tipusId) {
        this.tipusId = tipusId;
    }

    @XmlTransient
    public List<InfoUtilitzacio> getInfoUtilitzacioList() {
        return infoUtilitzacioList;
    }

    public void setInfoUtilitzacioList(List<InfoUtilitzacio> infoUtilitzacioList) {
        this.infoUtilitzacioList = infoUtilitzacioList;
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
        if (!(object instanceof PasiExpres)) {
            return false;
        }
        PasiExpres other = (PasiExpres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.PasiExpres[ id=" + id + " ]";
    }
    
}
