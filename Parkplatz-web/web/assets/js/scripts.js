
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }
        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});

          function ValidTodas(e){
            key = e.keyCode || e.which;

             if(key >=65 && key <=90 || key >= 97 && key <=122){
                 return true; 
             }
             else{
                 if(key === 8 || key === 32 || key === 96){
                     return true;
                 }
                 else{
                     return false;
                 }
             }
            }
            function Correo(e){
               key = e.keyCode || e.which;
               if(key >=48 && key <=57 || key >=65 && key <=90 || key >=97 && key <=122){
                   return true;
               }
               else{
                   if(key === 64 || key === 46 || key === 95 || key === 8){
                   return true;
                   }
                   else{
                       return false;
                   }
               }
    }
    
    function contra(e){
        key = e.keyCode || e.which;
        if(key >=46 && key <=57 || key >=65 && key <=96 || key >=97 && key <=125){
            return true;
        }
        else{
            if(key === 8 ){
                return true;
            }
            else{
                return false;
            }
        }
    }
    function ano(e){
        key = e.keyCode || e.which;
        if(key >=48 && key <=57){
            return true;
        }
        else{
            if(key === 8){
                return true;
            }
            else{
               return false; 
            }
        }
    }
    function comprobar(cam1,cam2){
        c1 = cam1.value;
        c2 = cam2.value;
        if(c1===c2){
            return true;
        }
        else{
            alert("Contrasenas no compatibles");
            return false;
        }
    }
