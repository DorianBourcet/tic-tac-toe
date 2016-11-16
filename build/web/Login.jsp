<%-- 
    Document   : Login
    Created on : 2016-11-14, 10:34:51
    Author     : jycy
--%>

<div class="modal fade" id="Login-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <h4 class="text-xs-center">
            Log in
            <%--LOGIN TO FINISH--%>
        </h4>
        <form> 
            <div class="input-group padding-input-login">
                <span class="input-group-addon" id="User"><i class="fa fa-user" aria-hidden="true"></i></span>
                <input id="user" name="username" type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" data-toggle="tooltip" data-placement="top" title="Entrez votre nom d'utilisateur">
            </div>
            <div id="WrgUser">Utilisateur inexistant</div>
            <div class="input-group padding-input-login">
                <span class="input-group-addon" id="Pass"><i class="fa fa-lock" aria-hidden="true"></i></span></span>
                <input id="pass" name="password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon2" data-toggle="tooltip" data-placement="top" title="Entrez votre password">
            </div>
            <div id="WrgPass">Mauvais mot de passe</div>
            <input id="login" type="button" class="btn btn-lg btn-success btn-block" value="connecter">
        </form>
    </div>
  </div>
</div>

