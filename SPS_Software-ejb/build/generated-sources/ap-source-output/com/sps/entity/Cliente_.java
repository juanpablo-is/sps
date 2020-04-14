package com.sps.entity;

import com.sps.entity.Persona;
import com.sps.entity.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-07T11:34:26")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Double> precio;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> inicio;
    public static volatile SingularAttribute<Cliente, String> fin;
    public static volatile CollectionAttribute<Cliente, Reserva> reservaCollection;
    public static volatile SingularAttribute<Cliente, Integer> id;
    public static volatile SingularAttribute<Cliente, Integer> cupos;
    public static volatile SingularAttribute<Cliente, Persona> idPersona;

}