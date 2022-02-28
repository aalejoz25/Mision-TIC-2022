package com.mycompany.reto5.model;

import com.mycompany.reto5.model.Operaciones;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T11:59:00")
@StaticMetamodel(Operadores.class)
public class Operadores_ { 

    public static volatile SingularAttribute<Operadores, Integer> idOperador;
    public static volatile SingularAttribute<Operadores, String> descripcion;
    public static volatile ListAttribute<Operadores, Operaciones> operacionesList;

}