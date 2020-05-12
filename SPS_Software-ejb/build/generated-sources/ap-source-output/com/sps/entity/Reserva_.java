package com.sps.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "EclipseLink-2.5.2.v20140319-rNA", date = "2020-05-11T14:51:55")
@StaticMetamodel(Reserva.class)
public class Reserva_ {

    private Reserva_() {
    }

    public static volatile SingularAttribute<Reserva, Double> precio;
    public static volatile SingularAttribute<Reserva, Cliente> idCliente;
    public static volatile SingularAttribute<Reserva, String> entrada;
    public static volatile SingularAttribute<Reserva, Boolean> ocupado;
    public static volatile SingularAttribute<Reserva, Usuario> idUsuario;
    public static volatile SingularAttribute<Reserva, Integer> id;
    public static volatile SingularAttribute<Reserva, String> dia;
    public static volatile SingularAttribute<Reserva, String> salida;

}
