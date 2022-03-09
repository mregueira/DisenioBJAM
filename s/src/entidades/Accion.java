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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import servicios.ServicioEntidad;
import servicios.ServicioString;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "accion", catalog = "campiutti", schema = "")
public class Accion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAccion", nullable = false)
    private Integer idAccion;
    
    @Column(name = "codigo")
    private Integer codigo;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    @Basic(optional = false)
    @Column(name = "cerrada", nullable = false)
    private boolean cerrada;
    
    @Column(name = "fechaDeCierre")
    @Temporal(TemporalType.DATE)
    private Date fechaDeCierre;
    
    @Basic(optional = false)
    @Column(name = "esCorrectiva", nullable = false)
    private boolean esCorrectiva;
    @Basic(optional = false)
    @Column(name = "esPreventiva", nullable = false)
    private boolean esPreventiva;
    

    @Basic(optional = false)
    @Column(name = "origen", nullable = false, length = 30)
    private String origen;


    @Column(name = "cantidadRechazoCliente")
    private Integer cantidadRechazoCliente;

    @Column(name = "cantidadRechazoProveedor")
    private Integer cantidadRechazoProveedor;

    @Column(name = "costoFleteCliente")
    private Integer costoFleteCliente;

    @Column(name = "costoFleteProveedor")
    private Integer costoFleteProveedor;
    
    @Column(name = "informeAsociado", length = 50)
    private String informeAsociado;

    @Lob
    @Column(name = "notaPreventiva", length = 65535)
    private String notaPreventiva;
    @Lob
    @Column(name = "notaNoConformidad", length = 65535)
    private String notaNoConformidad;
    @Lob
    @Column(name = "notaInmediata", length = 65535)
    private String notaInmediata;
    @Lob
    @Column(name = "notaCausa", length = 65535)
    private String notaCausa;
    @Lob
    @Column(name = "notaCorrectiva", length = 65535)
    private String notaCorrectiva;
    @Lob
    @Column(name = "notaSeguimiento", length = 65535)
    private String notaSeguimiento;

    @Lob
    @Column(name = "notaEfectividad", length = 65535)
    private String notaEfectividad;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAccion", orphanRemoval=true)
    private List<AccionEquipo> accionEquipoList;

    
    public String getOrigen() {
        int largo = ServicioEntidad.getColumnLength(Accion.class, "origen");        
        return ServicioString.padR(origen, largo, "0");
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    


    public Accion() {
    }

    public Accion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public Integer getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Integer idAccion) {
        this.idAccion = idAccion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }



    public boolean getEsCorrectiva() {
        return esCorrectiva;
    }

    public void setEsCorrectiva(boolean esCorrectiva) {
        this.esCorrectiva = esCorrectiva;
    }

    public boolean getEsPreventiva() {
        return esPreventiva;
    }

    public void setEsPreventiva(boolean esPreventiva) {
        this.esPreventiva = esPreventiva;
    }

    
    
    public String getInformeAsociado() {
        return informeAsociado;
    }

    public void setInformeAsociado(String informeAsociado) {
        this.informeAsociado = informeAsociado;
    }

    public String getNotaPreventiva() {
        return notaPreventiva;
    }

    public void setNotaPreventiva(String notaPreventiva) {
        this.notaPreventiva = notaPreventiva;
    }

    public String getNotaNoConformidad() {
        return notaNoConformidad;
    }

    public void setNotaNoConformidad(String notaNoConformidad) {
        this.notaNoConformidad = notaNoConformidad;
    }

    public String getNotaInmediata() {
        return notaInmediata;
    }

    public void setNotaInmediata(String notaInmediata) {
        this.notaInmediata = notaInmediata;
    }

    public String getNotaCausa() {
        return notaCausa;
    }

    public void setNotaCausa(String notaCausa) {
        this.notaCausa = notaCausa;
    }

    public String getNotaCorrectiva() {
        return notaCorrectiva;
    }

    public void setNotaCorrectiva(String notaCorrectiva) {
        this.notaCorrectiva = notaCorrectiva;
    }

    public String getNotaSeguimiento() {
        return notaSeguimiento;
    }

    public void setNotaSeguimiento(String notaSeguimiento) {
        this.notaSeguimiento = notaSeguimiento;
    }

    public String getNotaEfectividad() {
        return notaEfectividad;
    }

    public void setNotaEfectividad(String notaEfectividad) {
        this.notaEfectividad = notaEfectividad;
    }

    public List<AccionEquipo> getAccionEquipoList() {
        return accionEquipoList;
    }

    public void setAccionEquipoList(List<AccionEquipo> accionEquipoList) {
        this.accionEquipoList = accionEquipoList;
    }

    public Integer getCantidadRechazoCliente() {
        return cantidadRechazoCliente;
    }

    public void setCantidadRechazoCliente(Integer cantidadRechazoCliente) {
        this.cantidadRechazoCliente = cantidadRechazoCliente;
    }

    public Integer getCantidadRechazoProveedor() {
        return cantidadRechazoProveedor;
    }

    public void setCantidadRechazoProveedor(Integer cantidadRechazoProveedor) {
        this.cantidadRechazoProveedor = cantidadRechazoProveedor;
    }

    public Integer getCostoFleteCliente() {
        return costoFleteCliente;
    }

    public void setCostoFleteCliente(Integer costoFleteCliente) {
        this.costoFleteCliente = costoFleteCliente;
    }

    public Integer getCostoFleteProveedor() {
        return costoFleteProveedor;
    }

    public void setCostoFleteProveedor(Integer costoFleteProveedor) {
        this.costoFleteProveedor = costoFleteProveedor;
    }

    
    
    



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAccion != null ? idAccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Accion)) {
            return false;
        }
        Accion other = (Accion) object;
        return !((this.idAccion == null && other.idAccion != null) || (this.idAccion != null && !this.idAccion.equals(other.idAccion)));
    }

    @Override
    public String toString() {
        return "Acción " + codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getCerrada() {
        return cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public Date getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(Date fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
