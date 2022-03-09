
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

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "instrumento", catalog = "campiutti", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo"})})

public class Instrumento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInstrumento", nullable = false)
    private Integer idInstrumento;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    @Column(name = "marca", length = 30)
    private String marca;
    @Column(name = "serie", length = 30)
    private String serie;
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "idInstrumentoEstado")
    private Integer idInstrumentoEstado;
    @Column(name = "esDigital")
    private Boolean esDigital;
    @Column(name = "tieneSalida")
    private Boolean tieneSalida;
    @Column(name = "lugar", length = 200)
    private String lugar;
    @Column(name = "minimo1", precision = 22)
    private Double minimo1;
    @Column(name = "maximo1", precision = 22)
    private Double maximo1;
    @Column(name = "minimo2", precision = 22)
    private Double minimo2;
    @Column(name = "maximo2", precision = 22)
    private Double maximo2;
    @Column(name = "minimo3", precision = 22)
    private Double minimo3;
    @Column(name = "maximo3", precision = 22)
    private Double maximo3;
    @Column(name = "resolucion1", precision = 22)
    private Double resolucion1;
    @Column(name = "resolucion2", precision = 22)
    private Double resolucion2;
    @Column(name = "resolucion3", precision = 22)
    private Double resolucion3;

    @JoinColumn(name = "idInstrumentoTipo", referencedColumnName = "idInstrumentoTipo", nullable = false)
    @ManyToOne(optional = false)
    private InstrumentoTipo idInstrumentoTipo;

    @OneToMany(mappedBy = "idInstrumento6")
    private List<LoteProceso> loteProcesoList;
    @OneToMany(mappedBy = "idInstrumento5")
    private List<LoteProceso> loteProcesoList1;
    @OneToMany(mappedBy = "idInstrumento4")
    private List<LoteProceso> loteProcesoList2;
    @OneToMany(mappedBy = "idInstrumento3")
    private List<LoteProceso> loteProcesoList3;
    @OneToMany(mappedBy = "idInstrumento2")
    private List<LoteProceso> loteProcesoList4;
    @OneToMany(mappedBy = "idInstrumento1")
    private List<LoteProceso> loteProcesoList5;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstrumento")
    private List<Calibracion> calibracionList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstrumento")
    private List<InstrumentoEstabilidad>  instrumentoEstabilidadList;
    
    
    
    public Instrumento() {
    }

    public Instrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public Instrumento(Integer idInstrumento, int codigo) {
        this.idInstrumento = idInstrumento;
        this.codigo = codigo;
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdInstrumentoEstado() {
        return idInstrumentoEstado;
    }

    public void setIdInstrumentoEstado(Integer idInstrumentoEstado) {
        this.idInstrumentoEstado = idInstrumentoEstado;
    }

    public Boolean getEsDigital() {
        return esDigital;
    }

    public void setEsDigital(Boolean esDigital) {
        this.esDigital = esDigital;
    }

    public Boolean getTieneSalida() {
        return tieneSalida;
    }

    public void setTieneSalida(Boolean tieneSalida) {
        this.tieneSalida = tieneSalida;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Double getMinimo1() {
        return minimo1;
    }

    public void setMinimo1(Double minimo1) {
        this.minimo1 = minimo1;
    }

    public Double getMaximo1() {
        return maximo1;
    }

    public void setMaximo1(Double maximo1) {
        this.maximo1 = maximo1;
    }

    public Double getMinimo2() {
        return minimo2;
    }

    public void setMinimo2(Double minimo2) {
        this.minimo2 = minimo2;
    }

    public Double getMaximo2() {
        return maximo2;
    }

    public void setMaximo2(Double maximo2) {
        this.maximo2 = maximo2;
    }

    public Double getMinimo3() {
        return minimo3;
    }

    public void setMinimo3(Double minimo3) {
        this.minimo3 = minimo3;
    }

    public Double getMaximo3() {
        return maximo3;
    }

    public void setMaximo3(Double maximo3) {
        this.maximo3 = maximo3;
    }

    public Double getResolucion1() {
        return resolucion1;
    }

    public void setResolucion1(Double resolucion1) {
        this.resolucion1 = resolucion1;
    }

    public Double getResolucion2() {
        return resolucion2;
    }

    public void setResolucion2(Double resolucion2) {
        this.resolucion2 = resolucion2;
    }

    public Double getResolucion3() {
        return resolucion3;
    }

    public void setResolucion3(Double resolucion3) {
        this.resolucion3 = resolucion3;
    }

    public InstrumentoTipo getIdInstrumentoTipo() {
        return idInstrumentoTipo;
    }

    public void setIdInstrumentoTipo(InstrumentoTipo idInstrumentoTipo) {
        this.idInstrumentoTipo = idInstrumentoTipo;
    }


    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }

    public List<LoteProceso> getLoteProcesoList1() {
        return loteProcesoList1;
    }

    public void setLoteProcesoList1(List<LoteProceso> loteProcesoList1) {
        this.loteProcesoList1 = loteProcesoList1;
    }

    public List<LoteProceso> getLoteProcesoList2() {
        return loteProcesoList2;
    }

    public void setLoteProcesoList2(List<LoteProceso> loteProcesoList2) {
        this.loteProcesoList2 = loteProcesoList2;
    }

    public List<LoteProceso> getLoteProcesoList3() {
        return loteProcesoList3;
    }

    public void setLoteProcesoList3(List<LoteProceso> loteProcesoList3) {
        this.loteProcesoList3 = loteProcesoList3;
    }

    public List<LoteProceso> getLoteProcesoList4() {
        return loteProcesoList4;
    }

    public void setLoteProcesoList4(List<LoteProceso> loteProcesoList4) {
        this.loteProcesoList4 = loteProcesoList4;
    }

    public List<LoteProceso> getLoteProcesoList5() {
        return loteProcesoList5;
    }

    public void setLoteProcesoList5(List<LoteProceso> loteProcesoList5) {
        this.loteProcesoList5 = loteProcesoList5;
    }

    public List<Calibracion> getCalibracionList() {
        return calibracionList;
    }

    public void setCalibracionList(List<Calibracion> calibracionList) {
        this.calibracionList = calibracionList;
    }

    public List<InstrumentoEstabilidad> getInstrumentoEstabilidadList() {
        return instrumentoEstabilidadList;
    }

    public void setInstrumentoEstabilidadList(List<InstrumentoEstabilidad> instrumentoEstabilidadList) {
        this.instrumentoEstabilidadList = instrumentoEstabilidadList;
    }
    
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstrumento != null ? idInstrumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Instrumento)) {
            return false;
        }
        Instrumento other = (Instrumento) object;
        return (this.idInstrumento != null || other.idInstrumento == null) && (this.idInstrumento == null || this.idInstrumento.equals(other.idInstrumento));
    }

    @Override
    public String toString() {
//        if(nombre != null){
//            return codigo + " - " + nombre;
//        }
        return codigo + "";
        
    }

}
