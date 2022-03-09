package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "stockUbicacion", catalog = "campiutti", schema = "")
public class StockUbicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "activa")
    private Boolean activa;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStockUbicacion", nullable = false)
    private Integer idStockUbicacion;
    
    @Column(name = "nombre", length = 30)
    private String nombre;
    
    
    @JoinColumn(name = "idEquipo", referencedColumnName = "idEquipo")
    @ManyToOne
    private Equipo idEquipo;
    
    @JoinColumn(name = "idStockAlmacen", referencedColumnName = "idStockAlmacen", nullable = false)
    @ManyToOne(optional = false)
    private StockAlmacen idStockAlmacen;
    
    @OneToMany(mappedBy = "idStockUbicacion")
    private List<LoteProceso> loteProcesoList;
    


    public StockUbicacion() {
    }

    public StockUbicacion(Integer idStockUbicacion) {
        this.idStockUbicacion = idStockUbicacion;
    }

    public Integer getIdStockUbicacion() {
        return idStockUbicacion;
    }

    public void setIdStockUbicacion(Integer idStockUbicacion) {
        this.idStockUbicacion = idStockUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    


    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }


    public StockAlmacen getIdStockAlmacen() {
        return idStockAlmacen;
    }

    public void setIdStockAlmacen(StockAlmacen idStockAlmacen) {
        this.idStockAlmacen = idStockAlmacen;
    }

    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStockUbicacion != null ? idStockUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StockUbicacion)) {
            return false;
        }
        StockUbicacion other = (StockUbicacion) object;
        return (this.idStockUbicacion != null || other.idStockUbicacion == null) && (this.idStockUbicacion == null || this.idStockUbicacion.equals(other.idStockUbicacion));
    }
}
