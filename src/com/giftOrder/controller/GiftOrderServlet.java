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
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println("Order action: " + action);	

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
			
			if(orderDetail == null){
				orderDetail = new LinkedHashMap<>();
				orderMoney = 0;
			}	
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			
			//計算單禮物購買總金額
			Integer unitPrice;
			if(giftd_no==null)
				unitPrice = giftSvc.getOneGift(gift_no).getGift_price();//取得該禮物原價
			else
				unitPrice = giftSvc.getMoney(gift_no);//取得該禮物優惠價
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//判斷禮物是否存在購物車
			if(orderDetail.containsKey(giftOrderDetailVO)){
				//用來建立新giftEdits用
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> temp = new LinkedHashMap<>();
				//deep copy (orderDetail)
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori = new LinkedHashMap<>();
			    for (Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> entry : orderDetail.entrySet()){
			    	ori.put(entry.getKey(),entry.getValue());
				}					
			    Set<GiftOrderDetailVO> oriSet = ori.keySet();
			    //開始找尋修正的新vo位f置，找到後則替代
			    for(GiftOrderDetailVO vo: oriSet){
			    	if(vo.equals(giftOrderDetailVO)){
			    		giftOrderDetailVO.setGiftod_amount(vo.getGiftod_amount() + giftod_amount);
			    		Integer total = unitPrice*giftod_amount;
			    		giftOrderDetailVO.setGiftod_money(total);
			    		giftOrderDetailVO.setGiftod_inventory(vo.getGiftod_inventory() + giftod_amount);
			    		temp.put(giftOrderDetailVO, giftReceiveList);
			    	}else{
			    		temp.put(vo, ori.get(vo));
			    	}
			    }
			    //將更新後的map給予覆蓋
			    orderDetail = temp;
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
			//以下未使用
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
		
		//來自前端gift_order的ajax請求，刪除購物車的[禮物清單]
		if("deleteOrder".equals(action)){		
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			HttpSession session = req.getSession();
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
		}
		
		//來自前端gift_order的ajax請求，修改購物車的禮物購買數量
		if("edit_giftod_amount".equals(action)){		
			res.setContentType("application/json;charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			Map<String,String> map = new HashMap<>();
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> orderDetail = (Map<GiftOrderDetailVO, List<GiftReceiveVO>>) session.getAttribute("orderDetail");
			Integer orderMoney = (Integer) session.getAttribute("orderMoney");
			List<GiftReceiveVO> giftReceiveList = null;

			String requestURL = req.getParameter("requestURL");
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			
			//計算單禮物購買總金額
			Integer unitPrice;
			if(giftd_no==null)
				unitPrice = giftSvc.getOneGift(gift_no).getGift_price();//取得該禮物原價
			else
				unitPrice = giftSvc.getMoney(gift_no);//取得該禮物優惠價
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//用來建立新giftEdits用
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> temp = new LinkedHashMap<>();
			//deep copy (orderDetail)
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori = new LinkedHashMap<>();
		    for (Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> entry : orderDetail.entrySet()){
		    	ori.put(entry.getKey(),entry.getValue());
			}					
		    Set<GiftOrderDetailVO> oriSet = ori.keySet();
		    //開始找尋修正的新vo位f置，找到後則替代
		    for(GiftOrderDetailVO vo: oriSet){
		    	if(vo.equals(giftOrderDetailVO)){
		    		giftOrderDetailVO.setGiftod_amount(giftod_amount);
		    		Integer total = unitPrice*giftod_amount;
		    		giftOrderDetailVO.setGiftod_money(total);
		    		giftOrderDetailVO.setGiftod_inventory(giftod_amount);
		    		temp.put(giftOrderDetailVO, giftReceiveList);
		    	}else{
		    		temp.put(vo, ori.get(vo));
		    	}
		    }
		    //將更新後的map給予覆蓋
		    orderDetail = temp;

			session.setAttribute("orderDetail", orderDetail);
			orderMoney = calculateTotalSum(orderDetail);
			session.setAttribute("orderMoney", orderMoney);
			//以下未使用
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			map.put("orderMoney", orderMoney.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
		
		//來自前端gift_order的ajax請求，修改收禮人的收贈禮數量
		if("edit_giftr_amount".equals(action)){		
			String requestURL = req.getParameter("requestURL");
			String gift_no = req.getParameter("gift_no");
			String mem_no_other = req.getParameter("mem_no_other");
			String giftod_amount = req.getParameter("giftod_amount");
		}
		
		//來自前端gift_order的ajax請求，修改收禮人的收贈禮留言
		if("edit_giftr_message".equals(action)){		
			String requestURL = req.getParameter("requestURL");
			String gift_no = req.getParameter("gift_no");
			String mem_no_other = req.getParameter("mem_no_other");
			String giftr_message = req.getParameter("giftr_message");
		}
		
	}

}
