package com.sps.sessionBean;

import com.sps.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface ReservaFacadeLocal {

    boolean create(Reserva reserva);

    boolean edit(Reserva reserva);

    boolean remove(Reserva reserva);

    Reserva find(Object id);

    List<Reserva> findAll();
    
    List<Reserva> findAllByUsuario(Usuario persona);

    List<Reserva> findRange(int[] range);

    int count();

    Number findSelector(Cliente idCliente);
    
    Reserva findByUsuario(Usuario persona);
}
