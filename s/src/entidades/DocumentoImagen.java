/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@Entity
@Table(name = "documentoImagen", catalog = "campiutti", schema = "")
@XmlRootElement
public class DocumentoImagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocumentoImagen", nullable = false)
    private Integer idDocumentoImagen;
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Basic(optional = false)
    @Lob
    @Column(name = "imagen", nullable = false)
    private byte[] imagen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "factorZoom", precision = 22)
    private Double factorZoom;
    @Column(name = "factorRotacion", precision = 22)
    private Double factorRotacion;
    @Basic(optional = false)
    
    @Column(name = "idTipoArchivo", nullable = false)
    private int idTipoArchivo;

    @JoinColumn(name = "idDocumentoEdicion", referencedColumnName = "idDocumentoEdicion", nullable = false)
    @ManyToOne(optional = false)
    private DocumentoEdicion idDocumentoEdicion;

    public DocumentoImagen() {
    }

    public DocumentoImagen(Integer idDocumentoImagen) {
        this.idDocumentoImagen = idDocumentoImagen;
    }

    public DocumentoImagen(Integer idDocumentoImagen, byte[] imagen, int idTipoArchivo) {
        this.idDocumentoImagen = idDocumentoImagen;
        this.imagen = imagen;
        this.idTipoArchivo = idTipoArchivo;
    }

    public Integer getIdDocumentoImagen() {
        return idDocumentoImagen;
    }

    public void setIdDocumentoImagen(Integer idDocumentoImagen) {
        this.idDocumentoImagen = idDocumentoImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Double getFactorZoom() {
        return factorZoom;
    }

    public void setFactorZoom(Double factorZoom) {
        this.factorZoom = factorZoom;
    }

    public Double getFactorRotacion() {
        return factorRotacion;
    }

    public void setFactorRotacion(Double factorRotacion) {
        this.factorRotacion = factorRotacion;
    }

    public int getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(int idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public DocumentoEdicion getIdDocumentoEdicion() {
        return idDocumentoEdicion;
    }

    public void setIdDocumentoEdicion(DocumentoEdicion idDocumentoEdicion) {
        this.idDocumentoEdicion = idDocumentoEdicion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentoImagen != null ? idDocumentoImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoImagen)) {
            return false;
        }
        DocumentoImagen other = (DocumentoImagen) object;
        if ((this.idDocumentoImagen == null && other.idDocumentoImagen != null) || (this.idDocumentoImagen != null && !this.idDocumentoImagen.equals(other.idDocumentoImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.DocumentoImagen[ idDocumentoImagen=" + idDocumentoImagen + " ]";
    }
    
}
