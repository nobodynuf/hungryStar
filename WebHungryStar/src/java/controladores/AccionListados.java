/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package controladores;

import entidades.Album;
import entidades.Artista;
import entidades.Cancion;
import entidades.Usuario;
import facades.AlbumFacade;
import facades.ArtistaFacade;
import facades.CancionFacade;
import facades.LogFacade;
import facades.UsuarioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.ListaCanciones;
import pojo.Registro;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionListados", urlPatterns = {"/doSession"})
public class AccionListados extends HttpServlet {

    @EJB
    AlbumFacade alFacade;

    @EJB
    LogFacade lFacade;

    @EJB
    UsuarioFacade uFacade;

    @EJB
    ArtistaFacade aFacade;

    @EJB
    CancionFacade cFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (req.getParameter("session")) {
            case "todas":
                int usuario = ((int) ((Usuario)req.getSession().getAttribute("usuario")).getId());
                
                // se crean objetos en la sesion para agilizar su acceso luego
                List<Artista> listaArtista = new ArrayList<>();
                List<Artista> otraListaArtista = aFacade.findAll();

                List<Cancion> laOtraLista = cFacade.findAll();
                // usando ListaCanciones para convertir las canciones en algo simple
                ListaCanciones listaCancionSimple = new ListaCanciones();

                List<ListaCanciones.CancionSimple> listaAMostrar = listaCancionSimple.listaCancionSimple(laOtraLista, usuario);
                Registro.LOG.info("Llenada lista objeto sesion Cancion");
                
                List<Album> laOtraListaAlbum = alFacade.findAll();
                List<Album> listaAlbum = new ArrayList<>();

                

//                for (Album album : laOtraListaAlbum) {
//                    for (ListaCanciones.CancionSimple cancion : listaAMostrar) {
//                        if (((int) album.getId()) == ((int) cancion.getIdAlbum())) {
//                            if (!listaAlbum.contains(album)) {
//                                listaAlbum.add(album);
//                            }
//                        }
//                    }
//                }

                Registro.LOG.info("Llenada lista objeto sesion Albunes");

                req.getSession().setAttribute("listaArtistas", otraListaArtista);
                req.getSession().setAttribute("listaCanciones", new ArrayList<>(listaAMostrar));
                req.getSession().setAttribute("listaAlbunes", laOtraListaAlbum);
                
            default:
                if (req.getParameter("forward") != null) {
                    resp.sendRedirect(req.getParameter("forward")+"?action=listar");
                } else {
                    resp.sendRedirect("home.jsp");
                }
                break;
        }
    }

}
