<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- chiave mappa di ernesto -->
<script src="http://maps.google.com/maps?file=api&amp;v=3&amp;sensor=false&amp;key=ABQIAAAAlGa2VqYOASraNv9ZURE43xS-I694MGUuRqMBpCJkKeEjfTngCBSFzWz6dHQxzq17qUBwGg47JrzjMg" 
		type="text/javascript"></script>

<%String s=(String)request.getSession().getAttribute("imei"); %>

<script type="text/javascript">
google.load('maps', '3'); // Load version 3 of the Maps API
function load() {
	var val="<%=s %>";
	if (GBrowserIsCompatible()) {
		var map = new GMap2(document.getElementById("map"));
		map.addControl(new GLargeMapControl());
		map.addControl(new GScaleControl());
		var center = new GLatLng(41.028089,14.618969);
		map.setCenter(center, 15);
		var begin = function(){
			GDownloadUrl("provax.php?imei="+val, function(data, responseCode) {
			var xml = GXml.parse(data);
			var marker1;
			var punto;
			var markers = xml.documentElement.getElementsByTagName("marker");
			for (var i = 0; i < markers.length; i++) {
				var lat = parseFloat(markers[i].getAttribute("latitudine"));
				var lon = parseFloat(markers[i].getAttribute("longitudine"));
				//alert("Latitudine: "+lat + " Longitudine: "+ lon); 
				var punto = new GLatLng(lat,lon);
				marker1 = new GMarker(punto, {title: "Sono qui"});
				map.addOverlay(marker1);
				map.setCenter(punto,10);
				map.setZoom( 16 );
			}
		}).periodical(3000);
		};
		begin();
	}
}

</script>

</HEAD>
<BODY onload="load()">
	<div id="map" style="width: 1300px; height: 600px"></div>
	
</BODY>
</html>