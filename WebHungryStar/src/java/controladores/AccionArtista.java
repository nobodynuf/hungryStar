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
import javax.json.JsonObject;
import pojo.ListaCanciones;

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
            resp.setContentType("text/xml;charset=UTF-8");
            switch (req.getParameter("action")) {
                case "listar":
                    if (req.getSession().getAttribute("usuario") == null) {
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        return;
                    }

                    // se crean objetos en la sesion para agilizar su acceso luego
                    List<Artista> listaArtista = new ArrayList<>();
                    List<Artista> otraListaArtista = aFacade.findAll();
                    
                    List<Cancion> laOtraLista = cFacade.findAll();
                    // usando ListaCanciones para convertir las canciones en algo simple
                    ListaCanciones listaCancionSimple = new ListaCanciones();
                    List<ListaCanciones.CancionSimple> listaAMostrar = listaCancionSimple.listaCancionSimple(laOtraLista);
                    
                    List<Album> laOtraListaAlbum = alFacade.findAll();
                    List<Album> listaAlbum = new ArrayList<>();
                    
                    for (ListaCanciones.CancionSimple cancion : listaAMostrar) {
                        if (cancion.getIdUsuario() == ((int) ((Usuario) req.getAttribute("usuario")).getId())) {
                            
                            listaAMostrar.add(cancion);
                        }
                    }
                    Registro.LOG.info("Llenada lista objeto sesion Cancion");
                    
                    // buscamos las canciones que esten enlazadas a un artista
                    for (ListaCanciones.CancionSimple cancion : listaAMostrar) {
                        for (Artista artista : otraListaArtista) {
                            if (((int) artista.getId()) == ((int) cancion.getIdArtista())) {
                                listaArtista.add(artista);
                            }
                        }
                    }
                    Registro.LOG.info("Llenada lista objeto sesion Artista");
                    
                    for (Album album : laOtraListaAlbum) {
                        for (ListaCanciones.CancionSimple cancion : listaAMostrar) {
                            if (((int) album.getId()) == ((int) cancion.getIdAlbum())) {
                                listaAlbum.add(album);
                                break;
                            }
                        }
                    }
                    
                    Registro.LOG.info("Llenada lista objeto sesion Albunes");
                    
                    req.getSession().setAttribute("listaArtistas", otraListaArtista);
                    req.getSession().setAttribute("listaCanciones", listaAMostrar);
                    req.getSession().setAttribute("listaAlbunes", laOtraListaAlbum);
                    Registro.LOG.info("Redireccionando a lista artista");
                    req.getRequestDispatcher("artistas.jsp").forward(req, resp);
                    break;

                case "modificar":
                    String nombreArtista = req.getParameter("nombreArtista");
                    int idArtista = Integer.parseInt(req.getParameter("idArtista"));

                    if (!nombreArtista.isEmpty()) {
                        Artista a = aFacade.find(idArtista);
                        a.setNombre(nombreArtista);
                        aFacade.edit(a);
                        Registro.LOG.info("Modificado correcto de artista, redireccionando");
                        return;

                    } else {
                        Registro.LOG.warning("Error al procesar AJAX para modificar el artista");
                    }
                    break;
                case "eliminar":
                    Integer numero = Integer.parseInt(req.getParameter("idArtista"));
                    Artista aEliminar = aFacade.find(numero);
                    Registro.LOG.info("Encontrado artista a eliminar");

                    aFacade.remove(aEliminar);
                    Registro.LOG.info("Artista eliminado correctamente, redireccionando");

                    return;
                default:
                    throw new Exception("Error al procesar la solicitud GET");

            }
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());
        }
    }

    /**
     * Metodo doPost que controla el agregar <h1>Artistas</h1>
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombreArtista = req.getParameter("txtNombre").toString();

            if (!nombreArtista.isEmpty()) {
                Artista a = new Artista();
                a.setNombre(nombreArtista);
                aFacade.create(a);

                req.getSession().setAttribute(nombreArtista, a);
                Registro.LOG.log(Level.INFO, "Artista values [id]{0} [nombre ]{1}", new Object[]{a.getId(), a.getNombre()});

                Registro.LOG.info("Agregado artista a db, redireccionando");

                req.setAttribute("resultado", "Agregado correctamente!");
                resp.sendRedirect("./Artista?action=listar");

            } else {
                throw new Exception("POST PARAMETERS EMPTY");
            }
        } catch (Exception e) {

            Registro.LOG.log(Level.INFO, "Error al procesar post action ADD artista {0}", e.getMessage());
            req.setAttribute("resultado", "Ha ocurrido un error procesando la solicitud");
            resp.sendRedirect("./Artista?action=listar");
        }
    }

}
