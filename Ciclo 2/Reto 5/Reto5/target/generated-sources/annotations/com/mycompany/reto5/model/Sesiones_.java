package com.mycompany.reto5.model;

import com.mycompany.reto5.model.Operaciones;
import com.mycompany.reto5.model.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T11:59:00")
@StaticMetamodel(Sesiones.class)
public class Sesiones_ { 

    public static volatile SingularAttribute<Sesiones, Integer> idSesion;
    public static volatile SingularAttribute<Sesiones, Date> fechaInicioSesion;
    public static volatile SingularAttribute<Sesiones, Usuarios> usuario;
    public static volatile ListAttribute<Sesiones, Operaciones> operacionesList;
    public static volatile SingularAttribute<Sesiones, Date> fechaCierreSesion;

}