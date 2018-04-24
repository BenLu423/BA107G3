
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*" %>
<%@ page import="com.account_report.model.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title></title>
    </head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>


<% 
	request.getAttribute("statement");	

	AccountReportVO arvo = null;
	AccountReportService ars = new AccountReportService();
	List<AccountReportVO> arList = ars.getAll();
	request.setAttribute("arList", arList);
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
							<select class="form-control" id="selectWord" name="statement">
  								<option value="">���|���A</option>
  								<option value="����">����</option>
  								<option value="�ݼf��">�ݼf��</option>
  								<option value="�w����">�w����</option>
							</select>
						</div>
						<div class="form-group">
						<button class="btn btn-default form-control" type="submit">�d��</button>
						<input type="hidden" name="action" value="get_prohibit">
						</div>
					</form>
    			</div>
    			
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">

		        </div>
			<%--�i�H��������ɷ|���W�䧮��ܿ��~ --%>
        	<%@ include file="pages/page1.file" %>		
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
					<c:if test="${empty accreplist}">
					
						<c:forEach var="arlist" items="${arList}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">		
	        				<tr>
		        			<td>${arlist.mem_no_self}</td>
		        			<td>${arlist.mem_no_other}</td>
		        			<td>${arlist.accrep_reason}</td>
		        			<td>${arlist.accrep_cnt}</td>
		        			<td>${arlist.accrep_time}</td>
		        			<td>${arlist.accrep_permit}</td>
		        			<td>
		        			<a href="<%=request.getContextPath()%>/back_end/forbid/getone_mem_prohibit.jsp?self=${arlist.mem_no_self}&other=${arlist.mem_no_other}" class="btn btn-default">�ק�</a>
							</td>
	        			<form method="POST" action="<%=request.getContextPath()%>/accpre/accpre.do">
		        			<td><button type="submit" class="btn">�R��</button></td>
		        			<input type="hidden" name="self" value="${arlist.mem_no_self}">
		        			<input type="hidden" name="other" value="${arlist.mem_no_other}">
		        			<input type="hidden" name="action" value="delete">
		        			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			   					 <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
	        			</form>
        				</tr>
        			</c:forEach>
        			
  				 </c:if>
  					<c:if test="${not empty accreplist}">
  						<%--@ include file="pages/pageA1.file" --%>
						<c:forEach var="arlist" items="${accreplist}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">		
	        				<tr>
		        			<td>${arlist.mem_no_self}</td>
		        			<td>${arlist.mem_no_other}</td>
		        			<td>${arlist.accrep_reason}</td>
		        			<td>${arlist.accrep_cnt}</td>
		        			<td>${arlist.accrep_time}</td>
		        			<td>${arlist.accrep_permit}</td>
		        			<td>
		        			<a href="<%=request.getContextPath()%>/back_end/forbid/getone_mem_prohibit.jsp?self=${arlist.mem_no_self}&other=${arlist.mem_no_other}" class="btn btn-default">�ק�</a>
							</td>
	        			<form method="POST" action="<%=request.getContextPath()%>/accpre/accpre.do">
		        			<td><button type="submit" class="btn">�R��</button></td>
		        			<input type="hidden" name="self" value="${arlist.mem_no_self}">
		        			<input type="hidden" name="other" value="${arlist.mem_no_other}">
		        			<input type="hidden" name="action" value="delete">
	        			</form>
        				</tr>
        			</c:forEach>
        			<%--@ include file="pages/pageB2.file" --%>
        			 <b>���� �X �d �� �� �� �p �U �� ��: �@<font color=red><%=rowNumber%></font>��</b>	
  				 </c:if>
			</tbody>
		</table>
        <%@ include file="pages/page2.file" %>
				
				
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