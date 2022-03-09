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
@Table(name = "loteComponente", catalog = "campiutti", schema = "")
@XmlRootElement
public class LoteComponente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteComp", nullable = false)
    private Integer idLoteComp;
    @Basic(optional = false)
    @Column(name = "grupo", nullable = false)
    private int grupo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "consumo", precision = 22)
    private Double consumo;

    @JoinColumn(name = "idLoteProceso", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProceso;
    
    @JoinColumn(name = "idArticuloMPC", referencedColumnName = "idArticulo", nullable = false)
    @ManyToOne(optional = false)
    private Articulo idArticuloMPC;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteComponente", orphanRemoval=true)
    private List<LoteComponenteAsignado> loteComponenteAsignadoList;

    public LoteComponente() {
    }

    public LoteComponente(Integer idLoteComp) {
        this.idLoteComp = idLoteComp;
    }

    public LoteComponente(Integer idLoteComp, int grupo) {
        this.idLoteComp = idLoteComp;
        this.grupo = grupo;
    }

    public Integer getIdLoteComp() {
        return idLoteComp;
    }

    public void setIdLoteComp(Integer idLoteComp) {
        this.idLoteComp = idLoteComp;
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

    public LoteProceso getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(LoteProceso idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    public Articulo getIdArticuloMPC() {
        return idArticuloMPC;
    }

    public void setIdArticuloMPC(Articulo idArticuloMPC) {
        this.idArticuloMPC = idArticuloMPC;
    }

    @XmlTransient
    public List<LoteComponenteAsignado> getLoteComponenteAsignadoList() {
        return loteComponenteAsignadoList;
    }

    public void setLoteComponenteAsignadoList(List<LoteComponenteAsignado> loteComponenteAsignadoList) {
        this.loteComponenteAsignadoList = loteComponenteAsignadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteComp != null ? idLoteComp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoteComponente)) {
            return false;
        }
        LoteComponente other = (LoteComponente) object;
        return (this.idLoteComp != null || other.idLoteComp == null) && (this.idLoteComp == null || this.idLoteComp.equals(other.idLoteComp));
    }

    @Override
    public String toString() {
        return "entidades.LoteComponente[ idLoteComp=" + idLoteComp + " ]";
    }
    
}
