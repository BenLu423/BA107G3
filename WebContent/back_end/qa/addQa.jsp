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
<title>新增常見問題</title>
</head>
<body>
	<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	   		<div class="col-xs-12 col-sm-10 cont">
	   		
	   		<div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">

            <div class="inner-bg">
                <div class="container">
                    <div class="row">
				        <h1><strong>常見問題管理</strong> </h1>
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
                        			<h2>新增常見問題</h2>	
                        			 <h4><a href="select_page1.jsp">回首頁</a></h4>
                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-envelope"></i>
	                        		</div>
                            </div>
                            	<div class="form-bottom contact-form">
				 <%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color:red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
									
								<FORM METHOD="post" ACTION="qa.do" name="form1">
			                    	
			                    	<div class="form-group"><p class="lead">刊登日期</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_date"  class="contact-email form-control" id="f_date1">
			                        </div>
			                        <div class="form-group"><p class="lead">常見問題標題</p>
			                        	<label class="sr-only" for="contact-subject"></label>
			                        	<input type="text" name="qa_title"  class="contact-subject form-control"
			                        	 value="<%= (qaVO==null)? "帳號登不進去怎麼辦？" : qaVO.getQa_title()%>" >
			                        </div>
			                        <div class="form-group"><p class="lead">常見問題內容</p>
			                        	<label class="sr-only" for="contact-message"></label>
			                        	<input name="qa_cnt"  class="contact-message form-control"
			                        	 value="<%= (qaVO==null)? "可能是帳號被檢舉封鎖 ,請與本網頁人員聯絡。" : qaVO.getQa_cnt()%>">
			                        </div>
			                        <input type="hidden" name="action" value="insert">
			                         <input type="submit" value="送出新增">
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
								       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
								       format:'Y-m-d',         //format:'Y-m-d H:i:s',
									   value: '<%=qa_date%>', // value:   new Date(),
							           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
							           //startDate:	            '2017/07/10',  // 起始日
							           //minDate:               '-1970-01-01', // 去除今日(不含)之前
							           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
							        });
							        
							 </script>
  
</html>
