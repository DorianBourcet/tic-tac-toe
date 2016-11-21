<%-- 
    Document   : Menu
    Created on : 2016-11-10, 08:24:03
    Author     : jycy
--%>

<div id="Menu">
    <div id="profile">
        <div id="avatar" class="img-thumbnail rounded-circle mx-auto d-block">
            
        </div>
        <p  class="p-name">
            <% if (session.getAttribute("connecte")!=null){ 
                out.print(session.getAttribute("connecte"));
            }%>
        </p>
        <%--<p id="btn4" >{{TEST ROTATE}}</p>  --%>
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
                <div id="listejoueur" class="card-block">
                    <div>Online poeple</div>
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
    <input type="button"class="btn btn-block btn-danger" value="Invite" data-toggle="modal" data-target="#Invite-modal"/>
    <input type="button"id="start" class="btn btn-block btn-success" value="Start"/>
    
    <div class="modal fade" id="Invite-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <h1 class="text-xs-center">
                Invitation
            </h1>
            <form> 
                <div class="input-group padding-input-login">
                    <span class="input-group-addon" id="User"><i class="fa fa-user" aria-hidden="true"></i></span>
                    <input id="user-inv" name="username" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" data-toggle="tooltip" data-placement="top" title="Entrez un nom d'utilisateur">
                </div>
                <div id="WrgInvUser">Utilisateur inexistant ou non connecté</div>
                <div id="RgtInvUser">Invitation avec succes!</div>
                <input id="btn-inv" type="button" class="btn btn-lg btn-danger btn-block" value="Inviter">
            </form>
        </div>
      </div>
    </div>
            
</div>