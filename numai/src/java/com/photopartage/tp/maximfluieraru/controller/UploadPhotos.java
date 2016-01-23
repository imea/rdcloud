/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photopartage.tp.maximfluieraru.controller;

import com.photopartage.tp.maximfluieraru.dao.HelperContext;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO;
import com.photopartage.tp.maximfluieraru.dao.implementaion.PhotoDAO;
import com.photopartage.tp.maximfluieraru.model.Page;
import com.photopartage.tp.maximfluieraru.model.Photo;
import com.photopartage.tp.maximfluieraru.model.User;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.sql.Timestamp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Maxim
 */
@WebServlet(name = "UploadPhotos", urlPatterns = {"/UploadPhotos"})
@MultipartConfig
public class UploadPhotos extends HttpServlet {

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
            throws ServletException, IOException{
        final PrintWriter writer = response.getWriter();

        HttpSession session = request.getSession();
        String pageId = "4";
        Page page = (new PageDAO()).find(pageId);
        session.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }


        String path = this.getServletContext().getRealPath("") + "ImageContext";

        final Part filePart = request.getPart("uploadImg");

        if (filePart.getSize() == 0) {
            rd.forward(request, response);
        }
        String fileName = HelperContext.getFileName(filePart);

        String user_email = user.getUser_email();

        Timestamp ts = HelperContext.toMySqlDateNow();

        OutputStream outStream = null;
        InputStream inputStream = null;
        File file = null;

        try {

            file = new File(path + File.separator + fileName);

            outStream = new FileOutputStream(file);
            inputStream = filePart.getInputStream();

            int read = 0;

            final byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outStream.write(bytes, 0, read);
            }
            outStream.flush();

        } catch (FileNotFoundException e) {
            writer.println("<br/> ERREUR: " + e.getMessage());
        } finally {
            if (outStream != null) {
                outStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

            long fileSize = file.length();
            
           

            Photo photo = new Photo(fileName, fileSize, user_email);
            
             
            BufferedImage buffimg = ImageIO.read(file);
             
            buffimg.flush();
          
            photo.setBufferedImage(buffimg);
            

            if (new PhotoDAO().insert(photo)) {
                
                user.getUser_photos().add(photo);
                file.delete();
                
            }else{
                writer.print("Error");
            }
            
           

            response.sendRedirect("index.jsp");
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
