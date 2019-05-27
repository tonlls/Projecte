/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author tonll
 */
public abstract class PersistenceFactory {
    public static Persistence getInstance(String nomClasseComponent,String parametreNomFitxerProp) throws PersistenceException {
       Persistence obj = null;
       try {
           Class c = Class.forName(nomClasseComponent);
           if (parametreNomFitxerProp==null || parametreNomFitxerProp.length()==0) {
               obj = (Persistence) c.newInstance();
           } else {
               Constructor co = c.getConstructor(String.class);
               obj = (Persistence) co.newInstance(parametreNomFitxerProp);
           }
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException| NoSuchMethodException | SecurityException | IllegalArgumentException| InvocationTargetException ex) {
           throw new PersistenceException("Error " + nomClasseComponent, ex);
       }
       return obj;
   }
}
