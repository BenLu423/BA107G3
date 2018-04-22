<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
<jsp:useBean id="friendsListSvc" scope="page" class="com.friends_list.model.FriendsListService"/>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<jsp:useBean id="giftOrderViewSvc" scope="page" class="com.giftOrderView.model.GiftOrderViewService"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>禮物商城</title>
	<jsp:include page="/front_end/header.jsp"></jsp:include>
	<script src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.js"></script> 
	<script type="text/javascript"  charset="UTF-8"  src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.full.js"></script> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift_order.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift_view.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
            <div style="height:0px;"></div>
             <%-- Exception資訊 --%>
    		${errorMsgs.Exception}
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<table class="table table-hover orderTable" style="z-index:20;position:relative;font-size:20px;">
				<caption></caption>
				<thead>
		            <tr>
		            	<th class="orderView-empty" style="width:15%;text-align:center;">禮物</th>
		                <th class="orderView-person" style="width:85%;">
		                	<div>
			                	<span style="display:inline-block;width:150px;">會員</span>
								<span style="display:inline-block;width:200px;">接收數量</span>
			                	<span style="display:inline-block;width:300px;">訊息留言</span>
			                	<span style="display:inline-block;width:100px;">獲贈時間</span>
		            		</div>
		                </th>
		            </tr>
				</thead>
				<tbody>
		        <c:forEach	var="recGift" items="${(receiveViewList!=null) ? receiveViewList : giftOrderViewSvc.getReceiveAll(memSelf.mem_no)}" varStatus="status">
					<c:if test="${status.index!=0 and recGift.gift_no!=gift_no}">
								<div class="rec-total">X${count}</div>
			                </td>
			            </tr>
					</c:if>		
		            <c:if test="${status.index==0 or recGift.gift_no!=gift_no}">
			            <c:set var="gift_no" value="${recGift.gift_no}"/>
			        	<c:set var="count" value="0"/>
		            	<tr>
			                <td class="orderView-gift" style="text-align:center;">
			                	<img style="width:150px;height:150px;" src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${recGift.gift_no}">
			                </td>
			                <td class="orderView-amount" colspan="2">
		            </c:if>
			            	<c:set var="count" value="${count+recGift.giftr_amount}"/>
		            		<div style="border:1px solid black;">
			                	<span style="display:inline-block;width:50px;">
			                		<img style="width:50px;height:50px;" src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${recGift.mem_no_self}">
			                	</span>
			                	<span style="display:inline-block;width:100px;">${recGift.mem_name_self}</span>
								<span style="display:inline-block;width:200px;">${recGift.giftr_amount}個　</span>
			                	<span style="display:inline-block;width:300px;">${recGift.giftr_message}</span>
			                	<span style="display:inline-block;width:250px;font-size:16px;">
			                		<fmt:formatDate value="${recGift.giftr_time}" pattern="yyyy-MM-dd a HH:mm:ss"/>
			                	</span>
		            		</div>
		        </c:forEach>
								<div class="rec-total">X${count}</div>
			                </td>
			            </tr>
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
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  .no-border{
    border: 0;
    box-shadow: none;
  }
  .orderView-amount{
  	position: relative;	
  }
  .rec-total{
  	position:absolute;
 	top: 0px;
 	left: -100px;
 	border-radius:5%;
 	color: #ff0000;	
 	font-size:40px;	
 	font-weight:900;
  }
</style>
<script>
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:false,
	  step: 60
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false,
   	    maxDate: new Date()		
	   })
	  },
	  timepicker:false,
	  step: 60
	 });
});
</script>
</html>