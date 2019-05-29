/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tonll
 */
public class PersistenciaMySql implements Persistencia{

    private EntityManager em;

    public PersistenciaMySql() throws PersistenceException {
        this("persistence.properties");
    }
    public PersistenciaMySql(String file) throws PersistenceException{
        String nomUnitatPersistencia = null;
        EntityManagerFactory emf = null;
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(file));
        } catch (IOException ex) {
            throw new PersistenceException("Error en llegir de fitxer de propietats", ex);
        }
        nomUnitatPersistencia = p.getProperty("unitatPersistencia");
        if (nomUnitatPersistencia == null || nomUnitatPersistencia.length() == 0) {
            throw new PersistenceException("Fitxer de propietats " + file + " no inclou propietat \"unitatPersistencia\"");
        }        
        try {
            emf = Persistence.createEntityManagerFactory(nomUnitatPersistencia);
        } catch (javax.persistence.PersistenceException ex) {
            throw new PersistenceException("Problemes en crear l'EntityManagerFactory", ex);
        }
        try {
            em = emf.createEntityManager();
        } catch (javax.persistence.PersistenceException ex) {
            throw new PersistenceException("Problemes en crear l'EntityManager", ex);
        }
    }
    @Override
    public void addIncidencia(Incidencia i){
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
    }
    @Override
    public void updateIncidencia(Incidencia i){
        em.getTransaction().begin();
        Incidencia in=em.find(Incidencia.class,i.id);
        
       //em.persist(this);
    }
    @Override
    public void closeIncidencia(Incidencia i){
        em.getTransaction().begin();
        em.persist(i);
        String sql="SELECT MAX(id) FROM INCIDENCIS WHERE atraccio_id="+i.getAtraccio();
        String sql2="INSERT INTO ";
    }
    @Override
    public List<Incidencia> getIncidencies(int atraccioId){
        String cad = "select i from incidencia i where i.atraccio_id = :atr";
        Query q = em.createQuery(cad);
        q.setParameter("atr",atraccioId);
        List<Incidencia> ll = q.getResultList();
        return ll;
    }
    @Override
    public List<Atraccio> getAtraccions(){
        String cad = "select a from atraccio a";
        Query q = em.createQuery(cad);
        List<Atraccio> ll = q.getResultList();
        return ll;
    }
    @Override
    public void updateCua(int atraccioId){
        
    }
}
