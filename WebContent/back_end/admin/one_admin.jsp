<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.auth_feature.model.*" %>
<%	
	AuthFeatureService authFeatureSvc = new AuthFeatureService();
	List<AuthFeatureVO> auths = authFeatureSvc.getAll();
	pageContext.setAttribute("auths",auths);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>One Admin</title>
</head>
<body>

<jsp:include page="/back_end/header.jsp"></jsp:include>
	<div class="col-xs-12 col-sm-10 cont" id="admin">

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
						<h1>���u��ƺ޲z</h1>
					</div>
					<div class="col-xs-12 col-sm-12 admin-search">
						<form action="<%=request.getContextPath()%>/admin/admin.do" method="post">
							<div class="input-group">
								<input type="text" class="form-control" placeholder="���u�s��" name="search_no">
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
								<input type="hidden" name="action" value="search_admin">
								<div class="input-group-btn">
									<button class="btn btn-default" type="submit">
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
							<table class="table table-borderexd">
								<tr>
									<td><img src="<%=request.getContextPath()%>/back_end/res/img/hito.png" style="width: 50px"></td>
									<td>���u�s��<br>${admin.adm_no}
									</td>
									<td>���u�m�W<br>${admin.adm_name}
									</td>
									<td>���u�b��<br>${admin.adm_acct}
									</td>
									<td>���u�H�c<br>${admin.adm_mail}
									</td>
									<td>
									<form method="post" action="<%=request.getContextPath()%>/admin/admin.do">
										<input type="submit" value="�ק�">
										<input type="hidden" name="adm_no" value="${admin.adm_no}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="getOne_For_Update"></form>
									</td>
									<td><form method="post" action="<%=request.getContextPath()%>/admin/admin.do">
										<input type="submit" value="�R��">
										<input type="hidden" name="adm_no" value="${admin.adm_no}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="action" value="delete"></form></td>
								</tr>
								<tr>
									<td colspan="7">
										<c:forEach var="checked" items="${admin.tag}" varStatus="loop">
											<div class="auth-list ${(checked==1)?'checked':''}">
											<span class='glyphicon glyphicon-ok hlyphicon-lg'></span>
											<h5>${auths[loop.index].auth_name}</h5>
											</div>
										</c:forEach>
									</td>
								</tr>
							</table>
						
						<c:if test="${not empty errorMsgs}">
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>