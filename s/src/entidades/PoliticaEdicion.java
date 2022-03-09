/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "politicaEdicion", catalog = "campiutti", schema = "")
@XmlRootElement
public class PoliticaEdicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPoliticaEdicion", nullable = false)
    private Integer idPoliticaEdicion;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Lob
    @Column(name = "naturalezaDelCambio", length = 65535)
    private String naturalezaDelCambio;

    @Lob
    @Column(name = "politica", length = 65535)
    private String politica;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    
    public PoliticaEdicion() {
    }

    public PoliticaEdicion(Integer idPoliticaEdicion) {
        this.idPoliticaEdicion = idPoliticaEdicion;
    }

    public PoliticaEdicion(Integer idPoliticaEdicion, Date fecha) {
        this.idPoliticaEdicion = idPoliticaEdicion;
        this.fecha = fecha;
    }

    public Integer getIdPoliticaEdicion() {
        return idPoliticaEdicion;
    }

    public void setIdPoliticaEdicion(Integer idPoliticaEdicion) {
        this.idPoliticaEdicion = idPoliticaEdicion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNaturalezaDelCambio() {
        return naturalezaDelCambio;
    }

    public void setNaturalezaDelCambio(String naturalezaDelCambio) {
        this.naturalezaDelCambio = naturalezaDelCambio;
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
        hash += (idPoliticaEdicion != null ? idPoliticaEdicion.hashCode() : 0);
        return hash;
    }

    public String getPolitica() {
        return politica;
    }

    public void setPolitica(String politica) {
        this.politica = politica;
    }

    
    
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PoliticaEdicion)) {
            return false;
        }
        PoliticaEdicion other = (PoliticaEdicion) object;
        return !((this.idPoliticaEdicion == null && other.idPoliticaEdicion != null) || (this.idPoliticaEdicion != null && !this.idPoliticaEdicion.equals(other.idPoliticaEdicion)));
    }

    @Override
    public String toString() {
        return "entidades.PoliticaEdicion[ idPoliticaEdicion=" + idPoliticaEdicion + " ]";
    }
    
}
