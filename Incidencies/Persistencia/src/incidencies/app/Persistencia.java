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
    public void closeIncidencia(Incidencia i);
    public List<Incidencia> getIncidencies(int atraccioId);
    public List<Atraccio> getAtraccions();
    public void updateCua(int atraccioId);
}
