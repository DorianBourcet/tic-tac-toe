<%-- 
    Document   : Login
    Created on : 2016-11-14, 10:34:51
    Author     : jycy
--%>

<div class="modal fade" id="Login-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
        <h4>
            Log in
            <%--LOGIN TO FINISH--%>
        </h4>
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

