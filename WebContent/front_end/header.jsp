<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="giftSvc" scope="page" class="com.gift.model.GiftService"/>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	
	<title>header</title>
		
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/index_chat.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/chat.js" charset="UTF-8"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/header.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_friends.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_diary.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_chat.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/event.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/jquery.countdown.css"> 
	<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.legacy.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/custombox/custombox.min.css">
	
</head>
<body>
<%
	request.setAttribute("requsetURL", request.getRequestURI());
	String path = (String)request.getAttribute("requsetURL");
	Object isSession = session.getAttribute("memSelf");
	if(isSession == null && !(path.equals("/BA107G3/front_end/login.jsp"))){
		session.setAttribute("location", request.getRequestURI());
	}
%>




<!-- header -->
<nav class="navbar navbar-default navbar-fixed-top mynavbar">
<div class="container-fluid">      
<div class="row">
     <div class="col-xs-12 col-sm-1">
     </div>
     <div class="col-xs-12 col-sm-2">
		<div class="logo">
             <a href="<%=request.getContextPath()%>/front_end/index.jsp">
             <img src="<%=request.getContextPath()%>/front_end/res/img/logo/Logo.gif">
             
             </a>
         </div>
     </div>
     
     <div class="col-xs-12 col-sm-9">
         <div class="row">
         <%--登入成功 --%>
         <c:if test="${not empty memSelf}" var="true" scope="session">
             <div class="col-xs-12 col-sm-3 col-sm-offset-9 login">
                     <div class="btn-group btn-group-justified mymenu-icon">        
                     	 
	                 	 <div class="btn-group login-photo">
                           <%--   <img src="<%=request.getContextPath()%>/DBGifReader4?mem_id=<%=mem_id%>">--%>
                          	        
                           		 <img id="index-photo-stick" src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${memSelf.mem_no}">
                         	  
                         </div>   
                     	 
                         <div class="btn-group">
                         	<a href="<%=request.getContextPath()%>/front_end/member/modify_personal_data_main_page.jsp">          
                             <button type="button" class="btn btn-default mybutton-icon">
                                 	${memSelf.mem_name}
                             </button>
                             </a>      
                         </div>    
                                                
                         <div class="btn-group">
                         <form action="<%=request.getContextPath()%>/member/mem.do" method="post">
                             <button type="submit" class="btn btn-default mybutton-icon" name="linked_logon_page">
                                 	登出
                             </button>        
                              	<input type="hidden" name="action" value="getlinked_logon_page">
                    	 </form>  
                         </div>                           
                                         
                         <div class="btn-group" id="myCart">
                             <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            	 購物車 <span class="badge" id="cartSum">${fn:length(orderDetail)}</span>
                             </button>
                             <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                 <c:forEach var="detail" items="${orderDetail}">
                                 <c:set var="gift" value="${giftSvc.getOneGift(detail.key.gift_no)}" scope="page"/>
                                 <li>
	                                 <a href="#">
		                                 <span>
			                                 <img style="height:30px;" src="<%=request.getContextPath()%>/DBGifReader4?table=GIFT&gift_no=${gift.gift_no}">
		                                 </span>
		                                 <span>　${gift.gift_name}　${detail.key.giftod_amount}個</span>
	                                 </a>
                                 </li>
                                 </c:forEach>
                                 <li role="separator" class="divider"></li>
                                 <li>total: $${orderMoney}</li>
                                 <li><a href="<%=request.getContextPath()%>/front_end/gift/gift_order.jsp">前往購物車</a></li>
                             </ul>                                
                         </div>
                         
                         <div class="btn-group">
                             <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                             	通知 <span class="badge">888</span>
                             </button>
                             <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu2" style="text-align: left">
                                    <li><a href="#">　 劉德華　　請求加為好友</a></li>
                                    <li><a href="#">　 周子瑜　　已成好友</a></li>
                                    <li><a href="#">　蠟筆小新　 已新增留言</a></li>
                                </ul>                                
                            </div>
                            <div class="btn-group btn-group-menu">
                                <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <div class="menu-icon"></div>
                                <div class="menu-icon"></div>
                                <div class="menu-icon"></div>
                                </button>
                                <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu3">
                                    <li><a href="<%=request.getContextPath()%>/front_end/member/modify_personal_data_main_page.jsp?mem_no=${memSelf.mem_no}&mem_account=${memSelf.mem_account}">帳號設定</a></li>
                                    <li><a href="#">好友管理</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">收禮清單</a></li>
                                    <li><a href="<%=request.getContextPath()%>/front_end/gift/gift_history.jsp"">贈禮紀錄</a></li>
                                    <li><a href="#">加值服務</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">我的日記</a></li>
                                    <li><a href="#">我的活動</a></li>
                                </ul>                                
                            </div>
                        </div>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
							  <li><a href="#">Regular link</a></li>
							  <li class="disabled"><a href="#">Disabled link</a></li>
							  <li><a href="#">Another link</a></li>
						</ul>                       
                </div>
                </c:if>
                
                <%-- 訪客 --%>
                <c:if test="${empty memSelf}" var="true" scope="application">
                  <div class="col-xs-12 col-sm-3 col-sm-offset-9 login">
                     <div class="btn-group btn-group-justified mymenu-icon">        
                     	 
	                 	<div class="btn-group">
		                 	<form action="<%=request.getContextPath()%>/member/mem.do" method="post">
		                     	<button type="submit" class="btn btn-default mybutton-icon" name="linked_login_page">
		                         	登入
		                    	 </button>
		                       <input type="hidden" name="action" value="getlinked_login_page">
		         	    	</form>
		                </div>       
                       </div>                        
                  </div>
                </c:if>
                
                
                    
                <div class="col-xs-12 col-sm-12">
                    <div class="collapse navbar-collapse">
                        <nav class="menu menu--iris">
                        <ul class="nav navbar-nav menu__list">
                        	 <!-- menu__item--current -->
                    	<li class="menu__item"><a href="#" class="news.html">最新公告</a></li>
                    	<li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/member/member_search_all.jsp" class="friends.html">交友搜尋</a></li> 
                        <li class="menu__item"><a href="diary.html" class="menu__link">日記專欄</a></li>
                        <li class="menu__item"><a href="event.html" class="menu__link">活動特輯</a></li>
                        <li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/gift/gift_index.jsp" class="menu__link">禮物商城</a></li>
                        <li class="menu__item"><a href="chat.html" class="menu__link">聊天室</a></li>         
                    </ul>
                    </nav>
                </div> 
                
            </div>  					
        </div>

    </div>
