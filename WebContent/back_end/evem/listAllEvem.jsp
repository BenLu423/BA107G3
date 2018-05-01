<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.evem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    EvemService evemSvc = new EvemService();
    List<EvemVO> list = evemSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���ʯd��</title>
	<link href="../../bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="../../scripts/jquery-1.11.2.min.js"></script>
	<script src="../../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>
	
	<script>console.log(evemVO)</script>
	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;width:1300px;border-color:#000;padding:7px;margin:100px;background: rgba(255,220,220,1);">

     
  <table id="table-1">
	<tr><td>

		 <h3>�Ҧ����ʯd�����</h3>
	
	
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%@ include file="page1.file" %> 
	<table class="table-hover " border="1px,solid" >	
		<thead>
			<tr>
				<th class="text-center" style="width:150px;"><font size="6">���ʯd���s��</font></th>
				<th class="text-center" style="width:150px;"><font size="6">���ʽs��</font></th>
				<th class="text-center" style="width:150px;"><font size="6">�|���s��</font></th>
				<th class="text-center" style="width:150px;"><font size="6">�d���ɶ�</font></th>
				<th class="text-center" style="width:150px;"><font size="6">�d�����e</font></th>
				
				<th class="text-center" style="width:200px;"><font size="6">�d���^�����e</font></th>
				<th class="text-center" style="width:150px;"><font size="6">�d���^��</font></th>
				
			</tr>
		</thead>
	<tbody>
		<c:forEach var="evemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td class="text-center"><font size="5">${evemVO.evemes_no}</font></td>
			<td class="text-center"><font size="5">${evemVO.eve_no}</font></td>
			<td class="text-center"><font size="5">${evemVO.mem_no}</font></td>
			<td class="text-center"><font size="5">${evemVO.evemes_time}</font></td>
			<td class="text-center"><font size="5">${evemVO.evemes_cnt}</font></td>
			
			<td class="text-center"><font size="5">${evemVO.evemes_rescnt}</font></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/evem/evem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�^�Ь��ʯd��">
			     <input type="hidden" name="evemes_no"  value="${evemVO.evemes_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/qa/qa.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="�R��"> -->
<%-- 			     <input type="hidden" name="qa_no"  value="${qaVO.qa_no}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"> -->
<!-- 			  </FORM> -->
			</td>
		</tr>
	</c:forEach>
<%@ include file="page2.file" %></tbody>
</table>
</div>


</table>
	</div>
    </div>
                
  </body>
</html>
				
			