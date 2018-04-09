<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>禮物商城</title>
	<jsp:include page="/front_end/header.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift_order.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
            <div style="height:0px;"></div>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<table class="table table-hover orderTable">
				    <caption>吐司網購物中心</caption>
				        <thead>
				            <tr>
				                <th class="order-name">禮物名稱</th>
				                <th class="order-pic">禮物圖片</th>
				                <th class="order-amount">數量</th>
				                <th class="order-unit">單價</th>
				                <th class="order-money">小計</th>
				                <th class="order-inventory">可買量</th>
				                <th class="order-person">收禮人/收禮數量</th>
				            </tr>
				        </thead>
				        <tbody>
				        <c:forEach var="detail" items="${orderDetail}">
     						<c:set var="gift" value="${giftSvc.getOneGift(detail.key.gift_no)}" scope="page"/>
				            <tr>
				                <td class="order-name">
				                	<p class="order-disCount">${(detail.key.giftd_no!=null) ? '限時優惠商品' : ''}</p>
				                	<p>${gift.gift_name}</p>
				                </td>
				                <td class="order-pic">
				                	<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.gift_no}">
				                </td>
				                <td class="order-amount">
				                	<select name="giftod_amount" style="height: 30px;width: 45px;">
										<c:forEach var="count" begin="1" end="${giftSvc.getAmount(gift.gift_no)}" step="1">
											<option value="${count}" ${(detail.key.giftod_amount==count) ? 'selected' : ''}>${count}</option>
										</c:forEach>
									</select>  	
				                </td>
				                <td class="order-unit">${detail.key.giftod_unit}</td>
				                <td class="order-money">${detail.key.giftod_money}</td>
				                <td class="order-inventory"></td>
				                <td class="order-person">資料</td>
				            </tr>
				        </c:forEach>
				        </tbody>
				</table>                   
			</div>   
	    </div> 
		<div class="col-xs-12 col-sm-1"></div>
	</div>    
</div>	      
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>