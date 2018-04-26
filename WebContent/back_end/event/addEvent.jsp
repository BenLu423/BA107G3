<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*"%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��Ʒs�W - addEvent.jsp</title>

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
		 <h4><a href="event_title.jsp">�^����</a></h4>
<div class="col-xs-12 col-sm-10 cont">
	   		
	<div style="border-width:6px;border-style:double;border-color:#000; width:1200px;padding:7px;margin:150px;background: rgba(255,220,220,1);">
	
   

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="event.do" enctype="multipart/form-data">
<table style="table-layout:fixed;">

	<tr>
		<td style="word-wrap:break-word;">���ʤ����W��:</td>
		<td><input type="TEXT" name="evec_no" size="45"
			 value="<%= (eventVO==null)? "EC001" : eventVO.getEvec_no()%>" /></td>
	</tr>
		<tr>
		<td style="word-wrap:break-word;">���ʦW��:</td>
		<td><input type="TEXT" name="eve_name" size="45"
			 value="<%= (eventVO==null)? "���~�D�D��" : eventVO.getEvec_no()%>" /></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�}�l���W�ɶ�:</td>
		<td><input name="eve_start" id="f_date1" type="text"></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�������W�ɶ�:</td>
		<td><input name="eve_end" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">�}�l���W�ɶ�:</td>
		<td><input name="eve_time" id="f_date3" type="text"></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">���ʸԱ�:</td>
		<td><input type="TEXT" name="eve_cnt" size="45"
			 value="<%= (eventVO==null)? "���d�ǹC�Ǹg�窺�z" : eventVO.getEve_cnt()%>" /></td>
	</tr>

	
	<tr>
		<td style="word-wrap:break-word;">���ʦW�B:</td>
		<td><input type="TEXT" name="eve_quota" size="45" 
			 value="<%= (eventVO==null)? "10" : eventVO.getEve_quota()%>" /></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">���ʦa�I:</td>
		<td><input type="TEXT" name="eve_site" size="45"
			 value="<%= (eventVO==null)? "�x��" : eventVO.getEve_site()%>" /></td>
	</tr><tr>
		<td style="word-wrap:break-word;">���ʳ��W�O��:</td>
		<td><input type="TEXT" name="eve_regfee" size="45" 
			 value="<%= (eventVO==null)? "2000" : eventVO.getEve_regfee()%>" /></td>
	</tr>
	<tr>
		<td style="word-wrap:break-word;">���ʪ��A:</td>
		<td><input type="TEXT" name="eve_sts" size="45" value="<%= (eventVO==null)? "�W�[" :eventVO.getEve_sts()%>" /></td>
	</tr>
	
	<tr>
		<td>���|���Ϥ��ùw���W��:</td>
		<td>
		
		<input type="file" id="imgInp" name="eve_pic" size="45" />
		<img class="col-md-6" id="blah"  alt="�w���Ϥ���ܩ�" />
	
		
		</form> <br />


</table>
<br>


<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</div>
</div>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

		<% 
		  java.sql.Timestamp eve_start  = null;
		  try {
			  eve_start = eventVO.getEve_start();
		   } catch (Exception e) {
			   eve_start = new java.sql.Timestamp(System.currentTimeMillis());
		   }
		%>
		<% 
		  java.sql.Timestamp eve_end = null;
		  try {
			  eve_end = eventVO.getEve_end();
		   } catch (Exception e) {
			   eve_end = new java.sql.Timestamp(System.currentTimeMillis());
		   }
		%>
		<% 
		  java.sql.Timestamp eve_time = null;
		  try {
			  eve_time = eventVO.getEve_time();
		   } catch (Exception e) {
			   eve_time = new java.sql.Timestamp(System.currentTimeMillis());
		   }
		%>
        

       
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
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=eve_start%>', // value:   new Date(),
		 
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
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		 
		   value: '<%=eve_end%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		 
		   value: '<%=eve_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });


</script>
</html>