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
@Table(name = "equipoReparacion", catalog = "campiutti", schema = "")
@XmlRootElement
public class EquipoReparacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEquipoReparacion", nullable = false)
    private Integer idEquipoReparacion;
    @Lob
    @Column(name = "notaFalla", length = 65535)
    private String notaFalla;
    @Lob
    @Column(name = "notaReparacion", length = 65535)
    private String notaReparacion;
    @Lob
    @Column(name = "notaMejoras", length = 65535)
    private String notaMejoras;
    @Column(name = "fechaFalla")
    @Temporal(TemporalType.DATE)
    private Date fechaFalla;
    @Column(name = "fechaParada")
    @Temporal(TemporalType.DATE)
    private Date fechaParada;
    @Column(name = "fechaMarcha")
    @Temporal(TemporalType.DATE)
    private Date fechaMarcha;
    @Column(name = "horasParada")
    private Integer horasParada;
    @Basic(optional = false)
    @Column(name = "idCorreccionTipo", nullable = false)
    private int idCorreccionTipo;
    @Lob
    @Column(name = "notaSeguridad", length = 65535)
    private String notaSeguridad;
    @Basic(optional = false)
    @Column(name = "idEstado", nullable = false)
    private int idEstado;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo", nullable = false)
    @ManyToOne(optional = false)
    private Equipo idEquipo;

    public EquipoReparacion() {
    }

    public EquipoReparacion(Integer idEquipoReparacion) {
        this.idEquipoReparacion = idEquipoReparacion;
    }

    public EquipoReparacion(Integer idEquipoReparacion, int idCorreccionTipo, int idEstado) {
        this.idEquipoReparacion = idEquipoReparacion;
        this.idCorreccionTipo = idCorreccionTipo;
        this.idEstado = idEstado;
    }

    public Integer getIdEquipoReparacion() {
        return idEquipoReparacion;
    }

    public void setIdEquipoReparacion(Integer idEquipoReparacion) {
        this.idEquipoReparacion = idEquipoReparacion;
    }

    public String getNotaFalla() {
        return notaFalla;
    }

    public void setNotaFalla(String notaFalla) {
        this.notaFalla = notaFalla;
    }

    public String getNotaReparacion() {
        return notaReparacion;
    }

    public void setNotaReparacion(String notaReparacion) {
        this.notaReparacion = notaReparacion;
    }

    public String getNotaMejoras() {
        return notaMejoras;
    }

    public void setNotaMejoras(String notaMejoras) {
        this.notaMejoras = notaMejoras;
    }

    public Date getFechaFalla() {
        return fechaFalla;
    }

    public void setFechaFalla(Date fechaFalla) {
        this.fechaFalla = fechaFalla;
    }

    public Date getFechaParada() {
        return fechaParada;
    }

    public void setFechaParada(Date fechaParada) {
        this.fechaParada = fechaParada;
    }

    public Date getFechaMarcha() {
        return fechaMarcha;
    }

    public void setFechaMarcha(Date fechaMarcha) {
        this.fechaMarcha = fechaMarcha;
    }

    public Integer getHorasParada() {
        return horasParada;
    }

    public void setHorasParada(Integer horasParada) {
        this.horasParada = horasParada;
    }

    public int getIdCorreccionTipo() {
        return idCorreccionTipo;
    }

    public void setIdCorreccionTipo(int idCorreccionTipo) {
        this.idCorreccionTipo = idCorreccionTipo;
    }

    public String getNotaSeguridad() {
        return notaSeguridad;
    }

    public void setNotaSeguridad(String notaSeguridad) {
        this.notaSeguridad = notaSeguridad;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipoReparacion != null ? idEquipoReparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoReparacion)) {
            return false;
        }
        EquipoReparacion other = (EquipoReparacion) object;
        return !((this.idEquipoReparacion == null && other.idEquipoReparacion != null) || (this.idEquipoReparacion != null && !this.idEquipoReparacion.equals(other.idEquipoReparacion)));
    }

    @Override
    public String toString() {
        return "entidades.EquipoReparacion[ idEquipoReparacion=" + idEquipoReparacion + " ]";
    }
    
}
