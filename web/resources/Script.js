$('#Login-modal').modal({
    backdrop: 'static',
    keyboard:  false
});
   
$('#Login-modal').modal('show');

$(function(){
   if ($(".p-name").html() !== "") $(".p-name").fadeIn(); 
   setInterval(function(){ 
       $.getJSON('./obtenir.do?element=listeJoueurs',function(data,status){
           $('#listejoueur').empty();
           $.each(data,function(i,joueur){
               //$('#stars').append('<input type="text" id="star" value="'+star+'" />');
               if($.trim($('.p-name').html()) !== joueur)
               $('#listejoueur').append('<div><span class="col-xs-1"><i class="fa fa-user" aria-hidden="true"></i></span><span class="col-xs-8">     '+joueur+'</span><span class="col-xs-1"><i class=" fa fa-plus " aria-hidden="true"></i></span></div>');
           });
       });
   }, 5000);
   
   
});

$("#login").click(function(){
    var url = './login.do?username='+$('#user').val()+'&password='+$('#pass').val();
    $('#user').css("border-color","");
    $('#pass').css("border-color","");
    $('#WrgUser').fadeOut();
    $('#WrgPass').fadeOut();
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
                $(".p-name").append($('#user').val());
                $(".p-name").fadeIn();
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

$(".case").hover(function(){
    if($(this).html() === "")
    $(this).css("opacity","0.5").append("X");
},function(){
    if ($(this).css("opacity")=== "0.5")
    $(this).empty();
    });
    
$(".case").click(function(){
    if ($(this).css("opacity")=== "0.5"){
        //Add method AJAX to send response to server
        $(this).css("opacity","1");
    }    
});



