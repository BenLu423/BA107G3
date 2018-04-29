<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.account_report.model.*"%>
<%@ page import="java.util.*"%>

<%
AccountReportService reportSvc = new AccountReportService();
List<AccountReportVO> accountReport = reportSvc.findallStatement("待審核");
pageContext.setAttribute("reportCount",accountReport.size());

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>toast後台管理</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	<div class="col-xs-12 col-sm-10 cont">

		<div class="col-xs-12 col-sm-4">
			<div class="page-header">
				<h1>《公告》</h1>
				<div class="pre-scrollable"
					style="background-color: #ddd; font-size: 20px; max-height: 250px;padding:10px">
					● 歡迎新成員沛沛加入！<br> ● 新增七月七夕活動<br> ● 新增七月限時禮物<br> ●
					本月檢舉數量20筆<br> ● 3/14白色情人節活動順利落幕<br> ● 新增白色情人節限時禮物<br>
					● 2/14情人節活動順利落幕<br> ● 新增情人節限定禮物<br> ● 新增三月白色情人節活動<br>

				</div>
			</div>
		</div><br>
		
		
		
		
		<div>
			<table id="indext-table" class="table table-bordered" style="width:200px;margin-top:60px">
			<tr>
			<th><font style="font-size:25px">待處理檢舉<br><span class="glyphicon glyphicon-alert"></span></font></th>
			</tr>
			<tr>
			<td><a href="<%=request.getContextPath()%>/back_end/forbid/mem_prohibit.jsp">目前有${reportCount}筆</a></td>
			
			</tr>
			
			</table>
			
			
		</div>
		
	</div>
	
	
	



	


</body>
</html>