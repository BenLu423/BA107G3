<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>

    <title>µn¤J­¶­±</title>

    

    <style type="text/css">
    .set-login{
        background-color: rgb(255,220,220);
        min-height: 270px;
        margin-top: 6%;
        
    	padding-bottom : 15px;

    }
    .set-login-btn{
      width: 100%;
	  
    }


    </style>
  
  </head>
    <body>
	
	<!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
	<!-- //header-->

        <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
               	  <div class="col-xs-12 col-sm-4 col-sm-offset-4 set-login">
       				<div><h3>µn¤J</h3></div>
       				<hr>
                      <div>
                      <form action="<%=request.getContextPath()%>/member/mem.do" method="POST" class="set-form">
                          <div class="form-group">
                            <label for="mem_account">±b¸¹</label>
                            	<%-- ¿ù»~ªí¦C --%>
								  <c:if test="${not empty errorMsgs}">
                           			  <p style="color:red">${errorMsgs.account}</p>
                         		  </c:if> 
								
                            
                            <input type="text" name="mem_account" id="mem_account" class="form-control">
                          </div>

                          <div class="form-group">
                            <label for="mem_password">±K½X</label>
                             <%-- ¿ù»~ªí¦C --%>
                             <c:if test="${not empty errorMsgs}">
                             			<p style="color:red">${errorMsgs.password}</p>	
                             </c:if>
                            <input type="password" name="mem_password" id="mem_password" class="form-control">
                          </div>

                          <div>  
                            <input type="submit" name="send" id="send" class="btn btn-default set-login-btn" value="µn¤J">
                          </div>
                           
                       	  <div>
                       	  	<input type="hidden" name="action" value = "getAccount_judge">
                          </div>
             
                     	</form>
                     	</div>
                     	
                     	<div>
                     	
						<form action="<%=request.getContextPath()%>/member/mem.do" method="POST" class="set-form">
		                          <br>
		                          <div>  
		                            <input type="submit" name="getlinked_member_register" id="getlinked_member_register" class="btn btn-default set-login-btn" value="µù¥U">
		                          </div>
		                       
		                       	  <div>
		                       	  	<input type="hidden" name="action" value = "getlinked_member_register_page">
		                          </div>
		                          <div>
		                       	  <input type="hidden" name="getlinked_member_registerpath" value ="<%=request.getServletPath()%>">
		                          </div>
	                  	   </form>
	  
	                  	  	  
                  	   </div>
                  	   <br>
       

  
				</div>
		    </div> 
		    <div class="col-xs-12 col-sm-1"></div>		
  			</div>
  		</div>

	
   

	
    <!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->
    </body>
</html>




