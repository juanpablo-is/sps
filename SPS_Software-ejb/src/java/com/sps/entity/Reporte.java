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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Pablo
 */
@Entity
@Table(name = "REPORTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r")
    , @NamedQuery(name = "Reporte.findById", query = "SELECT r FROM Reporte r WHERE r.id = :id")
    , @NamedQuery(name = "Reporte.findByFecha", query = "SELECT r FROM Reporte r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "Reporte.findByTexto", query = "SELECT r FROM Reporte r WHERE r.texto = :texto")
    , @NamedQuery(name = "Reporte.findByTitulo", query = "SELECT r FROM Reporte r WHERE r.titulo = :titulo")
    , @NamedQuery(name = "Reporte.findLast", query = "SELECT r FROM Reporte r ORDER BY r.id DESC")
    , @NamedQuery(name = "Reporte.findByCliente", query = "SELECT r FROM Reporte r WHERE r.idCliente = :idCliente ORDER BY r.id DESC")
    , @NamedQuery(name = "Reporte.findByEstado", query = "SELECT r FROM Reporte r WHERE r.estado = :estado")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 700)
    @Column(name = "TEXTO")
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private Boolean estado;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "PLACA")
    @ManyToOne
    private Usuario idUsuario;

    public Reporte() {
    }

    public Reporte(String texto, String titulo, Cliente idCliente, Usuario idUsuario) {
        this.texto = texto;
        this.titulo = titulo;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sps.entity.Reporte[ id=" + id + " ]";
    }

}
