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
package directory.model.ejb;

import directory.model.jpa.Post;
import directory.model.jpa.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> implements PostFacadeLocal {
    @PersistenceContext(unitName = "MailboxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

    @Override
    public List<Object[]> findPostWithUser() {
        return em.createQuery("SELECT u.username, p.content, p.createdAt FROM User u, Post p WHERE u.idUser = p. ORDER BY p.createdAt ASC").getResultList();
    }

    @Override
    public List<Object[]> findPostWithUserAndNewsGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> findReceivedPosts(Integer userId) {
        return em.createQuery("SELECT p FROM Post p WHERE (p.idUserTo.idUser = :userId)").setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Post> findDistinctPost() {
        return null;
    }
    
}
