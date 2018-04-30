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
					<h3 class="panel-title">個人資料</h3>
				</div>
					<table class="table table-hover">	
						<tr>
							<td>		
		   				 	   <div class="form-group">
		                         <label for="mem_name">暱稱:</label>
		                         <input type="text" name="mem_name" id="mem_name" size="25" value="${memSelf.mem_name}">
		                         <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_name}</span></c:if> 
		                       </div>
	                       </td>
                       </tr>
	
 		               <tr>
 		               		<td>                  
		                          <div class="form-group">
		                            <label for="mem_phone">手機:</label>  
		                            <input type="text" name="mem_phone" id="mem_phone" size="25" value="${memSelf.mem_phone}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_phone}</span></c:if> 
		                          </div>
		                     </td>     
		               </tr>          
 		                <tr>
							<td>         
		                          <div class="form-group">
		                            <label for="mem_mail">電子郵件:</label>  
		                            <input type="text" name="mem_mail" id="mem_mail" size="25" value="${memSelf.mem_mail}">
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_mail}</span></c:if> 
		                          </div>
		                     </td>
		                 </tr>
		                 
		                 
		                 <tr>
		                 	<td>
			                  <div class="form-group">
	                           		 <label for="mem_gender">性別:</label>	 
						                              男<input type="radio" name="mem_gender" id="mem_gender" value="男" ${memSelf.mem_gender=='男' ?'checked':''}>
						                              女<input type="radio" name="mem_gender" id="mem_gender" value="女" ${memSelf.mem_gender=='女' ?'checked':''}>
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
	                            <label for="mem_bloodtype">血型:</label>
	                            <select class="" name="mem_bloodtype" id="mem_bloodtype">
	                              <option value="">請選擇:</option>
	                              <option value="A" ${memSelf.mem_bloodtype=='A' ?'selected':''}>A型</option>
	                              <option value="B" ${memSelf.mem_bloodtype=='B' ?'selected':''}>B型</option>
	                              <option value="AB" ${memSelf.mem_bloodtype=='AB' ?'selected':''}>AB型</option>
	                              <option value="O" ${memSelf.mem_bloodtype=='O' ?'selected':''}>O型</option>
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
                            <label for="mem_county">地區:</label>
              
                            <select class="" name="mem_county" id="mem_county">
                              <option value="${memSelf.mem_county}" selected>請選擇:</option>
                              <option value="基隆市" ${memSelf.mem_county=='基隆市' ?'selected':''}>基隆市</option>
                              <option value="台北市" ${memSelf.mem_county=='台北市' ?'selected':''}>台北市</option>
                              <option value="新北市" ${memSelf.mem_county=='新北市' ?'selected':''}>新北市</option>
                              <option value="桃園市" ${memSelf.mem_county=='桃園市' ?'selected':''}>桃園市</option>
                              <option value="新竹市" ${memSelf.mem_county=='新竹市' ?'selected':''}>新竹市</option>
                              <option value="新竹縣" ${memSelf.mem_county=='新竹縣' ?'selected':''}>新竹縣</option>
                              <option value="苗栗縣" ${memSelf.mem_county=='苗栗縣' ?'selected':''}>苗栗縣</option>
                              <option value="台中市" ${memSelf.mem_county=='台中市' ?'selected':''}>台中市</option>
                              <option value="彰化縣" ${memSelf.mem_county=='彰化縣' ?'selected':''}>彰化縣</option>
                              <option value="南投縣" ${memSelf.mem_county=='南投縣' ?'selected':''}>南投縣</option>
                              <option value="雲林縣" ${memSelf.mem_county=='雲林縣' ?'selected':''} >雲林縣</option>
                              <option value="嘉義市" ${memSelf.mem_county=='嘉義市' ?'selected':''}>嘉義市</option>
                              <option value="嘉義縣" ${memSelf.mem_county=='嘉義縣' ?'selected':''}>嘉義縣</option>
                              <option value="台南市" ${memSelf.mem_county=='台南市' ?'selected':''}>台南市</option>
                              <option value="高雄市" ${memSelf.mem_county=='高雄市' ?'selected':''}>高雄市</option>
                              <option value="屏東縣" ${memSelf.mem_county=='屏東縣' ?'selected':''}>屏東縣</option>
                              <option value="台東縣" ${memSelf.mem_county=='台東縣' ?'selected':''}>台東縣</option>
                              <option value="花蓮市" ${memSelf.mem_county=='花蓮市' ?'selected':''}>花蓮市</option>
                              <option value="宜蘭市" ${memSelf.mem_county=='宜蘭市' ?'selected':''}>宜蘭市</option>
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
		                          <label for="mem_height">身高:</label> 
