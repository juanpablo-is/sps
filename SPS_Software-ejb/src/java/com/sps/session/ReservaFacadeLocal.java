package com.sps.session;

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

    List<Reserva> findAllByUsuario(Usuario usuario);

    List<Reserva> findAllByUsuarioInicio(Usuario usuario);

    List<Reserva> findAllByCliente(Cliente parqueo);

    List<Reserva> findAllTime(String id);

    Reserva findLastCheck(Usuario usuario);

    List<Reserva> findRange(int[] range);

    int count();

    Number findBySelector(Cliente idCliente);

    void getReservasPorHora();

    String graficoReserva(Usuario persona);

    List<Object[]> getPlazasByCliente(Cliente cliente);

}
