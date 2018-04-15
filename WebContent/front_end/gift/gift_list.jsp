<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var = "now" value = "<%= new java.util.Date()%>" />
<jsp:useBean id="giftSvc" class="com.gift.model.GiftService" scope="page"></jsp:useBean>
<jsp:useBean id="giftDiscountSvc" class="com.giftDiscount.model.GiftDiscountService" scope="page"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift_list.js"></script> --%>
<title>禮物商城</title>
</head>
<body>
<div class="col-xs-12 col-sm-12 gift-div">
	<div class="row">
	<%@ include file="giftPage1.file" %> 
	<c:forEach var="gift" items="${(gifts!=null) ? gifts : (giftSvc.canBuy)}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">
<%--       	<c:if test="${(status.count)%5 == 1}"> --%>
<!--        	<div class="col-xs-12 col-sm-1 gift-side2"></div> -->
<%--       	</c:if> --%>
		<c:set var="discount" value="${giftDiscountSvc.getCurrentValidGift(gift.key.gift_no)}"/>
		<div class="col-xs-12 col-sm-2 gift-side">
			<div class="gift-item">
				<div class="item-img">
					<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.key.gift_no}">
						<div class="gift-discount">
							<c:if test="${discount!=null}">
								${discount.giftd_percent*10}折
							</c:if>
						</div>
					<div class="gift-labels">
						<c:forEach var="label" items="${gift.value}">
							<p>${label.giftl_name}</p>
						</c:forEach>
					</div>               
				</div>
				<div>
					<h3>${gift.key.gift_name}</h3>
        			<input type="hidden" name="gift_no" value="${gift.key.gift_no}">
					<p class="JQellipsis">${gift.key.gift_cnt}</p>
					
					<p>Price : 
						<span>$<fmt:formatNumber type="number" value="${(discount!=null) ? gift.key.gift_price*discount.giftd_percent : gift.key.gift_price}" maxFractionDigits="0"/></span>　
						<c:if test="${discount!=null}">
							<span><s>$${gift.key.gift_price}</s></span>
						</c:if>
					</p>
<!-- 					<p>Price :  -->
<!-- 						<span>$${gift.key.gift_price}</span> -->
<%-- 						<span><s><c:if test="${not empty giftD.gift_price}">$${giftD.gift_price}</c:if></s></span> --%>
<!-- 					</p> -->
					<c:choose>
						<c:when test="${discount!=null and discount.giftd_start < now and now < discount.giftd_end}">
							<div style="color:red;text-align:center;font-size:20px;min-height:30px" data-countdown="${discount.giftd_end}"></div>
						</c:when>
						<c:otherwise>
							<div style="color:red;text-align:center;font-size:20px;min-height:30px"></div>
						</c:otherwise>
					</c:choose>
					<button type="button" style="margin-right: 0px;">FOLLOW</button>
					<button type="button" class="addToCart" style="margin: 0px;">ADD TO CART</button>
					<select name="giftod_amount" style="height: 30px;width: 45px;">
						<c:forEach var="count" begin="1" end="${(discount!=null) ? giftSvc.getAmount(gift.key.gift_no) : '10'}" step="1">
							<option value="${count}">${count}</option>
						</c:forEach>
					</select>  
					<c:if test="${(discount!=null)}">
						<input type="hidden" name="giftd_no" value="${discount.giftd_no}">
					</c:if>
					<input type="hidden" name="action" value="addOrder">
				</div>
        	</div>
		</div>
<%-- 		<c:if test="${(status.count)%5 == 0}"> --%>
<!--        	<div class="col-xs-12 col-sm-1 gift-side2"></div> -->
<%--       	</c:if> --%>
    </c:forEach>
    <div class="col-xs-12 col-sm-12">
	<%@ include file="giftPage2.file" %>
	</div> 
	</div>
</div> 		
</body>
</html>