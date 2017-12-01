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
import pojo.ListaCanciones;
import pojo.Registro;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionAlbum", urlPatterns = {"/Album"})
public class AccionAlbum extends HttpServlet {

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
        resp.setContentType("text/xml;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        try {
            switch (req.getParameter("action")) {
                case "listar":
                    if (req.getSession().getAttribute("usuario") == null) {
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        return;
                    }
                    Registro.LOG.info("Redireccionando a lista ALBUM");
                    resp.sendRedirect("album.jsp");
                    break;

                case "modificar":
                    String nombreAlbum = req.getParameter("nombreAlbum");
                    Integer idAlbum = new Integer(req.getParameter("idAlbum"));

                    Album al = alFacade.find(idAlbum);
                    Registro.LOG.info("Obtenidos params con exito Album");
                    al.setNombre(nombreAlbum);
                    alFacade.edit(al);
                    Registro.LOG.info("Modificado con exito Album");
                    resp.sendRedirect("doSession?session=todas&forward=Album");

                    return;
                case "eliminar":
                    Integer idEliminar = new Integer(req.getParameter("idAlbum"));
                    Album albumEliminar = alFacade.find(idEliminar);
                    Registro.LOG.info("Encontrado Album a eliminar");
                    alFacade.remove(albumEliminar);
                    
                    Registro.LOG.info("Albuma eliminado correctamente, redireccionando");
                    resp.sendRedirect("doSession?session=todas&forward=Album");
                    
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/xml;charset=UTF-8");
        try {
            int id_artista = Integer.parseInt(req.getParameter("listaArtistas"));
            String nombre = req.getParameter("txtNombre");

            if (id_artista < 1) {
                throw new Exception("indice de artista fuera de rango");
            }

            Registro.LOG.log(Level.INFO, "Parametros cargados correctamente {0}, {1}",
                    new Object[]{id_artista, nombre});

            Album a = new Album();
            a.setIdArtista(id_artista);
            a.setNombre(nombre);
            alFacade.create(a);
            Registro.LOG.info("Album creado correctamente, redireccionando");
            req.setAttribute("resultado", "Agregado correctamente!");
            resp.sendRedirect("doSession?session=todas&forward=Album");
        } catch (Exception e) {
            resp.getWriter().println("<script>alert('error: " + e.getMessage() + "')</script>");
        }
    }

}
