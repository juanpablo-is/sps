package com.sps.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "EclipseLink-2.5.2.v20140319-rNA", date = "2020-05-11T14:51:55")
@StaticMetamodel(Movilidad.class)
public class Movilidad_ {

    private Movilidad_() {
    }

    public static volatile SingularAttribute<Movilidad, String> id;
    public static volatile SingularAttribute<Movilidad, String> empresa;
    public static volatile SingularAttribute<Movilidad, Persona> idPersona;

}
