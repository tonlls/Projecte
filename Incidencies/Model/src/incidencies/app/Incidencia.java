/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tonll
 */
@Entity
@Table(name = "incidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencia.findAll", query = "SELECT i FROM Incidencia i"),
    @NamedQuery(name = "Incidencia.findById", query = "SELECT i FROM Incidencia i WHERE i.incidenciaPK.id = :id"),
    @NamedQuery(name = "Incidencia.findByAtraccioId", query = "SELECT i FROM Incidencia i WHERE i.incidenciaPK.atraccioId = :atraccioId"),
    @NamedQuery(name = "Incidencia.findByDataInici", query = "SELECT i FROM Incidencia i WHERE i.dataInici = :dataInici"),
    @NamedQuery(name = "Incidencia.findByDataFi", query = "SELECT i FROM Incidencia i WHERE i.dataFi = :dataFi"),
    @NamedQuery(name = "Incidencia.findByMisatgeEstat", query = "SELECT i FROM Incidencia i WHERE i.misatgeEstat = :misatgeEstat"),
    @NamedQuery(name = "Incidencia.findByDataFiPrevista", query = "SELECT i FROM Incidencia i WHERE i.dataFiPrevista = :dataFiPrevista")})
public class Incidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IncidenciaPK incidenciaPK;
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
    @JoinColumn(name = "atraccio_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atraccio atraccio;
    @JoinColumn(name = "estat_operatiu_id", referencedColumnName = "id")
    @ManyToOne
    private EstatOperatiu estatOperatiuId;

    public Incidencia() {
    }

    public Incidencia(IncidenciaPK incidenciaPK) {
        this.incidenciaPK = incidenciaPK;
    }

    public Incidencia(IncidenciaPK incidenciaPK, Date dataInici) {
        this.incidenciaPK = incidenciaPK;
        this.dataInici = dataInici;
    }

    public Incidencia(int id, int atraccioId) {
        this.incidenciaPK = new IncidenciaPK(id, atraccioId);
    }

    public IncidenciaPK getIncidenciaPK() {
        return incidenciaPK;
    }

    public void setIncidenciaPK(IncidenciaPK incidenciaPK) {
        this.incidenciaPK = incidenciaPK;
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

    public Atraccio getAtraccio() {
        return atraccio;
    }

    public void setAtraccio(Atraccio atraccio) {
        this.atraccio = atraccio;
    }

    public EstatOperatiu getEstatOperatiuId() {
        return estatOperatiuId;
    }

    public void setEstatOperatiuId(EstatOperatiu estatOperatiuId) {
        this.estatOperatiuId = estatOperatiuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (incidenciaPK != null ? incidenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencia)) {
            return false;
        }
        Incidencia other = (Incidencia) object;
        if ((this.incidenciaPK == null && other.incidenciaPK != null) || (this.incidenciaPK != null && !this.incidenciaPK.equals(other.incidenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Incidencia[ incidenciaPK=" + incidenciaPK + " ]";
    }
    
}
