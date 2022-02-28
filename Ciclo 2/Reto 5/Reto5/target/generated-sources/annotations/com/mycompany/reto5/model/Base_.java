package com.mycompany.reto5.model;

import com.mycompany.reto5.model.Operaciones;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T11:59:00")
@StaticMetamodel(Base.class)
public class Base_ { 

    public static volatile SingularAttribute<Base, String> descripcion;
    public static volatile SingularAttribute<Base, Integer> idBase;
    public static volatile ListAttribute<Base, Operaciones> operacionesList;

}