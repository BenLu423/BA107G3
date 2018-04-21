<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.gift.model.*" %>
<%@ page import="com.giftLabel.model.*"%>
<%@ page import="java.util.*" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<title>���§���޲z</title>
    </head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>
<% //�]���ϥΨ�jquery-1.11.1�A�G����bheader.jsp���U   %>
<script type="text/javascript" src="<%=request.getContextPath()%>/back_end/js/gift_index.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/gift_index.css"> 

<div class="col-xs-12 col-sm-10 cont gift-cont">
	<div class="row">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="col-xs-12 col-sm-12">
		<div class="panel panel-default">
    		<div class="panel-body">

    			
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">
<!-- 		            <div class="btn-group pull-right"> -->
<!-- 		                <button type="button" class="btn btn-primary btn-filter" data-target="never">�|���W�[</button> -->
<!-- 		                <button type="button" class="btn btn-success btn-filter" data-target="added">�W�[��</button> -->
<!-- 		                <button type="button" class="btn btn-danger btn-filter" data-target="off" default>�w�U�[</button> -->
<!-- 		                <button type="button" class="btn btn-default btn-filter" data-target="all">�Ҧ�§��</button> -->
<!-- 		            </div> -->
		        </div>
				<div class="table-container giftAll gift-metadata">
<!-- 					<p style="color:red;font-size:20px;margin:0px;">��§������[�W�[��]�~�X�{�b§���ӫ�<p> -->
        			<table style="width:100%">
        			<tbody>
        			<tr>
                		<td class="giftd-edit"><div>�����u�f</div></td>
<!--                 		<td class="giftd-no"><div>�u�f�s��</div></td>	 -->
                		<td class="giftd-no"><div>§�����A</div></td>	
                		<td class="giftd-pic"><div>§���ԭz</div></td>	
                		<td class="giftd-percent"><div>�馩</div></td>
                		<td class="giftd-start"><div>�}�l�ɶ�</div></td>	
                		<td class="giftd-end"><div>�����ɶ�</div></td>
                		<td class="giftd-amount"><div>�Ѿl�ƶq</div></td>
                		<td class="giftd-time"><div>�Ѿl�ɶ�</div></td>
                	</tr>	
                	</tbody>
        			</table>
        		</div>
<%--         		<c:if test="${(empty giftDiscounts) and (empty giftDiscountQuery)}"> --%>
<%--         			<jsp:forward page="/gift/giftDiscount.do"> --%>
<%--         				<jsp:param name="action" value="searchGiftDiscounts" /> --%>
<%--         			</jsp:forward> --%>
<%-- 				</c:if> --%>
				<c:if test="${giftDiscountEdits != null}">
				<jsp:include page="gift_discount_listEdit.jsp"></jsp:include>
				</c:if>
				<hr style="width: 100%; height: 2px; background-color: black; margin: 10px 0px 0px 0px;">
				<jsp:include page="gift_discount_list.jsp"></jsp:include>
<%-- 				�ثe����:${gifts.size()} --%>
<%-- 				�קﵧ��:${giftEdits.size()} --%>
    		</div>
		</div>	
		</div>							
	</div>
	<div class="col-xs-12 col-sm-1">
		<a href="<%=request.getContextPath()%>/gift/gift.do?action=gift_discount_add&requestURL=<%=request.getServletPath()%>">
			<img src="<%=request.getContextPath()%>/back_end/res/img/gift/addPage.ico" alt="Add" id="addPage">
		</a>
	</div>
	</div>
</div>	
	
</body>
</html>