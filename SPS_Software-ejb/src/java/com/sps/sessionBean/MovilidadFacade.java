package com.sps.sessionBean;

import com.sps.entity.Movilidad;
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
public class MovilidadFacade extends AbstractFacade<Movilidad> implements MovilidadFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovilidadFacade() {
        super(Movilidad.class);
    }

    @Override
    public List<String> findByEmpresa() {
        Query query = getEntityManager().createNamedQuery("Movilidad.findByEmpresaGroup");
        List<String> list = query.getResultList();
        return list;
    }
    
}
