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
@Table(name = "sesiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sesiones.findAll", query = "SELECT s FROM Sesiones s"),
    @NamedQuery(name = "Sesiones.findByIdSesion", query = "SELECT s FROM Sesiones s WHERE s.idSesion = :idSesion"),
    @NamedQuery(name = "Sesiones.findByFechaInicioSesion", query = "SELECT s FROM Sesiones s WHERE s.fechaInicioSesion = :fechaInicioSesion"),
    @NamedQuery(name = "Sesiones.findByFechaCierreSesion", query = "SELECT s FROM Sesiones s WHERE s.fechaCierreSesion = :fechaCierreSesion")})
public class Sesiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sesion")
    private Integer idSesion;
    @Basic(optional = false)
    @Column(name = "fecha_inicio_sesion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicioSesion;
    @Column(name = "fecha_cierre_sesion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierreSesion;
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")
    @ManyToOne(optional = false)
    private Usuarios usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sesion")
    private List<Operaciones> operacionesList;

    public Sesiones() {
    }

    public Sesiones(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Sesiones(Integer idSesion, Date fechaInicioSesion) {
        this.idSesion = idSesion;
        this.fechaInicioSesion = fechaInicioSesion;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public Date getFechaInicioSesion() {
        return fechaInicioSesion;
    }

    public void setFechaInicioSesion(Date fechaInicioSesion) {
        this.fechaInicioSesion = fechaInicioSesion;
    }

    public Date getFechaCierreSesion() {
        return fechaCierreSesion;
    }

    public void setFechaCierreSesion(Date fechaCierreSesion) {
        this.fechaCierreSesion = fechaCierreSesion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
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
        hash += (idSesion != null ? idSesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sesiones)) {
            return false;
        }
        Sesiones other = (Sesiones) object;
        if ((this.idSesion == null && other.idSesion != null) || (this.idSesion != null && !this.idSesion.equals(other.idSesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reto5.model.Sesiones[ idSesion=" + idSesion + " ]";
    }
    
}
