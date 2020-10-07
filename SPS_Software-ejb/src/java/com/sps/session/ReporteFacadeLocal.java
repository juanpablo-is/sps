package com.sps.session;

import com.sps.entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface ReporteFacadeLocal {

    boolean create(Reporte reporte);

    boolean edit(Reporte reporte);

    boolean remove(Reporte reporte);

    Reporte find(Object id);

    List<Reporte> findAll();

    List<Reporte> findRange(int[] range);

    int count();
    
    List<Reporte> findLast();
    
    List<Reporte> findByCliente(Cliente cliente, int cantidad);
}
