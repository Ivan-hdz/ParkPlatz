<%-- 
    Document   : inicio
    Created on : 1/12/2015, 08:39:28 PM
    Author     : UCER
--%>

<%@page import="ParkPlatzWSC.Conductor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%         
        if(session.getAttribute("conductor") == null){         
            response.sendRedirect("../index.jsp");
        }
    %>
<!DOCTYPE html>
<html>
    <head>
        <title>ParkPlatz</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
        <script src="../JS/jquery.min.js"></script>
        <script src="../JS/jquery.dropotron.min.js"></script>
        <script src="../JS/skel.min.js"></script>
        <script src="../JS/skel-layers.min.js"></script>
        <script src="../JS/init.js"></script>
        <script src="../assets/js/refresh.js"></script>
        <noscript>
            <link rel="stylesheet" href="css/skel.css" />
            <link rel="stylesheet" href="css/style.css" />
        </noscript>
    <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
    </head>
    <body class="homepage">
        
        <div id="header">
            <div class="container">
                <h1><a href="#" id="logo">ParkPlatz</a></h1>
                <nav id="nav">
                    <ul>
                        <li><a href="inicio.jsp">Inicio</a></li>
                        <li><a href="miMapa.jsp">Mi mapa</a></li>
                        <li><a href="favoritos.jsp">Favoritos</a></li>
                        <li><a href="recientes.jsp">Recientes</a></li>
                        <li><a href="feedback.jsp">FeedBack</a></li>
                        <li><a href="suppConductor.jsp">Dame Soporte</a></li>
                    </ul>
                </nav>
                <div id="banner">
                    <div class="container">
                        <section>
                                <header class="major">
                                        <h2>Bienvenido a ParkPlatz</h2>
                                        <span class="byline"></span>
                                </header>
                                <a href="#" class="button alt" onclick="window.location.href = 'configCuenta.jsp'">Configurar cuenta</a>
                                <br><br>
                                <form action="../LogOut" method="post">
                                    <button type="submit" class="button alt" >Salir</button>
                                </form>
                        </section>			
                    </div>
                </div>
            </div>
        </div>
        <div class="wrapper style2">
            <section class="container">
                    <header class="major">
                            <h2>La nueva forma de buscar estacionamiento</h2>
                            <span class="byline">Encontrar estacionamiento nunca fue tan facil</span>
                    </header>
                    <div class="row no-collapse-1">
                        <section class="4u">
                                <a href="#" class="image feature"><img src="../assets/img/pic02.jpg" alt=""></a>
                                <p>Busca los estacionamientos cercanos a tu ubicacion.</p>
                        </section>
                        <section class="4u">
                                <a href="#" class="image feature"><img src="../assets/img/pic03.jpg" alt=""></a>
                                <p>Agrega a tus favoritos los estacionamientos que mas te gusten.</p>
                        </section>
                        <section class="4u">
                                <a href="#" class="image feature"><img src="../assets/img/pic04.jpg" alt=""></a>
                                <p>Escribe tus comentarios al estacionamiento que desees.</p>
                        </section>

                    </div>
            </section>
        </div>
    </body>
</html>


        