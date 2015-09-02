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
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {
    @PersistenceContext(unitName = "MailboxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User lookupByUserName(String userName) {
        return (User) em.createNamedQuery("User.findByUsername").setParameter("username", userName).getSingleResult();
    }

    @Override
    public List<User> lookupByReadRightTrue() {
        return em.createNamedQuery("User.findByReadRight").setParameter("readRight", true).getResultList();
    }

    @Override
    public List<User> lookupByWriteRightTrue() {
        return em.createNamedQuery("User.findByWriteRight").setParameter("writeRight", true).getResultList();
    }
    
}
