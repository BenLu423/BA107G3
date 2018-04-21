<%@page import="java.util.List"%>
<%@page import="com.qa.model.QaVO"%>
<%@page import="com.qa.model.QaService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>

<%
QaService qaSvc = new QaService();
List<QaVO> list = qaSvc.getAll();
pageContext.setAttribute("list",list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Toast</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
            
                          <div class="collapse navbar-collapse">
                        <nav class="menu menu--iris">
                        <ul class="nav navbar-nav menu__list">


        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-sm-offset-2">
                    <div class="page-header">
                        <div style="border-bottom-style:double; ">
                      <h1>�|���`�����D</h1>
                    </div>
                    <div style="height: 20px"></div>
         <div class="col-xs-12 col-sm-10 col-sm-offset-1">
         
         <%@ include file="page1.file" %> 
	
		
         <% int count = 1; %>
         
         
        <c:forEach var="qaVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        	<% count++; %>
<%--         		<% if(count%4 == 1) {%> --%>
        		<c:if test="<%=count%4 == 0%>">
                <div class="panel panel-danger">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                    ${qaVO.qa_cnt}
                   </div>
                </div>
                </c:if>
<%--                 <% } %> --%>
                <% if(count%4 == 1) {%>
                <div class="panel panel-success">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                    ${qaVO.qa_cnt}
                   </div>
                </div>
                <% } %>
                <% if(count%4 == 2) {%>
                 <div class="panel panel-info"> 
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                       ${qaVO.qa_cnt}
                        </div>
                </div>
                 <% } %>
                <% if(count%4 == 3) {%>
                <div class="panel panel-warning">
                   <div class="panel-heading">
                      <h3 class="panel-title">${qaVO.qa_title}</h3>
                   </div>
                   <div class="panel-body">
                     ${qaVO.qa_cnt}
                      </div>
                </div>
                 <% } %>
          </c:forEach>
          <%@ include file="page2.file" %>
<!--                 <div class="panel panel-success"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">�ڶ񪺳o�Ǹ�Ʒ|���|�X�h�ܡH</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                      �z�n�A�ڭ̤F�Ѥj�a��ӤH���p�θ�Ʀw������ߡA�z����Ƥw�z�L�ڭ̧������[�K�t�ΫO�@(�H1111�H�O�Ȧ��ݨD¾�̪��Y�ԺA�פ�ӳB�z)�C�P�ɡA�ڭ̤]�n�D�����޲z�H�����i�p�U���|�|����ơA���g�z�ӤH�P�N�A��L�|��(�]�tVIP�|��)�O�L�k���o�z���ԲӸ�ơA�ԲӸ�T�i�Ѿ\���p�v�O�@�F���C -->
<!--                    </div> -->
<!--                 </div> -->
<!--                 <div class="panel panel-info"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">��������U�ݭn�d�o��h��ơH</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                       �z�n�A�ڭ̬��F�ë��Y�Ԫ���W��{�ҡA�ݳz�L�z�ӤH��ƪ�����סA�T�O�C��|���ɦ����v�q�A����������ͧ󦳫O�١C�O�ѤF�A��͸�ƶ�o�V�ԲӡA�N�V�e�����j�a�{�ѧA�@�I -->
<!--                    </div> -->
<!--                 </div> -->
<!--                 <div class="panel panel-warning"> -->
<!--                    <div class="panel-heading"> -->
<!--                       <h3 class="panel-title">���ʦ��O�����e�O�H</h3> -->
<!--                    </div> -->
<!--                    <div class="panel-body"> -->
<!--                       �z�n�A���ʳ��W�O�̦a�I�覡���P�Ӥ��P�C�b�z�I��u��ͬ��ʡv��A�Y���}����W�������ʡA�z�i�b�e���W�ݨ�U�ɶ������γ��W�O�ΡA�̱z���ɶ��w��i����W��ú�O�Y�i�C�@�몺���ʶO�ά��b99~2500�������A�V���Ū����a�B�\���A�άO�ݹL�]���p�ˬ��ʶO�ά۹�����A���O��^�B��P�P�۳B�ɶ��]�۹���h�A�ҥi�ѱz�ۦ�D��C -->
<!--                    </div> -->
<!--                 </div> -->
                   
    
                </div>
            </div>
        </div>
    </ul>
 </nav>
</div> 
            
            
            
            
        </div>    
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>