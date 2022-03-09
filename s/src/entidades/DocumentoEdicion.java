/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "documentoEdicion", catalog = "campiutti", schema = "")
@XmlRootElement


public class DocumentoEdicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumentoEdicion", nullable = false)
    private Integer idDocumentoEdicion;

    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private int codigo;
    
    
    
    @JoinColumn(name = "idDocumento", referencedColumnName = "idDocumento", nullable = false)
    @ManyToOne(optional = false)
    private Documento idDocumento;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocumentoEdicion")
    private List<DocumentoImagen> documentoImagenList;

    public DocumentoEdicion() {
    }

    public DocumentoEdicion(Integer idDocumentoEdicion) {
        this.idDocumentoEdicion = idDocumentoEdicion;
    }


    public Integer getIdDocumentoEdicion() {
        return idDocumentoEdicion;
    }

    public void setIdDocumentoEdicion(Integer idDocumentoEdicion) {
        this.idDocumentoEdicion = idDocumentoEdicion;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Documento getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Documento idDocumento) {
        this.idDocumento = idDocumento;
    }

    @XmlTransient
    public List<DocumentoImagen> getDocumentoImagenList() {
        return documentoImagenList;
    }

    public void setDocumentoImagenList(List<DocumentoImagen> documentoImagenList) {
        this.documentoImagenList = documentoImagenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentoEdicion != null ? idDocumentoEdicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoEdicion)) {
            return false;
        }
        DocumentoEdicion other = (DocumentoEdicion) object;
        return !((this.idDocumentoEdicion == null && other.idDocumentoEdicion != null) || (this.idDocumentoEdicion != null && !this.idDocumentoEdicion.equals(other.idDocumentoEdicion)));
    }

    @Override
    public String toString() {
        return "entidades.DocumentoEdicion[ idDocumentoEdicion=" + idDocumentoEdicion + " ]";
    }
    
}
