<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.admin.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>新增員工帳號</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>
	
	
	<div class="col-xs-12 col-sm-10 cont" id="adminInsert">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
						<h1>新增帳號</h1>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12" style="background-color:#fff;border-color:#aaa;border-style:solid">
					<form class="form-inline" action="<%=request.getContextPath()%>/admin/admin.do" method="post">
						<div class="form-group">
							<label for="adminName">員工姓名</label> <input type="text"
								class="form-control" id="adminName" placeholder="Enter name"
								name="adminName" value="${requestScope.admin['adm_name']}">
						</div>
						<br>
						<div class="form-group">
							<label for="account">員工帳號</label> <input type="text"
								class="form-control" id="account" placeholder="Enter account"
								name="account" value="${requestScope.admin['adm_acct']}">
						</div>
						<br>
						
						<div class="form-group">
							<label for="mail">員工信箱</label> <input type="text"
								class="form-control" id="mail" placeholder="Enter password"
								name="mail" value="${requestScope.admin['adm_mail']}">
						</div>
						<br>

						<hr>
						<jsp:useBean id="authFeatureSvc" scope="page" class="com.auth_feature.model.AuthFeatureService"/>
						<h4>設定權限</h4>
						<div class="searchable-container">
						<c:forEach var="authFeatureVO" items="${authFeatureSvc.all}">
							<div data-toggle="buttons" class="btn-group bizmoduleselect">
								<label class="btn btn-default">
									<div class="bizcontent">
										<input type="checkbox" name="auth" autocomplete="off" value="${authFeatureVO['auth_no']}"> 
										<span class="glyphicon glyphicon-ok glyphicon-lg"></span>
										<h5>${authFeatureVO['auth_name']}</h5>
									</div>
								</label>
							</div>
						</c:forEach>
						</div>

						<br> <br>
						
						<button type="submit" class="btn btn-default" id="login" style="margin-bottom:10px">新增</button>
						<input type="hidden" name="action" value="insert_admin">
						
						<c:if test="${not empty errorMsgs}">
							<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
							</ul>
						</c:if>
					</form>

					<button id="insertNewAdmin" style="margin-bottom:10px">GO</button>

				</div>
			</div>
		</div>
		</div>
		
		<script>
			$('#insertNewAdmin').click(function(){
				$('#adminName').val("竹竹");
				$('#account').val("QQQ");
				$('#mail').val("jwes4421@gmail.com");
				
			});
		
		</script>
		
		
</body>
</html>