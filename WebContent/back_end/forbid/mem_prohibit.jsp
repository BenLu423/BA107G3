
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
  								<option value="">檢舉狀態</option>
  								<option value="全部">全部</option>
  								<option value="待審核">待審核</option>
  								<option value="已封鎖">已封鎖</option>
							</select>
						</div>
						<div class="form-group">
						<button class="btn btn-default form-control" type="submit">查詢</button>
						<input type="hidden" name="action" value="get_prohibit">
						</div>
					</form>
    			</div>
    			
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">

		        </div>
			<%--可以執行但有時會莫名其妙顯示錯誤 --%>
        	<%@ include file="pages/page1.file" %>		
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
		        			<a href="<%=request.getContextPath()%>/back_end/forbid/getone_mem_prohibit.jsp?self=${arlist.mem_no_self}&other=${arlist.mem_no_other}" class="btn btn-default">修改</a>
							</td>
	        			<form method="POST" action="<%=request.getContextPath()%>/accpre/accpre.do">
		        			<td><button type="submit" class="btn">刪除</button></td>
		        			<input type="hidden" name="self" value="${arlist.mem_no_self}">
		        			<input type="hidden" name="other" value="${arlist.mem_no_other}">
		        			<input type="hidden" name="action" value="delete">
		        			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			   					 <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
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
		        			<a href="<%=request.getContextPath()%>/back_end/forbid/getone_mem_prohibit.jsp?self=${arlist.mem_no_self}&other=${arlist.mem_no_other}" class="btn btn-default">修改</a>
							</td>
	        			<form method="POST" action="<%=request.getContextPath()%>/accpre/accpre.do">
		        			<td><button type="submit" class="btn">刪除</button></td>
		        			<input type="hidden" name="self" value="${arlist.mem_no_self}">
		        			<input type="hidden" name="other" value="${arlist.mem_no_other}">
		        			<input type="hidden" name="action" value="delete">
	        			</form>
        				</tr>
        			</c:forEach>
        			<%--@ include file="pages/pageB2.file" --%>
        			 <b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber%></font>筆</b>	
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