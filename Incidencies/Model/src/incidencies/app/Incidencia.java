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
@Table(name = "incidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencia.findAll", query = "SELECT i FROM Incidencia i"),
    @NamedQuery(name = "Incidencia.findById", query = "SELECT i FROM Incidencia i WHERE i.id = :id"),
    @NamedQuery(name = "Incidencia.findByOberta", query = "SELECT i FROM Incidencia i WHERE i.oberta = :oberta"),
    @NamedQuery(name = "Incidencia.findByDataInici", query = "SELECT i FROM Incidencia i WHERE i.dataInici = :dataInici"),
    @NamedQuery(name = "Incidencia.findByDataFi", query = "SELECT i FROM Incidencia i WHERE i.dataFi = :dataFi"),
    @NamedQuery(name = "Incidencia.findByMisatgeEstat", query = "SELECT i FROM Incidencia i WHERE i.misatgeEstat = :misatgeEstat"),
    @NamedQuery(name = "Incidencia.findByDataFiPrevista", query = "SELECT i FROM Incidencia i WHERE i.dataFiPrevista = :dataFiPrevista")})
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "oberta")
    private boolean oberta;
    @Basic(optional = false)
    @Column(name = "data_inici")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInici;
    @Column(name = "data_fi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFi;
    @Column(name = "misatge_estat")
    private String misatgeEstat;
    @Column(name = "data_fi_prevista")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFiPrevista;
    @JoinColumn(name = "atraccio_id", referencedColumnName = "id")
    @ManyToOne
    private Atraccio atraccioId;
    @JoinColumn(name = "estat_operatiu_id", referencedColumnName = "id")
    @ManyToOne
    private EstatOperatiu estatOperatiuId;
    @OneToMany(mappedBy = "incidenciaId")
    private List<Atraccio> atraccioList;

    public Incidencia() {
    }

    public Incidencia(Integer id) {
        this.id = id;
    }

    public Incidencia(Integer id, boolean oberta, Date dataInici) {
        this.id = id;
        this.oberta = oberta;
        this.dataInici = dataInici;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getOberta() {
        return oberta;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public String getMisatgeEstat() {
        return misatgeEstat;
    }

    public void setMisatgeEstat(String misatgeEstat) {
        this.misatgeEstat = misatgeEstat;
    }

    public Date getDataFiPrevista() {
        return dataFiPrevista;
    }

    public void setDataFiPrevista(Date dataFiPrevista) {
        this.dataFiPrevista = dataFiPrevista;
    }

    public Atraccio getAtraccioId() {
        return atraccioId;
    }

    public void setAtraccioId(Atraccio atraccioId) {
        this.atraccioId = atraccioId;
    }

    public EstatOperatiu getEstatOperatiuId() {
        return estatOperatiuId;
    }

    public void setEstatOperatiuId(EstatOperatiu estatOperatiuId) {
        this.estatOperatiuId = estatOperatiuId;
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
        if (!(object instanceof Incidencia)) {
            return false;
        }
        Incidencia other = (Incidencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Incidencia[ id=" + id + " ]";
    }
    
}
