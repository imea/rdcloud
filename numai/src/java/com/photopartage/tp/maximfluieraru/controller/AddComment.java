/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.implementaion.CommentDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.model.Comment;
import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.Photo;
import com.photopartage.tp.maximfluieraru.model.User;
import java.awt.JobAttributes;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddComment", urlPatterns = {"/AddComment"})
public class AddComment extends HttpServlet {

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
        String photo_id = (String) request.getParameter("commentIdPhoto");
        String textComment = ((String) request.getParameter("textComment")).trim();
        User user = (User) session.getAttribute("user");
         String pageId = ((Page) session.getAttribute("page")).getPage_id();

            Page page = (new PageDAO()).find(pageId);
            session.setAttribute("page", page);
        if (user != null) {

            String user_email = user.getUser_email();
            int p_id = Integer.parseInt(photo_id);

            if (textComment == null || textComment.trim().equals("")) {
                response.sendRedirect("index.jsp");
                return;
            }
            Comment comment = new Comment(textComment, user_email, p_id);

            (new CommentDAO()).insert(comment);

            switch (pageId) {
                case "1":
                    ArrayList<Photo> listP = (ArrayList<Photo>) session.getAttribute("publicPhotos");
                    for (Photo ph : listP) {
                        if (ph.getPhoto_id() == p_id) {
                            ph.getPhoto_comments().add(comment);
                        }
                    }
           
                    break;
                case "4":
                    ArrayList<Photo> listU = user.getUser_photos();
                    for (Photo ph : listU) {
                        if (ph.getPhoto_id() == p_id) {
                            ph.getPhoto_comments().add(comment);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("index.jsp");
        //request.getRequestDispatcher("index.jsp").forward(request, response);
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
