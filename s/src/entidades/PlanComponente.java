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

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "planComponente", catalog = "campiutti", schema = "")
public class PlanComponente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanComponente", nullable = false)
    private Integer idPlanComponente;
    
    @Basic(optional = false)
    @Column(name = "grupo", nullable = false)
    private int grupo;
    
    @Column(name = "consumo", precision = 22)
    private Double consumo;
    
    @JoinColumn(name = "idArticuloMPC", referencedColumnName = "idArticulo", nullable = false)
    @ManyToOne(optional = false)
    private Articulo idArticuloMPC;
    
    @JoinColumn(name = "idPlanProceso", referencedColumnName = "idPlanProceso", nullable = false)
    @ManyToOne(optional = false)
    private PlanProceso idPlanProceso;

    public PlanComponente() {
    }

    public PlanComponente(Integer idPlanComponente) {
        this.idPlanComponente = idPlanComponente;
    }

    public PlanComponente(Integer idPlanComponente, int grupo) {
        this.idPlanComponente = idPlanComponente;
        this.grupo = grupo;
    }

    public Integer getIdPlanComponente() {
        return idPlanComponente;
    }

    public void setIdPlanComponente(Integer idPlanComponente) {
        this.idPlanComponente = idPlanComponente;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }


    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public Articulo getIdArticuloMPC() {
        return idArticuloMPC;
    }

    public void setIdArticuloMPC(Articulo idArticuloMPC) {
        this.idArticuloMPC = idArticuloMPC;
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
        hash += (idPlanComponente != null ? idPlanComponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlanComponente)) {
            return false;
        }
        PlanComponente other = (PlanComponente) object;
        if ((this.idPlanComponente == null && other.idPlanComponente != null) || (this.idPlanComponente != null && !this.idPlanComponente.equals(other.idPlanComponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PlanComponente[idPlanComponente=" + idPlanComponente + "]";
    }

}
