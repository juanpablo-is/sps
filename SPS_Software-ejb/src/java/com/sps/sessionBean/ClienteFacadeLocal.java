package com.sps.sessionBean;

import com.sps.entity.Cliente;
import com.sps.entity.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface ClienteFacadeLocal {

    boolean create(Cliente cliente);

    boolean edit(Cliente cliente);

    boolean remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();

    List<Cliente> findByCedula(Persona cedula);

}
