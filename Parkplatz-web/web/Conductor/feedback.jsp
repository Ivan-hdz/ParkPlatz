<%-- 
    Document   : feedback
    Created on : 2/12/2015, 06:48:58 PM
    Author     : UCER
--%>

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
		<script src="../JS/jquery.min.js" type="text/javascript"></script>
                <script src="../JS/skel.min.js" type="text/javascript"></script> 
                <script src="../JS/skel-layers.min.js" type="text/javascript"></script>
                <script src="../JS/init.js" type="text/javascript"></script>
                <script src="../JS/jquery.dropotron.min.js" type="text/javascript"></script>
                <script src="../assets/js/refresh.js"></script>
		<noscript>
			<link rel="stylesheet" href="../css/skel.css" />
			<link rel="stylesheet" href="../css/style.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body class="no-sidebar">
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


                </div>
        </div>
        <div id="main" class="wrapper style1">
                <div class="container">
                        <section>
                                <header class="major">
                                        <h2>FeedBack</h2>
                                </header>
                        </section>
                </div>
            <div class="container">
                <form action="../newFeedBack" method="post" id="form">
                    <textarea name="feedback" form="form" required placeholder="Escribe tu comentario"></textarea><br>
                    <button type="submit" class="button alt">Enviar</button>              
                </form>
                
            </div>
        </div>
	</body>
</html>
