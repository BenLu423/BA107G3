<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>


<%
    EventService eventSvc = new EventService();
    List<EventVO> list = eventSvc.getAll_sts_on();
    pageContext.setAttribute("list",list);
%>

<%  
    List<EventVO> listOFF = eventSvc.getAll_sts_off();
    pageContext.setAttribute("listOFF",listOFF);
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
<div style=border-style:double;background:rgba(255,220,220,1);width:1500px>
	
  <h4><a href="event_title.jsp">�^����</a></h4><br>
  <h2>�����s�� </h2><br>

  <table class="table" style="width: 100%">
 
    <thead>
      <tr>
       	<th><font size="6">���ʽs��</font></th>
		<th><font size="6">�����s��</font></th>
		<th><font size="6">���ʦW��</font></th>
		<th><font size="6">�}�l�ɶ�</font></th>
		<th><font size="6">�����ɶ�</font></th>
		
		<th style="width:150px;"><font size="6">���ʸԱ�</font></th>
		<th><font size="6">���ʹϤ�</font></th>
		<th><font size="6">���ʦW�B</font></th>
		<th><font size="6">���ʦa�I</font></th>
		<th><font size="6">���ʶO��</font></th>
		
		<th><font size="6">���ʭק�</font></th>
		<th><font size="6">���ʪ��A</font></th>
		<th><font size="6">�ѥ[�W��</font></th>
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
			
			<td style="width:150px;"><font size="5">${eventVO.eve_cnt}</font></td>
			<td><img style="width:150px;" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}"></td>
			<td><font size="5">${eventVO.eve_quota}</font></td>
			<td><font size="5">${eventVO.eve_site}</font></td>
			<td><font size="5">${eventVO.eve_regfee}</font></td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update2"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�U�[��">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action" value="onToOff"></FORM>
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
