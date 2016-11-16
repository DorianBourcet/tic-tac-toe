<%-- 
    Document   : Grid
    Created on : 2016-11-14, 09:26:48
    Author     : jycy
--%>

<div id="Game" class="container col-xs-12 col-xl-8 offset-xl-2">
    <div id="Grid">
        <%--Grid here--%>
        <%
            for(int i= 0;i<3;i++){
                for(int j = 0;j<3;j++)
                {
                    out.print("<div class='col-xs-4 tile'> <p class = 'case' id= '"+i+j+"'></p> </div>");
                }
            }
        %>
    </div>
        <%--Player 1--%>
    <span class="col-xs-6">
        <span id="Player-1" class="img-thumbnail rounded-circle d-inline-block align-middle"></span>
        <span>{{player1}}</span>
    </span>
        <%--Player 2--%>
    <span class="col-xs-6 text-xs-right">
        <span>{{player2}}</span>
        <span id="Player-2" class="img-thumbnail rounded-circle d-inline-block align-middle"></span>
    </span>
</div>
