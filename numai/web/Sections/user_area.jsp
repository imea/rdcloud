<%-- 
    Document   : user_area
    Created on : 2015-10-31, 21:01:23
    Author     : Maxim
--%>

<%@page import="com.photopartage.tp.maximfluieraru.model.UserFriend"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Page"%>
<%@page import="com.photopartage.tp.maximfluieraru.dao.implementaion.PageDAO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Friend"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.Photo"%>
<%@page import="com.photopartage.tp.maximfluieraru.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% User user = (User) session.getAttribute("user");

    if (user == null) {

        Page pg = new PageDAO().find("5");
        session.setAttribute("page", pg);
        response.sendRedirect("index.jsp");
        return;
    }
%>

<div class="container">
    <div  class="col-lg-3 col-sm-4 col-xs-6 navbar-fixed-top">
        <form class="form-inline" role="form" action="./" method="post" >
            <div class="form-group">
                <input class="form-control" name="email_friend" type="email" placeholder="ami@mail.com" />
            </div>
            <div class="form-group">
                <button class="btn btn-default" name="btn" value="addFriend" >Ajouter Ami</button>
            </div>
        </form>
    </div> 
    </br>    

    <div class="row">
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <form class="form-inline" role="form" action="./"  method="POST" enctype="multipart/form-data" >
                    <div class="caption">
                        <input   class="update-split update-info" type="file" accept="image/*" name="uploadImg" id="file" title="Téléverser une photo"/>
                        <button  class="form-inline" name="btn" value="upload" >Publier</button>     
                    </div>
                </form>
            </div>
        </div>
    </div>  

</div> 

<div class="container">
    <div class="row">

        <div class="span4 badge">

            <p>
                <i class="icon-envelope"></i> <%= user.getUser_email()%> <br>
                <i class="icon-globe"></i><%= user.getUser_first_name()%>  <br>
                <i class="icon-gift"></i>Membre depuis:  <%= user.getUser_created_date()%>
            </p>
        </div>


    </div>
</div>

<div class="container">
    <div class="row">
        <div class="row ">
            <%

                Iterator itr = user.getUser_photos().iterator();
                Photo photo = null;
                Comment comment = null;
                while (itr.hasNext()) {
                    photo = (Photo) itr.next();
                    Iterator itrc = photo.getPhoto_comments().iterator();

            %>            

            <div style="max-width: 100%;" class="col-xs-18 col-sm-6 col-md-3 btn btn-primary" >
                <form method="post" action="./"> 
                    <input hidden="true" name="lblPhoto" value="<%= photo.getPhoto_id()%>" />
                    <label for="lbl" class="label-warning">
                        <%= photo.getImage_path()%>
                    </label>
                    <button  id ="lbl" class="pull-right" id="signINform" name="btn" value="deletePhoto" >X</button>
                </form> 

                <%
                    String ext = photo.getImage_path().substring(photo.getImage_path().length() - 3);

                    BufferedImage image = photo.getBufferedImage();// base de donnees 

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    ImageIO.write(image, ext, baos);

                    baos.flush();

                    byte[] imageInByteArray = baos.toByteArray();

                    baos.close();

                    String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
                    String src = "data:image/" + ext + ";" + "base64," + b64;
                %>

                <img  alt="<%= photo.getImage_path()%>" class="thumbnail img-responsive" title="<%= photo.getPhoto_id()%>" src="<%=src%>"/>


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

                    <form class="form-inline" action="./" method="post" >

                        <div class="form-group">
                            <input hidden="true" name="commentIdPhoto" value='<%=photo.getPhoto_id()%>' />
                            <textarea name="textComment"  class="form-control" placeholder="Your comments" ></textarea>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-default" name="btn" value="comment">Ajouter</button>
                        </div>
                    </form>                   
                </div>

                <%
                    String tag = photo.getTag();
                    switch (tag) {
                        case "public":
                %>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="protected" value="protected" class="btn btn-group-xs" >Protected</button>
                </form>

                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="private" value="private" class="btn btn-group-xs " >Private</button>
                </form>

                <%
                        break;
                    case "private":
                %>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="protected" value="protected" class=" btn btn-group-xs " >Protected</button>
                </form>

                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="public" value="public" class=" btn btn-group-xs ">Public</button>
                </form>
                <%
                        break;
                    case "protected":
                %>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="private" value="private" class="btn btn-group-xs" >Private</button>
                </form>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="public" value="public" class="  btn btn-group-xs ">Public</button>
                </form>
                <%
                        break;
                    default:
                %>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="protected" value="protected" class="btn btn-group-xs" >Protected</button>
                </form>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="private" value="private" class="btn btn-group-xs" >Private</button>
                </form>
                <form class="form-inline" role="form" action="./" method="post" >
                    <input hidden="true" name="idPhoto" value="<%= photo.getPhoto_id()%>" />
                    <button name="btn" title="public" value="public" class="btn btn-group-xs">Public</button>
                </form>
                <%
                            break;
                    }


                %>

            </div>
            <div class="separator">
            </div>

            <%                }
            %>

        </div>
    </div>         
