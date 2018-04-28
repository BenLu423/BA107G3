<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.qa.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  QaVO qaVO = (QaVO) request.getAttribute("qaVO");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�s�W�`�����D</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">

            <div class="inner-bg">
                <div class="container">
                    <div class="row">
				        <h1><strong>�`�����D�޲z</strong> </h1>
				        <h4><a href="select_page1.jsp"></a></h4>
                            <div class="description">
                            	<p>
	                            	 <a href="#"><strong></strong></a>
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-10 col-sm-offset-1 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h2>�s�W�`�����D</h2>	
                        			 <h4><a href="select_page1.jsp">�^����</a></h4>
                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-envelope"></i>
	                        		</div>
                            </div>
                            	<div class="form-bottom contact-form">
				 <%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">�Эץ��H�U���~:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
									
								<FORM METHOD="post" ACTION="qa.do" name="form1">
			                    	
			                    	<div class="form-group"><p class="lead">�Z�n���</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_date"  class="contact-email form-control" id="f_date1">
			                        </div>
			                        <div class="form-group"><p class="lead">�`�����D���D</p>
			                        	<label class="sr-only" for="contact-subject"></label>
			                        	<input type="text" name="qa_title"  class="contact-subject form-control"
			                        	 value="<%= (qaVO==null)? "�b���n���i�h����H" : qaVO.getQa_title()%>" >
			                        </div>
			                        <div class="form-group"><p class="lead">�`�����D���e</p>
			                        	<label class="sr-only" for="contact-message"></label>
			                        	<input name="qa_cnt"  class="contact-message form-control"
			                        	 value="<%= (qaVO==null)? "�i��O�b���Q���|���� ,�лP�������H���p���C" : qaVO.getQa_cnt()%>">
			                        </div>
			                        <input type="hidden" name="action" value="insert">
			                         <input type="submit" value="�e�X�s�W">
			                   		</FORM>
			                   
			             
							</div>
                        </div>
                    </div>
                </div>
            </div>
        	
        </div>
        
          				
  </body>
  	<% 
							  java.sql.Date qa_date = null;
							  try {
								    qa_date = qaVO.getQa_date();
							   } catch (Exception e) {
								    qa_date = new java.sql.Date(System.currentTimeMillis());
							   }
							%>
							<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.datetimepicker.css" />
							<script src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.js"></script>
							<script src="<%=request.getContextPath()%>/back_end/datetimepicker/jquery.datetimepicker.full.js"></script>
							
							<style>
							  .xdsoft_datetimepicker .xdsoft_datepicker {
							           width:  300px;   /* width:  300px; */
							  }
							  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
							           height: 151px;   /* height:  151px; */
							  }
							</style>
							
							<script>
							        $.datetimepicker.setLocale('zh');
							        $('#f_date1').datetimepicker({
								       theme: '',              //theme: 'dark',
								       timepicker:false,       //timepicker:true,
								       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
								       format:'Y-m-d',         //format:'Y-m-d H:i:s',
									   value: '<%=qa_date%>', // value:   new Date(),
							           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
							           //startDate:	            '2017/07/10',  // �_�l��
							           //minDate:               '-1970-01-01', // �h������(���t)���e
							           //maxDate:               '+1970-01-01'  // �h������(���t)����
							        });
							        
							 </script>
  
</html>
