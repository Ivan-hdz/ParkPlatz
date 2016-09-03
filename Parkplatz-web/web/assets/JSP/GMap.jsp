<%-- 
    Document   : GMapRegistroEsta
    Created on : 16/11/2015, 04:24:20 PM
    Author     : honte_000
--%>

<%@page contentType="text/html" pageEncoding="UTF8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <script src = "http://maps.googleapis.com/maps/api/js"></script>
         <script>
    var geocoder;
    var map;
    var marker;
function initialize() {
    geocoder = new google.maps.Geocoder();
   
    var address = "<%=request.getParameter("direccion") %>";
    if(address == "1"){
        document.getElementById('map').innerHTML = "<link href='https://fonts.googleapis.com/css?family=Bangers' rel='stylesheet' type='text/css'><h2 style=\"color: white; font-family: 'Bangers', cursive; padding: 5px\">Favor de llenar el formulario para, finalmente buscar su ubicación presionando el botón correspondiente.</h2>"
    }else{
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
          var mapOptions = {
            zoom: 17,
            streetViewControl: false,
            center: results[0].geometry.location
          };
          
          map = new google.maps.Map(document.getElementById("map"), mapOptions);  
        marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location,
    <% if (!request.getParameter("editable").equals("no")){ %>
            draggable: true,
    <%}%>
            icon: '../img/parkLogo.png'
        });
        document.title = marker.getPosition();
        marker.addListener('dragend', function(){
            document.title = marker.getPosition();
            
        });
      } else {
        alert("Ha ocurrido un error favor de intentarlo mas tarde " + status);
      }
    });
    }
  }
 
</script>
<body onload="initialize()" style="background-color: #263238; position: fixed; top: -7px;left: -7px ;width: 100%; height: 100%">
    <div id="map"  style="width: 100%; height: 100%;"></div>
    <span id="coors" name="coors" ></span>
</body> 
