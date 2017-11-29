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

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import pojo.ListaCanciones;
import pojo.ListaCanciones.CancionSimple;

/**
 * max_allowed_packet = 256M en my.ini y funciona, lo juro
 * @author nobodynuf
 */
@WebServlet(name = "AccionCancion", urlPatterns = {"/Cancion"})

@MultipartConfig(fileSizeThreshold = 1024*1024*200,
        maxFileSize = 1024*1024*200,
        maxRequestSize = 1024*1024*210
)

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
        
        try {
            String txtNombre = req.getParameter("txtNombre");
            int idArtista = Integer.parseInt(req.getParameter("listaArtistas"));
            int idAlbum = Integer.parseInt(req.getParameter("listaAlbum"));
            
            Part filePart = req.getPart("fileArchivo");
            byte[] p = new byte[filePart.getInputStream().available()];
            String contentDisp = filePart.getHeader("content-disposition");
            String[] items = contentDisp.split(";");
            String nombre = null;
            
            for (String s : items) {
                if (s.trim().startsWith("filename")) {
                    nombre = s.substring(s.indexOf("\"")+1, s.length()-1);
                }
            }
            
            if (!(nombre.substring(nombre.indexOf("."), nombre.length())).equals(".mp3")) {
                throw new Exception("Unsupported file format");
            }
            
            filePart.getInputStream().read(p);
            Cancion ca = new Cancion();
            ca.setId(0);
            ca.setDato(p);
            ca.setIdAlbum(idAlbum);
            ca.setIdArtista(idArtista);
            ca.setNombre(txtNombre);
            ca.setIdUsuario(((Usuario) req.getSession().getAttribute("usuario")).getId());
            
            cFacade.create(ca);
            
            req.getRequestDispatcher("cancion.jsp").forward(req, resp);
            
        } catch (Exception e) {
        }
        
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

                    // se crean objetos en la sesion para agilizar su acceso luego
                    List<Artista> listaArtista = new ArrayList<>();
                    List<Artista> otraListaArtista = aFacade.findAll();
                    
                    List<Cancion> laOtraLista = cFacade.findAll();
                    // usando ListaCanciones para convertir las canciones en algo simple
                    ListaCanciones listaCancionSimple = new ListaCanciones();
                    List<CancionSimple> listaConvertida = listaCancionSimple.listaCancionSimple(laOtraLista);
                    List<CancionSimple> listaAMostrar = new ArrayList<CancionSimple>();
                     
                    List<Album> laOtraListaAlbum = alFacade.findAll();
                    List<Album> listaAlbum = new ArrayList<>();
                    
                    for (CancionSimple cancion : listaConvertida) {
                        if (cancion.getIdUsuario() == ((int) ((Usuario) req.getAttribute("usuario")).getId())) {
                            
                            listaAMostrar.add(cancion);
                        }
                    }
                    Registro.LOG.log(Level.INFO, "Llenada lista objeto sesion Cancion: {0}", listaAMostrar.size());
                    
                    // buscamos las canciones que esten enlazadas a un artista
                    
                    //SE CAE
                    for (CancionSimple cancion : listaConvertida) {
                        for (Artista artista : otraListaArtista) {
                            if (((int) artista.getId()) == ((int) cancion.getIdArtista())) {
                                listaArtista.add(artista);
                            }
                        }
                    }
                    Registro.LOG.info("Llenada lista objeto sesion Artista: "+listaArtista.size());
                    
                    for (Album album : laOtraListaAlbum) {
                        for (CancionSimple cancion : listaConvertida) {
                            if (((int) album.getId()) == ((int) cancion.getIdAlbum())) {
                                listaAlbum.add(album);
                                break;
                            }
                        }
                    }
                    
                    Registro.LOG.info("Llenada lista objeto sesion Albunes: "+listaAlbum.size());
                    
                    req.getSession().setAttribute("listaArtistas", otraListaArtista);
                    req.getSession().setAttribute("listaCanciones", listaAMostrar);
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
