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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "stockAlmacen", catalog = "campiutti", schema = "")
public class StockAlmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStockAlmacen", nullable = false)
    private Integer idStockAlmacen;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "idStockAlmacenTipo")
    private Integer idStockAlmacenTipo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idStockAlmacen")
    private List<StockUbicacion> stockUbicacionList;
    
    @OneToMany(mappedBy = "idStockAlmacen")
    private List<LoteProceso> loteProcesoList;
    

    public StockAlmacen() {
    }

    public StockAlmacen(Integer idStockAlmacen) {
        this.idStockAlmacen = idStockAlmacen;
    }

    public Integer getIdStockAlmacen() {
        return idStockAlmacen;
    }

    public void setIdStockAlmacen(Integer idStockAlmacen) {
        this.idStockAlmacen = idStockAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdStockAlmacenTipo() {
        return idStockAlmacenTipo;
    }

    public void setIdStockAlmacenTipo(Integer idStockAlmacenTipo) {
        this.idStockAlmacenTipo = idStockAlmacenTipo;
    }

    public List<StockUbicacion> getStockUbicacionList() {
        return stockUbicacionList;
    }

    public void setStockUbicacionList(List<StockUbicacion> stockUbicacionList) {
        this.stockUbicacionList = stockUbicacionList;
    }

    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStockAlmacen != null ? idStockAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StockAlmacen)) {
            return false;
        }
        StockAlmacen other = (StockAlmacen) object;
        return (this.idStockAlmacen != null || other.idStockAlmacen == null) && (this.idStockAlmacen == null || this.idStockAlmacen.equals(other.idStockAlmacen));
    }

    @Override
    public String toString() {
        if(nombre == null){
            return "";
        }
        return nombre.trim();
    }

}
