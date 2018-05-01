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
                                    <span>　星　座　愛　情　分　析　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>覺得朋友適合這類型主題記得告訴他一起出來認識</span>
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
                                    <span>　海　外　主　題　曲　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>曾在海外工作生活，或有留學遊學經驗的您</span>
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
                                    <span>　料　理　狂　想　派　對　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>美食+好友，讓你一次擁有！想抓住另一半的心，就要先抓住他的胃！</span>
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
                                        <h2 class="title">我愛西門町</h2>
                                        <p class="desc">　　其實除了看電影以外我真的不喜歡去西門町但，發現西門町有個世外桃源紅樓後方有ㄧ片酒吧可以悠閒的聚聚聊天，啜小酒我對他們的水煙還蠻感興趣的改天來去......</p>
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
                                        <h2 class="title">心好累</h2>
                                        <p class="desc">　　我想要好好過日子我想將你遺忘但為何你跟小三卻要出現在我夢裡好真實好清楚的夢境心好痛好痛我已經退讓了還要我怎樣可不可以不要再來打擾我的生活，你說......</p>
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
                                        <h2 class="title">這是食物</h2>
                                        <p class="desc">　　Wow~~看得出這像鏡面一樣被雕琢光滑璀璨的</p>
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
                                        <h2 class="title">空虛寂寞覺得冷</h2>
                                        <p class="desc">　　空虛寂寞覺得冷作雞都會！ ... 是它說座機太苦悶，做的沒興趣，我只是安慰她而已，妳也是雞，還是隻老母雞，你有沒有曾幾何時覺得空虛寂寞，覺得冷？</p>
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
                                        <h2 class="title">好消息真開心</h2>
                                        <p class="desc">　　最近與許多久未見的朋友見面，都異口同聲的說，我變瘦了， 其實，是生病了，是壓力大導致的!還好經醫生的治療， 只是，短暫的新陳代謝出問題，所幸無大礙!</p>
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
			console.log("popular連線成功");
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
			console.log("popular離線成功");
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