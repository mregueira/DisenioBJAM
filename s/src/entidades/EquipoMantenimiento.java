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
@Table(name = "equipoMantenimiento", catalog = "campiutti", schema = "")
@XmlRootElement
public class EquipoMantenimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEquipoMantenimiento", nullable = false)
    private Integer idEquipoMantenimiento;
    @Basic(optional = false)
    @Column(name = "idTipo", nullable = false)
    private int idTipo;
    @Basic(optional = false)
    @Column(name = "idNivel", nullable = false)
    private int idNivel;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "periodoEnDias")
    private Integer periodoEnDias;
    @Column(name = "fechaControl")
    @Temporal(TemporalType.DATE)
    private Date fechaControl;
    @Lob
    @Column(name = "nota", length = 65535)
    private String nota;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo", nullable = false)
    @ManyToOne(optional = false)
    private Equipo idEquipo;

    public EquipoMantenimiento() {
    }

    public EquipoMantenimiento(Integer idEquipoMantenimiento) {
        this.idEquipoMantenimiento = idEquipoMantenimiento;
    }

    public EquipoMantenimiento(Integer idEquipoMantenimiento, int idTipo, int idNivel) {
        this.idEquipoMantenimiento = idEquipoMantenimiento;
        this.idTipo = idTipo;
        this.idNivel = idNivel;
    }

    public Integer getIdEquipoMantenimiento() {
        return idEquipoMantenimiento;
    }

    public void setIdEquipoMantenimiento(Integer idEquipoMantenimiento) {
        this.idEquipoMantenimiento = idEquipoMantenimiento;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeriodoEnDias() {
        return periodoEnDias;
    }

    public void setPeriodoEnDias(Integer periodoEnDias) {
        this.periodoEnDias = periodoEnDias;
    }

    public Date getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(Date fechaControl) {
        this.fechaControl = fechaControl;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
        hash += (idEquipoMantenimiento != null ? idEquipoMantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoMantenimiento)) {
            return false;
        }
        EquipoMantenimiento other = (EquipoMantenimiento) object;
        if ((this.idEquipoMantenimiento == null && other.idEquipoMantenimiento != null) || (this.idEquipoMantenimiento != null && !this.idEquipoMantenimiento.equals(other.idEquipoMantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.EquipoMantenimiento[ idEquipoMantenimiento=" + idEquipoMantenimiento + " ]";
    }
    
}
