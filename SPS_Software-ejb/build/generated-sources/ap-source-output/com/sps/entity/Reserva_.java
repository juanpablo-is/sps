package com.sps.entity;

import com.sps.entity.Historial;
import com.sps.entity.Plaza;
import com.sps.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-29T15:40:23")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Date> fecha;
    public static volatile SingularAttribute<Reserva, Boolean> estado;
    public static volatile CollectionAttribute<Reserva, Historial> historialCollection;
    public static volatile SingularAttribute<Reserva, Usuario> idUsuario;
    public static volatile SingularAttribute<Reserva, Integer> id;
    public static volatile SingularAttribute<Reserva, Plaza> idPlaza;

}