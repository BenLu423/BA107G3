package com.giftOrder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONML;

import com.gift.model.GiftService;
import com.gift.model.GiftVO;
import com.giftDiscount.model.GiftDiscountService;
import com.giftLabel.model.GiftLabelVO;
import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftReceive.model.GiftReceiveVO;
import com.google.gson.Gson;

public class GiftOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println("Order action: " + action);	

		//�Ӧ۫e��gift_list��ajax�ШD�A�K�[§���έק�§���ƶq���ʪ���
		if("addOrder".equals(action)){			
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");
			GiftOrderDetailVO giftOrderDetailVO = null;
			List<GiftReceiveVO> giftReceiveList = null;
			
			if(orderDetail == null){
				orderDetail = new LinkedHashMap<>();
				orderMoney = 0;
			}	
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			//�P�_§���O�_�s�b�ʪ���
			boolean isExist = false;
			for(GiftOrderDetailVO detailVO: orderDetail.keySet()){
				String giftNo = detailVO.getGift_no();
				String giftdNo = detailVO.getGiftd_no();
				if((gift_no.equals(giftNo) && giftd_no==null) ||
						(gift_no.equals(giftNo) && giftd_no!=null && giftd_no.equals(giftdNo))){
					giftOrderDetailVO = detailVO;
					giftReceiveList = orderDetail.get(detailVO);
					isExist = true;
					map.put("isExist", "yes");
					break;
				}
			}
			Integer unitPrice;
			if(giftd_no==null)
				unitPrice = giftSvc.getOneGift(gift_no).getGift_price();//���o��§�����
			else
				unitPrice = giftSvc.getMoney(gift_no);//���o��§���u�f��
			//�p���§���ʶR�`���B
			Integer total;
			if(isExist){
				//§���w�s�b�ʪ�����[�ק�ƶq]
				giftOrderDetailVO.setGiftod_unit(unitPrice);
				Integer oriAmount = giftOrderDetailVO.getGiftod_amount();
				giftOrderDetailVO.setGiftod_amount(oriAmount + giftod_amount);
				total = unitPrice*(oriAmount + giftod_amount);
				giftOrderDetailVO.setGiftod_money(total);
				Integer oriInventory = giftOrderDetailVO.getGiftod_inventory();
				giftOrderDetailVO.setGiftod_inventory(oriInventory + giftod_amount);
				
				
				//�Ψӫإ߷sgiftEdits��
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> temp = new LinkedHashMap<>();
				//deep copy (orderDetail)
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori = new LinkedHashMap<>();
			    for (Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> entry : orderDetail.entrySet()){
			    	ori.put(entry.getKey(),new ArrayList<GiftReceiveVO>(entry.getValue()));
				}					
			    Set<GiftOrderDetailVO> oriSet = ori.keySet();
			    //�}�l��M�ץ����svo��m�A����h���N
			    for(GiftOrderDetailVO vo: oriSet){
			    	if(vo.equals(giftOrderDetailVO))
			    		temp.put(giftOrderDetailVO, giftReceiveList);
			    	else
			    		temp.put(vo, ori.get(vo));
			    }
			    //�N��s�᪺map�����л\
			    orderDetail = temp;
			}else{
				//§���Y���b�ʪ�����[�s�W]
				giftOrderDetailVO = new GiftOrderDetailVO();
				giftOrderDetailVO.setGift_no(gift_no);
				if(giftd_no != null)
					giftOrderDetailVO.setGiftd_no(giftd_no);
				giftOrderDetailVO.setGiftod_unit(unitPrice);
				giftOrderDetailVO.setGiftod_amount(giftod_amount);
				total = unitPrice*giftod_amount;
				giftOrderDetailVO.setGiftod_money(total);
				giftOrderDetailVO.setGiftod_inventory(giftod_amount);
				giftReceiveList = new ArrayList<>();
				orderDetail.put(giftOrderDetailVO, giftReceiveList);
			}
			session.setAttribute("orderDetail", orderDetail);
			orderMoney += (unitPrice * giftod_amount);
			session.setAttribute("orderMoney", orderMoney);
			//�H�U���ϥ�
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			map.put("gift_amount", giftod_amount.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
	}

}
