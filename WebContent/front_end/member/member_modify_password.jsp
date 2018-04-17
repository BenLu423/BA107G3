<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

				<form action="<%=request.getContextPath()%>/member/mem.do"  method="POST" class="set-form">          
      				 		  
      				 		  <div class="form-group">
                            <label for="old_pass">舊密碼:</label>
                            <input type="text" name="old_pass" id="old_pass" size="25" value="">
                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_old}</span></c:if>  
                          </div>

     						  <div class="form-group">
                            <label for="new_pass">新密碼:</label>
                            <input type="text" name="new_pass" id="new_pass" size="25" value="">
                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_new}</span></c:if>
                          </div>

                          <div class="form-group">
                            <label for="new_pass">新密碼:</label>
                            <input type="text" name="new_pass_s" id="new_pass_s" size="25" value="">
                          </div>


                         <div>  
                           <input type="submit" class="btn btn-info set-login-btn" value="提交">
                         </div>
                         
                         <div>
                         	<input type="hidden" name="mem_no" value="${memSelf.mem_no}">
                            <input type="hidden" name="action" value = "getmodify_password">
                         </div>          	
        		</form>


</body>
</html>