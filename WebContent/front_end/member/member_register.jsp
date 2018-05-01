<%@ page contentType="text/html; charset=Big5" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>

    <title>�n�J����</title>

    

    <style type="text/css">
    .set-login{
        background-color: rgb(255,220,220);
        min-height: 650px;
        margin-top: 6%;
        margin-bottom: 5%;
        padding-bottom: 2%;

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
       				<div><h3>�|�����U</h3></div>
                      <form action="<%=request.getContextPath()%>/member/mem.do" method="POST" class="set-form">
           
                          <div class="form-group">
                            <label for="mem_account">�b��:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_account}</p>
                             </c:if>
                            <input type="text" name="mem_account" id="mem_account" class="form-control" value="">
 						              </div>
						  
                          <div class="form-group">
                            <label for="mem_password">�K�X:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_password}</p>
                             </c:if>  
                            <input type="password" name="mem_password" id="mem_password" class="form-control" value="">
                          </div>

                          <div class="form-group">
                            <label for="doublecheck">�T�{�K�X:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.doublecheck}</p>
                             </c:if>     
                            <input type="password" name="doublecheck" id="mem_password1" class="form-control" value="">
                          </div>
                          
                          <div class="form-group">
                            <label for="mem_name">�ʺ�:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_name}</p>
                             </c:if>   
                            <input type="text" name="mem_name" id="mem_name" class="form-control" value="">
                          </div>
                                   
                       	  <div class="form-group">
                            <label for="mem_birthday">�X�ͦ~��:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_birthday}</p>
                             </c:if>  
                            <input type="date" name="mem_birthday" id="mem_birthday" class="form-control" value="">
                          </div>
						
                        	
                         <div class="form-group">
                           		 <label for="mem_gender">�ʧO:</label>
                           		 <c:if test="${not empty errorMsgs}">
                           		 <p style="color:red">${errorMsgs.mem_gender}</p>
                            	 </c:if> 
					                              �k<input type="radio" name="mem_gender" id="mem_gender1" value="�k">
					                              �k<input type="radio" name="mem_gender" id="mem_gender2" value="�k">
                         </div>	
                        	
                        	
                          <div class="form-group">
                            <label for="mem_bloodtype">�嫬:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_bloodtype}</p>
                            </c:if>  
                            <select class="" name="mem_bloodtype" id="mem_bloodtype">
                              <option value="" selected>�п��:</option>
                              <option value="A">A��</option>
                              <option value="B">B��</option>
                              <option value="AB">AB��</option>
                              <option value="O">O��</option>
                            </select>
                          </div>

                               
                          <div class="form-group">
                            <label for="mem_county">�a��:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_county}</p>
                            </c:if>  
                            <select class="" name="mem_county" id="mem_county">
                              <option value="" selected>�п��:</option>
                              <option value="�򶩥�">�򶩥�</option>
                              <option value="�x�_��">�x�_��</option>
                              <option value="�s�_��">�s�_��</option>
                              <option value="��饫">��饫</option>
                              <option value="�s�˥�">�s�˥�</option>
                              <option value="�s�˿�">�s�˿�</option>
                              <option value="�]�߿�">�]�߿�</option>
                              <option value="�x����">�x����</option>
                              <option value="���ƿ�">���ƿ�</option>
                              <option value="�n�뿤">�n�뿤</option>
                              <option value="���L��">���L��</option>
                              <option value="�Ÿq��">�Ÿq��</option>
                              <option value="�Ÿq��">�Ÿq��</option>
                              <option value="�x�n��">�x�n��</option>
                              <option value="������">������</option>
                              <option value="�̪F��">�̪F��</option>
                              <option value="�x�F��">�x�F��</option>
                              <option value="�Ὤ��">�Ὤ��</option>
                              <option value="�y����">�y����</option>
                            </select>
                          </div>


                          <div class="form-group">
                            <label for="mem_height">����:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_height}</p>
                            </c:if> 
                            <select class="" name="mem_height" id="height">
                              <option value="" selected>�п��:</option>
                            </select>
                          </div>

                          <div class="form-group">
                            <label for="mem_weight">�魫:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_weight}</p>
                            </c:if> 
                            <select class="" name="mem_weight" id="weight">
                              <option value="" selected>�п��:</option>
                            </select>
                          </div>


                           <div class="form-group">
                            <label for="mem_emotion">�P�����p:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_emotion}</p>
                            </c:if> 
                            <select class="" name="mem_emotion" id="mem_emotion">
                              <option value="" selected>�п��:</option>
                              <option value="�樭">�樭</option>
                              <option value="í�w�橹��">í�w�橹��</option>
                              <option value="�w�B">�w�B</option>
                              <option value="���B">���B</option>
                              <option value="�@������">�@������</option>
                              <option value="�O�K">�O�K</option>
                            </select>
                          </div>
                          
                          <div class="form-group">
                            <label for="mem_contact">�橹���Y:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_contact}</p>
                            </c:if> 
                            <select class="" name="mem_contact" id="mem_contact">
                              <option value="" selected>�п��:</option>
                              <option value="��L�ͽ�">��L�ͽ�</option>
                              <option value="�ͤߦn��">�ͤߦn��</option>
                              <option value="�k�k�B��">�k�k�B��</option>
                              <option value="���B��H">���B��H</option>
                              <option value="�������H">�������H</option>
                            </select>
                          </div>

                          <div>  
                            <input type="submit" name="send" id="send" class="btn btn-default set-login-btn" value="����">
                          </div>
                          
                          <div>
                            <input type="hidden" name="action" value = "getregister_judge">
                          </div>
                        
                     </form>
       

  				  <button type="button" id="aaa">�s�W�b��</button>	
				</div>
			
		    </div> 
		    <div class="col-xs-12 col-sm-1"></div>
		  	
  			</div>
  				
  		</div>
		
    <!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->
	<script type="text/javascript">
    
    window.onload = function(){
      var height = document.getElementById("height");
          for(var i = 120; i <= 220; i++){
            var text = i + "����";
            var value = i;
            var opt = new Option(text,value);
            height.add(opt);
          }
      var weight = document.getElementById("weight");
        for(var i = 30; i <= 220; i++){
          var text = i + "����";
          var value = i;
          var opt = new Option(text,value);
          weight.add(opt);
        }
    }
    
    $(document).ready(function(){
    	$("#aaa").click(function(){
    		$("#mem_account").val("helloworld");
    		$("#mem_password").val("helloworld");
    		$("#mem_password1").val("helloworld");
    		$("#mem_name").val("����");
    		$("#mem_birthday").val("1988-06-11");
    		$("#mem_gender2").attr("checked", "checked");
    		$('#mem_bloodtype option[value="A"]').attr("selected",true);
    		$('#mem_county option[value="�s�_��"]').attr("selected",true);
    		$('#height option[value="169"]').attr("selected",true);
    		$('#weight option[value="48"]').attr("selected",true);
    		$('#mem_emotion option[value="�樭"]').attr("selected",true);
    		$('#mem_contact option[value="�k�k�B��"]').attr("selected",true);
    	});	p
    });
    
    </script>    
	
	
	
    </body>
</html>




