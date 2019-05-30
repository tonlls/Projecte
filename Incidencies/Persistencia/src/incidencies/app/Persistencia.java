/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package incidencies.app;

import java.util.List;

/**
 *
 * @author tonll
 */
public interface Persistencia {
    public void addIncidencia(Incidencia i);
    public void updateIncidencia(Incidencia i);
    public List<Atraccio> getAtraccions();
    public void updateCua(int atraccioId);
    public void moreCua(Atraccio a);
    public void minusCua(Atraccio a);
    public void closeIncidencia(Incidencia i);
    public List<EstatOperatiu> getEstats();
    public void updateEstat(Incidencia i,EstatOperatiu e);
    public void updateMissatge(Incidencia i,String msg);
    public void updateAtraccio(Atraccio a);
}
