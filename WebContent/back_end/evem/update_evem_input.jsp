<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.evem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  EvemVO evemVO = (EvemVO) request.getAttribute("evemVO");
  request.setAttribute("evemVO",evemVO);
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
					 <h7>update_evem_input.jsp</h7>
					
				</td></tr>
			</table>
			
			<center><font  color="#B5090B" face="Microsoft JhengHei" size="14"><i>�d���^��:</i></font></center>
			
			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">�Эץ��H�U���~:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			
			<FORM METHOD="post" ACTION="evem.do" name="form1">
			<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th><span style="font-family:Microsoft JhengHei;">����</span></th>
									<th><span style="font-family:Microsoft JhengHei;">���e</span></th>
								</tr>
							</thead>
							<tbody>
								
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">���ʯd���s��:</span></font></td>
					<td><font size="6">${evemVO.evemes_no}</font></td>
				</tr>
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">���ʯd�����:</span></font></td>
					<td><font size="6">${evemVO.evemes_time}</font></td>
				</tr>
					<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">�d�����e:</span></font></td>
					<td><font size="6">${evemVO.evemes_cnt}</font></td>
				</tr>
				
				<tr>
					<td><font size="14"><span style="font-family:Microsoft JhengHei;">�^�Яd�����e:</span></font></td>
					<td><font size="6"><input type="text" name="evemes_rescnt" value="<%=evemVO.getEvemes_rescnt()%>" /></font></td>
				</tr>
			</table>
							</tbody>
						
					</div>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="evemes_no" value="<%=evemVO.getEvemes_no()%>">
					<input type="submit" value="�e�X�^��"></FORM>
					<div id="mbutton">���_�p���s</div>
			</div>
		</div>
		</body>
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
<% 
		  java.sql.Timestamp evemes_restime = null;
		  try {
			  evemes_restime = evemVO.getEvemes_restime();
		   } catch (Exception e) {
			   evemes_restime = new java.sql.Timestamp(System.currentTimeMillis());
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
 	       format:'Y-m-d'        //format:'Y-m-d H:i:s',
<%--  		   value: '<%=eventVO.getEve_start()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_end()%>', // value:   new Date(), --%>
<%--  		   value: '<%=eventVO.getEve_time()%>', // value:   new Date(), --%>
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
  
        
</script>

<script>
							$(document).ready(function() {
							$("#mbutton").click(function() {
								 	$("input[name='evemes_rescnt']").val("��....�A�Q�Q�A��?");
								
									});
							});
</script>

</html>
	   		