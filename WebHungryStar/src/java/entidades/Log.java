/*
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jorge Manriquez
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByFechaIngreso", query = "SELECT l FROM Log l WHERE l.logPK.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Log.findByUsuarioIngreso", query = "SELECT l FROM Log l WHERE l.logPK.usuarioIngreso = :usuarioIngreso")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogPK logPK;

    public Log() {
    }

    public Log(LogPK logPK) {
        this.logPK = logPK;
    }

    public Log(Date fechaIngreso, int usuarioIngreso) {
        this.logPK = new LogPK(fechaIngreso, usuarioIngreso);
    }

    public LogPK getLogPK() {
        return logPK;
    }

    public void setLogPK(LogPK logPK) {
        this.logPK = logPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logPK != null ? logPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logPK == null && other.logPK != null) || (this.logPK != null && !this.logPK.equals(other.logPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Log[ logPK=" + logPK + " ]";
    }

}
