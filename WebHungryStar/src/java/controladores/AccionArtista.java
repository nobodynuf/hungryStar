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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionArtista", urlPatterns = {"/Artista"})
public class AccionArtista extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            switch (req.getParameter("action")) {
                case "listar":
                    if (req.getSession().getAttribute("usuario") == null) {
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        return;
                    }
                    Usuario user = (Usuario) req.getSession().getAttribute("usuario");
                    List<Album> listaAlbunes = (List<Album>)req.getSession().getAttribute("listaAlbunes");
                    List<Artista> listaArtistas = (List<Artista>)req.getSession().getAttribute("listaArtistas");
                    List<Cancion> listaCancion = (List<Cancion>)req.getSession().getAttribute("listaCanciones");
                    // TODO logica accion artista
                    
                    break;

                default:
                    throw new Exception("Error al procesar la solicitud GET");

            }
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());
        }
    }

}
