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
               $('#listejoueur').append('<a value="'+joueur+'" class="inv" ><div class="col-xs-12"><i class="fa fa-user" aria-hidden="true"></i><span >     '+joueur+'</span></div></a>');
           });
       });
       $.getJSON('./obtenir.do?element=listeInvitations',function(data,status){
           $('#listeinvitations').empty();
           $.each(data,function(i,invitation){
               //$('#stars').append('<input type="text" id="star" value="'+star+'" />');
               if($.trim($('.p-name').html()) === invitation.invite)
               $('#listeinvitations').append('<a value="'+invitation.hote+'" class="inv" ><div class="col-xs-12"><i class="fa fa-user" aria-hidden="true"></i><span >     '+invitation.hote+'</span></div></a>');
           });
       });   
   }, 5000);
   
$("#listejoueur" ).on( "click", "a", function() {
    //alert($(this).attr("value"));
    var joueur = $(this).attr("value");
    $.get("./inviter.do", {joueur: joueur}).done(function(data) {
        //alert( "Data Loaded: " + data );
    });
});

$("#listeinvitations" ).on( "click", "a", function() {
    //alert($(this).attr("value"));
    var joueur = $(this).attr("value");
    $.get("./start.do", {joueur: joueur}).done(function(data) {
        if (data === "1")
            if ($("#Game").css("display") === "none") $("#Game").fadeToggle();
            iniPartie();
    });
});
   
   setInterval(function(){
               var url = './obtenir.do?element=etatPartie';
               $.get(url,function(data,status){
                   if( $.trim(data) !== "true") iniPartie();
               });
           },1000);
   
   
   var init;
   
   function iniPartie(){
       init = setInterval(function(){
           var url = './obtenir.do?element=grille';
               $.getJSON(url,function(data){
                   $.each(data,function(l,v){
                       $.each(this,function(c,val){
                           if(val !==" "){
                               $("#"+l+c+"").empty();
                               $("#"+l+c+"").append(val);
                           }
                       });   
                   });
               });
               var url = './obtenir.do?element=etatPartie';
               $.get(url,function(data,status){
                   if( $.trim(data) !== "true"){
                       var url = './obtenir.do?element=tour';
                       $.get(url,function(data,status){
                           if(data === $.trim($(".p-name").html())){
                               var url = './obtenir.do?element=symbole';
                                   $.get(url,function(data,status){
                                       $(".case").hover(function(){
                                           if($(this).html() === "")
                                                $(this).css("opacity","0.5").append(data);
                                            },function(){
                                            if ($(this).css("opacity")=== "0.5"){
                                                $(this).css("opacity","1");
                                                $(this).empty();
                                            }
                                            });
                                    });
                                    $(".case").click(function(){
                                        if ($(this).css("opacity")=== "0.5"){
                                        //Add method AJAX to send response to server
                                        var url = './turn.do?l='+$.trim($(this).attr('value')[0])+'&c='+$.trim($(this).attr('value')[1]);
                                        $.get(url,function(data,status){
                                        });
                                        $(this).css("opacity","1");
                                        $(".case").off();
                                    }    
                                });
                            }
                            else $(".case").off();
                        });
                    }
                    else {
                        $(".case").off();
                        var url = './obtenir.do?element=vainqueur';
                        $.get(url,function(data,status){
                            //if (data ===)
                            //alert("Vainqueur: "+data);
                        });
                        $("#Game").fadeOut();
                        var url = './start.do?supprimer';
                        $.get(url,function(data,status){});
                        clearInterval(init);
                    }//PARTIE TERMINER;
                });
           },1000);
   }
});


$("#btn-inv").click(function(){
    $('#user-inv').css("border-color","");
    $('#WrgInvUser').fadeOut();
    $('#RgtInvUser').fadeOut();
    var url = './inviter.do?joueur='+$("#user-inv").val();
    $.get(url,function(data,status){
        switch(data){
            case "0":
                $('#user-inv').css("border-color","red");
                $('#WrgInvUser').fadeToggle();
                break;
            case "1":
                $('#user-inv').css("border-color","lime");
                $('#RgtInvUser').fadeToggle();
                break;
        };
    });
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


$("#user-inv").keyup(function(event){
    if(event.keyCode === 13){
        $("#btn-inv").click();
    }
});

$("#pass").keyup(function(event){
    if(event.keyCode === 13){
        $("#login").click();
    }
});