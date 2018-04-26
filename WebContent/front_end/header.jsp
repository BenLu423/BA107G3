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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_chat.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/index_chat.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/chat.js" charset="UTF-8"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/header.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_friends.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_diary.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/event.css">
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/css/jquery.countdown.css"> 
	<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.min.js"></script>
	<script src="<%=request.getContextPath()%>/front_end/js/custombox/custombox.legacy.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/css/custombox/custombox.min.css">
	
</head>
<body>
<%
	request.setAttribute("requsetURL", request.getRequestURI());
	String path = (String) request.getAttribute("requsetURL");
	Object isSession = session.getAttribute("memSelf");

if (isSession == null && (!(path.equals(request.getContextPath() + "/front_end/login.jsp"))
		&& !(path.equals(request.getContextPath() + "/front_end/member/member_register.jsp")))) {
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
								<button class="btn btn-default dropdown-toggle mybutton-icon"
									type="button" id="dropdownMenu2" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="true">
									通知 <span class="badge">0</span>
								</button>
								<%--新增 id set-fri--%>
								<ul
									class="dropdown-menu agile_short_dropdown dropdown-menu-right"
									aria-labelledby="dropdownMenu2"
									style="text-align: left; min-width: 260px;" id="set-fri">
	
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
                                    <li><a href="<%=request.getContextPath()%>/front_end/member/member_manage_friendslist.jsp">好友管理</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="<%=request.getContextPath()%>/front_end/gift/gift_receive.jsp">收禮清單</a></li>
                                    <li><a href="<%=request.getContextPath()%>/front_end/gift/gift_history.jsp">贈禮紀錄</a></li>
                                    <li><a href="<%=request.getContextPath()%>/front_end/deposit/deposit_index.jsp">加值服務</a></li>
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
                    	<li class="menu__item"><a href="#" class="menu__link">最新公告</a></li>
                    	<li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/member/member_search_all.jsp" class="menu__link">交友搜尋</a></li> 
                        <li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/map/map.jsp" class="menu__link">距離配對</a></li>         
                        <li class="menu__item"><a href="#" class="menu__link">日記專欄</a></li>
                        <li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/event/listAllEvent.jsp" class="menu__link">活動特輯</a></li>
                        <li class="menu__item"><a href="<%=request.getContextPath()%>/front_end/gift/gift_index.jsp" class="menu__link">禮物商城</a></li>
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
// $(document).ready(function() {
	var count = 0;
	
	
		var memSend_name;
		var memSend;
		var memGet;
		var MyPoint = "/MemberWS/${memSelf.mem_no}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var addfri = document.getElementById("addfri");
		var webSocket;		
		
		function sendaddfri(){
			var memSend = document.getElementById('self').value;
			var memGet = document.getElementById('other').value;
			var type = document.getElementById('type').value;
			var jsonObj = {"type": type, "memSend": memSend, "memGet": memGet};
			webSocket.send(JSON.stringify(jsonObj));	
		};
	function notice(){
		
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event){
			console.log("notice連線成功");
		};
		
		webSocket.onmessage = function(event) {
			
			var json = JSON.parse(event.data);
			console.log(json.memGet);
			console.log(json.memSend);
			console.log(json.type);
			console.log(json.memSend_name);
			var memSend_name = json.memSend_name;
			var memSend = json.memSend;
			var memGet = json.memGet;
			
			
			////////////////////////////////////////////////////////
			//		創建元素順序									  //
			//		var pname = document.createElement('p');	  //
			//		pname.innerHTML = memSend_name;	  			  //
			//		pname.setAttribute("class", "democlass");	  //
			////////////////////////////////////////////////////////
			//***********BA107G3-back要換路徑*************//
			
			var div = document.createElement('div');
			
			var li = document.createElement('li');
			var liid = "id" + count;
			li.setAttribute('id',liid);
			
			var newlinkpath = "/BA107G3/front_end/member/personal_page.jsp?mem_no="+memSend;
			var newlink = document.createElement('a');
			var link = "a" + count;
			newlink.innerHTML = "&nbsp&nbsp&nbsp&nbsp"+ memSend_name + "&nbsp&nbsp&nbsp&nbsp請求加為好友&nbsp&nbsp&nbsp&nbsp";
			newlink.setAttribute('href', newlinkpath);
			newlink.setAttribute('id', link);
			
			var add = ("addfrie" + count).toString();
			
		
			var addfri = document.createElement('button');
			addfri.setAttribute("id", add);
			addfri.setAttribute("class","btn btn-default");
			addfri.setAttribute("style","width:15%");
		
			console.log(addfri);
			
			var addspan = document.createElement('span');
			addspan.setAttribute("class","glyphicon glyphicon-ok");
			addfri.appendChild(addspan);
			//addfriend onclick
			addfri.addEventListener('click',addfriends,false);
			
			var del = "delfri" + count;
			var delfri = document.createElement('button');
			delfri.setAttribute("id", del);
			delfri.setAttribute("class","btn btn-default");
			delfri.setAttribute("style","width:15%");
			delfri.addEventListener('click',delfriends,false);

			
			var delspan = document.createElement('span');
			delspan.setAttribute("class","glyphicon glyphicon-remove");
			delfri.appendChild(delspan);
			
			
			
			var memSend_no = document.createElement('input');
			var send_no = "memSend_no" + count;
			memSend_no.setAttribute("type","hidden");
			memSend_no.setAttribute("name","memSend_no");
			memSend_no.setAttribute("value", memSend);
			memSend_no.setAttribute("id", send_no);
			console.log(memSend);
			
			
			var memGet_no = document.createElement('input');
			var get_no = "memGet_no" + count;
			memGet_no.setAttribute("type","hidden");
			memGet_no.setAttribute("name","memGet_no");
			memGet_no.setAttribute("value", memGet);
			memGet_no.setAttribute("id", get_no);
			console.log(memGet);
		
			
			var action = document.createElement('input');
			action.setAttribute("type","hidden");
			action.setAttribute("name","action");
			action.setAttribute("value", "fri");
			action.setAttribute("id","action");
			
	
			
			count++;
			div.appendChild(li);
			li.appendChild(newlink);
			li.appendChild(addfri);
			li.appendChild(delfri);
			li.appendChild(memSend_no);
			li.appendChild(memGet_no);
			li.appendChild(action);
	
	
			
			$('#set-fri').prepend(div);
		
		};
		webSocket.onerror = function(event){
		};
		webSocket.onclose = function(event) {		
		};
// 		function sendaddfri(){
// 			var memSend = document.getElementById('self').value;
// 			var memGet = document.getElementById('other').value;
// 			var type = document.getElementById('type').value;
// 			var jsonObj = {"type": type, "memSend": memSend, "memGet": memGet};
// 			webSocket.send(JSON.stringify(jsonObj));	
// 		};
		
	}
	
	notice();

	function addfriends(e){
		var t = e.currentTarget.id;
		console.log("====e.target: ",e.target);
		console.log("====e.target.id : ",e.target.id);
		console.log("====e.currentTarget: ",e.currentTarget);
		console.log("====e.currentTarget.id : ",e.currentTarget.id);
		
		var tid = t.toString();
		console.log(tid);
		var num = "";

		for(var i = 0; i < tid.length; i++){
			if(!(isNaN(tid.charAt(i)))){
			num += tid.charAt(i);
			//alert(num);
			}
		}
//		alert("num : " + num);
//		alert("typeof : " + typeof(num));
		$("#a"+num).hide();
		$("#addfrie"+num).hide();
		$("#delfri"+num).hide();

//		$("#a0").hide();
//		$("#addfrie0").hide();
//		$("#delfri0").hide();

		var url = "<%=request.getContextPath()%>/friends/firlist.do";
		var mg = "#memGet_no"+num;
		var ms = "#memSend_no"+num;

		var g = document.getElementById("memGet_no0").value;
		var s = document.getElementById("memSend_no0").value;
		console.log("---------");
		console.log(g);
		console.log(s);
		console.log("---------");
		console.log($(mg).val());
		console.log($(ms).val());	
		
			
			
			$.ajax({
				url: url,
				type: "POST",
				data:{
					"action": $("#action").val(),
					"memGet_no":$(mg).val(),
					"memSend_no":$(ms).val()
				}, 
			});
		
	};	
	
	function delfriends(e){
		var t = e.currentTarget.id;
		console.log("====e.target: ",e.target);
		console.log("====e.target.id : ",e.target.id);
		console.log("====e.currentTarget: ",e.currentTarget);
		console.log("====e.currentTarget.id : ",e.currentTarget.id);
		
		var tid = t.toString();
		console.log(tid);
		var num = "";

		for(var i = 0; i < tid.length; i++){
			if(!(isNaN(tid.charAt(i)))){
			num += tid.charAt(i);
			//alert(num);
			}
		}
//		alert("num : " + num);
//		alert("typeof : " + typeof(num));
		$("#a"+num).hide();
		$("#addfrie"+num).hide();
		$("#delfri"+num).hide();

//		$("#a0").hide();
//		$("#addfrie0").hide();
//		$("#delfri0").hide();

		var url = "<%=request.getContextPath()%>/friends/firlist.do";
		var mg = "#memGet_no"+num;
		var ms = "#memSend_no"+num;

		var g = document.getElementById("memGet_no0").value;
		var s = document.getElementById("memSend_no0").value;
		console.log("---------");
		console.log(g);
		console.log(s);
		console.log("---------");
		console.log($(mg).val());
		console.log($(ms).val());	
		
			
			
			$.ajax({
				url: url,
				type: "POST",
				data:{
					"action": "dfri",
					"memGet_no":$(mg).val(),
					"memSend_no":$(ms).val()
				}, 
			});
		
	};	
	

	
	var i = "0";
	//go : giftOrder
    connectOrder();
	function connectOrder() {
		var goPoint = "/GiftOrderServer/${memSelf.mem_no}";
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
	        if(giftAction.action == "sendGift"){
// 	        	//設置新的class名稱
				$(modal).removeClass('init-modal');
	        	$(modal).addClass('new'+i);
// 	        	//設置送禮人照片與名稱
	        	$(modal).find('img:eq(0)').attr('src','${pageContext.request.contextPath}/memgetpic/mem.do?mem_no='+giftReceiveVO.mem_no_self);
// 	        	$(modal).find('table tr:eq(2) td')[0].innerText = $(memberSvc.getOneMem(giftReceiveVO.mem_no_self).mem_name);
// 	        	//設置收贈禮留言訊息
				if(giftReceiveVO.giftr_message != null)
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
// });
$(window).on('beforeunload',function(){
	console.log("disconnect ws:giftOrder");
	goWebSocket.close();
	console.log("disconnect ws:notice")
	webSocket.close();	
});
</script>


</html>