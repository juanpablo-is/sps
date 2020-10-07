package com.sps.entity;

import com.sps.entity.Reserva;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-29T15:40:23")
@StaticMetamodel(Historial.class)
public class Historial_ { 

    public static volatile SingularAttribute<Historial, Double> precio;
    public static volatile SingularAttribute<Historial, Integer> id;
    public static volatile SingularAttribute<Historial, Date> salida;
    public static volatile SingularAttribute<Historial, Reserva> idReserva;

}