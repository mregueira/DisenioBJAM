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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "plan", catalog = "campiutti", schema = "")
public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlan", nullable = false)
    private Integer idPlan;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    
    @Column(name = "edicion")
    private Integer edicion;
    
    @Lob
    @Column(name = "nota", length = 65535)
    private String nota;
    
    
    @OneToMany(mappedBy = "idPlan")
    private List<Lote> loteList;
    
    
    @JoinColumn(name = "idArticulo", referencedColumnName = "idArticulo", nullable = false)
    @ManyToOne(optional = false)
    private Articulo idArticulo;
    
    
    @JoinColumn(name = "idDocumento", referencedColumnName = "idDocumento")
    @ManyToOne
    private Documento idDocumento;
    
    @JoinColumn(name = "idProcesoLiberador", referencedColumnName = "idPlanProceso")
    @ManyToOne
    private PlanProceso idProcesoLiberador;
    
    
    
    
    
    public Plan() {
    }

    public Plan(Integer idPlan) {
        this.idPlan = idPlan;
    }


    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }


//    public List<Lote> getLoteList() {
//        return loteList;
//    }
//
//    public void setLoteList(List<Lote> loteList) {
//        this.loteList = loteList;
//    }

    public Articulo getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Articulo idArticulo) {
        this.idArticulo = idArticulo;
    }


    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
    }

    public PlanProceso getIdProcesoLiberador() {
        return idProcesoLiberador;
    }

    public void setIdProcesoLiberador(PlanProceso idProcesoLiberador) {
        this.idProcesoLiberador = idProcesoLiberador;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlan != null ? idPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        return (this.idPlan != null || other.idPlan == null) && (this.idPlan == null || this.idPlan.equals(other.idPlan));
    }

}
