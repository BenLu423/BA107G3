<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.evep.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EvepService evepSvc = new EvepService();
    List<EvepVO> list = evepSvc.getAll_EVEP(); 
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
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
<div style=border-style:double;background:rgba(255,220,220,1);width:900px>
	
		
		<a href="select_page3.jsp">回首頁</a>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
  <font color='red'>請修正以下錯誤:
  <ul>
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>活動編號</th>
		<th>會員編號</th>
		<th>已/未報到</th>
		<th>報到碼</th>
	</tr>
	
<%-- 	<c:set var="mem" value="${memberSvc.getOneMem(friend.mem_no_other)}"/> --%>
<c:forEach var="evenp" items="${list}">
		<tr align='center' valign='middle'>
			<td>${evenp.eve_no}</td>
			<td>${evenp.mem_no}</td>
			<td>${evenp.evep_sts}</td>
			<td><img  style="width:150px;" src="<%=request.getContextPath()%>/Image_Evep_Servlet?mem_no=${evenp.mem_no}&eve_no=${evenp.eve_no}"></td>
<%-- 		</tr><img style="width:150px;" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}"> --%>
			</c:forEach>
	
</table>
</div>
</div>
</div>
</div>



</body>
</html>
