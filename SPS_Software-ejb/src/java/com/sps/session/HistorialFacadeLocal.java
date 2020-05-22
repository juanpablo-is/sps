package com.sps.session;

import com.sps.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface HistorialFacadeLocal {

    boolean create(Historial historial);

    boolean edit(Historial historial);

    boolean remove(Historial historial);

    Historial find(Object id);

    List<Historial> findAll();

    List<Historial> findRange(int[] range);

    int count();

    List<Historial> findByCliente(Cliente cliente);

    List<Historial> findByUsuario(Usuario usuario);

}
