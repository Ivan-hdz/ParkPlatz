<%-- 
    Document   : accionBaja
    Created on : 7/12/2015, 05:40:27 PM
    Author     : UCER
--%>

<%@page import="ParkPlatzWSC.Administrador"%>
<%@page import="ParkPlatzWSC.Estacionamiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String opc = request.getParameter("opc");
if(opc== null)
{
    opc ="-1";
}
%>
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
                <script>
                   
                        function dame(){
                            var val = $('#busca').val();
                            $.post("../ServletConsultaUsuarios",
                            {
                                'opc' :'1',
                                'nombre' : val
                            },function(data, status){
                                $('#todos').html(data);
                                
                            });
                        }
                        
                   
                </script>
	</head>
	<body class="no-sidebar">

                <div id="header">
                        <div class="container">

                            <h1><a href="#" id="logo">ParkPlatz</a></h1>

                            <nav id="nav">
                                <ul>
                                        <li><a href="inicio.jsp">Inicio</a></li>
                                        <li><a href="miMapa.jsp">Mi mapa</a></li>
                                        <li><a href="feedbackA.jsp">FeedBack</a></li>
                                        <li><a href="">Acciones</a>  
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
                                                <h2>Dar Baja</h2>
                                                <form action="" method="post">
                                                    <input onkeypress="dame()" type="text" id="busca" name="nombre" required placeholder="Ingresa nombre de estacionamineto"><br>
                                                   
                                                </form>
                                                <section>
                                                    <p id="todos"></p>
                                                </section>
                    

                                        </header>
                                </section>
                        </div>
                </div>
	</body>
</html>
