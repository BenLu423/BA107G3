<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="giftLabelSvc" scope="page" class="com.giftLabel.model.GiftLabelService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>後端禮物管理</title>
</head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>
<% //因有使用到jquery-1.11.1，故須放在header.jsp之下   %>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_listEdit.css">  



<div class="col-xs-12 col-sm-10 cont gift-cont">
	<div class="row">
    <%-- Exception資訊 --%>
    ${errorMsgs.Exception}
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 gift-add">	
	<div class="col-xs-12 col-sm-10 gift-add-card">
		<div class="modal-dialog modal-lg">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/gift.do">
			<div class="modal-content">

				<div class="modal-body gift-add-body">
            	<!-- content goes here -->
            		<button type="button" class="close gift-add-close" data-dismiss="modal">
						<p>×</p>
					</button>
					<div class="form-group">
					<div class="errMsg">${errorMsgs["gift_name"]}</div>
<%-- 						<div class="errMsg">${addGift}</div> --%>
                		<label for="gift_name">禮物名稱</label>
                		<input type="text" name="gift_name" id="gift_name" 
                				value="${param.gift_name}" maxlength="20" class="form-control
                				 gift-add-name" ${(not empty errorMsgs["gift_name"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
              		</div>
					<div class="form-group">
					<div class="errMsg">${errorMsgs["gift_labels"]}</div>
                		<label for="gift_labels">禮物標籤</label>
						<select name="gift_labels" class="selectpicker" data-live-search="true" data-width="100%" multiple>
							<c:forEach var="label" items="${giftLabelSvc.all}" varStatus="status">
								<option value="${label.giftl_no}" ${(fn:contains(labelList, label)) ? 'selected' : ''}>${label.giftl_name}</option>
							</c:forEach>
						</select>               		</div>              		
              		<div class="form-group">
              		<div class="errMsg">${errorMsgs["gift_price"]}</div>
                		<label for="gift_price">禮物價格</label>
                		<input type="Number" name="gift_price" 
                			value="${param.gift_price}" id="gift_price" min="0" class="form-control gift-add-price"
                			 ${(not empty errorMsgs["gift_price"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
              		</div>
              		<div class="form-group">
              		<div class="errMsg">${errorMsgs["gift_cnt"]}</div>
                		<label for="gift_cnt">禮物內容</label>
                		<div><textarea name="gift_cnt" id="gift_cnt" 
                		class="form-control gift-add-cnt" rows="4" cols="50" maxlength="200"
                		${(not empty errorMsgs["gift_price"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>${param.gift_cnt}</textarea></div>
              		</div>          		
              		<div class="form-group" style="height:268px">
	              		<div class="errMsg">${errorMsgs["gift_pic"]}</div>
                		<input type="file" name="gift_pic" class="gift-add-pic">
                		<img src="${noAddPic}" alt="" style="width:268px; height:268px">
              		</div>
            		

				</div>
				<div class="modal-footer">
					<div class="btn-group btn-group-justified" role="group" aria-label="group button">
						<div class="btn-group btn-delete">
							<button type="reset" class="btn btn-default gift-add-reset">Reset</button>
						</div>
						<div class="btn-group">
							<input type="hidden" name="index" value="${sts.index}">
							<input type="hidden" name="action" value="addGift">
							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							<button type="submit" class="btn btn-default gift-add-submit">Save</button>
						</div>
					</div>
				</div>
				<div>
					<button type="button" id="magicButton" class="btn btn-block" style="width:auto;">magic</button>
				</div>
			</div>
			</form>
		</div>
	</div>
  
	</div>
	<div class="col-xs-12 col-sm-1">
		<a href="<%=request.getContextPath()%>/gift/gift.do?action=gift_show">
			<img src="<%=request.getContextPath()%>/back_end/res/img/gift/backPage.ico" alt="Add" id="backPage">
		</a>
<!-- 		<button id="giftAdd"> -->
<%-- 		<img src="<%=request.getContextPath()%>/back_end/res/img/gift/addGift.ico" alt="Add" id="addGift"> --%>
<!-- 		</button> -->
	</div>	
	</div>
</div>
</body>
<script type="text/javascript">
$(document).ready( function() {
	$('#magicButton').click(function(){
		var add = $('.gift-add-body').find('input');
		add[0].value = '小吳獨家限量照';
		add[2].value = '6666';
		$('#gift_cnt')[0].innerText = 'JAVA啟蒙先師，無私奉獻的陪我們燃燒生命，但最愛的結衣不拍續集了';
	});
});
</script>
</html>