<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/front_end/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-1"></div>
			<div class="col-xs-12 col-sm-10 content">
				<div class="content-top"></div>
				<div class="input-group searchBar" id="adv-search">
					<input type="text" class="form-control"
						placeholder="Search for snippets" />
					<div class="input-group-btn">
						<div class="btn-group" role="group">
							<div class="dropdown dropdown-lg">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-expanded="false">
									<span class="caret"></span>
								</button>
								<div class="dropdown-menu dropdown-menu-right" role="menu">
									<form class="form-horizontal" role="form">
										<div class="form-group">
											<label for="filter">Filter by</label> <select
												class="form-control">
												<option value="0" selected>All Snippets</option>
												<option value="1">Featured</option>
												<option value="2">Most popular</option>
												<option value="3">Top rated</option>
												<option value="4">Most commented</option>
											</select>
										</div>
										<div class="form-group">
											<label for="contain">Author</label> <input
												class="form-control" type="text" />
										</div>
										<div class="form-group">
											<label for="contain">Contains the words</label> <input
												class="form-control" type="text" />
										</div>
										<button type="submit" class="btn btn-primary">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										</button>
									</form>
								</div>
							</div>
							<button type="button" class="btn btn-primary">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</div>
					</div>
				</div>

				<div class="col-xs-12 col-sm-10 col-sm-offset-1">
					<div role="tabpanel">
						<div align="right">
							<button class="btn-basic btn-lg">建立聊天室</button>
						</div>
						<!-- 標籤面板：標籤區 -->
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#tab1"
								aria-controls="tab1" role="tab" data-toggle="tab">全部</a></li>
							<li role="presentation"><a href="#tab2" aria-controls="tab2"
								role="tab" data-toggle="tab">地區</a></li>
							<li role="presentation"><a href="#tab3" aria-controls="tab3"
								role="tab" data-toggle="tab">體育</a></li>
							<li role="presentation"><a href="#tab4" aria-controls="tab4"
								role="tab" data-toggle="tab">休閒</a></li>

						</ul>

						<!-- 標籤面板：內容區 -->

						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab1">
								<div align="right" id="total"></div>
								<table class="table table-striped chat-table" id="allType">




								</table>
								
								<div align="center">
									<ul class="pagination">
										<li><a href="#">&laquo;</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">&raquo;</a></li>
									</ul>
								</div>
							</div>
							
							
							
							
							
							<div role="tabpanel" class="tab-pane" id="tab2">
								<div align="right" id="area-all"></div>
								<table class="table table-striped chat-table" id="area">
								<tbody>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：地區</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：地區</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：地區</td>
									</tr>
									</tbody>
								</table>
								<div align="center">
									<ul class="pagination">
										<li><a href="#">&laquo;</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">&raquo;</a></li>
									</ul>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="tab3">
								<div align="right" id="sports-all"></div>
								<table class="table table-striped chat-table" id="sports">
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：體育</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：體育</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：體育</td>
									</tr>
								</table>
								<div align="center">
									<ul class="pagination">
										<li><a href="#">&laquo;</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">&raquo;</a></li>
									</ul>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane" id="tab4">
								<div align="right" id="casual-all"></div>
								<table class="table table-striped chat-table" id="casual">
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：休閒</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：休閒</td>
									</tr>
									<tr>
										<td width="5px">●</td>
										<td><a href="">XXX聊天室</a></td>
										<td>室長：XXX</td>
										<td>人數：XX人</td>
										<td>分類：休閒</td>
									</tr>
								</table>
								<div align="center">
									<ul class="pagination">
										<li><a href="#">&laquo;</a></li>
										<li class="active"><a href="#">1</a></li>
										<li><a href="#">&raquo;</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
			<div class="col-xs-12 col-sm-1"></div>
		</div>
	</div>

	<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>