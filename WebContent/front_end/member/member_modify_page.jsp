<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
		
		<form action="<%=request.getContextPath()%>/member/mem.do"  method="POST" class="set-form" enctype="multipart/form-data">          
        				 		  
        				 		  <div class="form-group">
		                            <label for="mem_name">暱稱:</label>
		                            <input type="text" name="mem_name" id="mem_name" size="25" value="${memSelf.mem_name}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_name}</span></c:if> 
		                          </div>
	
 		                                 
		                          <div class="form-group">
		                            <label for="mem_phone">手機:</label>  
		                            <input type="text" name="mem_phone" id="mem_phone" size="25" value="${memSelf.mem_phone}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_phone}</span></c:if> 
		                          </div>
		                          
 		                         
		                          <div class="form-group">
		                            <label for="mem_mail">電子郵件:</label>  
		                            <input type="text" name="mem_mail" id="mem_mail" size="25" value="${memSelf.mem_mail}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_mail}</span></c:if> 
		                          </div>
		                          
  		                          
		                          <div class="form-group">
		                          <label for="mem_height">身高:</label> 
		                            <select class="" name="mem_height" id="height">
		                              <option value="${memSelf.mem_height}"selected>請選擇:</option>
		                              <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if> 
		                            </select>
		                          
		                   
		                            <label for="mem_weight">體重:</label> 
		                            <select class="" name="mem_weight" id="weight">
		                              <option value="${memSelf.mem_weight}"selected>請選擇:</option>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if> 
  		                   	  		<c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_weight}</span></c:if>
                         		 </div>
                       		 	
				                  <div class="form-group">
		                            <label for="mem_emotion">感情狀況:</label> 
		                            <select class="" name="mem_emotion" id="mem_emotion">
		                              <option value="${memSelf.mem_emotion}" selected>請選擇:</option>
		                              <option value="單身">單身</option>
		                              <option value="穩定交往中">穩定交往中</option>
		                              <option value="已婚">已婚</option>
		                              <option value="離婚">離婚</option>
		                              <option value="一言難盡">一言難盡</option>
		                              <option value="保密">保密</option>
		                            </select>
		                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_emotion}</span></c:if> 
		                          </div>
		                         
 		                          
 		                          
		                          <div class="form-group">
		                            <label for="mem_contact">交往關係:</label> 
		                            <select class="" name="mem_contact" id="mem_contact">
		                              <option value="${memSelf.mem_contact}" selected>請選擇:</option>
		                              <option value="其他友誼">其他友誼</option>
		                              <option value="談心好友">談心好友</option>
		                              <option value="男女朋友">男女朋友</option>
		                              <option value="結婚對象">結婚對象</option>
		                              <option value="網路情人">網路情人</option>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_contact}</span></c:if> 
		                          </div>
  		                          
		                          
  
		                          <div class="form-group">                    
										    <div>
										        <img class="preview" style="max-width: 150px; max-height: 150px;">
										        <div class="size"></div>
										    </div>
										    <input type='file' class="mem_photo" name="mem_photo">
		                          </div>
  		                          
		                          
		                            <div class="form-group">
	                                    <label for="">興趣</label>
	                                    	<input class="" type="checkbox" name="mem_interest1" value="上網">上網
			                         		<input class="" type="checkbox" name="mem_interest2" value="聽音樂">聽音樂
			                         		<input class="" type="checkbox" name="mem_interest3" value="看電影">看電影
			                         		<input class="" type="checkbox" name="mem_interest4" value="健身">健身
			                         		<input class="" type="checkbox" name="mem_interest5" value="看書">看書
			                         		<input class="" type="checkbox" name="mem_interest6" value="打球">打球
			                         		<input class="" type="checkbox" name="mem_interest7" value="玩遊戲">玩遊戲
			                         		<input class="" type="checkbox" name="mem_interest8" value="旅行">旅行
			                         		<c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_interest}</span></c:if> 
			                        </div>
			                       
								
	                          <div>  
	                            <input type="submit" class="btn btn-info set-login-btn" value="提交">
	                          </div>
	                          
	                          <div>
	                          	<input type="hidden" name="mem_no" value="${memSelf.mem_no}">
	                            <input type="hidden" name="action" value = "getmodify_data_judge">
	                          </div>          	
        					</form>
        				
		
		
		
		
		
		
		
		
</body>
</html>