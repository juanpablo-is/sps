package com.sps.entity;

import com.sps.entity.Persona;
import com.sps.entity.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-02T23:10:51")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> marca;
    public static volatile SingularAttribute<Usuario, String> idPropiedad;
    public static volatile SingularAttribute<Usuario, String> tipoVehiculo;
    public static volatile CollectionAttribute<Usuario, Reserva> reservaCollection;
    public static volatile SingularAttribute<Usuario, Persona> idPersona;
    public static volatile SingularAttribute<Usuario, String> placa;

}