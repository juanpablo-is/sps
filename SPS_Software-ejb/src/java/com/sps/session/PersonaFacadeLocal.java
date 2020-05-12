package com.sps.session;

import com.sps.entity.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface PersonaFacadeLocal {

    boolean create(Persona persona);

    boolean edit(Persona persona);

    boolean remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();
    
    Persona findLogin(String email, String password);

}
