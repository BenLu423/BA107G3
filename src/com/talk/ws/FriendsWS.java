package com.talk.ws;


import java.util.*;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.friends_list.model.FriendsService;
import com.member.model.MemberVO;


@ServerEndpoint(value="/FriendWS/{mem_no}" , configurator=ServletAwareConfig.class)
public class FriendsWS {
	
	private EndpointConfig config;
	public static final Map<String , Session> onlineMem = new HashMap<String,Session>();
	private MemberVO self;

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no,Session session , EndpointConfig config) throws JSONException{
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		
		self = (MemberVO)httpSession.getAttribute("memSelf");//上線會員
		onlineMem.put(self.getMem_no(), session);
		System.out.println(self.getMem_name()+"上線");
		
		sendFriends(session);
		getOnlineFri(session);
		
	}
	
	@OnMessage
	public void onMessage(Session session, String message){
		

		session.getAsyncRemote().sendText(message);
		
	}
	
	@OnError
	public void onError(Session session, Throwable e){
		
	}
	
	@OnClose
	public void onClose(Session session, CloseReason reason) throws Exception{
		FriendsService friSvc = new FriendsService();
		List<MemberVO>friends = friSvc.getMemFri(self);
		
		JSONObject result = null;
		result = new JSONObject();
		result.append("type","leave");
		result.append("memberNO",self.getMem_no());
		
		for(MemberVO friend : friends){
			if(onlineMem.containsKey(friend.getMem_no())){//比對好友是否在上線名單
				
				Session friendsSession = onlineMem.get(friend.getMem_no());
				friendsSession.getAsyncRemote().sendText(result.toString());
			}
		}
		
		
		session.close();
		onlineMem.remove(self.getMem_no());
		
		
	}
	
	//推給好友上線訊息
	public void sendFriends(Session session) throws JSONException{
		FriendsService friSvc = new FriendsService();
		List<MemberVO> friends = friSvc.getMemFri(self);//上線會員好友清單
		
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendEveryFri");
		result.append("memberNO",self.getMem_no());
		
		for(MemberVO friend : friends){
			if(onlineMem.containsKey(friend.getMem_no())){//比對好友是否在上線名單
				
				Session friendsSession = onlineMem.get(friend.getMem_no());
				friendsSession.getAsyncRemote().sendText(result.toString());
			}

		}
	}
	
	//取得上線好友的狀態
	public void getOnlineFri(Session session) throws JSONException{
		FriendsService friSvc = new FriendsService();
		List<MemberVO>friends = friSvc.getMemFri(self);//好友總人數
		Set<String>onlineFri = new HashSet<String>();//在線的好友
		
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendSelf");
		result.append("onlineFri",onlineFri);
		
		for(MemberVO friend : friends){
			if(onlineMem.containsKey(friend.getMem_no())){//比對好友是否在上線名單
				onlineFri.add(friend.getMem_no());
//				Session friendsSession = onlineMem.get(friend.getMem_no());
//				friendsSession.getAsyncRemote().sendText(result.toString());
			}
		}
		session.getAsyncRemote().sendText(result.toString());
	}

}
