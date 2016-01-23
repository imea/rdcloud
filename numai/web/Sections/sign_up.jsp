<%-- 
    Document   : sign_up
    Created on : 2015-10-18, 16:04:33
    Author     : Maxim
--%>

<%@page import="com.photopartage.tp.maximfluieraru.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<fieldset>
    <legend><strong>Inscription</strong></legend>
    </br></br>
    <% User user = (User) session.getAttribute("user");
        if (user == null) {%>


    <div class="container">
        <div class="row centered-form">
            <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Créer un compte, c'est gratuit, simple et rapide. <small>photopartage.com</small></h3>
                    </div>
                    <div class="panel-body">
                        <form action="./" method="post">
                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="user_sign_up" id="first_name" class="form-control input-sm" placeholder="First Name">
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="user_sign_up" id="last_name" class="form-control input-sm" placeholder="Last Name">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <input type="email" name="user_sign_up" id="email" class="form-control input-sm" placeholder="Email Address">
                            </div>

                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="password" name="user_sign_up" id="password" class="form-control input-sm" placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <input type="password" name="user_sign_up" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
                                    </div>
                                </div>
                            </div>

                            <button name="btn" value="signUp" class="btn btn-info btn-block">Enregistrez-moi</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%} else {%>
    <h2 class="bg-success">Vous êtes inscrit</h2>
    <%}%>
</fieldset>
