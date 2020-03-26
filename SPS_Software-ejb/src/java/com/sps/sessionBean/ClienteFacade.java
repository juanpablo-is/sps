package com.sps.sessionBean;

import com.sps.entity.Cliente;
import com.sps.entity.Persona;
import com.sps.entity.Usuario;
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
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> findByCedula(Persona cedula) {
        Query query = getEntityManager().createNamedQuery("Cliente.findByCedula");
        query.setParameter("idPersona", cedula);
        List<Cliente> list = query.getResultList();
        return list;
    }

}
