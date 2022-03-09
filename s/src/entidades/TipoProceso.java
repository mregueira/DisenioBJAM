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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "tipoProceso", catalog = "campiutti", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idTipoProceso"})})
@XmlRootElement

public class TipoProceso implements Serializable {
    @Basic(optional = false)
    @Column(name = "idTipo", nullable = false)
    private int idTipo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoProceso", nullable = false)
    private Integer idTipoProceso;
    
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    
    @Column(name = "pasoObligado")
    private Boolean pasoObligado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoProceso")
    private List<LoteProceso> loteProcesoList;
    
    private static final long serialVersionUID = 1L;
    
    public TipoProceso() {
    }

    public TipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public TipoProceso(Integer idTipoProceso, String nombre, int tipo) {
        this.idTipoProceso = idTipoProceso;
        this.nombre = nombre;
        this.idTipo = tipo;
    }

    public Integer getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(Integer idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getPasoObligado() {
        return pasoObligado;
    }

    public void setPasoObligado(Boolean pasoObligado) {
        this.pasoObligado = pasoObligado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoProceso != null ? idTipoProceso.hashCode() : 0);
        return hash;
    }

    @XmlTransient
    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoProceso)) {
            return false;
        }
        TipoProceso other = (TipoProceso) object;
        return (this.idTipoProceso != null || other.idTipoProceso == null) && (this.idTipoProceso == null || this.idTipoProceso.equals(other.idTipoProceso));
    }

    @Override
    public String toString() {
        return nombre.trim();
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
}
