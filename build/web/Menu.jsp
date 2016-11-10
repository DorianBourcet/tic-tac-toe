<%-- 
    Document   : Menu
    Created on : 2016-11-10, 08:24:03
    Author     : jycy
--%>

<div id="Menu">
    <div id="profile">
        <div id="avatar" class="img-thumbnail rounded-circle mx-auto d-block">
            
        </div>
        <p  class="p-name">{{PlayerName}} </p>
       <p id="btn4" >{{TEST ROTATE}}</p>  
    </div>
    <div id="accordion" role="tablist" aria-multiselectable="true">
        <div class="card no-marging">
            <div class="card-header" role="tab" id="headingOne">
                <h5 class="mb-0">
                    <a data-toggle="collapse"  data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Joueur en ligne
                        <i class="fa fa-arrow-right" aria-hidden="true"></i>
                    </a>
                </h5>
            </div>

            <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne">
                <div class="card-block">
                    Online poeple
                </div>
            </div>
        </div>
        <div class="card no-marging">
            <div class="card-header" role="tab" id="headingTwo">
                <h5 class="mb-0">
                    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Partie 
                        <i class="fa fa-arrow-right" aria-hidden="true"></i>
                    </a>
                </h5>
            </div>
            <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="card-block">
                    Recherche partie
                </div>
            </div>
        </div>
    </div>
</div>
<%/*
<script>
    $(function() {
        $('#btn4').click(function() {
            $(this).rotate({ endDeg:90,duration:.10, persist:true });        TEST ROTATE
        }, function() {
            $(this).rotate({ endDeg:0,duration:.10, persist:true});
            });
    });
    
</script> */
%>
