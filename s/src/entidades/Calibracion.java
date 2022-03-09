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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "calibracion", catalog = "campiutti", schema = "")
public class Calibracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCalibracion", nullable = false)
    private Integer idCalibracion;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fechaVence")
    @Temporal(TemporalType.DATE)
    private Date fechaVence;
    @Column(name = "esApto")
    private Boolean esApto;
    @Lob
    @Column(name = "nota", length = 2147483647)
    private String nota;
    @JoinColumn(name = "idInstrumento", referencedColumnName = "idInstrumento", nullable = false)
    @ManyToOne(optional = false)
    private Instrumento idInstrumento;


    public Calibracion() {
    }

    public Calibracion(Integer idCalibracion) {
        this.idCalibracion = idCalibracion;
    }

    public Integer getIdCalibracion() {
        return idCalibracion;
    }

    public void setIdCalibracion(Integer idCalibracion) {
        this.idCalibracion = idCalibracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public Boolean getEsApto() {
        return esApto;
    }

    public void setEsApto(Boolean esApto) {
        this.esApto = esApto;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Instrumento getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Instrumento idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalibracion != null ? idCalibracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Calibracion)) {
            return false;
        }
        Calibracion other = (Calibracion) object;
        return !((this.idCalibracion == null && other.idCalibracion != null) || (this.idCalibracion != null && !this.idCalibracion.equals(other.idCalibracion)));
    }

    @Override
    public String toString() {
        return "entidades.Calibracion[idCalibracion=" + idCalibracion + "]";
    }

}
