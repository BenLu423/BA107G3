<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  QaVO qaVO = (QaVO) request.getAttribute("qaVO"); 
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>常見問題管理</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">

         <table id="table-1">
	<tr><td>
		 <h3>ListOneQa</h3>
		 <h4><a href="select_page1.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table border="1px,solid">
	<tr>
		<th>常見問題編號</th>
		<th>刊登日期</th>
		<th>常見問題標題</th>
		<th>常見問題內容</th>
	</tr>
	<tr>
		<td><%=qaVO.getQa_no()%></td>
		<td><%=qaVO.getQa_date()%></td>
		<td><%=qaVO.getQa_title()%></td>
		<td><%=qaVO.getQa_cnt()%></td>
		
	</tr>
</table>
                            
							</div>
                        </div>
                
  </body>
</html>
