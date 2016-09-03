<%-- 
    Document   : recientes
    Created on : 2/12/2015, 11:16:35 PM
    Author     : UCER
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ParkPlatzWSC.Recientes"%>
<%@page import="ParkPlatzWSC.Conductor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                            <h2>Recientes</h2>
                <%
        if(session.getAttribute("conductor") == null){         
            response.sendRedirect("../index.jsp");
        }
                else{                
                    try {
                        Conductor conduc = (Conductor) session.getAttribute("conductor");
                        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
                        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
                        conduc = port.getMisRecientes(conduc);
                        
                        if(conduc.isMisRecientesLleno()){
                            Recientes recien = new Recientes();
                            out.println("<table>"
                                    + "<tr>"
                                    + "<td><h2>Fecha</h2></td>"                             
                                    + "<td><h2>Nombre</h2></td>"
                                    + "<td><h2>Direccion</h1></td>"
                                    + "</tr>");
                            for(int i = 0; i <= (conduc.getMisRecientes().size()); i++){
                                recien = conduc.getMisRecientes().get(i);
                                out.println("<tr>"
                                        + "<td><h1>" + recien.getFecha()+ "</h1><br></td>"
                                        + "<td><h1>" + recien.getNombreEsta() + "</h1><br></td>"
                                        + "<td><span class='byline'>" + recien.getCalle() +", " + recien.getColonia() + ", " + recien.getDelegacion()
                                        + ", " + recien.getEstado()+"</span><br></td>"
                                        + "</tr>");
                            }
                            out.println("</table>");
                        }
                        else{
                        out.println("<span class='byline'>No hay Recientes</span>");
                        }  
                    } catch (Exception ex) {
                        // TODO handle custom exceptions here
                    }
                    
          
                }
                %>     
                                    </header>
                            </section>
                    </div>
            </div>
	</body>
</html>
