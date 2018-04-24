package com.giftOrder.ws;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

import com.gift.model.*;
import com.gift.ws.GiftStatusWS;
import com.giftDiscount.model.*;
import com.giftLabel.model.GiftLabelVO;
import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftReceive.model.GiftReceiveService;
import com.giftReceive.model.GiftReceiveVO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.talk.ws.ServletAwareConfig;

@ServerEndpoint(value="/GiftOrderServer/{mem_no}")
public class GiftOrderWS {
	
	//private EndpointConfig config;
	private GiftStatusWS giftStatusWS = new GiftStatusWS();
	private static final Map<String,Session> allUsers = Collections.synchronizedMap(new LinkedHashMap<>());

	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no, Session userSession) throws IOException {
		//this.config = config;
		//HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
		allUsers.put(mem_no, userSession);
		System.out.println(mem_no + "的go已連線");
	}
	
	@OnMessage
	public void onMessage(Session userSession, String giftInformation){
	}
	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace(System.err);
	}
	
	@OnClose
	public void onClose(@PathParam("mem_no") String mem_no, Session userSession, CloseReason reason) {
		allUsers.remove(mem_no);
		System.out.println(mem_no + "號的go已離線 : " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	public void multipleSendGift(String action, Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail) {
		//將所有的N筆訂單與N*N筆收贈禮進行推播
		for(Entry<GiftOrderDetailVO, List<GiftReceiveVO>> set: orderDetail.entrySet()){
			GiftOrderDetailVO giftOrderDetailVO = set.getKey();
			//如果該筆訂單為限時優惠禮物，則通知線上所有人員數量已減少
			if(giftOrderDetailVO.getGiftd_no()!=null){
System.out.println("GiftOrderWS update gift :" + giftOrderDetailVO.getGift_no());
				giftStatusWS.broadcast("updateGift", giftOrderDetailVO.getGift_no());
			}
			//將該筆訂單的N筆收贈禮分別通知[在線]的收禮人
			String gift_no = giftOrderDetailVO.getGift_no();
			for(GiftReceiveVO giftReceiveVO: set.getValue()){
				//收禮人編號
				String mem_no_other = giftReceiveVO.getMem_no_other();
				Session session = allUsers.get(mem_no_other);
				//如果該收禮人在線上
				if(session != null){
					GiftService giftSvc = new GiftService();
					//取得對應的[禮物VO/禮物標籤VO]與[限時優惠VO←可能為null]
					Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(gift_no);
					GiftDiscountService gdSvc = new GiftDiscountService();
					GiftDiscountVO giftDiscountVO = gdSvc.getCurrentValidGift(gift_no);
					
					//建立一個list來存放[{action},{禮物VO},{List<禮物標籤VO>},{收贈禮VO},{限時優惠VO}]
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
					list.add(giftReceiveVO);
					if(giftDiscountVO != null)
						list.add(giftDiscountVO);

					//將list轉換成json傳遞出去
					Type listType = new TypeToken<List<Object>>() {}.getType();
					Gson gson = new Gson();
					String data = gson.toJson(list, listType);	
System.out.println("GiftOrderWS receiveNotice :" + gift_no);					
System.out.println(data);	
					session.getAsyncRemote().sendText(data);
				}
					
			}
		}
	}
}