</div>


<hr />

<div class="container">
    <div class="row">
        <div class="row ">
            <%
                Iterator itrS = user.getUser_friends_photos().iterator();
                Photo p = null;
                Comment c = null;
                while (itrS.hasNext()) {
                    p = (Photo) itrS.next();
                    Iterator itrc = p.getPhoto_comments().iterator();

            %>            

            <div style="max-width: 100%;" class="col-xs-18 col-sm-6 col-md-3 btn btn-primary" >

                <%                    String exts = p.getImage_path().substring(p.getImage_path().length() - 3);

                    BufferedImage img = p.getBufferedImage();// base de donnees 

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    ImageIO.write(img, exts, baos);

                    baos.flush();

                    byte[] imageInByteArray = baos.toByteArray();

                    baos.close();

                    String b64s = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
                    String src = "data:image/" + exts + ";" + "base64," + b64s;
                %>

                <img  alt="<%= p.getImage_path()%>" class="thumbnail img-responsive" title="<%= p.getPhoto_id()%>" src="<%=src%>"/>


                <div class="caption">

                    <ul class="commentList">

                        <li>
                            <div class="commenterImage">

                            </div>
                            <div class="commentText">
                                <span class="date sub-text" ><%= p.getImage_path()%></span>
                            </div>
                        </li>
                        <% while (itrc.hasNext()) {
                                c = (Comment) itrc.next();
                        %>
                        <li>
                            <div class="commentText">
                                <span class="date sub-text" ><%=c.getComment_user_email()%></span>
                                <span class="date sub-text" ><%=c.getComment_created_date()%></span>
                            </div>
                            <div class="commenterImage">
                                <%= c.getComment_text()%>
                            </div>
                        </li>
                        <%}%>
                    </ul>

                    <form class="form-inline" action="./" method="post" >

                        <div class="form-group">
                            <input hidden="true" name="commentIdPhoto" value='<%=p.getPhoto_id()%>' />
                            <textarea name="textComment"  class="form-control" placeholder="Your comments" ></textarea>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-default" name="btn" value="comment">Ajouter</button>
                        </div>
                    </form>                   
                </div>
            </div>
            <div class="separator">
            </div>

            <%                }
            %>

        </div>
    </div>         
</div>

<hr />

<div class="container">
    <div class="row" >
        <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Demande d'amitié envoyé</div>

                <!-- List group -->
                <ul class="list-group">
                    <%
                        for (UserFriend uf : user.getUser_friends_UnconfirmedInvitation()) {
                            String ufEmail = uf.getFriend();
                    %>
                    <li class="list-group-item">

                        <form method="post" action="./"> 
                            <input hidden="true" name="lblFriend" value="<%= ufEmail%>" />
                            <input hidden="true" name="unconfirmed" value="true" />
                            <label for="lbl" class="label-warning">
                                <%= ufEmail%>
                            </label>
                            <button  id ="lbl" class="pull-right" id="signINform" name="btn" value="deleteFriend" >X</button>
                        </form> 

                    </li>

                    <%}%>
                </ul>
            </div>            
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Invitation d'amitié</div>

                <!-- List group -->
                <ul class="list-group">
                    <%
                        for (UserFriend uf : user.getUser_friends_RecivedInvitation()) {
                            String ufEmail = uf.getFriend();
                    %>
                    <li class="list-group-item">

                        <form method="post" action="./"> 
                            <input hidden="true" name="email_friend" value="<%=ufEmail%>" />
                            <input hidden="true" name="confirm" value="<%= ufEmail%>" />
                            <label for="lbl" class="label-warning">
                                <%= ufEmail%>
                            </label>
                            <button  id ="lbl" class="pull-right" id="signINform" name="btn" value="addFriend" >+</button>
                        </form> 

                    </li>

                    <%}%>
                </ul>
            </div>            
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-4 col-sm-offset-3 col-md-offset-4">
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Amis</div>

                <!-- List group -->
                <ul class="list-group">
                    <%
                        for (UserFriend uf : user.getUser_friends()) {
                            String ufEmail = uf.getFriend();
                    %>
                    <li class="list-group-item">


                        <form method="post" action="./"> 
                            <input hidden="true" name="lblFriend" value="<%= ufEmail%>" />
                            <label for="lbl" class="label-warning">
                                <%= ufEmail%>
                            </label>
                            <button  id ="lbl" class="pull-right" id="signINform" name="btn" value="deleteFriend" >X</button>
                        </form> 

                    </li>

                    <%}%>
                </ul>
            </div>            
        </div>  
    </div>
</div>

