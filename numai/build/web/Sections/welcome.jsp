<%-- 
    Document   : welcome
    Created on : 2015-10-31, 16:01:06
    Author     : Maxim
--%>


<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Comment"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Photo"%>
<%@page import="java.util.Iterator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container">
    <div class="row">

        <%
            ArrayList<Photo> list = (ArrayList<Photo>) session.getAttribute("publicPhotos");

            if (list.size() > 0) {
                Iterator itr = list.iterator();

                Photo photo = null;
                Comment comment = null;
                while (itr.hasNext()) {
                    photo = (Photo) itr.next();
                    Iterator itrc = photo.getPhoto_comments().iterator();

        %>            

        <div class="col-xs-18 col-sm-6 col-md-3 btn btn-primary" >


            <%                String ext = photo.getImage_path().substring(photo.getImage_path().length() - 3);

                BufferedImage image = photo.getBufferedImage();// base de donnees 

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                ImageIO.write(image, ext, baos);

                baos.flush();

                byte[] imageInByteArray = baos.toByteArray();

                baos.close();

                String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
                String src = "data:image/" + ext + ";" + "base64," + b64;
            %>


            <img class="thumbnail img-responsive" src='<%=src%>' title="<%= photo.getPhoto_id()%>" />

            <div class="caption">
                <ul class="commentList">

                    <li>
                        <div class="commenterImage">

                        </div>
                        <div class="commentText">
                            <span class="date sub-text" ><%= photo.getImage_path()%></span>
                        </div>
                    </li>
                    <% while (itrc.hasNext()) {
                            comment = (Comment) itrc.next();
                    %>
                    <li>
                        <div class="commentText">
                            <span class="date sub-text" ><%=comment.getComment_user_email()%></span>
                            <span class="date sub-text" ><%=comment.getComment_created_date()%></span>
                        </div>
                        <div class="commenterImage">
                            <%= comment.getComment_text()%>
                        </div>
                    </li>
                    <%}%>
                </ul>


                <% if ((User) session.getAttribute("user") != null) {%>
                <form class="form-inline" action="./" method="post" >
                    <div class="form-group">
                        <input hidden="true" name="commentIdPhoto" value='<%=photo.getPhoto_id()%>' />
                        <textarea name="textComment"  class="form-control" placeholder="Your comments" ></textarea>
                    </div>

                    <div class="form-group">
                        <button class="btn btn-default" name="btn" value="comment">Ajouter</button>
                    </div>
                </form>
                <%}%>

            </div>
        </div>
        <div class="separator">
        </div>

        <%      }
            }
        %>
    </div>         
</div>


