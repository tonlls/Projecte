/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.connectors;

import incidencies.app.PersistenceFactory;
import java.util.List;
import javax.persistence.EntityManager;
import incidencies.app.Persistence;
import javax.persistence.EntityManagerFactory;
import static javax.persistence.Persistence.createEntityManagerFactory;

/**
 *
 * @author tonll
 */
public class MySql_Connector implements Persistence{
    //private static
    private static EntityManager createEntityManager(){
        EntityManagerFactory factory = createEntityManagerFactory("Model");
        return factory.createEntityManager();
    }
    @Override
    public void addIncidencia(int atraccioId) {
        
        List<incidencia> resultList = manager.createQuery("Select a From Employee a", incidencia.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (incidencia next : resultList) {
            System.out.println("next employee: " + next);
        }
        // insert into incidencia values()
    }

    @Override
    public void updateIncidencia(int atraccioId) {
        //update from incidencia set c=c where
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeIncidencia(int atraccioId) {
        //update
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getIncidencies(int atraccioId) {
        //select * from incidencia where 
        List<incidencia> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
        System.out.println("num of employess:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getAtraccions() {
        //select * from atraccio
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCua(int atraccioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
