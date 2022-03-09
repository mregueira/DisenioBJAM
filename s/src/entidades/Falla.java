package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name= "falla", catalog = "campiutti", schema = "")
@XmlRootElement

public class Falla implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idFalla;
    
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Basic(optional = false)
    @Column(name = "idDepartamento", nullable = false)
    private int idDepartamento;
    
    @Lob
    @Column(length = 65535)
    private String nota;
    
    @Basic(optional = false)
    @Column(nullable = false)
    private int cantidad;
    
    
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", nullable = false)
    @ManyToOne(optional = false)
    private Articulo idArticulo;
    
    @JoinColumn(name = "idFallaTipo", referencedColumnName = "idFallaTipo", nullable = false)
    @ManyToOne(optional = false)
    private FallaTipo idFallaTipo;

    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    
    public Falla() {
    }

    public Falla(Integer idFalla) {
        this.idFalla = idFalla;
    }

    public Falla(Integer idFalla, Date fecha, int cantidad) {
        this.idFalla = idFalla;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Integer getIdFalla() {
        return idFalla;
    }

    public void setIdFalla(Integer idFalla) {
        this.idFalla = idFalla;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Articulo getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulo idArticulo) {
        this.idArticulo = idArticulo;
    }

    public FallaTipo getIdFallaTipo() {
        return idFallaTipo;
    }

    public void setIdFallaTipo(FallaTipo idFallaTipo) {
        this.idFallaTipo = idFallaTipo;
    }

    public Usuario getIdUsuario() {
	return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
	this.idUsuario = idUsuario;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFalla != null ? idFalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Falla)) {
            return false;
        }
        Falla other = (Falla) object;
        return !((this.idFalla == null && other.idFalla != null) || (this.idFalla != null && !this.idFalla.equals(other.idFalla)));
    }

    @Override
    public String toString() {
        return "entidades.Falla[ idFalla=" + idFalla + " ]";
    }
    
}
