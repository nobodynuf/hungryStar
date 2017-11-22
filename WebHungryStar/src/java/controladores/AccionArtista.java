/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package controladores;

import com.sun.xml.registry.uddi.bindings_v2_2.GetRegisteredInfo;
import entidades.Album;
import entidades.Artista;
import entidades.Cancion;
import entidades.Usuario;
import facades.AlbumFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Registro;
import facades.ArtistaFacade;
import facades.CancionFacade;
import facades.LogFacade;
import facades.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionArtista", urlPatterns = {"/Artista"})
public class AccionArtista extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (req.getParameter("action")) {
                case "listar":
                    if (req.getSession().getAttribute("usuario") == null) {
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        return;
                    }
//                    Usuario user = (Usuario) req.getSession().getAttribute("usuario");
//                    List<Album> listaAlbunes = (List<Album>)req.getSession().getAttribute("listaAlbunes");
//                    List<Artista> listaArtistas = (List<Artista>)req.getSession().getAttribute("listaArtistas");
//                    List<Cancion> listaCancion = (List<Cancion>)req.getSession().getAttribute("listaCanciones");
                    Registro.LOG.info("Redireccionando a lista artista");
                    req.getRequestDispatcher("artistas.jsp").forward(req, resp);
                    break;

                case "modificar":
                    if (req.getParameter("nombre") != null) {
                        String nombre = req.getParameter("nombre");
                        Integer id = Integer.valueOf(req.getParameter("id"));
                        Artista a = aFacade.find(id);
                        a.setNombre(nombre);
                        aFacade.edit(a);
                        Registro.LOG.info("Modificado correcto de artista, redireccionando");
                        req.getRequestDispatcher("artistas.jsp");
                        return;
                    } else {
                        Registro.LOG.info("Error al procesar AJAX");
                    }
                    break;
                default:
                    throw new Exception("Error al procesar la solicitud GET");

            }
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Registro.LOG.log(Level.INFO, "Entrado a metodo POST de Servlet Artista");
            String nombreArtista = req.getParameter("txtNombre");
            Registro.LOG.log(Level.INFO, "Parametro pasado por post [Nombre: {0}]", nombreArtista);
            if (!nombreArtista.isEmpty()) {
                Artista a = new Artista();
                a.setNombre(nombreArtista);
                aFacade.create(a);
                Registro.LOG.info("Agregado artista a db");

                
                List<Artista> otraListaArtista = aFacade.findAll();
                
                req.setAttribute("listaArtistas", otraListaArtista);


                resp.sendRedirect("./Artista?action=listar");

            } else {
                throw new Exception("POST PARAMETERS EMPTY");
            }
        } catch (Exception e) {
            Registro.LOG.log(Level.INFO, "Error al procesar post action ADD artista {0}", e.getMessage());
        }
    }

}
