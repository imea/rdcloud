/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.HelperContext;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.UserDAO;
import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maxim
 */
@WebServlet(name = "SignUp", urlPatterns = {"/SignUp"})
public class SignUp extends HttpServlet {

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

        String[] newUserData = request.getParameterValues("user_sign_up");
        String direction = "index.jsp";
        RequestDispatcher rd;
        Page page;

        if (HelperContext.hasEmptyValue(newUserData)) {
            rd = request.getRequestDispatcher(direction);
            rd.forward(request, response);
            return;
        }
        UserDAO userDao = new UserDAO();
        User user = new User(newUserData);
        String password = HelperContext.encryptSha1(user.getUser_password().trim());
        user.setUser_password(password);

        if (userDao.insert(user)) {
            String pageId = "4";
            page = (new PageDAO()).find(pageId);
            
            request.getSession().setAttribute("page", page);
            request.getSession().setAttribute("user", user);
        }

        response.sendRedirect("index.jsp");
//        rd = request.getRequestDispatcher(direction);
//        rd.forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
