package com.sps.entity;

import com.sps.entity.Cliente;
import com.sps.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-29T15:40:23")
@StaticMetamodel(Reporte.class)
public class Reporte_ { 

    public static volatile SingularAttribute<Reporte, String> texto;
    public static volatile SingularAttribute<Reporte, Date> fecha;
    public static volatile SingularAttribute<Reporte, Boolean> estado;
    public static volatile SingularAttribute<Reporte, Cliente> idCliente;
    public static volatile SingularAttribute<Reporte, Usuario> idUsuario;
    public static volatile SingularAttribute<Reporte, String> titulo;
    public static volatile SingularAttribute<Reporte, Integer> id;

}