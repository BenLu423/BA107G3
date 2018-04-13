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
<title>Insert</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	<script>console.log(qaVO)</script>
	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000; width:600px;padding:7px;margin:150px;background: rgba(255,220,220,1);">

          <table id="table-1">
   <tr><td><h3>IBM Qa: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Qa: Home</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:blue">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllQa.jsp'>List</a> all Qas.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/qa/qa.do" >
        <b>��J���u�s�� (�p7001):</b>
        <input type="text" name="qa_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="qaSvc" scope="page" class="com.qa.model.QaService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/qa/qa.do" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="qa_no">
         <c:forEach var="qaVO" items="${qaSvc.all}" > 
          <option value="${qaVO.qa_no}">${qaVO.qa_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addQa.jsp'>Add</a> a new Qa.</li>
</ul>
          
							</div>
                        </div>
            
  </body>
</html>
