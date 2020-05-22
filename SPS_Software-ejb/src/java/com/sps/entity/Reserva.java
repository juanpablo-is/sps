package com.sps.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
    , @NamedQuery(name = "Reserva.findByDia", query = "SELECT r FROM Reserva r WHERE r.dia = :dia")
    , @NamedQuery(name = "Reserva.findByEntrada", query = "SELECT r FROM Reserva r WHERE r.entrada = :entrada")
    , @NamedQuery(name = "Reserva.findByUsuario", query = "SELECT r FROM Reserva r WHERE r.idUsuario =:idUsuario")
    , @NamedQuery(name = "Reserva.findByUsuarioInicio", query = "SELECT r FROM Reserva r WHERE r.idUsuario =:idUsuario ORDER BY r.id DESC")
    , @NamedQuery(name = "Reserva.findByUsuarioCliente", query = "SELECT r FROM Reserva r WHERE r.idUsuario =:idUsuario AND r.idPlaza.idCliente = :idCliente ORDER BY r.id DESC")
    , @NamedQuery(name = "Reserva.findByIdCliente", query = "SELECT p.id, r.idUsuario, p.tipoVehiculo FROM Plaza p LEFT JOIN Reserva r ON p.id = r.idPlaza AND r.id IN (SELECT r2.id FROM Reserva r2 WHERE r2.id IN (SELECT MAX(r3.id) FROM Reserva r3 GROUP BY r3.idPlaza)) WHERE p.idCliente = :idCliente")
    , @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado")})

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "DIA")
    private String dia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ENTRADA")
    private String entrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private Boolean estado;
    @JoinColumn(name = "ID_PLAZA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Plaza idPlaza;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "PLACA")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReserva")
    private Collection<Historial> historialCollection;

    public Reserva() {
    }

    public Reserva(Integer id) {
        this.id = id;
    }

    public Reserva(String dia, String entrada, Boolean estado, Plaza plaza, Usuario usuario) {
        this.dia = dia;
        this.entrada = entrada;
        this.estado = estado;
        this.idPlaza = plaza;
        this.idUsuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Plaza getIdPlaza() {
        return idPlaza;
    }

    public void setIdPlaza(Plaza idPlaza) {
        this.idPlaza = idPlaza;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Historial> getHistorialCollection() {
        return historialCollection;
    }

    public void setHistorialCollection(Collection<Historial> historialCollection) {
        this.historialCollection = historialCollection;
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sps.entity.Reserva[ id=" + id + " ]";
    }

}
