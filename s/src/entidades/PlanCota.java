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

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "planCota", catalog = "campiutti", schema = "")
public class PlanCota implements Serializable {
    @Column(name = "severidadIni")
    private Integer severidadIni;
    @Column(name = "ocurrenciaIni")
    private Integer ocurrenciaIni;
    @Column(name = "deteccionIni")
    private Integer deteccionIni;
    @Column(name = "severidadFin")
    private Integer severidadFin;
    @Column(name = "deteccionFin")
    private Integer deteccionFin;
    @Column(name = "ocurrenciaFin")
    private Integer ocurrenciaFin;
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)

    @Column(name = "idPlanCota", nullable = false)
    private Integer idPlanCota;
    
    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "idClase")
    private Integer idClase;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "tolerancia", length = 50)
    private String tolerancia;
    @Column(name = "idTipoAnalisisP")
    private Integer idTipoAnalisisP;
    @Column(name = "idRegistroP")
    private Integer idRegistroP;
    @Column(name = "muestraP")
    private Integer muestraP;
    @Column(name = "idTipoAnalisisC")
    private Integer idTipoAnalisisC;
    @Column(name = "idRegistroC")
    private Integer idRegistroC;
    @Column(name = "muestraC")
    private Integer muestraC;
    
    @JoinColumn(name = "idFrecuenciaC", referencedColumnName = "idFrecuencia")
    @ManyToOne
    private Frecuencia idFrecuenciaC;
    
    @JoinColumn(name = "idFrecuenciaP", referencedColumnName = "idFrecuencia")
    @ManyToOne
    private Frecuencia idFrecuenciaP;
    
    @JoinColumn(name = "idInstrumentoTipoC", referencedColumnName = "idInstrumentoTipo")
    @ManyToOne
    private InstrumentoTipo idInstrumentoTipoC;
    
    @JoinColumn(name = "idInstrumentoTipoP", referencedColumnName = "idInstrumentoTipo")
    @ManyToOne
    private InstrumentoTipo idInstrumentoTipoP;

    @JoinColumn(name = "idPlanCotaTipo", referencedColumnName = "idPlanCotaTipo")
    @ManyToOne
    private PlanCotaTipo idPlanCotaTipo;
    
    @JoinColumn(name = "idPlanProceso", referencedColumnName = "idPlanProceso", nullable = false)
    @ManyToOne(optional = false)
    private PlanProceso idPlanProceso;


    public PlanCota() {
    }

    public PlanCota(Integer idPlanCota) {
        this.idPlanCota = idPlanCota;
    }

    public Integer getIdPlanCota() {
        return idPlanCota;
    }

    public void setIdPlanCota(Integer idPlanCota) {
        this.idPlanCota = idPlanCota;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(String tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Integer getIdTipoAnalisisP() {
        return idTipoAnalisisP;
    }

    public void setIdTipoAnalisisP(Integer idTipoAnalisisP) {
        this.idTipoAnalisisP = idTipoAnalisisP;
    }

    public Integer getIdRegistroP() {
        return idRegistroP;
    }

    public void setIdRegistroP(Integer idRegistroP) {
        this.idRegistroP = idRegistroP;
    }

    public Integer getMuestraP() {
        return muestraP;
    }

    public void setMuestraP(Integer muestraP) {
        this.muestraP = muestraP;
    }

    public Integer getIdTipoAnalisisC() {
        return idTipoAnalisisC;
    }

    public void setIdTipoAnalisisC(Integer idTipoAnalisisC) {
        this.idTipoAnalisisC = idTipoAnalisisC;
    }

    public Integer getIdRegistroC() {
        return idRegistroC;
    }

    public void setIdRegistroC(Integer idRegistroC) {
        this.idRegistroC = idRegistroC;
    }

    public Integer getMuestraC() {
        return muestraC;
    }

    public void setMuestraC(Integer muestraC) {
        this.muestraC = muestraC;
    }


    public Frecuencia getIdFrecuenciaC() {
        return idFrecuenciaC;
    }

    public void setIdFrecuenciaC(Frecuencia idFrecuenciaC) {
        this.idFrecuenciaC = idFrecuenciaC;
    }

    public Frecuencia getIdFrecuenciaP() {
        return idFrecuenciaP;
    }

    public void setIdFrecuenciaP(Frecuencia idFrecuenciaP) {
        this.idFrecuenciaP = idFrecuenciaP;
    }

    public InstrumentoTipo getIdInstrumentoTipoC() {
        return idInstrumentoTipoC;
    }

    public void setIdInstrumentoTipoC(InstrumentoTipo idInstrumentoTipoC) {
        this.idInstrumentoTipoC = idInstrumentoTipoC;
    }

    public InstrumentoTipo getIdInstrumentoTipoP() {
        return idInstrumentoTipoP;
    }

    public void setIdInstrumentoTipoP(InstrumentoTipo idInstrumentoTipoP) {
        this.idInstrumentoTipoP = idInstrumentoTipoP;
    }

    public PlanCotaTipo getIdPlanCotaTipo() {
	return idPlanCotaTipo;
    }

    public void setIdPlanCotaTipo(PlanCotaTipo idPlanCotaTipo) {
	this.idPlanCotaTipo = idPlanCotaTipo;
    }


    public PlanProceso getIdPlanProceso() {
        return idPlanProceso;
    }

    public void setIdPlanProceso(PlanProceso idPlanProceso) {
        this.idPlanProceso = idPlanProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanCota != null ? idPlanCota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlanCota)) {
            return false;
        }
        PlanCota other = (PlanCota) object;
        return !((this.idPlanCota == null && other.idPlanCota != null) || (this.idPlanCota != null && !this.idPlanCota.equals(other.idPlanCota)));
    }



    @Override
    public String toString() {
        if(nombre != null && tolerancia != null){
            return nombre + " - " + tolerancia;
        }
        if(nombre != null){
            return nombre;
        }
        if(tolerancia != null){
            return tolerancia;
        }
        return "";
    }


    public Integer getSeveridadIni() {
        return severidadIni;
    }

    public void setSeveridadIni(Integer severidadIni) {
        this.severidadIni = severidadIni;
    }

    public Integer getOcurrenciaIni() {
        return ocurrenciaIni;
    }

    public void setOcurrenciaIni(Integer ocurrenciaIni) {
        this.ocurrenciaIni = ocurrenciaIni;
    }

    public Integer getDeteccionIni() {
        return deteccionIni;
    }

    public void setDeteccionIni(Integer deteccionIni) {
        this.deteccionIni = deteccionIni;
    }

    public Integer getSeveridadFin() {
        return severidadFin;
    }

    public void setSeveridadFin(Integer severidadFin) {
        this.severidadFin = severidadFin;
    }

    public Integer getDeteccionFin() {
        return deteccionFin;
    }

    public void setDeteccionFin(Integer deteccionFin) {
        this.deteccionFin = deteccionFin;
    }

    public Integer getOcurrenciaFin() {
        return ocurrenciaFin;
    }

    public void setOcurrenciaFin(Integer ocurrenciaFin) {
        this.ocurrenciaFin = ocurrenciaFin;
    }
    
}
