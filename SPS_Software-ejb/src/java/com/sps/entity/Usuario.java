/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByPlaca", query = "SELECT u FROM Usuario u WHERE u.placa = :placa")
    , @NamedQuery(name = "Usuario.findByMarca", query = "SELECT u FROM Usuario u WHERE u.marca = :marca")
    , @NamedQuery(name = "Usuario.findByCedula", query = "SELECT u FROM Usuario u WHERE u.idPersona = :idPersona")
    , @NamedQuery(name = "Usuario.findByIdPropiedad", query = "SELECT u FROM Usuario u WHERE u.idPropiedad = :idPropiedad")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "PLACA")
    private String placa;
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MARCA")
    private String marca;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_PROPIEDAD")
    private String idPropiedad;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Usuario() {
    }

    public Usuario(String idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Usuario(String idPropiedad, String placa, String marca) {
        this.idPropiedad = idPropiedad;
        this.placa = placa;
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(String idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPropiedad != null ? idPropiedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idPropiedad == null && other.idPropiedad != null) || (this.idPropiedad != null && !this.idPropiedad.equals(other.idPropiedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "placa=" + placa + ", marca=" + marca + ", idPropiedad=" + idPropiedad + ", idPersona=" + idPersona + '}';
    }

}
