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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/jquery.countdown.css"> 
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/countdown-2.2.0/jquery.countdown.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/countdown-2.2.0/jquery.plugin.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/fly/fly.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/fly/requestAnimationFrame.js"></script>
 
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift_list.js"></script> --%>
<title>禮物商城</title>
</head>
<body>
<div class="col-xs-12 col-sm-12 gift-div">
	<div class="row">
	<c:forEach var="discount" items="${giftDiscountSvc.all}" varStatus="status">
<%--       	<c:if test="${(status.count)%5 == 1}"> --%>
<!--        	<div class="col-xs-12 col-sm-1 gift-side"></div> -->
<%--       	</c:if> --%>
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
					<div style="color:red;text-align:center;font-size:20px;" data-countdown="${discount.giftd_end}"></div>
					<button type="button" style="margin-right: 0px;">FOLLOW</button>
					<button type="button" class="addToCart" style="margin: 0px;">ADD TO CART</button>
					<select name="giftod_amount" style="height: 30px;width: 45px;">
						<c:forEach var="count" begin="1" end="${giftSvc.getAmount(giftD.gift_no)}" step="1">
							<option value="${count}">${count}</option>
						</c:forEach>
					</select>  
					<input type="hidden" name="giftd_no" value="${discount.giftd_no}">
					<input type="hidden" name="action" value="addOrder">
				</div>
        	</div>
		</div>
<%-- 		<c:if test="${(status.count)%5 == 0}"> --%>
<!--        	<div class="col-xs-12 col-sm-1 gift-side"></div> -->
<%--       	</c:if> --%>
    </c:forEach>
    <div class="col-xs-12 col-sm-12">
	</div> 
	</div>
	<img id="alertGift" src="${pageContext.request.contextPath}/front_end/res/img/gift/not_enough.jpg" style="width:500px" alt="" >
