<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin_auth.model.*" %>
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
<title>Admin update</title>
</head>
<body>

	<jsp:include page="/back_end/header.jsp"></jsp:include>
	<div class="col-xs-12 col-sm-10 cont" id="admin">

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
						<h1>員工資料修改</h1>
						<c:if test="${not empty errorMsgs}">
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="searchable-container">
						<div class="item col-sm-12">
						
							<form  action="<%=request.getContextPath()%>/admin/admin.do" method="post">
								<table class="table table-bordered">
								<tr>
									<td><img src="<%=request.getContextPath()%>/back_end/res/img/hito.png" style="width: 50px"></td>
									<td>員工編號<br>${admin.adm_no}
									</td>
									<td>員工姓名<br> <input type="text" name="adminName" value="${admin.adm_name}"></td>
									<td>員工帳號<br><input type="text" name="account" value="${admin.adm_acct}"></td>
									<td>員工信箱<br> <input type="text" name="mail" value="${admin.adm_mail}"></td>
									<td><input type="submit" value="送出">
										<input type="hidden" name="adm_no" value="${admin.adm_no}">
										<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
										<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">
										<input type="hidden" name="action" value="update">
									</td>
								</tr>
								<tr>
									<td colspan="6">
									
									<c:forEach var="tag" items="${admin.tag}" varStatus="loop">
									<div data-toggle="buttons" class="btn-group bizmoduleselect">
										<label class="btn btn-default  ${(tag==1)?'active':''}">
											<div class="bizcontent">
												<input type="checkbox" name="auth" autocomplete="off" value="${auths[loop.index].auth_no}" ${(tag==1)?'checked':''}> 
												<span class="glyphicon glyphicon-ok glyphicon-lg"></span>
												<h5>${auths[loop.index].auth_name}</h5>
											</div>
										</label>
									</div>
									</c:forEach>
									</td>
								</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>