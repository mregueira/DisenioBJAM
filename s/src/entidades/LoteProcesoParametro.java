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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "loteProcesoParametro", catalog = "campiutti", schema = "")
@XmlRootElement
public class LoteProcesoParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteProcesoParametro", nullable = false)
    private Integer idLoteProcesoParametro;
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column(name = "periodo")
    private Integer periodo;
    
    @Column(name = "corrector")
    private Integer corrector;
    @Column(name = "idTipoAccion")
    private Integer idTipoAccion;
    @JoinColumn(name = "idLoteProceso", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProceso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProcesoParametro")
    private List<LoteProcesoParametroMedida> loteProcesoParametroMedidaList;

    public LoteProcesoParametro() {
    }

    public LoteProcesoParametro(Integer idLoteProcesoParametro) {
        this.idLoteProcesoParametro = idLoteProcesoParametro;
    }

    public Integer getIdLoteProcesoParametro() {
        return idLoteProcesoParametro;
    }

    public void setIdLoteProcesoParametro(Integer idLoteProcesoParametro) {
        this.idLoteProcesoParametro = idLoteProcesoParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getCorrector() {
        return corrector;
    }

    public void setCorrector(Integer corrector) {
        this.corrector = corrector;
    }

    public Integer getIdTipoAccion() {
        return idTipoAccion;
    }

    public void setIdTipoAccion(Integer idTipoAccion) {
        this.idTipoAccion = idTipoAccion;
    }

    public LoteProceso getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(LoteProceso idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    @XmlTransient
    public List<LoteProcesoParametroMedida> getLoteProcesoParametroMedidaList() {
        return loteProcesoParametroMedidaList;
    }

    public void setLoteProcesoParametroMedidaList(List<LoteProcesoParametroMedida> loteProcesoParametroMedidaList) {
        this.loteProcesoParametroMedidaList = loteProcesoParametroMedidaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteProcesoParametro != null ? idLoteProcesoParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoteProcesoParametro)) {
            return false;
        }
        LoteProcesoParametro other = (LoteProcesoParametro) object;
        if ((this.idLoteProcesoParametro == null && other.idLoteProcesoParametro != null) || (this.idLoteProcesoParametro != null && !this.idLoteProcesoParametro.equals(other.idLoteProcesoParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LoteProcesoParametro[ idLoteProcesoParametro=" + idLoteProcesoParametro + " ]";
    }
    
}
