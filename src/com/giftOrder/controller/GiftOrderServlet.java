package com.giftOrder.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.gift.model.*;
import com.giftDiscount.model.GiftDiscountService;
import com.giftDiscount.model.GiftDiscountVO;
import com.giftOrder.model.GiftOrderService;
import com.giftOrder.model.GiftOrderVO;
import com.giftOrderDetail.model.GiftOrderDetailVO;
import com.giftReceive.model.GiftReceiveVO;
import com.google.gson.Gson;
import com.member.model.MemberVO;

public class GiftOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//�W�[�q����Ӥ����ƶq�A�����i�ק��[gift_no�Bgiftd_no�Bgifto_no�Bgiftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> addGODAmount(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_amount){
		//�إߤ@�ӥ��s���q����Ӹ�T
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//�զX�X�n�ק諸�q�����VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //�}�l��M�ץ���vo��m�A����h�}�l�ק�
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//���o��§���u�f��
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//�����л\�W�s�ƶq
	    		vo.setGiftod_amount(oriAmount + giftod_amount);
	    		vo.setGiftod_money((oriAmount + giftod_amount) * unit);
	    		vo.setGiftod_inventory(oirInventory + giftod_amount);
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //�N��s�᪺map�^��
	    return newMap;
	}	
	
	//�����л\�q����Ӥ����ƶq�A�����i�ק��[gift_no�Bgiftd_no�Bgifto_no�Bgiftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> updateGODAmount(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_amount){
		//�إߤ@�ӥ��s���q����Ӹ�T
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//�զX�X�n�ק諸�q�����VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //�}�l��M�ץ���vo��m�A����h�}�l�ק�
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//���o��§���u�f��
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//�����л\�W�s�ƶq
	    		vo.setGiftod_amount(giftod_amount);
	    		vo.setGiftod_money(giftod_amount * unit);
	    		vo.setGiftod_inventory(oirInventory + (giftod_amount- oriAmount));
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //�N��s�᪺map�^��
	    return newMap;
	}
	
	//�ק�q����Ӥ����i�μƶq(�ϥ�+-)�A�����i�ק��[gift_no�Bgiftd_no�Bgifto_no�Bgiftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> updateGODInventory(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_inventory){
		//�إߤ@�ӥ��s���q����Ӹ�T
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//�զX�X�n�ק諸�q�����VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //�}�l��M�ץ���vo��m�A����h�}�l�ק�
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//���o��§���u�f��
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//�����i�ϥμƶq
	    		vo.setGiftod_inventory(oirInventory + giftod_inventory);
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //�N��s�᪺map�^��
	    return newMap;
	}
	
	private Integer calculateTotalSum(Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail){
    	int sum = 0;
    	for(GiftOrderDetailVO vo: orderDetail.keySet()){
    		sum += vo.getGiftod_money();
    	}
    	return sum;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("Order action: " + action);	

		//���b�ШD
		if("checkoutOrder".equals(action)){
			HttpSession session = req.getSession();
			String contextPath = req.getContextPath();
			String gifto_remark = req.getParameter("gifto_remark");
			
			//���o�Ҧ��q�����+����§�C��
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			//���o��e�|���s��
			MemberVO member = (MemberVO)session.getAttribute("memSelf");
			String mem_no = member.getMem_no();
			//�إ߭q��VO
			GiftOrderVO giftOrderVO = new GiftOrderVO();
			giftOrderVO.setMem_no(mem_no);
			giftOrderVO.setGifto_remark(gifto_remark);
			
			GiftOrderService giftOrderSvc = new GiftOrderService();
			giftOrderSvc.insert(giftOrderVO, orderDetail);
			
			session.setAttribute("orderDetail", null);
			session.setAttribute("orderMoney", null);
			res.sendRedirect(contextPath+"/front_end/gift/gift_history.jsp");
		}
			
	
		//�Ӧ۫e��gift_list��ajax�ШD�A�K�[§���έק�§���ƶq���ʪ���
		if("addOrder".equals(action)){			
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");
			List<GiftReceiveVO> giftReceiveList = null;			
			Gson gson = new Gson();
			
			if(orderDetail == null){
				orderDetail = new LinkedHashMap<>();
				orderMoney = 0;
			}	
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			
			//�p���§���ʶR�`���B
			int unitPrice = giftSvc.getMoney(gift_no);//���o��§���u�f��
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//��M������i�γѾl�ƶq
			int oriAmount = 0; 
		    for(GiftOrderDetailVO vo: orderDetail.keySet()){
		    	if(vo.equals(giftOrderDetailVO)){
		    		oriAmount = vo.getGiftod_amount();
		    		break;
		    	}
		    }
		    
		    //�P�_�W�[���ƶq�O�_���W�L�����u�f���{�s�ƶq
		    if(giftd_no!=null){
		    	GiftDiscountService giftDiscountSvc = new GiftDiscountService();
		    	GiftDiscountVO giftDiscountVO = giftDiscountSvc.getOneGD(giftd_no);
		    	int oriGiftdAmount	= giftDiscountVO.getGiftd_amount();
		    	if(oriGiftdAmount < (giftod_amount+oriAmount)){
		    		map.put("status", "failure");
					map.put("gift_no", gift_no);
					map.put("gift_name", giftVO.getGift_name());
					out.print(gson.toJson(map));
					return;
		    	}
		    }
			//�P�_§���O�_�s�b�ʪ���
			if(orderDetail.containsKey(giftOrderDetailVO)){
				orderDetail = addGODAmount(orderDetail, gift_no, giftd_no, giftod_amount);
			}else{
				giftOrderDetailVO.setGiftod_amount(giftod_amount);
				Integer total = unitPrice*giftod_amount;
	    		giftOrderDetailVO.setGiftod_money(total);
	    		giftOrderDetailVO.setGiftod_inventory(giftod_amount);
				giftReceiveList = new ArrayList<>();
				orderDetail.put(giftOrderDetailVO, giftReceiveList);
			}

			session.setAttribute("orderDetail", orderDetail);
			orderMoney = calculateTotalSum(orderDetail);
			session.setAttribute("orderMoney", orderMoney);
			
			map.put("status", "success");
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			giftod_amount += oriAmount;
			map.put("giftod_amount", giftod_amount.toString());
			map.put("orderMoney", orderMoney.toString());
			out.print(gson.toJson(map));
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�R���ʪ�����[§���M��]
		if("deleteOrder".equals(action)){	
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();			
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			HttpSession session = req.getSession();
			Map<String,String> map = new HashMap<>();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");
			
			//�R���s�b�ʪ�����§��
			for(GiftOrderDetailVO detailVO: orderDetail.keySet()){
				String giftNo = detailVO.getGift_no();
				String giftdNo = detailVO.getGiftd_no();
				if((gift_no.equals(giftNo) && giftd_no=="") ||
						(gift_no.equals(giftNo) && giftd_no.equals(giftdNo))){
					orderDetail.remove(detailVO);
					break;
				}
			}
			session.setAttribute("orderDetail", orderDetail);
			orderMoney = calculateTotalSum(orderDetail);
			session.setAttribute("orderMoney", orderMoney);
			
			GiftService giftSvc = new GiftService();
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			map.put("status", "success");
			map.put("gift_name", giftVO.getGift_name());
			map.put("orderMoney", orderMoney.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�ק��ʪ�����§���ʶR�ƶq
		if("edit_giftod_amount".equals(action)){		
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			Gson gson = new Gson();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");

			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			int unitPrice = giftSvc.getMoney(gift_no);
			
		    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
		    giftOrderDetailVO.setGift_no(gift_no);
			//��X�n�ק諸�q�����VO
		    if(giftd_no!=null && !"".equals(giftd_no))
		    	giftOrderDetailVO.setGiftd_no(giftd_no);			
			for(GiftOrderDetailVO vo: orderDetail.keySet()){
				if(vo.equals(giftOrderDetailVO)){
					giftOrderDetailVO = vo;
					break;
				}
			}
			
			//���o��§������������§�C��
			List<GiftReceiveVO> recList = orderDetail.get(giftOrderDetailVO);
			
			//�p��X�ثe��§���w�ذe�h�ּƶq
			int isUse = 0;
			for(GiftReceiveVO recVO: recList){
				isUse += recVO.getGiftr_amount();
			}
			
			if(giftod_amount < isUse){
				map.put("status", "failure");
				map.put("oriAmount", giftOrderDetailVO.getGiftod_amount().toString());
				out.print(gson.toJson(map));
System.out.println("�ק諸�q����Ӽƶq�p��w�ذe�X�h���ƶq");
				return;				
			}
			
			
			//��s�q��
			orderDetail = updateGODAmount(orderDetail, gift_no, giftd_no, giftod_amount);
			session.setAttribute("orderDetail", orderDetail);
			orderMoney = calculateTotalSum(orderDetail);
			session.setAttribute("orderMoney", orderMoney);
			
			map.put("status", "success");
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			map.put("giftod_money", new Integer(unitPrice*giftod_amount).toString());
			map.put("giftod_inventory", new Integer(giftod_amount-isUse).toString());			
			map.put("orderMoney", orderMoney.toString());
			out.print(gson.toJson(map));
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�s�W�@������§[GiftReceiveVO]
		if("addRecGift".equals(action)){
			//�����Ѽ�
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			String mem_no_self = req.getParameter("mem_no_self");
			String mem_no_other = req.getParameter("mem_no_other");
			String amount = req.getParameter("giftr_amount");	
			String giftr_message = req.getParameter("giftr_message");
			Integer giftr_amount = null;
			if(!"".equals(amount)){
				giftr_amount = new Integer(amount);
			}
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");

			GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(!"".equals(giftd_no))
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			//�إߤ@���s������§VO
			GiftReceiveVO giftReceiveVO = new GiftReceiveVO();
			giftReceiveVO.setMem_no_self(mem_no_self);
			giftReceiveVO.setMem_no_other(mem_no_other);
			if(!"".equals(giftr_amount))
				giftReceiveVO.setGiftr_amount(giftr_amount);
			if(!"".equals(giftr_message))
			giftReceiveVO.setGiftr_message(giftr_message);
			
			//�ˬd����§�ƶq[GiftReceiveVO]�O�_���W�L�i�γѾl�ƶq[GiftOrderDetailVO]
			for(GiftOrderDetailVO vo: orderDetail.keySet()){
				if(vo.equals(giftOrderDetailVO))
					giftOrderDetailVO = vo;
			}
			int inventory = giftOrderDetailVO.getGiftod_inventory() - giftr_amount;
			if(inventory < 0){
				map.put("status", "failure");
				out.print(gson.toJson(map));
System.out.println("�ثe��§���ذe�ƶq: �w�W�L");
				return;
			}
			
			//�ˬd�P���q����ӬO�_���e���P�ӷ|��
			List<GiftReceiveVO> giftReceiveList = orderDetail.get(giftOrderDetailVO);
			if(giftReceiveList.contains(giftReceiveVO)){
				map.put("status", "failure");
				out.print(gson.toJson(map));
System.out.println("�ثe��§���w�ذe�L�P�@�H");
				return;
			}
			
			//�s�W����§����
			giftReceiveList.add(giftReceiveVO);
			//��s�q��
			orderDetail = updateGODInventory(orderDetail, gift_no, giftd_no, -giftr_amount);
			session.setAttribute("orderDetail", orderDetail);
			
			map.put("status", "success");
			map.put("giftr_amount", giftr_amount.toString());
			out.print(gson.toJson(map));
System.out.println("�ثe��§���ذe�ƶq:" + giftReceiveList.size());
		}		
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�R���@������§[GiftReceiveVO]
		if("removeRecGift".equals(action)){
			//�����Ѽ�
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			String mem_no_self = req.getParameter("mem_no_self");
			String mem_no_other = req.getParameter("mem_no_other");
			String giftr_amount = req.getParameter("giftr_amount");	
			String giftr_message = req.getParameter("giftr_message");
			
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			//��X�R������§���ӵ��q�����
			GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(!"".equals(giftd_no))
				giftOrderDetailVO.setGiftd_no(giftd_no);
			List<GiftReceiveVO> giftReceiveList = orderDetail.get(giftOrderDetailVO);
			//��X�R������§���ӵ�����§����
			for(GiftReceiveVO vo: giftReceiveList){
				if(vo.getMem_no_other().equals(mem_no_other)){
					giftReceiveList.remove(vo);
					break;
				}
			}
			
			if(!"".equals(giftr_amount))
				orderDetail = updateGODInventory(orderDetail, gift_no, giftd_no, new Integer(giftr_amount));
			session.setAttribute("orderDetail", orderDetail);
			
			map.put("status", "success");
			map.put("giftr_amount", giftr_amount);
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}		
				
		//�Ӧ۫e��gift_order��ajax�ШD�A�ק怜��§
		if("editRecGift".equals(action)){		
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			String mem_no_self = req.getParameter("mem_no_self");
			String mem_no_other = req.getParameter("mem_no_other");
			String amount = req.getParameter("giftr_amount");
			String giftr_message = req.getParameter("giftr_message");
			
			Integer giftr_amount = new Integer(amount);
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			Gson gson = new Gson();
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");

			//��X�n�ק諸�q�����VO
		    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
		    giftOrderDetailVO.setGift_no(gift_no);
		    if(giftd_no!=null && !"".equals(giftd_no))
		    	giftOrderDetailVO.setGiftd_no(giftd_no);			
			for(GiftOrderDetailVO vo: orderDetail.keySet()){
				if(vo.equals(giftOrderDetailVO)){
					giftOrderDetailVO = vo;
					break;
				}
			}
			
			//��X�n�ק諸����§VO
			List<GiftReceiveVO> recList = orderDetail.get(giftOrderDetailVO);
			GiftReceiveVO giftReceiveVO = new GiftReceiveVO();
			giftReceiveVO.setMem_no_self(mem_no_self);
			giftReceiveVO.setMem_no_other(mem_no_other);
			int index = recList.indexOf(giftReceiveVO);
			giftReceiveVO = recList.get(index);

			//�ˬd�ק諸����§�ƶq[GiftReceiveVO]�O�_���W�L�ӵ��q����Ӫ��i�γѾl�ƶq[GiftOrderDetailVO]
			Integer giftod_inventory = giftOrderDetailVO.getGiftod_inventory();
			//�W�[���ƶq
			Integer addAmount = giftr_amount - giftReceiveVO.getGiftr_amount();
			if((giftod_inventory - addAmount) < 0){
				map.put("status", "failure");
				map.put("oriAmount", giftReceiveVO.getGiftr_amount().toString());
				out.print(gson.toJson(map));
System.out.println("�w�W�L�i�ػP��§���ƶq");
				return;				
			}
			
			//����§���q�檺�Ѿl�ƶq
			orderDetail = updateGODInventory(orderDetail, gift_no, giftd_no, -addAmount);
			session.setAttribute("orderDetail", orderDetail);
			giftReceiveVO.setGiftr_amount(giftr_amount);
			giftReceiveVO.setGiftr_message(giftr_message);
			
			//�ק怜��§���ƶq
			recList.remove(index);
			recList.add(index, giftReceiveVO);
			
			map.put("status", "success");
			map.put("gift_no", gift_no);
			map.put("giftr_amount", addAmount.toString());
			out.print(gson.toJson(map));			
		}

	}

}
