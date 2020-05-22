package com.sps.session;

import com.sps.entity.Cliente;
import com.sps.entity.Plaza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface PlazaFacadeLocal {

    boolean create(Plaza plaza);

    boolean edit(Plaza plaza);

    boolean remove(Plaza plaza);

    Plaza find(Object id);

    List<Plaza> findAll();

    List<Plaza> findRange(int[] range);

    int count();

    List<Object[]> findNoExistsGroup(boolean tipo, boolean cubierto);

    Plaza findPlazaNoExists(boolean tipo, boolean cubierto, Cliente idCliente);

}
