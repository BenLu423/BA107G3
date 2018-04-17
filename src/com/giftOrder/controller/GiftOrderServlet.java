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

	//增加訂單明細內的數量，但不可修改到[gift_no、giftd_no、gifto_no、giftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> addGODAmount(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_amount){
		//建立一個全新的訂單明細資訊
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//組合出要修改的訂單明細VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //開始找尋修正的vo位置，找到後則開始修改
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//取得該禮物優惠價
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//直接覆蓋上新數量
	    		vo.setGiftod_amount(oriAmount + giftod_amount);
	    		vo.setGiftod_money((oriAmount + giftod_amount) * unit);
	    		vo.setGiftod_inventory(oirInventory + giftod_amount);
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //將更新後的map回傳
	    return newMap;
	}	
	
	//直接覆蓋訂單明細內的數量，但不可修改到[gift_no、giftd_no、gifto_no、giftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> updateGODAmount(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_amount){
		//建立一個全新的訂單明細資訊
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//組合出要修改的訂單明細VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //開始找尋修正的vo位置，找到後則開始修改
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//取得該禮物優惠價
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//直接覆蓋上新數量
	    		vo.setGiftod_amount(giftod_amount);
	    		vo.setGiftod_money(giftod_amount * unit);
	    		vo.setGiftod_inventory(oirInventory + (giftod_amount- oriAmount));
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //將更新後的map回傳
	    return newMap;
	}
	
	//修改訂單明細內的可用數量(使用+-)，但不可修改到[gift_no、giftd_no、gifto_no、giftod_no]
	private Map<GiftOrderDetailVO, List<GiftReceiveVO>> updateGODInventory(Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori,
			String gift_no, String giftd_no, int giftod_inventory){
		//建立一個全新的訂單明細資訊
		Map<GiftOrderDetailVO, List<GiftReceiveVO>> newMap = new LinkedHashMap<>();
		//組合出要修改的訂單明細VO
	    GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
	    giftOrderDetailVO.setGift_no(gift_no);
	    if(giftd_no!=null && !"".equals(giftd_no))
	    	giftOrderDetailVO.setGiftd_no(giftd_no);
	    //開始找尋修正的vo位置，找到後則開始修改
	    Set<GiftOrderDetailVO> oriSet = ori.keySet();
	    for(GiftOrderDetailVO vo: oriSet){
	    	if(vo.equals(giftOrderDetailVO)){
	    		GiftService giftSvc = new GiftService();
				int unit = giftSvc.getMoney(gift_no);//取得該禮物優惠價
	    		int oriAmount = vo.getGiftod_amount();
	    		int oirInventory = vo.getGiftod_inventory();
	    		
	    		//扣除可使用數量
	    		vo.setGiftod_inventory(oirInventory + giftod_inventory);
	    	}
	    	newMap.put(vo, ori.get(vo));
	    }
	    //將更新後的map回傳
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

		//結帳請求
		if("checkoutOrder".equals(action)){
			HttpSession session = req.getSession();
			String contextPath = req.getContextPath();
			String gifto_remark = req.getParameter("gifto_remark");
			
			//取得所有訂單明細+收贈禮列表
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			//取得當前會員編號
			MemberVO member = (MemberVO)session.getAttribute("memSelf");
			String mem_no = member.getMem_no();
			//建立訂單VO
			GiftOrderVO giftOrderVO = new GiftOrderVO();
			giftOrderVO.setMem_no(mem_no);
			giftOrderVO.setGifto_remark(gifto_remark);
			
			GiftOrderService giftOrderSvc = new GiftOrderService();
			giftOrderSvc.insert(giftOrderVO, orderDetail);
			
			session.setAttribute("orderDetail", null);
			session.setAttribute("orderMoney", null);
			res.sendRedirect(contextPath+"/front_end/gift/gift_history.jsp");
		}
			
	
		//來自前端gift_list的ajax請求，添加禮物或修改禮物數量至購物車
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
			
			//計算單禮物購買總金額
			int unitPrice = giftSvc.getMoney(gift_no);//取得該禮物優惠價
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//找尋原先的可用剩餘數量
			int oriAmount = 0; 
		    for(GiftOrderDetailVO vo: orderDetail.keySet()){
		    	if(vo.equals(giftOrderDetailVO)){
		    		oriAmount = vo.getGiftod_amount();
		    		break;
		    	}
		    }
		    
		    //判斷增加的數量是否有超過限時優惠的現存數量
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
			//判斷禮物是否存在購物車
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
		
		//來自前端gift_order的ajax請求，刪除購物車的[禮物清單]
		if("deleteOrder".equals(action)){	
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();			
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			HttpSession session = req.getSession();
			Map<String,String> map = new HashMap<>();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");
			
			//刪除存在購物車的禮物
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
		
		//來自前端gift_order的ajax請求，修改購物車的禮物購買數量
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
			//找出要修改的訂單明細VO
		    if(giftd_no!=null && !"".equals(giftd_no))
		    	giftOrderDetailVO.setGiftd_no(giftd_no);			
			for(GiftOrderDetailVO vo: orderDetail.keySet()){
				if(vo.equals(giftOrderDetailVO)){
					giftOrderDetailVO = vo;
					break;
				}
			}
			
			//取得該禮物對應的收贈禮列表
			List<GiftReceiveVO> recList = orderDetail.get(giftOrderDetailVO);
			
			//計算出目前該禮物已贈送多少數量
			int isUse = 0;
			for(GiftReceiveVO recVO: recList){
				isUse += recVO.getGiftr_amount();
			}
			
			if(giftod_amount < isUse){
				map.put("status", "failure");
				map.put("oriAmount", giftOrderDetailVO.getGiftod_amount().toString());
				out.print(gson.toJson(map));
System.out.println("修改的訂單明細數量小於已贈送出去的數量");
				return;				
			}
			
			
			//更新訂單
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
		
		//來自前端gift_order的ajax請求，新增一筆收贈禮[GiftReceiveVO]
		if("addRecGift".equals(action)){
			//接收參數
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
			
			//建立一筆新的收贈禮VO
			GiftReceiveVO giftReceiveVO = new GiftReceiveVO();
			giftReceiveVO.setMem_no_self(mem_no_self);
			giftReceiveVO.setMem_no_other(mem_no_other);
			if(!"".equals(giftr_amount))
				giftReceiveVO.setGiftr_amount(giftr_amount);
			if(!"".equals(giftr_message))
			giftReceiveVO.setGiftr_message(giftr_message);
			
			//檢查收贈禮數量[GiftReceiveVO]是否有超過可用剩餘數量[GiftOrderDetailVO]
			for(GiftOrderDetailVO vo: orderDetail.keySet()){
				if(vo.equals(giftOrderDetailVO))
					giftOrderDetailVO = vo;
			}
			int inventory = giftOrderDetailVO.getGiftod_inventory() - giftr_amount;
			if(inventory < 0){
				map.put("status", "failure");
				out.print(gson.toJson(map));
System.out.println("目前該禮物贈送數量: 已超過");
				return;
			}
			
			//檢查同筆訂單明細是否有送給同個會員
			List<GiftReceiveVO> giftReceiveList = orderDetail.get(giftOrderDetailVO);
			if(giftReceiveList.contains(giftReceiveVO)){
				map.put("status", "failure");
				out.print(gson.toJson(map));
System.out.println("目前該禮物已贈送過同一人");
				return;
			}
			
			//新增收贈禮紀錄
			giftReceiveList.add(giftReceiveVO);
			//更新訂單
			orderDetail = updateGODInventory(orderDetail, gift_no, giftd_no, -giftr_amount);
			session.setAttribute("orderDetail", orderDetail);
			
			map.put("status", "success");
			map.put("giftr_amount", giftr_amount.toString());
			out.print(gson.toJson(map));
System.out.println("目前該禮物贈送數量:" + giftReceiveList.size());
		}		
		
		//來自前端gift_order的ajax請求，刪除一筆收贈禮[GiftReceiveVO]
		if("removeRecGift".equals(action)){
			//接收參數
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
			//找出刪除收贈禮的該筆訂單明細
			GiftOrderDetailVO giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(!"".equals(giftd_no))
				giftOrderDetailVO.setGiftd_no(giftd_no);
			List<GiftReceiveVO> giftReceiveList = orderDetail.get(giftOrderDetailVO);
			//找出刪除收贈禮的該筆收贈禮紀錄
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
				
		//來自前端gift_order的ajax請求，修改收贈禮
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

			//找出要修改的訂單明細VO
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
			
			//找出要修改的收贈禮VO
			List<GiftReceiveVO> recList = orderDetail.get(giftOrderDetailVO);
			GiftReceiveVO giftReceiveVO = new GiftReceiveVO();
			giftReceiveVO.setMem_no_self(mem_no_self);
			giftReceiveVO.setMem_no_other(mem_no_other);
			int index = recList.indexOf(giftReceiveVO);
			giftReceiveVO = recList.get(index);

			//檢查修改的收贈禮數量[GiftReceiveVO]是否有超過該筆訂單明細的可用剩餘數量[GiftOrderDetailVO]
			Integer giftod_inventory = giftOrderDetailVO.getGiftod_inventory();
			//增加的數量
			Integer addAmount = giftr_amount - giftReceiveVO.getGiftr_amount();
			if((giftod_inventory - addAmount) < 0){
				map.put("status", "failure");
				map.put("oriAmount", giftReceiveVO.getGiftr_amount().toString());
				out.print(gson.toJson(map));
System.out.println("已超過可贈與的禮物數量");
				return;				
			}
			
			//扣除禮物訂單的剩餘數量
			orderDetail = updateGODInventory(orderDetail, gift_no, giftd_no, -addAmount);
			session.setAttribute("orderDetail", orderDetail);
			giftReceiveVO.setGiftr_amount(giftr_amount);
			giftReceiveVO.setGiftr_message(giftr_message);
			
			//修改收贈禮的數量
			recList.remove(index);
			recList.add(index, giftReceiveVO);
			
			map.put("status", "success");
			map.put("gift_no", gift_no);
			map.put("giftr_amount", addAmount.toString());
			out.print(gson.toJson(map));			
		}

	}

}
