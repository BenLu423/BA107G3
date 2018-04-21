<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="giftLabelSvc" scope="page" class="com.giftLabel.model.GiftLabelService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>後端禮物管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/custombox/custombox.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.css" />
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_listEdit.css">  
</head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>
<% //因有使用到jquery-1.11.1，故須放在header.jsp之下   %>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.min.js"></script>
<script src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.js"></script> 
<script type="text/javascript"  charset="UTF-8"  src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.full.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/imgcheckbox/jquery.imgcheckbox.js"></script>
<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.min.js"></script>
<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.legacy.min.js"></script>

<div class="col-xs-12 col-sm-10 cont gift-cont">
	<div class="row">
    <%-- Exception資訊 --%>
    ${errorMsgs.Exception}
	<div class="col-xs-12 col-sm-10 col-sm-offset-1 gift-add">	
		<div class="col-xs-12 col-sm-12">
		<table style="font-size:30px;">
			<tr style="height:70px;">
				<td>優惠開始時段：</td>
				<td>
					<input type="text" name="giftd_start" id="start_dateTime">至
					<input type="text" name="giftd_end" id="end_dateTime">
				</td>
			</tr>
			<tr style="height:70px;">
				<td>折扣數：</td>
				<td>
					<input type="Number" name="giftd_percent" min="0" step="0.01" id="giftd_percent">
				</td>
			</tr>
			<tr style="height:70px;">
				<td>數量：</td>
				<td>
					<input type="Number" name="giftd_amount" min="0" style="margin-right:60px;"  id="giftd_amount">
					<button type="button" id="addGD">新增限時優惠禮物</button>
				</td>
			</tr>
		</table>
		</div>
  		<div class="col-xs-12 col-sm-8 gift-search">
		<%-- Exception資訊 --%>
		${errorMsgs.Exception}
		<form class="form-inline">
			<div class="form-group">
				<input type="text" name ="keyword" class="form-control" placeholder="輸入要查詢的禮物名稱" id="searchContext" style="margin-left:15px;">
			</div>
		</form>
		</div>	
	
	<div class="col-xs-12 col-sm-12" id="giftPic">
	</div>
	</div>
	<div class="col-xs-12 col-sm-1">
		<a href="<%=request.getContextPath()%>/gift/gift.do?action=gift_discount_show">
			<img src="<%=request.getContextPath()%>/back_end/res/img/gift/backPage.ico" alt="Add" id="backPage">
		</a>
	</div>	
	</div>
</div>
</body>
<style>
.custombox-lock{
	overflow:inherit;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	searchGifts();
	
	$('body').on('change', '#searchContext',function() {
		searchGifts();
	});
	var successModal = new Custombox.modal({
		content: {
// 			effect: 'Slide',
			overlay: false,
	    	target: '#successGift'
		},
		overlay: {
			active: false,
		  }
	});
	var i = "0";
	$('body').on('click', '#addGD',function() {
		var start_dateTime = $('#start_dateTime')[0].value;
		var end_dateTime = $('#end_dateTime')[0].value;
		var giftd_percent = $('#giftd_percent')[0].value;
		var giftd_amount = $('#giftd_amount')[0].value;
		var obj = [{name: "action",value: "addGD"},
		  		   {name: "start_dateTime",value: start_dateTime},
		  		   {name: "end_dateTime"  ,value: end_dateTime},
		  		   {name: "giftd_percent" ,value: giftd_percent},
		  		   {name: "giftd_amount"  ,value: giftd_amount}];
		$('#giftPic').find('span').each(function (index) {
			if($(this).attr('class').match('imgChked')){
				var text = $(this)[0].innerHTML;
				var gift_no = text.substring(55,59);
				obj[obj.length] = {name: "gift_no", value: gift_no};
			}
       	});
		$.ajax({
			type: 'POST',
			url: '/BA107G3/gift/giftUTF8.do',
			data: obj,
			dataType: 'json',
			success: (function(json) {
				if(json.status=='success'){
					window.location.href = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/back_end/gift/gift_discount.jsp';
				}else if(json.status=='failure'){
					console.log(json);
					if(json.giftd_start != null){
						$('#start_dateTime')[0].placeholder = json.giftd_start;
					}
					if(json.giftd_end != null){
						$('#end_dateTime')[0].placeholder = json.giftd_end;
					}
					if(json.giftd_percent != null){
						$('#giftd_percent')[0].placeholder = json.giftd_percent;
					}
					if(json.giftd_amount != null){
						$('#giftd_amount')[0].placeholder = json.giftd_amount;
					}
				}
			}),
			error:(function() { console.log("second error"); })
		}); 
	});
	$.datetimepicker.setLocale('zh');
	$(function(){
		 $('#start_dateTime').datetimepicker({
		  format:'Y-m-d H:i',
		  onShow:function(){
		   this.setOptions({
			minDate:new Date(),  
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
});	
function searchGifts(){
	var obj = $('form').serializeArray();
	obj[obj.length] = {name: "action",  value: "searchAllGift"};   
	$.ajax({
		type: 'POST',
		url: '/BA107G3/gift/giftUTF8.do',
		data: obj,
		dataType: 'json',
		success: (function(json) {
			$('#giftPic')[0].innerHTML = "";
			for(var i=0; i<json.length; i++){
				var img = $('<img>').attr('src','${pageContext.request.contextPath}/DBGifReader4?table=GIFT&gift_no='+json[i].gift_no);
				img.css('width','150px').css('height','150px');
				$('#giftPic').append(img);
				img.imgCheckbox({
					"styles": {
						"span.imgCheckbox img": {
							"border-radius": "4px"
						},
						"span.imgCheckbox.imgChked img": {
							"transform": "scale(0.9)",
						},
						"span.imgCheckbox": {
							"border": "none",
						}
					},
					"checkMarkSize": "50px",
					"graySelected" : true
				});
			}
		}),
		error:(function() { console.log("second error"); })
	}); 
}
</script>
</html>