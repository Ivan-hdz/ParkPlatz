<%-- 
    Document   : favoritos
    Created on : 4/12/2015, 11:30:44 PM
    Author     : UCER
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ParkPlatzWSC.Favoritos"%>
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
                                        <h2>Favoritos</h2>
                <%
                if(session.getAttribute("conductor") == null){         
                    response.sendRedirect("../index.jsp");
                }
                else{    
                    try {
                        Conductor conduc = (Conductor) session.getAttribute("conductor");
                        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
                        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
                        conduc = port.getMisFavs(conduc);
                    if(conduc.isMisFavsLLeno()){
                        Favoritos fav = new Favoritos();
                        out.println("<table>"
                                + "<tr>"
                                + "<td><h2>Nombre</h2></td>"
                                + "<td><h2>Direccion</h2></td>"
                                + "</tr>");
                        for(int i = 0; i <= (conduc.getMisFavs().size()); i++){
                            fav = conduc.getMisFavs().get(i);
                            out.println("<tr>"
                                    + "<td><h1>" + fav.getNombreEsta() + "</h1><br></td>"
                                    + "<td><span class='byline'>" + fav.getCalle() +", " + fav.getColonia() + ", " + fav.getDelegacion()
                                    + ", " + fav.getEstado()+"</span><br></td>"
                                    + "</tr>");
                        }
                        out.println("</table>");
                    }
                    else{
                    out.println("<span class='byline'>No hay Favoritos</span>");
                    }                            
                            
                        } catch (Exception ex) {

                        }

                }
                
                %>
                
                                </header>
                        </section>
                </div>
        </div>
	</body>
</html>
