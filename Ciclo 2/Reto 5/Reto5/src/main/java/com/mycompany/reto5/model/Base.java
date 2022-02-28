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
@Table(name = "base")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Base.findAll", query = "SELECT b FROM Base b"),
    @NamedQuery(name = "Base.findByIdBase", query = "SELECT b FROM Base b WHERE b.idBase = :idBase"),
    @NamedQuery(name = "Base.findByDescripcion", query = "SELECT b FROM Base b WHERE b.descripcion = :descripcion")})
public class Base implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_base")
    private Integer idBase;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "base")
    private List<Operaciones> operacionesList;

    public Base() {
    }

    public Base(Integer idBase) {
        this.idBase = idBase;
    }

    public Base(Integer idBase, String descripcion) {
        this.idBase = idBase;
        this.descripcion = descripcion;
    }

    public Integer getIdBase() {
        return idBase;
    }

    public void setIdBase(Integer idBase) {
        this.idBase = idBase;
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
        hash += (idBase != null ? idBase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Base)) {
            return false;
        }
        Base other = (Base) object;
        if ((this.idBase == null && other.idBase != null) || (this.idBase != null && !this.idBase.equals(other.idBase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reto5.model.Base[ idBase=" + idBase + " ]";
    }
    
}
