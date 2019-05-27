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
public class IncidenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "atraccio_id")
    private int atraccioId;

    public IncidenciaPK() {
    }

    public IncidenciaPK(int id, int atraccioId) {
        this.id = id;
        this.atraccioId = atraccioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash += (int) id;
        hash += (int) atraccioId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncidenciaPK)) {
            return false;
        }
        IncidenciaPK other = (IncidenciaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.atraccioId != other.atraccioId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.IncidenciaPK[ id=" + id + ", atraccioId=" + atraccioId + " ]";
    }
    
}
