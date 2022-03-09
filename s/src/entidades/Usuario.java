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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "usuario", catalog = "campiutti", schema = "")
public class Usuario implements Serializable {
    
    @Lob
    @Column(name = "clave")
    private byte[] clave;
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;


    @Column(name = "nombreLogin", length = 15)
    private String nombreLogin;

    @Column(name = "apellido", length = 30)
    private String apellido;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<UsuarioPermiso> usuarioPermisoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<DepartamentoRolUsuario> departamentoRolUsuarioList;
    
    
    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, int codigo, String nombre) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<UsuarioPermiso> getUsuarioPermisoList() {
        return usuarioPermisoList;
    }

    public void setUsuarioPermisoList(List<UsuarioPermiso> usuarioPermisoList) {
        this.usuarioPermisoList = usuarioPermisoList;
    }

    public List<DepartamentoRolUsuario> getDepartamentoRolUsuarioList() {
        return departamentoRolUsuarioList;
    }

    public void setDepartamentoRolUsuarioList(List<DepartamentoRolUsuario> departamentoRolUsuarioList) {
        this.departamentoRolUsuarioList = departamentoRolUsuarioList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario)));
    }

    @Override
    public String toString() {
        if(getNombre() != null && getApellido() != null){
            return getNombre().trim() + " " + getApellido().trim();
        }
        if(getNombre() != null){
            return getNombre().trim();
        }
        if(getApellido() != null ){
            return getApellido().trim();
        }
        return "¿¿??";
    }



    public byte[] getClave() {
        return clave;
    }

    public void setClave(byte[] clave) {
        this.clave = clave;
    }

}
