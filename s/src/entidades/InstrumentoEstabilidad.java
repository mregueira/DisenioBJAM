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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "instrumentoEstabilidad", catalog = "campiutti", schema = "")
@XmlRootElement
public class InstrumentoEstabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInstrumentoEstabilidad", nullable = false)
    private Integer idInstrumentoEstabilidad;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "patron", nullable = false)
    private double patron;
    @Basic(optional = false)
    @Column(name = "medida1", nullable = false)
    private double medida1;
    @Basic(optional = false)
    @Column(name = "medida2", nullable = false)
    private double medida2;
    @Basic(optional = false)
    @Column(name = "medida3", nullable = false)
    private double medida3;
    
    @JoinColumn(name = "idInstrumento", referencedColumnName = "idInstrumento", nullable = false)
    @ManyToOne(optional = false)
    private Instrumento idInstrumento;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public InstrumentoEstabilidad() {
    }

    public InstrumentoEstabilidad(Integer idInstrumentoEstabilidad) {
        this.idInstrumentoEstabilidad = idInstrumentoEstabilidad;
    }

    public InstrumentoEstabilidad(Integer idInstrumentoEstabilidad, Date fecha, double patron, double medida1, double medida2, double medida3) {
        this.idInstrumentoEstabilidad = idInstrumentoEstabilidad;
        this.fecha = fecha;
        this.patron = patron;
        this.medida1 = medida1;
        this.medida2 = medida2;
        this.medida3 = medida3;
    }

    public Integer getIdInstrumentoEstabilidad() {
        return idInstrumentoEstabilidad;
    }

    public void setIdInstrumentoEstabilidad(Integer idInstrumentoEstabilidad) {
        this.idInstrumentoEstabilidad = idInstrumentoEstabilidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPatron() {
        return patron;
    }

    public void setPatron(double patron) {
        this.patron = patron;
    }

    public double getMedida1() {
        return medida1;
    }

    public void setMedida1(double medida1) {
        this.medida1 = medida1;
    }

    public double getMedida2() {
        return medida2;
    }

    public void setMedida2(double medida2) {
        this.medida2 = medida2;
    }

    public double getMedida3() {
        return medida3;
    }

    public void setMedida3(double medida3) {
        this.medida3 = medida3;
    }

    public Instrumento getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Instrumento idInstrumento) {
        this.idInstrumento = idInstrumento;
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
        hash += (idInstrumentoEstabilidad != null ? idInstrumentoEstabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InstrumentoEstabilidad)) {
            return false;
        }
        InstrumentoEstabilidad other = (InstrumentoEstabilidad) object;
        if ((this.idInstrumentoEstabilidad == null && other.idInstrumentoEstabilidad != null) || (this.idInstrumentoEstabilidad != null && !this.idInstrumentoEstabilidad.equals(other.idInstrumentoEstabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idInstrumento.getNombre();
    }
    
}
