<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
  <head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <title>�n�J����</title>

   

    <style type="text/css">
    .set-login{
        background-color: #fff;
        min-height: 650px;
        margin-top: 10%;
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
    
	<%-- ���~���C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color:red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
		</ul>
	</c:if>
	


    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 set-login">
                      <div><h3>�|�����U</h3></div>
                      <form action="<%=request.getContextPath()%>/member/mem.do" method="POST" class="set-form">
           
                          <div class="form-group">
                            <label for="mem_account">�b��:</label>
                            <input type="text" name="mem_account" id="mem_account" class="form-control" value="">
 						 </div>
						  
                          <div class="form-group">
                            <label for="mem_password">�K�X:</label>  
                            <input type="text" name="mem_password" id="mem_password" class="form-control" value="">
                          </div>

                          <div class="form-group">
                            <label for="doublecheck">�T�{�K�X:</label>  
                            <input type="text" name="doublecheck" id="" class="form-control" value="">
                          </div>
                          
                          <div class="form-group">
                            <label for="mem_name">�ʺ�:</label>  
                            <input type="text" name="mem_name" id="mem_name" class="form-control" value="">
                          </div>
                                   
                       	  <div class="form-group">
                            <label for="mem_birthday">�X�ͦ~��:</label>  
                            <input type="date" name="mem_birthday" id="mem_birthday" class="form-control" value="">
                          </div>
						
                        	
                         <div class="form-group">
                           		 <label for="mem_gender">�ʧO:</label> 
					                              �k<input type="radio" name="mem_gender" id="mem_gender" value="�k">
					                              �k<input type="radio" name="mem_gender" id="mem_gender" value="�k">
                         </div>	
                        	
                        	
                          <div class="form-group">
                            <label for="mem_bloodtype">�嫬:</label> 
                            <select class="" name="mem_bloodtype" id="mem_bloodtype">
                              <option selected>�п��:</option>
                              <option value="A">A��</option>
                              <option value="B">B��</option>
                              <option value="AB">AB��</option>
                              <option value="O">O��</option>
                            </select>
                          </div>

                               
                          <div class="form-group">
                            <label for="mem_county">�a��:</label> 
                            <select class="" name="mem_county" id="mem_county">
                              <option selected>�п��:</option>
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
                            <select class="" name="mem_height" id="height">
                              <option selected>�п��:</option>
                            </select>
                          </div>

                          <div class="form-group">
                            <label for="mem_weight">�魫:</label> 
                            <select class="" name="mem_weight" id="weight">
                              <option selected>�п��:</option>
                            </select>
                          </div>


                           <div class="form-group">
                            <label for="mem_emotion">�P�����p:</label> 
                            <select class="" name="mem_emotion" id="mem_emotion">
                              <option selected>�п��:</option>
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
                            <input type="submit" name="send" id="send" class="btn btn-info set-login-btn" value="����">
                          </div>
                          
                          <div>
                            <input type="hidden" name="action" value = "getregister_judge">
                          </div>
                        
                     </form>

                </div>
            </div>
   
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
    

    </script>    
    </body>
</html>