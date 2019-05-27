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
public class ZonaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "parc_id")
    private int parcId;

    public ZonaPK() {
    }

    public ZonaPK(int id, int parcId) {
        this.id = id;
        this.parcId = parcId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcId() {
        return parcId;
    }

    public void setParcId(int parcId) {
        this.parcId = parcId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) parcId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZonaPK)) {
            return false;
        }
        ZonaPK other = (ZonaPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.parcId != other.parcId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.ZonaPK[ id=" + id + ", parcId=" + parcId + " ]";
    }
    
}
