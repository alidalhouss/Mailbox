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
@Table(name = "newsgroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NewsGroup.findAll", query = "SELECT n FROM NewsGroup n"),
    @NamedQuery(name = "NewsGroup.findByIdNewsgroup", query = "SELECT n FROM NewsGroup n WHERE n.idNewsgroup = :idNewsgroup"),
    @NamedQuery(name = "NewsGroup.findByName", query = "SELECT n FROM NewsGroup n WHERE n.name = :name")})
public class NewsGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_NEWSGROUP")
    private Integer idNewsgroup;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNewsgroup")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNewsgroup")
    private Collection<User> userCollection;

    public NewsGroup() {
    }

    public NewsGroup(Integer idNewsgroup) {
        this.idNewsgroup = idNewsgroup;
    }

    public NewsGroup(Integer idNewsgroup, String name) {
        this.idNewsgroup = idNewsgroup;
        this.name = name;
    }

    public Integer getIdNewsgroup() {
        return idNewsgroup;
    }

    public void setIdNewsgroup(Integer idNewsgroup) {
        this.idNewsgroup = idNewsgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNewsgroup != null ? idNewsgroup.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewsGroup)) {
            return false;
        }
        NewsGroup other = (NewsGroup) object;
        if ((this.idNewsgroup == null && other.idNewsgroup != null) || (this.idNewsgroup != null && !this.idNewsgroup.equals(other.idNewsgroup))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "directory.model.jpa.NewsGroup[ idNewsgroup=" + idNewsgroup + " ]";
    }
    
}
