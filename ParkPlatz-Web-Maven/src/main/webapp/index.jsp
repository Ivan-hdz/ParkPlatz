<%-- 
    Document   : index
    Created on : 8/12/2015, 02:54:17 PM
    Author     : UCER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("conductor") != null){
        if(session.getAttribute("tipo").equals("1")){
            out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Conductor/inicio.jsp'/>");
        }
        else{
            out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/Administrador/inicio.jsp'/>");
        }
        
    }
%>
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
        <link href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400italic&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="assets/css/style.css">
        <script src="JS/jquery.min.js" type="text/javascript"></script>
        <style>
            body{
                background-color: #3498db;
                height: 100%;
                width: 100%;
                font-family: 'PT Sans', Helvetica, Arial, sans-serif;
                font-weight: bold;
                text-shadow: 2px 0 0 #2C3E50, -2px 0 0 #2C3E50, 0 2px 0 #2C3E50, 0 -2px 0 #2C3E50, 1px 1px #2C3E50, -1px -1px 0 #2C3E50, 1px -1px 0 #2C3E50, -1px 1px 0 #2C3E50;
                text-align: center;
                color: #fff;
                background-image: url('assets/img/home.jpg');
                background-repeat: round;
            }
            h1{
                font-family: 'PT Sans', Helvetica, Arial, sans-serif;
                font-weight: bold;
                text-shadow: 2px 0 0 #2C3E50, -2px 0 0 #2C3E50, 0 2px 0 #2C3E50, 0 -2px 0 #2C3E50, 1px 1px #2C3E50, -1px -1px 0 #2C3E50, 1px -1px 0 #2C3E50, -1px 1px 0 #2C3E50;
                text-align: center;
            }
            .preview{
                width: 33%;
                height: 20em;
                display: table;
                float: left;
                opacity: 1;
               background-color: transparent; 
               text-align: center;
            }
            .previewText{
                width: 33%;
                height: 3em;
                display: table;
                float: left;
                opacity: 1;
               outline:  #52B3D9 1px solid;
               background-color:  transparent; 
               text-align: center;
            }
            img{
                width: 100%;
                height: 100%;
                opacity: 1;
            }
            .contenedor{
                
                text-align: center;
                width: 100%;
                height: 33%;
                background-color: transparent; 
                opacity:  1;
            }
            .contenedorRojo{
                top: -2em;
                background-color: transparent; 
                height: 33%;
                padding: 2em;
                margin-top: -1.5em;
                opacity: 1;
            }
            .contenedor:hover{
                opacity: 1;
            }
            .contenedorRojo:hover{
                opacity: 1;
            }
            
            td{
                margin-top:  2em;
                margin-bottom: 2em;
            }
            a{
                color: #fff;
            }
            label{
                font-family: 'Roboto Condensed', sans-serif;
                font-style: italic;
                font-size: 24px;
                cursor:pointer;
            }
            .contenedorVerde{
                margin-top: 1em;
                margin-bottom: 1em;
                height: 33%;
                background-color: transparent;
                opacity:  0.8;
                padding: 2em;
            }
            .contenedorVerde:hover{
                opacity: 1;
            }
            .javaDownload{
                width: 120px;
                height: 60px;
                border: 1px solid #2C3E50;
                border-radius: 10px;
                background-color: transparent;
                transition:  all .8s;
                cursor:  pointer;
            }
            .javaDownload:hover{
                background-color: #ECF0F1;
            }
            #javaD{
                
            }
        </style>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="page-container" >
            <font size='18'>ParkPlatz</font>
            
            <br><br>
            <table class="contenedor">
                <tr>
                    <td class="preview">
                        
                                <img src="assets/img/pic02.jpg"   alt="">
                        
                    </td>
                    <td class="preview">
                        
                                <img src="assets/img/pic03.jpg" alt="">
                                
                    </td>
                    <td class="preview">
                        
                                <img src="assets/img/pic04.jpg" alt="">
                                
                    </td>
            </tr>
            <tr>
                <td class="previewText cuadro"><center>Busca los estacionamientos cercanos a tu ubicacion.</center></td>
                <td class="previewText cuadro"><center> Agrega a tus favoritos los estacionamientos que mas te gusten.</center></td>
                <td class="previewText cuadro"><center>Escribe tus comentarios al estacionamiento que desees.</center></td>
            </tr>
            </table>
            <br><br>
            <section class="contenedorRojo">
            <form action="Login" method="post" id="formulario">
                <h1>Ingresar</h1>
                <input type="email" required name="username" class="username" placeholder="Correo electronico">
                <input type="password" required name="password" class="password" placeholder="Contraseña">
                <button type="submit" >Iniciar Sesion</button>
                <button type="submit" onclick="window.location.href = 'Conductor/registro.html'">Registrarse</button>
                <div class="error"><span>+</span></div>
                <br><br>
                <a href="recuperarContra.jsp">¿Olvidaste tu Contraseña?</a>
            </form>
            </section>
    
        <section class="contenedorVerde">
        <h2>Disponible en:</h2>
        <a href="builds/ParkPlatz-Desktop/ParkPlatz-Desktop SuperAlpha.jar">
            <img id="javaD" class="javaDownload" src="assets/img/java-logo.png" alt="Descargar ParkPlatz"/><br>
            <label for="javaD">Descargar: Aplicación de Estacionamiento (Escritorio)</label>
        </a>
        </section>
            
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        
        <script src="assets/js/scripts.js"></script>
        <script src="assets/js/refresh.js"></script>
    </body>

</html>