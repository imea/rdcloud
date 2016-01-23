/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.model.Page;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maxim
 */
@WebServlet(name = "index", urlPatterns = {"/acceuil"})
@MultipartConfig
public class index extends HttpServlet {

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

        HttpSession session = request.getSession(true);
        Page page = (Page) session.getAttribute("page");

        if (page == null || page.getPage_id() == null || page.getPage_id().equals("-1")) {
            page = new Page();
            page.setPage_id("1");
        }

        String pageId = (String) request.getParameter("pageId");

        if (pageId != null) {
            page.setPage_id(pageId);
        }

        String btn = (String) request.getParameter("btn");
        String ppp[] = {"public", "protected", "private"};
        String direction = "index.jsp";
        RequestDispatcher rd;

        if (btn != null) {
            switch (btn) {
                case "signIn":
                    direction = "SignIn";
                    break;
                case "signUp":
                    direction = "SignUp";
                    break;
                case "upload":
                    direction = "UploadPhotos";

                    break;
                case "addFriend":
                    direction = "AddFriend";
                    break;

                case "comment":
                    direction = "AddComment";
                    break;

                case "deleteFriend":
                    direction = "DeleteFriend";
                    break;

                case "deletePhoto":
                    direction = "DeletePhoto";
                    break;

                default:

                    for (String p : ppp) {
                        if (p.equals(btn)) {
                            direction="ChangeTag";
                            break;
                        }
                    }

                    break;
            }
        } else {

            page = (new PageDAO()).find(page.getPage_id());

            session.setAttribute("page", page);

            if ("5".equals(page.getPage_id())) {
                direction = "LogOut";
            }
        }

        rd = request.getRequestDispatcher(direction);

        rd.forward(request, response);

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
