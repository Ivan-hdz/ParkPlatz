<%-- 
    Document   : suppAdmin
    Created on : 4/04/2016, 06:37:25 PM
    Author     : Ivan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if(session.getAttribute("conductor") == null){         
            response.sendRedirect("../index.jsp");
        }
        else{
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
            <style>
                .pane30{
                    height: 10em;
                    width: 30%;
                    border-right: 1px solid #141414;
                    float: left;
                    display: table;
                }
                .pane70{
                    height: 10em;
                    width: 70%;
                    float: left;
                    display: table;
                }
                section{
                    background-color: white; color: #141414; width: 100%; height: 100%;
                    border-top: 1px solid #141414;
                    border-left: 1px solid #141414;
                    border-right: 1px solid #141414;
                }
                body{
                    background-color: white;
                    
                }
                li{
                    cursor: pointer;
                    border-bottom:  1px #141414 dotted;
                }
            </style>
            <script>
                  var ID = 0;
                var intervalo;
                var admin = 'si';
                $(document).ready(function(){
                //Trae todo de una conversacion en especifico
                function traeTodo(){
               ID = $('#key').val();
              
                $.post('../SupportServlet', {
                    'ID': ID,
                    'admin': admin,
                    'operacion': 'getMensajes'
                }, function(data, status){
                    var conver = $.parseJSON(data);
                    $('#emisor').html('');
                    $('#emisor').html('<b>De: </b> '+ conver.emisor);
                    $('#mensajes').html('');
                    for(var j = 0; j<conver.mensajes.length; j++){
                        $('#mensajes').append('<p>'+conver.mensajes[j] + '</p>');
                     }
                     
                });
            }
            //Trae todas las conversaciones
                    $.get('../SupportServlet?admin=si', function(data, status){
                        var conversaciones = $.parseJSON(data);
                     
                        for(var i = 0; i<conversaciones.length; i++){
                                $('#listaChats').append('<li class=\'listaItem\' id=\''+conversaciones[i].id+'\'>'+'De: '+conversaciones[i].emisor+'<br>'+'Asunto: '+conversaciones[i].asunto+'<br></li>');
                            }
                            $('#mensajes').html('De click en algun chat mostrado');
                            $('.listaItem').on('click', function(){
                                ID = this.id;
                                console.log(this.id+ this.ID+' ss');
                                $.post('../SupportServlet', {
                                    'ID': ID,
                                    'admin': admin,
                                    'operacion': 'getMensajes'
                                }, function(data, status){
                                    var conver = $.parseJSON(data);
                                    $('#emisor').html('');
                                    $('#emisor').html('<b>De: </b> '+ conver.emisor);
                                    $('#mensajes').html('');
                                    for(var j = 0; j<conver.mensajes.length; j++){
                                        $('#mensajes').append('<p>'+conver.mensajes[j] + '</p>');
                                     }
                                      $('#formu').html('<input type=\'hidden\' id=\'key\' value='+ID+'><input type=\'text\' id=\'texto\'/><input type=\'submit\' id=\'enviarMensaje\' class=\'enviarMensaje\' value=\'Enviar\'>');
                                        //Enviar mensaje
                                    $('#enviarMensaje').on('click', function(){
                                        var msg = $('#texto').val();
                                        console.log($('#texto').val());
                                        if($('#texto').val() !== '' ||  $('#texto').val() !== ' '){
                                            ID = $('#key').val();
                                            console.log(ID);
                                            $.post('../SupportServlet',{
                                                'operacion': 'sendMensaje',
                                                'admin': admin,
                                                'ID': ID,
                                                'mensaje': msg
                                            }, function(data, status){
                                                $('#texto').val('');
                                                return true;
                                            });
                                        }else{
                                            return false;
                                        }
                                    });
                                     intervalo = setInterval(traeTodo,500);
                                });
                            });
                            });
                                //Envia un mensaje
                
            });
                
                        
    
            </script>
    </head>
    <body class="homepage">
        <div id="header">
                <div class="container">
                    <h1><a href="#" id="logo">ParkPlatz</a></h1>

                    <nav id="nav">
                            <ul>
                                    <li><a href="inicio.jsp">Inicio</a></li>
                                    <li><a href="miMapa.jsp">Mi mapa</a></li>
                                    <li><a href="feedbackA.jsp">FeedBack</a></li>
                                    <li>Acciones
                                            <ul>
                                                <li><a href="accionBaja.jsp">Dar de baja</a></li>
                                                <li><a href="accionAviso.jsp">Dar aviso</a></li>
                                            </ul>
                                    </li>
                                    <li><a href="usuarios.jsp">Usuarios</a></li>
                                    <li><a href="suppAdmin.jsp">Soporte</a></li>
                            </ul>
                    </nav>
                    <section class="container wrapper style1" style="background-color: white; color: #141414; width: 100%; height: 100%">
                        <h1>Soporte</h1>
                        <section class="pane30">
                                <ul id="listaChats">
                                        
                                </ul>
                            </section>
                        <section class="pane70">
                               <h3 id="emisor"></h3>
                    <p id="mensajes"></p>
                    <p id="formu"></p>
                     </section>
                    </section>
    </body>
    <%}%>
</html>
