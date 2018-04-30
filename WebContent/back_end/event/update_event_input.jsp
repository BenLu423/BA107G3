<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO"); 
%>

<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���ʸ�ƭק�</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<!--�Ϥ���ܪ��ɮץ[�J  -->
<script type='text/javascript'
	src='http://code.jquery.com/jquery-1.7.2.min.js'></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front/js/calendar.css">
<script language="JavaScript"
	src="<%=request.getContextPath()%>/front/js/calendarcode.js"></script>

<script>$(function()
{
	$("#imgInp").change(function(){
		if (this.files && this.files[0]) {
			var reader = new FileReader();
			
			reader.onload = function (e) {
				$('#blah').attr('src', e.target.result);
			}
			
			reader.readAsDataURL(this.files[0]);
		}
	});
}) ;
</script>
<body bgcolor='white'>


         
	<jsp:include page="/back_end/header.jsp"></jsp:include>


		

<div class="col-xs-12 col-sm-10 cont">
	   		
	<div style="border-width:6px;border-style:double;border-color:#000; width:1200px;padding:7px;margin:150px;background: rgba(255,220,220,1);">
	
<h4><a href="event_title.jsp">�^����</a></h4>  
<h2>��ƭק�:</h2>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="event.do" name="form2" enctype="multipart/form-data">
<table  style="table-layout:fixed;">

	<tr>
		<td style="word-wrap:break-word;">���ʽs��:</td>
		<td><input type="TEXT" name="eve_no" size="45"	value="<%=eventVO.getEve_no()%>" /></td>
	</tr>
		<tr>
		<td style="word-wrap:break-word;">���ʤ����W��:</td>
		<td><input type="TEXT" name="evec_no" size="45"	value="<%=eventVO.getEvec_no()%>" /></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">���ʦW��:</td>
		<td><input type="TEXT" name="eve_name" size="45" value="<%=eventVO.getEve_name()%>" /></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�}�l���W�ɶ�:</td>
		<td>
			<input name="eve_start" id="f_date1" type="text" 
			value='<fmt:formatDate value="${eventVO.eve_start}" pattern="yyyy-MM-dd"/>'>
		</td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�������W�ɶ�:</td>
		<td>
			<input name="eve_end" id="f_date2" type="text" 
			value="<fmt:formatDate value="${eventVO.eve_end}" pattern="yyyy-MM-dd"/>">
		</td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�����|��ɶ�:</td>
		<td>	
			<input name="eve_time" id="f_date3" type="text" 
			value="<fmt:formatDate value="${eventVO.eve_time}" pattern="yyyy-MM-dd"/>">
		</td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">���ʸԱ�:</td>
		<td><input type="TEXT" name="eve_cnt" size="45"	value="<%=eventVO.getEve_cnt()%>" /></td>
	</tr>
	
	<tr>
		<td style="word-wrap:break-word;">���ʦW�B:</td>
		<td><input type="TEXT" name="eve_quota" size="45" value="<%=eventVO.getEve_quota()%>" /></td>
	</tr>
	<tr>
		<td>���ʦa�I:</td>
		<td><input type="TEXT" name="eve_site" size="45" value="<%=eventVO.getEve_site()%>" /></td>
	</tr>
	<tr>
		<td>���ʳ��W�O��:</td>
		<td><input type="TEXT" name="eve_regfee" size="45" value="<%=eventVO.getEve_regfee()%>" /></td>
	</tr>
	<tr>
		<td>���ʪ��A:</td>
		<td><input type="TEXT" name="eve_sts" size="45" value="<%=eventVO.getEve_sts()%>" /></td>
	</tr>
<tr>
		<td>�ثe�Ϥ�:</td>
		<td><img class="col-md-5" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}">
		</td>
	</tr>
	<tr>
		<td>���|���Ϥ��ùw���W��:</td>
		<td>
		<form id="form2">
		<input type="file" id="imgInp" name="eve_pic" size="45" />
		<img class="col-md-6" id="blah"  alt="�w���Ϥ���ܩ�" />
	<!-- 	<img id="blah" src="DBGifReader402?pag_no=" alt="your image" /> -->
	<!-- 	<img id="blah" src="#" alt="your image" /> -->
		
		</form> <br />
		</td>
	
</tr>
</table>

<br>
<input type="hidden" name="action" value="update2">
<input type="hidden" name="eve_no" value="<%=eventVO.getEve_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</div>
</div>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.js"></script> 
<script type="text/javascript"  charset="UTF-8"  src="<%=request.getContextPath()%>/back_end/js/datetimepicker/jquery.datetimepicker.full.js"></script> 

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
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d'        //format:'Y-m-d H:i:s',
<%--  		   value: '<%=eventVO.getEve_start()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_end()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_time()%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d'         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=eventVO.getEve_start()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_end()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_time()%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        }); $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
            theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d'         //format:'Y-m-d H:i:s',
<%--   		   value: '<%=eventVO.getEve_start()%>', // value:   new Date(), --%>
<%--   		   value: '<%=eventVO.getEve_end()%>', // value:   new Date(), --%>
<%--   		   value: '<%=eventVO.getEve_time()%>', // value:   new Date(), --%>
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
            //startDate:	            '2017/07/10',  // �_�l��
            //minDate:               '-1970-01-01', // �h������(���t)���e
            //maxDate:               '+1970-01-01'  // �h������(���t)����
         });
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
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

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
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


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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
<script src="<%=request.getContextPath()%>/front/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/jquery.scrollUp.min.js"></script>
<script src="<%=request.getContextPath()%>/front/js/price-range.js"></script>
<script
	src="<%=request.getContextPath()%>/front/js/jquery.prettyPhoto.js"></script>
<script src="<%=request.getContextPath()%>/front/js/main.js"></script>

</html>