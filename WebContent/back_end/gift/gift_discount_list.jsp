<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>後端禮物管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 
</head>
<body>
	<div class="table-container giftAll">
		<%@ include file="giftPage1.file" %>
		<table class="table table-filter gift-management">
			<tbody>
				
				<c:forEach var="gift" items="${gifts}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td class="giftd-edit">
						<div>
							<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/gift/giftDiscount.do">
								<button class='btn btn-info btn-xs'>
									<span class="glyphicon glyphicon-edit"></span> Edit
								</button>
								<input type="hidden" name="action" value=searchGiftDiscounts>
								<input type="hidden" name="whichPage"	value="<%=whichPage%>">
								<input type="hidden" name="edit_giftd_no" value="${gift.key.giftd_no}">
							</form>
						</div>
					</td>
					<td class="giftd-no">
						<div>
							<p>${gift.key.giftd_no}</p>
						</div>
					</td>
					<td class="giftd-pic-1">
						<p>${gift.value.gift_no}</p>
						<p>${gift.value.gift_name}</p>
					</td>
					<td class="giftd-pic-2">
						<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.value.gift_no}">
					</td>
					<td class="giftd-percent">
						<div>
							<p>${gift.key.giftd_percent}</p>
						</div>
					</td>
					<td class="giftd-start">
						<div>
							<p><fmt:formatDate value="${gift.key.giftd_start}" pattern="yyyy-MM-dd"/></p>
							<p><fmt:formatDate value="${gift.key.giftd_start}" pattern="HH:mm:ss"/></p>
						</div>
					</td>
					<td class="giftd-end">
						<div>
							<p><fmt:formatDate value="${gift.key.giftd_end}" pattern="yyyy-MM-dd"/></p>
							<p><fmt:formatDate value="${gift.key.giftd_end}" pattern="HH:mm:ss"/></p>
						</div>
					</td>
					<td class="giftd-amount">
						<div>
							<p>${gift.key.giftd_amount}</p>
						</div>
					</td>
					<td class="time">
						<div>
							<p></p>
						</div>
					</td>					
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<%@ include file="giftPage2_disCount.file" %> 

		<p style="text-align:right;">頁數:<%=whichPage%> / <%=pageNumber%></p>	
		
	</div>
</body>
</html>