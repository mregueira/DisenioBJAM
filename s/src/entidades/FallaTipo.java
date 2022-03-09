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
@Table(name = "fallaTipo", catalog = "campiutti", schema = "")
@XmlRootElement

public class FallaTipo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idFallaTipo;

    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFallaTipo", orphanRemoval=true)
    private List<Falla> fallaList;

    public FallaTipo() {
    }

    public FallaTipo(Integer idFallaTipo) {
        this.idFallaTipo = idFallaTipo;
    }

    public FallaTipo(Integer idFallaTipo, String nombre) {
        this.idFallaTipo = idFallaTipo;
        this.nombre = nombre;
    }

    public Integer getIdFallaTipo() {
        return idFallaTipo;
    }

    public void setIdFallaTipo(Integer idFallaTipo) {
        this.idFallaTipo = idFallaTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Falla> getFallaList() {
        return fallaList;
    }

    public void setFallaList(List<Falla> fallaList) {
        this.fallaList = fallaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFallaTipo != null ? idFallaTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FallaTipo)) {
            return false;
        }
        FallaTipo other = (FallaTipo) object;
        if ((this.idFallaTipo == null && other.idFallaTipo != null) || (this.idFallaTipo != null && !this.idFallaTipo.equals(other.idFallaTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
