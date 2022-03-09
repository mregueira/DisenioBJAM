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
@Table(name = "departamentoRolUsuario", catalog = "campiutti", schema = "")
@XmlRootElement
public class DepartamentoRolUsuario implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDepartamentoRolUsuario", nullable = false)
    private Integer idDepartamentoRolUsuario;
    
    @Basic(optional = false)
    @Column(name = "idTipoCalificacion", nullable = false)
    private int idTipoCalificacion;
    
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    @JoinColumn(name = "idDepartamentoRol", referencedColumnName = "idDepartamentoRol", nullable = false)
    @ManyToOne(optional = false)
    private DepartamentoRol idDepartamentoRol;

    private static final long serialVersionUID = 1L;
    
    
    public DepartamentoRolUsuario() {
    }

    public DepartamentoRolUsuario(Integer idDepartamentoRolUsuario) {
        this.idDepartamentoRolUsuario = idDepartamentoRolUsuario;
    }

    public DepartamentoRolUsuario(Integer idDepartamentoRolUsuario, int idTipoCalificacion, boolean activo) {
        this.idDepartamentoRolUsuario = idDepartamentoRolUsuario;
        this.idTipoCalificacion = idTipoCalificacion;
        this.activo = activo;
    }

    public Integer getIdDepartamentoRolUsuario() {
        return idDepartamentoRolUsuario;
    }

    public void setIdDepartamentoRolUsuario(Integer idDepartamentoRolUsuario) {
        this.idDepartamentoRolUsuario = idDepartamentoRolUsuario;
    }

    public int getIdTipoCalificacion() {
        return idTipoCalificacion;
    }

    public void setIdTipoCalificacion(int idTipoCalificacion) {
        this.idTipoCalificacion = idTipoCalificacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idDepartamentoRolUsuario != null ? idDepartamentoRolUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DepartamentoRolUsuario)) {
            return false;
        }
        DepartamentoRolUsuario other = (DepartamentoRolUsuario) object;
        return !((this.idDepartamentoRolUsuario == null && other.idDepartamentoRolUsuario != null) || (this.idDepartamentoRolUsuario != null && !this.idDepartamentoRolUsuario.equals(other.idDepartamentoRolUsuario)));
    }

    @Override
    public String toString() {
        return idUsuario.toString();
    }

}
