<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="giftDiscountSvc" class="com.giftDiscount.model.GiftDiscountService" scope="page"/>
<jsp:useBean id="giftLabelSvc" class="com.giftLabel.model.GiftLabelService"/>
<jsp:useBean id="giftSvc" class="com.gift.model.GiftService" scope="page"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift_list.js"></script> --%>
<title>禮物商城</title>
</head>
<body>
<div class="col-xs-12 col-sm-12">
	<div class="row">
	<c:forEach var="discount" items="${giftDiscountSvc.all}" varStatus="status">
      	<c:if test="${(status.count)%5 == 1}">
       	<div class="col-xs-12 col-sm-1 gift-side"></div>
      	</c:if>
		<div class="col-xs-12 col-sm-2 gift-side">
			<div class="gift-item">
				<div class="item-img">
					<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${discount.gift_no}">
					<div class="gift-discount">
						${discount.giftd_percent*10}折
					</div>
					<div class="gift-labels">
						<c:forEach var="label" items="${giftLabelSvc.getListGiftLabel(discount.gift_no)}">
							<p>${label.giftl_name}</p>
						</c:forEach>
					</div>               
				</div>
				<div>
					<c:set var="giftD" value="${giftSvc.getOneGift(discount.gift_no)}" scope="page"/>
					<h3>${giftD.gift_name}</h3>
        			<input type="hidden" name="gift_no" value="${giftD.gift_no}">
					<p class="JQellipsis">${giftD.gift_cnt}</p>
					
					<p>Price : 
						<span>$<fmt:formatNumber type="number" value="${giftD.gift_price*discount.giftd_percent}" maxFractionDigits="0"/></span>　
						<span><s>$${giftD.gift_price}</s></span>
					</p>
					<button type="button" style="margin-right: 0px;">FOLLOW</button>
					<button type="button" class="addToCart" style="margin: 0px;">ADD TO CART</button>
					<select name="giftod_amount" style="height: 30px;width: 45px;">
						<c:forEach var="count" begin="1" end="${giftSvc.getAmount(giftD.gift_no)}" step="1">
<%-- 						<c:forEach var="count" begin="1" end="${giftSvc.getAmount(giftD.gift_no)}" step="1"> --%>
							<option value="${count}">${count}</option>
						</c:forEach>
					</select>  
					<input type="hidden" name="giftd_no" value="${discount.giftd_no}">
					<input type="hidden" name="action" value="addOrder">
				</div>
        	</div>
		</div>
		<c:if test="${(status.count)%5 == 0}">
       	<div class="col-xs-12 col-sm-1 gift-side"></div>
      	</c:if>
    </c:forEach>
    <div class="col-xs-12 col-sm-12">
	</div> 
	</div>
</div> 		
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('.addToCart').click(function(){
		var addGift = $(this).parent('div').children().serializeArray();
		console.log(JSON.stringify(addGift));
		$.ajax({
			  type: 'POST',
			  url: '/BA107G3/gift/giftOrder.do',
			  data: addGift,
			  dataType: 'text',
			  success: (function() { alert("second success"); }),
			  error:(function() { alert("second error"); })
			}); 
	});

});
</script>
</html>