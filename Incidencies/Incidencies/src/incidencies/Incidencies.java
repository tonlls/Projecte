/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies;

import incidencies.app.PersistenceException;
import incidencies.app.PersistenceFactory;
import incidencies.app.Persistencia;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Incidencies {
    public static void main(String[] args) throws PersistenceException {
        Persistencia p=PersistenceFactory.getInstance("incidencies.app.PersistenciaMySql",null);
    }
    
}
