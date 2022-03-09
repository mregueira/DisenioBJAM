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
@Table(name = "loteProcesoParametroMedida", catalog = "campiutti", schema = "")
@XmlRootElement
public class LoteProcesoParametroMedida implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteProcesoParametroMedida", nullable = false)
    private Integer idLoteProcesoParametroMedida;

    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    
    @Column(name = "idTipoAccion")
    private Integer idTipoAccion;
    @Column(name = "cantidadPiezas")
    private Integer cantidadPiezas;
    @JoinColumn(name = "idLoteProcesoParametro", referencedColumnName = "idLoteProcesoParametro", nullable = false)
    @ManyToOne(optional = false)
    private LoteProcesoParametro idLoteProcesoParametro;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    public LoteProcesoParametroMedida() {
    }

    public LoteProcesoParametroMedida(Integer idLoteProcesoParametroMedida) {
        this.idLoteProcesoParametroMedida = idLoteProcesoParametroMedida;
    }

    public Integer getIdLoteProcesoParametroMedida() {
        return idLoteProcesoParametroMedida;
    }

    public void setIdLoteProcesoParametroMedida(Integer idLoteProcesoParametroMedida) {
        this.idLoteProcesoParametroMedida = idLoteProcesoParametroMedida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdTipoAccion() {
        return idTipoAccion;
    }

    public void setIdTipoAccion(Integer idTipoAccion) {
        this.idTipoAccion = idTipoAccion;
    }

    public Integer getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(Integer cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public LoteProcesoParametro getIdLoteProcesoParametro() {
        return idLoteProcesoParametro;
    }

    public void setIdLoteProcesoParametro(LoteProcesoParametro idLoteProcesoParametro) {
        this.idLoteProcesoParametro = idLoteProcesoParametro;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteProcesoParametroMedida != null ? idLoteProcesoParametroMedida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoteProcesoParametroMedida)) {
            return false;
        }
        LoteProcesoParametroMedida other = (LoteProcesoParametroMedida) object;
        if ((this.idLoteProcesoParametroMedida == null && other.idLoteProcesoParametroMedida != null) || (this.idLoteProcesoParametroMedida != null && !this.idLoteProcesoParametroMedida.equals(other.idLoteProcesoParametroMedida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LoteProcesoParametroMedida[ idLoteProcesoParametroMedida=" + idLoteProcesoParametroMedida + " ]";
    }
    
}
