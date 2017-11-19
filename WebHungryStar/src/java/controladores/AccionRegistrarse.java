/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;

import facades.UsuarioFacade;
import facades.LogFacade;
import entidades.Usuario;
import entidades.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.Encriptador;

import pojo.Registro;

/**
 *
 * @author nobodynuf
 */
@WebServlet(name = "AccionRegistrarse", urlPatterns = {"/AccionRegistrarse"})
public class AccionRegistrarse extends HttpServlet {

    @EJB
    UsuarioFacade uFacade;

    @EJB
    LogFacade lFacade;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Usuario user = new Usuario();

            user.setNombre(req.getParameter("txtNombre"));
            user.setPass(Encriptador.encriptar(req.getParameter("txtPassword")));
            
            
            
            if (user.getPass().isEmpty()) {
                throw new Exception("La contraseña, en el servlet, no se pudo guardar");
            }
                        
            user.setUsername(req.getParameter("txtUsername"));

            for (Usuario usuario : uFacade.findAll()) {
                if (usuario.getUsername() == user.getPass()) {
                    throw new Exception("Usuario duplicado");
                }
            }
            uFacade.create(user);
            lFacade.create(
                    new Log(Date.from(Instant.now()), user.getId())
            );
            Registro.LOG.info("Creado el usuario");
            
            req.getSession().setAttribute("usuario", user);
            Registro.LOG.info("Creada session para respectivo usuario");
            
            Registro.LOG.info("Intentando redireccionar");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
            
        } catch (Exception e) {
            Registro.LOG.severe(e.getMessage());

        }
    }

    
    
}
