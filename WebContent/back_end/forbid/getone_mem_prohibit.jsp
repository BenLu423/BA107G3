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
	<title>�b�����|�޲z</title>
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
					<th class="MEM_NO_SELF">���|�H�s��</th>
					<th class="MEM_NO_OTHER">�Q���|�H�s��</th>
					<th class="ACCREP_REASON">���|�z��</th>
					<th class="ACCREP_CNT">���|���e</th>
					<th class="ACCREP_TIME">���|���</th>
					<th class="ACCREP_PERMIT">���|���A</th>
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
	        				<option value="${arvo.accrep_permit}" selected>�п��:</option>
	        				<option value="�ݼf��">�ݼf��</option>
	        				<option value="�w����">�w����</option>
	        			</select>
	        			</td>
	        			
	        			<td>	
	        			<button type="submit" class="btn">�T�w</button>
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