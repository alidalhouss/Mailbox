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
@Table(name = "messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findByIdMessage", query = "SELECT m FROM Message m WHERE m.idMessage = :idMessage"),
    @NamedQuery(name = "Message.findByContent", query = "SELECT m FROM Message m WHERE m.content = :content"),
    @NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead"),
    @NamedQuery(name = "Message.findByCreatedAt", query = "SELECT m FROM Message m WHERE m.createdAt = :createdAt")})
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MESSAGE")
    private Integer idMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CONTENT")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_READ")
    private boolean isRead;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "ID_USER_FROM", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private User idUserFrom;
    @JoinColumn(name = "ID_USER_TO", referencedColumnName = "ID_USER")
    @ManyToOne(optional = false)
    private User idUserTo;

    public Message() {
    }

    public Message(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public Message(Integer idMessage, String content, boolean isRead, Date createdAt) {
        this.idMessage = idMessage;
        this.content = content;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public Integer getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (idMessage != null ? idMessage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.idMessage == null && other.idMessage != null) || (this.idMessage != null && !this.idMessage.equals(other.idMessage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "directory.model.jpa.Message[ idMessage=" + idMessage + " ]";
    }
    
}
