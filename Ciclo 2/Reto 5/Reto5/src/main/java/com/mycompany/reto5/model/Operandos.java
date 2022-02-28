/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reto5.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "operandos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operandos.findAll", query = "SELECT o FROM Operandos o"),
    @NamedQuery(name = "Operandos.findByIdOperando", query = "SELECT o FROM Operandos o WHERE o.idOperando = :idOperando"),
    @NamedQuery(name = "Operandos.findByOperando", query = "SELECT o FROM Operandos o WHERE o.operando = :operando")})
public class Operandos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_operando")
    private Integer idOperando;
    @Basic(optional = false)
    @Column(name = "operando")
    private String operando;
    @JoinColumn(name = "id_operacion", referencedColumnName = "id_operacion")
    @ManyToOne(optional = false)
    private Operaciones idOperacion;

    public Operandos() {
    }

    public Operandos(Integer idOperando) {
        this.idOperando = idOperando;
    }

    public Operandos(Integer idOperando, String operando) {
        this.idOperando = idOperando;
        this.operando = operando;
    }

    public Integer getIdOperando() {
        return idOperando;
    }

    public void setIdOperando(Integer idOperando) {
        this.idOperando = idOperando;
    }

    public String getOperando() {
        return operando;
    }

    public void setOperando(String operando) {
        this.operando = operando;
    }

    public Operaciones getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Operaciones idOperacion) {
        this.idOperacion = idOperacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperando != null ? idOperando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operandos)) {
            return false;
        }
        Operandos other = (Operandos) object;
        if ((this.idOperando == null && other.idOperando != null) || (this.idOperando != null && !this.idOperando.equals(other.idOperando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.reto5.model.Operandos[ idOperando=" + idOperando + " ]";
    }
    
}
