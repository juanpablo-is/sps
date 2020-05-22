package com.sps.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "PLAZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plaza.findAll", query = "SELECT p FROM Plaza p")
    , @NamedQuery(name = "Plaza.findById", query = "SELECT p FROM Plaza p WHERE p.id = :id")
    , @NamedQuery(name = "Plaza.findByIdCliente", query = "SELECT p FROM Plaza p WHERE p.idCliente = :idCliente")
    , @NamedQuery(name = "Plaza.findByTipoVehiculo", query = "SELECT p FROM Plaza p WHERE p.tipoVehiculo = :tipoVehiculo")
//    , @NamedQuery(name = "Plaza.plazasHabilitadasGroup", query = "SELECT p.idCliente FROM Plaza p WHERE p.cubierto = :cubierto AND p.tipoVehiculo = :tipoVehiculo AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.idPlaza = p and r.estado = true) GROUP BY p.idCliente")
//    , @NamedQuery(name = "Plaza.plazasHabilitadas", query = "SELECT p FROM Plaza p WHERE p.cubierto = :cubierto AND p.tipoVehiculo = :tipoVehiculo AND p.idCliente = :idCliente AND NOT EXISTS (SELECT r FROM Reserva r WHERE r.idPlaza = p and r.estado = true)")
    , @NamedQuery(name = "Plaza.findByCubierto", query = "SELECT p FROM Plaza p WHERE p.cubierto = :cubierto")})

public class Plaza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_VEHICULO")
    private Boolean tipoVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUBIERTO")
    private Boolean cubierto;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlaza")
    private Collection<Reserva> reservaCollection;

    public Plaza() {
    }

    public Plaza(String id) {
        this.id = id;
    }

    public Plaza(String id, Boolean tipoVehiculo, Boolean cubierto, Cliente cliente) {
        this.id = id;
        this.tipoVehiculo = tipoVehiculo;
        this.cubierto = cubierto;
        this.idCliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Boolean tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Boolean getCubierto() {
        return cubierto;
    }

    public void setCubierto(Boolean cubierto) {
        this.cubierto = cubierto;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
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
        if (!(object instanceof Plaza)) {
            return false;
        }
        Plaza other = (Plaza) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sps.entity.Plaza[ id=" + id + " ]";
    }

}
