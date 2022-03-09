package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "lote", catalog = "campiutti", schema = "")

public class Lote implements Serializable {
    @Column(name =     "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name =     "fechaElimina")
    @Temporal(TemporalType.DATE)
    private Date fechaElimina;

    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLote", nullable = false)
    private Integer idLote;
    
    
    @Lob
    @Column(name = "nota", length = 65535)
    private String nota;
    
    @Column(name = "edicion")
    private Integer edicion;
    
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @Column(name = "finalizada")
    private Boolean finalizada;
    
    
    @Column(name = "preserie")
    private Boolean preserie;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLote", orphanRemoval=true)
    private List<LoteProceso> loteProcesoList;
    
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", nullable = false)
    @ManyToOne(optional = false)
    private Articulo idArticulo;
    
    
    @JoinColumn(name = "idProcesoLiberador", referencedColumnName = "idLoteProceso")
    @ManyToOne
    private LoteProceso idProcesoLiberador;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    
    
    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan")
    @ManyToOne
    private Plan idPlan;
    
    
    
    

    public Lote() {
    }

    public Lote(Integer idLote) {
        this.idLote = idLote;
    }

    public Lote(Integer idLote, int codigo) {
        this.idLote = idLote;
        this.codigo = codigo;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }


    public Articulo getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulo idArticulo) {
        this.idArticulo = idArticulo;
    }

    public LoteProceso getIdProcesoLiberador() {
        return idProcesoLiberador;
    }

    public void setIdProcesoLiberador(LoteProceso idProcesoLiberador) {
        this.idProcesoLiberador = idProcesoLiberador;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }


    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }

    public boolean getPreserie() {
        if(preserie == null){
            return false;
        }
        return preserie;
    }

    public void setPreserie(Boolean preserie) {
        this.preserie = preserie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        return (this.idLote != null || other.idLote == null) && (this.idLote == null || this.idLote.equals(other.idLote));
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return Integer.toString(codigo).trim();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaElimina() {
        return fechaElimina;
    }

    public void setFechaElimina(Date fechaElimina) {
        this.fechaElimina = fechaElimina;
    }

}
