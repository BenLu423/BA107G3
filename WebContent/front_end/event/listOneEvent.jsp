<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%@ page import="com.evep.model.*"%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO");
  EvepService eveSvc = new EvepService();
  List<String> list = eveSvc.getEvenMemNo(eventVO.getEve_no());
  pageContext.setAttribute("eventMem", list);//�����ʰѥ[�|��
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
              <div class="collapse navbar-collapse">
                  	<nav class="menu menu--iris">
                  	<ul class="nav navbar-nav menu__list">

					</ul>
					</nav>

        <div class="jumbotron">
          <div class="container" style="width:fit-content;height:100%">
          <img src="<%=request.getContextPath()%>/imagesServlet?action=event&no=<%=eventVO.getEve_no()%>">
          </div>
        </div>
            <div class="container">
			<div class="row">
				<div class="col-xs-10 style="padding:50px;font-size:1.1em;">
					
					<div class="page-header">
<%-- 					�|��${memSelf.mem_no} --%>
					<c:if test="${eventMem}.contains(${memSelf.mem_no})">�w�ѥ[</c:if>
					<c:forEach var="eventMem" items="${eventMem}">
<%-- 						�ѥ[${eventMem} --%>
						<font style="color:red"><c:if test="${eventMem == memSelf.mem_no}">�w�ѥ[�A���R�Գq��</c:if></font>
					</c:forEach>
					
					
					<jsp:useBean id="evecSvc" scope="page" class="com.evec.model.EvecService" />
					  <h1><%=eventVO.getEve_name()%>�@/�@<small><%=(evecSvc.getOne(eventVO.getEvec_no())).getEvec_name()%></small></h1>
					</div>
          			<%=eventVO.getEve_cnt()%>
					<div class="well">
						<p>�i���ʮɶ��j${eventVO.eve_start}�A�����аѾ\���W���</p>
					</div>
					 
              		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/event/event.do" >
<!-- 					   <p><a id="btn-joinEvent" class="btn btn-primary btn-lg"  href='#modal-id' data-toggle="modal" role="button" type="submit">���W����</a></p> -->
					   <input type="hidden" name="action" value="getEvent_apply">
					   <input type="hidden" name="eve_no" value="${eventVO.eve_no}">
					   <input type="hidden" name="member_no" value="${memSelf.mem_no}">
					   <input type="submit" value="���W����">
					 </FORM>
              	
         
<!-- 				<div class="modal fade" id="modal-id"> -->
<!-- 			<div class="modal-dialog"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> -->
<!-- 						<h4 class="modal-title">���\�T��</h4> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body"> -->
<!-- 						���ߧA�w���\���W�������ʡA���R�Գq�� -->
<!-- 					</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-default" data-dismiss="modal">�T�w</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
            </div>
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