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
 			<div class="col-xs-12 col-sm-1">
 			<div style="padding-top:8px;padding-bottom:8px;">
 				好友名單
 			</div>
	        <div id="ul_Source3" class="sortgroup3">
				<c:forEach var="friend" items="${friendsListSvc.getMemberFriends(memSelf.mem_no)}">
					<c:set var="mem" value="${memberSvc.getOneMem(friend.mem_no_other)}"/>
					<div class="sourceFriend">
						<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${mem.mem_no}" style="height:50px;width:50px;">
						<input type="NUMBER" class="gifr_amount" name="giftr_amount" style="width:50px;text-align:center;">
						 <textarea class="giftr_message" name="giftr_message" rows="4" cols="20"></textarea>
					<input type="hidden" name="mem_no_other" value="${friend.mem_no_other}">
					</div>
					<div style="height:50px;width:200px;background-color:white;position:absolute;display:inline-block;margin-left:60px"></div>
				</c:forEach>	          
				<div style="height:50px;width:50px;background-color:white;position:absolute;"></div>
	        </div>
			</div>
            
			<div class="col-xs-12 col-sm-10">
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
				                <th class="order-person" style="text-align:left;">收禮人/收禮數量</th>
				                <th class="order-delete">刪除</td>
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
				                <td class="order-inventory-enough">足夠</td>
    							<td class="order-person">
        							<div class="sortgroup3" style="min-height:117px;border:0px solid black;text-align:center;"></div>
    							</td>
				                <td class="order-delete">
									<button class='btn btn-danger btn-xs deleteOrder'>
										<span class="glyphicon glyphicon-remove"></span> 刪除
									</button>
									<input type="hidden" name="gift_no" value="${gift.gift_no}">
									<input type="hidden" name="giftd_no" value="${detail.key.giftd_no}">
									<input type="hidden" name="action" value=deleteOrder>
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
				                </td>
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
<style> 
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    margin-bottom: 10px;
  }
  
.sourceFriend {
    width: 100%;
    height: 50px;
  }
  
</style>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
$('.deleteOrder').click(function(){
	var delGift = $(this).parents('tr').children('td[class=order-delete]').children().serializeArray()
	console.log(delGift);
		$.ajax({
			  type: 'POST',
			  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
			  data: delGift,
			  dataType: 'text',
			  success: (function() {
				  window.location = '${pageContext.request.contextPath}/front_end/gift/gift_order.jsp';
			  }),
			  error:(function() { alert("second error"); })
			}); 
	});

$('select[name=giftod_amount]').change(function(){
	var giftod_amount = $(this).children('option:selected').val();
	var gift_no = $(this).parents('tr').children('td[class=order-delete]').children('input[name=gift_no]').val();
	var giftd_no = $(this).parents('tr').children('td[class=order-delete]').children('input[name=giftd_no]').val();
	var requestURL = $(this).parents('tr').children('td[class=order-delete]').children('input[name=requestURL]').val();
	var obj = jQuery.parseJSON('[{"name":"action", "value":"edit_giftod_amount"}'+
							   ',{"name":"gift_no", "value":"'+gift_no+'"}'+
							   ',{"name":"giftd_no", "value":"'+giftd_no+'"}'+
							   ',{"name":"giftod_amount", "value":"'+giftod_amount+'"}'+
							   ',{"name":"requestURL", "value":"'+requestURL+'"}]');
	console.log(obj);
	$.ajax({
		  type: 'POST',
		  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
		  data: obj,
		  dataType: 'json',
		  success: (function() {
			  $('#myCart').children('ul').children('li:contains("total")')[0].innerText = '123';
// 			  window.location = '${pageContext.request.contextPath}/front_end/gift/gift_order.jsp';
		  }),
		  error:(function() { alert("second error"); })
		});
});

$('body').on('change', 'input[name=giftr_amount]',function() {
	var gift_no = $(this).parents('tr').children('td[class=order-delete]').children('input[name=gift_no]').val();
	var mem_no_other = $(this).siblings('input[name=mem_no_other]').val();
	var giftr_amount = this.value;
	var giftr_message = $(this).siblings('textarea[name=giftr_message]').val();
	var requestURL = $(this).parents('tr').children('td[class=order-delete]').children('input[name=requestURL]').val();
	var obj = jQuery.parseJSON('[{"name":"action", "value":"edit_giftr_amount"}'+
			   				   ',{"name":"gift_no", "value":"'+gift_no+'"}'+
			   				   ',{"name":"mem_no_other", "value":"'+mem_no_other+'"}'+
			   				   ',{"name":"giftr_message", "value":"'+giftr_message+'"}'+
			   				   ',{"name":"giftr_amount", "value":"'+giftr_amount+'"}'+
			   				   ',{"name":"requestURL", "value":"'+requestURL+'"}]');
	console.log(obj);
	$.ajax({
	  type: 'POST',
	  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
	  data: obj,
	  dataType: 'text',
	  success: (function() {
//			  window.location = contextPath + '/gift/gift.do?action=serachFrontGifts&tabWho='+tabWho+'&requestURL='+servletPath+'&whichPage='+whichPage;
	  }),
	  error:(function() { alert("second error"); })
	});
});

$('body').on('change', 'textarea[name=giftr_message]',function() {
	var gift_no = $(this).parents('tr').children('td[class=order-delete]').children('input[name=gift_no]').val();
	var mem_no_other = $(this).siblings('input[name=mem_no_other]').val();
	var giftr_amount = $(this).siblings('input[name=giftr_amount]').val();
	var giftr_message = this.value;	
	var requestURL = $(this).parents('tr').children('td[class=order-delete]').children('input[name=requestURL]').val();
	var obj = jQuery.parseJSON('[{"name":"action", "value":"edit_giftr_message"}'+
			   				   ',{"name":"gift_no", "value":"'+gift_no+'"}'+
			   				   ',{"name":"mem_no_other", "value":"'+mem_no_other+'"}'+
			   				   ',{"name":"giftr_amount", "value":"'+giftr_amount+'"}'+
			   				   ',{"name":"giftr_message", "value":"'+giftr_message+'"}'+
			   				   ',{"name":"requestURL", "value":"'+requestURL+'"}]');
	console.log(obj);
	$.ajax({
	  type: 'POST',
	  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
	  data: obj,
	  dataType: 'text',
	  success: (function() {
//			  window.location = contextPath + '/gift/gift.do?action=serachFrontGifts&tabWho='+tabWho+'&requestURL='+servletPath+'&whichPage='+whichPage;
	  }),
	  error:(function() { alert("second error"); })
	});
});

$(".sourceFriend").draggable({
    helper: "clone",
    connectToSortable: ".sortgroup3"
});


$(".sortgroup3").sortable(
{
    receive: function (e, ui) { isOut = false; },
    over: function (e, ui) { isOut = false; },
    out: function (e, ui) { isOut = true; },

    beforeStop: function (e, ui) {
        if (isOut) {
            $(ui.item).remove();
        }
    }
}).disableSelection();
</script>
</html>