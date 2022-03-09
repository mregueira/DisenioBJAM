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
@Table(name = "instrumentoRango", catalog = "campiutti", schema = "")

public class InstrumentoRango implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInstrumentoRango", nullable = false)
    private Integer idInstrumentoRango;

    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name = "idCalibracionTipo")
    private Integer idCalibracionTipo;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "periodo")
    private Integer periodo;

    @Column(name = "periodoEstabilidad")
    private Integer periodoEstabilidad;


    @Column(name = "puntosAMedir")
    private Integer puntosAMedir;

    @Column(name = "n")
    private Integer n;

    @Column(name = "numeroDeRango")
    private Integer numeroDeRango;

    @JoinColumn(name = "idInstrumentoTipo", referencedColumnName = "idInstrumentoTipo", nullable = false)
    @ManyToOne(optional = false)
    private InstrumentoTipo idInstrumentoTipo;

    @JoinColumn(name = "idInstrumentoTipo1", referencedColumnName = "idInstrumentoTipo")
    @ManyToOne
    private InstrumentoTipo idInstrumentoTipo1;

    @JoinColumn(name = "idInstrumentoTipo2", referencedColumnName = "idInstrumentoTipo")
    @ManyToOne
    private InstrumentoTipo idInstrumentoTipo2;

    @JoinColumn(name = "idInstrumentoTipo3", referencedColumnName = "idInstrumentoTipo")
    @ManyToOne
    private InstrumentoTipo idInstrumentoTipo3;

    public InstrumentoRango() {
    }

    public InstrumentoRango(Integer idInstrumentoRango) {
        this.idInstrumentoRango = idInstrumentoRango;
    }

    public InstrumentoRango(Integer idInstrumentoRango, int codigo) {
        this.idInstrumentoRango = idInstrumentoRango;
        this.codigo = codigo;
    }

    public Integer getIdInstrumentoRango() {
        return idInstrumentoRango;
    }

    public void setIdInstrumentoRango(Integer idInstrumentoRango) {
        this.idInstrumentoRango = idInstrumentoRango;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getIdCalibracionTipo() {
        return idCalibracionTipo;
    }

    public void setIdCalibracionTipo(Integer idCalibracionTipo) {
        this.idCalibracionTipo = idCalibracionTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getPeriodoEstabilidad() {
        return periodoEstabilidad;
    }

    public void setPeriodoEstabilidad(Integer periodoEstabilidad) {
        this.periodoEstabilidad = periodoEstabilidad;
    }
    
    

    public Integer getPuntosAMedir() {
        return puntosAMedir;
    }

    public void setPuntosAMedir(Integer puntosAMedir) {
        this.puntosAMedir = puntosAMedir;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getNumeroDeRango() {
        return numeroDeRango;
    }

    public void setNumeroDeRango(Integer numeroDeRango) {
        this.numeroDeRango = numeroDeRango;
    }

    public InstrumentoTipo getIdInstrumentoTipo() {
        return idInstrumentoTipo;
    }

    public void setIdInstrumentoTipo(InstrumentoTipo idInstrumentoTipo) {
        this.idInstrumentoTipo = idInstrumentoTipo;
    }

    public InstrumentoTipo getIdInstrumentoTipo1() {
        return idInstrumentoTipo1;
    }

    public void setIdInstrumentoTipo1(InstrumentoTipo idInstrumentoTipo1) {
        this.idInstrumentoTipo1 = idInstrumentoTipo1;
    }

    public InstrumentoTipo getIdInstrumentoTipo2() {
        return idInstrumentoTipo2;
    }

    public void setIdInstrumentoTipo2(InstrumentoTipo idInstrumentoTipo2) {
        this.idInstrumentoTipo2 = idInstrumentoTipo2;
    }

    public InstrumentoTipo getIdInstrumentoTipo3() {
        return idInstrumentoTipo3;
    }

    public void setIdInstrumentoTipo3(InstrumentoTipo idInstrumentoTipo3) {
        this.idInstrumentoTipo3 = idInstrumentoTipo3;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstrumentoRango != null ? idInstrumentoRango.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof InstrumentoRango)) {
            return false;
        }
        InstrumentoRango other = (InstrumentoRango) object;
        return !((this.idInstrumentoRango == null && other.idInstrumentoRango != null) || (this.idInstrumentoRango != null && !this.idInstrumentoRango.equals(other.idInstrumentoRango)));
    }

    @Override
    public String toString() {
        return "entidades.InstrumentoRango[idInstrumentoRango=" + idInstrumentoRango + "]";
    }

}
