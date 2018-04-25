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
				<div class="page-container">
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
		            <form action="">
		                <input placeholder="Card number" type="text" name="number">
		                <input placeholder="Full name" type="text" name="name">
		                <input placeholder="MM/YY" type="text" name="expiry">
		                <input placeholder="CVC" type="text" name="cvc">
		            </form>
		        </div>
		        <div class="col-xs-12 col-sm-9" style="text-align:right;margin-top:70px;">
					<div>
						<img id="coin" src="<%=request.getContextPath()%>/front_end/res/img/deposit/coin.jpg" alt="">	
						<p style="display:inline-block;" id="remainMoney">目前代幣：　</p>
						<p style="display:inline-block;" id="remainMoney">${memSelf.mem_deposit}</p>
					</div>
					<div>
						<p style="width:72px;display:inline-block;"></p>
						<p style="display:inline-block;" id="remainMoney">購買代幣：　</p>
						<p style="display:inline-block;" id="remainMoney">0</p>
					</div>
					<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
					<div id="remainMoney">剩餘代幣：　${memSelf.mem_deposit}</div>
		        </div>
	    	</div>    
		<div class="col-xs-12 col-sm-1"></div>
	</div>    
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
</style>
<script>
$(document).ready( function() {
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
			console.log($(el).find('input[name=depo_no]')[0].value);//編號
			console.log($(el).find('input[name=depo_value]')[0].value);//價格
	    }
	});
});
</script>
</html>