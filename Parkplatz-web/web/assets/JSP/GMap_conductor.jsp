<%-- 
    Document   : GMapRegistroEsta
    Created on : 16/11/2015, 04:24:20 PM
    Author     : honte_000
--%>
<%@page import="java.io.File"%>
<%@page import="java.net.URI"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Modelo.Sql"%>
<%
    Connection con = Sql.conectar();
    PreparedStatement ps = con.prepareStatement("call verTodoEsta()");
    ResultSet rs = ps.executeQuery();
    
%>
<%@page contentType="text/html" pageEncoding="UTF8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Mi mapa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <script src = "http://maps.googleapis.com/maps/api/js"></script>
         <script>
    var geocoder;
    var map;
    var marker;
    var x;
    var y;
function initialize() {
    
    geocoder = new google.maps.Geocoder();
   if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position){
            x = position.coords.latitude;
            y = position.coords.longitude;
            var mapOptions = 
            {
                center:new google.maps.LatLng(x, y),
                zoom: 14,
                streetViewControl: false
            };
            map = new google.maps.Map(document.getElementById("map"),mapOptions);
            //Marcador de posicion del usuario
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(x,y),
                map: map,
                draggable:false
             });
             marker.setMap(map);
             
             //Marcador de estacionamiento
             var arrayEst = new Array();
             var arrayInfo = new Array();
             <% 
                
                 while(rs.next()){ 
                     //
                     String tabla =  "";
                     PreparedStatement ps1 = con.prepareStatement("call parkplatz.getStatusLugares(1);");
                  //   ps1.setInt(1, rs.getInt("idEst"));
                     ResultSet rs1 = ps1.executeQuery();
                     while(rs1.next()){
                          tabla+="<---------><br>";
                        tabla += "<label style=\'font-family: Arial\'>En piso <small>"+rs1.getString(2)+"</small></label><br>";
                        tabla += "<label style=\'font-family: Arial\'><small>"+rs1.getString(3)+" lugares "+rs1.getString(1)+"</small></label><br>";
                         tabla+="<---------><br>";  
                          System.out.println("Pasa por status");
                     }
                    
                     //
                     String context = this.getServletContext().getContextPath();
                     
                    String urlLimpia[] = new String[2] ;
                     if(rs.getString(7).equals("default")){
                        urlLimpia[1] = "assets/img/home.jpg";
                       
                     }else{
                        File f = new File(rs.getString(7));
                         URI u = f.toURI();
                        String url = u.toURL().toExternalForm();
                        urlLimpia = url.split(context+"/build/web/");
                        
                     }
             %>
                     var contenidoHTML = "<%="<section style=\'background-color: #141414; color: white; width: auto; height: auto\'>"+
                "<section style=\'display: table;width: 300px; height: 150px; background-color: transparent\'>"
                             + "<img src='"+context+"/"+urlLimpia[1]+"' width='100%' height='100%' />"
                +"</section>"+tabla+
                "<section style=\'height: 40%; width: 100%; padding: 5px\'>"
                +"<label style=\'font-family: Arial\'>Nombre: <small>"+ rs.getString(2) +"</small></label><br>"
                  +  "<label style=\'font-family: Arial\'>Calle y numero: <small>"+ rs.getString(3) +"</small></label><br>"
                    +"<label style=\'font-family: Arial\'>Colonia: <small>"+ rs.getString(4) +"</small></label><br>"
                    +"<label style=\'font-family: Arial\'>Delegación/Municipio: <small>"+ rs.getString(5) +"</small></label><br>"
                    +"<label style=\'font-family: Arial\'>Estado: <small>"+ rs.getString(6) +"</small><br>"
                +"</section>"
            +"</section>" %>";
                var est = new google.maps.Marker({
                   position: new google.maps.LatLng(<%= rs.getDouble("x") %>, <%= rs.getDouble("y") %>),
                   map: map,
                   draggable:false,
                   icon: '/Parkplatz-web/assets/img/parkLogo.png'
                });
                var info = new google.maps.InfoWindow({
                    content: contenidoHTML
                });
                Object.defineProperty(info, 'id',{
                    enumerable: true,
                    configurable: true,
                    writable: true,
                    value: '<%=rs.getString(2) %>'
                });
                Object.defineProperty(est, 'id',{
                    enumerable: true,
                    configurable: true,
                    writable: true,
                    value: '<%=rs.getString(2) %>'
                });
                arrayEst.push(est);
                arrayInfo.push(info);
                
               
                google.maps.event.addListener(est,'click',function(){
                    for(var i =0;i<arrayEst.length; i++){
                        var est = arrayEst[i];
                        if(est.id === this.id && arrayInfo[i].id === this.id){
                            arrayInfo[i].open(map,this);
                            console.log(est.id);
                        }
                    }
                    
                    
                });
                google.maps.event.addListener(map,'click', function(){
                    for(var i = 0; i<arrayInfo.length; i++){
                        arrayInfo[i].close();
                    }
                });
                est.setMap(map);
             <%} %>
        });
    } else {
        alert("Rastreo de ubicación no soportada por su navegador");
    }
    
    
}
        
 
</script>
<body onload="initialize()">
    <h2 style=" position: relative; text-align: right; padding-right: .5em ">Estacionamientos cercanos a mi.</h2>
                
    <div id="map"  style="width: 100%; height: 25em; display: block;position: relative; top: -4em; "></div>
    <span id="coors" name="coors" ></span>
</body> 
