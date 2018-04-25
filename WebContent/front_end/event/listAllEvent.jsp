<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%
EventService eventSvc = new EventService();
List<EventVO> list = eventSvc.getAll_sts_on();
pageContext.setAttribute("list",list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Toast</title>

</head>

<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
           
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
  <div role="tabpanel">

	<div class="tab-content">
           <div role="tabpanel" class="tab-pane active" id="tab1">
        <c:forEach var="eventVO" items="${list}" >
	
               <div class="container-fluid eventlist">
                  <div class="col-sm-4 pic">
                     <img src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}" class="img-responsive center-block"> 
                  </div>
                  <div class="col-sm-8 text">
                      <font data-brackets2-id='7547' size="20"> ${eventVO.eve_name} </font>
                      <p>${eventVO.eve_cnt}</P>
                  
                      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/event/event.do" style="margin-bottom: 0px;">
		     <input type="submit" value="活動詳情">
		     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
		     <input type="hidden" name="action" value="getEvent_No">
		</FORM>
                     </div>
                 </div> 
        </c:forEach>

        
        </div>
        </div>
        </div>
        </div>
   
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>

<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>