/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.io.IOException;

/**
 *
 * @author tonll
 */
public class PersistenceException extends Exception {

    PersistenceException(String string,Exception ex) {
        System.out.println(string+ex);
        ex.printStackTrace();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    PersistenceException(String string) {
        System.out.println(string);
    }
    
}
