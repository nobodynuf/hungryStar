/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package controladores;

import entidades.Log;
import entidades.Usuario;
import facades.LogFacade;
import facades.UsuarioFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Date;
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
    LogFacade lFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            Usuario user = uFacade.buscarPorUsername(req.getParameter("txtUsername"), Encriptador.encriptar(req.getParameter("txtPassword")));
            Registro.LOG.info("Servlet" + req.getParameter("txtUsername"));
            for (Usuario usuario : uFacade.findAll()) {
                if (usuario.getUsername().equals(req.getParameter("txtUsername"))
                        & usuario.getPass().equals(Encriptador.encriptar(req.getParameter("txtPassword")))) {
                    req.getSession().setAttribute("usuario", usuario);
                    Registro.LOG.log(Level.INFO, "Usuario: {0} - Auth correcto", usuario.getNombre());

                    lFacade.create(new Log(Date.from(Instant.now()), usuario.getId()));

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
            Registro.LOG.info("Redireccionando a home.jsp");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());
        }
    }

}
