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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jorge Manriquez
 */
@Embeddable
public class LogPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario_ingreso")
    private int usuarioIngreso;

    public LogPK() {
    }

    public LogPK(Date fechaIngreso, int usuarioIngreso) {
        this.fechaIngreso = fechaIngreso;
        this.usuarioIngreso = usuarioIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(int usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fechaIngreso != null ? fechaIngreso.hashCode() : 0);
        hash += (int) usuarioIngreso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogPK)) {
            return false;
        }
        LogPK other = (LogPK) object;
        if ((this.fechaIngreso == null && other.fechaIngreso != null) || (this.fechaIngreso != null && !this.fechaIngreso.equals(other.fechaIngreso))) {
            return false;
        }
        if (this.usuarioIngreso != other.usuarioIngreso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.LogPK[ fechaIngreso=" + fechaIngreso + ", usuarioIngreso=" + usuarioIngreso + " ]";
    }

}
