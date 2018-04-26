<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.MemberService" %>
<%@ page import="com.member.model.MemberVO" %>
<%@ page import="com.friends_list.model.*" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.Set"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <title>Title Page</title>


    <style type="text/css">
    		 .set-col-sm-bgcolor{
      background-color: rgba(255,220,220,1);
    
    }
  .set-col-sm-font{
      margin-bottom: 15px;
      font-size: 18px;
      letter-spacing: 5px;
  }
  

      
     .set-title{
      background-color: rgb(255,220,220);
      padding-right: 10px;
      padding-left: 10px;
      padding-top: 20px;
      padding-bottom: 20px;
      border-radius: 5px;

    }
       /*大頭貼*/
    .set-photostick{
      width: 320px;
      height: 299px;
      border-radius: 30px;
      border: 1.5px solid;
      margin-top: 45px;
    }

    .item-imgarea{
        /*padding: 49.5px;*/
        padding-top: 33.5px;
        padding-bottom: 43.5px;
        padding-left: 5px;
    }
    .set-icon{
      margin-top: 15px;
      margin-left: 10px;
      width: 65px;
      /*height: 8%;*/
    }
    
      /*按鈕*/
    .item-imgarea img:hover {
      background: #000;
      color: #fff;
      /*transition:0.5s all;*/
    }


    
    /*家好友左邊margin*/
    .set-icon-left{
      margin-left: 40px;
    }
    
    .set-page{
      margin-top: 20px;
      margin-right: 0px;
      margin-left: 0px;
      margin-bottom: 0px;
    }


    /*表格格式*/
    .set-table th{
      height: 45px;
      width: 35%;
      
    }
  
    .set-tabs-margin{
      padding: 15px;
    }

    /*固定內容面版大小*/
    #tab-control-panle{
      margin-top: 15px;
      margin-left: 15px;
      margin-left: 15px;
      width: 100%;
      height: 100%;
    }

    body{
      font-family: "微軟正黑體";
    }
    
    </style>
  
  </head>
    <body>
    <%
    	String mem_no = request.getParameter("mem_no");
    	String session_mem_no = null;
    	MemberService ms = new MemberService();
    	MemberVO getoneMemberData = ms.getOneMem(mem_no);
    	request.setAttribute("getoneMemberData", getoneMemberData);
    	
    	if(session.getAttribute("memSelf") != null){
    		session_mem_no = ((MemberVO)session.getAttribute("memSelf")).getMem_no();
    	}
    	if(session.getAttribute("memSelf") == null){
    		
    		session.setAttribute("personal_mem_no", mem_no);
    	}
    	if(mem_no == null){
    		response.sendRedirect(request.getContextPath()+"/front_end/index.jsp");
    	}	
    %>
    <!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
