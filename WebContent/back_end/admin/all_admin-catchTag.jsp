<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admin_auth.model.*" %>
<%@ page import="com.auth_feature.model.*" %>
<%
	AdminService adminSvc = new AdminService();
	List<AdminVO> list = adminSvc.getAll();
	pageContext.setAttribute("admin", list);
	
	AuthFeatureService authFeatureSvc = new AuthFeatureService();
	List<AuthFeatureVO> auths = authFeatureSvc.getAll();
	pageContext.setAttribute("auths",auths);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Admin List</title>
</head>
<body>

	<jsp:include page="/back_end/header.jsp"></jsp:include>


	<div class="col-xs-12 col-sm-10 cont" id="admin">

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
						<h1>員工資料管理</h1>
						<c:if test="${not empty errorMsgs}">
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>
					</div>
					<div class="col-xs-12 col-sm-12 admin-search">
						<form action="<%=request.getContextPath()%>/admin/admin.do" method="post">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="員工編號" name="search_no">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="search_admin">
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit" style="height:34px;">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">

					<div class="admin-list">

						<div class="item col-sm-12">

							<%@ include file="pages/page1.file" %> 
							<c:forEach var="adminVO" items="${admin}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<table class="table table-borderexd">
									<tr>
										<td><img src="<%=request.getContextPath()%>/back_end/res/img/hito.png" style="width: 50px"></td>
										<td>員工編號<br>${adminVO.adm_no}
										</td>
										<td>員工姓名<br>${adminVO.adm_name}
										</td>
										<td>員工帳號<br>${adminVO.adm_acct}
										</td>
										<td>員工密碼<br>${adminVO.adm_pwd}
										</td>
										<td>
										<form method="post" action="<%=request.getContextPath()%>/admin/admin.do">
											<input type="submit" value="修改">
											<input type="hidden" name="adm_no" value="${adminVO.adm_no}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="getOne_For_Update"></form>
										</td>
										<td><form method="post" action="<%=request.getContextPath()%>/admin/admin.do">
											<input type="submit" value="刪除">
											<input type="hidden" name="adm_no" value="${adminVO.adm_no}">
											<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
											<input type="hidden" name="whichPage" value="<%=whichPage%>">
											<input type="hidden" name="action" value="delete"></form></td>
									</tr>
									<tr>
										<td colspan="7">
										
										
											<c:forEach var="checked" items="${adminVO.tag}" varStatus="loop">
												
												<div class="auth-list ${(checked==1)?'checked':''}">
												<span class='glyphicon glyphicon-ok hlyphicon-lg'></span>
												<h5>${auths[loop.index].auth_name}</h5>
												</div>
												
											</c:forEach>
											
											
										</td>
									</tr>
								</table>
						</c:forEach>
						<%@ include file="pages/page2.file" %>
						
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		
	</script>


</body>
</html>