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
<!--��ͷj�M����-->
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
			                            <%--�ƦX�d��--%>
			                            <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/member/mem.do" method="post">
			                                 <h3>�ƦX�d��</h3>
			                                 
			                                  <div class="form-group">
			                                    <label for="">�ʧO</label>
			                                    	�k<input class="" type="radio" name="mem_gender" id="mem_gender" value="�k" selected>
					                         		�k<input class="" type="radio" name="mem_gender" id="mem_gender" value="�k">
			                                  </div>
	                                 
			                                   <div class="form-group">
			                                    <label for="">�~��</label>
			                               			  <select class="" name="mem_age">
			                                        <option value="" selected>�п��:</option>
			                                        <option value="20">�G�Q���H�U</option>
					                                <option value="30">�G�Q���ܤT�Q��</option>
					                                <option value="40">�T�Q���ܥ|�Q��</option>
					                                <option value="50">�|�Q���H�W</option>
			                                    </select>
			                                  </div>
			                                  
			                                  <div class="form-group">
			                                    <label for="">����</label>
			                                   	<select name="mem_height1" id="mem_height1">
			                                   		<option value="" selected>�п��:</option>
			                                   	</select>
			                                   	~
			                                   	<select name="mem_height2" id="mem_height2">
			                                   		<option value="" selected>�п��:</option>
			                                   	</select>
			                                  </div>
			                                  
			                               
			                                  <div class="form-group">
			                                    <label for="">�魫</label>
			                                   	<select name="mem_weight1" id="mem_weight1">
			                                   		<option value="" selected>�п��:</option>
			                                   	</select>
			                                   	~
			                                   	<select name="mem_weight2" id="mem_weight2">
			                                   		<option value="" selected>�п��:</option>
			                                   	</select>
			                                  </div>
			                             
			                                  <div class="form-group">
			                                    <label for="mem_county">�a��</label>
			                                    <select class="" name="mem_county">
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
			                                    <label for="">�P�����p</label>
			                                    	<input class="" type="checkbox" name="mem_emotion1" value="�樭">�樭
					                         		<input class="" type="checkbox" name="mem_emotion2" value="í�w�橹��">í�w�橹��
					                         		<input class="" type="checkbox" name="mem_emotion3" value="�w�B">�w�B
					                         		<input class="" type="checkbox" name="mem_emotion4" value="���B">���B
					                         		<input class="" type="checkbox" name="mem_emotion5" value="�@������">�@������
					                         		<input class="" type="checkbox" name="mem_emotion6" value="�O�K">�O�K
			                                  </div>
			                                  
			                                  <div class="form-group">
			                                    <label for="">�橹���Y</label>
			                                    	<input class="" type="checkbox" name="mem_contact1" value="��L�ͽ�">��L�ͽ�
					                         		<input class="" type="checkbox" name="mem_contact2" value="�ͤߦn��">�ͤߦn��
					                         		<input class="" type="checkbox" name="mem_contact3" value="�k�k�B��">�k�k�B��
					                         		<input class="" type="checkbox" name="mem_contact4" value="���B��H">���B��H
					                         		<input class="" type="checkbox" name="mem_contact5" value="�������H">�������H
			                                  </div>
			                                
			                                  
			                                  
			                                  
			                                  <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			                                  <input type="hidden" name="action" value="listMems_ByCompositeQuery">
			                                </form>
			                            </div>
			                        </div>
			                        <%--�ҽk�d��--%>
			                        <form action="<%=request.getContextPath()%>/member/mem.do" method="POST">
					                        <div>
					                        <input type="text" class="form-control" placeholder="�п�J�ʺ�:" name="mem_name"/>
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
									<P>�ʺ�:${memData.mem_name}</P>
									<p>�ʧO:${memData.mem_gender}</p>	
									<p>§��:${memData.mem_receive_gift}</p>		
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
									<P>�ʺ�:${memData.mem_name}</P>
									<p>�ʧO:${memData.mem_gender}</p>	
									<p>§��:${memData.mem_receive_gift}</p>		
								</div>
							</div>
						</c:forEach>
					</c:if>	
					
					<c:if test="${(not empty getallMemberData1)}">
       					<c:forEach var="memData" items="${getallMemberData1}">	
       						<div class="col-xs-12 col-sm-4">
								<div class="item">
									<p>�s��:${memData.mem_no}</p>
									<a href="<%=request.getContextPath()%>/front_end/member/personal_page.jsp?mem_no=${memData.mem_no}">
									<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memData.mem_no}" width="150px" height="150px">
									</a>
									<P>�ʺ�:${memData.mem_name}</P>
									<p>�ʧO:${memData.mem_gender}</p>	
									<p>§��:${memData.mem_receive_gift}</p>		
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
	            var text = i + "����";
	            var value = i;
	            var opt1 = new Option(text,value);
	            var opt2 = new Option(text,value);
	            height1.add(opt1);
	            height2.add(opt2);
	          }
	          var weight1 = document.getElementById("mem_weight1");
	          var weight2 = document.getElementById("mem_weight2");
	          	for(var i = 30; i <= 220; i++){
	          		var text = i + "����";
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