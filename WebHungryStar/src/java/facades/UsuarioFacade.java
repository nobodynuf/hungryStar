/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
package facades;

import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.Encriptador;
import pojo.Registro;

/**
 *
 * @author Jorge Manriquez
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "WebHungryStarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Deprecated
    public Usuario buscarPorUsername(String user, String pass) {
        Registro.LOG.info("ingresado al metodo FACE");
        for (Usuario usuario : this.findAll()) {
            Registro.LOG.info("ingresado al ciclo FACE");
            Registro.LOG.info("USER: " + usuario.getUsername());
            Registro.LOG.info("PASS: " + usuario.getPass());
            Registro.LOG.info(usuario.getUsername().equals(user)
                    & usuario.getPass().equals(Encriptador.encriptar(pass)) ? "SI" : "NOU");
            if (usuario.getUsername().equals(user)) {
                if (usuario.getPass().equals(Encriptador.encriptar(pass))) {
                    Registro.LOG.info(Encriptador.encriptar(pass));
                    return usuario;
                }
            }

        }
        return null;
    }

}
