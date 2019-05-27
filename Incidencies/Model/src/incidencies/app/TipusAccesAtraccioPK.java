/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author tonll
 */
@Embeddable
public class TipusAccesAtraccioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "tipus_pasi_id")
    private int tipusPasiId;
    @Basic(optional = false)
    @Column(name = "atraccio_id")
    private int atraccioId;

    public TipusAccesAtraccioPK() {
    }

    public TipusAccesAtraccioPK(int tipusPasiId, int atraccioId) {
        this.tipusPasiId = tipusPasiId;
        this.atraccioId = atraccioId;
    }

    public int getTipusPasiId() {
        return tipusPasiId;
    }

    public void setTipusPasiId(int tipusPasiId) {
        this.tipusPasiId = tipusPasiId;
    }

    public int getAtraccioId() {
        return atraccioId;
    }

    public void setAtraccioId(int atraccioId) {
        this.atraccioId = atraccioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tipusPasiId;
        hash += (int) atraccioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipusAccesAtraccioPK)) {
            return false;
        }
        TipusAccesAtraccioPK other = (TipusAccesAtraccioPK) object;
        if (this.tipusPasiId != other.tipusPasiId) {
            return false;
        }
        if (this.atraccioId != other.atraccioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.TipusAccesAtraccioPK[ tipusPasiId=" + tipusPasiId + ", atraccioId=" + atraccioId + " ]";
    }
    
}
