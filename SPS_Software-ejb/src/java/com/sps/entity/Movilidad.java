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
@Table(name = "MOVILIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movilidad.findAll", query = "SELECT m FROM Movilidad m")
    , @NamedQuery(name = "Movilidad.findById", query = "SELECT m FROM Movilidad m WHERE m.id = :id")
    , @NamedQuery(name = "Movilidad.findByPersona", query = "SELECT m FROM Movilidad m WHERE m.idPersona = :idPersona")
    , @NamedQuery(name = "Movilidad.findByEmpresa", query = "SELECT m FROM Movilidad m WHERE m.empresa = :empresa")
    , @NamedQuery(name = "Movilidad.findByEmpresaGroup", query = "SELECT m.empresa FROM Movilidad m GROUP BY m.empresa")})
public class Movilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "EMPRESA")
    private String empresa;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "CEDULA")
    @ManyToOne(optional = false)
    private Persona idPersona;

    public Movilidad() {
    }

    public Movilidad(String id, String empresa, Persona idPersona) {
        this.id = id;
        this.empresa = empresa;
        this.idPersona = idPersona;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
        if (!(object instanceof Movilidad)) {
            return false;
        }
        Movilidad other = (Movilidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"empresa\":\"" + empresa + "\", \"perfil\":\"movilidad\"}";
    }
    
}
