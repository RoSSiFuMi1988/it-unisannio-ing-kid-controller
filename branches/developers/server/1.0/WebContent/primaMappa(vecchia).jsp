<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!--
    Created by Artisteer v3.0.0.41778
    Base template (without user's data) checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <meta name="description" content="Description">
    <meta name="keywords" content="Keywords">


    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="script.js"></script>

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

</head>
<BODY onload="load()">
	<div id="art-page-background-middle-texture">
<div id="art-main">
    <div class="cleared reset-box"></div>
<div class="art-nav">
	<div class="art-nav-l"></div>
	<div class="art-nav-r"></div>
<div class="art-nav-outer">
<div class="art-nav-wrapper">
<div class="art-nav-inner">
	<ul class="art-hmenu">
		<li>
			<a href="./index1.html"><span class="l"></span><span class="r"></span><span class="t">Login</span></a>
		</li>	
		<li>
			<a href="./registrazione1.html"><span class="l"></span><span class="r"></span><span class="t">Registrazione</span></a>
		</li>	
		<li>
			<a href="./gestore.html"><span class="l"></span><span class="r"></span><span class="t">Gestore</span></a>
		</li>	
		<li>
			<a href="./primaMappa.jsp" class="active"><span class="l"></span><span class="r"></span><span class="t">Localizzazione</span></a>
		</li>	
		<li>
			<a href="./Logout"><span class="l"></span><span class="r"></span><span class="t">Logout</span></a>
		</li>	
		<li>
			<a href="./contattaci1.html"><span class="l"></span><span class="r"></span><span class="t">Contattaci...</span></a>
		</li>	
	</ul>
</div>
</div>
</div>
</div>
<div class="cleared reset-box"></div>
<div class="art-header">
        <div class="art-header-clip">
            <div class="art-header-png"></div>
            <div class="art-header-jpeg"></div>
        </div>
    <div class="art-header-wrapper">
    <div class="art-header-inner">
        <div class="art-headerobject"></div>
        <script type="text/javascript" src="swfobject.js"></script>
        <div id="art-flash-area">
        <div id="art-flash-container">
        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="884" height="150" id="art-flash-object">
        	<param name="movie" value="container.swf">
        	<param name="quality" value="high">
        	<param name="scale" value="default">
        	<param name="wmode" value="transparent">
        	<param name="flashvars" value="color1=0xFFFFFF&amp;alpha1=.50&amp;framerate1=24&amp;loop=true&amp;wmode=transparent&amp;clip=images/flash.swf&amp;radius=5&amp;clipx=0&amp;clipy=-35&amp;initalclipw=900&amp;initalcliph=225&amp;clipw=884&amp;cliph=221&amp;width=884&amp;height=150&amp;textblock_width=0&amp;textblock_align=no&amp;hasTopCorners=true&amp;hasBottomCorners=true">
            <param name="swfliveconnect" value="true">
        	<!--[if !IE]>-->
        	<object type="application/x-shockwave-flash" data="container.swf" width="884" height="150">
        	    <param name="quality" value="high">
        	    <param name="scale" value="default">
        	    <param name="wmode" value="transparent">
            	<param name="flashvars" value="color1=0xFFFFFF&amp;alpha1=.50&amp;framerate1=24&amp;loop=true&amp;wmode=transparent&amp;clip=images/flash.swf&amp;radius=5&amp;clipx=0&amp;clipy=-35&amp;initalclipw=900&amp;initalcliph=225&amp;clipw=884&amp;cliph=221&amp;width=884&amp;height=150&amp;textblock_width=0&amp;textblock_align=no&amp;hasTopCorners=true&amp;hasBottomCorners=true">
                <param name="swfliveconnect" value="true">
        	<!--<![endif]-->
        		<div class="art-flash-alt"><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player"></a></div>
        	<!--[if !IE]>-->
        	</object>
        	<!--<![endif]-->
        </object>
        </div>
        </div>
        <script type="text/javascript">    swfobject.switchOffAutoHideShow(); swfobject.registerObject("art-flash-object", "9.0.0", "expressInstall.swf");</script>
        <div class="art-logo">
                 <h1 class="art-logo-name"><a href="./index.html">Kid Control</a></h1>
                         <h2 class="art-logo-text">Trovalo dovunque esso sia...</h2>
                </div>
    </div>
    </div>
    </div>
    <div class="cleared reset-box"></div>
    <div class="art-sheet">
        <div class="art-sheet-tl"></div>
        <div class="art-sheet-tr"></div>
        <div class="art-sheet-bl"></div>
        <div class="art-sheet-br"></div>
        <div class="art-sheet-tc"></div>
        <div class="art-sheet-bc"></div>
        <div class="art-sheet-cl"></div>
        <div class="art-sheet-cr"></div>
        <div class="art-sheet-cc"></div>
        <div class="art-sheet-body">
            <div class="art-content-layout">
                <div class="art-content-layout-row">
                    <div class="art-layout-cell art-sidebar1">
<div class="art-vmenublock">
    <div class="art-vmenublock-body">
                <div class="art-vmenublockheader">
                    <h3 class="t">Menu verticale</h3>
                </div>
                <div class="art-vmenublockcontent">
                    <div class="art-vmenublockcontent-body">
                <ul class="art-vmenu">
	<li>
		<a href="./index1.html"><span class="l"></span><span class="r"></span><span class="t">Login</span></a>
	</li>	
	<li>
		<a href="./registrazione1.html"><span class="l"></span><span class="r"></span><span class="t">Registrazione</span></a>
	</li>	
	<li>
		<a href="./gestore.html"><span class="l"></span><span class="r"></span><span class="t">Gestore</span></a>
	</li>	
	<li>
		<a href="./primaMappa.jsp" class="active"><span class="l"></span><span class="r"></span><span class="t">Localizzazione</span></a>
	</li>	
	<li>
		<a href="./Logout"><span class="l"></span><span class="r"></span><span class="t">Logout</span></a>
	</li>	
	<li>
		<a href="./contattaci1.html"><span class="l"></span><span class="r"></span><span class="t">Contattaci...</span></a>
	</li>	
</ul>
                
                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>

                      <div class="cleared"></div>
                    </div>
                    <div class="art-layout-cell art-content">
<div class="art-post">
    <div class="art-post-body">
<div class="art-post-inner art-article">
                                <div class="art-postmetadataheader">
                                        <h2 class="art-postheader">
                    <img src="./images/postheadericon.png" width="26" height="26" alt="">Localizzazione
                                        </h2>
                    <div class="cleared"></div>
                                                        </div>
                                <div class="art-postcontent">


<div id="map" style="width: 600px; height: 600px"></div>


                </div>
                <div class="cleared"></div>
                </div>

		<div class="cleared"></div>
    </div>
</div>

                      <div class="cleared"></div>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
            <div class="art-footer">
                <div class="art-footer-t"></div>
                <div class="art-footer-l"></div>
                <div class="art-footer-b"></div>
                <div class="art-footer-r"></div>
                <div class="art-footer-body">
                    <a href="#" class="art-rss-tag-icon" title="RSS"></a>
                            <div class="art-footer-text">
                                
<p>Copyright © 2011. All Rights Reserved.</p>


                                                            </div>
                    <div class="cleared"></div>
                </div>
            </div>
    		<div class="cleared"></div>
        </div>
    </div>
    <div class="cleared"></div>
    <p class="art-page-footer"><a href="http://www.artisteer.com/?p=website_templates">Website Template</a> created with Artisteer.</p>
    <div class="cleared"></div>
</div>
</div>
	
</BODY>
</html>