package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "departamentoResp", catalog = "campiutti", schema = "")
@XmlRootElement

public class DepartamentoResp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDepartamentoResp", nullable = false)
    private Integer idDepartamentoResp;
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "responsabilidad")
    private Boolean responsabilidad;
    @Column(name = "autoridad")
    private Boolean autoridad;
    
    
    
    @JoinColumn(name = "idDepartamentoRol", referencedColumnName = "idDepartamentoRol")
    @ManyToOne
    private DepartamentoRol idDepartamentoRol;

    public DepartamentoResp() {
    }

    public DepartamentoResp(Integer idDepartamentoResp) {
        this.idDepartamentoResp = idDepartamentoResp;
    }

    public Integer getIdDepartamentoResp() {
        return idDepartamentoResp;
    }

    public void setIdDepartamentoResp(Integer idDepartamentoResp) {
        this.idDepartamentoResp = idDepartamentoResp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(Boolean responsabilidad) {
        this.responsabilidad = responsabilidad;
    }

    public Boolean getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(Boolean autoridad) {
        this.autoridad = autoridad;
    }

    
    
    public DepartamentoRol getIdDepartamentoRol() {
        return idDepartamentoRol;
    }

    public void setIdDepartamentoRol(DepartamentoRol idDepartamentoRol) {
        this.idDepartamentoRol = idDepartamentoRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamentoResp != null ? idDepartamentoResp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DepartamentoResp)) {
            return false;
        }
        DepartamentoResp other = (DepartamentoResp) object;
        return !((this.idDepartamentoResp == null && other.idDepartamentoResp != null) || (this.idDepartamentoResp != null && !this.idDepartamentoResp.equals(other.idDepartamentoResp)));
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
