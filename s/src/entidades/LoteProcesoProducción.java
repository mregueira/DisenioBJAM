package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "loteProcesoProducción", catalog = "campiutti", schema = "")
@XmlRootElement
public class LoteProcesoProducción implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteProcesoProducción", nullable = false)
    private Integer idLoteProcesoProducción;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @JoinColumn(name = "idLoteProceso", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProceso;

    public LoteProcesoProducción() {
    }

    public LoteProcesoProducción(Integer idLoteProcesoProducción) {
        this.idLoteProcesoProducción = idLoteProcesoProducción;
    }

    public Integer getIdLoteProcesoProducción() {
        return idLoteProcesoProducción;
    }

    public void setIdLoteProcesoProducción(Integer idLoteProcesoProducción) {
        this.idLoteProcesoProducción = idLoteProcesoProducción;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public LoteProceso getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(LoteProceso idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteProcesoProducción != null ? idLoteProcesoProducción.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LoteProcesoProducción)) {
            return false;
        }
        LoteProcesoProducción other = (LoteProcesoProducción) object;
        return !((this.idLoteProcesoProducción == null && other.idLoteProcesoProducción != null) || (this.idLoteProcesoProducción != null && !this.idLoteProcesoProducción.equals(other.idLoteProcesoProducción)));
    }

    @Override
    public String toString() {
        return "Producción";
    }
    
}
