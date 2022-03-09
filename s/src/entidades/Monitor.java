package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "monitor", catalog = "campiutti", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ip"})})
@XmlRootElement
public class Monitor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMonitor", nullable = false)
    private Integer idMonitor;
    @Basic(optional = false)
    @Column(name = "ip", nullable = false, length = 13)
    private String ip;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @JoinColumn(name = "idInstrumento1", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento1;
    @JoinColumn(name = "idMonitorProcesoEnMedicion", referencedColumnName = "idMonitorProceso")
    @ManyToOne
    private MonitorProceso idMonitorProcesoEnMedicion;
    @JoinColumn(name = "idEquipo1", referencedColumnName = "idEquipo")
    @ManyToOne
    private Equipo idEquipo1;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "idEquipo2", referencedColumnName = "idEquipo")
    @ManyToOne
    private Equipo idEquipo2;
    @JoinColumn(name = "idInstrumento8", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento8;
    @JoinColumn(name = "idEquipo3", referencedColumnName = "idEquipo")
    @ManyToOne
    private Equipo idEquipo3;
    @JoinColumn(name = "idInstrumento2", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento2;
    @JoinColumn(name = "idInstrumento3", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento3;
    @JoinColumn(name = "idInstrumento4", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento4;
    @JoinColumn(name = "idInstrumento5", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento5;
    @JoinColumn(name = "idInstrumento6", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento6;
    @JoinColumn(name = "idInstrumento7", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento7;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMonitor")
    private List<MonitorProceso> monitorProcesoList;

    public Monitor() {
    }

    public Monitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    public Monitor(Integer idMonitor, String ip, String nombre) {
        this.idMonitor = idMonitor;
        this.ip = ip;
        this.nombre = nombre;
    }

    public Integer getIdMonitor() {
        return idMonitor;
    }

    public void setIdMonitor(Integer idMonitor) {
        this.idMonitor = idMonitor;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Instrumento getIdInstrumento1() {
        return idInstrumento1;
    }

    public void setIdInstrumento1(Instrumento idInstrumento1) {
        this.idInstrumento1 = idInstrumento1;
    }

    public MonitorProceso getIdMonitorProcesoEnMedicion() {
        return idMonitorProcesoEnMedicion;
    }

    public void setIdMonitorProcesoEnMedicion(MonitorProceso idMonitorProcesoEnMedicion) {
        this.idMonitorProcesoEnMedicion = idMonitorProcesoEnMedicion;
    }

    public Equipo getIdEquipo1() {
        return idEquipo1;
    }

    public void setIdEquipo1(Equipo idEquipo1) {
        this.idEquipo1 = idEquipo1;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Equipo getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(Equipo idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }

    public Instrumento getIdInstrumento8() {
        return idInstrumento8;
    }

    public void setIdInstrumento8(Instrumento idInstrumento8) {
        this.idInstrumento8 = idInstrumento8;
    }

    public Equipo getIdEquipo3() {
        return idEquipo3;
    }

    public void setIdEquipo3(Equipo idEquipo3) {
        this.idEquipo3 = idEquipo3;
    }

    public Instrumento getIdInstrumento2() {
        return idInstrumento2;
    }

    public void setIdInstrumento2(Instrumento idInstrumento2) {
        this.idInstrumento2 = idInstrumento2;
    }

    public Instrumento getIdInstrumento3() {
        return idInstrumento3;
    }

    public void setIdInstrumento3(Instrumento idInstrumento3) {
        this.idInstrumento3 = idInstrumento3;
    }

    public Instrumento getIdInstrumento4() {
        return idInstrumento4;
    }

    public void setIdInstrumento4(Instrumento idInstrumento4) {
        this.idInstrumento4 = idInstrumento4;
    }

    public Instrumento getIdInstrumento5() {
        return idInstrumento5;
    }

    public void setIdInstrumento5(Instrumento idInstrumento5) {
        this.idInstrumento5 = idInstrumento5;
    }

    public Instrumento getIdInstrumento6() {
        return idInstrumento6;
    }

    public void setIdInstrumento6(Instrumento idInstrumento6) {
        this.idInstrumento6 = idInstrumento6;
    }

    public Instrumento getIdInstrumento7() {
        return idInstrumento7;
    }

    public void setIdInstrumento7(Instrumento idInstrumento7) {
        this.idInstrumento7 = idInstrumento7;
    }

    @XmlTransient
    public List<MonitorProceso> getMonitorProcesoList() {
        return monitorProcesoList;
    }

    public void setMonitorProcesoList(List<MonitorProceso> monitorProcesoList) {
        this.monitorProcesoList = monitorProcesoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMonitor != null ? idMonitor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        return !((this.idMonitor == null && other.idMonitor != null) || (this.idMonitor != null && !this.idMonitor.equals(other.idMonitor)));
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
