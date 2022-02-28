package com.mycompany.reto5.model;

import com.mycompany.reto5.model.Base;
import com.mycompany.reto5.model.Operadores;
import com.mycompany.reto5.model.Operandos;
import com.mycompany.reto5.model.Sesiones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-08-20T11:59:00")
@StaticMetamodel(Operaciones.class)
public class Operaciones_ { 

    public static volatile ListAttribute<Operaciones, Operandos> operandosList;
    public static volatile SingularAttribute<Operaciones, Operadores> operadores;
    public static volatile SingularAttribute<Operaciones, String> resultado;
    public static volatile SingularAttribute<Operaciones, Sesiones> sesion;
    public static volatile SingularAttribute<Operaciones, Integer> idOperacion;
    public static volatile SingularAttribute<Operaciones, Date> fechaOperacion;
    public static volatile SingularAttribute<Operaciones, Base> base;

}