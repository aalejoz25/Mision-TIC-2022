/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "operaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operaciones.findAll", query = "SELECT o FROM Operaciones o"),
    @NamedQuery(name = "Operaciones.findByIdOperacion", query = "SELECT o FROM Operaciones o WHERE o.idOperacion = :idOperacion"),
    @NamedQuery(name = "Operaciones.findByResultado", query = "SELECT o FROM Operaciones o WHERE o.resultado = :resultado"),
    @NamedQuery(name = "Operaciones.findByFechaOperacion", query = "SELECT o FROM Operaciones o WHERE o.fechaOperacion = :fechaOperacion")})
public class Operaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operacion")
    private Integer idOperacion;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;
    @Basic(optional = false)
    @Column(name = "fecha_operacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaOperacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperacion")
    private List<Operandos> operandosList;
    @JoinColumns({
        @JoinColumn(name = "base", referencedColumnName = "id_base"),
        @JoinColumn(name = "base", referencedColumnName = "id_base")})
    @ManyToOne(optional = false)
    private Base base;
    @JoinColumns({
        @JoinColumn(name = "operador", referencedColumnName = "id_operador"),
        @JoinColumn(name = "operador", referencedColumnName = "id_operador")})
    @ManyToOne(optional = false)
    private Operadores operadores;
    @JoinColumn(name = "sesion", referencedColumnName = "id_sesion")
    @ManyToOne(optional = false)
    private Sesiones sesion;

    public Operaciones() {
    }

    public Operaciones(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Operaciones(Integer idOperacion, String resultado, Date fechaOperacion) {
        this.idOperacion = idOperacion;
        this.resultado = resultado;
        this.fechaOperacion = fechaOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    @XmlTransient
    public List<Operandos> getOperandosList() {
        return operandosList;
    }

    public void setOperandosList(List<Operandos> operandosList) {
        this.operandosList = operandosList;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Operadores getOperadores() {
        return operadores;
    }

    public void setOperadores(Operadores operadores) {
        this.operadores = operadores;
    }

    public Sesiones getSesion() {
        return sesion;
    }

    public void setSesion(Sesiones sesion) {
        this.sesion = sesion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacion != null ? idOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operaciones)) {
            return false;
        }
        Operaciones other = (Operaciones) object;
        if ((this.idOperacion == null && other.idOperacion != null) || (this.idOperacion != null && !this.idOperacion.equals(other.idOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reto5.model.Operaciones[ idOperacion=" + idOperacion + " ]";
    }
    
}
