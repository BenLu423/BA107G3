<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
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
               <div style="border-width:6px;border-style:double;border-color:#000;padding:7px;margin:150px;background: rgba(255,220,220,1);">	 
          <FORM METHOD="post" ACTION="qa.do" name="form1">
			                      	
			                 
			                    	<div class="form-group"><p class="lead">會員帳號</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_account}">
			                        </div>
			                        <div class="form-group"><p class="lead">會員姓名</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_name}">
			                        </div>
			                        <div class="form-group"><p class="lead">性別</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_gender}">
			                        </div>
			                        <div class="form-group"><p class="lead">生日</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_birthday}">
			                        </div>
			                        <div class="form-group"><p class="lead">地址</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_county}">
			                        </div>
			                        
			                         <div class="form-group"><p class="lead">身高</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_height}">
			                        </div>
			                         <div class="form-group"><p class="lead">體重</p>
			                    		<label class="sr-only" for="contact-email"></label>
			                        	<input type="text" name="qa_no" class="contact-email form-control" 
			                        	 value="${memSelf.mem_weight}">
			                        </div>
			                        
			                        
			                        
			                         <input type="hidden" name="action" value="insert">
			                         <input type="submit" value="送出新增">
			                   		</FORM>
			                      
			                        </div>
			                        
<!-- 			              <         
			                   		</FORM> 
<!--            <table> -->

<!-- 	<tr> -->
<%-- 			<td>${memSelf.mem_no}</td> --%>
<%-- 			<td>${memSelf.mem_account}</td> --%>
<%-- 			<td>${memSelf.mem_name}</td> --%>
<%-- 			<td>${memSelf.mem_gender}</td> --%>
<%-- 			<td>${memSelf.mem_birthday}</td> --%>
<%-- 			<td>${memSelf.mem_county}</td> --%>
<%-- 			<td>${memSelf.mem_height}</td>	 --%>
<%-- 			<td>${memSelf.mem_weight}</td>		 --%>
		
<!-- 	</tr> -->
<!-- </table> -->
           
           
           
        </div>    
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>

		
