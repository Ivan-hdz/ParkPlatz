<%-- 
    Document   : feedbackA
    Created on : 6/12/2015, 07:18:07 PM
    Author     : UCER
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.net.Socket"%>
<%@page import="java.net.ServerSocket"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ParkPlatzWSC.Feedback"%>
<%@page import="ParkPlatzWSC.Administrador"%>
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
                <script src="../assets/js/refresh.js"></script>
                <script src="../JS/jquery.dropotron.min.js" type="text/javascript"></script>
 
		<noscript>
			<link rel="stylesheet" href="../css/skel.css" />
			<link rel="stylesheet" href="../css/style.css" />
		</noscript>
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
             
	</head>
        <body class="no-sidebar" >

                <div id="header">
                        <div class="container">

                            <h1><a href="#" id="logo">ParkPlatz</a></h1>

                            <nav id="nav">
                                <ul>
                                        <li><a href="inicio.jsp">Inicio</a></li>
                                        <li><a href="miMapa.jsp">Mi mapa</a></li>
                                        <li><a href="feedbackA.jsp">FeedBack</a></li>
                                        <li><a href="?opc=3">Acciones</a>  
                                                <ul>
                                                    <li><a href="accionBaja.jsp">Dar de baja</a></li>
                                                    <li><a href="accionAviso.jsp">Dar aviso</a></li>
                                                </ul>
                                        </li>
                                        <li><a href="usuarios.jsp">Usuarios</a></li>
                                        <li><a href="suppAdmin.jsp">Soporte</a></li>
                                </ul>
                            </nav>

                        </div>
                </div>

                <div id="main" class="wrapper style1">
                        <div class="container">
                                <section>
                                        <header class="major">
                                                <h1>FeedBack</h1>
                                                <%
                if(session.getAttribute("conductor") == null){         
                    out.println("<META HTTP-EQUIV='REFRESH' CONTENT='.0000001;URL=http://localhost/Parkplatz-web/index.html'/>");
                }
                else{    
                    try {
                        Administrador admin = (Administrador) session.getAttribute("conductor");
                        ParkPlatzWSC.ParkPlatzWS_Service service = new ParkPlatzWSC.ParkPlatzWS_Service();
                        ParkPlatzWSC.ParkPlatzWS port = service.getParkPlatzWSPort();
                        admin = port.getFeedback(admin);
                    if(admin.isFeedLleno()){
                        Feedback feed = new Feedback();
                        out.println("<table>"
                                + "<tr>"
                                + "<td><h4>ID</h4></td>"
                                + "<td><h4>Descripcion</h4></td>"
                                + "<td><h4>ID Usuario</h4></td>"
                                + "<td><h4>Correo del usuario</h4></td>"
                                + "<td><h4>Fecha</h4></td>"
                                + "</tr>");
                        for(int i = 0; i <= (admin.getCatFeedback().size()); i++){
                            feed = admin.getCatFeedback().get(i);
                            out.println("<tr>"
                                    + "<td><h1>" + feed.getIdFeedback() + "</h1><br></td>"
                                    + "<td><h1>"+feed.getDescripcion()+"</h1><br></td>"
                                    + "<td><h1>"+feed.getIdUsuarioRedactor()+"</h1><br></td>"
                                    + "<td><h1>"+feed.getCorreoUsuarioRedactor()+"</h1><br></td>"
                                    + "<td><h1>"+feed.getFecha()+"</h1><br></td>"
                                    + "</tr>");
                        }
                        out.println("</table>");
                    }
                    else{
                    out.println("<span class='byline'>Feedback Vacio</span>");
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
