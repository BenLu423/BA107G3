<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
  <head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <title>登入頁面</title>

   

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
    

    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2">
                <div class="col-xs-12 col-sm-6 col-sm-offset-3 set-login">
                      <div><h3>會員註冊</h3></div>
                      <form action="<%=request.getContextPath()%>/member/mem.do" method="POST" class="set-form">
           
                          <div class="form-group">
                            <label for="mem_account">帳號:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_account}</p>
                             </c:if>
                            <input type="text" name="mem_account" id="mem_account" class="form-control" value="">
 						 </div>
						  
                          <div class="form-group">
                            <label for="mem_password">密碼:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_password}</p>
                             </c:if>  
                            <input type="text" name="mem_password" id="mem_password" class="form-control" value="">
                          </div>

                          <div class="form-group">
                            <label for="doublecheck">確認密碼:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.doublecheck}</p>
                             </c:if>     
                            <input type="text" name="doublecheck" id="" class="form-control" value="">
                          </div>
                          
                          <div class="form-group">
                            <label for="mem_name">暱稱:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_name}</p>
                             </c:if>   
                            <input type="text" name="mem_name" id="mem_name" class="form-control" value="">
                          </div>
                                   
                       	  <div class="form-group">
                            <label for="mem_birthday">出生年月:</label>
                             <c:if test="${not empty errorMsgs}">
                             <p style="color:red">${errorMsgs.mem_birthday}</p>
                             </c:if>  
                            <input type="date" name="mem_birthday" id="mem_birthday" class="form-control" value="">
                          </div>
						
                        	
                         <div class="form-group">
                           		 <label for="mem_gender">性別:</label>
                           		 <c:if test="${not empty errorMsgs}">
                           		 <p style="color:red">${errorMsgs.mem_gender}</p>
                            	 </c:if> 
					                              男<input type="radio" name="mem_gender" id="mem_gender" value="男">
					                              女<input type="radio" name="mem_gender" id="mem_gender" value="女">
                         </div>	
                        	
                        	
                          <div class="form-group">
                            <label for="mem_bloodtype">血型:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_bloodtype}</p>
                            </c:if>  
                            <select class="" name="mem_bloodtype" id="mem_bloodtype">
                              <option value="" selected>請選擇:</option>
                              <option value="A">A型</option>
                              <option value="B">B型</option>
                              <option value="AB">AB型</option>
                              <option value="O">O型</option>
                            </select>
                          </div>

                               
                          <div class="form-group">
                            <label for="mem_county">地區:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_county}</p>
                            </c:if>  
                            <select class="" name="mem_county" id="mem_county">
                              <option value="" selected>請選擇:</option>
                              <option value="基隆市">基隆市</option>
                              <option value="台北市">台北市</option>
                              <option value="新北市">新北市</option>
                              <option value="桃園市">桃園市</option>
                              <option value="新竹市">新竹市</option>
                              <option value="新竹縣">新竹縣</option>
                              <option value="苗栗縣">苗栗縣</option>
                              <option value="台中市">台中市</option>
                              <option value="彰化縣">彰化縣</option>
                              <option value="南投縣">南投縣</option>
                              <option value="雲林縣">雲林縣</option>
                              <option value="嘉義市">嘉義市</option>
                              <option value="嘉義縣">嘉義縣</option>
                              <option value="台南市">台南市</option>
                              <option value="高雄市">高雄市</option>
                              <option value="屏東縣">屏東縣</option>
                              <option value="台東縣">台東縣</option>
                              <option value="花蓮市">花蓮市</option>
                              <option value="宜蘭市">宜蘭市</option>
                            </select>
                          </div>


                          <div class="form-group">
                            <label for="mem_height">身高:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_height}</p>
                            </c:if> 
                            <select class="" name="mem_height" id="height">
                              <option value="" selected>請選擇:</option>
                            </select>
                          </div>

                          <div class="form-group">
                            <label for="mem_weight">體重:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_weight}</p>
                            </c:if> 
                            <select class="" name="mem_weight" id="weight">
                              <option value="" selected>請選擇:</option>
                            </select>
                          </div>


                           <div class="form-group">
                            <label for="mem_emotion">感情狀況:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_emotion}</p>
                            </c:if> 
                            <select class="" name="mem_emotion" id="mem_emotion">
                              <option value="" selected>請選擇:</option>
                              <option value="單身">單身</option>
                              <option value="穩定交往中">穩定交往中</option>
                              <option value="已婚">已婚</option>
                              <option value="離婚">離婚</option>
                              <option value="一言難盡">一言難盡</option>
                              <option value="保密">保密</option>
                            </select>
                          </div>
                          
                          <div class="form-group">
                            <label for="mem_contact">交往關係:</label>
                            <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_contact}</p>
                            </c:if> 
                            <select class="" name="mem_contact" id="mem_contact">
                              <option value="" selected>請選擇:</option>
                              <option value="其他友誼">其他友誼</option>
                              <option value="談心好友">談心好友</option>
                              <option value="男女朋友">男女朋友</option>
                              <option value="結婚對象">結婚對象</option>
                              <option value="網路情人">網路情人</option>
                            </select>
                          </div>

                          <div>  
                            <input type="submit" name="send" id="send" class="btn btn-info set-login-btn" value="提交">
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