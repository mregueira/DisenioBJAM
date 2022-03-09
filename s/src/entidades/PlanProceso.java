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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "planProceso", catalog = "campiutti", schema = "")
public class PlanProceso implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanProceso")
    private List<PlanProcesoParametro> planProcesoParametroList;
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanProceso", nullable = false)
    private Integer idPlanProceso;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "idStockEstado")
    private Integer idStockEstado;
    
    @Column(name = "tiempoCiclo")
    private Integer tiempoCiclo;
    
    @Column(name = "usarStockEnEquipo", nullable = false)
    private boolean usarStockEnEquipo;
    
    @Lob
    @Column(name = "nota", length = 2147483647)
    private String nota;
    
    @JoinColumn(name = "idStockAlmacen", referencedColumnName = "idStockAlmacen")
    @ManyToOne
    private StockAlmacen idStockAlmacen;

    @JoinColumn(name = "idStockUbicacion", referencedColumnName = "idStockUbicacion")
    @ManyToOne
    private StockUbicacion idStockUbicacion;

    
    @JoinColumn(name = "idTipoProceso", referencedColumnName = "idTipoProceso")
    @ManyToOne
    private TipoProceso idTipoProceso;

    @JoinColumn(name = "idPlan", referencedColumnName = "idPlan", nullable = false)
    @ManyToOne(optional = false)
    private Plan idPlan;
    
    @JoinColumn(name = "idPlanProcesoO", referencedColumnName = "idPlanProceso")
    @ManyToOne
    private PlanProceso idPlanProcesoO;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanProceso")
    private List<PlanComponente> planComponenteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanProceso")
    private List<PlanCota> planCotaList;

    @OneToMany(mappedBy = "idPlanProcesoO")
    private List<PlanProceso> planProcesoList;

    @OneToMany(mappedBy = "idProcesoLiberador")
    private List<Plan> planList;

    public PlanProceso() {
    }

    public PlanProceso(Integer idPlanProceso) {
        this.idPlanProceso = idPlanProceso;
    }

    public Integer getIdPlanProceso() {
        return idPlanProceso;
    }

    public void setIdPlanProceso(Integer idPlanProceso) {
        this.idPlanProceso = idPlanProceso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdStockEstado() {
        return idStockEstado;
    }

    public void setIdStockEstado(Integer idStockEstado) {
        this.idStockEstado = idStockEstado;
    }

    public Integer getTiempoCiclo() {
        return tiempoCiclo;
    }

    public void setTiempoCiclo(Integer tiempoCiclo) {
        this.tiempoCiclo = tiempoCiclo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public boolean getUsarStockEnEquipo() {
        return usarStockEnEquipo;
    }

    public void setUsarStockEnEquipo(boolean usarStockEnEquipo) {
        this.usarStockEnEquipo = usarStockEnEquipo;
    }

    
    public List<PlanComponente> getPlanComponenteList() {
        return planComponenteList;
    }

    public void setPlanComponenteList(List<PlanComponente> planComponenteList) {
        this.planComponenteList = planComponenteList;
    }

    public Plan getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Plan idPlan) {
        this.idPlan = idPlan;
    }

    public List<PlanProceso> getPlanProcesoList() {
        return planProcesoList;
    }

    public void setPlanProcesoList(List<PlanProceso> planProcesoList) {
        this.planProcesoList = planProcesoList;
    }

    public PlanProceso getIdPlanProcesoO() {
        return idPlanProcesoO;
    }

    public void setIdPlanProcesoO(PlanProceso idPlanProcesoO) {
        this.idPlanProcesoO = idPlanProcesoO;
    }

    public StockAlmacen getIdStockAlmacen() {
        return idStockAlmacen;
    }

    public void setIdStockAlmacen(StockAlmacen idStockAlmacen) {
        this.idStockAlmacen = idStockAlmacen;
    }


    public StockUbicacion getIdStockUbicacion() {
        return idStockUbicacion;
    }

    public void setIdStockUbicacion(StockUbicacion idStockUbicacion) {
        this.idStockUbicacion = idStockUbicacion;
    }

    public TipoProceso getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(TipoProceso idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public List<PlanCota> getPlanCotaList() {
        return planCotaList;
    }

    public void setPlanCotaList(List<PlanCota> planCotaList) {
        this.planCotaList = planCotaList;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanProceso != null ? idPlanProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PlanProceso)) {
            return false;
        }
        PlanProceso other = (PlanProceso) object;
        if ((this.idPlanProceso == null && other.idPlanProceso != null) || (this.idPlanProceso != null && !this.idPlanProceso.equals(other.idPlanProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(nombre == null || nombre.isEmpty() ){
            return ((Integer)codigo).toString().trim() + "-" + idTipoProceso.getNombre().trim();
        }
        return ((Integer)codigo).toString().trim() + "-" + nombre.trim();
    }

    @XmlTransient
    public List<PlanProcesoParametro> getPlanProcesoParametroList() {
        return planProcesoParametroList;
    }

    public void setPlanProcesoParametroList(List<PlanProcesoParametro> planProcesoParametroList) {
        this.planProcesoParametroList = planProcesoParametroList;
    }

}
