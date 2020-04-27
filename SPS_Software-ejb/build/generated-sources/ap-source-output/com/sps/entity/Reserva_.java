package com.sps.entity;

import com.sps.entity.Cliente;
import com.sps.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-26T19:24:59")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Double> precio;
    public static volatile SingularAttribute<Reserva, Cliente> idCliente;
    public static volatile SingularAttribute<Reserva, String> entrada;
    public static volatile SingularAttribute<Reserva, Boolean> ocupado;
    public static volatile SingularAttribute<Reserva, Usuario> idUsuario;
    public static volatile SingularAttribute<Reserva, Integer> id;
    public static volatile SingularAttribute<Reserva, String> dia;
    public static volatile SingularAttribute<Reserva, String> salida;

}