<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:useBean id="gift" class="com.gift.model.GiftService" scope="page"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift_list.js"></script> --%>
<title>禮物商城</title>
</head>
<body>
<div class="col-xs-12 col-sm-12">
	<div class="row">
	<%@ include file="giftPage1.file" %> 
	<c:forEach var="gift" items="${(gifts!=null) ? gifts : (gift.canBuy)}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="status">
      	<c:if test="${(status.count)%5 == 1}">
       	<div class="col-xs-12 col-sm-1 gift-side"></div>
      	</c:if>
		<div class="col-xs-12 col-sm-2 gift-side">
			<div class="gift-item">
				<div class="item-img">
					<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.key.gift_no}">
					<div class="gift-labels">
						<div class="gift-discount">
							${discount.giftd_percent}
						</div>
						<c:forEach var="label" items="${gift.value}">
							<p>${label.giftl_name}</p>
						</c:forEach>
					</div>               
				</div>
				<div>
					<h3>${gift.key.gift_name}</h3>
        			<input type="hidden" name="gift_no" value="${gift.key.gift_no}">
					<p class="JQellipsis">${gift.key.gift_cnt}</p>
					<p>Price : <span>$${gift.key.gift_price}</span></p>
					<button type="button" style="margin-right: 0px;">FOLLOW</button>
					<button type="button" class="addToCart" style="margin: 0px;">ADD TO CART</button>
					<select name="giftod_amount" style="height: 30px;width: 45px;">
						<c:forEach var="count" begin="1" end="10" step="1">
							<option value="${count}">${count}</option>
						</c:forEach>
					</select>  
					<input type="hidden" name="action" value="addOrder">
				</div>
        	</div>
		</div>
		<c:if test="${(status.count)%5 == 0}">
       	<div class="col-xs-12 col-sm-1 gift-side"></div>
      	</c:if>
    </c:forEach>
    <div class="col-xs-12 col-sm-12">
	<%@ include file="giftPage2.file" %>
	</div> 
	</div>
</div> 		
</body>
</html>