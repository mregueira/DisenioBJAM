package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "frecuencia", catalog = "campiutti", schema = "")
public class Frecuencia implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFrecuencia", nullable = false)
    private Integer idFrecuencia;

    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 3)
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "segundos")
    private Integer segundos;
    @Column(name = "tolerancia")
    private Integer tolerancia;

    @OneToMany(mappedBy = "idFrecuenciaC")
    private List<LoteCota> loteCotaList;

    @OneToMany(mappedBy = "idFrecuenciaP")
    private List<LoteCota> loteCotaList2;

    public Frecuencia() {
    }

    public Frecuencia(Integer idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
    }

    public Frecuencia(Integer idFrecuencia, String codigo, String nombre) {
        this.idFrecuencia = idFrecuencia;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Integer getIdFrecuencia() {
        return idFrecuencia;
    }

    public void setIdFrecuencia(Integer idFrecuencia) {
        this.idFrecuencia = idFrecuencia;
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

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }


    public List<LoteCota> getLoteCotaList() {
        return loteCotaList;
    }

    public void setLoteCotaList(List<LoteCota> loteCotaList) {
        this.loteCotaList = loteCotaList;
    }


    public List<LoteCota> getLoteCotaList2() {
        return loteCotaList2;
    }

    public void setLoteCotaList2(List<LoteCota> loteCotaList2) {
        this.loteCotaList2 = loteCotaList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFrecuencia != null ? idFrecuencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Frecuencia)) {
            return false;
        }
        Frecuencia other = (Frecuencia) object;
        return (this.idFrecuencia != null || other.idFrecuencia == null) && (this.idFrecuencia == null || this.idFrecuencia.equals(other.idFrecuencia));
    }

    @Override
    public String toString() {
        return nombre.trim();
    }

}
