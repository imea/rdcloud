<%-- 
    Document   : index
    Created on : 2015-10-15, 21:26:32
    Author     : Maxim
--%>

<%@page import="com.photopartage.tp.maximfluieraru.model.Page"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String title = "ERROR!";
    String bodySection = "Sections/error_404.jsp";

    Page pageS = (Page) session.getAttribute("page");
    title = pageS.getTitle();
    bodySection = "Sections/" + pageS.getSection() + ".jsp";
    
    if ("".equals(pageS.getSection())) {
        title = "ERROR!";
        bodySection = "Sections/error_404.jsp";
    }


%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
        <title><%= title%></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="Resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="Resources/css/contextStyle.css" rel="stylesheet" type="text/css"/>
        
        <!-- mon css ne fonction pas -->
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" />
    </head>

    <body class="modal-body" >

        <jsp:include page='Sections/header.jsp' />

        <main>
            <jsp:include page='<%= bodySection%>' />
        </main>

        <jsp:include page='Sections/footer.jsp' />

    </body>

</html>