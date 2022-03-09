package entidades;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "loteMedida", catalog = "campiutti", schema = "")

public class LoteMedida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLoteMedida", nullable = false)
    private Integer idLoteMedida;

    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private double valor;

    @Basic(optional = false)
    @Column(name = "decimales", nullable = false)
    private int decimales;

    @JoinColumn(name = "idLoteCota", referencedColumnName = "idLoteCota", nullable = false)
    @ManyToOne(optional = false)
    private LoteCota idLoteCota;

    
    public LoteMedida() {
    }

    public LoteMedida(Integer idLoteMedida) {
        this.idLoteMedida = idLoteMedida;
    }

    public Integer getIdLoteMedida() {
        return idLoteMedida;
    }

    public void setIdLoteMedida(Integer idLoteMedida) {
        this.idLoteMedida = idLoteMedida;
    }

    public LoteCota getIdLoteCota() {
        return idLoteCota;
    }

    public void setIdLoteCota(LoteCota idLoteCota) {
        this.idLoteCota = idLoteCota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLoteMedida != null ? idLoteMedida.hashCode() : 0);
        return hash;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getDecimales() {
        return decimales;
    }

    public void setDecimales(int decimales) {
        this.decimales = decimales;
    }


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LoteMedida)) {
            return false;
        }
        LoteMedida other = (LoteMedida) object;
        return (this.idLoteMedida != null || other.idLoteMedida == null) && (this.idLoteMedida == null || this.idLoteMedida.equals(other.idLoteMedida));
    }

    @Override
    public String toString() {
        String format = "#";
        for(int i = 0; i < getDecimales(); i++){
            if(i == 0){
                format += ".";
            }
            format += "0";
        }
        return new DecimalFormat(format).format(getValor());
    }
    
}
