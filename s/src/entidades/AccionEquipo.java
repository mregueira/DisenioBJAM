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
@Table(name = "accionEquipo", catalog = "campiutti", schema = "")
@XmlRootElement
public class AccionEquipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccionEquipo", nullable = false)
    private Integer idAccionEquipo;
    @Column(name = "nombreExterno", length = 50)
    private String nombreExterno;
    @Column(name = "esResponsable")
    private Boolean esResponsable;
    @Column(name = "esDetector")
    private Boolean esDetector;
    @Column(name = "esLider")
    private Boolean esLider;


    @JoinColumn(name = "idAccion", referencedColumnName = "idAccion", nullable = false)
    @ManyToOne(optional = false)
    private Accion idAccion;

    
    @JoinColumn(name = "idExterno", referencedColumnName = "idExterno")
    @ManyToOne
    private Externo idExterno;


    public AccionEquipo() {
    }

    public AccionEquipo(Integer idAccionEquipo) {
        this.idAccionEquipo = idAccionEquipo;
    }

    public Integer getIdAccionEquipo() {
        return idAccionEquipo;
    }

    public void setIdAccionEquipo(Integer idAccionEquipo) {
        this.idAccionEquipo = idAccionEquipo;
    }

    public String getNombreExterno() {
        return nombreExterno;
    }

    public void setNombreExterno(String nombreExterno) {
        this.nombreExterno = nombreExterno;
    }

    public Boolean getEsResponsable() {
        return esResponsable;
    }

    public void setEsResponsable(Boolean esResponsable) {
        this.esResponsable = esResponsable;
    }

    public Boolean getEsDetector() {
        return esDetector;
    }

    public void setEsDetector(Boolean esDetector) {
        this.esDetector = esDetector;
    }

    public Boolean getEsLider() {
        return esLider;
    }

    public void setEsLider(Boolean esLider) {
        this.esLider = esLider;
    }


    public Accion getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Accion idAccion) {
        this.idAccion = idAccion;
    }


    public Externo getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Externo idExterno) {
        this.idExterno = idExterno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccionEquipo != null ? idAccionEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AccionEquipo)) {
            return false;
        }
        AccionEquipo other = (AccionEquipo) object;
        return !((this.idAccionEquipo == null && other.idAccionEquipo != null) || (this.idAccionEquipo != null && !this.idAccionEquipo.equals(other.idAccionEquipo)));
    }

    @Override
    public String toString() {
        return "entidades.AccionEquipo[ idAccionEquipo=" + idAccionEquipo + " ]";
    }
    
}
