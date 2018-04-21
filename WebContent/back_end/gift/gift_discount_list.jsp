<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="giftDiscount" class="com.giftDiscount.model.GiftDiscountService" scope="page"/>
<c:set var = "now" value = "<%= new java.util.Date()%>" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>後端禮物管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/jquery.countdown.css"> 
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/countdown-2.2.0/jquery.countdown.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/countdown-2.2.0/jquery.plugin.js"></script> 
</head>
<body>
	<div class="table-container giftAll">
		<%@ include file="giftPage1_disCount.file" %>
		<table class="table table-filter gift-management">
			<tbody>
				
				<c:forEach var="gift" items="${(giftDiscounts!=null) ? giftDiscounts : (giftDiscount.giftDiscountTotal)}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td class="giftd-edit">
						<div>
							<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/giftDiscount.do">
								<button class='btn btn-info btn-xs'>
									<span class="glyphicon glyphicon-edit"></span> Edit
								</button>
								<input type="hidden" name="action" value=searchGiftDiscounts>
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">
								<input type="hidden" name="edit_giftd_no" value="${gift.key.giftd_no}">
							</form>
						</div>
					</td>
					<td class="giftd-no">
						<div>
<%-- 							<p>${gift.key.giftd_no}</p> --%>
							<p>${gift.value.gift_is_on}</p>
						</div>
					</td>
					<td class="giftd-pic-1">
						<p>${gift.value.gift_no}</p>
						<p>${gift.value.gift_name}</p>
					</td>
					<td class="giftd-pic-2">
						<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.value.gift_no}">
					</td>
					<td class="giftd-percent">
						<div>
							<p>${gift.key.giftd_percent}</p>
						</div>
					</td>
					<td class="giftd-start">
						<div>
							<p><fmt:formatDate value="${gift.key.giftd_start}" pattern="yyyy-MM-dd"/></p>
							<p><fmt:formatDate value="${gift.key.giftd_start}" pattern="HH:mm:ss"/></p>
						</div>
					</td>
					<td class="giftd-end">
						<div>
							<p><fmt:formatDate value="${gift.key.giftd_end}" pattern="yyyy-MM-dd"/></p>
							<p><fmt:formatDate value="${gift.key.giftd_end}" pattern="HH:mm:ss"/></p>
						</div>
					</td>
					<td class="giftd-amount">
						<div>
							<p>${gift.key.giftd_amount}</p>
						</div>
					</td>
					<td class="giftd-time" style="text-align:center;">
						<c:choose>
							<c:when test='${gift.key.giftd_start > now}'>
								<div style="color:green;">尚未開始</div>
							</c:when>
							<c:when test='${gift.key.giftd_end < now}'>
								<div style="color:#BBB;">已結束</div>
							</c:when>
							<c:otherwise>
								<div style="color:red;" data-countdown="${gift.key.giftd_end}"></div>
							</c:otherwise>
						</c:choose>
					</td>					
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="giftPage2_disCount.file" %> 

		<p style="text-align:right;">頁數:<%=whichPage%> / <%=pageNumber%></p>	
		
	</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('[data-countdown]').each(function() {
		  var $this = $(this), finalDate = $(this).data('countdown');
		  $this.countdown(finalDate, function(event) {
		    $this.html(event.strftime('%D days %H:%M:%S'));
		  }).on('finish.countdown', function() {
			  $(this).css('color','#BBB');
			  $(this)[0].innerText = "已結束";
		  });
	});
});
</script>
</html>