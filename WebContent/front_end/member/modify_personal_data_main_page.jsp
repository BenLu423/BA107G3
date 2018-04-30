<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

<title>Insert title here</title>
<style type="text/css">
.set-login-btn{
min-width: 100%;
}
.panel-warning>.set-panel{
background: rgba(255,220,220);
}
</style>
</head>
<body>
	<!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
	<!-- //header-->
	<%
		String mem_no = (String)request.getParameter("mem_no");
	%>
	
		
		<div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
                
                <div class="col-xs-12 col-sm-3">
                    	    <div class="panel-group" id="accordion2" role="tablist">
                        <!-- 區塊1 -->
                        <div class="panel panel-warning">
                          <div class="panel-heading set-panel" role="tab" id="panel1">
                            <h4 class="panel-title">
                            
                          	        修改個人資料
                          
                            </h4>
                          </div>
                          <div id="" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                            <div class="panel-body">
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfpass">修改密碼</a></div><br>
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfdata">個人資料</a></div><br>
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfintro">自我介紹</a></div><br>
                            </div>
                          </div>
                        </div>
                        </div>
                
                </div>
            
                <div class="col-xs-12 col-sm-7 col-sm-offset-1" id="modify-area">
                
            				<% 
 							String chang_page = "";
 							Map<String,String> myself_page = (Map<String,String>)session.getAttribute("myself_page");
 						
 							if(myself_page != null){
 								Set<String> keys = myself_page.keySet();
 								for(String key : keys){
 									String value = myself_page.get(key);
 									if(key.equals("selfintro")){
 										chang_page = value;
 									}else if(key.equals("selfdata")){
 										chang_page = value;
 									}else if(key.equals("selfpass")){
 										chang_page = value;
 									}
 								}
 						%>
 						<%} %>
                   	
					
								  <div class="col-xs-12 col-sm-9">
			              			<c:if test="${empty myself_page}">
			              			<jsp:include page="/front_end/member/member_modify_password.jsp"/>
			              			</c:if>
			              			<c:if test="${not empty myself_page}">
			 						<jsp:include page="<%=chang_page%>"/>   
			 						</c:if>           
           						   </div>
								 
					
                 </div>
             </div>
           </div>
           <div class="col-xs-12 col-sm-1"></div>   
         </div>
		
		
		
		
		
		
		
		
		
		
	
	<!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->

	
	  <script type="text/javascript">
    
	  

		   
			  $(function (){		  
				    function format_float(num, pos)
				    {
				        var size = Math.pow(10, pos);
				        return Math.round(num * size) / size;
				    }
				 
				    function preview(input) {
				 
				        if (input.files && input.files[0]) {
				            var reader = new FileReader();
				            
				            reader.onload = function (e) {
				                $('.preview').attr('src', e.target.result);
				            }
				            reader.readAsDataURL(input.files[0]);
				        }
				    }
				    $("body").on("change", ".mem_photo", function (){
				        preview(this);
				    })    
				})


			window.onload = function(){
              var height = document.getElementById("height");
                  for(var i = 120; i <= 220; i++){
                    var text = i + "公分";
                    var value = i;
                    var opt = new Option(text,value);
                    height.add(opt);
                  }
              var weight = document.getElementById("weight");
                for(var i = 30; i <= 220; i++){
                  var text = i + "公斤";
                  var value = i;
                  var opt = new Option(text,value);
                  weight.add(opt);
                }              
            }
	  
   
    
    </script>    

</body>
</html>