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
               <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
                        <!-- 區塊1 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel1">
                            <h4 class="panel-title">
                              <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
                          	        修改個人資料
                              </a>
                            </h4>
                          </div>
                          <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                            <div class="panel-body">
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfpass">修改密碼</a></div><br>
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfdata">個人資料</a></div><br>
                                <div><a href="<%=request.getContextPath()%>/member/mem.do?action=selfpage&selfp=selfintro">自我介紹</a></div><br>
                            </div>
                          </div>
                        </div>
                        <!-- 區塊2 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel2">
                            <h4 class="panel-title">
                              <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                             	     收合項目標題二
                              </a>
                            </h4>
                          </div>
                          <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                            <div class="panel-body">
                              	  內容二置入在這裡
                            </div>
                          </div>
                        </div>
                        <!-- 區塊3 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel3">
                            <h4 class="panel-title">
                              <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
                            	      收合項目標題三
                              </a>
                            </h4>
                          </div>
                          <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
                            <div class="panel-body">
                              	  內容三置入在這裡
                            </div>
                          </div>
                        </div>
                      </div>

                </div>
                <div class="col-xs-12 col-sm-9">
                      <div class="col-xs-12 col-sm-1"></div>
                      
                      	<div class="col-xs-12 col-sm-10" id="modify-area">
                   	
               			<%--------------------------------需要改動路徑變BA107G3時--------------------------------%>
  
        				 
        				<%--chang_page = value.substring(value.indexOf("k")+1);修改value.indexOf值 --%>
 						<% 
 							String chang_page = "";
 							Map<String,String> myself_page = (Map<String,String>)session.getAttribute("myself_page");
 							if(myself_page != null){
 								Set<String> keys = myself_page.keySet();
 								for(String key : keys){
 									String value = myself_page.get(key);
 									if(key.equals("selfintro")){
 										chang_page = value.substring(value.indexOf("k")+1);
 									}else if(key.equals("selfdata")){
 										chang_page = value.substring(value.indexOf("k")+1);
 									}else if(key.equals("selfpass")){
 										chang_page = value.substring(value.indexOf("k")+1);
 									}
 								}
 						%>
 					
 					
 						<%} %>
 						<%if(myself_page != null){ %>
                 		<jsp:include page="<%=chang_page%>"></jsp:include>
                 		<%}%>
                     	 </div>
                     <%---------------------------------------------------------------------------------%> 
                      <div class="col-xs-12 col-sm-2"></div>
                </div>
              </div>
              <div class="col-xs-12 col-sm-1"></div>   
            </div>
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