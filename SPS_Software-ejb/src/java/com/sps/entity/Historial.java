package com.sps.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "HISTORIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h")
    , @NamedQuery(name = "Historial.findById", query = "SELECT h FROM Historial h WHERE h.id = :id")
    , @NamedQuery(name = "Historial.findByIdReserva", query = "SELECT h FROM Historial h WHERE h.idReserva = :idReserva")
    , @NamedQuery(name = "Historial.findByPrecio", query = "SELECT h FROM Historial h WHERE h.precio = :precio")
    , @NamedQuery(name = "Historial.findByCliente", query = "SELECT h FROM Historial h WHERE h.idReserva IN (SELECT r FROM Reserva r WHERE r.idPlaza IN (SELECT p FROM Plaza p WHERE p.idCliente = :idCliente))")
    , @NamedQuery(name = "Historial.findByUsuario", query = "SELECT h FROM Historial h WHERE h.idReserva IN (SELECT r FROM Reserva r WHERE r.idUsuario = :idUsuario)")
    , @NamedQuery(name = "Historial.findBySalida", query = "SELECT h FROM Historial h WHERE h.salida = :salida")})

public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date salida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @JoinColumn(name = "ID_RESERVA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Reserva idReserva;

    public Historial() {
    }

    public Historial(Integer id) {
        this.id = id;
    }

    public Historial(Integer id, Date salida, double precio) {
        this.id = id;
        this.salida = salida;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sps.entity.Historial[ id=" + id + " ]";
    }
    
}
