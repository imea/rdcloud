/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.implementaion.FriendDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.UserFriendDAO;
import com.photopartage.tp.maximfluieraru.model.Friend;
import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.User;
import com.photopartage.tp.maximfluieraru.model.UserFriend;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maxim
 */
@WebServlet(name = "AddFriend", urlPatterns = {"/AddFriend"})
public class AddFriend extends HttpServlet {

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

        String friendEmail = (String) request.getParameter("email_friend").trim();
        User user = (User) session.getAttribute("user");
        boolean confirm = false;

        if (null != request.getParameter("confirm")) {
            confirm = true;
        }

        String pageId = "4";
        Page page = (new PageDAO()).find(pageId);
        session.setAttribute("page", page);

        if (user != null) {
            if (!confirm) {

                if (friendEmail == null || friendEmail.equals("") || friendEmail.equals(user.getUser_email())) {
                    response.sendRedirect("index.jsp");
                    return;
                }
            }
        } else {
            response.sendRedirect("index.jsp");
        }

        Friend friend = new Friend(friendEmail);
        FriendDAO friendDAO = new FriendDAO();
        UserFriend userFriend = new UserFriend(user.getUser_email(), friend.getFriend_email());
        UserFriendDAO userFriendDao = new UserFriendDAO();

        if (friendDAO.find(friend)) {

            if (userFriendDao.find(userFriend)) {

                response.sendRedirect("index.jsp");
                return;
            } else {
                userFriendDao.insert(userFriend);
                if (confirm) {
                    user.getUser_friends().add(userFriend);
                    user.getUser_friends_RecivedInvitation().remove(userFriend);
                } else {
                    user.getUser_friends_UnconfirmedInvitation().add(userFriend);
                }

                response.sendRedirect("index.jsp");

                return;
            }
        }

        if (friendDAO.insert(friend) && !userFriendDao.insert(userFriend)) {
            friendDAO.delete(friend);
        }
        if (confirm) {
            user.getUser_friends().add(userFriend);
            user.getUser_friends_RecivedInvitation().remove(userFriend);
        } else {
            user.getUser_friends_UnconfirmedInvitation().add(userFriend);

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
