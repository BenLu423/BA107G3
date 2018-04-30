<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>


<%
    EventService eventSvc = new EventService();
    List<EventVO> list = eventSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%  
    List<EventVO> listOFF = eventSvc.getAll_sts_off();
    pageContext.setAttribute("listOFF",listOFF);
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>下架中活動</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	
<div class="col-xs-4 col-sm-4 cont">
	   		

<div style="height: 70px"></div>
   
<div class="container">
<div class="col-12" >
<div style=border-style:double;background:rgba(255,220,220,1);width:1500px>
	
  <h4><a href="event_title.jsp">回首頁</a></h4><br>
  <h2>活動總覽</h2><br>

  <table class="table" style="width: 100%">
 
    <thead>
      <tr>
       	<th style="width:150px;"><font size="4">編號</font></th>
		<th style="width:150px;"><font size="4">編號</font></th>
		<th style="width:150px;"><font size="4">標題</font></th>
		<th style="width:150px;"><font size="4">開始時間</font></th>
		<th style="width:150px;"><font size="4">結束時間</font></th>
		
		<th style="width:100px;"><font size="4">活動詳情</font></th>
		<th style="width:150px;"><font size="4">活動圖片</font></th>
		<th style="width:150px;"><font size="4">名額</font></th>
		<th style="width:150px;"><font size="4">地點</font></th>
		<th style="width:150px;"><font size="4">費用</font></th>
		
		<th style="width:150px;"><font size="4">修改</font></th>
		<th style="width:180px;"><font size="4">上下架</font></th>
		<th style="width:150px;"><font size="4">參加名單</font></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="eventVO" items="${list}">
      <tr>
      
      		<td><font size="5">${eventVO.eve_no}</font></td>
			<td><font size="5">${eventVO.evec_no}</font></td>
			<td><font size="5">${eventVO.eve_name}</font></td>
			<td><font size="5">${eventVO.eve_start}</font></td>
			<td><font size="5">${eventVO.eve_end}</font></td>
			
			<td><div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;font-size:5px">${eventVO.eve_cnt}</div></td>
			<td><img style="width:150px;" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}"></td>
			<td><font size="4">${eventVO.eve_quota}</font></td>
			<td><font size="4">${eventVO.eve_site}</font></td>
			<td><font size="4">${eventVO.eve_regfee}</font></td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update2"></FORM>
			</td>
			<td style="width:80px">
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value=<c:if test="${eventVO.eve_sts eq '上架'}">"下架"</c:if><c:if test="${eventVO.eve_sts eq '下架'}">"上架"</c:if>>
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action" value=<c:if test="${eventVO.eve_sts eq '上架'}">"onToOff"</c:if><c:if test="${eventVO.eve_sts eq '下架'}">"offToOn"</c:if>></FORM>
			</td>
		
			<td style="width:90px">
			<a href="<%=request.getContextPath()%>/back_end/event/listAllEvemp.jsp"><button>瀏覽</button></a>
			  
			</td>
      </tr>      
     
     </c:forEach>
    </tbody>
  			
    </table>
  
    </div>
    </div>
  </div>
  </div>


</body>
</html>
