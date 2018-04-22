<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.*"%>
<%
MemberVO memSelf = (MemberVO) session.getAttribute("memSelf");
MemberService memSvc = new MemberService();
List<MemberVO> allMem = memSvc.getallMem();
request.setAttribute("allMem",allMem);
String jsonStr = new JSONArray(allMem).toString();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>map</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
        <div class="content-top"></div>  
    		<div class="col-xs-1 col-sm-3" style="background-color:pink ; height:750px">
    			<br>選擇距離：<select id="kyori">
    				<% 
    				int count=0;
    				for(int i = 1 ; i<=300 ; i++){
    					
    					if(i==1 || i%10==0 ){
    						count++;
    				%>
						<option value="<%=count%>"><%=i %></option>
					<%}} %>    				
    			</select>公里
    		
    		</div>
    		<div class="col-xs- col-sm-9" style="background-color:skyblue; height:750px">
			
			<h3>距離配對</h3>
			    <div id="map" style="width:100% ; height:90%"></div>
			    <script>
			    
			    var memCircle ;
			    var map;
			    var currentInfoWindow = ''; //Global variable   
			      function initMap() {
			        var uluru = {lat:<%=(memSelf==null)? "23.58" : memSelf.getMem_latitude()%>, lng:<%=(memSelf==null)? "120.58" : memSelf.getMem_longitude()%>};
			        map = new google.maps.Map(document.getElementById('map'), {
			          zoom: 15,
			          center: uluru
			        });
			        
			        var marker = new google.maps.Marker({
			            position: uluru,
			            map: map
			          });
			        
			        memCircle = new google.maps.Circle({
			            strokeColor: '#FF0000',
			            strokeOpacity: 0.8,
			            strokeWeight: 2,
			            fillColor: '#FF0000',
			            fillOpacity: 0.35,
			            map: map,
			            center: uluru,
			            radius: 1000
			          });
			        
			        <c:forEach var="allMem" items="${allMem}">
				        var myLatlng = new google.maps.LatLng(${allMem.mem_latitude},${allMem.mem_longitude});
				        var icon = {
				        		url:"<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${allMem.mem_no}",
				        		size:new google.maps.Size(50, 50),
				        		origin: new google.maps.Point(0, 0),
				        		anchor: new google.maps.Point(0, 32),
				        		scaledSize: new google.maps.Size(50, 50)
				        };
				        var ${allMem.mem_no} = new google.maps.Marker({
				            position: myLatlng,
				            icon:icon,
				            title:"${allMem.mem_name}",
				            
				        });
				        
				        
				        
				        var infoWindow_${allMem.mem_no} = new google.maps.InfoWindow({
				            content: "會員：${allMem.mem_name}<br>性別：${allMem.mem_gender}"+
				            ""
				        }); 
				        
	
				        // To add the marker to the map, call setMap();
				        ${allMem.mem_no}.setMap(map);
				       
				        ${allMem.mem_no}.addListener('click', function() {
				        	if(currentInfoWindow != ''){    
				              currentInfoWindow.close();   
				              currentInfoWindow = '';   
				            }   
				        
				        	infoWindow_${allMem.mem_no}.open(map, ${allMem.mem_no});
				        	
				        	currentInfoWindow = infoWindow_${allMem.mem_no};
				          });
			        </c:forEach>
			        
			        
			      
			      }
			      
			      
			      
			      /*************控制距離和縮放****************/
			      $("#kyori").change(function(){
			    	var hani = $("#kyori option:selected").text();//距離
			    	var val = $("#kyori option:selected").val();//縮放
			    	//alert(val);
			    	map.setZoom(15-val);	 
			    	memCircle.setRadius(hani * 1000);
			    	 
			      });
			      
			      
			      
			      
			    </script>
			    <script async defer
			    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCysVlOJDkATMLRKkDFn18qTR4ip-N-nf4&callback=initMap">
			    </script>
			
			
			</div>
    	
    	
    	
    	</div>
    </div>
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>