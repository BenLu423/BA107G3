<%@page import="jdk.nashorn.internal.runtime.ListAdapter"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.MemberService"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<!--交友搜尋頁面-->
<style>
<!--
.btn-group .btn{
height: 34px;
}

-->
</style>
</head>
<body>
		
		
		
	
			<%
				MemberService ms = new MemberService();
				List<MemberVO> getallMember = new ArrayList<MemberVO>();
				getallMember = ms.getallMem();
				application.setAttribute("getallMember", getallMember);
			%>
			
	
	<!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
	<!-- //header-->
		

	  <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content set-center">
                <div class="content-top"></div>
               	  <div class="col-xs-12 col-sm-8 col-sm-offset-2 set-login">
               	  <div class="col-xs-12 col-sm-12">
					        <div class="input-group searchBar" id="adv-search">
					  
			                <div class="input-group-btn">
			                    <div class="btn-group set-search" role="group" style="margin-left:25%">
			                        <div class="dropdown dropdown-lg">
			                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret" height="34px"></span></button>
			                            <div class="dropdown-menu dropdown-menu-right" role="menu">
			                            <%--複合查詢--%>
			                            <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/member/mem.do" method="post">
			                                 <h3>複合查詢</h3>
			                                 
			                                  <div class="form-group">
			                                    <label for="">性別</label>
			                                    	男<input class="" type="radio" name="mem_gender" id="mem_gender" value="男" selected>
					                         		女<input class="" type="radio" name="mem_gender" id="mem_gender" value="女">
			                                  </div>
	                                 
			                                   <div class="form-group">
			                                    <label for="">年齡</label>
			                               			  <select class="" name="mem_age">
			                                        <option value="" selected>請選擇:</option>
			                                        <option value="20">二十歲以下</option>
					                                <option value="30">二十歲至三十歲</option>
					                                <option value="40">三十歲至四十歲</option>
					                                <option value="50">四十歲以上</option>
			                                    </select>
			                                  </div>
			                                  
			                                  <div class="form-group">
			                                    <label for="">身高</label>
			                                   	<select name="mem_height1" id="mem_height1">
			                                   		<option value="" selected>請選擇:</option>
			                                   	</select>
			                                   	~
			                                   	<select name="mem_height2" id="mem_height2">
			                                   		<option value="" selected>請選擇:</option>
			                                   	</select>
			                                  </div>
			                                  
			                               
			                                  <div class="form-group">
			                                    <label for="">體重</label>
			                                   	<select name="mem_weight1" id="mem_weight1">
			                                   		<option value="" selected>請選擇:</option>
			                                   	</select>
			                                   	~
			                                   	<select name="mem_weight2" id="mem_weight2">
			                                   		<option value="" selected>請選擇:</option>
			                                   	</select>
			                                  </div>
			                             
			                                  <div class="form-group">
			                                    <label for="mem_county">地區</label>
			                                    <select class="" name="mem_county">
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
			                                    <label for="">感情狀況</label>
			                                    	<input class="" type="checkbox" name="mem_emotion1" value="單身">單身
					                         		<input class="" type="checkbox" name="mem_emotion2" value="穩定交往中">穩定交往中
					                         		<input class="" type="checkbox" name="mem_emotion3" value="已婚">已婚
					                         		<input class="" type="checkbox" name="mem_emotion4" value="離婚">離婚
					                         		<input class="" type="checkbox" name="mem_emotion5" value="一言難盡">一言難盡
					                         		<input class="" type="checkbox" name="mem_emotion6" value="保密">保密
			                                  </div>
			                                  
			                                  <div class="form-group">
			                                    <label for="">交往關係</label>
			                                    	<input class="" type="checkbox" name="mem_contact1" value="其他友誼">其他友誼
					                         		<input class="" type="checkbox" name="mem_contact2" value="談心好友">談心好友
					                         		<input class="" type="checkbox" name="mem_contact3" value="男女朋友">男女朋友
					                         		<input class="" type="checkbox" name="mem_contact4" value="結婚對象">結婚對象
					                         		<input class="" type="checkbox" name="mem_contact5" value="網路情人">網路情人
			                                  </div>
			                                
			                                  
			                                  
			                                  
			                                  <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			                                  <input type="hidden" name="action" value="listMems_ByCompositeQuery">
			                                </form>
			                            </div>
			                        </div>
			                        <%--模糊查詢--%>
			                        <form action="<%=request.getContextPath()%>/member/mem.do" method="POST">
					                        <div>
					                        <input type="text" class="form-control" placeholder="請輸入暱稱:" name="mem_name"/>
					                        </div>
					                        <div>
					                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
					                        </div>
					                        <div>
					                        <input type="hidden" name="action" value="blur_search">
					                        </div>
			                        </form>
			                        </div>
			                </div>
			                </div>
		               	  	

               	  </div>
               	  <br><br><br><br><br>
             
               	  
               	<div class="col-xs-12 col-sm-12" align ="center">
               		 		<c:if test="${not empty memSelf.mem_no}">
	    						<%--
	    							MemberVO memvo = (MemberVO)session.getAttribute("memSelf");
	    							String mem_no = memvo.getMem_no();
	    							List<MemberVO> getallmem = (List<MemberVO>) application.getAttribute("getallMemberData");
	    							for(int i = 0; i < getallmem.size(); i++){
	    								if(mem_no.equals(getallmem.get(i).getMem_no())){
	    									getallmem.remove(i);
	    									break;
	    								}
	    							}
	    						--%>
	    						
	    							<%--
	    								MemberVO memvo = (MemberVO)session.getAttribute("memSelf");
	    								String mem_no = memvo.getMem_no();
	    								List<MemberVO> getallmem = (List<MemberVO>) application.getAttribute("getallMemberData");
	    							--%>
	    						
    						</c:if>
    						
    					
		
		
		
						<c:if test="${empty getallMemberData1 && empty getallMemberData}">
       					<c:forEach var="memData" items="${getallMember}">	
       						<div class="col-xs-12 col-sm-4">
								<div class="item">
			
									<a href="<%=request.getContextPath()%>/front_end/member/personal_page.jsp?mem_no=${memData.mem_no}">
									<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memData.mem_no}" width="150px" height="150px">
									</a>
									<P>暱稱:${memData.mem_name}</P>
									<p>性別:${memData.mem_gender}</p>	
									<p>禮物:${memData.mem_receive_gift}</p>		
								</div>
							</div>
						</c:forEach>
					</c:if>
		
	
					<c:if test="${empty getallMemberData1}">
       					<c:forEach var="memData" items="${getallMemberData}">	
       						<div class="col-xs-12 col-sm-4">
								<div class="item">
				
									<a href="<%=request.getContextPath()%>/front_end/member/personal_page.jsp?mem_no=${memData.mem_no}">
									<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memData.mem_no}" width="150px" height="150px">
									</a>
									<P>暱稱:${memData.mem_name}</P>
									<p>性別:${memData.mem_gender}</p>	
									<p>禮物:${memData.mem_receive_gift}</p>		
								</div>
							</div>
						</c:forEach>
					</c:if>	
					
					<c:if test="${(not empty getallMemberData1)}">
       					<c:forEach var="memData" items="${getallMemberData1}">	
       						<div class="col-xs-12 col-sm-4">
								<div class="item">
									<p>編號:${memData.mem_no}</p>
									<a href="<%=request.getContextPath()%>/front_end/member/personal_page.jsp?mem_no=${memData.mem_no}">
									<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memData.mem_no}" width="150px" height="150px">
									</a>
									<P>暱稱:${memData.mem_name}</P>
									<p>性別:${memData.mem_gender}</p>	
									<p>禮物:${memData.mem_receive_gift}</p>		
								</div>
							</div>
						</c:forEach>
					</c:if>		
					
					
					
					
       			</div>
				</div>
		    </div> 
		    <div class="col-xs-12 col-sm-1"></div>		
  			</div>
  		</div>
	


	<!-- FOOTER -->
	<jsp:include page="/front_end/footer.jsp"></jsp:include>
	<!-- FOOTER END-->
	<script type="text/javascript">
		window.onload = function() {
			var height1 = document.getElementById("mem_height1");
			var height2 = document.getElementById("mem_height2");

	          for(var i = 120; i <= 220; i++){
	            var text = i + "公分";
	            var value = i;
	            var opt1 = new Option(text,value);
	            var opt2 = new Option(text,value);
	            height1.add(opt1);
	            height2.add(opt2);
	          }
	          var weight1 = document.getElementById("mem_weight1");
	          var weight2 = document.getElementById("mem_weight2");
	          	for(var i = 30; i <= 220; i++){
	          		var text = i + "公斤";
	          		var value = i;
	          		var opt1 = new Option(text,value);
	          		var opt2 = new Option(text,value);
	          		weight1.add(opt1);
	          		weight2.add(opt2);
	          	}
		}
	
	</script>
</body>
</html>