<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.flsc.model.*"%>
<%@ page import="com.path.model.*"%>

<%
FlscVO flscVO = (FlscVO) request.getAttribute("flscQuery");

PathService pathSvc = new PathService();
String customs="customs";
PathVO pathVO1 = pathSvc.getOnePathFromTo(flscVO.getFlsc_gate(),customs);
PathVO pathVO2 = pathSvc.getOnePathFromTo(customs,flscVO.getFlsc_bag());

%>


<!DOCTYPE html>
<html lang="en" class="easy-sidebar-active">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>航班時刻表</title>

<!-- Bootstrap Core CSS -->
<link href="./css/bootstrap.css" rel="stylesheet">

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/path/mousewheel.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/front-end/path/mapbox.js"></script>
 
<style type="text/css"> 
    #viewport { 
        width: 1286px; 
        height: 500px; 
        cursor: move; 
        margin: 20px auto; 
        overflow: hidden; /*keep map contents from spilling over if JS is disabled*/ 
    } 
</style>  
 
 
</head>


<body bgcolor='white'>

	<div align="center">
	
<div>
     <h2>
		航班: <%=flscVO.getFlsc_airlinecode()%><%=flscVO.getFlsc_flno()%>
		將在登機門<%=flscVO.getFlsc_gate()%>下飛機<br>
		由登機門<%=flscVO.getFlsc_gate()%>到海關的路徑為<br>
		<img src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>" width="800" id="ImagePreview"><br>
		過海關後,請到行李轉盤<%=flscVO.getFlsc_bag()%>領行李<br>
		<img src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO2.getPath_id()%>" width="800" id="ImagePreview">
	</h2>
</div>
	
<hr>
測試放大縮小圖片	
	
<div id="viewport"> 
    <div style="background: url(<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>) no-repeat; width: 1286px; height: 1097px;"> 
        <!--top level map content goes here--> 
    </div> 

    <div style="height: 1097px; width: 1286px;"> 
        <img src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>" alt="" /> 
        <div class="mapcontent"> 
            <!--map content goes here--> 
        </div> 
    </div> 
    
    <div style="height: 1794px; width: 2104px;"> 
        <img src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>" alt="" /> 
         <div class="mapcontent"> 
            <!--map content goes here--> 
        </div> 
    </div> 
    <div style="height: 2492px; width: 2922px;"> 
        <img src="<%= request.getContextPath() %>/front-end/path/pathImg.do?path_id=<%=pathVO1.getPath_id()%>" alt="" /> 
         <div class="mapcontent"> 
            <!--map content goes here--> 
        </div> 
    </div>     
      
</div> 	

<script type="text/javascript"> 
    $(document).ready(function() { 
        $("#viewport").mapbox({mousewheel: true}); 
    }); 
</script> 

<script type="text/javascript"> 
    $("#viewport").mapbox({ 
        zoom: true, // does map zoom? 
        pan: true, // does map move side to side and up to down? 
        defaultLayer: 0, // starting at 0, which layer shows up first 
        layerSplit: 4, // how many times to split each layer as a zoom level 
        mapContent: ".mapcontent", // the name of the class on the content inner layer 
        defaultX: null, // default positioning on X-axis 
        defaultY: null, // default positioning on Y-axis 
        zoomToCursor: true, // if true, position on the map where the cursor is set will stay the same relative distance from the edge when zooming 
        doubleClickZoom: false, // if true, double clicking zooms to mouse position 
        clickZoom: false, // if true, clicking zooms to mouse position 
        doubleClickZoomOut: false, // if true, double clicking zooms out to mouse position 
        clickZoomOut: false, // if true, clicking zooms out to mouse position 
        doubleClickMove: false, // if true, double clicking moves the map to the cursor position 
        clickMove: false, // if true, clicking moves the map to the cursor position 
        doubleClickDistance: 1, // number of positions (determined by layerSplit) to move on a double-click zoom event 
        clickDistance: 1, // number of positions (determined by layerSplit) to move on a click zoom event 
        callBefore: function(layer, xcoord, ycoord, viewport) {}, // this callback happens before dragging of map starts 
        callAfter: function(layer, xcoord, ycoord, viewport) {}, // this callback happens at end of drag after map is released "mouseup" 
        beforeZoom: function(layer, xcoord, ycoord, viewport) {}, // callback before a zoom happens 
        afterZoom: function(layer, xcoord, ycoord, viewport) {}, // callback after zoom has completed 
        mousewheel: false /* requires mousewheel event plugin: http://plugins.jquery.com/project/mousewheel*/ 
    } 
) 
</script> 

<script type="text/javascript"> 
    $("#viewport").mapbox("zoom");//zooms in 1 level (determined by layerSplit option) 
    $("#viewport").mapbox("zoom", 2);//zooms in 2 levels 
    $("#viewport").mapbox("back");//zooms out 1 level 
    $("#viewport").mapbox("back", 2);//zooms out 2 levels 
    $("#viewport").mapbox("zoomTo", 1);//zooms to the default size of the third layer (0 being the first) 
    $("#viewport").mapbox("left");//move the current layer left 10 pixels 
    $("#viewport").mapbox("right");//move the current layer right 10 pixels 
    $("#viewport").mapbox("up");//move the current layer up 10 pixels 
    $("#viewport").mapbox("down");//move the current layer down 10 pixels 
    $("#viewport").mapbox("left", 20);//move the current layer left 20 pixels 
    $("#viewport").mapbox("right", 20);//move the current layer right 20 pixels 
    $("#viewport").mapbox("up", 20);//move the current layer up 20 pixels 
    $("#viewport").mapbox("down", 20);//move the current layer down 20 pixels 
    $("#viewport").mapbox("center");//centers current layer 
    $("#viewport").mapbox("center", { 
        x: 200, 
        y: 400 
    });//centers current layer at positions 200px, 400px on the x and y axis 
</script> 

<script>
jQuery(document).ready(function() { 
    $("#viewport").mapbox({ 
        mousewheel: true, 
        layerSplit: 8//smoother transition for mousewheel 
    }); 
    jQuery(".map-control a").click(function() {//control panel 
        var viewport = $("#viewport"); 
        //this.className is same as method to be called 
        if(this.className == "zoom" || this.className == "back") { 
            viewport.mapbox(this.className, 2);//step twice 
        } 
        else { 
            viewport.mapbox(this.className); 
        } 
        return false; 
    }); 
}) 

</script>

<br>
		
		
	</div>
	<br>
	<br>




	
</body>

</html>
