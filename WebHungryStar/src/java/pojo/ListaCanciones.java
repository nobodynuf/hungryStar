/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package pojo;

import entidades.Cancion;
import facades.CancionFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Jorge Manriquez
 */
public class ListaCanciones {

    

    public List<CancionSimple> listaCancionSimple(List<Cancion> lista) {
        List<CancionSimple> retorno = new ArrayList<>();
        for (Cancion cancion : lista) {
            CancionSimple cancionSimple = new CancionSimple(
                    cancion.getId(), cancion.getNombre(), cancion.getIdArtista(), cancion.getIdUsuario(), cancion.getIdAlbum()
            );
            retorno.add(cancionSimple);
        }
        return retorno;
    }

    public class CancionSimple {

        private Integer id;
        private String nombre;
        private int idArtista;
        private int idUsuario;
        private int idAlbum;

        public CancionSimple(Integer id, String nombre, int idArtista, int idUsuario, int idAlbum) {
            this.id = id;
            this.nombre = nombre;
            this.idArtista = idArtista;
            this.idUsuario = idUsuario;
            this.idAlbum = idAlbum;
        }

        public CancionSimple() {

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

        public int getIdArtista() {
            return idArtista;
        }

        public void setIdArtista(int idArtista) {
            this.idArtista = idArtista;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public int getIdAlbum() {
            return idAlbum;
        }

        public void setIdAlbum(int idAlbum) {
            this.idAlbum = idAlbum;
        }
        
    }
}
