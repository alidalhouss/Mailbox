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
import directory.model.jpa.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ali DALHOUSS <ali.dalhouss@gmail.com>
 */
@WebServlet(name = "UserRemoveServlet", urlPatterns = {"/directory/user/remove/*"})
public class UserRemoveServlet extends HttpServlet {
    @EJB
    UserFacadeLocal userBean;
    @EJB
    NewsGroupFacadeLocal newsGroupBean;
    
    User user = new User();

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
            out.println("<title>Servlet UserRemoveServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRemoveServlet at " + request.getContextPath() + "</h1>");
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
        String idUser = request.getParameter("user_id");
        user = userBean.find(Integer.parseInt(idUser));

        try {
            userBean.remove(user);
            request.setAttribute("messageContent", "The entry was removed successfully");
            request.setAttribute("messageStatus", "success");
        } catch (Exception e) {
            request.setAttribute("messageContent", "An error occured: " + e.getMessage());
            request.setAttribute("messageStatus", "danger");
        }

        request.setAttribute("userList", userBean.findAll());
        request.setAttribute("newsGroups", newsGroupBean.findAll());
        getServletContext().getRequestDispatcher("/directory/user/view.jsp").forward(request, response);
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
        String idUser = request.getParameter("user_id");
        user = userBean.find(Integer.parseInt(idUser));
        
        userBean.remove(user);
        
        response.sendRedirect(request.getContextPath() + "/directory/user");
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
