package com.sps.sessionBean;

import com.sps.entity.Cliente;
import com.sps.entity.Reserva;
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
public class ReservaFacade extends AbstractFacade<Reserva> implements ReservaFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }

    @Override
    public Number findSelector(Cliente idCliente) {
        Query query = getEntityManager().createNamedQuery("Reserva.findBySelector");
        query.setParameter("idCliente", idCliente);
        Number select = (Number) query.getSingleResult();
        return select;
    }

    @Override
    public Reserva findByUsuario(Usuario persona) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByUsuario");
        query.setParameter("idUsuario", persona);

        if (query.getResultList().size() < 1) {
            return null;
        }
        Reserva reserva = (Reserva) query.getResultList().get(0);
        return reserva;
    }

    @Override
    public List<Reserva> findAllByUsuario(Usuario persona) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByUsuario");
        query.setParameter("idUsuario", persona);
        List<Reserva> reservas = query.getResultList();
        return reservas;
    }

}
