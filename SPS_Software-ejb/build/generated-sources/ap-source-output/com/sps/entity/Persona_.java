package com.sps.entity;

import com.sps.entity.Cliente;
import com.sps.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-26T19:24:59")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile SingularAttribute<Persona, String> correo;
    public static volatile SingularAttribute<Persona, String> contrasenia;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile CollectionAttribute<Persona, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile CollectionAttribute<Persona, Cliente> clienteCollection;

}