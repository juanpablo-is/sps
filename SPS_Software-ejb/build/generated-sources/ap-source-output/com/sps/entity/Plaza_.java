package com.sps.entity;

import com.sps.entity.Cliente;
import com.sps.entity.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-29T15:40:23")
@StaticMetamodel(Plaza.class)
public class Plaza_ { 

    public static volatile SingularAttribute<Plaza, Cliente> idCliente;
    public static volatile SingularAttribute<Plaza, String> id;
    public static volatile SingularAttribute<Plaza, Boolean> tipoVehiculo;
    public static volatile CollectionAttribute<Plaza, Reserva> reservaCollection;
    public static volatile SingularAttribute<Plaza, Boolean> cubierto;

}