package com.sps.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CLIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id")
    , @NamedQuery(name = "Cliente.findByCarros", query = "SELECT c FROM Cliente c WHERE c.carros = :carros")
    , @NamedQuery(name = "Cliente.findByMotos", query = "SELECT c FROM Cliente c WHERE c.motos = :motos")
    , @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion")
    , @NamedQuery(name = "Cliente.findByLatitud", query = "SELECT c FROM Cliente c WHERE c.latitud = :latitud")
    , @NamedQuery(name = "Cliente.findByLongitud", query = "SELECT c FROM Cliente c WHERE c.longitud = :longitud")
    , @NamedQuery(name = "Cliente.findByInicio", query = "SELECT c FROM Cliente c WHERE c.inicio = :inicio")
    , @NamedQuery(name = "Cliente.findByFin", query = "SELECT c FROM Cliente c WHERE c.fin = :fin")
    , @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cliente.findByCedula", query = "SELECT c FROM Cliente c WHERE c.idPersona = :idPersona")
    , @NamedQuery(name = "Cliente.findByPrecio", query = "SELECT c FROM Cliente c WHERE c.precio = :precio")})

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CARROS")
    private int carros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOTOS")
    private int motos;
    @Size(max = 50)
    @Column(name = "DIRECCION")
    private String direccion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUD")
    private BigDecimal latitud;
    @Column(name = "LONGITUD")
    private BigDecimal longitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "INICIO")
    private String inicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "FIN")
    private String fin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente")
    private Collection<Plaza> plazaCollection;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Cliente() {
    }

    public Cliente(String id) {
        this.id = id;
    }

    public Cliente(String id, int carros, int motos, String inicio, String fin, String nombre, double precio) {
        this.id = id;
        this.carros = carros;
        this.motos = motos;
        this.inicio = inicio;
        this.fin = fin;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCarros() {
        return carros;
    }

    public void setCarros(int carros) {
        this.carros = carros;
    }

    public int getMotos() {
        return motos;
    }

    public void setMotos(int motos) {
        this.motos = motos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Plaza> getPlazaCollection() {
        return plazaCollection;
    }

    public void setPlazaCollection(Collection<Plaza> plazaCollection) {
        this.plazaCollection = plazaCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"nombre\":\"" + nombre + "\", \"perfil\":\"cliente\", \"direccion\":\"" + direccion + "\"}";
    }

}
