<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script src="<%=request.getContextPath()%>/front_end/member/mem_modify_page/ckeditor/ckeditor.js"></script>
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

	<script type="text/javascript">
	
		$(document).ready(function(){
			$("#intro").click(function() {
				$.ajax({
					
					
				});
			});
		});
			
	
	
	
	
	</script>
	<div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
            
          
              <div class="col-xs-12 col-sm-3">
               <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
                        <!-- �϶�1 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel1">
                            <h4 class="panel-title">
                              <a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
                          	        �ק�ӤH���
                              </a>
                            </h4>
                          </div>
                          <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                            <div class="panel-body">
                                <div><button type="button" id="pass" class="btn btn-default" onclick="pass()">�ק�K�X</button></div><br>
                                <div><button type="button" id="data" class="btn btn-default" onclick="data()">�ӤH���</button></div><br>
                                <div><button type="button" id="intro" class="btn btn-default" >�ۧڤ���</button></div><br>
                            </div>
                          </div>
                        </div>
                        <!-- �϶�2 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel2">
                            <h4 class="panel-title">
                              <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                             	     ���X���ؼ��D�G
                              </a>
                            </h4>
                          </div>
                          <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                            <div class="panel-body">
                              	  ���e�G�m�J�b�o��
                            </div>
                          </div>
                        </div>
                        <!-- �϶�3 -->
                        <div class="panel panel-default">
                          <div class="panel-heading" role="tab" id="panel3">
                            <h4 class="panel-title">
                              <a href="#ccc" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc">
                            	      ���X���ؼ��D�T
                              </a>
                            </h4>
                          </div>
                          <div id="ccc" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3">
                            <div class="panel-body">
                              	  ���e�T�m�J�b�o��
                            </div>
                          </div>
                        </div>
                      </div>

                </div>
                <div class="col-xs-12 col-sm-9">
                      <div class="col-xs-12 col-sm-1"></div>
                      
                      	<div class="col-xs-12 col-sm-10" id="modify-area">
<%--                    	
                      	<!-- �ק�K�X-->
                      		<div id="modify_pass_area" style="display: none">
        				 	<p>pass</p>
        				 	</div>
                      	<!-- �ӤH���-->
        				 	<div id="modify_data_area" style="display: none">
        				 	<p>modify</p>
        				 	</div>
 						<!-- �ۧڤ���-->
                          <div id="intro_area" style="display: none">
                           <form name = "form" action="/BA107G3/member/mem.do" method="post">
					        	<textarea name="mem_intro" id="content" rows="10" cols="80"></textarea>
					            <div>
					            <input type = 'submit' value = '����' class="btn btn-info">
								</div>
								<div>
								<input type="hidden" name="action" value = "getintro_judge">
								</div>
					        </form>
                          </div>
--%>                      
                     	 </div>
                      <div class="col-xs-12 col-sm-1"></div>
                </div>
              </div>
              <div class="col-xs-12 col-sm-1"></div>   
            </div>
        </div>  

		
	
	<!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->
<%-- 
	<script type="text/javascript">
	
		<!--�ӤH���-->
		function pass(){
			var pass = document.getElementById('pass');
			var intro_area = document.getElementById("intro_area");
        	var modify_data_area = document.getElementById('modify_data_area');
    		var modify_pass_area = document.getElementById('modify_pass_area');
    			pass.onclick = function(){
    				if(modify_pass_area.style.display == "none"){
    					intro_area.style.display = "none";
    					modify_data_area.style.display = "none";
    					modify_pass_area.style.display = "block";
    				}	
    			}
		}
	
	
	
		<!--�ۧڤ���-->
        function intro() {
        	var intro = document.getElementById('intro');
			var intro_area = document.getElementById("intro_area");
        	var modify_data_area = document.getElementById('modify_data_area');
    		var modify_pass_area = document.getElementById('modify_pass_area');
				intro.onclick = function(){	
				if(intro_area.style.display == "none"){
					modify_pass_area.style.display="none";
					modify_data_area.style.display="none";
					intro_area.style.display="block";
				}
			}
		}
        <!--�ӤH���-->
        function data() {
        	var data = document.getElementById('data');
        	var intro_area = document.getElementById("intro_area");
			var modify_data_area = document.getElementById('modify_data_area');
			var modify_pass_area = document.getElementById('modify_pass_area');
        			data.onclick = function(){
					if(modify_data_area.style.display == "none"){
						intro_area.style.display="none";
						modify_pass_area.style.display = "none";
						modify_data_area.style.display="block";
					}
        		
			}
		}
        
        
     </script>
--%>
	<script>
		CKEDITOR.replace( 'content', {});
	</script>

</body>
</html>