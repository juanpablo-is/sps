package com.sps.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    ,
    @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
    ,
    @NamedQuery(name = "Reserva.findByDia", query = "SELECT r FROM Reserva r WHERE r.dia = :dia ORDER BY r.id DESC")
    ,
    @NamedQuery(name = "Reserva.findByEntrada", query = "SELECT r FROM Reserva r WHERE r.entrada = :entrada")
    ,
    @NamedQuery(name = "Reserva.findBySalida", query = "SELECT r FROM Reserva r WHERE r.salida = :salida")
    ,
    @NamedQuery(name = "Reserva.findBySelector", query = "SELECT COUNT(r) FROM Reserva r WHERE r.idCliente =:idCliente")
    ,
    @NamedQuery(name = "Reserva.findByUsuario", query = "SELECT r FROM Reserva r WHERE r.idUsuario =:idUsuario")
    ,
    @NamedQuery(name = "Reserva.findByCliente", query = "SELECT r FROM Reserva r WHERE r.idCliente =:idCliente")
    ,
    @NamedQuery(name = "Reserva.findByOcupado", query = "SELECT r FROM Reserva r WHERE r.ocupado = :ocupado")})

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA")
    private String dia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ENTRADA")
    private String entrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @Basic(optional = false)
    @Size(max = 8)
    @Column(name = "SALIDA")
    private String salida;
    @Column(name = "OCUPADO")
    private Boolean ocupado;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "PLACA")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Reserva() {
    }

    public Reserva(String dia, String entrada, String salida, Usuario usuario, Cliente cliente, boolean opcion) {
        this.dia = dia;
        this.entrada = entrada;
        this.salida = salida;
        this.idUsuario = usuario;
        this.idCliente = cliente;
        this.ocupado = opcion;
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

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        return "Reserva{" + "id=" + id + ", dia=" + dia + ", entrada=" + entrada + ", salida=" + salida + ", ocupado=" + ocupado + ", idCliente=" + idCliente + ", idUsuario=" + idUsuario + '}';
    }

}
