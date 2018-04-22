package com.talk.ws;


import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.friends_list.model.FriendsListVO;
import com.friends_list.model.FriendsService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.talk.model.TalkService;
import com.talk.model.TalkVO;


@ServerEndpoint(value="/FriendWS/{mem_no}" , configurator=ServletAwareConfig.class)
public class FriendsWS {
	
	private EndpointConfig config;
	public static final Map<String , Session> onlineMem = new HashMap<String,Session>();
	private MemberVO self;

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no,Session session , EndpointConfig config) throws JSONException{
		// 設定成500KB為了配合Android bundle傳送大小
		int maxBufferSize = 5 * 1024 * 1024;
		session.setMaxTextMessageBufferSize(maxBufferSize);
		session.setMaxBinaryMessageBufferSize(maxBufferSize);
		
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		
		if (httpSession != null) {
		   self = (MemberVO) httpSession.getAttribute("memSelf");// 上線會員
		} else {
		   MemberService mSvc = new MemberService();
		   self = mSvc.getOneMem(mem_no);
		}
		
		onlineMem.put(self.getMem_no(), session);
		System.out.println(self.getMem_name()+"上線");
		
		sendFriends(session);
		getOnlineFri(session);
		
		
		
	}
	
	@OnMessage
	public void onMessage(Session session, String message){
		
		try{
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		//System.out.println(message);
		JSONObject jsonObj = new JSONObject(message);
		String type = jsonObj.getString("type");
		Session friSession = null;
		//System.out.println(jsonObj);
		//取得與好友的歷史訊息
		try{
			if("getOneTalk".equals(type)){
				
				String friNo = jsonObj.getString("friNo");
				httpSession.setAttribute("nowFriNo", friNo);
	//			System.out.println("取得連線的好友"+friNo);
				getTalk(friNo,session);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//有新訊息傳入
		if("sendMessage".equals(type)){
			
			updateCnt(jsonObj , "getNewMessage",friSession);
			
		}
		
		//有新圖傳入
		if("sendImg".equals(type)){
			updateCnt(jsonObj , "getNewImg",friSession);
			//System.out.println(jsonObj);
		}
		
		//刪除未讀字串
		if("deleteUnread".equals(type)){
			String friNo = jsonObj.getString("friNo");
			FriendsService friSvc = new FriendsService();
			TalkService talkSvc = new TalkService();
			FriendsListVO friends= friSvc.getOne(self.getMem_no(), friNo);
			TalkVO talk = talkSvc.getOneTalk(friends);
			String cnt = talk.getTalk_cnt();
			
			if(cnt!=null){
				JSONArray jsonarray = new JSONArray(cnt);
				for(int i = 0; i < jsonarray.length() ; i++){
					JSONObject oneMessage = jsonarray.getJSONObject(i);
					if(oneMessage.has("read")){
						if(oneMessage.getString("memSend").equals(friNo)){
							oneMessage.remove("read");
						}
					}
				}
				
				talk.setTalk_cnt(jsonarray.toString());
				talkSvc.updateCnt(talk);
			}
			
		}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	@OnError
	public void onError(Session session, Throwable e){
		e.printStackTrace();
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
		
		System.out.println(self.getMem_name()+"離線");
		session.close();
		onlineMem.remove(self.getMem_no());
//		System.out.println(self.getMem_name()+"close");
		
		
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
		List<MemberVO>friendList = friSvc.getMemFri(self);//好友總人數
		Set<String>onlineFri = new HashSet<String>();//在線的好友
		TalkService talkSvc = new TalkService();
		Map<String , Integer> read = new HashMap<String , Integer>();
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendSelf");
		result.append("onlineFri",onlineFri);
		result.append("read", read);
		
		for(MemberVO friend : friendList){
			if(onlineMem.containsKey(friend.getMem_no())){//比對好友是否在上線名單
				onlineFri.add(friend.getMem_no());
			}
			
			FriendsListVO friends = friSvc.getOne(self.getMem_no(), friend.getMem_no());
			TalkVO talk = null;
			String cnt = null;
			
			try{
				talk = talkSvc.getOneTalk(friends);
				cnt = talk.getTalk_cnt();
				int count = 0;
				JSONArray jsonarray = new JSONArray(cnt);
				for(int i = 0 ; i<jsonarray.length();i++){
					JSONObject oneMessage = jsonarray.getJSONObject(i);
					if(oneMessage.has("read")){
						String memGet = oneMessage.getString("memGet");
						if(memGet.equals(self.getMem_no())){
							count++;
						}
					}
					read.put(friend.getMem_no(), count);
				}
			}catch(Exception e ){
				talk = null;
			}
			
			
			
//			String unread = "\"read\":\"未讀\"";
//			int count = 0;
//			int start = 0;
//			if(cnt!=null){
//				while (cnt.indexOf(unread, start) >= 0 && start < cnt.length()) {
//					count++;
//					start = cnt.indexOf(unread, start) + unread.length();
//				}
//			}
//			read.put(friend.getMem_no(), count);
			
			
		}
		
		if(onlineFri.size()<=0){
			result.remove("onlineFri");
		}
		
		session.getAsyncRemote().sendText(result.toString());
		
	}
	
	//取得與好友的歷史訊息
	public void getTalk(String friNo , Session session) throws JSONException{
		
		TalkService talkSvc = new TalkService();
		FriendsListVO friends = new FriendsListVO();
		friends.setMem_no_self(self.getMem_no());
		friends.setMem_no_other(friNo);
		TalkVO talk=null;
		
		//雙方之間沒有talkVO時建一個
		try{
			talk = talkSvc.getOneTalk(friends);
		}catch(Exception e ){
			TalkVO newTalk = new TalkVO();
			newTalk.setMem_no_send(friNo);
			newTalk.setMem_no_get(self.getMem_no());
			java.util.Date today = new java.util.Date();
			long time = today.getTime();
			Timestamp date = new Timestamp(time);
			newTalk.setTalk_time(date);
			talkSvc.addTalk(newTalk);
			talk = talkSvc.getOneTalk(friends);
		}
		
		String cnt = talk.getTalk_cnt();
		if(cnt!=null){
			JSONObject result = null;
			result = new JSONObject();
			result.append("type","getMessage");
			result.append("friNo", friNo);
			result.append("message",talk.getTalk_cnt());
			
			session.getAsyncRemote().sendText(result.toString());
		}
	}
	

	public void updateCnt(JSONObject jsonObj,String type,Session friSession) throws JSONException{
		String friNo = jsonObj.getString("memGet");
		String sendType = jsonObj.getString("sendType");
		
		JSONObject result = new JSONObject();
		result.append("type",type);
		result.append("memSend", jsonObj.getString("memSend"));
		result.append("memGet", friNo);
		result.append("date", jsonObj.getString("date"));
		result.append("message", jsonObj.getString("message"));
		

		jsonObj.remove("type");//為了存入資料庫，先將type移除
		
		TalkService talkSvc = new TalkService();
		FriendsService friSvc = new FriendsService();
		FriendsListVO friends = friSvc.getOne(self.getMem_no(), friNo);
		
		friSession = onlineMem.get(friNo);
		
		if(friSession!=null){
			friSession.getAsyncRemote().sendText(result.toString());
			TalkVO talk = talkSvc.getOneTalk(friends);
			//修改時間
			java.util.Date today = new java.util.Date();
			long time = today.getTime();
			Timestamp date = new Timestamp(time);
			talk.setTalk_time(date);
			
			//修改訊息
			String cnt = talk.getTalk_cnt();
			
			if(cnt==null){//沒有歷史訊息時
				
				talk.setTalk_cnt("["+jsonObj.toString()+"]");
				talkSvc.updateCnt(talk);
				return;
			}
			
			StringBuilder sb = new StringBuilder(cnt);
			sb.insert(sb.length()-1,","+jsonObj);
			
			talk.setTalk_cnt(sb.toString());
			
			talkSvc.updateCnt(talk);
			
			return;
		
		}else{
			//好友不在線上的話先將訊息存入資料庫
			TalkVO talk=talkSvc.getOneTalk(friends);
			
			//修改時間
			java.util.Date today = new java.util.Date();
			long time = today.getTime();
			Timestamp date = new Timestamp(time);
			talk.setTalk_time(date);
			
			//修改訊息
			String cnt = talk.getTalk_cnt();
			
			if(cnt==null){//沒有歷史訊息時
				talk.setTalk_cnt("["+jsonObj.toString()+"]");
				talkSvc.updateCnt(talk);
				return;
			}
			
			StringBuilder sb = new StringBuilder(cnt);
			sb.insert(sb.length()-1,","+jsonObj);
			
			talk.setTalk_cnt(sb.toString());
			
			talkSvc.updateCnt(talk);
			return;
		
		}
	}
	
	
}