</div>
</div>
</nav>   
<!-- //header-->
 <div class="receive-modal init-modal">
      <a href="javascript:void(0);" onclick="Custombox.modal.close();" class="receive-close"><i class="fa fa-times"></i></a>
      <table>
      	<tr>
      		<td colspan="2" class="receiveFlash receiveHeader"><h1>送　　禮　　通　　知</h1></td>
      	</tr>
      	<tr>
      		<td style="width:100px;text-align:center;">
      			<img class="receiver" src="">
      		</td>
      		<td rowspan="3">
      			<div class="receiveAmount"></div>
				<img class="receiveGift" src="">
      		</td>
      	</tr>
      	<tr>
      		<td style="padding:0px 5px 0px 5px;text-align:center;"></td>
      	</tr>
      	<tr>
      		<td>
      			<p></p>
      		</td>
      	</tr>
      	<tr>
      		
      	</tr>
      </table>
</div>
</body>
<style>
.custombox-lock{
	overflow:inherit;
}
.receiveFlash {
    animation-duration: 1.5s;
    animation-name: receiveFlash;
    animation-iteration-count: infinite;
    animation-direction: alternate;
    animation-timing-function: ease-in-out;
}
@keyframes receiveFlash {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var i = "0";
	//go : giftOrder
    connectOrder();
	function connectOrder() {
		var goPoint = "/GiftOrderServer/${memSelf.mem_no}";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var goendPointURL = "ws://" + window.location.host + webCtx + goPoint;
		
	    console.log("connect ws:giftOrder");
		// 建立 websocket 物件
		goWebSocket = new WebSocket(goendPointURL);
		
		goWebSocket.onopen = function(event) {
			console.log("goWebSocket 成功連線");
		};

		goWebSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        var giftAction = jsonObj[0];
	        var giftVO = jsonObj[1];
	        var giftLabelList = jsonObj[2];
	        var giftReceiveVO = jsonObj[3];
	        var giftDiscountVO = jsonObj[4];
	        var modal = $('.init-modal').clone()[0];
			$( "body" ).append(modal);
	        if(giftAction.action == "receiveGift"){
// 	        	//設置新的class名稱
				$(modal).removeClass('init-modal');
	        	$(modal).addClass('new'+i);
// 	        	//設置送禮人照片與名稱
	        	$(modal).find('img:eq(0)').attr('src','${pageContext.request.contextPath}/memgetpic/mem.do?mem_no='+giftReceiveVO.mem_no_self);
// 	        	$(modal).find('table tr:eq(2) td')[0].innerText = $(memberSvc.getOneMem(giftReceiveVO.mem_no_self).mem_name);
// 	        	//設置收贈禮留言訊息
	        	$(modal).find('table tr:eq(3) p')[0].innerText = giftReceiveVO.giftr_message;
// 	        	//設置收贈禮物的照片
	        	$(modal).find('img:eq(1)').attr('src','${pageContext.request.contextPath}/DBGifReader4?table=GIFT&gift_no='+giftVO.gift_no);
// 	        	//設置收贈禮的數量
	        	$(modal).find('div')[0].innerText = 'X'+giftReceiveVO.giftr_amount;
	        	// Instantiate new modal
	        	var modalJS = new Custombox.modal({
	        		content: {
	        			effect: 'newspaper',
	        			overlay: false,
	        			width:  '400px',
	        	    	target: '.new'+i
	        		},
	        		overlay: {
	        			active: false,
	        		  }
	        	});
	        	modalJS.open();
	        	i = parseInt(i) + 1;
	        }
		};

		goWebSocket.onclose = function(event) {
			console.log("goWebSocket 已離線");
		};
	}
});
$(window).on('beforeunload',function(){
	console.log("disconnect ws:giftOrder");
	goWebSocket.close();
});
</script>
</html>