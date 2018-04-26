<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Event: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

         
	<jsp:include page="/back_end/header.jsp"></jsp:include>


<div class="col-xs-12 col-sm-10 cont">
	   		
	<div style="border-width:6px;border-style:double;border-color:#000; width:650px;padding:7px;margin:80px;background: rgba(255,220,220,1);">
	<div><img src="<%=request.getContextPath()%>/back_end/res/img/event/event2.jpg"></div>
  
<h3>���ʫ�x</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

   <ul>
	  <li>�s�W�@�Ӭ���&nbsp&nbsp&nbsp&nbsp<a href='addEvent.jsp'>����</a></li>
	  <li>
		    <FORM METHOD="post" ACTION="event.do" >
		        <b>��J��@���ʽs��</b>
		        <input type="text" name="eve_no" value="E002">
		        <input type="hidden" name="action" value="getOne_For_Display2">
		        <input type="submit" value="�e�X">
		    </FORM>
 	 </li>

  	     <jsp:useBean id="eventSvc" scope="page" class="com.event.model.EventService" />
   
		  <li>
		     <FORM METHOD="post" ACTION="event.do" >
		       <b>��ܬ��ʽs��:</b>
		       <select size="1" name="eve_no">
		         <c:forEach var="eventVO" items="${eventSvc.all}" > 
		          <option value="${eventVO.eve_no}">${eventVO.eve_no}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display2">
		       <input type="submit" value="�e�X">
		    </FORM>
		  </li>
  
	  		  <li><br>�����`��&nbsp&nbsp&nbsp&nbsp<a href='listAllEvent.jsp'>����</a><br></li>
	 
      </ul>
	</div>
</div>

</body>
</html>