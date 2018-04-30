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

<title>Insert title here</title>
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
		frilist = fs.browsFriList("是", self);
		request.setAttribute("frilist", frilist);
		List<FriendsListVO> frijsp = (List<FriendsListVO>)request.getAttribute("frilist");
		MemberVO memvo = new MemberVO();
		MemberService ms = new MemberService();
	
		
	--%>
	<%
		List<FriendsListVO> havebeenfrilist = new ArrayList<FriendsListVO>();
		havebeenfrilist = (List<FriendsListVO>)request.getAttribute("havebeenfrilist1");
		request.setAttribute("havebeenfrilist", havebeenfrilist);
	%>
	
	<div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
                
                <div class="col-xs-12 col-sm-3">
                    	<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
                        <!-- 區塊1 -->
                        <div class="panel panel-warning ">
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
            
                <div class="col-xs-12 col-sm-7 col-sm-offset-1">

	                   		<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">好友名單</h3>
							</div>
							</div>
						<table class="table table-hover">	
									
									<c:if test="${not empty havebeenfrilist}">
						
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
									
									
									<%
									for(FriendsListVO fris : havebeenfrilist){
									MemberVO memvohbfri = new MemberVO();
									MemberService mshbfri = new MemberService();
									memvohbfri = mshbfri.getOneMem(fris.getMem_no_other());
									request.setAttribute("memvohbfri", memvohbfri);
									%>
									<tbody>
										<tr>
											<td><img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memvohbfri.mem_no}" height="50px" width="50px"></td>
											<td>${memvohbfri.mem_name}</td>
											<td>${memvohbfri.mem_county}</td>
											<td>${memvohbfri.mem_emotion}</td>
											<td>${memvohbfri.mem_mail}</td>
											<td><a class="btn btn-primary" href="<%=request.getContextPath()%>/member/mem.do?mem_no=${memvohbfri.mem_no}&action=getOne_From06">刪除好友</a></td>
											
										</tr>
									</tbody>
	
								<%} %>

								</c:if>
								</table>
						
						
		
						<c:if test="${openModal!=null}">
								<% 	
									MemberVO Onememvo = (MemberVO)request.getAttribute("memvo");
								%>
						
								<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content">
												
											<div class="modal-header">
								                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								                <h3 class="modal-title" id="myModalLabel">刪除好友</h3>
								            </div>
											
											<div class="modal-body">
													<table class="table table-hover">
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
													<tbody>
													<tr>
													<td><img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=<%=Onememvo.getMem_no()%>" height="50px" width="50px"></td>
													<td><%=Onememvo.getMem_name()%></td>
													<td><%=Onememvo.getMem_county()%></td>
													<td><%=Onememvo.getMem_emotion()%></td>
													<td><%=Onememvo.getMem_mail()%></td>
													</tr>
													</tbody>
													</table>
	
											</div>
											
											<div class="modal-footer">					            	
								               	<form action="<%=request.getContextPath()%>/friends/firlist.do" method="POST" class="set-form">
												<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
												<button type="submit" class="btn btn-primary">確定刪除</button>
												<input type="hidden" name="memself" value="${memSelf.mem_no}">
												<input type="hidden" name="memother" value="<%=Onememvo.getMem_no()%>">
												<input type="hidden" name="action" value="delfrilist">
												</form>
								            </div>
										
										</div>
									</div>
								</div>
								
								        <script>
								    		 $("#basicModal").modal({show: true});
								        </script>
								 </c:if>
					
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