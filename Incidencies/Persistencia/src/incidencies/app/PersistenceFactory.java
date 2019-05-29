/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tonll
 */
public abstract class PersistenceFactory {
    public static Persistencia getInstance(String nomClasseComponent,String parametreNomFitxerProp) throws PersistenceException {
       Persistencia obj = null;
       try {
           Class c = Class.forName(nomClasseComponent);
           if (parametreNomFitxerProp==null || parametreNomFitxerProp.length()==0) {
               obj = (Persistencia) c.newInstance();
           } else {
               Constructor co = c.getConstructor(String.class);
               obj = (Persistencia) co.newInstance(parametreNomFitxerProp);
           }
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| NoSuchMethodException | SecurityException | IllegalArgumentException| InvocationTargetException ex) {
           throw new PersistenceException("Error " + nomClasseComponent, ex);
       }
       return obj;
   }
}
