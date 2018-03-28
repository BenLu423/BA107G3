<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.gift.model.*" %>

<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
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

    
<div class="col-xs-12 col-sm-10 cont">
	<div class="row">
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="page-header"><h1> ����§�� </h1></div>
	</div>
	<div class="col-xs-12 col-sm-10 col-sm-offset-1">
		<div class="panel panel-default">
    		<div class="panel-body">
    			<div class="col-xs-12 col-sm-6 gift-search">
					<form>
						<span><input type="text" placeholder="�j�M§���N��/�W��/����" name="search" >
						<button type="submit" class="btn btn-default glyphicon glyphicon-search" ></button></span>
					</form>	    		
    			</div>
		        <div class="col-xs-12 col-sm-6 pull-right gift-menu">
		            <div class="btn-group pull-right">
		                <button type="button" class="btn btn-primary btn-filter" data-target="�|���W�[" id="never">�|���W�[</button>
		                <button type="button" class="btn btn-success btn-filter" data-target="�W�[��" id="added">�W�[��</button>
		                <button type="button" class="btn btn-danger btn-filter" data-target="�w�U�[" id="off">�w�U�[</button>
		                <button type="button" class="btn btn-default btn-filter" data-target="all" id="all">�Ҧ�§��</button>
		            </div>
		        </div>
				<div class="table-container giftAll gift-metadata">
        			<table>
        			<tbody>
        			<tr>
                		<td class="gift-edit"><div><p>§��</p></div></td>
                		<td class="gift-pic"><div>�Ϥ�</div></td>	
                		<td class="gift-no"><div>�W��</div></td>	
                		<td class="gift-label"><div>����</div></td>
                		<td class="gift-contents"><div>�ԭz</div></td>	
                		<td class="gift-price"><div>����</div></td>
                		<td class="gift-status"><div>���A</div></td>
                		<td class="gift-track"><div>�ʶR����</div></td>
                		<td class="gift-buy"><div>�l�ܤH��</div></td>
                		<td class="gift-discount"><% //<div>�O�_��</div><div>�����u�f</div> %></td>	
                	</tr>	
                	</tbody>
        			</table>
        		</div>
        		<div class="table-container giftAll">
            		<table class="table table-filter gift-management">
                	<tbody>
                	<c:set var="statusList" value="�|���W�[,�W�[��,�w�U�["/>
                	
                	<c:forEach var="gift" items="${giftSvc.giftAll}">
                    <tr data-status="${gift.key.gift_is_on}">                  	
                    	<td class="gift-edit">
                    		<div>
                    			<a class='btn btn-info btn-xs' href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a>
                    		</div>
                    		<div>
                    			<a class='btn btn-danger btn-xs' href="#"><span class="glyphicon glyphicon-remove"></span> Delete</a>
      						</div>
                    	</td>

                        <td class="gift-pic">
                            <div>
                                	<img src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.key.gift_no}">
                            </div>
                        </td>
                        <td class="gift-no">
                        	<div>
                        		<h2>${gift.key.gift_no}</h2>
                                <h3>${gift.key.gift_name}</h3>
                        	</div>
                        </td>
                        <td class="gift-label">
                        	<div>
                        		<c:forEach var="label" items="${gift.value}">
                        			<p><u>${label.giftl_name}</u></p>
                        		</c:forEach>
                        	</div>
                        </td>                        
                       	<td class="gift-contents">
                            <div>
                                <p>${gift.key.gift_cnt}</p>
                            </div>
                        </td>
                        <td class="gift-price">
                        	<div>
	                        	<p>$${gift.key.gift_price}</p>
	                        </div>
                        </td>
                        <td class="gift-status">
                        	<div class="never btn-style">
                        		<c:forTokens  var="status" items="${statusList}" delims=",">
                        			<c:choose>
                        				<c:when test="${status == gift.key.gift_is_on}">
                        					<button type="button" class="btn currentlyStatus">${status}</button>
                        				</c:when>
                        				<c:otherwise>
                        					<button type="button" class="btn" style="display: none">${status}</button>
                        				</c:otherwise>
                        			</c:choose>
                        		</c:forTokens>
                        	</div>
                        </td>
                        <td class="gift-track">
                        	<div>
                        		<p>${gift.key.gift_track_qty}��</p>
                        	</div>
                        </td>
                        <td class="gift-buy">
                        	<div>
                        		<p>${gift.key.gift_buy_qty}�H</p>
                        	</div>
                        </td>   
                        <td class="gift-discount">
                        	<% /* <div>
                        		<p>YES</p>
                        	</div> */%>
                        </td>                        
                    </tr>
                    </c:forEach>
                	</tbody>
            		</table>
            		<div class="col-xs-12 col-sm-12">
            			<div align="center">
			                <ul class="pagination">
			                	<li><a href="#">&laquo;</a></li>
			                	<li class="active"><a href="#">1</a></li>
			                	<li class=""><a href="#">2</a></li>
			                	<li class=""><a href="#">3</a></li>
			                	<li class=""><a href="#">...</a></li>
			                	<li class=""><a href="#">8</a></li>
			                	<li class=""><a href="#">9</a></li>
			                	<li class=""><a href="#">10</a></li>
			                	<li><a href="#">&raquo;</a></li>
			                </ul>
				        </div>
            		</div>
        		</div>
    		</div>
		</div>								
	</div>
	</div>
</div>	
	
<style>

button[id=added]{
	background-color: #FFF;
	color: green;
	font-weight: bolder;
}
button[id=off]{
	background-color: #FFF;
	color: red;
	font-weight: bolder;
}
button[id=all]{
	background-color: #FFF;
	color: black;
	border: 1px solid black;
}

button[id=all]:hover{
	background-color: #000;
	color: white;
}
</style>	
</body>
</html>