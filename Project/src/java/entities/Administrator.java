/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 1
 */
@Entity
@Table(name = "administrator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT a FROM Administrator a"),
    @NamedQuery(name = "Administrator.findByAdministratorId", query = "SELECT a FROM Administrator a WHERE a.administratorId = :administratorId"),
    @NamedQuery(name = "Administrator.findByAdministratorName", query = "SELECT a FROM Administrator a WHERE a.administratorName = :administratorName"),
    @NamedQuery(name = "Administrator.findByAdministratorLogin", query = "SELECT a FROM Administrator a WHERE a.administratorLogin = :administratorLogin"),
    @NamedQuery(name = "Administrator.findByAdministratorPassword", query = "SELECT a FROM Administrator a WHERE a.administratorPassword = :administratorPassword")})
public class Administrator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administrator_id")
    private Integer administratorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "administrator_name")
    private String administratorName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "administrator_login")
    private String administratorLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "administrator_password")
    private String administratorPassword;

    public Administrator() {
    }

    public Administrator(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public Administrator(Integer administratorId, String administratorName, String administratorLogin, String administratorPassword) {
        this.administratorId = administratorId;
        this.administratorName = administratorName;
        this.administratorLogin = administratorLogin;
        this.administratorPassword = Integer.toString(administratorPassword.hashCode());
    }

    public Integer getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Integer administratorId) {
        this.administratorId = administratorId;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getAdministratorLogin() {
        return administratorLogin;
    }

    public void setAdministratorLogin(String administratorLogin) {
        this.administratorLogin = administratorLogin;
    }

    public String getAdministratorPassword() {
        return administratorPassword;
    }

    public String getAdministratorPasswordHash() {
        return administratorPassword;
    }

    public void setAdministratorPassword(String administratorPassword) {
        this.administratorPassword = Integer.toString(administratorPassword.hashCode());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administratorId != null ? administratorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrator)) {
            return false;
        }
        Administrator other = (Administrator) object;
        if ((this.administratorId == null && other.administratorId != null) || (this.administratorId != null && !this.administratorId.equals(other.administratorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return administratorId + ". " + administratorName;
    }
}
