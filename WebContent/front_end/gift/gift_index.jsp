<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>禮物商城</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift.js"></script>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
			<!-- 搜尋區 -->
			<div class="input-group searchBar" id="adv-search">
			<input type="text" class="form-control" placeholder="Search for Gift" />
			<div class="input-group-btn">
			    <div class="btn-group" role="group">
			        <div class="dropdown dropdown-lg">
			            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret"></span></button>
			            <div class="dropdown-menu dropdown-menu-right" role="menu">
			                <form class="form-horizontal" role="form">
			                  <div class="form-group">
			                    <label for="filter">Filter by</label>
			                    <select class="form-control">
			                        <option value="0" selected>All Snippets</option>
			                        <option value="1">Featured</option>
			                        <option value="2">Most popular</option>
			                        <option value="3">Top rated</option>
			                        <option value="4">Most commented</option>
			                    </select>
			                  </div>
			                  <div class="form-group">
			                    <label for="contain">Author</label>
			                    <input class="form-control" type="text" />
			                  </div>
			                  <div class="form-group">
			                    <label for="contain">Contains the words</label>
			                    <input class="form-control" type="text" />
			                  </div>
			                  <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			                </form>
			            </div>
			        </div>
			        <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			        </div>
			</div>
			</div>   
			<!-- //搜尋區 -->          

			<div class="col-xs-12 col-sm-12">
		        <div role="tabpanel">
		        <!-- 標籤面板：標籤區 -->
			        <ul class="nav nav-tabs" role="tablist">
			            <li role="presentation" class="active">
			                <a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">限時優惠</a>
			            </li>
			            <li role="presentation">
			                <a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">最新禮物</a>
			            </li>
			            <li role="presentation">
			                <a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab">全部禮物</a>
			            </li>
			        </ul>
			                    
					<!-- 標籤面板：內容區 -->
					<div class="tab-content gift-content">
				    	<div role="tabpanel" class="tab-pane active" id="tab1">
					        <!-- 第一層 -->
							<jsp:include page="/front_end/gift/gift_list.jsp"></jsp:include>
				    	</div>
					    <div role="tabpanel" class="tab-pane" id="tab2">
					        <!-- 頭條標籤的內容 -->
					    </div>
					    <div role="tabpanel" class="tab-pane" id="tab3">
					        <!-- 最新標籤的內容 -->
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