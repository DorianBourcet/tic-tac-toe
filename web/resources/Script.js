$('#Login-modal').modal({
    backdrop: 'static',
    keyboard:  false
});
    
$('#Login-modal').modal('show');

$("#login").click(function(){
    var url = './login.do?username='+$('#user').val()+'&password='+$('#pass').val();
    $('#user').css("border-color","");
    $('#pass').css("border-color","");
    $('#WrgUser').fadeOut();
    $('#WrgPass').fadeOut();
    //$('#WrgUser').css("display","none");
    //$('#WrgPass').css("display","none");
    $.get(url,function(data,status){
        switch(data){
            case "0":
                //code if no user
                $('#user').css("border-color","red");
                $('#WrgUser').fadeToggle();
                break;
            case "1":
                //code good authentification
                $('#Login-modal').modal('hide');
                break;
            case "2":
                //code if wrong password
                $('#pass').css("border-color","red");
                $('#WrgPass').fadeToggle();
                break;
        };
    });
});

$("#pass").keyup(function(event){
    if(event.keyCode === 13){
        $("#login").click();
    }
});


