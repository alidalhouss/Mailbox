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

import directory.model.jpa.NewsGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */
@Local
public interface NewsGroupFacadeLocal {

    void create(NewsGroup newsGroup);

    void edit(NewsGroup newsGroup);

    void remove(NewsGroup newsGroup);

    NewsGroup find(Object id);

    List<NewsGroup> findAll();

    List<NewsGroup> findRange(int[] range);

    int count();
    
}
