<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    QaService qaSvc = new QaService();
    List<QaVO> list = qaSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>
<h4><a href="select_page2.jsp">�^����</a></h4>
	
	<script>console.log(qaVO)</script>
	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">

     
  <table id="table-1">
	<tr><td>
		 <h3>�Ҧ��`�����D��� - listAllQa.jsp</h3>
	<h4><a href="select_page1.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	
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

<table>
	<tr>
		<th class="text-center">���D�s��</th>
		<th class="text-center">�Z�n���</th>
		<th class="text-center">�`�����D���D</th>
		<th class="text-center">�`�����D���e</th>
		
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td class="text-center">${qaVO.qa_no}</td>
			<td class="text-center">${qaVO.qa_date}</td>
			<td class="text-center">${qaVO.qa_title}</td>
			<td class="text-center">${qaVO.qa_cnt}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/qa/qa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="qa_no"  value="${qaVO.qa_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/qa/qa.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="qa_no"  value="${qaVO.qa_no}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

     
     
     
     
      
	</div>
    </div>
                
  </body>
</html>
