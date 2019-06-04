/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
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

    public static EntityManager em;

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
        try{
            
        String cad = "select a from Atraccio a";
        Query q = em.createQuery(cad);
        List<Atraccio> ll = q.getResultList();
        }
        catch(Exception e){
            throw new PersistenceException("",e);
        }
    }
    @Override
    public void addIncidencia(Incidencia i){
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        Atraccio a=i.getAtraccioId();
        em.getTransaction().begin();
        em.persist(a);
        a.setIncidenciaId(i);
        a.setEstatActualId(i.getEstatOperatiuId());
        em.getTransaction().commit();
        em.refresh(a);
    }
    @Override
    public void updateIncidencia(Incidencia i){
        em.getTransaction().begin();
        Incidencia in=em.find(Incidencia.class,i.getId());
        in.setEstatOperatiuId(i.getEstatOperatiuId());
        em.persist(in);
        em.getTransaction().commit();
        em.flush();
    }
    @Override
    public List<Atraccio> getAtraccions(){
        String cad = "select a from Atraccio a";
        Query q = em.createQuery(cad);
        List<Atraccio> ll = q.getResultList();
        return ll;
    }
    @Override
    public void updateCua(int atraccioId){
        
    }

    @Override
    public void moreCua(Atraccio a) {
        em.getTransaction().begin();
        em.persist(a);
        int i=a.getClientsCua();
        i=i+1;
        a.setClientsCua(i);
        em.getTransaction().commit();
    }

    @Override
    public void minusCua(Atraccio a) {
        em.getTransaction().begin();
        em.persist(a);
        int i=a.getClientsCua();
        if(i>0)i=i-1;
        a.setClientsCua(i);
        em.getTransaction().commit();
    }
    @Override
    public void closeIncidencia(Incidencia i){
        em.getTransaction().begin();
        em.persist(i);
        i.setDataFi(new Date());
        i.setOberta(false);
        em.getTransaction().commit();
        em.getTransaction().begin();
        Atraccio a=i.getAtraccioId();
        a.setIncidenciaId(null);
        em.getTransaction().commit();
    }

    @Override
    public List<EstatOperatiu> getEstats() {
        String cad = "select a from EstatOperatiu a";
        Query q = em.createQuery(cad);
        List<EstatOperatiu> ll = q.getResultList();
        return ll;
    }

    @Override
    public void updateEstat(Incidencia i, EstatOperatiu e) {
        em.getTransaction().begin();
        em.persist(i);
        i.setEstatOperatiuId(e);
        em.getTransaction().commit();
    }

    @Override
    public void updateMissatge(Incidencia i, String msg) {
        em.getTransaction().begin();
        em.persist(i);
        i.setMisatgeEstat(msg);
        em.getTransaction().commit();
    }
    @Override
    public void updateAtraccio(Atraccio a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
}
