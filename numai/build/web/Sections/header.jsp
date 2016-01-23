

<%@page import="com.photopartage.tp.maximfluieraru.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : header
    Created on : 2015-10-18, 13:30:05
    Author     : Maxim
--%>

<header>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <div class="head">
                        <div class="row-fluid">
                            <div class="span12">
                                <div class="span6">
                                    <h1 class="muted active">photopartage.com</h1>
                                </div>

                                <nav class="navbar">
                                    <div class="container-fluid">
                                        <ul class="navbar-form">

                                            <li class="btn pull-left alert-success"><a href="./Acceuil?pageId=1">Acceuil</a></li>

                                            <% User user = (User) session.getAttribute("user");
                                                if (user == null) {
                                            %>

                                            <li class="btn pull-right alert-success" ><a href="./sign_in?pageId=2">Sign In</a></li>
                                            <li class="btn pull-right alert-success" ><a href="./sign_up?pageId=3">Sign Up</a></li>

                                            <%} else {%>

                                            <li class="btn pull-right alert-success" ><a href="./log_out?pageId=5">Log Out</a></li>
                                            <li class="btn pull-right alert-success" ><a href="./user_area?pageId=4"><%= user.getUser_first_name()%></a></li>

                                            <%}%>
                                        </ul>
                                    </div>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr />

</header>
