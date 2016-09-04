<%-- 
    Document   : miMapa
    Created on : 6/12/2015, 07:13:52 PM
    Author     : UCER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%         
        if(session.getAttribute("conductor") == null){         
            response.sendRedirect("http://localhost/Parkplatz-web/index.jsp");
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
               
		<noscript>
			<link rel="stylesheet" href="../css/skel.css" />
			<link rel="stylesheet" href="../css/style.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
        <body >
            <section style='width: 100%; height: 100%'>
            <section style="width: 100%; height: 20%; float: left; display: table">
                <div id="header">
                        <div class="container">

                                        <h1><a href="#" id="logo">ParkPlatz</a></h1>

                                        <nav id="nav" >
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
            </section>
            <section style="width: 100%; height: 100%; ">
                <div id="main" class="wrapper style1">
                    
                <jsp:include flush="true"  page="../assets/JSP/GMap_conductor.jsp" ></jsp:include>
                                                    
            </section>
            </section>
	</body>
</html>
