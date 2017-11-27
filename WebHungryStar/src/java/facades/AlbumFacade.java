/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */

package facades;

import entidades.Album;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge Manriquez
 */
@Stateless
public class AlbumFacade extends AbstractFacade<Album> {

    @PersistenceContext(unitName = "WebHungryStarPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlbumFacade() {
        super(Album.class);
    }

    public List<Album> businessMethod() {
        return super.findAll();
    }
    

}
