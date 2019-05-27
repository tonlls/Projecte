/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tipus_acces_atraccio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipusAccesAtraccio.findAll", query = "SELECT t FROM TipusAccesAtraccio t"),
    @NamedQuery(name = "TipusAccesAtraccio.findByTipusPasiId", query = "SELECT t FROM TipusAccesAtraccio t WHERE t.tipusAccesAtraccioPK.tipusPasiId = :tipusPasiId"),
    @NamedQuery(name = "TipusAccesAtraccio.findByAtraccioId", query = "SELECT t FROM TipusAccesAtraccio t WHERE t.tipusAccesAtraccioPK.atraccioId = :atraccioId")})
public class TipusAccesAtraccio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipusAccesAtraccioPK tipusAccesAtraccioPK;
    @JoinColumn(name = "tipus_pasi_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipusPasiExpres tipusPasiExpres;
    @JoinColumn(name = "atraccio_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Atraccio atraccio;
    @JoinColumn(name = "tipus_acces_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipusAcces tipusAccesId;

    public TipusAccesAtraccio() {
    }

    public TipusAccesAtraccio(TipusAccesAtraccioPK tipusAccesAtraccioPK) {
        this.tipusAccesAtraccioPK = tipusAccesAtraccioPK;
    }

    public TipusAccesAtraccio(int tipusPasiId, int atraccioId) {
        this.tipusAccesAtraccioPK = new TipusAccesAtraccioPK(tipusPasiId, atraccioId);
    }

    public TipusAccesAtraccioPK getTipusAccesAtraccioPK() {
        return tipusAccesAtraccioPK;
    }

    public void setTipusAccesAtraccioPK(TipusAccesAtraccioPK tipusAccesAtraccioPK) {
        this.tipusAccesAtraccioPK = tipusAccesAtraccioPK;
    }

    public TipusPasiExpres getTipusPasiExpres() {
        return tipusPasiExpres;
    }

    public void setTipusPasiExpres(TipusPasiExpres tipusPasiExpres) {
        this.tipusPasiExpres = tipusPasiExpres;
    }

    public Atraccio getAtraccio() {
        return atraccio;
    }

    public void setAtraccio(Atraccio atraccio) {
        this.atraccio = atraccio;
    }

    public TipusAcces getTipusAccesId() {
        return tipusAccesId;
    }

    public void setTipusAccesId(TipusAcces tipusAccesId) {
        this.tipusAccesId = tipusAccesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipusAccesAtraccioPK != null ? tipusAccesAtraccioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipusAccesAtraccio)) {
            return false;
        }
        TipusAccesAtraccio other = (TipusAccesAtraccio) object;
        if ((this.tipusAccesAtraccioPK == null && other.tipusAccesAtraccioPK != null) || (this.tipusAccesAtraccioPK != null && !this.tipusAccesAtraccioPK.equals(other.tipusAccesAtraccioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.TipusAccesAtraccio[ tipusAccesAtraccioPK=" + tipusAccesAtraccioPK + " ]";
    }
    
}
