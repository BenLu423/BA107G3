<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.friends_list.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.talk.model.*"%>
<%
	MemberVO memSelf = (MemberVO) session.getAttribute("memSelf");
	FriendsService friSvc = new FriendsService();
	if (memSelf != null) {
		List<MemberVO> friends = friSvc.getMemFri(memSelf);
		session.setAttribute("friends", friends);
	}
	
	String nowFriNo = (String)session.getAttribute("nowFriNo");
	if(nowFriNo != null){
		session.setAttribute("nowFriNo", nowFriNo);
	}
	
	TalkVO holdTalk = (TalkVO)session.getAttribute("holdTalk");
	if(holdTalk!=null){
		session.setAttribute("holdTalk",holdTalk);
	}
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>footer</title>

</head>
<body onload="chatConnect();" onunload="chatDisconnect();">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
          <img src="" id="showBigPic">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  

  
  
  
	<!-- chat -->
	<div id="index-chat" class="container">
		<div class="row index-chat-title">
			<div class="col-xs-12 col-sm-12">
				<p>Toast.Chat</p>
			</div>
		</div>
		<div class="row index-chat-person">
			<div class="col-xs-12 col-sm-12" style="padding-right: 0px">
				<p>好 友 名 單</p>
				<div class="pre-scrollable" style="height: 110px">

					<c:forEach var="friends" items="${friends}">
						<div id="${friends.mem_no}" style="margin:5px">
							<img src="<%=request.getContextPath()%>/front_end/res/img/footer/close.jpg"> 
							<a href="#" onclick="getTalk('${friends.mem_no}')">${friends.mem_name}</a>
							
						</div>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="row index-chat-message">
			<!-- 		聊天內容                         -->
			<div id="friName">&nbsp</div><!-- 目前聊天的好友 -->
			<div class="col-xs-12 col-sm-12" id="chat-message">

			</div>
			<div class="col-xs-12 col-sm-12" style="background-color: #FFF;">
				<input type="text" placeholder="type message..." id="inputMessage" onkeydown="if (event.keyCode == 13) sendMessage();"/>
				<span><label class="glyphicon glyphicon-picture"><input id="upImg" type="file" style="display:none;"/></label></span>
				<span id="sendIcon" onclick="sendMessage();"><span class="fa fa-commenting-o"></span></span>
			</div>
		</div>
		<button id="chatSwitchOpen" type="button">
			<span class="fa fa-users fa-3x"></span>
		</button>
		<button id="chatSwitchClose" type="button">
			<span class="fa fa-arrow-circle-right fa-3x"></span>
		</button>
	</div>
	<!-- //chat -->
	

	<!-- FOOTER -->
	<div class="footer">
		<div class="col-xs-12 col-sm-4">
			<p>Contact us</p>
			<p>0800-080-978</p>
			<p>toast@gmail.com</p>
		</div>
		<div class="col-xs-12 col-sm-4">
			<p>Service</p>
			<p>關於我們</p>
			<p>最新公告</p>
		</div>
		<div class="col-xs-12 col-sm-4">
			<p>Follow us</p>
		</div>
		<div class="col-xs-12 col-sm-12">
			<p>Copyright Toast All Rights Reserved</p>
		</div>

		<div class="gotop ons" id="gototop">
			<a href="#"><img
				src="<%=request.getContextPath()%>/front_end/res/img/footer/upload.png"></a>
		</div>
	</div>
	<!-- FOOTER END-->
	<script>
	
	/********************卷軸置底************************/
	
	function down() {
		var div = document.getElementById('chat-message');
		div.scrollTop = div.scrollHeight;
		
	}

	
	/********************chatWebSocket*********************/
	
		var MyPoint = "/FriendWS/${memSelf.mem_no}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

		var chatWebSocket;
		
		var nowFriNo ;
		
		function chatConnect() {
			chatWebSocket = new WebSocket(endPointURL);

			chatWebSocket.onopen = function(event) {
				
				nowFriNo='${nowFriNo}';
				if(nowFriNo!=''){
					getTalk('${nowFriNo}');
				}
				
			};

			chatWebSocket.onmessage = function(event) {
				
				var jsonObj = JSON.parse(event.data);

			/**********************上下線處理************************/
			if (jsonObj.type == 'sendEveryFri') { //接收好友上線通知
				var friId = jsonObj.memberNO;
				$("#" + friId + ">img").attr("src","<%=request.getContextPath()%>/front_end/res/img/footer/online.jpg");
            } 
			if (jsonObj.type == 'leave') { //接收好友離線通知
				var friId = jsonObj.memberNO;
				$("#"+friId+">img").attr("src","<%=request.getContextPath()%>/front_end/res/img/footer/close.jpg");
            } 
			if (jsonObj.type == 'sendSelf') { //上線接收在線好友清單
				var onlineFri = jsonObj.onlineFri;
				if(onlineFri!=null && onlineFri[0].length!=0){
					onlineFri.forEach(function(fri){
						$("#"+fri+">img").attr("src","<%=request.getContextPath()%>/front_end/res/img/footer/online.jpg");
								});
				}
				
				var str = JSON.stringify(jsonObj.read);//未讀數量的map<編號,數量>
				var read = eval("("+str+")");
				for (var key in read) {  
					var a = read[key];
					for (var k in a) {
						if(a[k]>0){
							$("#"+k).append("<span class='unread'>"+a[k]+"</span>");
						}
					}   
				}  
				
				
			}
			/*********************接收歷史訊息***************************/
			
			if (jsonObj.type == 'getMessage') { 
				var jsonMessage = jsonObj.message; //str
				
				var content = JSON.parse(jsonMessage);//jsonArray
				if(content!=null){
					var selfNo = '${memSelf.mem_no}';
					nowFriNo = String(new String(jsonObj.friNo));//好友編號
					//alert("歷史訊息nowFriNo-type"+typeof(nowFriNo));//String
					
					for(var i = 0 ; i < content.length ; i++){
						if(content[i].sendType === 'pic' && content[i].memSend === selfNo){
							var add = "<div class='selfImg'><img src='"+content[i].message+"' style='width:60%' data-toggle='modal' data-target='#myModal' onclick='showBigPic(this);'></div><br>";
							$("#chat-message").append(add);
							down();
							
						}else if(content[i].sendType === 'pic' && content[i].memGet === selfNo){
							var add = "<div class='friImg'><img src='"+content[i].message+"' style='width:60%' data-toggle='modal' data-target='#myModal' onclick='showBigPic(this);'></div><br>";
							$("#chat-message").append(add);
							down();
							
						}else{
							if(content[i].memSend === selfNo){
								var add = "<p class='selfMessage'>"+content[i].message+"</p>"
								$("#chat-message").append(add);
								down();
							}else if(content[i].memGet === selfNo){
								var add = "<p class='friMessage'>"+content[i].message+"</p>"
								$("#chat-message").append(add);
								down();
							}
						}
					}
					
				}else{
	            	return;
	            }
				
            }
			
			/**********************接收新訊息*****************************/
			
			if(jsonObj.type == 'getNewMessage'){
				var count = 0;
				var chat_message = $("#chat-message");
				var message = jsonObj.message;
				var add = "<p class='friMessage'>"+message+"</p>";
				var memSend = new String(jsonObj.memSend);
				if(memSend==nowFriNo){
					$("#chat-message").append(add);
					down();
					return;
				}else if(memSend!=nowFriNo){
					
					var count = $("#"+memSend+">span").text();
					if(count === ""){
						count=1;
						$("#"+memSend).append("<span class='unread'>"+count+"</span>");
					}else{
						count++;
						$("#"+memSend+">span").text(count);
					}
				}
				return;
			}
			
			/**********************接收新圖片*****************************/
			if(jsonObj.type == 'getNewImg'){
				var chat_message = $("#chat-message");
				var message = jsonObj.message;
				var add = "<div class='friImg'><img src='"+message+"' style='width:60%' data-toggle='modal' data-target='#myModal' onclick='showBigPic(this);'></div><br>";
				var memSend = new String(jsonObj.memSend);
				if(memSend==nowFriNo){
					$("#chat-message").append(add);
					down();
					return;
				}
				return;
			}
			
			};

			chatWebSocket.onclose = function(event) {
				
			}

		}
		
		/**********************傳送新訊息*****************************/
		function sendMessage() {
			var memGet = nowFriNo;
			//alert("sendMessage nowFriNo = "+typeof(nowFriNo))
			var memSend = "${memSelf.mem_no}";
			var date = new Date();
			var message = $("#inputMessage").val().trim();
			
			//alert("sendMessage memGet = "+typeof(memGet));
			
			if(memGet == null){
				 $("#inputMessage").val("");
				 return;
			}
			if(message === ""){
				return;
			}
			
			var add = "<p class='selfMessage'>"+message+"</p>"
			$("#chat-message").append(add);
			
			var jsonObj = {
				"type" : "sendMessage",
				"memSend" : memSend,
				"memGet" : memGet,
				"date" : date,
				"message" : message,
				"sendType":"text",
				"read":"未讀",
			};
			
			chatWebSocket.send(JSON.stringify(jsonObj));
			 $("#inputMessage").val("");
			 $("#inputMessage").focus();
			 
			 down();

		}
		
		/*********************取歷史訊息**********************/
		function getTalk(friNo){
			
			friName = $('#friName').text();//目前訊息框上的好友名稱
			nowFriNo = friNo;
			//alert("getTalk friNo = "+typeof(friNo));//string
			var getName = $("#"+friNo+">a").text();//點擊好友名稱
			if(friName!=getName){
				$('#friName').text(getName);
				$("#chat-message").empty();
				var jsonObj = {
					"type" : "getOneTalk",
					"friNo" : friNo,
				};
				chatWebSocket.send(JSON.stringify(jsonObj));
				
				var deleteUnread = {
						"type":"deleteUnread",
						"friNo" : friNo,
						
				};
				
				chatWebSocket.send(JSON.stringify(deleteUnread));
				
				$("#"+friNo+">span").remove();
				
			}else{
				return;
			}
			
			
		}
		
		function chatDisconnect(){
			chatWebSocket.close();
			
		}
		
		
		
		/********************傳圖片************************/
		$(function(){
			$('#upImg').on('change',function(img){
				var file    = document.querySelector('input[id=upImg]').files[0];
				var reader  = new FileReader();
				var memGet = nowFriNo;
				var memSend = "${memSelf.mem_no}";
				var date = new Date();
				
				if (file) {
				    reader.readAsDataURL(file);
				    //var base = atob(reader.result);
				    reader.addEventListener("load",function() {
					var add = "<div class='selfImg'><img src='"+reader.result+"' style='width:60%' data-toggle='modal' data-target='#myModal' onclick='showBigPic(this);'></div><br>";
					$("#chat-message").append(add);
					var result = reader.result;
					//console.log(reader.result);//base64String
					var jsonObj = {
							"type" : "sendImg",
							"memSend" : memSend,
							"memGet" : memGet,
							"date" : date,
							"message" : result,
							"sendType":"pic",
							"read":"未讀",
							};

					chatWebSocket.send(JSON.stringify(jsonObj));
					down();
					}, false);
				    
				}
				
				})
				
		})
		
		
		/********************秀大圖************************/
		
		function showBigPic(e){
			var imgPath = e.src;
		    $("#showBigPic").attr("src",imgPath);
		}
		
	
		
	</script>


</body>
</html>