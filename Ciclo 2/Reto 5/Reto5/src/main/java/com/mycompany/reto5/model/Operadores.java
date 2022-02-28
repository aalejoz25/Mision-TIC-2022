/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "operadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operadores.findAll", query = "SELECT o FROM Operadores o"),
    @NamedQuery(name = "Operadores.findByIdOperador", query = "SELECT o FROM Operadores o WHERE o.idOperador = :idOperador"),
    @NamedQuery(name = "Operadores.findByDescripcion", query = "SELECT o FROM Operadores o WHERE o.descripcion = :descripcion")})
public class Operadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operador")
    private Integer idOperador;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operadores")
    private List<Operaciones> operacionesList;

    public Operadores() {
    }

    public Operadores(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public Operadores(Integer idOperador, String descripcion) {
        this.idOperador = idOperador;
        this.descripcion = descripcion;
    }

    public Integer getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Integer idOperador) {
        this.idOperador = idOperador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Operaciones> getOperacionesList() {
        return operacionesList;
    }

    public void setOperacionesList(List<Operaciones> operacionesList) {
        this.operacionesList = operacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperador != null ? idOperador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operadores)) {
            return false;
        }
        Operadores other = (Operadores) object;
        if ((this.idOperador == null && other.idOperador != null) || (this.idOperador != null && !this.idOperador.equals(other.idOperador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reto5.model.Operadores[ idOperador=" + idOperador + " ]";
    }
    
}
