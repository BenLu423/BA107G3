<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%@ page import="com.admin_auth.model.*"%>
<%
	AdminVO admin = (AdminVO)session.getAttribute("admin");
	List<Integer>tag = new ArrayList<Integer>();
	if(admin==null){
		tag.add(1);
		tag.add(1);
		tag.add(1);
		tag.add(1);
		tag.add(1);
		tag.add(1);
	}else{
		tag = admin.getTag();
	}
	//List<Integer>tag = admin.getTag();

	AuthService authSvc = new AuthService();
	List<AdminAuthVO> allAuth = authSvc.getAll();
	session.setAttribute("allAuth",allAuth);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>back_header</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/back.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/adminInsert.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/adminList.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back_end/css/adminUpdate.css">

</head>
<body>
	<div class="container-fluid backimg">
		<div class="row">
			<nav class="navbar navbar-inverse" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-ex1-collapse">
						<span class="sr-only">������</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<%=request.getContextPath()%>/back_end/back_page.jsp">Toast��x�޲z</a>
				</div>
				<div class="collapse navbar-collapse navbar-ex1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${sessionScope.admin.adm_name} �z�n</a></li>
						<li><a href="<%=request.getContextPath()%>/back_end/index.jsp">�n�X</a></li>
						<li><a href="#" id="timePanel"></a></li>
					</ul>
				</div>
			</div>
			</nav>


			<div class="col-xs-12 col-sm-2" id="sidenav">

				<table class="table table-bordered account" style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-list-alt"></span> ��x���u�޲z</th>
						</tr>
					</thead>
					
					<tbody style="display:<c:if test="<%= tag.get(0)==0 %>">none</c:if>">
						<tr>
							<td><a href="<%=request.getContextPath()%>/back_end/admin/all_admin-catchTag.jsp">���u��ƺ޲z</a></td>
						</tr>
						<tr>
							<td><a href="<%=request.getContextPath()%>/back_end/admin/insert_admin.jsp">�s�W�b��</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered account" style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-user"></span> �|���޲z</th>
						</tr>
					</thead>
					
					<tbody style="display:<c:if test="<%= tag.get(1)==0 %>">none</c:if>">
						<tr>
							<td><a href="#">�d�ݭӤH����</a></td>
						</tr>
						<tr>
							<td><a href="#">�|���v���޲z</a></td>
						</tr>
						<tr>
							<td><a href="#">�|���x�Ⱥ޲z</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered gift" style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-gift"></span> �ӫ��޲z</th>
						</tr>
					</thead>
					<tbody style="display:<c:if test="<%= tag.get(3)==0 %>">none</c:if>">
						<tr>
							<td><a href="<%=request.getContextPath()%>/back_end/gift/gift_index.jsp">����§��</a></td>
						</tr>
						<tr>
							<td><a href="<%=request.getContextPath()%>/back_end/gift/gift_discount.jsp">�����u�f</a></td>
						</tr>
						<tr>
							<td><a href="#">�����</a></td>
						</tr>
						<tr>
							<td><a href="#">�x�Ȫ��B</a></td>
						</tr>
					</tbody>
				</table>



				<table class="table table-bordered account" style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-heart"></span> ���ʺ޲z</th>
						</tr>
					</thead>
					<tbody style="display:<c:if test="<%= tag.get(4)==0 %>">none</c:if>">
						<tr>
							<td><a href="#">���ʤ��e�޲z</a></td>
						</tr>
					</tbody>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-ban-circle"></span>
								���|�޲z</th>
						</tr>
					</thead>
					<tbody style="display:<c:if test="<%= tag.get(5)==0 %>">none</c:if>">
						<tr>
							<td><a href="#">�b�����|�޲z�b��</a></td>
						</tr>
						<tr>
							<td><a href="#">��O���|�޲z</a></td>
						</tr>
						<tr>
							<td><a href="#">�d�����|</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-duplicate"></span> �����޲z</th>
						</tr>
					</thead>
					<tbody style="display:<c:if test="<%= tag.get(2)==0 %>">none</c:if>">
						<tr>
							<td><a href="#">�̷s���i�޲z</a></td>
						</tr>
						<tr>
							<td><a href="#">�`�����D�޲z</a></td>
						</tr>
					</tbody>
				</table>

			</div>

			<script src="https://code.jquery.com/jquery.js"></script>
			<script
				src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
			
</body>
</html>