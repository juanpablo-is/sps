package com.sps.session;

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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "SPS_Software-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> findByCedula(Persona persona) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByCedula");
        query.setParameter("idPersona", persona);
        List<Usuario> list = query.getResultList();
        return list;
    }

    @Override
    public Usuario findByPlaca(String placa) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByPlaca");
        query.setParameter("placa", placa);
        Usuario usuario = (Usuario) query.getSingleResult();
        return usuario;
    }

}
