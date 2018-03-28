<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
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
					<a class="navbar-brand" href="index.jsp">Toast��x�޲z</a>
				</div>
				<div class="collapse navbar-collapse navbar-ex1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${sessionScope.admin.adm_name} �z�n</a></li>
						<li><a href="back_login.jsp">�n�X</a></li>
						<li><a href="#" id="timePanel"></a></li>
					</ul>
				</div>
			</div>
			</nav>


			<div class="col-xs-12 col-sm-2" id="sidenav">

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-list-alt"></span>
								��x���u�޲z</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">���u��ƺ޲z</a></td>
						</tr>
						<tr>
							<td><a href="#">�s�W�b��</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-user"></span> �|���޲z</th>
						</tr>
					</thead>
					<tbody>
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
							<th><span class="glyphicon glyphicon-gift"></span> §���޲z</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="backGiftAll.html">����§��</a></td>
						</tr>
						<tr>
							<td><a href="#">�����u�f</a></td>
						</tr>
						<tr>
							<td><a href="#">�����</a></td>
						</tr>
						<tr>
							<td><a href="#">�x�Ȫ��B</a></td>
						</tr>
					</tbody>
				</table>



				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="
glyphicon glyphicon-heart"></span> ���ʺ޲z</th>
						</tr>
					</thead>
					<tbody>
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
					<tbody>
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
					<tbody>
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
			<script>
				var i = 0;
				function showTime() {
					var today = new Date();
					document.getElementById("timePanel").innerHTML = today
							.getFullYear()
							+ "�~"
							+ (today.getMonth() + 1)
							+ "��"
							+ today.getDate()
							+ "��  "
							+ today.getHours()
							+ " : "
							+ today.getMinutes()
							+ " : "
							+ today.getSeconds();
					i++;
// 					console.log(i);
				}

				function init() {
					showTime();
					setInterval(showTime, 1000);
				}

				window.onload = init;
			</script>
</body>
</html>