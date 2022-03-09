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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "loteProceso", catalog = "campiutti", schema = "")

public class LoteProceso implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteProceso", nullable = false)
    private Integer idLoteProceso;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    @Column(name = "usarStockEnEquipo", nullable = false)
    private boolean usarStockEnEquipo;
    
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;

    @Column(name =     "fechaIni")
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Column(name =     "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    
    @Column(name = "idStockEstado")
    private Integer idStockEstado;

    @Column(name = "tiempoCiclo")
    private Integer tiempoCiclo;
    
    @Column(name = "cantidadProducida")
    private Integer cantidadProducida;
    
    @Lob
    @Column(name = "nota", length = 65535)
    private String nota;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProceso", orphanRemoval=true)
    private List<LoteComponente> loteComponenteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProcesoMPC", orphanRemoval=true)
    private List<LoteComponenteAsignado> loteComponenteAsignadoList;
    
    @OneToMany(mappedBy = "idProcesoLiberador", orphanRemoval=true)
    private List<Lote> loteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProceso", orphanRemoval=true)
    private List<LoteCota> loteCotaList;

    @OneToMany(mappedBy = "idLoteProcesoO", orphanRemoval=true)
    private List<LoteProceso> loteProcesoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProceso")
    private List<MonitorProceso> monitorProcesoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProceso")
    private List<LoteProcesoProducción> loteProcesoProducciónList;
    
    
    
    @JoinColumn(name = "idStockAlmacen", referencedColumnName = "idStockAlmacen")
    @ManyToOne
    private StockAlmacen idStockAlmacen;
    
    
    @JoinColumn(name = "idLote", referencedColumnName = "idLote", nullable = false)
    @ManyToOne(optional = false)
    private Lote idLote;
    
    @JoinColumn(name = "idLoteProcesoO", referencedColumnName = "idLoteProceso")
    @ManyToOne
    private LoteProceso idLoteProcesoO;
    
    @JoinColumn(name = "idTipoProceso", referencedColumnName = "idTipoProceso", nullable = false)
    @ManyToOne(optional = false)
    private TipoProceso idTipoProceso;
    
    @JoinColumn(name = "idStockUbicacion", referencedColumnName = "idStockUbicacion")
    @ManyToOne
    private StockUbicacion idStockUbicacion;
    
    @JoinColumn(name = "idInstrumento6", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento6;

    @JoinColumn(name = "idInstrumento5", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento5;
    
    @JoinColumn(name = "idInstrumento4", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento4;
    
    @JoinColumn(name = "idInstrumento3", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento3;
    
    @JoinColumn(name = "idInstrumento2", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento2;
    
    @JoinColumn(name = "idInstrumento1", referencedColumnName = "idInstrumento")
    @ManyToOne
    private Instrumento idInstrumento1;
    
    @JoinColumn(name = "idUsuarioControl", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuarioControl;
    
    @JoinColumn(name = "idUsuarioLiberacion", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuarioLiberacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLoteProceso")
    private List<LoteProcesoParametro> loteProcesoParametroList;
    

    
    public LoteProceso() {
    }

    public LoteProceso(Integer idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
    }

    public LoteProceso(Integer idLoteProceso, Integer codigo) {
        this.idLoteProceso = idLoteProceso;
        this.codigo = codigo;
    }

    public Integer getIdLoteProceso() {
        return idLoteProceso;
    }

    public void setIdLoteProceso(Integer idLoteProceso) {
        this.idLoteProceso = idLoteProceso;
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

    public Integer getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(Integer cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
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

    public List<LoteProcesoParametro> getLoteProcesoParametroList() {
        return loteProcesoParametroList;
    }

    public void setLoteProcesoParametroList(List<LoteProcesoParametro> loteProcesoParametroList) {
        this.loteProcesoParametroList = loteProcesoParametroList;
    }

    

    public List<LoteComponente> getLoteComponenteList() {
        return loteComponenteList;
    }

    public void setLoteComponenteList(List<LoteComponente> loteComponenteList) {
        this.loteComponenteList = loteComponenteList;
    }

    public List<LoteComponenteAsignado> getLoteComponenteAsignadoList() {
        return loteComponenteAsignadoList;
    }

    public void setLoteComponenteAsignadoList(List<LoteComponenteAsignado> loteComponenteAsignadoList) {
        this.loteComponenteAsignadoList = loteComponenteAsignadoList;
    }


    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    public List<LoteCota> getLoteCotaList() {
        return loteCotaList;
    }

    public void setLoteCotaList(List<LoteCota> loteCotaList) {
        this.loteCotaList = loteCotaList;
    }

    public StockAlmacen getIdStockAlmacen() {
        return idStockAlmacen;
    }

    public void setIdStockAlmacen(StockAlmacen idStockAlmacen) {
        this.idStockAlmacen = idStockAlmacen;
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        this.idLote = idLote;
    }

    public List<LoteProceso> getLoteProcesoList() {
        return loteProcesoList;
    }

    public void setLoteProcesoList(List<LoteProceso> loteProcesoList) {
        this.loteProcesoList = loteProcesoList;
    }

    public LoteProceso getIdLoteProcesoO() {
        return idLoteProcesoO;
    }

    public void setIdLoteProcesoO(LoteProceso idLoteProcesoO) {
        this.idLoteProcesoO = idLoteProcesoO;
    }

    public TipoProceso getIdTipoProceso() {
        return idTipoProceso;
    }

    public void setIdTipoProceso(TipoProceso idTipoProceso) {
        this.idTipoProceso = idTipoProceso;
    }

    public StockUbicacion getIdStockUbicacion() {
        return idStockUbicacion;
    }

    public void setIdStockUbicacion(StockUbicacion idStockUbicacion) {
        this.idStockUbicacion = idStockUbicacion;
    }

    public Instrumento getIdInstrumento1() {
        return idInstrumento1;
    }

    public void setIdInstrumento1(Instrumento idInstrumento1) {
        this.idInstrumento1 = idInstrumento1;
    }

    public Instrumento getIdInstrumento2() {
        return idInstrumento2;
    }

    public void setIdInstrumento2(Instrumento idInstrumento2) {
        this.idInstrumento2 = idInstrumento2;
    }

    public Instrumento getIdInstrumento3() {
        return idInstrumento3;
    }

    public void setIdInstrumento3(Instrumento idInstrumento3) {
        this.idInstrumento3 = idInstrumento3;
    }

    public Instrumento getIdInstrumento4() {
        return idInstrumento4;
    }

    public void setIdInstrumento4(Instrumento idInstrumento4) {
        this.idInstrumento4 = idInstrumento4;
    }

    public Instrumento getIdInstrumento5() {
        return idInstrumento5;
    }

    public void setIdInstrumento5(Instrumento idInstrumento5) {
        this.idInstrumento5 = idInstrumento5;
    }

    public Instrumento getIdInstrumento6() {
        return idInstrumento6;
    }

    public void setIdInstrumento6(Instrumento idInstrumento6) {
        this.idInstrumento6 = idInstrumento6;
    }

    public Usuario getIdUsuarioControl() {
        return idUsuarioControl;
    }

    public void setIdUsuarioControl(Usuario idUsuarioControl) {
        this.idUsuarioControl = idUsuarioControl;
    }

    public Usuario getIdUsuarioLiberacion() {
        return idUsuarioLiberacion;
    }

    public void setIdUsuarioLiberacion(Usuario idUsuarioLiberacion) {
        this.idUsuarioLiberacion = idUsuarioLiberacion;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteProceso != null ? idLoteProceso.hashCode() : 0);
        return hash;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LoteProceso)) {
            return false;
        }
        LoteProceso other = (LoteProceso) object;
        return (this.idLoteProceso != null || other.idLoteProceso == null) && (this.idLoteProceso == null || this.idLoteProceso.equals(other.idLoteProceso));
    }
    @Override
    public String toString() {
        if(nombre == null || nombre.isEmpty() ){
            return ((Integer)codigo).toString().trim() + "-" + idTipoProceso.getNombre().trim();
        }
        return ((Integer)codigo).toString().trim() + "-" + nombre.trim();
    }

    public List<LoteProcesoProducción> getLoteProcesoProducciónList() {
        return loteProcesoProducciónList;
    }

    public void setLoteProcesoProducciónList(List<LoteProcesoProducción> loteProcesoProducciónList) {
        this.loteProcesoProducciónList = loteProcesoProducciónList;
    }

    
    
    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

        public Date getFechaFin() {
        return fechaFin;
    }

        public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public List<MonitorProceso> getMonitorProcesoList() {
        return monitorProcesoList;
    }

    public void setMonitorProcesoList(List<MonitorProceso> monitorProcesoList) {
        this.monitorProcesoList = monitorProcesoList;
    }
}
