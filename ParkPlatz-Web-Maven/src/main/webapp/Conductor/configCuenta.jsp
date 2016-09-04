<%-- 
    Document   : configCuenta
    Created on : 2/12/2015, 12:06:30 AM
    Author     : UCER
--%>

<%@page import="ParkPlatzWSC.Conductor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>ParkPlatz</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="../assets/css/reset.css">
        <link rel="stylesheet" href="../assets/css/supersized.css">
        <link rel="stylesheet" href="../assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<script>
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
        </script>
    </head>

    <body>

        <div class="page-container">
            <h1>Configurar cuenta</h1>
            <form action="../Modificar" method="post">
                
                <%
        if(session.getAttribute("conductor") == null){         
            response.sendRedirect("../index.jsp");
        }
        else{
        Conductor conduc = (Conductor) session.getAttribute("conductor");
                out.println("<input type='text' name='name' class='username' required placeholder='Nombre' value="+ conduc.getNombre()+" onkeypress='return ValidTodas(event)'>");
                out.println("<input type='text' name='aPaterno' class='username' required placeholder='Apellido Paterno' value=" + conduc.getAPaterno()+ " onkeypress='return ValidTodas(event)'>");
                out.println("<input type='text' name='aMaterno' class='username' required placeholder='Apellido Materno' value=" + conduc.getAMaterno()+ " onkeypress='return ValidTodas(event)'>");
                out.println("<input type='text' name='email' class='username' required placeholder='Correo electronico' value=" + conduc.getCorreo()+ ">");
                out.println("<input type='password' name='password' class='password' required placeholder='Contraseña'>");
                out.println("<input type='password' name='password2' class='password' required placeholder='Confirmar contraseña'>");
                out.println("<input type='hidden' name='isAd' value='2'>");
                }
                %>
                <button type="submit" onclick="return comprobar(password,password2)">Guardar cambios</button>
                <button onclick="window.location.href = 'inicio.jsp'">Cancelar</button>
                <div class="error"><span>+</span></div>
            </form>
        </div>

        <!-- Javascript -->
        <script src="../assets/js/jquery-1.8.2.min.js"></script>
        <script src="../assets/js/supersized.3.2.7.min.js"></script>
        <script src="../assets/js/supersized-init.js"></script>
        <script src="../assets/js/scripts.js"></script>
        <script src="../assets/js/refresh.js"></script>
    </body>

</html>
