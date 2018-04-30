<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<style>
.panel-warning>.set-panel{
background: rgba(255,220,220);
}
</style>

<body>
		
		<form action="<%=request.getContextPath()%>/member/mem.do"  method="POST" class="set-form" enctype="multipart/form-data">             				 		  		 		  
        	<div class="panel panel-warning">
				<div class="panel-heading set-panel">
					<h3 class="panel-title">�ӤH���</h3>
				</div>
					<table class="table table-hover">	
						<tr>
							<td>		
		   				 	   <div class="form-group">
		                         <label for="mem_name">�ʺ�:</label>
		                         <input type="text" name="mem_name" id="mem_name" size="25" value="${memSelf.mem_name}">
		                         <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_name}</span></c:if> 
		                       </div>
	                       </td>
                       </tr>
	
 		               <tr>
 		               		<td>                  
		                          <div class="form-group">
		                            <label for="mem_phone">���:</label>  
		                            <input type="text" name="mem_phone" id="mem_phone" size="25" value="${memSelf.mem_phone}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_phone}</span></c:if> 
		                          </div>
		                     </td>     
		               </tr>          
 		                <tr>
							<td>         
		                          <div class="form-group">
		                            <label for="mem_mail">�q�l�l��:</label>  
		                            <input type="text" name="mem_mail" id="mem_mail" size="25" value="${memSelf.mem_mail}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_mail}</span></c:if> 
		                          </div>
		                     </td>
		                 </tr>
		                 
		                 
		                 <tr>
		                 	<td>
			                  <div class="form-group">
	                           		 <label for="mem_gender">�ʧO:</label>	 
						                              �k<input type="radio" name="mem_gender" id="mem_gender" value="�k" ${memSelf.mem_gender=='�k' ?'checked':''}>
						                              �k<input type="radio" name="mem_gender" id="mem_gender" value="�k" ${memSelf.mem_gender=='�k' ?'checked':''}>
						             <c:if test="${not empty errorMsgs}">
	                           		 <p style="color:red">${errorMsgs.mem_gender}</p>
	                            	 </c:if> 
	                            	 <input type="hidden" id="a1" value="${memSelf.mem_gender}">
	                          </div>
	                         </td>	
		                 </tr>
		                 
		                 
		                 <tr>
							<td>
			                  <div class="form-group">
	                            <label for="mem_bloodtype">�嫬:</label>
	                            <select class="" name="mem_bloodtype" id="mem_bloodtype">
	                              <option value="">�п��:</option>
	                              <option value="A" ${memSelf.mem_bloodtype=='A' ?'selected':''}>A��</option>
	                              <option value="B" ${memSelf.mem_bloodtype=='B' ?'selected':''}>B��</option>
	                              <option value="AB" ${memSelf.mem_bloodtype=='AB' ?'selected':''}>AB��</option>
	                              <option value="O" ${memSelf.mem_bloodtype=='O' ?'selected':''}>O��</option>
	                            </select>
	                             <c:if test="${not empty errorMsgs}">
	                           	<p style="color:red">${errorMsgs.mem_bloodtype}</p>
	                            </c:if>  
	                            <input type="hidden" id = "a1" value="${memSelf.mem_bloodtype}">
	                          </div>
		                   </td>
		                 </tr>
		                 
		       			<tr>	 	
				            <td>      
				            <div class="form-group">
                            <label for="mem_county">�a��:</label>
              
                            <select class="" name="mem_county" id="mem_county">
                              <option value="${memSelf.mem_county}" selected>�п��:</option>
                              <option value="�򶩥�" ${memSelf.mem_county=='�򶩥�' ?'selected':''}>�򶩥�</option>
                              <option value="�x�_��" ${memSelf.mem_county=='�x�_��' ?'selected':''}>�x�_��</option>
                              <option value="�s�_��" ${memSelf.mem_county=='�s�_��' ?'selected':''}>�s�_��</option>
                              <option value="��饫" ${memSelf.mem_county=='��饫' ?'selected':''}>��饫</option>
                              <option value="�s�˥�" ${memSelf.mem_county=='�s�˥�' ?'selected':''}>�s�˥�</option>
                              <option value="�s�˿�" ${memSelf.mem_county=='�s�˿�' ?'selected':''}>�s�˿�</option>
                              <option value="�]�߿�" ${memSelf.mem_county=='�]�߿�' ?'selected':''}>�]�߿�</option>
                              <option value="�x����" ${memSelf.mem_county=='�x����' ?'selected':''}>�x����</option>
                              <option value="���ƿ�" ${memSelf.mem_county=='���ƿ�' ?'selected':''}>���ƿ�</option>
                              <option value="�n�뿤" ${memSelf.mem_county=='�n�뿤' ?'selected':''}>�n�뿤</option>
                              <option value="���L��" ${memSelf.mem_county=='���L��' ?'selected':''} >���L��</option>
                              <option value="�Ÿq��" ${memSelf.mem_county=='�Ÿq��' ?'selected':''}>�Ÿq��</option>
                              <option value="�Ÿq��" ${memSelf.mem_county=='�Ÿq��' ?'selected':''}>�Ÿq��</option>
                              <option value="�x�n��" ${memSelf.mem_county=='�x�n��' ?'selected':''}>�x�n��</option>
                              <option value="������" ${memSelf.mem_county=='������' ?'selected':''}>������</option>
                              <option value="�̪F��" ${memSelf.mem_county=='�̪F��' ?'selected':''}>�̪F��</option>
                              <option value="�x�F��" ${memSelf.mem_county=='�x�F��' ?'selected':''}>�x�F��</option>
                              <option value="�Ὤ��" ${memSelf.mem_county=='�Ὤ��' ?'selected':''}>�Ὤ��</option>
                              <option value="�y����" ${memSelf.mem_county=='�y����' ?'selected':''}>�y����</option>
                            </select>
            
                             <c:if test="${not empty errorMsgs}">
                           	<p style="color:red">${errorMsgs.mem_county}</p>
                            </c:if>  
                          </div>
		               		 </td>
		                </tr>         
		       			
		       			
	  
  		                 <tr>
							<td>        
		                          <div class="form-group">
		                          <label for="mem_height">����:</label> 
