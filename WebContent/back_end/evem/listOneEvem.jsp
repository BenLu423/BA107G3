<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.evem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  EvemVO evemVO = (EvemVO) request.getAttribute("evemVO"); 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>活動留言回覆</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">

         <table id="table-1">
	<tr><td>
		 <h3>ListOneEvem</h3>
		<h4><a href="<%=request.getContextPath()%>/back_end/evem/listAllEvem.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table border="1px,solid">
	<tr>
		<th style="width:170px;"><font size="6">活動留言編號</font></th>
		<th style="width:170px;"><font size="6">活動留言日期</font></th>
		<th style="width:170px;"><font size="6">留言內容</font></th>
		
		<th style="width:170px;"><font size="6">回覆留言內容</font></th>
		
	</tr>
	<tr>
		<td><font size="6"><%=evemVO.getEvemes_no()%></font></td>
		<td><font size="6"><%=evemVO.getEvemes_time()%></font></td>
		<td><font size="6"><%=evemVO.getEvemes_cnt()%></font></td>
		
		<td><font size="6"><%=evemVO.getEvemes_rescnt()%></font></td>
	</tr>
</table>
                            
							</div>
                        </div>
                
  </body>
</html>
