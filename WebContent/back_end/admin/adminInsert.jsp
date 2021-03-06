<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.admin.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>
	
	
	<script>console.log(adminVO);</script>
	
	<div class="col-xs-12 col-sm-10 cont">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
						<h1>新增帳號</h1>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<form class="form-inline" action="<%=request.getContextPath()%>/admin/admin.do" method="post">
						<div class="form-group">
							<label for="adminName">員工姓名</label> <input type="text"
								class="form-control" id="adminName" placeholder="Enter name"
								name="adminName" value="${admin['adm_name']}">
						</div>
						<br>
						<div class="form-group">
							<label for="account">員工帳號</label> <input type="text"
								class="form-control" id="account" placeholder="Enter account"
								name="account" value="${admin['adm_acct']}">
						</div>
						<br>
						<div class="form-group">
							<label for="pwd">員工密碼</label> <input type="password"
								class="form-control" id="pwd" placeholder="Enter password"
								name="pwd">
						</div>
						<br>

						<hr>
						<h4>設定權限</h4>
						<input type="checkbox" value="AF001" name="auth">帳號管理<br>
						<input type="checkbox" value="AF002" name="auth">會員管理<br>
						<input type="checkbox" value="AF003" name="auth">頁面管理<br>
						<input type="checkbox" value="AF004" name="auth">商城管理<br>
						<input type="checkbox" value="AF005" name="auth">折價券管理<br>
						<input type="checkbox" value="AF006" name="auth">活動管理<br>
						<input type="checkbox" value="AF007" name="auth">檢舉管理<br>

						<br> <br>
						
						<button type="submit" class="btn btn-default" id="login">新增</button>
						<input type="hidden" name="action" value="insert_admin">
						
						<c:if test="${not empty errorMsgs}">
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>

						
					</form>

				</div>
			</div>
		</div>
		</div>
</body>
</html>