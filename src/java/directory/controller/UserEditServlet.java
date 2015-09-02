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
import directory.model.ejb.UserFacadeLocal;
import directory.model.jpa.NewsGroup;
import directory.model.jpa.User;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "UserEditServlet", urlPatterns = {"/directory/user/edit/*"})
public class UserEditServlet extends HttpServlet {
    @EJB
    UserFacadeLocal userBean;
    @EJB
    NewsGroupFacadeLocal newsGroupBean;
    
    User user = new User();
    NewsGroup newsGroup = new NewsGroup();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserEditServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserEditServlet at " + request.getContextPath() + "</h1>");
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
        String user_id = request.getParameter("user_id");
        HttpSession session = request.getSession();
        User userCA = (User) session.getAttribute("userObj");
        if(user_id != null && !user_id.isEmpty()) {
            user = userBean.find(Integer.parseInt(user_id));
            newsGroup = newsGroupBean.find(user.getIdNewsgroup().getIdNewsgroup());
            request.setAttribute("user", user);
            request.setAttribute("newsGroup", newsGroup);
        } else {
            List<NewsGroup> newsGroups = newsGroupBean.findAll();
            request.setAttribute("newsGroups", newsGroups);
        }
        
        /* Redirect to user-form */
//        request.setAttribute("connectedAs", userCA.getUsername());
        getServletContext().getRequestDispatcher("/directory/user/user-form.jsp").forward(request, response);
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
        String userName = request.getParameter("username");
        String mailboxName = request.getParameter("mailbox_name");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String idUser = request.getParameter("user_id");
        boolean writeRight = false;
        if("on".equals(request.getParameter("write_right"))) writeRight = true;
        boolean readRight = false;
        if("on".equals(request.getParameter("read_right"))) readRight = true;
        
        
        /** Get the news group id **/
        String newsGroupId = request.getParameter("id_newsgroup");
        
        /** Create the object NewsGroup **/
        newsGroup = newsGroupBean.find(Integer.parseInt(newsGroupId));
        
        
        if(idUser == null || idUser.isEmpty()) {
            user.setIdNewsgroup(newsGroup);
            user.setIdUser(Integer.SIZE);
            user.setMailbox(mailboxName);
            user.setPassword(password);
            user.setReadRight(readRight);
            user.setWriteRight(writeRight);
            user.setRole(Integer.parseInt(role));
            user.setUsername(userName);
            
            /** Persist user into the database **/
            try {
                userBean.create(user);
                request.setAttribute("messageContent", "The entry was created successfully");
                request.setAttribute("messageStatus", "success");
            } catch (Exception e) {
                request.setAttribute("messageContent", "An error occured: " + e.getMessage());
                request.setAttribute("messageStatus", "danger");
            }
            
            
            
            
        } else {
            user.setMailbox(mailboxName);
            user.setUsername(userName);
            user.setReadRight(readRight);
            user.setWriteRight(writeRight);
            user.setRole(Integer.parseInt(role));
            user.setIdNewsgroup(newsGroup);
            user.setIdUser(Integer.parseInt(idUser));
            /** Edit the user into the database **/
            try {
                userBean.edit(user);
                request.setAttribute("messageContent", "The entry was updated successfully");
                request.setAttribute("messageStatus", "success");
            } catch (Exception e) {
               request.setAttribute("messageContent", "An error occured: " + e.getMessage());
               request.setAttribute("messageStatus", "error");
            }
        }
        HttpSession session = request.getSession();
        User userCA = (User) session.getAttribute("userObj");
        request.setAttribute("connectedAs", userCA.getUsername());
        request.setAttribute("userList", userBean.findAll());
        request.setAttribute("newsGroups", newsGroupBean.findAll());
        getServletContext().getRequestDispatcher("/directory/user/view.jsp").forward(request, response);
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
