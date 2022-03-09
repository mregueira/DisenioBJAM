/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "instrumentoTipo", catalog = "campiutti", schema = "")
public class InstrumentoTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInstrumentoTipo", nullable = false)
    private Integer idInstrumentoTipo;
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 5)
    private String codigo;
    
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Lob
    @Column(name = "notaCalibracion", length = 65535)
    private String notaCalibracion;
    @Lob
    @Column(name = "notaUso", length = 65535)
    private String notaUso;
    @Column(name = "incertidumbre1", precision = 22)
    private Double incertidumbre1;
    @Column(name = "incertidumbre2", precision = 22)
    private Double incertidumbre2;
    @Column(name = "incertidumbre3", precision = 22)
    private Double incertidumbre3;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstrumentoTipo")
    private List<Instrumento> instrumentoList;

    @OneToMany(mappedBy = "idInstrumentoTipoC")
    private List<LoteCota> loteCotaList;
    @OneToMany(mappedBy = "idInstrumentoTipoP")
    private List<LoteCota> loteCotaList1;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstrumentoTipo")
    private List<InstrumentoRango> instrumentoRangoList;

    @OneToMany(mappedBy = "idInstrumentoTipo1")
    private List<InstrumentoRango> instrumentoRangoList1;
    @OneToMany(mappedBy = "idInstrumentoTipo2")
    private List<InstrumentoRango> instrumentoRangoList2;
    @OneToMany(mappedBy = "idInstrumentoTipo3")
    private List<InstrumentoRango> instrumentoRangoList3;
    


    public InstrumentoTipo() {
    }

    public InstrumentoTipo(Integer idInstrumentoTipo) {
        this.idInstrumentoTipo = idInstrumentoTipo;
    }

    public InstrumentoTipo(Integer idInstrumentoTipo, String codigo, String nombre) {
        this.idInstrumentoTipo = idInstrumentoTipo;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdInstrumentoTipo() {
        return idInstrumentoTipo;
    }

    public void setIdInstrumentoTipo(Integer idInstrumentoTipo) {
        this.idInstrumentoTipo = idInstrumentoTipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNotaCalibracion() {
        return notaCalibracion;
    }

    public void setNotaCalibracion(String notaCalibracion) {
        this.notaCalibracion = notaCalibracion;
    }

    public String getNotaUso() {
        return notaUso;
    }

    public void setNotaUso(String notaUso) {
        this.notaUso = notaUso;
    }

    public Double getIncertidumbre1() {
        return incertidumbre1;
    }

    public void setIncertidumbre1(Double incertidumbre1) {
        this.incertidumbre1 = incertidumbre1;
    }

    public Double getIncertidumbre2() {
        return incertidumbre2;
    }

    public void setIncertidumbre2(Double incertidumbre2) {
        this.incertidumbre2 = incertidumbre2;
    }

    public Double getIncertidumbre3() {
        return incertidumbre3;
    }

    public void setIncertidumbre3(Double incertidumbre3) {
        this.incertidumbre3 = incertidumbre3;
    }


    public List<Instrumento> getInstrumentoList() {
        return instrumentoList;
    }

    public void setInstrumentoList(List<Instrumento> instrumentoList) {
        this.instrumentoList = instrumentoList;
    }


    public List<LoteCota> getLoteCotaList() {
        return loteCotaList;
    }

    public void setLoteCotaList(List<LoteCota> loteCotaList) {
        this.loteCotaList = loteCotaList;
    }

    public List<LoteCota> getLoteCotaList1() {
        return loteCotaList1;
    }

    public void setLoteCotaList1(List<LoteCota> loteCotaList1) {
        this.loteCotaList1 = loteCotaList1;
    }


    public List<InstrumentoRango> getInstrumentoRangoList() {
        return instrumentoRangoList;
    }

    public void setInstrumentoRangoList(List<InstrumentoRango> instrumentoRangoList) {
        this.instrumentoRangoList = instrumentoRangoList;
    }

    public List<InstrumentoRango> getInstrumentoRangoList1() {
        return instrumentoRangoList1;
    }

    public void setInstrumentoRangoList1(List<InstrumentoRango> instrumentoRangoList1) {
        this.instrumentoRangoList1 = instrumentoRangoList1;
    }

    public List<InstrumentoRango> getInstrumentoRangoList2() {
        return instrumentoRangoList2;
    }

    public void setInstrumentoRangoList2(List<InstrumentoRango> instrumentoRangoList2) {
        this.instrumentoRangoList2 = instrumentoRangoList2;
    }

    public List<InstrumentoRango> getInstrumentoRangoList3() {
        return instrumentoRangoList3;
    }

    public void setInstrumentoRangoList3(List<InstrumentoRango> instrumentoRangoList3) {
        this.instrumentoRangoList3 = instrumentoRangoList3;
    }
    
    
    
    
    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstrumentoTipo != null ? idInstrumentoTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InstrumentoTipo)) {
            return false;
        }
        InstrumentoTipo other = (InstrumentoTipo) object;
        return (this.idInstrumentoTipo != null || other.idInstrumentoTipo == null) && (this.idInstrumentoTipo == null || this.idInstrumentoTipo.equals(other.idInstrumentoTipo));
    }

    @Override
    public String toString() {
        return nombre;
    }

}
