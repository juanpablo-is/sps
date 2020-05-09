package com.sps.sessionBean;

import com.sps.entity.Movilidad;
import com.sps.entity.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface MovilidadFacadeLocal {

    boolean create(Movilidad movilidad);

    boolean edit(Movilidad movilidad);

    boolean remove(Movilidad movilidad);

    Movilidad find(Object id);

    List<Movilidad> findAll();

    List<Movilidad> findRange(int[] range);

    int count();

    List<String> findByEmpresa();

    Movilidad findByCedula(String cedula);

    Movilidad findByPersona(Persona cedula);

}
