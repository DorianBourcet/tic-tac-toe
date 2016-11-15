<%-- 
    Document   : index
    Created on : 2012-10-23, 20:17:01
    Author     : jycy
--%>


<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="resources/Basic_css_file.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" >
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Tic-tac-toe</title>
    </head>
    <body>   
        <%--<jsp:include page="Login.jsp" />--%>
        <jsp:include page="Menu.jsp" />
        
        <div id = "MainScreen">
            <jsp:include page="Grid.jsp" />
        </div>
    </body>
    
</html>
