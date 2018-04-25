<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="depositSvc" class="com.deposit.model.DepositService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/card/card.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/deposit.css">
<title>加值服務</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>

<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/card/card.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/tip_cards/jquery.tip_cards.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/imgcheckbox/jquery.imgcheckbox.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/tip_cards/tip_cards.css">

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"  style="margin-top:100px;"></div>  
			<div class="col-xs-12 col-sm-6">
				<div class="page-container" id="allMoney">
			        <ul class="tips">
			        	<c:forEach var="depositVO" items="${depositSvc.all}">
			      		<li>
				            <div class="tc_front" style="margin-bottom:10px;">
				            	<p>${depositVO.depo_name}</p>
				            	<span>
				            	<img style="width:20px;display:inline-block;" src="<%=request.getContextPath()%>/front_end/res/img/deposit/coin.jpg" alt="">
				            	${depositVO.depo_value}
				            	</span>
				            	<input type="hidden" name="depo_no" value="${depositVO.depo_no}"/>
				            	<input type="hidden" name="depo_value" value="${depositVO.depo_value}"/>
				            </div>
			          	</li>          
			        	</c:forEach>
					</ul>
				</div>
			
			</div>
			<div class="col-xs-12 col-sm-6">
				<div class="col-xs-12 col-sm-6 card-wrapper"></div>
		        <div class="col-xs-12 col-sm-6 form-container active" style="margin-top:25px;">
		            <form>
		                <input placeholder="Card number" type="text" name="number">
		                <input placeholder="Full name" type="text" name="name">
		                <input placeholder="MM/YY" type="text" name="expiry">
		                <input placeholder="CVC" type="text" name="cvc">
		            </form>
		        </div>
		        <div class="col-xs-12 col-sm-9" style="text-align:right;margin-top:70px;">
					<div>
						<img id="coin" src="<%=request.getContextPath()%>/front_end/res/img/deposit/coin.jpg" alt="">	
						<p style="display:inline-block;" id="currentMoney">目前代幣：　</p>
						<p style="display:inline-block;width:100px;" class="money">${memSelf.mem_deposit}</p>
					</div>
					<div>
						<p style="width:72px;display:inline-block;"></p>
						<p style="display:inline-block;" id="buyMoney">購買代幣：　</p>
						<p style="display:inline-block;width:100px;" class="money">0</p>
					</div>
					<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
					<div>
						<p style="width:72px;display:inline-block;"></p>
						<p style="display:inline-block;" id="remainMoney">剩餘代幣：　</p>
						<p style="display:inline-block;width:100px;" class="money">${memSelf.mem_deposit}</p>
					</div>
					<div>
						<button type="button" id="storedValue" class="btn btn-default btn-block" style="margin-top:40px;font-size:30px;">儲值</button>
						<input type="hidden" id="requestURL" value="${requestURL}">
					</div>
		        </div>
	    	</div>    
		<div class="col-xs-12 col-sm-12" style="margin-top:84px;"></div>
		</div>    
	</div>	 
	<div class="modal"></div>
</div>     
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
<style>
    input {
        margin: 10px 0px 10px 0px;
        display: block;
    }
    
    .page-container {
      background: #E5E8ED;
      padding: 0;
      text-align: center;
      margin: 0;
      height: 100%;
    }
    
    .page-container p{
      font-size: 21px;
      font-weight: 100;
      line-height: 180%;
    }
    
    .tc_card:nth-child(1n+1) .tc_inner_card  {
      border-bottom: 3px solid #1345ED;
    }
    
    .tc_card:nth-child(2n+2) .tc_inner_card  {
      border-bottom: 3px solid #03A010;
    }
    
    .tc_card:nth-child(3n+3) .tc_inner_card  {
      border-bottom: 3px solid #F8B00F;
    }
    
    .tc_card:nth-child(4n+4) .tc_inner_card {
      border-bottom: 3px solid #DD4330;
    }
    
    .tc_inner_card {
      width: 175px;
    }
    
	.modal {
	    display:    none;
	    position:   fixed;
	    z-index:    1000;
	    top:        0;
	    left:       0;
	    height:     100%;
	    width:      100%;
	    background: rgba( 255, 255, 255, .8 ) 
	                url('http://sampsonresume.com/labs/pIkfp.gif') 
	                50% 50% 
	                no-repeat;
	}
	
	body.loading {
	    overflow: hidden;   
	}
	
	body.loading .modal {
	    display: block;
	}
</style>
<script>
$(document).ready( function() {
	$('#storedValue').click(function(){
		$body = $("body");
		var requestURL = $('#requestURL')[0].value;
		if(requestURL=="" || requestURL==null){
			requestURL = "/front_end/gift/gift_index.jsp";
		}
		var obj = [{name: "action",value: "storedValue"},{name: "requestURL",value: requestURL},];
		$('#allMoney').find('span').each(function (index) {
			if($(this).attr('class')!=null && $(this).attr('class').match('imgChked')){
				var money = $(this).find('input[name=depo_value]')[0].value;
				obj[obj.length] = {name: "money", value: money};
			}
       	});
		$body.addClass("loading");
		$.ajax({
			type: 'POST',
			url: '/BA107G3/deposit/deposit.do',
			data: obj,
			dataType: 'json',
			success: (function(json) {
				if(json.status=='success'){
					setTimeout(function(){ $body.removeClass("loading"); 
					window.location.href = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}'+requestURL;
					}, 3000);
				}
			}),
			error:(function() { console.log("second error"); })
		}); 
	});
	
	$('.active form').card({
    	container: $('.card-wrapper')
	})
 	$(".tips").tip_cards();
	$(".tc_inner_card").imgCheckbox({
	    onload: function(){
	        // Do something fantastic!
	    },
	    onclick: function(el){
	    	var isChecked = el.hasClass("imgChked"),
		    imgEl = el.children()[0];  // the img element
		    var money = $('#buyMoney').next();
		    var total = $('#remainMoney').next();
		    if($(el).attr('class').match('imgChked')!=null){
			    money[0].innerText = parseInt(money[0].innerText) + parseInt($(el).find('input[name=depo_value]')[0].value);
			    total[0].innerText = parseInt(total[0].innerText) + parseInt($(el).find('input[name=depo_value]')[0].value);
		    }
		    else{
			    money[0].innerText = parseInt(money[0].innerText) - parseInt($(el).find('input[name=depo_value]')[0].value);
			    total[0].innerText = parseInt(total[0].innerText) - parseInt($(el).find('input[name=depo_value]')[0].value);
		    }
	    }
	});
});
</script>
</html>