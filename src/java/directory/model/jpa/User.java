/*
 * Copyright (C) 2015 Ali DALHOUSS <ali.dalhouss@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package directory.model.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByMailbox", query = "SELECT u FROM User u WHERE u.mailbox = :mailbox"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByReadRight", query = "SELECT u FROM User u WHERE u.readRight = :readRight"),
    @NamedQuery(name = "User.findByWriteRight", query = "SELECT u FROM User u WHERE u.writeRight = :writeRight")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_USER")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLE")
    private int role;
    @Size(min = 0, max = 255)
    @Column(name = "MAILBOX")
    private String mailbox;
    @Size(min = 0, max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "READ_RIGHT")
    private boolean readRight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WRITE_RIGHT")
    private boolean writeRight;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserFrom")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserTo")
    private Collection<Post> postCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserFrom")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserTo")
    private Collection<Message> messageCollection1;
    @JoinColumn(name = "ID_NEWSGROUP", referencedColumnName = "ID_NEWSGROUP")
    @ManyToOne(optional = false)
    private NewsGroup idNewsgroup;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String username, int role, boolean readRight, boolean writeRight) {
        this.idUser = idUser;
        this.username = username;
        this.role = role;
        this.readRight = readRight;
        this.writeRight = writeRight;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getReadRight() {
        return readRight;
    }

    public void setReadRight(boolean readRight) {
        this.readRight = readRight;
    }

    public boolean getWriteRight() {
        return writeRight;
    }

    public void setWriteRight(boolean writeRight) {
        this.writeRight = writeRight;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<Post> getPostCollection1() {
        return postCollection1;
    }

    public void setPostCollection1(Collection<Post> postCollection1) {
        this.postCollection1 = postCollection1;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    public NewsGroup getIdNewsgroup() {
        return idNewsgroup;
    }

    public void setIdNewsgroup(NewsGroup idNewsgroup) {
        this.idNewsgroup = idNewsgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "directory.model.jpa.User[ idUser=" + idUser + " ]";
    }
    
}
