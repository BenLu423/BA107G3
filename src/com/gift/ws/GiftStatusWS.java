package com.gift.ws;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

import com.gift.model.*;
import com.giftDiscount.model.*;
import com.giftLabel.model.GiftLabelVO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

@ServerEndpoint("/GiftStatusServer")
public class GiftStatusWS {
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + "的gs已連線");
	}
	
	@OnMessage
	public void onMessage(Session userSession, String giftInformation){
		//將接收到的jsonArr轉換成map
		Map<String,String> map = new HashMap<>();
		try {
			JSONArray jsonArr = new JSONArray(giftInformation);
			for(int i=0; i<jsonArr.length(); i++){
				JSONObject jsonObj = jsonArr.getJSONObject(i);
				String name  = (String) jsonObj.get("name");
				String value = (String) jsonObj.get("value"); 
				map.put(name, value);
			}
//			for(Entry<String, String> str: map.entrySet())
//				System.out.println(str.getKey() + " : " + str.getValue());
		} catch (JSONException e) {
			e.printStackTrace(System.err);
		}
		
		//更新[禮物VO的狀態]
		String gift_no = map.get("gift_no");
		String gift_is_on = map.get("gift_is_on");
		GiftService giftSvc = new GiftService();
		giftSvc.updateStatus(gift_no, gift_is_on);
		
		//取得對應的[禮物VO/禮物標籤VO]與[限時優惠VO←可能為null]
		Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(gift_no);
		GiftDiscountService gdSvc = new GiftDiscountService();
		GiftDiscountVO giftDiscountVO = gdSvc.getCurrentValidGift(gift_no);
		
		//建立一個list來存放[{限時優惠VO},{禮物VO},{List<禮物標籤VO>}]
		List<Object> list = new ArrayList<Object>();
		for(Entry<GiftVO, List<GiftLabelVO>> vo :gift.entrySet()){
			GiftVO giftVO = vo.getKey();
			//清除gftVO內的圖片內容，簡化傳遞的資訊
			giftVO.setGift_pic(null);
			list.add(giftVO);			
			List<GiftLabelVO> giftLabelVO = vo.getValue();
			list.add(giftLabelVO);
		}
		if(giftDiscountVO != null)
			list.add(giftDiscountVO);
		
		//將list轉換成json傳遞出去
		Type listType = new TypeToken<List<Object>>() {}.getType();
		Gson gson = new Gson();
		String data = gson.toJson(list, listType);
System.out.println(data);
		for (Session session : allSessions) {
			if (session.isOpen() && !session.equals(userSession))
				session.getAsyncRemote().sendText(data);
		}
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
		e.printStackTrace(System.err);
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + "號的gs已離線 : " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	public void broadcast(String gift_no, String action) {
		GiftService giftSvc = new GiftService();
		//取得對應的[禮物VO/禮物標籤VO]與[限時優惠VO←可能為null]
		Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(gift_no);
		GiftDiscountService gdSvc = new GiftDiscountService();
		GiftDiscountVO giftDiscountVO = gdSvc.getCurrentValidGift(gift_no);
		
		//建立一個list來存放[{action},{限時優惠VO},{禮物VO},{List<禮物標籤VO>}]
		List<Object> list = new ArrayList<Object>();
		Map<String,String> actionMap = new HashMap<>();
		actionMap.put("action", action);
		list.add(actionMap);
		for(Entry<GiftVO, List<GiftLabelVO>> vo :gift.entrySet()){
			GiftVO giftVO = vo.getKey();
			//清除gftVO內的圖片內容，簡化傳遞的資訊
			giftVO.setGift_pic(null);
			list.add(giftVO);			
			List<GiftLabelVO> giftLabelVO = vo.getValue();
			list.add(giftLabelVO);
		}
		if(giftDiscountVO != null)
			list.add(giftDiscountVO);
		
		//將list轉換成json傳遞出去
		Type listType = new TypeToken<List<Object>>() {}.getType();
		Gson gson = new Gson();
		String data = gson.toJson(list, listType);	
System.out.println(data);	
		for (Session session : allSessions) {
			if (session.isOpen())
				session.getAsyncRemote().sendText(data);
		}
	}
}
