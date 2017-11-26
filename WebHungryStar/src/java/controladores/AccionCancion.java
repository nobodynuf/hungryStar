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
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Registro;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionCancion", urlPatterns = {"/Cancion"})
public class AccionCancion extends HttpServlet {

    @EJB
    UsuarioFacade uFacade;

    @EJB
    ArtistaFacade aFacade;

    @EJB
    CancionFacade cFacade;

    @EJB
    AlbumFacade alFacade;

    @EJB
    LogFacade lFacade;

    /**
     * Create
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

    }

    /**
     * Read, Update, Delete
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {
            switch (req.getParameter("action")) {
                case "listar":
                    if (req.getSession().getAttribute("usuario") == null) {
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        return;
                    }

                    // se actualizan objetos en la sesion para agilizar su acceso luego
                    List<Artista> listaArtista = new ArrayList<>();
                    List<Artista> otraListaArtista = aFacade.findAll();

                    List<Cancion> laOtraLista = cFacade.findAll();
                    List<Cancion> lista = new ArrayList<>();

                    List<Album> laOtraListaAlbum = alFacade.findAll();
                    List<Album> listaAlbum = new ArrayList<>();

                    for (Cancion cancion : laOtraLista) {
                        if (cancion.getIdUsuario() == ((int) ((Usuario) req.getSession().getAttribute("usuario")).getId())) {
                            lista.add(cancion);
                        }
                    }
                    Registro.LOG.info("Llenada lista objeto sesion Cancion");

                    for (Cancion cancion : lista) {
                        for (Artista artista : otraListaArtista) {
                            if (((int) artista.getId()) == ((int) cancion.getIdArtista())) {
                                listaArtista.add(artista);
                            }
                        }
                    }
                    Registro.LOG.info("Llenada lista objeto sesion Artista");

                    for (Album album : laOtraListaAlbum) {
                        for (Cancion cancion : lista) {
                            if (((int) album.getId()) == ((int) cancion.getIdAlbum())) {
                                listaAlbum.add(album);
                                break;
                            }
                        }
                    }

                    Registro.LOG.info("Llenada lista objeto sesion Albunes");

                    req.getSession().setAttribute("listaArtistas", otraListaArtista);
                    req.getSession().setAttribute("listaCanciones", lista);
                    req.getSession().setAttribute("listaAlbunes", laOtraListaAlbum);

//                    Usuario user = (Usuario) req.getSession().getAttribute("usuario");
//                    List<Album> listaAlbunes = (List<Album>)req.getSession().getAttribute("listaAlbunes");
//                    List<Artista> listaArtistas = (List<Artista>)req.getSession().getAttribute("listaArtistas");
//                    List<Cancion> listaCancion = (List<Cancion>)req.getSession().getAttribute("listaCanciones");
                    Registro.LOG.info("Redireccionando a lista CANCION");
                    req.getRequestDispatcher("cancion.jsp").forward(req, resp);
                    break;
                case "modificar":
                    return;
                case "eliminar":
                    return;
                default:
                    throw new Exception("Error al procesar la solicitud get");

            }
        } catch (Exception e) {
            Registro.LOG.log(Level.SEVERE, "Error: {0}", e.getMessage());
        }
    }

}
// quiero puro &nbsp; el pate con ruedas
