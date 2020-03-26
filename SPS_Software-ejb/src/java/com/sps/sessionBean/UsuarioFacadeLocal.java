/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.sessionBean;

import com.sps.entity.Persona;
import com.sps.entity.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Pablo
 */
@Local
public interface UsuarioFacadeLocal {

    boolean create(Usuario usuario);

    boolean edit(Usuario usuario);

    boolean remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    List<Usuario> findByCedula(Persona cedula);

    int count();

}
