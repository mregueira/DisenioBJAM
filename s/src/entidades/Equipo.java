package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "equipo", catalog = "campiutti", schema = "")
@XmlRootElement

public class Equipo implements Serializable {

    @OneToMany(mappedBy = "idEquipo3")
    private List<Monitor> monitorList;

    @OneToMany(mappedBy = "idEquipo1")
    private List<Monitor> monitorList1;

    @OneToMany(mappedBy = "idEquipo2")
    private List<Monitor> monitorList2;

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEquipo", nullable = false)
    private Integer idEquipo;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 10)
    private String codigo;
    
    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @Column(name =     "fechaPlanMant")
    @Temporal(TemporalType.DATE)
    private Date fechaPlanMant;
    
    @Column(name =     "fechaEvaluacion")
    @Temporal(TemporalType.DATE)
    private Date fechaEvaluacion;

   
    @Column(name = "idEstado")
    private Integer idEstado;
    
    
    @Column(name = "edicionPlanMant")
    private Integer edicionPlanMant;
    
    @Lob
    @Column(name = "nota", length = 65535)
    private String nota;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", orphanRemoval=true)
    private List<EquipoMantenimiento> equipoMantenimientoList;
   
    @JoinColumn(name = "idUsuarioPlanMant", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuarioPlanMant;
    
    @JoinColumn(name = "idUsuarioEvaluacion", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuarioEvaluacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEquipo", orphanRemoval=true)
    private List<EquipoReparacion> equipoReparacionList;
    
    

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Equipo(Integer idEquipo, String codigo, int idEstado) {
        this.idEquipo = idEquipo;
        this.codigo = codigo;
        this.idEstado = idEstado;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    

    public Integer getEdicionPlanMant() {
        return edicionPlanMant;
    }

    public void setEdicionPlanMant(Integer edicionPlanMant) {
        this.edicionPlanMant = edicionPlanMant;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Usuario getIdUsuarioPlanMant() {
        return idUsuarioPlanMant;
    }

    public void setIdUsuarioPlanMant(Usuario idUsuarioPlanMant) {
        this.idUsuarioPlanMant = idUsuarioPlanMant;
    }

    public Usuario getIdUsuarioEvaluacion() {
        return idUsuarioEvaluacion;
    }

    public void setIdUsuarioEvaluacion(Usuario idUsuarioEvaluacion) {
        this.idUsuarioEvaluacion = idUsuarioEvaluacion;
    }

    @XmlTransient
    public List<EquipoMantenimiento> getEquipoMantenimientoList() {
        return equipoMantenimientoList;
    }

    public void setEquipoMantenimientoList(List<EquipoMantenimiento> equipoMantenimientoList) {
        this.equipoMantenimientoList = equipoMantenimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        return (this.idEquipo != null || other.idEquipo == null) && (this.idEquipo == null || this.idEquipo.equals(other.idEquipo));
    }

    @Override
    public String toString() {
        
        return codigo.trim() + " " + tipo.trim();
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Date getFechaPlanMant() {
        return fechaPlanMant;
    }

    public void setFechaPlanMant(Date fechaPlanMant) {
        this.fechaPlanMant = fechaPlanMant;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    @XmlTransient
    public List<Monitor> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<Monitor> monitorList) {
        this.monitorList = monitorList;
    }

    @XmlTransient
    public List<Monitor> getMonitorList1() {
        return monitorList1;
    }

    public void setMonitorList1(List<Monitor> monitorList1) {
        this.monitorList1 = monitorList1;
    }

    @XmlTransient
    public List<Monitor> getMonitorList2() {
        return monitorList2;
    }

    public void setMonitorList2(List<Monitor> monitorList2) {
        this.monitorList2 = monitorList2;
    }
   
}
