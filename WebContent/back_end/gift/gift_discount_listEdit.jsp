<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="giftLabelSvc" scope="page" class="com.giftLabel.model.GiftLabelService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>gift_listEdit.jsp</title>
<script src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.js"></script> 
<script type="text/javascript"  charset="UTF-8"  src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.full.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_listEdit.css">

</head>
<body>
	<div class="table-container giftAll">
		<table class="table table-filter gift-management">
			<tbody>
			<c:forEach var="gift" items="${giftDiscountEdits}">
				<c:set var="giftd_no" value="${gift.key.giftd_no}"></c:set>
				<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/giftDiscount.do">
				<tr>	
					<td class="giftd-edit">
						<div>
							<button type="submit" class='btn btn-warning btn-xs'>
								<span class="glyphicon glyphicon-upload"></span> Update
							</button>
							<input type="hidden" name="action" value="editDiscountGift">
							<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							<input type="hidden" name="whichPage"	value="<%=request.getParameter("whichPage")%>">
							
							<a href="<%=request.getContextPath()%>/gift/giftDiscount.do?action=searchGiftDiscounts&del_giftd_no=${gift.key.giftd_no}&whichPage=<%=request.getParameter("whichPage")%>">
							<button type="button" class='btn btn-default btn-xs gift-cancel'>
								<span class="glyphicon glyphicon-remove"></span> Cancel
							</button>
							</a>
						</div>
					</td>
					<td class="giftd-no">
							<p>${gift.key.giftd_no}</p>
							<input type="hidden" name="giftd_no" value="${gift.key.giftd_no}">
					</td>
					<td class="giftd-pic-1">
						<p>${gift.value.gift_no}</p>
						<p>${gift.value.gift_name}</p>
						<input type="hidden" name="gift_no" value="${gift.value.gift_no}">
					</td>
					<td class="giftd-pic-2">
						<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.value.gift_no}">
					</td>
					<td class="giftd-percent">
						<div class="errMsg">${giftEditMsgs[giftd_no]["giftd_percent"]}</div>
						<div>
							<input type="Number" name="giftd_percent" min="0" step="0.01"
							 value="${gift.key.giftd_percent}" ${(not empty giftEditMsgs[giftd_no]["giftd_percent"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
						</div>
					</td>
					<td class="giftd-timeRange">
						<div class="errMsg">${giftEditMsgs[giftd_no]["giftd_start"]}</div>
						<div>
							<input type="text" name="giftd_start" id="start_dateTime" 
								value="<fmt:formatDate value="${gift.key.giftd_start}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
								${(not empty giftEditMsgs[giftd_no]["giftd_start"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
						</div>
						<div>│</div>
						<div class="errMsg">${giftEditMsgs[giftd_no]["giftd_end"]}</div>
						<div>
							<input type="text" name="giftd_end" id="end_dateTime" 
								value="<fmt:formatDate value="${gift.key.giftd_end}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								${(not empty giftEditMsgs[giftd_no]["giftd_end"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
						</div>
					</td>
					<td class="giftd-amount">
					<div class="errMsg">${giftEditMsgs[giftd_no]["giftd_amount"]}</div>
						<div>
							<input type="Number" name="giftd_amount" min="0"
							 value="${gift.key.giftd_amount}" ${(not empty giftEditMsgs[giftd_no]["giftd_amount"]) ? 'style="background-color:rgb(255,150,120)"' : ''}>
						</div>
					</td>
					<td class="giftd-time">
						<div>
							<p></p>
						</div>
					</td>		
				</tr>
				</form>	
			</c:forEach>
			</tbody>
		</table>
	</div>	
</body>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh');
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
});
</script>
</html>