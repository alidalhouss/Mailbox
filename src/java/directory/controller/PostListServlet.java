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
package directory.controller;

import directory.model.ejb.NewsGroupFacadeLocal;
import directory.model.ejb.PostFacadeLocal;
import directory.model.ejb.UserFacadeLocal;
import directory.model.jpa.Post;
import directory.model.jpa.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */

@Stateless
@WebServlet(name = "PostListServlet", urlPatterns = {"/directory/newsgroup"})
public class PostListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB 
    PostFacadeLocal postBean;
    
    @EJB
    NewsGroupFacadeLocal newsGroupBean;
    
    @EJB
    UserFacadeLocal userBean;
    
    Post post = new Post();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostListServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User userCA = (User) session.getAttribute("userObj");

        List<Post> posts = postBean.findReceivedPosts(userCA.getIdUser());
        request.setAttribute("postList", posts);
        request.setAttribute("connectedAs", userCA);
        getServletContext().getRequestDispatcher("/directory/post/view.jsp").forward(request, response);
        for(Post postItem : posts) {
            postItem.setIsRead(true);
            postBean.edit(postItem);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User connectedUser = (User) session.getAttribute("userObj");
        User user = userBean.find(connectedUser.getIdUser());
        String postContent = request.getParameter("post-content");
        if(postContent.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/directory/newsgroup");
        } else {
            post = new Post();
            post.setContent(postContent);
            post.setIdNewsgroup(user.getIdNewsgroup());
            post.setIdUserFrom(user);

            post.setIsRead(false);
            Date now = new Date();
            Timestamp timeStamp = new Timestamp(now.getTime());
            post.setCreatedAt(timeStamp);
            List<User> userList = userBean.findAll();
            for (User userItem : userList) {
                post.setIdUserTo(userItem);
                postBean.create(post);
            }

            response.sendRedirect(request.getContextPath() + "/directory/newsgroup");
            
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
