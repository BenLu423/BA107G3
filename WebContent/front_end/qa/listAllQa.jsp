<%@page import="java.util.List"%>
<%@page import="com.qa.model.QaVO"%>
<%@page import="com.qa.model.QaService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<%
QaService qaSvc = new QaService();
List<QaVO> list = qaSvc.getAll();
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
            
                          <div class="collapse navbar-collapse">
                        <nav class="menu menu--iris">
                        <ul class="nav navbar-nav menu__list">


        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-sm-offset-2">
                    <div class="page-header">
                        <div style="border-bottom-style:double; ">
                      <h1>會員常見問題</h1>
                    </div>
                    <div style="height: 20px"></div>
         <div class="col-xs-12 col-sm-10 col-sm-offset-1">
         
         <%@ include file="page1.file" %> 
	
		
         <% int count = 1; %>
         
         
        <c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        	<% count++; %>
<%--         		<% if(count%4 == 1) {%> --%>
        		<c:if test="<%=count%4 == 0%>">
                <div class="panel panel-danger">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                    ${qaVO.qa_cnt}
                   </div>
                </div>
                </c:if>
<%--                 <% } %> --%>
                <% if(count%4 == 1) {%>
                <div class="panel panel-success">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                    ${qaVO.qa_cnt}
                   </div>
                </div>
                <% } %>
                <% if(count%4 == 2) {%>
                 <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                       ${qaVO.qa_cnt}
                        </div>
                </div>
                 <% } %>
                <% if(count%4 == 3) {%>
                <div class="panel panel-warning">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                     ${qaVO.qa_cnt}
                      </div>
                </div>
                 <% } %>
          </c:forEach>
          <%@ include file="page2.file" %>
<!--                 <div class="panel panel-success"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">我填的這些資料會洩漏出去嗎？</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                      您好，我們了解大家對個人隱私及資料安全的擔心，您的資料已透過我們完善的加密系統保護(以1111人力銀行對待求職者的嚴謹態度比照處理)。同時，我們也要求相關管理人員不可私下洩漏會員資料，未經您個人同意，其他會員(包含VIP會員)是無法取得您的詳細資料，詳細資訊可參閱隱私權保護政策。 -->
<!--                    </div> -->
<!--                 </div> -->
<!--                 <div class="panel panel-info"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">為什麼註冊需要留這麼多資料？</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                       您好，我們為了秉持嚴謹的實名制認證，需透過您個人資料的完整度，確保每位會員享有的權益，讓彼此的交友更有保障。別忘了，交友資料填得越詳細，就越容易讓大家認識你哦！ -->
<!--                    </div> -->
<!--                 </div> -->
<!--                 <div class="panel panel-warning"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">活動收費的內容是？</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                       您好，活動報名費依地點方式不同而不同。在您點選「交友活動」後，若有開放報名中的活動，您可在畫面上看到各時間場次及報名費用，依您的時間預算進行報名及繳費即可。一般的活動費用約在99~2500元之間，越高級的場地、餐飲，或是需過夜的聯誼活動費用相對較高，但是氣氛、質感與相處時間也相對較多，皆可由您自行挑選。 -->
<!--                    </div> -->
<!--                 </div> -->
                   
    
                </div>
            </div>
        </div>
    </ul>
 </nav>
</div> 
            
            
            
            
        </div>    
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>