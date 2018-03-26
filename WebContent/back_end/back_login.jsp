<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Toast back login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/backlogin.css">
</head>
<body>

<c:remove var="admin"/>
	<div class="dbody">

		<div class="loginbar">

			<h1 align="center" style="margin-bottom: 50px;">Toast後台登入</h1>

			<form class="form-inline" action="<%=request.getContextPath()%>/admin/admin.do" method="post">
				<div class="form-group">
					<label for="account">帳號</label> <input type="text"
						class="form-control" id="account" placeholder="Enter account"
						name="account" value="${account}">
				</div>
				
				
				
				<div class="form-group">
					<label for="pwd">密碼</label> <input type="password"
						class="form-control" id="pwd" placeholder="Enter password"
						name="pwd">
				</div>

				<button type="submit" class="btn btn-default" id="login">登入</button><br>
				<div class="errorMsgs" style="color:red">${errorMsgs}</div>
				<input type="hidden" name="action" value="backlogin">
			</form>

		</div>

	</div>


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


</body>
</html>