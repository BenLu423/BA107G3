<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
<jsp:useBean id="friendsListSvc" scope="page" class="com.friends_list.model.FriendsListService"/>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>禮物商城</title>
	<jsp:include page="/front_end/header.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
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
            <form method="POST" action="<%=request.getContextPath()%>/gift/giftOrder.do">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-offset-2">
					<div style="font-size:20px;vertical-align:bottom;margin:0px;" id="checkoutMoney">
					購買金額: $<p style="display:inline-block;">${orderMoney}</p>
					</div>
					<div style="font-size:20px;vertical-align:bottom;margin:0px;" id="checkoutMoney">
					折價券: <p style="display:inline-block;">xxxxx</p>
					</div>
					<div>
					訂單備註: <textarea name="gifto_remark" rows="3" cols="30"></textarea>
					</div>
				</div>
				<div class="col-xs-12 col-sm-3 col-sm-offset-3" style="margin-top:76px;">
					<div style="font-size:20px;vertical-align:bottom;margin:0px;color:red;">
					結帳金額: $<p style="display:inline-block;">${orderMoney}</p>
					</div>
					<div>
						<input type="hidden" name="action" value="checkoutOrder">
						<button class="gift-order-submit" id="gift-order-submit">同意條款並送出訂單</button>
					</div>
				</div>
	 			<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
 				</div>
			</div>  
			</form>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<table class="table table-hover orderTable" style="z-index:20;position:relative;">
				    <caption></caption>
				        <thead>
				            <tr>
				                <th class="order-name">禮物名稱</th>
				                <th class="order-pic">禮物圖片</th>
				                <th class="order-amount">數量</th>
				                <th class="order-unit">單價</th>
				                <th class="order-money">小計</th>
				                <th class="order-inventory">可買量</th>
				                <th class="order-person">收禮會員 | 數量 | 留言</th>
				                <th class="order-delete">可用剩餘數量</td>
				            </tr>
				        </thead>
				        <tbody>
				        <c:forEach var="detail" items="${orderDetail}">
     						<c:set var="gift" value="${giftSvc.getOneGift(detail.key.gift_no)}" scope="page"/>
				            <tr style="font-size:50px;">
				                <td class="order-name">
				                	<p class="order-disCount">${(detail.key.giftd_no!=null) ? '限時優惠商品' : ''}</p>
				                	<p style="font-size:25px;">${gift.gift_name}</p>
				                </td>
				                <td class="order-pic">
				                	<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.gift_no}">
				                </td>
				                <td class="order-amount">${detail.key.giftod_amount}</td>
				                <td class="order-unit">${detail.key.giftod_unit}</td>
				                <td class="order-money">${detail.key.giftod_money}</td>
				                <td class="order-inventory-enough" style="font-size:25px;">足夠</td>
    							<td class="order-person" style="font-size:15px;">
        							<div class="sortgroup3" style="min-height:117px;border:0px solid black;text-align:left;">
        								<c:forEach var="gift_receive" items="${detail.value}">
        								<div>
											<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${gift_receive.mem_no_other}" style="height:50px;width:50px;">
											<p style="line-height:2.5;display:inline-block;border-left:2px solid rgba(255,194,194,1);border-right:2px solid rgba(255,194,194,1);padding:5px;margin-bottom:0px;">${gift_receive.giftr_amount}個</p>
											<p style="display:inline-block;padding:5px;width:210px;margin-bottom:0px;">${gift_receive.giftr_message}</p>
										</div>
										<hr style="width: 100%; height: 2px; background-color: black; margin: 5px 0px 5px 0px;">
        								</c:forEach>
        							</div>
    							</td>
				                <td class="order-delete">${detail.key.giftod_inventory}</td>
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
<script type="text/javascript">
$('.gift-order-submit').click(function(){
	$(this).submit;
});
</script>
</html>