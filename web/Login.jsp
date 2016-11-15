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
            <div class="input-group padding-input-login">
                <span class="input-group-addon" id="Pass"><i class="fa fa-lock" aria-hidden="true"></i></span></span>
                <input id="pass" name="password" type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon2" data-toggle="tooltip" data-placement="top" title="Entrez votre password">
            </div>
            <input id="login" type="button" class="btn btn-lg btn-success btn-block" value="connecter">
        </form>
    </div>
  </div>
</div>

<script type="text/javascript">
    $('#Login-modal').modal({
        backdrop: 'static',
        keyboard:  false
    });
    
    $('#Login-modal').modal('show');
</script>

<script>
        $("#login").click(function(){
            $.get('/login.do?username='+$('#user').val()+'&password'+$('#pass').val(),function(data,status){
                switch(data){
                    case 0:
                        //code if no user
                        break;
                    case 1:
                        //code if wrong password
                        break;
                    case 2:
                        //code good authentification
                        $('#Login-modal').modal('hide');
                        break;
                    };
                });
            alert("after $.get");    
            });
</script>

