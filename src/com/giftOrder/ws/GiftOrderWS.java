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
		System.out.println(mem_no + "��go�w�s�u");
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
		System.out.println(mem_no + "����go�w���u : " + Integer.toString(reason.getCloseCode().getCode()));
	}
	
	public void multipleSendGift(String action, Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail) {
		//�N�Ҧ���N���q��PN*N������§�i�����
		for(Entry<GiftOrderDetailVO, List<GiftReceiveVO>> set: orderDetail.entrySet()){
			GiftOrderDetailVO giftOrderDetailVO = set.getKey();
			//�p�G�ӵ��q�欰�����u�f§���A�h�q���u�W�Ҧ��H���ƶq�w���
			if(giftOrderDetailVO.getGiftd_no()!=null){
System.out.println("GiftOrderWS update gift :" + giftOrderDetailVO.getGift_no());
				giftStatusWS.broadcast("updateGift", giftOrderDetailVO.getGift_no());
			}
			//�N�ӵ��q�檺N������§���O�q��[�b�u]����§�H
			String gift_no = giftOrderDetailVO.getGift_no();
			for(GiftReceiveVO giftReceiveVO: set.getValue()){
				//��§�H�s��
				String mem_no_other = giftReceiveVO.getMem_no_other();
				Session session = allUsers.get(mem_no_other);
				//�p�G�Ӧ�§�H�b�u�W
				if(session != null){
					GiftService giftSvc = new GiftService();
					//���o������[§��VO/§������VO]�P[�����u�fVO���i�ରnull]
					Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(gift_no);
					GiftDiscountService gdSvc = new GiftDiscountService();
					GiftDiscountVO giftDiscountVO = gdSvc.getCurrentValidGift(gift_no);
					
					//�إߤ@��list�Ӧs��[{action},{§��VO},{List<§������VO>},{����§VO},{�����u�fVO}]
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
					list.add(giftReceiveVO);
					if(giftDiscountVO != null)
						list.add(giftDiscountVO);

					//�Nlist�ഫ��json�ǻ��X�h
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
