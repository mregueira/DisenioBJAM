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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "documentoCarpeta", catalog = "campiutti", schema = "")
@XmlRootElement
public class DocumentoCarpeta implements Serializable {


    private static final long serialVersionUID = 1L;


    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumentoCarpeta", nullable = false)
    private Integer idDocumentoCarpeta;
    
    @Column(name = "nombre", length = 50)
    private String nombre;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumentoCarpeta")
    private List<Documento> documentoList;

    public DocumentoCarpeta() {
    }

    public DocumentoCarpeta(Integer idDocumentoCarpeta) {
        this.idDocumentoCarpeta = idDocumentoCarpeta;
    }

    public Integer getIdDocumentoCarpeta() {
        return idDocumentoCarpeta;
    }

    public void setIdDocumentoCarpeta(Integer idDocumentoCarpeta) {
        this.idDocumentoCarpeta = idDocumentoCarpeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentoCarpeta != null ? idDocumentoCarpeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentoCarpeta)) {
            return false;
        }
        DocumentoCarpeta other = (DocumentoCarpeta) object;
        return !((this.idDocumentoCarpeta == null && other.idDocumentoCarpeta != null) || (this.idDocumentoCarpeta != null && !this.idDocumentoCarpeta.equals(other.idDocumentoCarpeta)));
    }

    @Override
    public String toString() {
        return nombre.trim(); 
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }
    
}
