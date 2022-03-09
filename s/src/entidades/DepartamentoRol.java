package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "departamentoRol", catalog = "campiutti", schema = "")
@XmlRootElement

public class DepartamentoRol implements Serializable {

    @OneToMany(mappedBy = "idDepartamentoRol")
    private List<DepartamentoResp> departamentoRespList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDepartamentoRol")
    private List<DepartamentoRolUsuario> departamentoRolUsuarioList;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDepartamentoRol", nullable = false)
    private Integer idDepartamentoRol;
    
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    
    public DepartamentoRol() {
    }

    public DepartamentoRol(Integer idDepartamentoRol) {
        this.idDepartamentoRol = idDepartamentoRol;
    }

    public DepartamentoRol(Integer idDepartamentoRol, String nombre) {
        this.idDepartamentoRol = idDepartamentoRol;
        this.nombre = nombre;
    }

    public Integer getIdDepartamentoRol() {
        return idDepartamentoRol;
    }

    public void setIdDepartamentoRol(Integer idDepartamentoRol) {
        this.idDepartamentoRol = idDepartamentoRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamentoRol != null ? idDepartamentoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DepartamentoRol)) {
            return false;
        }
        DepartamentoRol other = (DepartamentoRol) object;
        return !((this.idDepartamentoRol == null && other.idDepartamentoRol != null) || (this.idDepartamentoRol != null && !this.idDepartamentoRol.equals(other.idDepartamentoRol)));
    }

    @Override
    public String toString() {
        return nombre.trim();
    }

    @XmlTransient
    public List<DepartamentoRolUsuario> getDepartamentoRolUsuarioList() {
        return departamentoRolUsuarioList;
    }

    public void setDepartamentoRolUsuarioList(List<DepartamentoRolUsuario> departamentoRolUsuarioList) {
        this.departamentoRolUsuarioList = departamentoRolUsuarioList;
    }

    @XmlTransient
    public List<DepartamentoResp> getDepartamentoRespList() {
        return departamentoRespList;
    }

    public void setDepartamentoRespList(List<DepartamentoResp> departamentoRespList) {
        this.departamentoRespList = departamentoRespList;
    }
    
}
