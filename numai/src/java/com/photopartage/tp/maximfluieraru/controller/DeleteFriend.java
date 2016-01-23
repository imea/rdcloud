/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.UserFriendDAO;
import com.photopartage.tp.maximfluieraru.model.Friend;
import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.User;
import com.photopartage.tp.maximfluieraru.model.UserFriend;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxim
 */
@WebServlet(name = "DeleteFriend", urlPatterns = {"/DeleteFriend"})
public class DeleteFriend extends HttpServlet {

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

        HttpSession session = request.getSession();
        String pageId = "4";
        Page page = (new PageDAO()).find(pageId);
        session.setAttribute("page", page);

        String friend_email = (String) request.getParameter("lblFriend");
        boolean unconfirmed = false;
        if (null!= request.getParameter("unconfirmed")){
            unconfirmed = true;
        }

        User user = (User) session.getAttribute("user");
        ArrayList<UserFriend> listf = (unconfirmed ? user.getUser_friends_UnconfirmedInvitation():user.getUser_friends());
        UserFriend uf = null;
        boolean remove = false;
        for (UserFriend f : listf) {

            if (f.getFriend().equals(friend_email)) {

                (new UserFriendDAO()).delete(f);
                remove = true;
                uf = f;
                break;
            }
        }
        
        if (remove) {
            listf.remove(uf);
        }

        response.sendRedirect("index.jsp");
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