<%-- 		                              <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if>  --%>
		                            <select class="" name="mem_height" id="height">
			                          <option value="" selected>�п��:</option>
		                              <c:forEach var="height" begin="120" end="220" step="1">
			                              <option value="${height}" ${(memSelf.mem_height==height) ? 'selected' : ''}>${height}����</option>
		                              </c:forEach>
		                            </select>

		                            <label for="mem_weight">�魫:</label> 
		                            <select class="" name="mem_weight" id="weight">
		                              <option value="" selected>�п��:</option>
		                              <c:forEach var="weight" begin="30" end="220" step="1">
			                              <option value="${weight}" ${(memSelf.mem_weight==weight) ? 'selected' : ''}>${weight}����</option>
		                              </c:forEach>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if> 
  		                   	  		<c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_weight}</span></c:if>
                         		 </div>
                         	</td>
                         </tr>		 
                       	<tr>	 	
				            <td>      
				            		<div class="form-group">
		                            <label for="mem_emotion">�P�����p:</label> 
		                            <select class="" name="mem_emotion" id="mem_emotion">
		                              <option value="${memSelf.mem_emotion}" selected>�п��:</option>
		                              <option value="�樭" ${memSelf.mem_emotion=='�樭' ?'selected':''}>�樭</option>
		                              <option value="í�w�橹��" ${memSelf.mem_emotion=='í�w�橹��' ?'selected':''}>í�w�橹��</option>
		                              <option value="�w�B" ${memSelf.mem_emotion=='�w�B' ?'selected':''}>�w�B</option>
		                              <option value="���B" ${memSelf.mem_emotion=='���B' ?'selected':''}>���B</option>
		                              <option value="�@������" ${memSelf.mem_emotion=='�@������' ?'selected':''}>�@������</option>
		                              <option value="�O�K" ${memSelf.mem_emotion=='�O�K' ?'selected':''}>�O�K</option>
		                            </select>
		                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_emotion}</span></c:if> 
		                          </div>
		               		 </td>
		                </tr>         
 		                <tr>
							<td>          
 		                          
		                          <div class="form-group">
		                            <label for="mem_contact">�橹���Y:</label> 
		                            <select class="" name="mem_contact" id="mem_contact">
		                              <option value="${memSelf.mem_contact}" selected>�п��:</option>
		                              <option value="��L�ͽ�" ${memSelf.mem_contact=='��L�ͽ�' ?'selected':''}>��L�ͽ�</option>
		                              <option value="�ͤߦn��" ${memSelf.mem_contact=='�ͤߦn��' ?'selected':''}>�ͤߦn��</option>
		                              <option value="�k�k�B��" ${memSelf.mem_contact=='�k�k�B��' ?'selected':''}>�k�k�B��</option>
		                              <option value="���B��H" ${memSelf.mem_contact=='���B��H' ?'selected':''}>���B��H</option>
		                              <option value="�������H" ${memSelf.mem_contact=='�������H' ?'selected':''}>�������H</option>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_contact}</span></c:if> 
		                          </div>
  		                     </td>     
		                 </tr>     
  						<tr>
							<td>
		                          <div class="form-group">                    
										     <label for="">�W�Ǥj�Y�K:</label>  
										    <input type='file' class="mem_photo" name="mem_photo">
										      <div>
										        <img class="preview" style="max-width: 150px; max-height: 150px;">
										        <div class="size"></div>
										    </div>
		                          </div>
  		                    </td>        
		                  </tr>
		                  
		                  <tr>
							<td>  
							<c:set var="interestList" value="�W��,ť����,�ݹq�v,����,�ݮ�,���y,���C��,�Ȧ�"/>
							<div class="form-group">
                            	<label for="">����</label>
								<c:forTokens var="interest" items="${interestList}" delims="," varStatus="status">
									<c:set var="isExists" value="false"/>
									<c:forTokens var="oriInterest" items="${memSelf.mem_interest}" delims=",">
										<c:if test="${oriInterest == interest}">
											<c:set var="isExists" value="true"/>
										</c:if>
									</c:forTokens>
									<c:choose>
										<c:when test="${isExists == 'true'}">
											<input class="" type="checkbox" name="mem_interest${status.count}" value="${interest}" checked>${interest}
										</c:when>
										<c:otherwise>
											<input class="" type="checkbox" name="mem_interest${status.count}" value="${interest}">${interest}
										</c:otherwise>
									</c:choose>
								</c:forTokens>
							</div>
			              </td>         
						</tr>
						<tr>
							<td>		
	                          <div>
	                           
	                            <input type="submit" class="btn btn-default set-login-btn" value="����">
	                          </div>
	                    	 </td>     
	                     </tr>     
	                          <div>
	                          	<input type="hidden" name="mem_no" value="${memSelf.mem_no}">
	                            <input type="hidden" name="action" value = "getmodify_data_judge">
	                          </div> 
	                 </table>         
	         </div>         	
        </form>
<script type="text/javascript">
	$(document).ready(function(){
		var bloodtype = $("")
		
	});


</script>
 
</body>
</html>