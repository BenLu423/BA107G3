<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
<jsp:useBean id="friendsListSvc" scope="page" class="com.friends_list.model.FriendsListService"/>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
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
            <!-- 搜尋區 -->
			<form method="post" action="<%=request.getContextPath()%>/gift/giftOrderView.do" class="form-horizontal" role="form">
			<div class="input-group searchBar" id="adv-search" style="display:block;">
			<span class="form-control no-border" style="padding:6px 0px 6px 0px;width:23%;font-size:16px;font-weight:800;">查詢訂單日期：</span>
			<input type="text" value="${param.start}" class="form-control" name="start" id="start_dateTime" 
				style="width:30%;font-size:24px;
				${(not empty errorMsgs.start) ? 'background-color:rgb(255,150,120);' : ''}">
			
			<span class="form-control no-border" style="padding:6px 0px 6px 0px;width:5%;font-size:16px;font-weight:900;text-align:center;"> ~ </span>
			
			<input type="text" value="${param.end}" class="form-control" name="end" id="end_dateTime" 
				style="width:30%;font-size:24px;margin-right:20px; 
				${(not empty errorMsgs.end) ? 'background-color:rgb(255,150,120);' : ''}">
			
			<div class="input-group-btn">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="action" value="searchView">
			        <button type="submit" class="btn btn-primary" style="border-radius:5px;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			</div>
			</div>   
		   	</form>
			<!-- //搜尋區 -->       
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<table class="table table-hover orderTable" style="z-index:20;position:relative;">
				    <caption></caption>
				        <thead>
				            <tr>
				            	<th class="orderView-empty">
				                <th class="orderView-person">贈送對象</th>
				                <th class="orderView-gift">禮物</th>
				                <th class="orderView-amount">數量</th>
				                <th class="orderView-time">時間</th>
				            </tr>
				        </thead>
				        <tbody>
				        <c:forEach	var="recGift" items="${orderViewList}">
				            <tr>
				            	<td class="orderView-empty"></td>
				                <td class="orderView-person">
									<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${recGift.mem_no_other}">
				                	<p>${recGift.mem_name_other}</p>
				                </td>
				                <td class="orderView-gift">
				                	<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${recGift.gift_no}">
				                	<p>${recGift.gift_name}</p>
				                </td>
				                <td class="orderView-amount">
									<p>${recGift.giftr_amount}</p>
				                </td>
				                <td class="orderView-time">
				                	<p><fmt:formatDate value="${recGift.gifto_time}" pattern="yyyy-MM-dd a HH:mm:ss"/></p>
				                </td>
<!-- 				                <td class="order-pic"> -->
<%-- 				                	<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.gift_no}"> --%>
<!-- 				                </td> -->
<%-- 				                <td class="order-amount">${detail.key.giftod_amount}</td> --%>
<%-- 				                <td class="order-unit">${detail.key.giftod_unit}</td> --%>
<%-- 				                <td class="order-money">${detail.key.giftod_money}</td> --%>
<!-- 				                <td class="order-inventory-enough" style="font-size:25px;">足夠</td> -->
<!--     							<td class="order-person" style="font-size:15px;"> -->
<!--         							<div class="sortgroup3" style="min-height:117px;border:0px solid black;text-align:left;"> -->
<%--         								<c:forEach var="gift_receive" items="${detail.value}"> --%>
<!--         								<div> -->
<%-- 											<p style="line-height:2.5;display:inline-block;border-left:2px solid rgba(255,194,194,1);border-right:2px solid rgba(255,194,194,1);padding:5px;margin-bottom:0px;">${gift_receive.giftr_amount}個</p> --%>
<%-- 											<p style="display:inline-block;padding:5px;width:210px;margin-bottom:0px;">${gift_receive.giftr_message}</p> --%>
<!-- 										</div> -->
<!-- 										<hr style="width: 100%; height: 2px; background-color: black; margin: 5px 0px 5px 0px;"> -->
<%--         								</c:forEach> --%>
<!--         							</div> -->
<!--     							</td> -->
<%-- 				                <td class="order-delete">${detail.key.giftod_inventory}</td> --%>
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