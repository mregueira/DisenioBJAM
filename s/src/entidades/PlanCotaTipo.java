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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "planCotaTipo", catalog = "campiutti", schema = "")
@XmlRootElement
public class PlanCotaTipo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPlanCotaTipo", nullable = false)
    private Integer idPlanCotaTipo;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    private static final long serialVersionUID = 1L;
    
   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlanCotaTipo")
    private List<LoteCota> loteCotaList;
   
    
    
    public PlanCotaTipo() {
    }

    public PlanCotaTipo(Integer idPlanCotaTipo) {
	this.idPlanCotaTipo = idPlanCotaTipo;
    }

    public PlanCotaTipo(Integer idPlanCotaTipo, String nombre) {
	this.idPlanCotaTipo = idPlanCotaTipo;
	this.nombre = nombre;
    }

    public Integer getIdPlanCotaTipo() {
	return idPlanCotaTipo;
    }

    public void setIdPlanCotaTipo(Integer idPlanCotaTipo) {
	this.idPlanCotaTipo = idPlanCotaTipo;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public List<LoteCota> getLoteCotaList() {
	return loteCotaList;
    }

    public void setLoteCotaList(List<LoteCota> loteCotaList) {
	this.loteCotaList = loteCotaList;
    }


    
    
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (idPlanCotaTipo != null ? idPlanCotaTipo.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof PlanCotaTipo)) {
	    return false;
	}
	PlanCotaTipo other = (PlanCotaTipo) object;
	return (this.idPlanCotaTipo != null || other.idPlanCotaTipo == null) && (this.idPlanCotaTipo == null || this.idPlanCotaTipo.equals(other.idPlanCotaTipo));
    }

    @Override
    public String toString() {
	return nombre;
    }
}
