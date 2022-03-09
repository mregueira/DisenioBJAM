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
@Table(name = "articuloTipo", catalog = "campiutti", schema = "")
public class ArticuloTipo implements Serializable {
    @Basic(optional = false)
    @Column(name = "idTipo", nullable = false)
    private int idTipo;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArticuloTipo", nullable = false)
    private Integer idArticuloTipo;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "usaICPM")
    private Boolean usaICPM;
    
    @Column(name = "uRelacion", length = 20)
    private String uRelacion;
    
    @Column(name = "uRelacionPlural", length = 20)
    private String uRelacionPlural;
    
    @Column(name = "uManipuleo", length = 20)
    private String uManipuleo;
    
    @Column(name = "uManipuleoPlural", length = 20)
    private String uManipuleoPlural;
    
    @Column(name = "uPedido", length = 20)
    private String uPedido;
    
    @Column(name = "uPedidoPlural", length = 20)
    private String uPedidoPlural;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticuloTipo", orphanRemoval=true)
    private List<Articulo> articuloList;

    public ArticuloTipo() {
    }

    public ArticuloTipo(Integer idArticuloTipo) {
        this.idArticuloTipo = idArticuloTipo;
    }

    public ArticuloTipo(Integer idArticuloTipo, String nombre) {
        this.idArticuloTipo = idArticuloTipo;
        this.nombre = nombre;
    }

    public Integer getIdArticuloTipo() {
        return idArticuloTipo;
    }

    public void setIdArticuloTipo(Integer idArticuloTipo) {
        this.idArticuloTipo = idArticuloTipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getUsaICPM() {
        return usaICPM;
    }

    public void setUsaICPM(Boolean usaICPM) {
        this.usaICPM = usaICPM;
    }

    public String getURelacion() {
        return uRelacion;
    }

    public void setURelacion(String uRelacion) {
        this.uRelacion = uRelacion;
    }

    public String getURelacionPlural() {
        return uRelacionPlural;
    }

    public void setURelacionPlural(String uRelacionPlural) {
        this.uRelacionPlural = uRelacionPlural;
    }

    public String getUManipuleo() {
        return uManipuleo;
    }

    public void setUManipuleo(String uManipuleo) {
        this.uManipuleo = uManipuleo;
    }

    public String getUManipuleoPlural() {
        return uManipuleoPlural;
    }

    public void setUManipuleoPlural(String uManipuleoPlural) {
        this.uManipuleoPlural = uManipuleoPlural;
    }

    public String getUPedido() {
        return uPedido;
    }

    public void setUPedido(String uPedido) {
        this.uPedido = uPedido;
    }

    public String getUPedidoPlural() {
        return uPedidoPlural;
    }

    public void setUPedidoPlural(String uPedidoPlural) {
        this.uPedidoPlural = uPedidoPlural;
    }

    public List<Articulo> getArticuloList() {
        return articuloList;
    }

    public void setArticuloList(List<Articulo> articuloList) {
        this.articuloList = articuloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticuloTipo != null ? idArticuloTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ArticuloTipo)) {
            return false;
        }
        ArticuloTipo other = (ArticuloTipo) object;
        return (this.idArticuloTipo != null || other.idArticuloTipo == null) && (this.idArticuloTipo == null || this.idArticuloTipo.equals(other.idArticuloTipo));
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

}
