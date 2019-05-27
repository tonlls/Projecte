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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "entrada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entrada.findAll", query = "SELECT e FROM Entrada e"),
    @NamedQuery(name = "Entrada.findById", query = "SELECT e FROM Entrada e WHERE e.id = :id"),
    @NamedQuery(name = "Entrada.findByData", query = "SELECT e FROM Entrada e WHERE e.data = :data"),
    @NamedQuery(name = "Entrada.findByDiesValidesa", query = "SELECT e FROM Entrada e WHERE e.diesValidesa = :diesValidesa"),
    @NamedQuery(name = "Entrada.findByPreu", query = "SELECT e FROM Entrada e WHERE e.preu = :preu")})
public class Entrada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "dies_validesa")
    private Integer diesValidesa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preu")
    private Float preu;
    @JoinTable(name = "entrada_parc", joinColumns = {
        @JoinColumn(name = "entrada_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "parc_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Parc> parcList;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne
    private CategoriaEntrada categoriaId;
    @JoinColumn(name = "preu_id", referencedColumnName = "id")
    @ManyToOne
    private Preu preuId;

    public Entrada() {
    }

    public Entrada(Integer id) {
        this.id = id;
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

    public Integer getDiesValidesa() {
        return diesValidesa;
    }

    public void setDiesValidesa(Integer diesValidesa) {
        this.diesValidesa = diesValidesa;
    }

    public Float getPreu() {
        return preu;
    }

    public void setPreu(Float preu) {
        this.preu = preu;
    }

    @XmlTransient
    public List<Parc> getParcList() {
        return parcList;
    }

    public void setParcList(List<Parc> parcList) {
        this.parcList = parcList;
    }

    public CategoriaEntrada getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaEntrada categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Preu getPreuId() {
        return preuId;
    }

    public void setPreuId(Preu preuId) {
        this.preuId = preuId;
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
        if (!(object instanceof Entrada)) {
            return false;
        }
        Entrada other = (Entrada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Entrada[ id=" + id + " ]";
    }
    
}
