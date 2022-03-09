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

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "loteCota", catalog = "campiutti", schema = "")
public class LoteCota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteCota", nullable = false)
    private Integer idLoteCota;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "cambiarSigno")
    private Boolean cambiarSigno;
    
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteCota")
    private List<LoteMedida> loteMedidaList;
    
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

    @JoinColumn(name = "idLoteProceso", referencedColumnName = "idLoteProceso", nullable = false)
    @ManyToOne(optional = false)
    private LoteProceso idLoteProceso;

    @JoinColumn(name = "idPlanCotaTipo", referencedColumnName = "idPlanCotaTipo", nullable = false)
    @ManyToOne(optional = false)
    private PlanCotaTipo idPlanCotaTipo;
    
    
    public LoteCota() {
    }

    public LoteCota(Integer idLoteCota) {
        this.idLoteCota = idLoteCota;
    }

    public LoteCota(Integer idLoteCota, int codigo) {
        this.idLoteCota = idLoteCota;
        this.codigo = codigo;
    }

    public Integer getIdLoteCota() {
        return idLoteCota;
    }

    public void setIdLoteCota(Integer idLoteCota) {
        this.idLoteCota = idLoteCota;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public Boolean getCambiarSigno() {
        return cambiarSigno;
    }

    public void setCambiarSigno(Boolean cambiarSigno) {
        this.cambiarSigno = cambiarSigno;
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

    public List<LoteMedida> getLoteMedidaList() {
        return loteMedidaList;
    }

    public void setLoteMedidaList(List<LoteMedida> loteMedidaList) {
        this.loteMedidaList = loteMedidaList;
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

    public LoteProceso getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(LoteProceso idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    public PlanCotaTipo getIdPlanCotaTipo() {
	return idPlanCotaTipo;
    }

    public void setIdPlanCotaTipo(PlanCotaTipo idPlanCotaTipo) {
	this.idPlanCotaTipo = idPlanCotaTipo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteCota != null ? idLoteCota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LoteCota)) {
            return false;
        }
        LoteCota other = (LoteCota) object;
        return (this.idLoteCota != null || other.idLoteCota == null) && (this.idLoteCota == null || this.idLoteCota.equals(other.idLoteCota));
    }

    @Override
    public String toString() {
        if(nombre != null && tolerancia != null){
            return nombre.trim() + "  " + tolerancia.trim();
        }
        if(nombre != null){
            return nombre.trim();
        }
        if(tolerancia != null){
            return tolerancia.trim();
        }
        return "Sin definir cota ni tolerancia";
    }

}
