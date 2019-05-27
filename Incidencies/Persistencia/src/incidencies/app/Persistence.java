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
public interface Persistence {
    public void addIncidencia(int atraccioId);
    public void updateIncidencia(int atraccioId);
    public void closeIncidencia(int atraccioId);
    public List<Incidencia> getIncidencies(int atraccioId);
    public List<Atraccio> getAtraccions();
    public void updateCua(int atraccioId);
}
