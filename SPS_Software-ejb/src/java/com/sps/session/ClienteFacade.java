package com.sps.session;

import com.sps.entity.Cliente;
import com.sps.entity.Persona;
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
    public List<Cliente> findByCedula(Persona persona) {
        Query query = getEntityManager().createNamedQuery("Cliente.findByCedula");
        query.setParameter("idPersona", persona);
        List<Cliente> list = query.getResultList();
        return list;
    }

    @Override
    public Cliente findByID(Integer id) {
        Query query = getEntityManager().createNamedQuery("Cliente.findById");
        query.setParameter("id", id);
        return (Cliente) query.getSingleResult();
    }

    @Override
    public Object getDinero(String id) {
        Query query = getEntityManager().createNativeQuery("SELECT COUNT(h.id), SUM(h.precio) FROM Historial h WHERE h.id_reserva IN (SELECT r.id FROM Reserva r WHERE r.id_plaza IN (SELECT p.id FROM Plaza p WHERE p.id_cliente = ?1)) AND DATE(h.salida) = CURRENT_DATE");
        query.setParameter("1", id);
        return query.getResultList().get(0);
    }

    @Override
    public int getReservas(String id) {
        Query query = getEntityManager().createNativeQuery("SELECT COUNT(r.id) FROM Reserva r WHERE r.id_plaza IN (SELECT p.id FROM Plaza p WHERE p.id_cliente = ?1) AND DATE(r.fecha) = CURRENT_DATE");
        query.setParameter("1", id);

        return (int) query.getSingleResult();
    }

    @Override
    public String graficaHistorial(String id) {
        Query query = getEntityManager().createNativeQuery("SELECT COUNT(h.id), HOUR(h.salida) FROM Historial h WHERE h.id_reserva IN (SELECT r.id FROM Reserva r WHERE r.id_plaza IN (SELECT p.id FROM Plaza p WHERE p.id_cliente = ?1)) AND DATE(h.salida) = CURRENT_DATE GROUP BY HOUR(salida) ORDER BY 2");
        query.setParameter("1", id);

        List<Object[]> historial = query.getResultList();
        String grafico = "";

        grafico = "https://quickchart.io/chart?c={type:'line',data:{labels:[";

        String[] tiempo = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        for (int i = 0; i < tiempo.length; i++) {
            grafico += "'" + tiempo[i] + "',";
        }

        grafico = grafico.substring(0, grafico.length() - 1) + "], datasets:[{label:'Reservas Mes', data: [";
        int cont = 0;

        for (int i = 0; i < tiempo.length; i++) {
            if (cont < historial.size()) {
                if ((int) historial.get(cont)[1] == i) {
                    grafico += ((int) historial.get(cont++)[0]) + ",";
                } else {
                    grafico += "0,";
                }
            } else {
                grafico += "0,";
            }
        }

        grafico = grafico.substring(0, grafico.length() - 1) + "], fill:false,borderColor:'blue'}]}}";
        return grafico;
    }
}
