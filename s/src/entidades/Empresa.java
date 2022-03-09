package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "empresa", catalog = "campiutti", schema = "")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmpresa", nullable = false)
    private Integer idEmpresa;
    
    @Lob
    @Column(name = "planContingencia", length = 65535)
    private String planContingencia;
    @Lob
    @Column(name = "planContingenciaCambios", length = 65535)
    private String planContingenciaCambios;
    

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public String getPlanContingencia() {
        return planContingencia;
    }

    public void setPlanContingencia(String planContingencia) {
        this.planContingencia = planContingencia;
    }

    public String getPlanContingenciaCambios() {
        return planContingenciaCambios;
    }

    public void setPlanContingenciaCambios(String planContingenciaCambios) {
        this.planContingenciaCambios = planContingenciaCambios;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        return !((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa)));
    }
}
