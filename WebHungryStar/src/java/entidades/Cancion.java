/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */

package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge Manriquez
 */
@Entity
@Table(name = "cancion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cancion.findAll", query = "SELECT c FROM Cancion c"),
    @NamedQuery(name = "Cancion.findById", query = "SELECT c FROM Cancion c WHERE c.id = :id"),
    @NamedQuery(name = "Cancion.findByNombre", query = "SELECT c FROM Cancion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cancion.findByIdAlbum", query = "SELECT c FROM Cancion c WHERE c.idAlbum = :idAlbum"),
    @NamedQuery(name = "Cancion.findByIdArtista", query = "SELECT c FROM Cancion c WHERE c.idArtista = :idArtista")})
public class Cancion implements Serializable {

    @Lob
    @Column(name = "dato")
    private byte[] dato;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "id_album")
    private Integer idAlbum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_artista")
    private int idArtista;

    public Cancion() {
    }

    public Cancion(Integer id) {
        this.id = id;
    }

    public Cancion(Integer id, String nombre, int idArtista) {
        this.id = id;
        this.nombre = nombre;
        this.idArtista = idArtista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cancion)) {
            return false;
        }
        Cancion other = (Cancion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cancion[ id=" + id + " ]";
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public byte[] getDato() {
        return dato;
    }

    public void setDato(byte[] dato) {
        this.dato = dato;
    }

}
