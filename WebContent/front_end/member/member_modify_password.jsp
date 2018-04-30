<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<style type="text/css">

.set-table-pass tr td{
padding-bottom: 100px;
}
.panel-warning>.set-panel{
background: rgba(255,220,220);
}
</style>
</head>
<body>

				<form action="<%=request.getContextPath()%>/member/mem.do"  method="POST" class="set-form">          
      				 		  
<!--       				 	  <div class="form-group"> -->
<!--                             <label for="old_pass">舊密碼:</label> -->
<!--                             <input type="text" name="old_pass" id="old_pass" size="25" value=""> -->
<%--                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_old}</span></c:if>   --%>
<!--                           </div> -->

<!--      						  <div class="form-group"> -->
<!--                             <label for="new_pass">新密碼:</label> -->
<!--                             <input type="text" name="new_pass" id="new_pass" size="25" value=""> -->
<%--                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_new}</span></c:if> --%>
<!--                           </div> -->

<!--                           <div class="form-group"> -->
<!--                             <label for="new_pass">新密碼:</label> -->
<!--                             <input type="text" name="new_pass_s" id="new_pass_s" size="25" value=""> -->
<!--                           </div> -->


<!--                          <div>   -->
<!--                            <input type="submit" class="btn btn-info set-login-btn" value="提交"> -->
<!--                          </div> -->
                         
<!--                          <div> -->
<%--                          	<input type="hidden" name="mem_no" value="${memSelf.mem_no}"> --%>
<!--                             <input type="hidden" name="action" value = "getmodify_password"> -->
<!--                          </div>   -->
                         
                         
                         
                    <div class="panel panel-warning">
						<div class="panel-heading set-panel">
							<h3 class="panel-title">修改密碼</h3>
						</div>
						<table class="table table-hover set-table-pass">
							<tr > 
								<td>
								<div class="form-group">
	                            <label for="old_pass">舊密碼:</label>
	                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_old}</span></c:if>  
	                            <input type="text" name="old_pass" id="old_pass" value="" class="form-control">
	                           
	                        	</div>
	                        	</td>
                        	</tr>
							<tr>
								<td>
								<div class="form-group set-form-group">
								<label for="new_pass">新密碼:</label>
								 <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_new}</span></c:if>
	                            <input type="text" name="new_pass" id="new_pass" value="" class="form-control">
	                           
								</div>
								</td>
							</tr>
							<tr>
								<td>
								<div class="form-group set-form-group">
	                            <label for="new_pass">新密碼:</label>
	                            <input type="text" name="new_pass_s" id="new_pass_s" value="" class="form-control">
								</div>
								</td>
							</tr>
							<tr>
								<td>
								<div>
	                      		<input type="submit" class="btn btn-default set-login-btn" value="提交">
	                      		</div>
								</td>
							</tr>
							
						</table>
	                      		
					</div>
					<div>
							
                         	<input type="hidden" name="mem_no" value="${memSelf.mem_no}">
                            <input type="hidden" name="action" value = "getmodify_password">
                     </div> 

        		</form>


</body>
</html>