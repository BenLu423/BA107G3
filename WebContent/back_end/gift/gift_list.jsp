<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="gift" class="com.gift.model.GiftService" scope="page"/>
<c:set var="statusList" value="�|���W�[,�w�U�[,�W�[��"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���§���޲z</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 
</head>
<body>
	<div class="table-container giftAll">
		<%@ include file="giftPage1.file" %>
		<table class="table table-filter gift-management">
			<tbody>
				<c:forEach var="gift" items="${(gifts!=null) ? gifts : (gift.giftAll)}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<%-- 					<c:choose> --%>
<%-- 						<c:when test='${gift.key.gift_is_on == "�|���W�["}'> --%>
<!-- 							<tr data-status="never"> -->
<%-- 						</c:when> --%>
<%-- 						<c:when test='${gift.key.gift_is_on == "�W�[��"}'> --%>
<!-- 							<tr data-status="added"> -->
<%-- 						</c:when> --%>
<%-- 						<c:when test='${gift.key.gift_is_on == "�w�U�["}'> --%>
<!-- 							<tr data-status="off"> -->
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
					<tr>
					<td class="gift-edit">
						<div>
							<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/gift.do">
								<button class='btn btn-info btn-xs'>
									<span class="glyphicon glyphicon-edit"></span> Edit
								</button>
								<input type="hidden" name="action" value="searchGifts">
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">
								<input type="hidden" name="edit_gift_no" value="${gift.key.gift_no}">
							</form>
						</div>
						<div>
							<c:if test='${gift.key.gift_is_on == "�|���W�["}'>
								<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/gift.do">
								<button class='btn btn-danger btn-xs'>
									<span class="glyphicon glyphicon-remove"></span> Delete
								</button>
								<input type="hidden" name="action" value="deleteOneGift">
								<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">
								<input type="hidden" name="gift_no" value="${gift.key.gift_no}">
								</form>
							</c:if>
						</div>
					</td>

					<td class="gift-pic">
						<div>
							<img
								src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.key.gift_no}">
						</div>
					</td>
					<td class="gift-no">
						<div>
							<h2>${gift.key.gift_no}</h2>
							<h3>${gift.key.gift_name}</h3>
						</div>
						<input type="hidden" name="gift_no" value="${gift.key.gift_no}">
					</td>
					<td class="gift-label">
						<div>
							<c:forEach var="label" items="${gift.value}">
								<p>
									<u>${label.giftl_name}</u>
								</p>
							</c:forEach>
						</div>
					</td>
					<td class="gift-contents">
						<div>
							<p>${gift.key.gift_cnt}</p>
						</div>
					</td>
					<td class="gift-price">
						<div>
							<p>$${gift.key.gift_price}</p>
						</div>
					</td>
					<td class="gift-status">
						<div class="never btn-style">
							<c:forTokens var="isOn" items="${statusList}" delims="," varStatus="status">
								<c:choose>
									<c:when test="${isOn == gift.key.gift_is_on}">
										<button type="button" class="btn currentlyStatus">${isOn}</button>
<%-- 										<c:set var=""></c:set> --%>
									</c:when>
									<c:otherwise>
										<c:if test="${isOn!='�|���W�[' or (isOn=='�|���W�[' and status.index>0)}">
										<button type="button" class="btn" style="display: none">${isOn}</button>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forTokens>
						</div>
					</td>
					<td class="gift-track">
						<div>
							<p>${gift.key.gift_track_qty}��</p>
						</div>
					</td>
					<td class="gift-buy">
						<div>
							<p>${gift.key.gift_buy_qty}�H</p>
						</div>
					</td>
					<td class="gift-discount">
						<% /* <div>
                        		<p>YES</p>
                        	</div> */%>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="giftPage2.file" %> 

		<p style="text-align:right;">����:<%=whichPage%> / <%=pageNumber%></p>	
		
	</div>
	�ثe����:${gifts.size()}
	�קﵧ��:${giftEdits.size()}
</body>
<script type="text/javascript">
$( document ).ready(function() {
	//gs : giftSatus
    connectgs();
    
	function connectgs() {
		var gsPoint = "/GiftStatusServer";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var gsendPointURL = "ws://" + window.location.host + webCtx + gsPoint;
		
	    console.log("connect ws:giftStatus");
		// �إ� websocket ����
		gsWebSocket = new WebSocket(gsendPointURL);
		
		gsWebSocket.onopen = function(event) {
			console.log("gsWebSocket ���\�s�u");
		};

		gsWebSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        console.log("onmessage: " + jsonObj);
		};

		gsWebSocket.onclose = function(event) {
			console.log("gsWebSocket �w���u");
		};
	}
    
});
$(window).on('beforeunload',function(){
	console.log("disconnect ws:giftStatus");
	gsWebSocket.close();
});

$('.gift-status button').click(function(){
	var status = $(this)[0].innerText;
	var del = $(this).parents('tr').children('td[class=gift-edit]').children('div')[1];
	var never = $(this).siblings('button')[0];
	
	if(status != "�|���W�["){
		del.innerText = '';
		if(never.innerText == "�|���W�["){
			never.remove();
		}
	}
	var obj = $(this).parents('tr').children('td[class=gift-no]').children().serializeArray();
	obj[obj.length] = {name: "gift_is_on",  value: status};   
	gsWebSocket.send(JSON.stringify(obj));
});
</script>
</html>