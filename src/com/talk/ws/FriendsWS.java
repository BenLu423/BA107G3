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
		// �]�w��500KB���F�t�XAndroid bundle�ǰe�j�p
		int maxBufferSize = 5 * 1024 * 1024;
		session.setMaxTextMessageBufferSize(maxBufferSize);
		session.setMaxBinaryMessageBufferSize(maxBufferSize);
		
		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		
		if (httpSession != null) {
		   self = (MemberVO) httpSession.getAttribute("memSelf");// �W�u�|��
		} else {
		   MemberService mSvc = new MemberService();
		   self = mSvc.getOneMem(mem_no);
		}
		
		onlineMem.put(self.getMem_no(), session);
		System.out.println(self.getMem_name()+"�W�u");
		
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
		//���o�P�n�ͪ����v�T��
		try{
			if("getOneTalk".equals(type)){
				
				String friNo = jsonObj.getString("friNo");
				httpSession.setAttribute("nowFriNo", friNo);
	//			System.out.println("���o�s�u���n��"+friNo);
				getTalk(friNo,session);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//���s�T���ǤJ
		if("sendMessage".equals(type)){
			
			updateCnt(jsonObj , "getNewMessage",friSession);
			
		}
		
		//���s�϶ǤJ
		if("sendImg".equals(type)){
			updateCnt(jsonObj , "getNewImg",friSession);
			//System.out.println(jsonObj);
		}
		
		//�R����Ū�r��
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
		List<MemberVO>friendList = friSvc.getMemFri(self);//�n���`�H��
		Set<String>onlineFri = new HashSet<String>();//�b�u���n��
		TalkService talkSvc = new TalkService();
		Map<String , Integer> read = new HashMap<String , Integer>();
		JSONObject result =null;
		result = new JSONObject();
		result.append("type", "sendSelf");
		result.append("onlineFri",onlineFri);
		result.append("read", read);
		
		for(MemberVO friend : friendList){
			if(onlineMem.containsKey(friend.getMem_no())){//���n�ͬO�_�b�W�u�W��
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
			
			
			
//			String unread = "\"read\":\"��Ū\"";
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
	
	//���o�P�n�ͪ����v�T��
	public void getTalk(String friNo , Session session) throws JSONException{
		
		TalkService talkSvc = new TalkService();
		FriendsListVO friends = new FriendsListVO();
		friends.setMem_no_self(self.getMem_no());
		friends.setMem_no_other(friNo);
		TalkVO talk=null;
		
		//���褧���S��talkVO�ɫؤ@��
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
		

		jsonObj.remove("type");//���F�s�J��Ʈw�A���Ntype����
		
		TalkService talkSvc = new TalkService();
		FriendsService friSvc = new FriendsService();
		FriendsListVO friends = friSvc.getOne(self.getMem_no(), friNo);
		
		friSession = onlineMem.get(friNo);
		
		if(friSession!=null){
			friSession.getAsyncRemote().sendText(result.toString());
			TalkVO talk = talkSvc.getOneTalk(friends);
			//�ק�ɶ�
			java.util.Date today = new java.util.Date();
			long time = today.getTime();
			Timestamp date = new Timestamp(time);
			talk.setTalk_time(date);
			
			//�ק�T��
			String cnt = talk.getTalk_cnt();
			
			if(cnt==null){//�S�����v�T����
				
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
			//�n�ͤ��b�u�W���ܥ��N�T���s�J��Ʈw
			TalkVO talk=talkSvc.getOneTalk(friends);
			
			//�ק�ɶ�
			java.util.Date today = new java.util.Date();
			long time = today.getTime();
			Timestamp date = new Timestamp(time);
			talk.setTalk_time(date);
			
			//�ק�T��
			String cnt = talk.getTalk_cnt();
			
			if(cnt==null){//�S�����v�T����
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