<%-- 		                              <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_height}</span></c:if>  --%>
		                            <select class="" name="mem_height" id="height">
			                          <option value="" selected>請選擇:</option>
		                              <c:forEach var="height" begin="120" end="220" step="1">
			                              <option value="${height}" ${(memSelf.mem_height==height) ? 'selected' : ''}>${height}公分</option>
		                              </c:forEach>
		                            </select>

		                            <label for="mem_weight">體重:</label> 
		                            <select class="" name="mem_weight" id="weight">
		                              <option value="" selected>請選擇:</option>
		                              <c:forEach var="weight" begin="30" end="220" step="1">
			                              <option value="${weight}" ${(memSelf.mem_weight==weight) ? 'selected' : ''}>${weight}公斤</option>
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
		                            <label for="mem_emotion">感情狀況:</label> 
		                            <select class="" name="mem_emotion" id="mem_emotion">
		                              <option value="${memSelf.mem_emotion}" selected>請選擇:</option>
		                              <option value="單身" ${memSelf.mem_emotion=='單身' ?'selected':''}>單身</option>
		                              <option value="穩定交往中" ${memSelf.mem_emotion=='穩定交往中' ?'selected':''}>穩定交往中</option>
		                              <option value="已婚" ${memSelf.mem_emotion=='已婚' ?'selected':''}>已婚</option>
		                              <option value="離婚" ${memSelf.mem_emotion=='離婚' ?'selected':''}>離婚</option>
		                              <option value="一言難盡" ${memSelf.mem_emotion=='一言難盡' ?'selected':''}>一言難盡</option>
		                              <option value="保密" ${memSelf.mem_emotion=='保密' ?'selected':''}>保密</option>
		                            </select>
		                             <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_emotion}</span></c:if> 
		                          </div>
		               		 </td>
		                </tr>         
 		                <tr>
							<td>          
 		                          
		                          <div class="form-group">
		                            <label for="mem_contact">交往關係:</label> 
		                            <select class="" name="mem_contact" id="mem_contact">
		                              <option value="${memSelf.mem_contact}" selected>請選擇:</option>
		                              <option value="其他友誼" ${memSelf.mem_contact=='其他友誼' ?'selected':''}>其他友誼</option>
		                              <option value="談心好友" ${memSelf.mem_contact=='談心好友' ?'selected':''}>談心好友</option>
		                              <option value="男女朋友" ${memSelf.mem_contact=='男女朋友' ?'selected':''}>男女朋友</option>
		                              <option value="結婚對象" ${memSelf.mem_contact=='結婚對象' ?'selected':''}>結婚對象</option>
		                              <option value="網路情人" ${memSelf.mem_contact=='網路情人' ?'selected':''}>網路情人</option>
		                            </select>
		                            <c:if test="${not empty errorMsgs}"><span style="color:red">${errorMsgs.mem_contact}</span></c:if> 
		                          </div>
  		                     </td>     
		                 </tr>     
  						<tr>
							<td>
		                          <div class="form-group">                    
										     <label for="">上傳大頭貼:</label>  
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
							<c:set var="interestList" value="上網,聽音樂,看電影,健身,看書,打球,玩遊戲,旅行"/>
							<div class="form-group">
                            	<label for="">興趣</label>
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
	                           
	                            <input type="submit" class="btn btn-default set-login-btn" value="提交">
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