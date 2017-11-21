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
import entidades.Log;
import entidades.Usuario;
import facades.AlbumFacade;
import facades.ArtistaFacade;
import facades.CancionFacade;
import facades.LogFacade;
import facades.UsuarioFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.Encriptador;
import pojo.Registro;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionLogin", urlPatterns = {"/AccionLogin"})
public class AccionLogin extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Usuario user = uFacade.buscarPorUsername(req.getParameter("txtUsername"), Encriptador.encriptar(req.getParameter("txtPassword")));
            Registro.LOG.log(Level.INFO, "Servlet{0}", req.getParameter("txtUsername"));
            for (Usuario usuario : uFacade.findAll()) {
                if (usuario.getUsername().equals(req.getParameter("txtUsername"))
                        & usuario.getPass().equals(Encriptador.encriptar(req.getParameter("txtPassword")))) {
                    req.getSession().setAttribute("usuario", usuario);
                    Registro.LOG.log(Level.INFO, "Usuario: {0} - Auth correcto", usuario.getNombre());

                    lFacade.create(new Log(Date.from(Instant.now()), usuario.getId()));

                    // se crean objetos en la sesion para agilizar su acceso luego
                    List<Artista> listaArtista = new ArrayList<>();
                    List<Artista> otraListaArtista = aFacade.findAll();

                    List<Cancion> laOtraLista = cFacade.findAll();
                    List<Cancion> lista = new ArrayList<>();

                    List<Album> laOtraListaAlbum = alFacade.findAll();
                    List<Album> listaAlbum = new ArrayList<>();

                    for (Cancion cancion : laOtraLista) {
                        if (cancion.getIdUsuario() == ((int) usuario.getId())) {
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
                            if (((int) album.getId()) == ((int)cancion.getIdAlbum())) {
                                listaAlbum.add(album);
                                break;
                            }
                        }
                    }

                    Registro.LOG.info("Llenada lista objeto sesion Albunes");
                    
                    req.setAttribute("listaArtistas", listaArtista);
                    req.setAttribute("listaCanciones", lista);
                    req.setAttribute("listaAlbunes", listaAlbum);
                    
                    Registro.LOG.info("Guardada la sesion, redireccionando");
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                    return;
                }
            }

            Registro.LOG.warning("Usuario no encontrado. Redireccionando a inicio");
            req.setAttribute("error", "true");

            req.getRequestDispatcher("home.jsp").forward(req, resp);
            return;
        } catch (ServletException | IOException e) {
            Registro.LOG.log(Level.SEVERE, "ERROR; {0}", e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Registro.LOG.info("Destruyendo sesion");
            req.getSession().setAttribute("usuario", null);
            req.getSession().setAttribute("listaArtista", null);

            Registro.LOG.info("Redireccionando a home.jsp");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());
        }
    }

}
