package com.sps.session;

import com.sps.entity.Cliente;
import com.sps.entity.Plaza;
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
public class PlazaFacade extends AbstractFacade<Plaza> implements PlazaFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlazaFacade() {
        super(Plaza.class);
    }

    @Override
    public List<Object[]> findNoExistsGroup(boolean tipo, boolean cubierto) {
        Query query = getEntityManager().createNativeQuery("SELECT p.ID_CLIENTE FROM Plaza p WHERE p.CUBIERTO = " + cubierto + " AND p.TIPO_VEHICULO = " + tipo + " AND NOT EXISTS (SELECT r.* FROM Reserva r WHERE r.ID_PLAZA = p.ID and r.ESTADO = true) GROUP BY p.ID_CLIENTE");
        return query.getResultList();
    }

    @Override
    public Plaza findPlazaNoExists(boolean tipo, boolean cubierto, Cliente idCliente) {
        Query query = getEntityManager().createNativeQuery("SELECT p.ID FROM Plaza p WHERE p.CUBIERTO = " + cubierto + " AND p.TIPO_VEHICULO = " + tipo + " AND p.ID_CLIENTE = '" + idCliente.getId() + "' AND NOT EXISTS (SELECT r.* FROM Reserva r WHERE r.ID_PLAZA = p.ID and r.ESTADO = true)");
//        query.setParameter("cubierto", Boolean.parseBoolean(cubierto ? "1" : "0"));
//        query.setParameter("tipoVehiculo", Boolean.parseBoolean(cubierto ? "true" : "false"));
//        query.setParameter("idCliente", idCliente);
        List<Object[]> plazas = query.getResultList();
        return find(plazas.get((int) (Math.random() * (plazas.size() - 1))));

    }
}
