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
@Table(name = "documento", catalog = "campiutti", schema = "")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumento", nullable = false)
    private Integer idDocumento;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento", orphanRemoval=true)
    private List<DocumentoEdicion> documentoEdicionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumento")
    private List<Plan> planList;
    
    @JoinColumn(name = "idDocumentoCarpeta", referencedColumnName = "idDocumentoCarpeta", nullable = false)
    @ManyToOne(optional = false)
    private DocumentoCarpeta idDocumentoCarpeta;
    
    @Column(name = "nombre", length = 70)
    private String nombre;

    @Column(name = "tema", length = 70)
    private String tema;
    
    @Column(name = "activo")
    private Boolean activo;

    @Column(name = "verEnM")
    private Boolean verEnM;
    
    

    public Documento() {
    }

    public Documento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }


    public List<DocumentoEdicion> getDocumentoEdicionList() {
        return documentoEdicionList;
    }

    public void setDocumentoEdicionList(List<DocumentoEdicion> documentoEdicionList) {
        this.documentoEdicionList = documentoEdicionList;
    }


    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public DocumentoCarpeta getIdDocumentoCarpeta() {
        return idDocumentoCarpeta;
    }

    public void setIdDocumentoCarpeta(DocumentoCarpeta idDocumentoCarpeta) {
        this.idDocumentoCarpeta = idDocumentoCarpeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getVerEnM() {
        return verEnM;
    }

    public void setVerEnM(Boolean verEnM) {
        this.verEnM = verEnM;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumento != null ? idDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        return !((this.idDocumento == null && other.idDocumento != null) || (this.idDocumento != null && !this.idDocumento.equals(other.idDocumento)));
    }

    @Override
    public String toString() {
        try{
            return nombre + " " + tema;
        }
        catch(Exception e){
            return nombre;
        }
            
    }


}
