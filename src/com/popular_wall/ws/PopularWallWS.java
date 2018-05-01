package com.popular_wall.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@ServerEndpoint("/PopularWallWS")
public class PopularWallWS {
	List<MemberVO> poplist = new ArrayList<MemberVO>();
	private static final Set<Session> PopallSessions = Collections.synchronizedSet(new HashSet<Session>());

	@OnOpen
	public void opOpen(Session popsession){
		System.out.println("popular連線成功");
		PopallSessions.add(popsession);
	}
	@OnMessage
	public void OnMessage(Session popsession,String popmessage){
		
	}
	@OnError
	public void onError(Session popsession, Throwable e){
		
	}
	@OnClose
	public void onClose(Session popsession, CloseReason reason){
		PopallSessions.remove(popsession);
		System.out.println("popular離線");
	}
	public void popReference(){
		JSONObject popjson = new JSONObject();
		JSONArray  popjsonarray = new JSONArray();
		MemberService ms = null;
		int c = 0;
		try{
			System.out.println("popReference1");
			ms = new MemberService();
			poplist = ms.pop();
			for(MemberVO pop : poplist){
			
				System.out.println(pop.getMem_name());
				popjsonarray.put(pop.getMem_no());
//				popjson.append("mem_receice_gift"+c, pop.getMem_receive_gift());
//				c++;
			}
			popjson.append("mem_no", popjsonarray);

			for(Session session : PopallSessions){
				if(session.isOpen()){
					System.out.println("popReference2");
					session.getBasicRemote().sendText(popjson.toString());
					
				}
			}
		}catch(IOException e){
		}catch(JSONException e){
			
		}
	}
}
