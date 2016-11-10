<%-- 
    Document   : index
    Created on : 2012-10-23, 20:17:01
    Author     : moumene
--%>
<%
    if (session.getAttribute("connecte")==null)  //non connecté
    {
%>
        
        <%//include login.jps ici avec : <jsp:include page="login.jsp" /> %>
        
<%
    }
%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css" integrity="sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi" crossorigin="anonymous">
    <link type="text/css" rel="stylesheet" href="resources/Basic_css_file.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Tic-tac-toe</title>
    </head>
    <body>   
    
<%        
        
        if (session.getAttribute("connecte")!=null)
        {
            out.println("<p class=\"resultat\">"+session.getAttribute("connecte")+", vous êtes connectés. "+
                        "<a href=\"logout.do?action=logout\">déconnexion</a></p>");

        }        
%>
        <jsp:include page="Menu.jsp" />
        <div id = "MainScreen">
            <%
        if (request.getAttribute("message")!=null)
        {
            out.println("<p class=\"errorMessage\">"+request.getAttribute("message")+"</p>");
        }
        String  ns1 = request.getParameter("nb1"),
                ns2 = request.getParameter("nb2"),
                ops = request.getParameter("operation"),
                selected=" selected=\"selected\"";
        if (ns1==null) ns1="";
        if (ns2==null) ns2="";
        %>
        <form action="abc.do" method="post">
            Nombre 1 : <input type="text" name="nb1" value="<%=ns1%>" />
            <select name="operation">
                <option value="">--choisir--</option>
                <option value="+" <%="+".equals(ops)?selected:""%>>+</option>
                <option value="-" <%="-".equals(ops)?selected:""%>>-</option>
                <option value="*" <%="*".equals(ops)?selected:""%>>*</option>
                <option value="/" <%="/".equals(ops)?selected:""%>>/</option>
            </select>
            Nombre 2 : <input type="text" name="nb2" value="<%=ns2%>" />
            <input type="submit" value=" = " />
        <%
        if (request.getAttribute("resultat")!=null)
        {
            //double x = ((Double)request.getAttribute("resultat")).doubleValue();
            out.println("<span class=\"resultat\">"+request.getAttribute("resultat")+"</span>");
        }
        %>
        </form>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js" integrity="sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK" crossorigin="anonymous"></script>
    </body>
    
</html>
