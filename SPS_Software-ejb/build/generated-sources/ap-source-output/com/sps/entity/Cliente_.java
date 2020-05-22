package com.sps.entity;

import com.sps.entity.Persona;
import com.sps.entity.Plaza;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-21T14:59:30")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Integer> motos;
    public static volatile SingularAttribute<Cliente, BigDecimal> latitud;
    public static volatile SingularAttribute<Cliente, BigDecimal> longitud;
    public static volatile SingularAttribute<Cliente, Double> precio;
    public static volatile CollectionAttribute<Cliente, Plaza> plazaCollection;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile SingularAttribute<Cliente, String> inicio;
    public static volatile SingularAttribute<Cliente, String> fin;
    public static volatile SingularAttribute<Cliente, String> id;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, Integer> carros;
    public static volatile SingularAttribute<Cliente, Persona> idPersona;

}