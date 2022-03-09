package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "externo", catalog = "campiutti", schema = "")
@XmlRootElement

public class Externo implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idExterno", nullable = false)
    private Integer idExterno;

    @Column(name = "idtipo")
    private Integer idTipo;
    
    
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "esCliente")
    private Boolean esCliente;

    @Column(name = "esClienteProveedor")
    private Boolean esClienteProveedor;

    @Column(name = "activo")
    private Boolean activo;
    

    public Externo() {
    }

    public Externo(Integer idExterno) {
        this.idExterno = idExterno;
    }


    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    

    public Externo(Integer idExterno, String nombre) {
        this.idExterno = idExterno;
        this.nombre = nombre;
    }

    public Integer getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Integer idExterno) {
        this.idExterno = idExterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Boolean getEsCliente() {
        return esCliente;
    }

    public void setEsCliente(Boolean esCliente) {
        this.esCliente = esCliente;
    }

    public Boolean getEsClienteProveedor() {
        return esClienteProveedor;
    }

    public void setEsClienteProveedor(Boolean esClienteProveedor) {
        this.esClienteProveedor = esClienteProveedor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExterno != null ? idExterno.hashCode() : 0);
        return hash;
    }

    
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Externo)) {
            return false;
        }
        Externo other = (Externo) object;
        return !((this.idExterno == null && other.idExterno != null) || (this.idExterno != null && !this.idExterno.equals(other.idExterno)));
    }

    @Override
    public String toString() {
        return nombre;
    }


}
