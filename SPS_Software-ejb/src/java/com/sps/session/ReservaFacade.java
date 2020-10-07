package com.sps.session;

import com.sps.entity.Cliente;
import com.sps.entity.Reserva;
import com.sps.entity.Usuario;
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
    public Number findBySelector(Cliente idCliente) {
        Query query = getEntityManager().createNamedQuery("Reserva.findBySelector");
        query.setParameter("idCliente", idCliente);
        Number select = (Number) query.getSingleResult();
        return select;
    }

    @Override
    public Reserva findLastCheck(Usuario usuario) {
        Query query = getEntityManager().createNamedQuery("Reserva.findLastCheck");
        query.setParameter("idUsuario", usuario);

        try {
            return (Reserva) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Reserva> findAllByUsuario(Usuario usuario) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByUsuario");
        query.setParameter("idUsuario", usuario);
        List<Reserva> reservas = query.getResultList();
        return reservas;
    }

    @Override
    public List<Reserva> findAllByCliente(Cliente cliente) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByCliente");
        query.setParameter("idCliente", cliente);
        List<Reserva> reservas = query.getResultList();
        return reservas;
    }

    @Override
    public List<Object[]> getPlazasByCliente(Cliente cliente) {
        Query query = getEntityManager().createNativeQuery("SELECT p.id, r.ID_USUARIO, p.TIPO_VEHICULO FROM Plaza p LEFT JOIN Reserva r ON p.id = r.ID_PLAZA AND r.id IN (SELECT r2.id FROM Reserva r2 WHERE r2.id IN (SELECT MAX(r3.id) FROM Reserva r3 GROUP BY r3.ID_PLAZA)  AND r2.fecha <= current_timestamp AND r2.estado = true) WHERE p.ID_CLIENTE = '" + cliente.getId() + "'");
        return query.getResultList();
    }

    @Override
    public List<Reserva> findAllByUsuarioInicio(Usuario usuario) {
        Query query = getEntityManager().createNamedQuery("Reserva.findByUsuarioInicio");
        query.setParameter("idUsuario", usuario);
        List<Reserva> reservas = query.setMaxResults(5).getResultList();
        return reservas;
    }
    
    @Override
    public List<Reserva> findAllTime(String id) {
        Query query = getEntityManager().createNamedQuery("Reserva.findAllTime");
        query.setParameter("idCliente", id);
        return query.getResultList();
    }

    @Override
    public void getReservasPorHora() {
        Query query = getEntityManager().createNativeQuery("SELECT SUBSTR(CHAR(TIME(r.entrada)),1,2), COUNT(SUBSTR(CHAR(TIME(r.entrada)),1,2)) FROM RESERVA r WHERE r.dia = current_date GROUP BY SUBSTR(CHAR(TIME(r.entrada)),1,2) ORDER BY 1");

        List<Object[]> list = query.getResultList();
        String name = "";
        int count = 0;

        /**
         * for (Object[] result : list) { name = (String) result[0]; count =
         * (int) result[1]; System.err.println(name + " : " + count); }
         *
         */
    }

    @Override
    public String graficoReserva(Usuario usuario) {
        Query query = getEntityManager().createNativeQuery("SELECT MONTH(dia), count(*) FROM RESERVA WHERE id_usuario = '" + usuario.getPlaca() + "' GROUP BY MONTH(dia) ");
        List<Object[]> list = query.getResultList();
        String grafico = "";
        if (list.size() > 0) {

            grafico = "https://quickchart.io/chart?c={type:'line',data:{labels:[";

            String[] mes = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
            for (int i = 0; i < 12; i++) {
                grafico += "'" + mes[i] + "',";
            }

            grafico = grafico.substring(0, grafico.length() - 1) + "], datasets:[{label:'Reservas Mes', data: [";

            int cont = 0;
            for (int i = 0; i < 12; i++) {
                if (list.size() > cont) {
                    int valor = (int) list.get(cont)[0];
                    if (valor == (i + 1)) {
                        grafico += ((int) list.get(cont)[1]) + ",";
                        cont++;
                    } else {
                        grafico += "0,";
                    }
                } else {
                    grafico += "0,";
                }
            }

            grafico = grafico.substring(0, grafico.length() - 1) + "], fill:false,borderColor:'blue'}]}}";
        }

        return grafico;
    }

}
