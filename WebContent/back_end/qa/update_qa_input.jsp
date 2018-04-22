<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  QaVO qaVO = (QaVO) request.getAttribute("qaVO");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	<script>console.log(qaVO)</script>
	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000; width:950px;padding:7px;margin:150px;background: rgba(255,220,220,1);;">
			
			<table id="table-1">
				<tr><td>
					 <h7>update_qa_input.jsp</h7>
					 <h4><a href="select_page1.jsp"></a></h4>
				</td></tr>
			</table>
			
			<center><font  color="#B5090B" face="Microsoft JhengHei" size="14"><i>常見問題資料修改:</i></font></center>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			
			<FORM METHOD="post" ACTION="qa.do" name="form1">
			<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th><span style="font-family:Microsoft JhengHei;">項目</span></th>
									<th><span style="font-family:Microsoft JhengHei;">內容</span></th>
								</tr>
							</thead>
							<tbody>
								
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">常見問題編號:</span></font></td>
					<td>${qaVO.qa_no}</td>
				</tr>
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">修改日期:</span></font></td>
					<td><input name="qa_date" id="f_date1" type="text" class="btn-basic btn-lg" ></td>
				</tr>
					<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">常見問題標題:</span></font></td>
					<td><input type="TEXT" name="qa_title" size="45" value="<%=qaVO.getQa_title()%>" class="btn-basic btn-lg"/></td>
				</tr>
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">常見問題內容:</span></font></td>
					<td><input type="TEXT" name="qa_cnt" size="45" value="<%=qaVO.getQa_cnt()%>" class="btn-basic btn-lg"/></td>
				</tr>
			</table>
								
								
								
								
								
							</tbody>
						
					</div>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="qa_no" value="<%=qaVO.getQa_no()%>">
					<input type="submit" value="送出修改"></FORM>
			</div>
		</div>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=qaVO.getQa_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>

</html>
	   		