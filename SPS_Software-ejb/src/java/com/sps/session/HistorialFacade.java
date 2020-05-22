package com.sps.session;

import com.sps.entity.Cliente;
import com.sps.entity.Historial;
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
public class HistorialFacade extends AbstractFacade<Historial> implements HistorialFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialFacade() {
        super(Historial.class);
    }

    @Override
    public List<Historial> findByCliente(Cliente cliente) {
        Query query = getEntityManager().createNamedQuery("Historial.findByCliente");
        query.setParameter("idCliente", cliente);
        List<Historial> historial = query.getResultList();
        return historial;
    }

    @Override
    public List<Historial> findByUsuario(Usuario usuario) {
        Query query = getEntityManager().createNamedQuery("Historial.findByUsuario");
        query.setParameter("idUsuario", usuario);
        List<Historial> historial = query.getResultList();
        return historial;
    }
    
}
