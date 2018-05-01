<%@page import="com.member.model.MemberService"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Toast</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<style>
.dynamicwall{
	margin: 10px;
}
</style>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <!-- <div class="content-top"></div> -->
            <!-- Carousel -->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel1.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="First slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>�@�P�@�y�@�R�@���@���@�R�@</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>ı�o�B�;A�X�o�����D�D�O�o�i�D�L�@�_�X�ӻ{��</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                    <div class="item">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel2.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="Second slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>�@���@�~�@�D�@�D�@���@</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>���b���~�u�@�ͬ��A�Φ��d�ǹC�Ǹg�窺�z</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                    <div class="item">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel3.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="Third slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>�@�ơ@�z�@�g�@�Q�@���@��@</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>����+�n�͡A���A�@���֦��I�Q���t�@�b���ߡA�N�n�����L���G�I</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                </div>
                <!-- Controls -->

                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
            <!-- /carousel -->
            
            <!-- friends -->
 			<%
 				MemberService ms = new MemberService();
 				List<MemberVO> popularList = new ArrayList<MemberVO>();
 				popularList = ms.pop();
 				pageContext.setAttribute("popularList", popularList);
 			%>
            <div class="col-xs-12 col-sm-6">
            <%int addidc = 0; %>
                <div class="tiles row">
                	<c:forEach var="pop" items="${popularList}">
                	<a href="<%=request.getContextPath()%>/front_end/member/personal_page.jsp?mem_no=${pop.mem_no}" id="p<%=addidc%>">
					<img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${pop.mem_no}" width="160px" height="160px" class="dynamicwall">
					</a>
					<%addidc++;%>
					</c:forEach> 
                </div>    
            </div>
            <div class="col-xs-12 col-sm-6">
                <div class="index-friend-text row">
                   <img src="<%=request.getContextPath()%>/front_end/res/img/index/index-friend.jpg">
                </div>
            </div>
            <!-- //friends -->
            

            <!-- diary -->
            <div class="col-xs-12 col-sm-12">
                <div class="row">
                    <div class="col-xs-12 col-sm-6">
                        <div class="index-diary-text row">
                            <img src="<%=request.getContextPath()%>/front_end/res/img/index/index-diary.jpg">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="row">
                            <ul class="event-list">
                                <li>
                                    <img src="res/img/index/index-diary-1.jpg">
                                    <div class="info">
                                        <h2 class="title">�ڷR����m</h2>
                                        <p class="desc">�@�@��갣�F�ݹq�v�H�~�گu�������w�h����m���A�o�{����m���ӥ@�~�緽���ӫ�観�����s�a�i�H�y�����E�E��ѡA��p�s�ڹ�L�̪��������Z�P���쪺��Ѩӥh......</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index/index-diary-2.jpg">
                                    <div class="info">
                                        <h2 class="title">�ߦn��</h2>
                                        <p class="desc">�@�@�ڷQ�n�n�n�L��l�ڷQ�N�A��Ѧ�����A��p�T�o�n�X�{�b�ڹڸ̦n�u��n�M�����ڹҤߦn�h�n�h�ڤw�g�h���F�٭n�ګ�˥i���i�H���n�A�ӥ��Z�ڪ��ͬ��A�A��......</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index/index-diary-3.jpg">
                                    <div class="info">
                                        <h2 class="title">�o�O����</h2>
                                        <p class="desc">�@�@Wow~~�ݱo�X�o���譱�@�˳Q�J�Z���ƽA����</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index/index-diary-4.jpg">
                                    <div class="info">
                                        <h2 class="title">�ŵ�I��ı�o�N</h2>
                                        <p class="desc">�@�@�ŵ�I��ı�o�N�@�����|�I ... �O�����y���ӭW�e�A�����S����A�ڥu�O�w���o�Ӥw�A�p�]�O���A�٬O���ѥ����A�A���S�����X���ı�o�ŵ�I��Aı�o�N�H</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index/index-diary-5.jpg">
                                    <div class="info">
                                        <h2 class="title">�n�����u�}��</h2>
                                        <p class="desc">�@�@�̪�P�\�h�[�������B�ͨ����A�����f�P�n�����A���ܽG�F�A ���A�O�ͯf�F�A�O���O�j�ɭP��!�٦n�g��ͪ��v���A �u�O�A�u�Ȫ��s���N�¥X���D�A�ҩ��L�jê!</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    
                </div>
            </div>
            <!-- //diary -->
        </div>    
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>
<script type="text/javascript">
	
	$(document).ready(function(){
		var popMyPoint = "/PopularWallWS";
		var pophost = window.location.host;
		var poppath = window.location.pathname;
		var popwebCtx = poppath.substring(0, poppath.indexOf('/', 1));
		var popendPointURL = "ws://" + window.location.host + popwebCtx + popMyPoint;
		var popwebSocket;	
		popwebSocket = new WebSocket(popendPointURL);
		popwebSocket.onopen = function(event){
			console.log("popular�s�u���\");
		};
		popwebSocket.onmessage = function(event){
			console.log("popMessage");
			var popjson = JSON.parse(event.data);
			console.log(typeof(popjson.mem_no[0]));
			console.log(popjson.mem_no[0][0]);
			
			$("#p0").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][0]);
			$("#p0 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][0]);
			
			$("#p1").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][1]);
			$("#p1 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][1]);
			
			$("#p2").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][2]);
			$("#p2 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][2]);
			
			$("#p3").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][3]);
			$("#p3 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][3]);
			
			$("#p4").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][4]);
			$("#p4 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][4]);
			
			$("#p5").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][5]);
			$("#p5 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][5]);
			
			$("#p6").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][6]);
			$("#p6 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][6]);
			
			$("#p7").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][7]);
			$("#p7 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][7]);
			
			$("#p8").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][8]);
			$("#p8 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][8]);
			
			$("#p9").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][9]);
			$("#p9 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][9]);
			
			$("#p10").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][10]);
			$("#p10 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][10]);
			
			$("#p11").attr("href","/BA107G3/front_end/member/personal_page.jsp?mem_no="+popjson.mem_no[0][11]);
			$("#p11 img").attr("src","/BA107G3/memgetpic/mem.do?mem_no="+popjson.mem_no[0][11]);
			
	
		};
		popwebSocket.onerror = function(event){
			
		};
		popwebSocket.onclose = function(event){
			console.log("popular���u���\");
		};
		
	});
	$(window).on('beforeunload',function(){

		console.log("disconnect ws:popwebSocket")
		popwebSocket.close();	
	});

</script>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>