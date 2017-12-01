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
import java.io.File;
import java.io.FileOutputStream;
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
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionCancion", urlPatterns = {"/Cancion"})

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200,
        maxFileSize = 1024 * 1024 * 200,
        maxRequestSize = 1024 * 1024 * 210
)

public class AccionCancion extends HttpServlet {

    @EJB
    CancionFacade cFacade;

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
                    nombre = s.substring(s.indexOf("\"") + 1, s.length() - 1);
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

            resp.sendRedirect("doSession?session=todas&forward=Cancion");

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

                    Registro.LOG.info("Redireccionando a lista CANCION");
                    resp.sendRedirect("cancion.jsp");
//                    req.getRequestDispatcher("cancion.jsp").forward(req, resp);
                    break;
                case "descargar":
                    Integer idCancion = Integer.parseInt(req.getParameter("idCancion"));
                    try {
                        Cancion c = cFacade.find(idCancion);

                        String path = req.getServletContext().getRealPath("");
                        path = path + File.separator + "archivos";
                        path = path + File.separator;
                        File archivo = new File(path);
                        path = path + c.getNombre();
                        path = path + ".mp3";

                        if (!archivo.exists()) {
                            archivo.mkdir();
                        }

                        FileOutputStream fos = new FileOutputStream(path);

                        Registro.LOG.info("Guardado el archivo en: " + path);
                        fos.write(c.getDato());
                        fos.close();
                        resp.setHeader("Accept", "application/json");
                        resp.setHeader("Content-type", "application/json");
                        resp.setContentType("application/json");
                        String json = "{\"dato\": \"/archivos/"+ c.getNombre() +".mp3\" }";
                        
                        
                        resp.getWriter().write(json);
                            return;
                    } catch (Exception e) {
                    }
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
