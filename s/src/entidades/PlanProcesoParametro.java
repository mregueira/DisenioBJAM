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
@Table(name = "planProcesoParametro", catalog = "campiutti", schema = "")
@XmlRootElement
public class PlanProcesoParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanProcesoParametro", nullable = false)
    private Integer idPlanProcesoParametro;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "corrector")
    private Integer corrector;
    
    @Column(name = "periodo")
    private Integer periodo;
    
    @Column(name = "idTipoAccion")
    private Integer idTipoAccion;
    @JoinColumn(name = "idPlanProceso", referencedColumnName = "idPlanProceso", nullable = false)
    @ManyToOne(optional = false)
    private PlanProceso idPlanProceso;

    public PlanProcesoParametro() {
    }

    public PlanProcesoParametro(Integer idPlanProcesoParametro) {
        this.idPlanProcesoParametro = idPlanProcesoParametro;
    }

    public Integer getIdPlanProcesoParametro() {
        return idPlanProcesoParametro;
    }

    public void setIdPlanProcesoParametro(Integer idPlanProcesoParametro) {
        this.idPlanProcesoParametro = idPlanProcesoParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCorrector() {
        return corrector;
    }

    public void setCorrector(Integer corrector) {
        this.corrector = corrector;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getIdTipoAccion() {
        return idTipoAccion;
    }

    public void setIdTipoAccion(Integer idTipoAccion) {
        this.idTipoAccion = idTipoAccion;
    }

    public PlanProceso getIdPlanProceso() {
        return idPlanProceso;
    }

    public void setIdPlanProceso(PlanProceso idPlanProceso) {
        this.idPlanProceso = idPlanProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanProcesoParametro != null ? idPlanProcesoParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanProcesoParametro)) {
            return false;
        }
        PlanProcesoParametro other = (PlanProcesoParametro) object;
        return !((this.idPlanProcesoParametro == null && other.idPlanProcesoParametro != null) || (this.idPlanProcesoParametro != null && !this.idPlanProcesoParametro.equals(other.idPlanProcesoParametro)));
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
