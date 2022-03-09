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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "sugerencia", catalog = "campiutti", schema = "")
@XmlRootElement
public class Sugerencia implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSugerencia", nullable = false)
    private Integer idSugerencia;

    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Lob
    @Column(name = "nota", nullable = false, length = 65535)
    private String nota;
    
    @Basic(optional = false)
    @Column(name = "idEstado", nullable = false)
    private int idEstado;

    @Basic(optional = false)
    @Column(name = "cantidadDePersonal", nullable = false)
    private int cantidadDePersonal;
    
    @Column(name = "fechaCumplimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaCumplimiento;
    
    @JoinColumn(name = "idUsuarioReceptor", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuarioReceptor;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Sugerencia() {
    }

    public Sugerencia(Integer idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public Sugerencia(Integer idSugerencia, Date fecha, int idEstado) {
        this.idSugerencia = idSugerencia;
        this.fecha = fecha;
        this.idEstado = idEstado;
    }

    public Integer getIdSugerencia() {
        return idSugerencia;
    }

    public void setIdSugerencia(Integer idSugerencia) {
        this.idSugerencia = idSugerencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidadDePersonal() {
        return cantidadDePersonal;
    }

    public void setCantidadDePersonal(int cantidadDePersonal) {
        this.cantidadDePersonal = cantidadDePersonal;
    }
    
    

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaCumplimiento() {
        return fechaCumplimiento;
    }

    public void setFechaCumplimiento(Date fechaCumplimiento) {
        this.fechaCumplimiento = fechaCumplimiento;
    }

    public Usuario getIdUsuarioReceptor() {
        return idUsuarioReceptor;
    }

    public void setIdUsuarioReceptor(Usuario idUsuarioReceptor) {
        this.idUsuarioReceptor = idUsuarioReceptor;
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
        hash += (idSugerencia != null ? idSugerencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sugerencia)) {
            return false;
        }
        Sugerencia other = (Sugerencia) object;
        if ((this.idSugerencia == null && other.idSugerencia != null) || (this.idSugerencia != null && !this.idSugerencia.equals(other.idSugerencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Sugerencia[ idSugerencia=" + idSugerencia + " ]";
    }
    
}
