<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.account_report.model.*"%>
<%@ page import="java.util.*"%>

<%
AccountReportService reportSvc = new AccountReportService();
List<AccountReportVO> accountReport = reportSvc.findallStatement("�ݼf��");
pageContext.setAttribute("reportCount",accountReport.size());

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>toast��x�޲z</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-10 cont">

		<div class="col-xs-12 col-sm-4">
			<div class="page-header">
				<h1>�m���i�n</h1>
				<div class="pre-scrollable"
					style="background-color: #ddd; font-size: 20px; max-height: 250px;padding:10px">
					�� �w��s�����K�K�[�J�I<br> �� �s�W�C��C�i����<br> �� �s�W�C�뭭��§��<br> ��
					�������|�ƶq20��<br> �� 3/14�զⱡ�H�`���ʶ��Q����<br> �� �s�W�զⱡ�H�`����§��<br>
					�� 2/14���H�`���ʶ��Q����<br> �� �s�W���H�`���w§��<br> �� �s�W�T��զⱡ�H�`����<br>

				</div>
			</div>
		</div><br>
		
		
		
		
		<div>
			<table id="indext-table" class="table table-bordered" style="width:200px;margin-top:60px">
			<tr>
			<th><font style="font-size:25px">�ݳB�z���|<br><span class="glyphicon glyphicon-alert"></span></font></th>
			</tr>
			<tr>
			<td><a href="<%=request.getContextPath()%>/back_end/forbid/mem_prohibit.jsp">�ثe��${reportCount}��</a></td>
			
			</tr>
			
			</table>
			
			
		</div>
		
	</div>
	
	
	



	


</body>
</html>