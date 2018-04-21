package com.gift.ws;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.Map.Entry;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.*;

import com.gift.model.*;
import com.giftDiscount.model.*;
import com.giftLabel.model.GiftLabelVO;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

@ServerEndpoint("/GiftStatusServer/{mem_no}")
public class GiftStatusWS {
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(@PathParam("mem_no") String mem_no, Session userSession) throws IOException {
		allSessions.add(userSession);
		System.out.println(userSession.getId() + "��gs�w�s�u");
	}
	
	@OnMessage
	public void onMessage(@PathParam("mem_no") String mem_no, Session userSession, String giftInformation){
	}
	
	@OnError
	public void onError(@PathParam("mem_no") String mem_no, Session userSession, Throwable e){
		e.printStackTrace(System.err);
	}
	
	@OnClose
	public void onClose(@PathParam("mem_no") String mem_no, Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + "����gs�w���u : " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	synchronized public void broadcast(String action, String gift_no) {
		GiftService giftSvc = new GiftService();
		//���o������[§��VO/§������VO]�P[�����u�fVO���i�ରnull]
		Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(gift_no);
		GiftDiscountService gdSvc = new GiftDiscountService();
		GiftDiscountVO giftDiscountVO = gdSvc.getCurrentValidGift(gift_no);
		
		//�إߤ@��list�Ӧs��[{action},{§��VO},{List<§������VO>},{�����u�fVO}]
		List<Object> list = new ArrayList<Object>();
		Map<String,String> actionMap = new HashMap<>();
		actionMap.put("action", action);
		list.add(actionMap);
		for(Entry<GiftVO, List<GiftLabelVO>> vo :gift.entrySet()){
			GiftVO giftVO = vo.getKey();
			//�M��gftVO�����Ϥ����e�A²�ƶǻ�����T
			giftVO.setGift_pic(null);
			list.add(giftVO);			
			List<GiftLabelVO> giftLabelVO = vo.getValue();
			list.add(giftLabelVO);
		}
		if(giftDiscountVO != null)
			list.add(giftDiscountVO);
		
		//�Nlist�ഫ��json�ǻ��X�h
		Type listType = new TypeToken<List<Object>>() {}.getType();
		Gson gson = new Gson();
		String data = gson.toJson(list, listType);	
System.out.println(data);	
		for (Session session : allSessions) {
			if (session.isOpen())
				try {
					session.getBasicRemote().sendText(data);
				} catch (IOException e) {
					e.printStackTrace(System.err);
				}
		}
	}
}
