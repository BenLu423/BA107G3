<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.event.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  EventVO eventVO = (EventVO) request.getAttribute("eventVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>��@����</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	
<div class="col-xs-4 col-sm-4 cont">
	   		

<div style="height: 70px"></div>
<div class="container">
	<div class="col-12" >
		<div style=border-style:double;background:rgba(255,220,220,1);width:1500px>
			
			 <h4><a href="event_title.jsp">�^����</a></h4><br>
			 <h3>��@����</h3><br>
			<table>
				<tr>
					<th style="width:300px;"><font size="5">���ʽs��</font></th>
					<th style="width:300px;"><font size="5">�����s��</font></th>
					<th style="width:250px;"><font size="5">���ʦW��</font></th>
					<th style="width:170px;"><font size="5">�}�l�ɶ�</font></th>
					<th style="width:170px;"><font size="5">�����ɶ�</font></th>
					
					<th style="width:400px;"><font size="5">�Ա�</font></th>
					<th style="width:170px;"><font size="5">�Ϥ�</font></th>
					<th style="width:170px;"><font size="5">�W�B</font></th>
					<th style="width:170px;"><font size="5">�a�I</font></th>
					<th style="width:170px;"><font size="5">�O��</font></th>
					<th style="width:170px;"><font size="5">���A</font></th>
				</tr>
				<tr>
					
		      		<td><font size="5">${eventVO.eve_no}</font></td>
					<td><font size="5">${eventVO.evec_no}</font></td>
					<td><font size="5">${eventVO.eve_name}</font></td>
					<td><font size="5">${eventVO.eve_start}</font></td>
					<td><font size="5">${eventVO.eve_end}</font></td>
					<td style="width:150px;"><div style="overflow: hidden;text-overflow:ellipsis;white-space: nowrap;font-size:5px">${eventVO.eve_cnt}</div></td>
					<td><img style="width:150px;" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}"></td>
					<td><font size="5">${eventVO.eve_quota}</font></td>
					<td><font size="5">${eventVO.eve_site}</font></td>
					<td><font size="5">${eventVO.eve_regfee}</font></td>
					<td><font size="5">${eventVO.eve_sts}</font></td>
			
				</tr>
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>