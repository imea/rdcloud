<%-- 
    Document   : sign_in
    Created on : 2015-10-18, 16:04:16
    Author     : Maxim
--%>

<%@page import="com.photopartage.tp.maximfluieraru.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="modal-body">
    <fieldset>
        <legend><strong>Authentifiez-vous</strong></legend></br>


        <% User user = (User) session.getAttribute("user");

            if (user == null) {
        %>
        <div class="container">    
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign In</div>
                    </div>  

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                        <form id="signINform" class="form-horizontal" role="form" action="./" method="post" >

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>

                                <input name="id_in" id="id_in" type="text" placeholder="Saisir votre courriel" />
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>

                                <input name="password_in" id="password_in" placeholder="Mot de passe" type="password" />
                            </div>

                            <div class="input-group">
                                <div class="checkbox">
                                    <label>
                                        <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                    </label>
                                </div>
                            </div> 

                            <div style="margin-top:10px" class="form-group">
                                <div class="col-sm-12 controls">

                                    <button class="btn-success" name="btn" value="signIn">Entrer</button>		
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        Pas de compte avec nous!
                                        <li class="btn"><a href="./sign_up?pageId=3">Sign Up</a></li>
                                    </div>
                                </div>
                            </div>    
                        </form>
                    </div>                     
                </div>  
            </div>
        </div>


        <% } else {
        %>
        <h2 class="checkbox" >Vous êtes connecté</h2>
        <%}
        %>
    </fieldset>

</div>


