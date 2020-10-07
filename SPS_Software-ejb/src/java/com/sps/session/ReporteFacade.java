package com.sps.session;

import com.sps.entity.*;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Pablo
 */
@Stateless
public class ReporteFacade extends AbstractFacade<Reporte> implements ReporteFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReporteFacade() {
        super(Reporte.class);
    }

    @Override
    public List<Reporte> findLast() {
        Query query = getEntityManager().createNamedQuery("Reporte.findLast");
        return query.setMaxResults(10).getResultList();
    }

    @Override
    public List<Reporte> findByCliente(Cliente cliente, int cantidad) {
        Query query = getEntityManager().createNamedQuery("Reporte.findByCliente");
        query.setParameter("idCliente", cliente);
        return query.setMaxResults(cantidad).getResultList();
    }
    
}
