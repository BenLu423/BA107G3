<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.account_report.model.*"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>帳號檢舉管理</title>
    </head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>

<% 
	
	String self = (String)request.getParameter("self");
	String other = (String)request.getParameter("other");
	
	AccountReportVO arvo = null;
	AccountReportService ars = new AccountReportService();
	arvo = ars.getOneMem(self, other);
	request.setAttribute("arvo", arvo);
	
	
%>
		
<div class="col-xs-12 col-sm-10 cont gift-cont">
	<div class="row">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="col-xs-12 col-sm-12">
		<div class="panel panel-default">
    		<div class="panel-body">
    			<div class="col-xs-12 col-sm-8">

				
					<form method="post" action="<%=request.getContextPath()%>/accpre/accpre.do" class="form-inline">
						<div class="form-group"> 
						
						</div>
						<div class="form-group">
						
						</div>
						<div class="form-group">
					
				
				
						</div>
					</form>
    			</div>
    			
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">

		        </div>
			
        			
        	<table class="table table-hover" style="width:100%">
			<thead>
				<tr>
					<th class="MEM_NO_SELF">檢舉人編號</th>
					<th class="MEM_NO_OTHER">被檢舉人編號</th>
					<th class="ACCREP_REASON">檢舉理由</th>
					<th class="ACCREP_CNT">檢舉內容</th>
					<th class="ACCREP_TIME">檢舉日期</th>
					<th class="ACCREP_PERMIT">檢舉狀態</th>
				</tr>
			</thead>
		
			<tbody>
					
        				<tr>
        			
	        			<td>${arvo.mem_no_self}</td>
	        			<td>${arvo.mem_no_other}</td>
	        			<td>${arvo.accrep_reason}</td>
	        			<td>${arvo.accrep_cnt}</td>
	        			<td>${arvo.accrep_time}</td>
	        			
	        			<form action="<%=request.getContextPath()%>/accpre/accpre.do" method="POST">			
	        			<td>
	        			<select name="permit">
	        				<option value="${arvo.accrep_permit}" selected>請選擇:</option>
	        				<option value="待審核">待審核</option>
	        				<option value="已封鎖">已封鎖</option>
	        			</select>
	        			</td>
	        			
	        			<td>	
	        			<button type="submit" class="btn">確定</button>
	        			</td>
	        			<input type="hidden" name="self" value="${arvo.mem_no_self}">
	        			<input type="hidden" name="other" value="${arvo.mem_no_other}">
	        			<input type="hidden" name="action" value="update">
	        			</form>	
			</tbody>
		</table>
   
				
				
    		</div>
		</div>	
		</div>							
	</div>
	<div class="col-xs-12 col-sm-1">
		
	</div>
	</div>
</div>	
	
</body>
</html>