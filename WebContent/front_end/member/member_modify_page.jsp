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
		                            <label for="mem_name">�ʺ�:</label>
		                            <input type="text" name="mem_name" id="mem_name" size="25" value="${memSelf.mem_name}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_name}</span></c:if> 
		                          </div>
	
 		                                 
		                          <div class="form-group">
		                            <label for="mem_phone">���:</label>  
		                            <input type="text" name="mem_phone" id="mem_phone" size="25" value="${memSelf.mem_phone}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_phone}</span></c:if> 
		                          </div>
		                          
 		                         
		                          <div class="form-group">
		                            <label for="mem_mail">�q�l�l��:</label>  
		                            <input type="text" name="mem_mail" id="mem_mail" size="25" value="${memSelf.mem_mail}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_mail}</span></c:if> 
		                          </div>
		                          
  		                          
		                          <div class="form-group">
		                          <label for="mem_height">����:</label> 
		                            <select class="" name="mem_height" id="height">
		                              <option value="${memSelf.mem_height}"selected>�п��:</option>
		                              <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if> 
		                            </select>
		                          
		                   
		                            <label for="mem_weight">�魫:</label> 
		                            <select class="" name="mem_weight" id="weight">
		                              <option value="${memSelf.mem_weight}"selected>�п��:</option>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if> 
  		                   	  		<c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_weight}</span></c:if>
                         		 </div>
                       		 	
				                  <div class="form-group">
		                            <label for="mem_emotion">�P�����p:</label> 
		                            <select class="" name="mem_emotion" id="mem_emotion">
		                              <option value="${memSelf.mem_emotion}" selected>�п��:</option>
		                              <option value="�樭">�樭</option>
		                              <option value="í�w�橹��">í�w�橹��</option>
		                              <option value="�w�B">�w�B</option>
		                              <option value="���B">���B</option>
		                              <option value="�@������">�@������</option>
		                              <option value="�O�K">�O�K</option>
		                            </select>
		                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_emotion}</span></c:if> 
		                          </div>
		                         
 		                          
 		                          
		                          <div class="form-group">
		                            <label for="mem_contact">�橹���Y:</label> 
		                            <select class="" name="mem_contact" id="mem_contact">
		                              <option value="${memSelf.mem_contact}" selected>�п��:</option>
		                              <option value="��L�ͽ�">��L�ͽ�</option>
		                              <option value="�ͤߦn��">�ͤߦn��</option>
		                              <option value="�k�k�B��">�k�k�B��</option>
		                              <option value="���B��H">���B��H</option>
		                              <option value="�������H">�������H</option>
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
	                                    <label for="">����</label>
	                                    	<input class="" type="checkbox" name="mem_interest1" value="�W��">�W��
			                         		<input class="" type="checkbox" name="mem_interest2" value="ť����">ť����
			                         		<input class="" type="checkbox" name="mem_interest3" value="�ݹq�v">�ݹq�v
			                         		<input class="" type="checkbox" name="mem_interest4" value="����">����
			                         		<input class="" type="checkbox" name="mem_interest5" value="�ݮ�">�ݮ�
			                         		<input class="" type="checkbox" name="mem_interest6" value="���y">���y
			                         		<input class="" type="checkbox" name="mem_interest7" value="���C��">���C��
			                         		<input class="" type="checkbox" name="mem_interest8" value="�Ȧ�">�Ȧ�
			                         		<c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_interest}</span></c:if> 
			                        </div>
			                       
								
	                          <div>  
	                            <input type="submit" class="btn btn-info set-login-btn" value="����">
	                          </div>
	                          
	                          <div>
	                          	<input type="hidden" name="mem_no" value="${memSelf.mem_no}">
	                            <input type="hidden" name="action" value = "getmodify_data_judge">
	                          </div>          	
        					</form>
        				
		
		
		
		
		
		
		
		
</body>
</html>