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
 			<div class="col-xs-12 col-sm-1">
 			<div style="padding-top:8px;padding-bottom:8px;">
 				好友名單
 			</div>
	        <div id="ul_Source3">
				<c:forEach var="friend" items="${friendsListSvc.getMemberFriends(memSelf.mem_no)}">
<!-- 					<div style="height:50px;width:25px;background-color:white;position:absolute;z-index:10;"></div> -->
					<div style="height:50px;width:300px;background-color:white;position:absolute;z-index:10;margin-left:50px"></div>
					<c:set var="mem" value="${memberSvc.getOneMem(friend.mem_no_other)}"/>
					<div class="sourceFriend">								
						<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${mem.mem_no}" style="height:50px;width:50px;">
						<input type="NUMBER" min="1" class="gifr_amount" name="giftr_amount" value="1" style="width:50px;text-align:center;">
						<textarea class="giftr_message" name="giftr_message" rows="4" cols="15"></textarea>
						<button class='btn btn-default btn-xs giftr_del'>
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<input type="hidden" name="mem_no_self" value="${memSelf.mem_no}">
						<input type="hidden" name="mem_no_other" value="${friend.mem_no_other}">
					</div>
				</c:forEach>	          
<!-- 				<div style="height:50px;width:50px;background-color:white;position:absolute;"></div> -->
	        </div>
			</div>
            
			<div class="col-xs-12 col-sm-10">
				<table class="table table-hover orderTable" style="z-index:20;position:relative;">
				    <caption>吐司網購物中心</caption>
				        <thead>
				            <tr>
				                <th class="order-name">禮物名稱</th>
				                <th class="order-pic">禮物圖片</th>
				                <th class="order-amount">數量</th>
				                <th class="order-unit">單價</th>
				                <th class="order-money">小計</th>
				                <th class="order-inventory">可買量</th>
				                <th class="order-person">收禮會員/數量/留言</th>
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
									<input type="hidden" name="mem_no_self" value="${memSelf.mem_no}">
									<input type="hidden" name="gift_no" value="${detail.key.gift_no}">
									<input type="hidden" name="giftd_no" value="${detail.key.giftd_no}">
									<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
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
									<input type="hidden" name="giftod_inventory" value="${detail.key.giftod_inventory}">
				                </td>
				                <td class="order-unit">${detail.key.giftod_unit}</td>
				                <td class="order-money">${detail.key.giftod_money}</td>
				                <td class="order-inventory-enough">足夠</td>
    							<td class="order-person">
        							<div class="sortgroup3" style="min-height:117px;border:0px solid black;text-align:left;">
        								<c:forEach var="gift_receive" items="${detail.value}">
        								<div class="sourceFriend">
											<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${gift_receive.mem_no_other}" style="height:50px;width:50px;">
											<input type="NUMBER" min="1" class="giftr_amount" name="giftr_amount" value="${gift_receive.giftr_amount}" style="width:50px;text-align:center;">
											<textarea class="giftr_message" name="giftr_message" rows="4" cols="20" style="resize:none;">${gift_receive.giftr_message}</textarea>
											<button class='btn btn-default btn-xs giftr_del'>
												<span class="glyphicon glyphicon-remove"></span>
											</button>
											<input type="hidden" name="mem_no_self" value="${gift_receive.mem_no_self}">
											<input type="hidden" name="mem_no_other" value="${gift_receive.mem_no_other}">
										</div>
        								</c:forEach>
        							</div>
    							</td>
				                <td class="order-delete">
									<button class='btn btn-danger btn-xs deleteOrder'>
										<span class="glyphicon glyphicon-remove"></span> 刪除
									</button>
				                </td>
				            </tr>
				        </c:forEach>
				        </tbody>
				</table>          
				<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
				<div class="col-xs-12 col-sm-12" style="text-align:right;">
					<span id="checkoutMoney">
						Total: $<p>${orderMoney}</p>
					</span>
					<a href="<%=request.getContextPath()%>/front_end/gift/gift_checkout.jsp">
						<img src="<%=request.getContextPath()%>/front_end/res/img/gift/ckeckout.jpg" style="height:60px;">結帳
					</a>
				</div>         
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
    width: 150px;
    height: 50px;
  }
a:focus, a:hover{
	color: black;
	text-decoration: none;
}  
a{
	color:black;
	font-size: 20px;
	font-weight: 600;
}
#checkoutMoney{
	display:inline-block;
	padding-right:50px;
	font-size:40px;
	vertical-align:middle;
	margin:0px; 
}
#checkoutMoney p{
 	display:inline-block;
 	margin:0px;
}
</style>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
function giftOrderDetail(thisDetail) {
	//0: mem_no_self[String]
	//1: gift_no[Sting]
	//2: giftd_no[String]
	//3: requestURL[String]
    return $(thisDetail).parents('tr').children('td[class=order-name]').children().serializeArray();
}

