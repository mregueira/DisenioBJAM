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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "usuarioPermiso", catalog = "campiutti", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idUsuario", "idPermiso"})})
@XmlRootElement

public class UsuarioPermiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuarioPermiso", nullable = false)
    private Integer idUsuarioPermiso;
    
    @Basic(optional = false)
    @Column(name = "idPermiso", nullable = false)
    private int idPermiso;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public UsuarioPermiso() {
    }

    public UsuarioPermiso(Integer idUsuarioPermiso) {
        this.idUsuarioPermiso = idUsuarioPermiso;
    }

    public UsuarioPermiso(Usuario usuario, int idPermiso) {
        this.idUsuario = usuario;
        this.idPermiso = idPermiso;
    }

    public Integer getIdUsuarioPermiso() {
        return idUsuarioPermiso;
    }

    public void setIdUsuarioPermiso(Integer idUsuarioPermiso) {
        this.idUsuarioPermiso = idUsuarioPermiso;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
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
        hash += (idUsuarioPermiso != null ? idUsuarioPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioPermiso)) {
            return false;
        }
        UsuarioPermiso other = (UsuarioPermiso) object;
        if ((this.idUsuarioPermiso == null && other.idUsuarioPermiso != null) || (this.idUsuarioPermiso != null && !this.idUsuarioPermiso.equals(other.idUsuarioPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.UsuarioPermiso[ idUsuarioPermiso=" + idUsuarioPermiso + " ]";
    }
    
}
