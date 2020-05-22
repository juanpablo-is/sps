package com.sps.session;

import com.sps.entity.Persona;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
        try {
            Query query = em.createNamedQuery("Persona.findByLogin");
            query.setParameter("correo", email);
            query.setParameter("contrasenia", contrasenia);
            return (Persona) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
