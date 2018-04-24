package android.com.member.ws;


import java.util.*;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import android.com.member.model.MemberService;
import android.com.member.model.MemberVO;

@ServerEndpoint(value = "/MemberWS/{mem_no}", configurator = ServletAwareConfig.class)
public class MemberWS {

		private EndpointConfig config;
		public static final Map<String, Session> onlineMem = new HashMap<String, Session>();
		private MemberVO self;

		@OnOpen
		public void onOpen(@PathParam("mem_no") String mem_no, Session session, EndpointConfig config)
				throws JSONException {
			this.config = config;
			
			int maxBufferSize = 500 * 1024;
			session.setMaxTextMessageBufferSize(maxBufferSize);
			session.setMaxBinaryMessageBufferSize(maxBufferSize);

			HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
			if (httpSession != null) {
				self = (MemberVO) httpSession.getAttribute("memSelf");// 上線會員
			}else{
				MemberService mSvc = new MemberService();
				self = mSvc.getOneByMemNo(mem_no);
			}
			
			onlineMem.put(self.getMem_no(), session);
			System.out.println(self.getMem_name()+"來囉");
		}

		@OnMessage
		public void onMessage(Session session, String message) throws JSONException {
			JSONObject jsonObj = new JSONObject(message);
			Session onlineSession = null;
			String type =(String)jsonObj.get("type");

			if ("sendAdd".equals(type)) {
				System.out.println(jsonObj);
				JSONObject result = null;
				String friNo = (String) jsonObj.getString("memGet");
				System.out.println(friNo);
				result = new JSONObject();
				result.append("type", "requestAdd");
				result.append("memSend", jsonObj.getString("memSend"));
				result.append("memGet", jsonObj.getString("memGet"));
				result.append("memSend_name", self.getMem_name());
				onlineSession = onlineMem.get(friNo);
				System.out.println(onlineSession);
				System.out.println(result.toString());
				onlineSession.getAsyncRemote().sendText(result.toString());
			}
			
			if ("respondAdd".equals(type)) {
				System.out.println(jsonObj);
				JSONObject result = null;
				String friNo = (String) jsonObj.getString("memGet");
				System.out.println(friNo);
				result = new JSONObject();
				result.append("type", "respondAdd");
				result.append("memSend", jsonObj.getString("memSend"));
				onlineSession = onlineMem.get(friNo);
				onlineSession.getAsyncRemote().sendText(result.toString());
			}
			
		}

		@OnError
		public void onError(Session session, Throwable e) {

		}

		@OnClose
		public void onClose(Session session, CloseReason reason) throws Exception {
			session.close();
			onlineMem.remove(self.getMem_no());

		}

}

