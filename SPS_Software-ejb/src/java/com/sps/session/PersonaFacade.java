package com.sps.session;

import com.sps.entity.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Pablo
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    @Override
    public Persona findLogin(String email, String contrasenia) {
        Query query = em.createNamedQuery("Persona.findByLogin");
        query.setParameter("correo", email);
        query.setParameter("contrasenia", contrasenia);
        return (Persona) query.getSingleResult();
    }
}
