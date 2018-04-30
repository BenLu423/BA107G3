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
  <title>�U�[������</title>
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
	
  <h4><a href="event_title.jsp">�^����</a></h4><br>
  <h2>�����`��</h2><br>

  <table class="table" style="width: 100%">
 
    <thead>
      <tr>
       	<th style="width:150px;"><font size="4">�s��</font></th>
		<th style="width:150px;"><font size="4">�s��</font></th>
		<th style="width:150px;"><font size="4">���D</font></th>
		<th style="width:150px;"><font size="4">�}�l�ɶ�</font></th>
		<th style="width:150px;"><font size="4">�����ɶ�</font></th>
		
		<th style="width:100px;"><font size="4">���ʸԱ�</font></th>
		<th style="width:150px;"><font size="4">���ʹϤ�</font></th>
		<th style="width:150px;"><font size="4">�W�B</font></th>
		<th style="width:150px;"><font size="4">�a�I</font></th>
		<th style="width:150px;"><font size="4">�O��</font></th>
		
		<th style="width:150px;"><font size="4">�ק�</font></th>
		<th style="width:180px;"><font size="4">�W�U�[</font></th>
		<th style="width:150px;"><font size="4">�ѥ[�W��</font></th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update2"></FORM>
			</td>
			<td style="width:80px">
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value=<c:if test="${eventVO.eve_sts eq '�W�['}">"�U�["</c:if><c:if test="${eventVO.eve_sts eq '�U�['}">"�W�["</c:if>>
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action" value=<c:if test="${eventVO.eve_sts eq '�W�['}">"onToOff"</c:if><c:if test="${eventVO.eve_sts eq '�U�['}">"offToOn"</c:if>></FORM>
			</td>
		
			<td style="width:90px">
			<a href="<%=request.getContextPath()%>/back_end/event/listAllEvemp.jsp"><button>�s��</button></a>
			  
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
