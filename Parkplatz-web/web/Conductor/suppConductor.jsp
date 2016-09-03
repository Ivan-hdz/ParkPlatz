<%-- 
    Document   : suppConductor
    Created on : 4/04/2016, 06:47:58 PM
    Author     : Ivan
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
                var admin = 'no';
                $(document).ready(function(){
                    //carga la conversacion seleccionada
                function traeTodo(){
                    
                ID = $('#key').val();
              
                $.post('../SupportServlet', {
                    'ID': ID,
                    'admin': admin,
                    'operacion': 'getMensajes'
                }, function(data, status){
                    var conver = $.parseJSON(data);
                    $('#emisor').html('');
                    $('#emisor').html('<b>De: </b> ParkPlatz');
                    $('#mensajes').html('');
                    for(var j = 0; j<conver.mensajes.length; j++){
                        $('#mensajes').append('<p>'+conver.mensajes[j] + '</p>');
                     }
                                
                });
            }
            //Trae las coversaciones existentes
                $.get('../SupportServlet?admin=no', function(data, status){
                        var conversaciones = $.parseJSON(data);
                        /*conversaciones =   [
                                {
                                    'emisor': 'honter1997@gmail.com',
                                    'ID': '12345',
                                    'asunto': 'Prueba'
                                },
                                {
                                    'emisor': 'honter1997@gmail.com',
                                    'ID': 'sas',
                                    'asunto': 'Prueba'
                                },
                                {
                                    'emisor': 'honter1997@gmail.com',
                                    'ID': '542',
                                    'asunto': 'Prueba'
                                }
                            ];
                        */
                       
                        for(var i = 0; i<conversaciones.length; i++){
                                $('#listaChats').append('<li class=\'listaItem\' id=\''+conversaciones[i].id+'\'>'+'De: '+conversaciones[i].emisor+'<br>'+'Asunto: '+conversaciones[i].asunto+'<br></li>');
                                
                        }
                            $('#mensajes').html('De click en algun chat mostrado');
                            $('.listaItem').on('click', function(){
                                
                                ID = this.id;
                                
                                $.post('../SupportServlet', {
                                    'ID': ID,
                                    'admin': admin,
                                    'operacion': 'getMensajes'
                                }, function(data, status){
                                    var conver = $.parseJSON(data);
                                    $('#emisor').html('');
                                    $('#emisor').html('<b>De: </b> ParkPlatz');
                                    $('#mensajes').html('');
                                    for(var j = 0; j<conver.mensajes.length; j++){
                                        $('#mensajes').append('<p>'+conver.mensajes[j] + '</p>');
                                     }
                                     $('#formu').html('<input type=\'hidden\' id=\'key\' value='+ID+'><input type=\'text\' id=\'texto\'/><input type=\'submit\' id=\'enviarMensaje\' class=\'enviarMensaje\' value=\'Enviar\'>');
                                      
                                    //Envia un mensaje
                                    $('#enviarMensaje').on('click', function(){
                                        var msg = $('#texto').val();
                                        if($('#texto').val() !== '' ||  $('#texto').val() !== ' '){
                                            ID = $('#key').val();
                                            console.log(ID);
                                            $.post('../SupportServlet', {
                                            'ID': ID,
                                            'admin': admin,
                                            'operacion': 'sendMensaje',
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
                });
               //al crear una nueva conversacion
               function setup(){
                    $('#nuevo').on('click', function(){
                   
                    if($('#asunto').val() !== '' || $('#asunto').val() !== ' '){
                        var asunto = $('#asunto').val();
                        ID = Math.random()*Math.PI*10000;
                        $.post('../SupportServlet',
                        {
                            'admin': admin,
                            'asunto': asunto,
                            'ID': ID,
                            'operacion': 'newConver'
                        }, function(data, status){
                            console.log(ID);
                            $.post('../SupportServlet', {
                                'idConver': ID,
                                'admin': 'no',
                                'operacion': 'getMensajes'
                            }, function(data, status){
                                var conver = $.parseJSON(data);
                                $('#emisor').html('');
                                $('#emisor').html('<b>De: </b> ParkPlatz');
                                $('#mensajes').html('');
                                for(var j = 0; j<conver.mensajes.length; j++){
                                    $('#mensajes').append('<p>'+conver.mensajes[j] + '</p>');
                                 }
                                 intervalo = setInterval(traeTodo(),1000);
                                });
                    });
                    }else{
                        alert('Favor de llenar el recuadro');
                        return false;
                    }
                    window.document.location.reload();
                });
                }
            </script>
    </head>
    <body class="homepage" onload="setup()">
        
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
<section class="container wrapper style1" style="background-color: white; color: #141414; width: 100%; height: 100%">
                <h1>Soporte</h1>
                <section>
                    <h1>Inicie una nuevo conversación con nuestros administradores</h1>
                    <label>Asunto: </label>
                    <input type="text" class="input-lg" id="asunto" />
                    <br>
                    <button type="submit" class="nuevoChat"   id="nuevo" >Crear</button>
                    <br>
                </section>
                <section class="pane30">
                        <ul id="listaChats">

                        </ul>
                    </section>
                <section  class="pane70">
                    <h3 id="emisor"></h3>
                    <p id="mensajes"></p>
                    <p id="formu"></p>
              
                </section>
            </section>
    </body>
</html>