$('.deleteOrder').click(function(){
	var obj = giftOrderDetail($(this));
	obj[obj.length] = {name: "action", value: "deleteOrder"};
	var detail = $(this).parents('tr');
	console.log(obj);
		$.ajax({
			  type: 'POST',
			  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
			  data: obj,
			  dataType: 'json',
			  success: (function(json) {
				  if(json.status == 'success'){
					  detail.remove();
					  $('#myCart').children('ul').find('span:contains("'+json.gift_name+'")').parent().remove();
					  $('#myCart').children('ul').children('li:contains("total")')[0].innerText = 'total: '+json.orderMoney;
					  $('#checkoutMoney').find('p')[0].innerText = json.orderMoney;
				  }
// 				  window.location = '${pageContext.request.contextPath}/front_end/gift/gift_order.jsp';
			  }),
			  error:(function() { alert("second error"); })
			}); 
	});

$('select[name=giftod_amount]').change(function(){
	var obj = giftOrderDetail($(this));;
	obj[obj.length] = {name: "action",  value: "edit_giftod_amount"};   	
	obj[obj.length] = {name: "giftod_amount",  value: $(this).children('option:selected').val()};   	
	console.log(obj);
	var giftod_money = $(this).parents('tr').children('td[class=order-money]')[0];
	var giftod_inventory = $(this).siblings('input[name=giftod_inventory]')[0];
	var giftod_amount = $(this).find('option:selected')[0];
	var ori = $(this).parents('tr').children('td[class=order-amount]');
	$.ajax({
		  type: 'POST',
		  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
		  data: obj,
		  dataType: 'json',
		  success: (function(json) {
			  if(json.status == 'success'){
				  $('#myCart').children('ul').find('span:contains("'+json.gift_name+'")')[0].innerText = json.gift_name+"　"+json.giftod_amount+'個';
				  $('#myCart').children('ul').children('li:contains("total")')[0].innerText = 'total: '+json.orderMoney;
				  $('#checkoutMoney').find('p')[0].innerText = json.orderMoney;
				  giftod_money.innerText = json.giftod_money;
	 			  giftod_inventory.value = json.giftod_inventory;
				  console.log(giftod_inventory.value);
				}else{
					alert("低於已贈送的禮物數量");
					ori.find('select option:selected')[0].selected = false;
					ori.find('select option[value='+json.oriAmount+']')[0].selected = true;
				}
			  
			  
		  }),
		  error:(function() { alert("second error"); })
		});
});

$('body').on('change', 'input[name=giftr_amount],textarea[name=giftr_message]',function() {
	var obj = giftOrderDetail($(this));;
	obj[obj.length] = {name: "mem_no_other",  value: $(this).parent().find('input[name=mem_no_other]').val()};   	
	obj[obj.length] = {name: "giftr_amount",  value: $(this).parent().find('input[name=giftr_amount]').val()};   	
	obj[obj.length] = {name: "giftr_message",  value: $(this).parent().find('textarea[name=giftr_message]').val()};   	
	obj[obj.length] = {name: "action",  value: "editRecGift"};   	
	console.log(obj);
	var giftod_inventory = $(this).parents('tr').children('td[class=order-amount]').children('input[name=giftod_inventory]')[0];
	var giftr_amount = $(this).parent().children('input[name=giftr_amount]')[0];
	$.ajax({
	  type: 'POST',
	  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
	  data: obj,
	  dataType: 'json',
	  success: (function(json) {
			if(json.status == 'success'){
				giftod_inventory.value = parseInt(giftod_inventory.value) - parseInt(json.giftr_amount); 
			}else{
				alert("已超過可贈送的禮物數量");
				giftr_amount.value = json.oriAmount;
			}
		  
//			  window.location = contextPath + '/gift/gift.do?action=serachFrontGifts&tabWho='+tabWho+'&requestURL='+servletPath+'&whichPage='+whichPage;
	  }),
	  error:(function() { alert("second error"); })
	});
});

$(".sourceFriend").draggable({
    helper: "clone",
    connectToSortable: ".sortgroup3",
    opacity:0.5
});

