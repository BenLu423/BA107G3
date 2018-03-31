<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<c:set var="statusList" value="尚未上架,上架中,已下架"/>
<jsp:useBean id="giftLabelSvc" scope="page" class="com.giftLabel.model.GiftLabelService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>gift_listEdit.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_listEdit.css">  
</head>
<body>
	<div class="table-container giftAll">
		<table class="table table-filter gift-management">
			<tbody>
			<c:forEach var="gift" items="${giftEdits}">
				<c:set var="gift_no" value="${gift.key.gift_no}"></c:set>
				<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/gift.do">
					<c:choose>
						<c:when test='${gift.key.gift_is_on == "尚未上架"}'>
							<tr data-status="never">
						</c:when>
						<c:when test='${gift.key.gift_is_on == "上架中"}'>
							<tr data-status="added">
						</c:when>
						<c:when test='${gift.key.gift_is_on == "已下架"}'>
							<tr data-status="off">
						</c:when>
					</c:choose>
					<td class="gift-edit">
						<div>
							<button type="submit" class='btn btn-warning btn-xs'>
								<span class="glyphicon glyphicon-upload"></span> Update
							</button>
							<input type="hidden" name="action" value="editGift">
							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
							
							<a href="<%=request.getContextPath()%>/gift/gift.do?action=searchGifts&del_gift_no=${gift.key.gift_no}&requestURL=<%=request.getServletPath()%>&whichPage=<%=request.getParameter("whichPage")%>">
							<button type="button" class='btn btn-default btn-xs gift-cancel'>
								<span class="glyphicon glyphicon-remove"></span> Cancel
							</button>
							</a>
						</div>
					</td>

					<td class="gift-pic" style="padding:0px 0px 8px 0px;">
						<div class="errMsg">${giftEditMsgs[gift_no]["gift_pic"]}</div>
						<div class="form-group" style="height:120px">
                		<img src="${pageContext.request.contextPath}/DBGifReader4?table=GIFT&gift_no=${gift.key.gift_no}">
                		<input type="file" name="gift_pic" class="gift-edit-pic" style="margin:0px;">
              			</div>
					</td>
					<td class="gift-no">
							<div class="errMsg">${giftEditMsgs[gift_no]["gift_name"]}</div>
							<p>${gift.key.gift_no}</p>
							<input type="hidden" name="gift_no" value="${gift.key.gift_no}">
							<input type="text" name="gift_name" value="${gift.key.gift_name}" ${(not empty giftEditMsgs[gift_no]["gift_name"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
					</td>
					<td class="gift-label">
						<div class="errMsg">${giftEditMsgs[gift_no]["gift_labels"]}</div>
						<select name="gift_labels" class="selectpicker" data-live-search="true" data-width="100%" multiple>
							<c:forEach var="label" items="${giftLabelSvc.all}">
								<option value="${label.giftl_no}" ${(fn:contains(gift.value, label)) ? 'selected' : ''}>${label.giftl_name}</option>
							</c:forEach>
						</select> 
					</td>
					<td class="gift-contents">
						<div class="errMsg">${giftEditMsgs[gift_no]["gift_cnt"]}</div>
						<textarea name="gift_cnt" class="form-control gift-add-cnt" 
						rows="4" cols="50" maxlength="200" ${(not empty giftEditMsgs[gift_no]["gift_cnt"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>${gift.key.gift_cnt}</textarea>
					</td>
					<td class="gift-price">
						<div class="errMsg">${giftEditMsgs[gift_no]["gift_price"]}</div>
						<input type="Number" name="gift_price" min="0" 
						value="${gift.key.gift_price}" ${(not empty giftEditMsgs[gift_no]["gift_price"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
					</td>
					<td class="gift-status">
						<div class="errMsg">${giftEditMsgs[gift_no]["gift_is_on"]}</div>
						<select class="form-control" name="gift_is_on" multiple="3" ${(not empty giftEditMsgs[gift_no]["gift_is_on"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
							<c:forTokens var="status" items="${statusList}" delims=",">
								<option value="${status}" ${(status == gift.key.gift_is_on) ? 'selected' : ''}>${status}</option> --%>
							</c:forTokens>
						</select>
					</td>
					<td class="gift-track">
						<div>
							<p>${gift.key.gift_track_qty}次</p>
							<input type="hidden" name="gift_track_qty" value="${gift.key.gift_track_qty}">
						</div>
					</td>
					<td class="gift-buy">
						<div>
							<p>${gift.key.gift_buy_qty}人</p>
							<input type="hidden" name="gift_buy_qty" value="${gift.key.gift_buy_qty}">
						</div>
					</td>
					<td class="gift-discount">
						<% /* <div>
                        		<p>YES</p>
                        	</div> */%>
					</td>
					</tr>
				</form>	
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>