package android.com.member.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import android.com.friends_list.model.FriendsService;
import android.com.member.model.MemberService;
import android.com.member.model.MemberVO;
import com.talk.ws.ServletAwareConfig;

@ServerEndpoint(value = "/DistanceSearchWS/{mem_no}", configurator = ServletAwareConfig.class)
public class DistanceSearchWS {

	private EndpointConfig config;
	public static final Map<String, Session> online = new HashMap<String, Session>();
	private String mem_no;

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no, Session session, EndpointConfig config)
			throws JSONException {
		// 設定成500KB為了配合Android bundle傳送大小
		int maxBufferSize = 5 * 1024 * 1024;
		session.setMaxTextMessageBufferSize(maxBufferSize);
		session.setMaxBinaryMessageBufferSize(maxBufferSize);

		this.config = config;
		HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");

		this.mem_no = mem_no;

		online.put(mem_no, session);
		System.out.println(mem_no + "距離配對上線");

		sendMems(session);
		getOnlineMem(session);

	}

	@OnMessage
	public void onMessage(Session session, String message) {

	}

	@OnError
	public void onError(Session session, Throwable e) {

	}

	@OnClose
	public void onClose(Session session, CloseReason reason) throws Exception {

//		JSONObject result = null;
//		result = new JSONObject();
//		result.append("type", "leave");
//		result.append("memberNO", mem_no);
//
//		Set<String> onlineMem = online.keySet();
//		Iterator<String> it = onlineMem.iterator();
//		while (it.hasNext()) {
//			String all_mem_no = it.next();
//			if (all_mem_no != mem_no) {
//				Session onlineSession = online.get(it.next());
//				onlineSession.getAsyncRemote().sendText(result.toString());
//			}
//		}
//
//		session.close();
//		online.remove(mem_no);
	}

	// 推上線會員訊息
	public void sendMems(Session session) throws JSONException {

		JSONObject result = null;
		result = new JSONObject();
		result.append("type", "sendEveryFri_distanceSearch");
		result.append("memberNO", mem_no);

		Set<String> onlineMem = online.keySet();
		Iterator<String> it = onlineMem.iterator();
		while (it.hasNext()) {
			String all_mem_no = it.next();
			if (all_mem_no != mem_no) {
				Session onlineSession = online.get(it.next());
				onlineSession.getAsyncRemote().sendText(result.toString());
			}
		}
	}

	// 取得上線會員的狀態
	public void getOnlineMem(Session session) throws JSONException {
		List<String> onlineMem = new ArrayList<>();

		JSONObject result = null;
		result = new JSONObject();
		result.append("type", "sendSelf_distanceSearch");
		result.append("onlineMem", onlineMem);

		Set<String> onlineMemSet = online.keySet();
		Iterator<String> it = onlineMemSet.iterator();
		while (it.hasNext()) {
			String all_mem_no = it.next();
			if (all_mem_no != mem_no) {
				onlineMem.add(all_mem_no);
			}
		}
		session.getAsyncRemote().sendText(result.toString());
	}
}
