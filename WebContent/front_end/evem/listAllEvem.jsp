<%@page import="java.util.List"%>
<%@page import="com.evem.model.EvemVO"%>
<%@page import="com.evem.model.EvemService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<%
EvemService evemSvc = new EvemService();
List<EvemVO> list = evemSvc.getAll();
pageContext.setAttribute("list",list);
%>
<%
  EvemVO evemVO = (EvemVO) request.getAttribute("evemVO");
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


        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-sm-offset-2">
                    <div class="page-header">
                        <div style="border-bottom-style:double; ">
                      <h1>活動留言</h1>
                    </div>
                    <div style="height: 20px"></div>
         <div class="col-xs-12 col-sm-10 col-sm-offset-1">
         
         <%@ include file="page1.file" %> 
	
		
         <% int count = 1; %>
         
         
        <c:forEach var="evemVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        	<% count++; %>
<%--         		<% if(count%4 == 1) {%> --%>
        		<c:if test="<%=count%4 == 0%>">
                <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${evemVO.evemes_cnt}</h3>
                   </div>
                   <div class="panel-body">
                    ${evemVO.evemes_rescnt}
                   </div>
                </div>
                </c:if>
                
<%--                 <% } %> --%>
                <% if(count%4 == 1) {%>
           <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${evemVO.evemes_cnt}</h3>
                   </div>
                   <div class="panel-body">
                     ${evemVO.evemes_rescnt}
                   </div>
                   <p>
                   
          
<!-- 					<button type="button" class="btn btn-default btn-lg btn-block">新增留言</button> -->
				
              
                </div>
                <% } %>
                <% if(count%4 == 2) {%>
                 <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${evemVO.evemes_cnt}</h3>
                   </div>
                   <div class="panel-body">
                       ${evemVO.evemes_rescnt}
                        </div>
                </div>
                 <% } %>
                <% if(count%4 == 3) {%>
             <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${evemVO.evemes_cnt}</h3>
                   </div>
                   <div class="panel-body">
                     ${evemVO.evemes_rescnt}
                      </div>
                </div>
                 <% } %>
          </c:forEach>
          <%@ include file="page2.file" %>

                   
		       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/evem/evem.do" name="form1">
		         <tr>
		            <td width="80" bgcolor="#FFFFFF"></td>
		            <td width="400" bgcolor="#FFFFFF">
		            <textarea name="evemes_cnt" rows="10" cols="112" value="<%= (evemVO==null)? "insert" : evemVO.getEvemes_cnt()%>"
		            ></textarea></td>
		        </tr>
		     


<%-- 				<input rows="4" cols="50" name="evemes_cnt" value="<%= (evemVO==null)? "" : evemVO.getEvemes_cnt()%>"> --%>
				
				</input>


		                
		  					
						
		                    <input type="hidden" name="action" value="insert3">
		                     <input type="submit" value="送出新增" class="btn btn-default btn-lg btn-block">
		                     
		          </FORM>
		          <div id="mbutton">神奇小按鈕</div>
		            </div> 
                </div>
            </div>
        </div>
    </ul>
 </nav>
</div> 
            
            
            
            
        </div>    
		<div class="col-xs-12 col-sm-1">
	        <a href="<%=request.getContextPath()%>/front_end/event/listAllEvent.jsp">
	        	<img src="${pageContext.request.contextPath}/front_end/res/img/event/back_event.jpg" alt="" style="width:130px;margin-top:130px;">
	        </a>
        </div>
    </div>      
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
 <script>
							$(document).ready(function() {
							$("#mbutton").click(function() {
								 	$("textarea[name='evemes_cnt']").val("寶寶有問題但寶寶不知道怎麼說?");
									
									});
							});
</script>
</html>