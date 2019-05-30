/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "atraccio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atraccio.findAll", query = "SELECT a FROM Atraccio a"),
    @NamedQuery(name = "Atraccio.findById", query = "SELECT a FROM Atraccio a WHERE a.id = :id"),
    @NamedQuery(name = "Atraccio.findByCapacitatMaximaRonda", query = "SELECT a FROM Atraccio a WHERE a.capacitatMaximaRonda = :capacitatMaximaRonda"),
    @NamedQuery(name = "Atraccio.findByDescripcioHTML", query = "SELECT a FROM Atraccio a WHERE a.descripcioHTML = :descripcioHTML"),
    @NamedQuery(name = "Atraccio.findByNom", query = "SELECT a FROM Atraccio a WHERE a.nom = :nom"),
    @NamedQuery(name = "Atraccio.findByTempsPerRonda", query = "SELECT a FROM Atraccio a WHERE a.tempsPerRonda = :tempsPerRonda"),
    @NamedQuery(name = "Atraccio.findByUrlFoto", query = "SELECT a FROM Atraccio a WHERE a.urlFoto = :urlFoto"),
    @NamedQuery(name = "Atraccio.findByClientsCua", query = "SELECT a FROM Atraccio a WHERE a.clientsCua = :clientsCua"),
    @NamedQuery(name = "Atraccio.findByAl\u00e7adaMinimaAcompanyat", query = "SELECT a FROM Atraccio a WHERE a.al\u00e7adaMinimaAcompanyat = :al\u00e7adaMinimaAcompanyat"),
    @NamedQuery(name = "Atraccio.findByAl\u00e7adaMinima", query = "SELECT a FROM Atraccio a WHERE a.al\u00e7adaMinima = :al\u00e7adaMinima")})
public class Atraccio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "capacitat_maxima_ronda")
    private Integer capacitatMaximaRonda;
    @Column(name = "descripcioHTML")
    private String descripcioHTML;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Column(name = "temps_per_ronda")
    private Integer tempsPerRonda;
    @Column(name = "url_foto")
    private String urlFoto;
    @Column(name = "clients_cua")
    private Integer clientsCua;
    @Column(name = "al\u00e7ada_minima_acompanyat")
    private Integer alçadaMinimaAcompanyat;
    @Column(name = "al\u00e7ada_minima")
    private Integer alçadaMinima;
    @OneToMany(mappedBy = "atraccioId")
    private List<Incidencia> incidenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atraccio")
    private List<TipusAccesAtraccio> tipusAccesAtraccioList;
    @JoinColumns({
        @JoinColumn(name = "zona_id", referencedColumnName = "id"),
        @JoinColumn(name = "parc_id", referencedColumnName = "parc_id")})
    @ManyToOne
    private Zona zona;
    @JoinColumn(name = "estat_actual_id", referencedColumnName = "id")
    @ManyToOne
    private EstatOperatiu estatActualId;
    @JoinColumn(name = "incidencia_id", referencedColumnName = "id")
    @ManyToOne
    private Incidencia incidenciaId;
    @OneToMany(mappedBy = "atraccioId")
    private List<InfoUtilitzacio> infoUtilitzacioList;

    public Atraccio() {
    }

    public Atraccio(Integer id) {
        this.id = id;
    }

    public Atraccio(Integer id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacitatMaximaRonda() {
        return capacitatMaximaRonda;
    }

    public void setCapacitatMaximaRonda(Integer capacitatMaximaRonda) {
        this.capacitatMaximaRonda = capacitatMaximaRonda;
    }

    public String getDescripcioHTML() {
        return descripcioHTML;
    }

    public void setDescripcioHTML(String descripcioHTML) {
        this.descripcioHTML = descripcioHTML;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getTempsPerRonda() {
        return tempsPerRonda;
    }

    public void setTempsPerRonda(Integer tempsPerRonda) {
        this.tempsPerRonda = tempsPerRonda;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public Integer getClientsCua() {
        return clientsCua;
    }

    public void setClientsCua(Integer clientsCua) {
        this.clientsCua = clientsCua;
    }

    public Integer getAlçadaMinimaAcompanyat() {
        return alçadaMinimaAcompanyat;
    }

    public void setAlçadaMinimaAcompanyat(Integer alçadaMinimaAcompanyat) {
        this.alçadaMinimaAcompanyat = alçadaMinimaAcompanyat;
    }

    public Integer getAlçadaMinima() {
        return alçadaMinima;
    }

    public void setAlçadaMinima(Integer alçadaMinima) {
        this.alçadaMinima = alçadaMinima;
    }

    @XmlTransient
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
    }

    @XmlTransient
    public List<TipusAccesAtraccio> getTipusAccesAtraccioList() {
        return tipusAccesAtraccioList;
    }

    public void setTipusAccesAtraccioList(List<TipusAccesAtraccio> tipusAccesAtraccioList) {
        this.tipusAccesAtraccioList = tipusAccesAtraccioList;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public EstatOperatiu getEstatActualId() {
        return estatActualId;
    }

    public void setEstatActualId(EstatOperatiu estatActualId) {
        this.estatActualId = estatActualId;
    }

    public Incidencia getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(Incidencia incidenciaId) {
        this.incidenciaId = incidenciaId;
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
        if (!(object instanceof Atraccio)) {
            return false;
        }
        Atraccio other = (Atraccio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "incidencies.app.Atraccio[ id=" + id + " ]";
    }
    
}
