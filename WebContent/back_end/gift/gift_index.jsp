<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.gift.model.*" %>
<%@ page import="com.giftLabel.model.*"%>
<%@ page import="java.util.*" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>後端禮物管理</title>
    </head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>
<% //因有使用到jquery-1.11.1，故須放在header.jsp之下   %>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 

<div class="col-xs-12 col-sm-10 cont gift-cont">
	<div class="row">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="col-xs-12 col-sm-12">
		<div class="panel panel-default">
    		<div class="panel-body">
    			<div class="col-xs-12 col-sm-8 gift-search">
    				<%-- Exception資訊 --%>
    				${errorMsgs.Exception}
					<form method="post" action="<%=request.getContextPath()%>/gift/gift.do" class="form-inline">
						<div class="form-group">
							<select class="form-control" id="selectWord">
  								<option value="keyWord">關鍵字</option>
  								<option value="gift_name">禮物名稱</option>
  								<option>禮物價格</option>
							</select>
						</div>
						<div class="form-group">
						<input type="text" name ="keyWord" class="form-control" placeholder="Search for..." id="searchContext">
						</div>
						<div class="form-group">
						<button class="btn btn-default form-control" type="submit">Go!</button>
						<input type="hidden" name="action" value="searchGifts">
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						</div>
					</form>
    			</div>
    			
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">
<!-- 		            <div class="btn-group pull-right"> -->
<!-- 		                <button type="button" class="btn btn-primary btn-filter" data-target="never">尚未上架</button> -->
<!-- 		                <button type="button" class="btn btn-success btn-filter" data-target="added">上架中</button> -->
<!-- 		                <button type="button" class="btn btn-danger btn-filter" data-target="off" default>已下架</button> -->
<!-- 		                <button type="button" class="btn btn-default btn-filter" data-target="all">所有禮物</button> -->
<!-- 		            </div> -->
		        </div>
				<div class="table-container giftAll gift-metadata">
        			<table>
        			<tbody>
        			<tr>
                		<td class="gift-edit"><div><p>禮物</p></div></td>
                		<td class="gift-pic"><div>圖片</div></td>	
                		<td class="gift-no"><div>名稱</div></td>	
                		<td class="gift-label"><div>標籤</div></td>
                		<td class="gift-contents"><div>敘述</div></td>	
                		<td class="gift-price"><div>價格</div></td>
                		<td class="gift-status"><div>狀態</div></td>
                		<td class="gift-track"><div>購買次數</div></td>
                		<td class="gift-buy"><div>追蹤人數</div></td>
                		<td class="gift-discount"><% //<div>是否有</div><div>限時優惠</div> %></td>	
                	</tr>	
                	</tbody>
        			</table>
        		</div>
        		<c:if test="${gifts==null}">
        			<jsp:forward page="/gift/gift.do">
        				<jsp:param name="action" value="searchGifts" />
        				<jsp:param name="requestURL" value="<%=request.getServletPath()%>" />
        			</jsp:forward>
				</c:if>
				<c:if test="${giftEdits != null}">
				<jsp:include page="gift_listEdit.jsp"></jsp:include>
				</c:if>
				<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
				<jsp:include page="gift_list.jsp"></jsp:include>
    		</div>
		</div>	
		</div>							
	</div>
	<div class="col-xs-12 col-sm-1">
		<a href="<%=request.getContextPath()%>/gift/gift.do?action=gift_add">
			<img src="<%=request.getContextPath()%>/back_end/res/img/gift/addPage.ico" alt="Add" id="addPage">
		</a>
	</div>
	</div>
</div>	
	
</body>
</html>