package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "monitorProceso", catalog = "campiutti", schema = "")
@XmlRootElement
public class MonitorProceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonitorProceso", nullable = false)
    private Integer idMonitorProceso;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "idEstado")
    private Integer idEstado;

    
    @OneToMany(mappedBy = "idMonitorProcesoEnMedicion")
    private List<Monitor> monitorList;
    @JoinColumn(name = "idLoteProceso", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProceso;
    @JoinColumn(name = "idMonitor", referencedColumnName = "idMonitor", nullable = false)
    @ManyToOne(optional = false)
    private Monitor idMonitor;

    public MonitorProceso() {
    }

    public MonitorProceso(Integer idMonitorProceso) {
        this.idMonitorProceso = idMonitorProceso;
    }

    public Integer getIdMonitorProceso() {
        return idMonitorProceso;
    }

    public void setIdMonitorProceso(Integer idMonitorProceso) {
        this.idMonitorProceso = idMonitorProceso;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    
    @XmlTransient
    public List<Monitor> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<Monitor> monitorList) {
        this.monitorList = monitorList;
    }

    public LoteProceso getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(LoteProceso idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    public Monitor getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Monitor idMonitor) {
        this.idMonitor = idMonitor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonitorProceso != null ? idMonitorProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MonitorProceso)) {
            return false;
        }
        MonitorProceso other = (MonitorProceso) object;
        return !((this.idMonitorProceso == null && other.idMonitorProceso != null) || (this.idMonitorProceso != null && !this.idMonitorProceso.equals(other.idMonitorProceso)));
    }

    @Override
    public String toString() {
        return idLoteProceso.toString();
    }
    
}
