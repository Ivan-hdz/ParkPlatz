
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
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

                <script>
                    $(document).ready(function(){
                            $.get('../ServletConsultaUsuarios', function(data, status){
                                $('#usuariosRegistrados').html(data);
                            });
                    });
                </script>
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
                                            <h2>Usuarios de ParkPlatz</h2><br>
                                            <p id="usuariosRegistrados"></p>
                                        </header>
                                    <section>
                                        
                                    </section>
                                </section>
                        </div>
                
                </div>
               
	</body>
</html>
