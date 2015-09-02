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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findByIdPost", query = "SELECT p FROM Post p WHERE p.idPost = :idPost"),
    @NamedQuery(name = "Post.findByIsRead", query = "SELECT p FROM Post p WHERE p.isRead = :isRead"),
    @NamedQuery(name = "Post.findByContent", query = "SELECT p FROM Post p WHERE p.content = :content"),
    @NamedQuery(name = "Post.findByCreatedAt", query = "SELECT p FROM Post p WHERE p.createdAt = :createdAt")})
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "ID_POST")
    private Integer idPost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_READ")
    private boolean isRead;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONTENT")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "ID_NEWSGROUP", referencedColumnName = "ID_NEWSGROUP")
    @ManyToOne(optional = false)
    private NewsGroup idNewsgroup;
    @JoinColumn(name = "ID_USER_FROM", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private User idUserFrom;
    @JoinColumn(name = "ID_USER_TO", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private User idUserTo;

    public Post() {
    }

    public Post(Integer idPost) {
        this.idPost = idPost;
    }

    public Post(Integer idPost, boolean isRead, String content, Date createdAt) {
        this.idPost = idPost;
        this.isRead = isRead;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public NewsGroup getIdNewsgroup() {
        return idNewsgroup;
    }

    public void setIdNewsgroup(NewsGroup idNewsgroup) {
        this.idNewsgroup = idNewsgroup;
    }

    public User getIdUserFrom() {
        return idUserFrom;
    }

    public void setIdUserFrom(User idUserFrom) {
        this.idUserFrom = idUserFrom;
    }

    public User getIdUserTo() {
        return idUserTo;
    }

    public void setIdUserTo(User idUserTo) {
        this.idUserTo = idUserTo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "directory.model.jpa.Post[ idPost=" + idPost + " ]";
    }
    
}