$(".sortgroup3").sortable({
    receive: function (e, ui) { 
    	isOut = false;
    	var obj = $(ui.helper[0]).children().serializeArray();
    	var gift_no  = $(ui.helper[0]).parents('tr').find('input[name=gift_no]').val();
    	var giftd_no = $(ui.helper[0]).parents('tr').find('input[name=giftd_no]').val();
    	obj[obj.length] = {name: "action",  value: "addRecGift"};   	
    	obj[obj.length] = {name: "gift_no", value: gift_no};
    	obj[obj.length] = {name: "giftd_no", value: giftd_no};
    	console.log(obj);
    	var giftod_inventory = $(ui.helper).parents('tr').children('td[class=order-amount]').children('input[name=giftod_inventory]')[0];
    	if(gift_no){
	    	$.ajax({
	  		  type: 'POST',
	  		  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
	  		  data: obj,
	  		  dataType: 'json',
	  		  success: (function(json) {
	  			if(json.status == 'success'){
	  				giftod_inventory.value = parseInt(giftod_inventory.value) - parseInt(json.giftr_amount); 
	  			}else{
	  				console.log(json.status);
	  			}
	  		  }),
	  		  error:(function() { alert("second error"); })
	  	    });
    	}
    },
    over: function (e, ui) { 
    	isOut = false;
    	var giftod_inventory = $(ui.helper).parents('tr').children('td[class=order-amount]').children('input[name=giftod_inventory]')[0];
    	var allPerson = $(ui.helper).parents('tr').children('td[class=order-person]').children('div');
    	if(parseInt(giftod_inventory.value)<=0){
 			$(ui.item).remove();
    	}
    	var person = [];
    	allPerson.children('div').each(function (index) {
    		var mem_no_other = $(this).children('input[name=mem_no_other]')[0];
    		if(mem_no_other){
    			var value = mem_no_other.value;
    			if(person.indexOf(value) == -1){
	    			person.push(value);
    			}else{
    				$(ui.item).remove();
    			}
    		}
    	});
    },
    out: function (e, ui) { isOut = true;},
    beforeStop: function (e, ui) {
        if (isOut) {
        	var obj = $(ui.helper[0]).children().serializeArray();
        	obj[obj.length] = {name: "action",  value: "removeRecGift"};   	
        	obj[obj.length] = {name: "gift_no", value: $(ui.helper[0]).parents('tr').find('input[name=gift_no]').val()};
        	obj[obj.length] = {name: "giftd_no", value: $(ui.helper[0]).parents('tr').find('input[name=giftd_no]').val()};
			var ori = $(this).parents('tr').children('td[class=order-amount]');
			var giftod_inventory = $(ui.helper).parents('tr').children('td[class=order-amount]').children('input[name=giftod_inventory]')[0];
        	$.ajax({
        		  type: 'POST',
        		  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
        		  data: obj,
        		  dataType: 'json',
        		  success: (function(json) {
        			if(json.status == 'success'){
        				$(ui.item).remove();
        				giftod_inventory.value = parseInt(giftod_inventory.value) + parseInt(json.giftr_amount); 
// 						var newValue = parseInt(ori.find('select option:selected')[0].innerText) + parseInt(json.giftr_amount);
// 						ori.find('select option:selected')[0].selected = false;
// 						ori.find('select option[value='+newValue+']')[0].selected = true;
        			}
        		  }),
        		  error:(function() { alert("second error"); })
        	});
        }
    }
}).disableSelection();

$('body').on('click', '.giftr_del',function() {
   	var obj = $(this).parents('div').children().serializeArray();
	obj[obj.length] = {name: "action",  value: "removeRecGift"};   	
	obj[obj.length] = {name: "gift_no", value: $(this).parents('tr').find('input[name=gift_no]').val()};
	obj[obj.length] = {name: "giftd_no", value: $(this).parents('tr').find('input[name=giftd_no]').val()};
	var unit = $(this).parents('tr').children('td[class=order-unit]');
	var amount = $(this).parents('tr').children('td[class=order-amount]');
	var money = $(this).parents('tr').children('td[class=order-money]');
	var delDiv = $(this).parent('div');
	var giftod_inventory = $(this).parents('tr').children('td[class=order-amount]').children('input[name=giftod_inventory]')[0];
	console.log(obj);
		$.ajax({
		  type: 'POST',
		  url: '${pageContext.request.contextPath}/gift/giftOrder.do',
		  data: obj,
		  dataType: 'json',
		  success: (function(json) {
			if(json.status == 'success'){
				giftod_inventory.value = parseInt(giftod_inventory.value) + parseInt(json.giftr_amount); 
				delDiv.remove();
// 				var newValue = parseInt(amount.find('select option:selected')[0].innerText) + parseInt(json.giftr_amount);
// 				amount.find('select option:selected')[0].selected = false;
// 				amount.find('select option[value='+newValue+']')[0].selected = true;
// 				money[0].innerText = parseInt(unit[0].innerText) * parseInt(newValue);
			}
		  }),
		  error:(function() { alert("second error"); })
	});
});
</script>
</html>