package com.talk.ws;


import java.util.*;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.friends_list.model.FriendsListVO;
import com.friends_list.model.FriendsService;
import com.member.model.MemberVO;
import com.talk.model.TalkService;


@ServerEndpoint(value="/FriendWS/{mem_no}" , configurator=ServletAwareConfig.class)
public class FriendsWS {
	
	private EndpointConfig config;
	public static final Map<String , Session> onlineMem = new HashMap<String,Session>();
	private MemberVO self;

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no,Session session , EndpointConfig config) throws JSONException{
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		self = (MemberVO)httpSession.getAttribute("memSelf");//�W�u�|��
		String friNo = (String)httpSession.getAttribute("nowFriNo");
		if(friNo !=null){
			getTalk(friNo,session);
		}
		onlineMem.put(self.getMem_no(), session);
		System.out.println(self.getMem_name()+"�W�u");
		
		sendFriends(session);
		getOnlineFri(session);
		
		
		
	}
	
	@OnMessage
	public void onMessage(Session session, String message) throws JSONException{
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		JSONObject jsonObj = new JSONObject(message);
		String type = jsonObj.getString("type");
		Session friSession = null;
		System.out.println(jsonObj);
		//���o�P�n�ͪ����v�T��
		if("getOneTalk".equals(type)){
			
			String friNo = jsonObj.getString("friNo");
			httpSession.setAttribute("nowFriNo", friNo);
//			System.out.println("���o�s�u���n��"+friNo);
			getTalk(friNo,session);
			
		}
		if("sendMessage".equals(type)){
			
			try{
			JSONObject result =null;
			String friNo = jsonObj.getString("memGet");
			result = new JSONObject();
			result.append("type","getNewMessage");
			result.append("memSend", jsonObj.getString("memSend"));
			result.append("memGet", friNo);
			result.append("date", jsonObj.getString("date"));
			result.append("message", jsonObj.getString("message"));
			
			friSession = onlineMem.get(friNo);
			friSession.getAsyncRemote().sendText(result.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
		}
		
		

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
			if(onlineMem.containsKey(friend.getMem_no())){//���n�ͬO�_�b�W�u�W��
				
				Session friendsSession = onlineMem.get(friend.getMem_no());
				friendsSession.getAsyncRemote().sendText(result.toString());
			}
		}
		
		System.out.println(self.getMem_name()+"���u");
		session.close();
		onlineMem.remove(self.getMem_no());
//		System.out.println(self.getMem_name()+"close");
		
		
	}
	
	//�����n�ͤW�u�T��
	public void sendFriends(Session session) throws JSONException{
		FriendsService friSvc = new FriendsService();
		List<MemberVO> friends = friSvc.getMemFri(self);//�W�u�|���n�ͲM��
		
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendEveryFri");
		result.append("memberNO",self.getMem_no());
		
		for(MemberVO friend : friends){
			if(onlineMem.containsKey(friend.getMem_no())){//���n�ͬO�_�b�W�u�W��
				
				Session friendsSession = onlineMem.get(friend.getMem_no());
				friendsSession.getAsyncRemote().sendText(result.toString());
			}

		}
	}
	
	//���o�W�u�n�ͪ����A
	public void getOnlineFri(Session session) throws JSONException{
		FriendsService friSvc = new FriendsService();
		List<MemberVO>friends = friSvc.getMemFri(self);//�n���`�H��
		Set<String>onlineFri = new HashSet<String>();//�b�u���n��
		
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendSelf");
		result.append("onlineFri",onlineFri);
		
		for(MemberVO friend : friends){
			if(onlineMem.containsKey(friend.getMem_no())){//���n�ͬO�_�b�W�u�W��
				onlineFri.add(friend.getMem_no());
//				Session friendsSession = onlineMem.get(friend.getMem_no());
//				friendsSession.getAsyncRemote().sendText(result.toString());
			}
		}
		session.getAsyncRemote().sendText(result.toString());
	}
	
	//���o�P�n�ͪ����v�T��
	public void getTalk(String friNo , Session session) throws JSONException{
		TalkService talkSvc = new TalkService();
		FriendsListVO friends = new FriendsListVO();
		friends.setMem_no_self(self.getMem_no());
		friends.setMem_no_other(friNo);
		String talk=null;
		try{
			talk = talkSvc.getOneTalk(friends);
		}catch(Exception e){
			talk = null;
		}
		JSONObject result = null;
		result = new JSONObject();
		result.append("type","getMessage");
		result.append("friNo", friNo);
		result.append("message",talk);
		
		session.getAsyncRemote().sendText(result.toString());
	}
	

}