</div> 		
</body>
<style>
.blink {
    animation-duration: 1s;
    animation-name: blink;
    animation-iteration-count: infinite;
    animation-direction: alternate;
    animation-timing-function: ease-in-out;
    font-weight: 700;
}
@keyframes blink {
    from {
        opacity: 1;
    }
    to {
        opacity: 0;
    }
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	//gs : giftSatus
    connectgs();
    
	function connectgs() {
		var gsPoint = "/GiftStatusServer/${memSelf.mem_no}";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var gsendPointURL = "ws://" + window.location.host + webCtx + gsPoint;
		
	    console.log("connect ws:giftStatus");
		// 建立 websocket 物件
		gsWebSocket = new WebSocket(gsendPointURL);
		
		gsWebSocket.onopen = function(event) {
			console.log("gsWebSocket 成功連線");
		};

		gsWebSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        console.log(jsonObj);
	        var giftAction = jsonObj[0];
	        var giftVO = jsonObj[1];
	        var giftLabelList = jsonObj[2];
	        var giftDiscountVO = jsonObj[3];
	        var allGift = $('div.col-xs-12.col-sm-2.gift-side');
	        
	        if(giftAction.action == "insertGift"){
	        	var isExists = "no";
	        	allGift.find('div:eq(4) input[name=gift_no]').each(function (index) {
	        		var gift_no = $(this)[0].value;
	        		if(gift_no == giftVO.gift_no){
	        			isExists= "yes";
	        			return;
	        		}
	        	});
				if(isExists == "yes")
					return;
	        	//複製禮物樣板與修改資訊
				var clone = allGift.clone()[0];
				
				//修改圖片
				$(clone).find('div.item-img img').attr('src','${pageContext.request.contextPath}/DBGifReader4?table=GIFT&gift_no='+giftVO.gift_no);
				//修改折扣數
				if(giftDiscountVO != null){
					$(clone).find('div.item-img div.gift-discount')[0].innerText = giftDiscountVO.giftd_percent + "折";
				}else{
					$(clone).find('div.item-img div.gift-discount')[0].innerText = "";
				}
				//修改標籤
				var labels = "";
				for(var i=0; i<giftLabelList.length; i++){
					labels += '<p>' + giftLabelList[i].giftl_name + '</p>'; 
				}
				$(clone).find('div.item-img div.gift-labels')[0].innerHTML = labels;
				//修改名稱
				$(clone).find('div:eq(4) h3')[0].innerText = giftVO.gift_name;
				$(clone).find('div:eq(4) input[name=gift_no]')[0].value = giftVO.gift_no;
				//修改內容
				var cnt = giftVO.gift_cnt;
				var length = cnt.length>=35 ? 35 : cnt.length;
				$(clone).find('div:eq(4) p.JQellipsis')[0].innerText = cnt.substring(0,length-1) + "...";
				$(clone).find('div:eq(4) p.JQellipsis').attr('title',cnt);
				if(giftDiscountVO != null){
					var price = parseInt(giftVO.gift_price) * parseFloat(giftDiscountVO.giftd_percent);
					$(clone).find('div:eq(4) input[name=giftd_no]')[0].value = giftDiscountVO.giftd_no;
					$(clone).find('div:eq(4) p:eq(1) span:eq(0)')[0].innerText = "$"+ parseInt(price);
					$(clone).find('div:eq(4) p:eq(1) span:eq(1)')[0].innerHTML = "<s>$"+ giftVO.gift_price + "</s>";
				}else{
					$(clone).find('div:eq(4) p:eq(1) span')[0].innerText = '$' + giftVO.gift_price;
					$(clone).find('div:eq(4) p:eq(1) span:eq(1)')[0].innerHTML = "<s></s>";
				}
				//修改價格
				//修改倒數計時
				$($(clone).find('div:eq(4) div:eq(0)')[0]).css('color','#FFF500');
				$($(clone).find('div:eq(4) div:eq(0)')[0])[0].innerText = "最新上架商品";
				$($(clone).find('div:eq(4) div:eq(0)')[0]).addClass('blink');
				//修改可選擇數量
				var optionValue = "";
				var count = 10;
				if(giftDiscountVO != null){
					count = parseInt(giftDiscountVO.giftd_amount);
				}
				for(var i=1; i<=count; i++){
					optionValue += "<option value="+i+">"+i+"</option>";
				}
				$(clone).find('div:eq(4) select')[0].innerHTML = optionValue;
				
				//顯示在頁面上
				var clone2 = $(clone).clone()[0];
				if(giftDiscountVO != null){
					$('div.col-xs-12.col-sm-12.gift-div:eq(0)').find(allGift)[0].before(clone2);
				}

	        	$('div.col-xs-12.col-sm-12.gift-div:eq(1)').find(allGift)[0].before(clone);
				
				console.log("gsWebSocket上架禮物　:" + giftVO.gift_no);
	        }//end if. [gift_is_on=="上架中"]
	        else if(giftAction.action == "deleteGift"){
	        	allGift.find('div:eq(4) input[name=gift_no]').each(function (index) {
	        		var gift_no = $(this)[0].value;
	        		if(gift_no == giftVO.gift_no){
	        			$(this).parents('div.col-xs-12.col-sm-2.gift-side').remove();
	        		}
	        	});
	        }//end if. [gift_is_on=="已下架"]
	        else if(giftAction.action == "updateGift"){
				//如果限時優惠，正在進行中	      
	        	if(giftDiscountVO != null){
					$('div.col-xs-12.col-sm-12.gift-div').find(allGift).each(function (index) {
		        		var gift_no = $(this).find('div:eq(4) input[name=gift_no]')[0].value;
		        		console.log(gift_no);
		        		console.log(giftVO.gift_no);
		        		console.log(gift_no == giftVO.gift_no);
		        		if(gift_no == giftVO.gift_no){
		       				//修改折扣數
		  					$(this).find('div.item-img div.gift-discount')[0].innerText = giftDiscountVO.giftd_percent + "折";
		       				//修改售價
		       				var price = parseInt(giftVO.gift_price) * parseFloat(giftDiscountVO.giftd_percent);
		       				$(this).find('div:eq(4) p:eq(1) span:eq(0)')[0].innerText = "$"+ parseInt(price);
							//修改倒數計時
							var GDdateE = new Date(giftDiscountVO.giftd_end);
							var GDdateFormat = GDdateE.getFullYear()+'-'+(GDdateE.getMonth()+1)+'-'+GDdateE.getDate()+' '+GDdateE.getHours()+':'+GDdateE.getMinutes()+':'+GDdateE.getSeconds()+'.0';
							var time = $($(this).find('div:eq(4) div:eq(0)')[0]).clone();
							var $this = $(time[0]);
								$this.countdown(GDdateFormat, function(event) {
								$this.html('剩餘 ' + event.strftime('%D days %H:%M:%S'));
							  }).on('finish.countdown', function() {
								  var index = $(this).parents('.col-xs-12.col-sm-12.gift-div').index();
								  if(index == 3){
									  var gift = $(this).parents('div.col-xs-12.col-sm-2.gift-side');
									  var oriValue = gift.find('div:eq(4) p:eq(1) span:eq(1)')[0].innerText;
									  gift.find('div:eq(4) p:eq(1) span:eq(0)')[0].innerText = oriValue;
									  gift.find('div:eq(4) p:eq(1) span:eq(1)')[0].innerHTML = '';
									  gift.find('div:eq(4) input[name=giftd_no]')[0].remove();
									  gift.find('div.item-img div.gift-discount')[0].innerText = "";
									  var optionValue = "";
									  for(var i=1; i<=10; i++){
											optionValue += "<option value="+i+">"+i+"</option>";
										}
									  gift.find('div:eq(4) select')[0].innerHTML = optionValue;
									  $(this)[0].innerText = "";
								  }else if(index == 8){
									  $(this).parents('div.col-xs-12.col-sm-2.gift-side').remove();
								  }
							  });
							$(this).find('div:eq(4) div:eq(0)')[0].after(time[0]);	
							$(this).find('div:eq(4) div:eq(0)')[0].remove();
							//修改可選擇數量
							var optionValue = "";
							var count = 10;
							if(giftDiscountVO != null){
								count = parseInt(giftDiscountVO.giftd_amount);
							}
							for(var i=1; i<=count; i++){
								optionValue += "<option value="+i+">"+i+"</option>";
							}
							$(this).find('div:eq(4) select')[0].innerHTML = optionValue;
		        			return;
		        		}
		        	});
	        	}else{
	        		//限時優惠已結束
	        		allGift.find('div:eq(4) input[name=gift_no]').each(function (index) {
		        		var gift_no = $(this)[0].value;
		        		if(gift_no == giftVO.gift_no){
		        			$(this).parents('div.col-xs-12.col-sm-2.gift-side').remove();
		        		}
		        	});
	        	}
	        	
	        	
	        }//end if. [禮物的限時優惠數量有所變更時]
		};

		gsWebSocket.onclose = function(event) {
			console.log("gsWebSocket 已離線");
		};
	}
	
	$('body').on('click', '.addToCart',function() {
		var img = $(this).parents('div[class=gift-item]').find('div.item-img img').clone();
		$(img).css('width','100px').css('height','100px').css('border-radius','50%').css('border','2px solid black');
		var oriPosition = $(this).offset();
		console.log($(this));
		var addGift = $(this).parent('div').children().serializeArray();
		$.ajax({
			type: 'POST',
			url: '/BA107G3/gift/giftOrder.do',
			data: addGift,
			dataType: 'json',
			success: (function(json) {
				if(json.status == 'success'){
					var isExists = "false";
			  		var position = "-1";
					$('#myCart').find('ul li').each(function (index) {
						var gift_no_HTML = $(this).find('a span:eq(0) img');
						if(gift_no_HTML.length != 0){
							var str = gift_no_HTML.attr('src');
							var len = str.length;
							var no = str.substring(len-4,len);
							if(no == json.gift_no){
							  	isExists = "true";
							  	position = index;
							}
						}
					});
					if(isExists == "false"){
						var context = "<li><a href='#'>"+
									  "<span><img style='height:30px;' src=${pageContext.request.contextPath}/DBGifReader4?table=GIFT&gift_no="+json.gift_no+"></span>"+
						        	  "<span>　 "+json.gift_name+"　"+json.giftod_amount+"個</span>"+
						        	  "</a></li>";
						$('#myCart').find('ul li:eq(-3)').before(context);
						$('#cartSum')[0].innerText = parseInt($('#cartSum')[0].innerText) + 1;
					}else{
						$('#myCart').find('ul li:eq('+position+') a span:eq(1)')[0].innerHTML = json.gift_name+"　"+json.giftod_amount+"個";
					}
					$('#myCart').children('ul').children('li:contains("total")')[0].innerText = 'total: '+json.orderMoney;
					var cart = $('#myCart').offset();
					flyer = $(img).addClass('u-flyer');
					flyer.fly({
					    start: {
					        left: oriPosition.left-$('html').scrollLeft(),
					        top: oriPosition.top-$('html').scrollTop()
					    },
					    end: {
					        left: cart.left-$('html').scrollLeft(),
					        top: cart.top-$('html').scrollTop(),
					        width: 0,
					        height: 0
					    }
					});
				}//end success
				else if(json.status == 'failure'){
					alertModal.open();
				}
				
				
			}),
			error:(function() { console.log("second error"); })
		}); 
	});
	
	$('[data-countdown]').each(function() {
		  var $this = $(this), finalDate = $(this).data('countdown');
		  $this.countdown(finalDate, function(event) {
		    $this.html('剩餘 ' + event.strftime('%D days %H:%M:%S'));
		  }).on('finish.countdown', function() {
			  var index = $(this).parents('.col-xs-12.col-sm-12.gift-div').index();
			  if(index == 3){
				  var gift = $(this).parents('div.col-xs-12.col-sm-2.gift-side');
				  var oriValue = gift.find('div:eq(4) p:eq(1) span:eq(1)')[0].innerText;
				  gift.find('div:eq(4) p:eq(1) span:eq(0)')[0].innerText = oriValue;
				  gift.find('div:eq(4) p:eq(1) span:eq(1)')[0].innerHTML = '';
				  gift.find('div:eq(4) input[name=giftd_no]')[0].remove();
				  gift.find('div.item-img div.gift-discount')[0].innerText = "";
				  var optionValue = "";
				  for(var i=1; i<=10; i++){
						optionValue += "<option value="+i+">"+i+"</option>";
					}
				  gift.find('div:eq(4) select')[0].innerHTML = optionValue;
				  $(this)[0].innerText = "";
			  }else if(index == 8){
				  $(this).parents('div.col-xs-12.col-sm-2.gift-side').remove();
			  }
		  });
	});
	var alertModal = new Custombox.modal({
		content: {
// 			effect: 'Slide',
			overlay: false,
	    	target: '#alertGift'
		},
		overlay: {
			active: false,
		  }
	});
});
$(window).on('beforeunload',function(){
	console.log("disconnect ws:giftStatus");
	gsWebSocket.close();
});
</script>
</html>