<!-- //header-->

	<script type="text/javascript">
		var url = "<%=request.getContextPath()%>/friends/firlist.do";		   
		   $(function () {
	            $('#addfri').click(function () {
	                $.ajax({
	                    url: url,
	                    type: "POST",
	                    data:{
	                    	"action": $("#action").val(),
	                    	"other": $("#other").val(),
	                    	"self": $("#self").val(),
	                    	"ismemfri":$("#ismemfri").val()
	                    },
	                    dataType: 'json',
	                    success: function (data) {
	                        console.log(data);
	                        alert(data.havebeenjoin);
	
	                    },
	                    error: function(xhr) {
	                
						}
	                });
	            });
	            
	        });
		   
		   $(function () {
			   var prourl = "<%=request.getContextPath()%>/accpre/accpre.do"; 
	           $('#send-pro').click(function(){
	               $.ajax({
	                   url: prourl,
	                   type: "POST",
	                   data:{
	                   	"action": $("#action1").val(),
	                   	"other": $("#other").val(),
	                   	"self": $("#self").val(),
	                   	"accrep_reason": $("#accrep_reason").val(),
	                   	"accrep_cnt": $("#accrep_cnt").val()
	                   },
	                   dataType: 'json',
	                   success: function (data) {
	                       console.log(data.isExist);
	                       alert(data.isExist);   
	                   },
	                   error: function(xhr) {
	                   	alert("錯誤");
	                   	alert(xhr.status);
						}
	               });
	           });  
		   });
		   
		   
	</script>
	
	<%
	FriendsListVO flvo = null;
	String getFrilist_modify = null;
	Boolean isFriend = false;
	String other = mem_no;
	Integer count = 0;
	String judgefri = null;
	%>
	
	<c:if test="${not empty memSelf}">
		
		<%	
			MemberVO memvo = (MemberVO)session.getAttribute("memSelf");
			String self = memvo.getMem_no();
			String str = null;
			FriendsService fs = new FriendsService();
			isFriend = fs.checkisfri(self, other, count);
				str = isFriend.toString();
			if(isFriend == true){
				flvo = new FriendsListVO();
				flvo = fs.getOne(self, other);
				str = isFriend.toString();
			}
			if(flvo != null){
				judgefri = flvo.getFrilist_modify();
			}else{
				judgefri = "加好友";
			}	
		%>
			<%=str%>
			<%if(str.equals("true")){%>
			<%=flvo.getFrilist_modify()%>
			<%}%>
			
	</c:if>

    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
                
                <div class="col-xs-12 col-sm-1"></div> 
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                

                   		
                       <div class="col-xs-12 col-sm-4 set-col-sm-font">
                              <div class="item item-imgarea">
                                 <img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${getoneMemberData.mem_no}" class="set-photostick">
								<p id="p"></p>
                                <c:if test="${not empty memSelf}">
                                	<%if(!(mem_no.equals(session_mem_no))) {%>
                        				<c:if test="${not empty memSelf}">
                        					<button data-toggle="modal" class="btn btn-default" id ="addfri" onclick="sendaddfri();">加入好友</button>
                              				<input type="hidden" id="other" name="other" value="<%=mem_no%>">
                              			   <input type="hidden" id="self" name="self" value="${memSelf.mem_no}">
			                                 <input type="hidden" id="isfri" name="self">
			                                 <input type="hidden" id="ismemfri" name="ismemfri" value="<%=judgefri%>">
			                          		 <input type="hidden" id="action" name="action" value="getaddfri">
			                          		 <input type="hidden" id="type" value="sendAdd">
	
			                           		<a href='#modal-id' data-toggle="modal" class="btn btn-default">檢舉會員</a>
												<div class="modal fade" id="modal-id">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
																<h4 class="modal-title">檢舉原因
																<select name="accrep_reason" id="accrep_reason">
																	<option value="" selected>請選擇:</option>
																	<option value="違規大頭貼">違規大頭貼</option>
																	<option value="假資料">假資料</option>
																	<option value="詐騙">詐騙</option>
																	<option value="騷擾">騷擾</option>
																	<option value="其他">其他</option>
																</select>
																</h4>
															</div>
															<div class="modal-body">
																	<textarea rows="4" cols="60" name="accrep_cnt" id="accrep_cnt"></textarea>
															</div>
															<input type="hidden" id="action1" name="action1" value="prohibit">
															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
																<button type="button" class="btn btn-primary" id="send-pro" data-dismiss="modal">送出</button>
															</div>
														</div>
													</div>
												</div>
                           					</c:if>
                           				<%}%>
                           			</c:if>
                           		<c:if test="${empty memSelf}">
                           		<button  class="btn btn-default">加入好友</button>
                           		<button  class="btn btn-default">檢舉會員</button>
                           		</c:if>
                         
                              </div>
                        </div>
                

                       <div class="col-xs-12 col-sm-6 set-col-sm-font">
                            <div>
                            <h1 class="set-title">個人基本資料</h1>
                            </div>
                                <div class="item">
                                  <table class="set-table">
                                    <tbody>
                                    <tr>
                                      <th>姓名</th>
                                      <td>${getoneMemberData.mem_name}</td>
                                    </tr>
                                    <tr>
                                      <th>性別</th>
                                      <td>${getoneMemberData.mem_gender}</td>
                                     </tr>
                                     <tr>
                                      <th>生日</th>
                                      <td>${getoneMemberData.mem_birthday}</td>
                                     </tr>
                                     <tr>
                                      <th>血型</th>
                                      <td>${getoneMemberData.mem_bloodtype}型</td>
                                     </tr>
                                     <tr>
                                      <th>身高</th>
                                      <td>${getoneMemberData.mem_height}</td>
                                     </tr>
                                     <tr>
                                      <th>體重</th>
                                      <td>${getoneMemberData.mem_weight}</td>
                                     </tr>
                                     <tr>
                                      <th>地址</th>
                                      <td>${getoneMemberData.mem_county}</td>
                                     </tr>
                                     <tr>
                                      <th>感情</th>
                                      <td>${getoneMemberData.mem_emotion}</td>
                                     </tr>
                                    </tbody>
                                    
                                  </table>
                                </div>
                       </div>

                    

                    <div class="col-xs-12 col-sm-10 set-col-sm-bgcolor set-col-sm-font">
                                <div role="tabpanel">
                          <!-- 標籤面板：標籤區 -->
                          <ul class="nav nav-tabs " role="tablist">
                              <li role="presentation" class="active">
                                  <a class="link-font-color" href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><p class="p-font-color">自我介紹</p></a>
                              </li>
                              <li role="presentation">
                                  <a class="link-font-color" href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><p class="p-font-color">我的興趣</p></a>
                              </li>
                              <li role="presentation">
                                  <a class="link-font-color" href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab"><p class="p-font-color">收到禮物</p></a>
                              </li>
                          </ul>

                          <!-- 標籤面板：內容區 -->
                          <div class="tab-content " id="tab-control-panle">
                              <div role="tabpanel" class="tab-pane active" id="tab1">
								<div>
									<p>${getoneMemberData.mem_intro}</p>
								</div>
                              </div>
                              <div role="tabpanel" class="tab-pane" id="tab2">
                            	<c:forEach var="interest" items="${getoneMemberData.mem_interest}">
                            		${interest}
                            	</c:forEach>
                            	
                              </div>
                              <div role="tabpanel" class="tab-pane" id="tab3">Lorem ipsum dolor sit amet.</div>
                               

                           </div>
                           
                        </div>
                


                    </div>
                    <div class="col-xs-12 col-sm-1"></div> 

            </div>
			      <div class="col-xs-12 col-sm-1"></div>
		    </div> 		
  		</div>
  	</div>
    

	<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="member_modify_page.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>

<!-- FOOTER -->
<%-- <jsp:include page="/front_end/footer.jsp"></jsp:include> --%>
<!-- FOOTER END-->
	</body>
</html>