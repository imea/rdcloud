/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.implementaion.CommentDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PhotoDAO;
import com.photopartage.tp.maximfluieraru.model.Comment;

import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.Photo;
import com.photopartage.tp.maximfluieraru.model.User;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "DeletePhoto", urlPatterns = {"/DeletePhoto"})
public class DeletePhoto extends HttpServlet {

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

        int photo_id = Integer.parseInt((String) request.getParameter("lblPhoto"));

        User user = (User) session.getAttribute("user");

        ArrayList<Photo> listph = user.getUser_photos();
        Photo p = null;
        boolean remove = false;
        for (Photo ph : listph) {
            if (ph.getPhoto_id() == photo_id) {

                ArrayList<Comment> listC = ph.getPhoto_comments();

                if (listC.size()>0){
                    CommentDAO cdao = new CommentDAO();
                    for (Comment c : listC){
                        cdao.delete(c);
                    }
                }

                (new PhotoDAO()).delete(ph);
                p = ph;
                remove = true;
                break;
            }
        }
        if (remove) {
            listph.remove(p);
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
