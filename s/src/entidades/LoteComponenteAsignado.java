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
@Table(name = "loteComponenteAsignado", catalog = "campiutti", schema = "")
@XmlRootElement
public class LoteComponenteAsignado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteComponenteAsignado", nullable = false)
    private Integer idLoteComponenteAsignado;

    @Column(name = "idStockEstado1")
    private Integer idStockEstado1;
    @Column(name = "idStockEstado2")
    private Integer idStockEstado2;
    @Column(name = "idStockEstado3")
    private Integer idStockEstado3;
    
    @JoinColumn(name = "idLoteComponente", referencedColumnName = "idLoteComp", nullable = false)
    @ManyToOne(optional = false)
    private LoteComponente idLoteComponente;
    
    //Materia prima o comonente relacionado
    @JoinColumn(name = "idLoteProcesoMPC", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProcesoMPC;

    public LoteComponenteAsignado() {
    }

    public LoteComponenteAsignado(Integer idLoteComponenteAsignado) {
        this.idLoteComponenteAsignado = idLoteComponenteAsignado;
    }

    public Integer getIdLoteComponenteAsignado() {
        return idLoteComponenteAsignado;
    }

    public void setIdLoteComponenteAsignado(Integer idLoteComponenteAsignado) {
        this.idLoteComponenteAsignado = idLoteComponenteAsignado;
    }

    public Integer getIdStockEstado1() {
        return idStockEstado1;
    }

    public void setIdStockEstado1(Integer idStockEstado1) {
        this.idStockEstado1 = idStockEstado1;
    }

    public Integer getIdStockEstado2() {
        return idStockEstado2;
    }

    public void setIdStockEstado2(Integer idStockEstado2) {
        this.idStockEstado2 = idStockEstado2;
    }

    public Integer getIdStockEstado3() {
        return idStockEstado3;
    }

    public void setIdStockEstado3(Integer idStockEstado3) {
        this.idStockEstado3 = idStockEstado3;
    }

    public LoteComponente getIdLoteComponente() {
        return idLoteComponente;
    }

    public void setIdLoteComponente(LoteComponente idLoteComponente) {
        this.idLoteComponente = idLoteComponente;
    }

    /**
     * 
     * @return materia prima o componente relacionada
     */
    public LoteProceso getIdLoteProcesoMPC() {
        return idLoteProcesoMPC;
    }

    /**
     * Ajusta la materia prima o componente relacionado
     * @param idLoteProcesoMPC 
     */
    public void setIdLoteProcesoMPC(LoteProceso idLoteProcesoMPC) {
        this.idLoteProcesoMPC = idLoteProcesoMPC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteComponenteAsignado != null ? idLoteComponenteAsignado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LoteComponenteAsignado)) {
            return false;
        }
        LoteComponenteAsignado other = (LoteComponenteAsignado) object;
        return (this.idLoteComponenteAsignado != null || other.idLoteComponenteAsignado == null) && (this.idLoteComponenteAsignado == null || this.idLoteComponenteAsignado.equals(other.idLoteComponenteAsignado));
    }

    @Override
    public String toString() {
        return "entidades.LoteComponenteAsignado[ idLoteComponenteAsignado=" + idLoteComponenteAsignado + " ]";
    }
    
}
