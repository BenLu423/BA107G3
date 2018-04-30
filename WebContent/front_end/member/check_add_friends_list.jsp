<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.friends_list.model.*" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">


<title>待審核名單</title>
<style type="text/css">

</style>
</head>
<body>
	<!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
	<!-- //header-->
		<%--  
		MemberVO mem = (MemberVO)session.getAttribute("memSelf");
		String self = mem.getMem_no();
		FriendsService fs = new FriendsService();
		List<FriendsListVO> frilist = new ArrayList<FriendsListVO>();
		frilist = fs.browsFriList("待審核", self);
		request.setAttribute("frilist", frilist);
		List<FriendsListVO> frijsp = (List<FriendsListVO>)request.getAttribute("frilist");
		MemberVO memvo = new MemberVO();
		MemberService ms = new MemberService();
		--%>
		
		<div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
                
                <div class="col-xs-12 col-sm-3">
                    	<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
                        <!-- 區塊1 -->
                        <div class="panel panel-warning">
                          <div class="panel-heading" role="tab" id="panel1">
                            <h3 class="panel-title">
                          	   好友管理
                            </h3>
                          </div>
                          <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                            <div class="panel-body">
                                <div><a href="<%=request.getContextPath()%>/front_end/member/member_manage_friendslist.jsp">好友名單</a></div><br>
                                <div><a href="<%=request.getContextPath()%>/front_end/member/check_add_friends_list.jsp">待審核</a></div><br>
                               
                            </div>
                          </div>
                        </div>
            	   	 </div>
                
                
                
                </div>
            	<%--
                <div class="col-xs-12 col-sm-7 col-sm-offset-1">
            			
	                   			<table class="table table-hover">	
									<caption>待審核</caption>
									<c:if test="<%=frijsp != null%>">
									<thead>
										<tr>
											<th>大頭貼</th>
											<th>姓名</th>
											<th>地區</th>
											<th>感情</th>
											<th>信箱</th>
											<th> </th>
										</tr>
									</thead>
									<%for(FriendsListVO fri : frijsp){
                   						memvo = ms.getOneMem(fri.getMem_no_other());
                   					%>
									<tbody>
										<tr>
											<td><img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=<%=memvo.getMem_no()%>" height="50px" width="50px"></td>
											<td><%=memvo.getMem_name()%></td>
											<td><%=memvo.getMem_county()%></td>
											<td><%=memvo.getMem_emotion()%></td>
											<td><%=memvo.getMem_mail()%></td>
											
											<td>
												<form action="<%=request.getContextPath()%>/friends/firlist.do" method="POST" class="set-form">
												<button type="submit" class="btn btn-primary">加入好友</button>
												<input type="hidden" name="memself" value="${memSelf.mem_no}">
												<input type="hidden" name="memother" value="<%=memvo.getMem_no()%>">
												<input type="hidden" name="action" value="finaladdfri">
												</form>
											</td>
											
											<td>
												<form action="<%=request.getContextPath()%>/friends/firlist.do" method="POST" class="set-form">
												<button type="submit" class="btn btn-primary">刪除好友</button>
												<input type="hidden" name="memself" value="${memSelf.mem_no}">
												<input type="hidden" name="memother" value="<%=memvo.getMem_no()%>">
												<input type="hidden" name="action" value="delfrilist">
												</form>
											</td>
											
											
											
											
										</tr>
									</tbody>
	
								<%} %>
								</c:if>
								</table>
							
                    </div>
                    --%>
                       <div class="col-xs-12 col-sm-7 col-sm-offset-1">
            			
	                   			<table class="table table-hover">	
									<div class="panel panel-warning">
										<div class="panel-heading">
											<h3 class="panel-title">待審核</h3>
										</div>
									</div>
									<c:if test="${not empty frilisthead}">
										<thead>
										<tr>
											<th>大頭貼</th>
											<th>姓名</th>
											<th>地區</th>
											<th>感情</th>
											<th>信箱</th>
											<th> </th>
										</tr>
									</thead>
									<c:forEach var="frilist" items="${frilisthead}">
									<tbody>
										<tr>
											<td><img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memvohead.mem_no}" height="50px" width="50px"></td>
											<td>${memvohead.mem_name}</td>
											<td>${memvohead.mem_county}</td>
											<td>${memvohead.mem_emotion}</td>
											<td>${memvohead.mem_mail}</td>
											
											<td>
												<form action="<%=request.getContextPath()%>/friends/firlist.do" method="POST" class="set-form">
												<button type="submit" class="btn btn-primary">加入好友</button>
												<input type="hidden" name="memself" value="${memSelf.mem_no}">
												<input type="hidden" name="memother" value="${memvohead.mem_no}">
												<input type="hidden" name="action" value="finaladdfri">
												</form>
											</td>
											
											<td>
												<form action="<%=request.getContextPath()%>/friends/firlist.do" method="POST" class="set-form">
												<button type="submit" class="btn btn-primary">刪除好友</button>
												<input type="hidden" name="memself" value="${memSelf.mem_no}">
												<input type="hidden" name="memother" value="${memvohead.mem_no}">
												<input type="hidden" name="action" value="delfrilistwait">
												</form>
											</td>
											
											
											
											
										</tr>
									</tbody>
	
							
									</c:forEach>
									</c:if>
								</table>
							
                    </div>
                </div>
              </div>
              <div class="col-xs-12 col-sm-1"></div>   
            </div>



		
	<!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->

</body>
</html>