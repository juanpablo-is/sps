package com.sps.sessionBean;

import com.sps.entity.Movilidad;
import com.sps.entity.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    @Override
    public Movilidad findByCedula(String cedula) {
        Query query = getEntityManager().createNamedQuery("Movilidad.findById");
        query.setParameter("id", cedula);
        return (Movilidad) query.getSingleResult();
    }

    @Override
    public Movilidad findByPersona(Persona persona) {
        try {
            Query query = getEntityManager().createNamedQuery("Movilidad.findByPersona");
            query.setParameter("idPersona", persona);
            return (Movilidad) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }
}
