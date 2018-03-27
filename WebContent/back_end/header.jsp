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
						<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">Toast後台管理</a>
				</div>
				<div class="collapse navbar-collapse navbar-ex1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">${sessionScope.admin.adm_name} 您好</a></li>
						<li><a href="back_login.jsp">登出</a></li>
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
								後台員工管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">員工資料管理</a></td>
						</tr>
						<tr>
							<td><a href="#">新增帳號</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-user"></span> 會員管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">查看個人頁面</a></td>
						</tr>
						<tr>
							<td><a href="#">會員權限管理</a></td>
						</tr>
						<tr>
							<td><a href="#">會員儲值管理</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered gift" style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-gift"></span> 禮物管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="backGiftAll.html">全部禮物</a></td>
						</tr>
						<tr>
							<td><a href="#">限時優惠</a></td>
						</tr>
						<tr>
							<td><a href="#">折價券</a></td>
						</tr>
						<tr>
							<td><a href="#">儲值金額</a></td>
						</tr>
					</tbody>
				</table>



				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="
glyphicon glyphicon-heart"></span> 活動管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">活動內容管理</a></td>
						</tr>
					</tbody>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-ban-circle"></span>
								檢舉管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">帳號檢舉管理帳號</a></td>
						</tr>
						<tr>
							<td><a href="#">日記檢舉管理</a></td>
						</tr>
						<tr>
							<td><a href="#">留言檢舉</a></td>
						</tr>
					</tbody>
				</table>

				<table class="table table-bordered account"
					style="margin-top: 10px;">
					<thead class="index-thead">
						<tr>
							<th><span class="glyphicon glyphicon-duplicate"></span> 頁面管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><a href="#">最新公告管理</a></td>
						</tr>
						<tr>
							<td><a href="#">常見問題管理</a></td>
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
							+ "年"
							+ (today.getMonth() + 1)
							+ "月"
							+ today.getDate()
							+ "日  "
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