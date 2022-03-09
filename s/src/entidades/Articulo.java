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
@Table(name = "articulo", catalog = "campiutti", schema = "")
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArticulo", nullable = false)
    private Integer idArticulo;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false, length = 30)
    private String codigo;

    @Column(name = "activo")
    private Boolean activo;
    
    @Column(name = "nombre", length = 40, nullable = false)
    private String nombre;
    
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticulo", orphanRemoval=true)
    private List<Lote> loteList;

    @JoinColumn(name = "idArticuloTipo", referencedColumnName = "idArticuloTipo", nullable = false)
    @ManyToOne(optional = false)
    private ArticuloTipo idArticuloTipo;
    
    public Articulo() {
    }

    public Articulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Articulo(Integer idArticulo, String codigo) {
        this.idArticulo = idArticulo;
        this.codigo = codigo;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    
    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    


    public ArticuloTipo getIdArticuloTipo() {
        return idArticuloTipo;
    }

    public void setIdArticuloTipo(ArticuloTipo idArticuloTipo) {
        this.idArticuloTipo = idArticuloTipo;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticulo != null ? idArticulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        return (this.idArticulo != null || other.idArticulo == null) && (this.idArticulo == null || this.idArticulo.equals(other.idArticulo));
    }

    @Override
    public String toString() {
        if(codigo != null && nombre != null){
            return codigo.trim() + " - " + nombre.trim();
        }
        if(nombre != null){
            return nombre.trim();
        }
        if(codigo != null){
            return codigo.trim();
        }
        return "";

    }

}
