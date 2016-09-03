<%-- 
    Document   : recuperame
    Created on : 6/12/2015, 11:39:46 PM
    Author     : honte_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar contraseña</title>
        <script>
            function checar(formu){
                var p = document.getElementById("pass");
                var cP = document.getElementById("Cpass");
                if(cP.value !== p.value){
                    alert("Contraseñas no coinciden.");
                    return false;
                }else{
                    formu.submit();
                    return true;
                }
            }
        </script>
        <%
            String id = request.getParameter("id");
            String correo = request.getParameter("email");
            
            
        %>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        
        <link rel="stylesheet" href="assets/css/style.css">
        <script src='https://www.google.com/recaptcha/api.js'></script>
         <script src="JS/jquery.min.js" type="text/javascript"></script>
         

    </head>
    <body style="background-image: url(assets/img/recovery.jpg)">
        <form action="RecuperaPass" method="POST" onsubmit="return checar(this)">
            <label>Nueva contraseña: <input type="password" id="pass" name="pass"></label> <br>
            <label>Repetir contraseña: <input type="password" id="Cpass" name="Cpass"></label> <br>
            <input type="hidden" value="<%= id %>" name="id">
            <input type="hidden" value="<%= correo %>" name="correo">
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>
