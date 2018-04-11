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
			
			if(orderDetail == null){
				orderDetail = new LinkedHashMap<>();
				orderMoney = 0;
			}	
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			Integer giftod_amount = new Integer(req.getParameter("giftod_amount"));
			GiftVO giftVO = giftSvc.getOneGift(gift_no);
			
			//�p���§���ʶR�`���B
			Integer unitPrice;
			if(giftd_no==null)
				unitPrice = giftSvc.getOneGift(gift_no).getGift_price();//���o��§�����
			else
				unitPrice = giftSvc.getMoney(gift_no);//���o��§���u�f��
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//�P�_§���O�_�s�b�ʪ���
			if(orderDetail.containsKey(giftOrderDetailVO)){
				//�Ψӫإ߷sgiftEdits��
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> temp = new LinkedHashMap<>();
				//deep copy (orderDetail)
				Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori = new LinkedHashMap<>();
			    for (Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> entry : orderDetail.entrySet()){
			    	ori.put(entry.getKey(),entry.getValue());
				}					
			    Set<GiftOrderDetailVO> oriSet = ori.keySet();
			    //�}�l��M�ץ����svo��f�m�A����h���N
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
			    //�N��s�᪺map�����л\
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
			//�H�U���ϥ�
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�R���ʪ�����[§���M��]
		if("deleteOrder".equals(action)){		
			String gift_no = req.getParameter("gift_no");
			String giftd_no = req.getParameter("giftd_no");
			HttpSession session = req.getSession();
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
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�ק��ʪ�����§���ʶR�ƶq
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
			
			//�p���§���ʶR�`���B
			Integer unitPrice;
			if(giftd_no==null)
				unitPrice = giftSvc.getOneGift(gift_no).getGift_price();//���o��§�����
			else
				unitPrice = giftSvc.getMoney(gift_no);//���o��§���u�f��
			
			GiftOrderDetailVO giftOrderDetailVO = null;
			giftOrderDetailVO = new GiftOrderDetailVO();
			giftOrderDetailVO.setGift_no(gift_no);
			if(giftd_no != null)
				giftOrderDetailVO.setGiftd_no(giftd_no);
			
			giftOrderDetailVO.setGiftod_unit(unitPrice);
			
			//�Ψӫإ߷sgiftEdits��
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> temp = new LinkedHashMap<>();
			//deep copy (orderDetail)
			Map<GiftOrderDetailVO, List<GiftReceiveVO>> ori = new LinkedHashMap<>();
		    for (Map.Entry<GiftOrderDetailVO, List<GiftReceiveVO>> entry : orderDetail.entrySet()){
		    	ori.put(entry.getKey(),entry.getValue());
			}					
		    Set<GiftOrderDetailVO> oriSet = ori.keySet();
		    //�}�l��M�ץ����svo��f�m�A����h���N
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
		    //�N��s�᪺map�����л\
		    orderDetail = temp;

			session.setAttribute("orderDetail", orderDetail);
			orderMoney = calculateTotalSum(orderDetail);
			session.setAttribute("orderMoney", orderMoney);
			//�H�U���ϥ�
			map.put("gift_no", gift_no);
			map.put("gift_name", giftVO.getGift_name());
			map.put("giftod_amount", giftod_amount.toString());
			map.put("orderMoney", orderMoney.toString());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�ק怜§�H������§�ƶq
		if("edit_giftr_amount".equals(action)){		
			String requestURL = req.getParameter("requestURL");
			String gift_no = req.getParameter("gift_no");
			String mem_no_other = req.getParameter("mem_no_other");
			String giftod_amount = req.getParameter("giftod_amount");
		}
		
		//�Ӧ۫e��gift_order��ajax�ШD�A�ק怜§�H������§�d��
		if("edit_giftr_message".equals(action)){		
			String requestURL = req.getParameter("requestURL");
			String gift_no = req.getParameter("gift_no");
			String mem_no_other = req.getParameter("mem_no_other");
			String giftr_message = req.getParameter("giftr_message");
		}
		
	}

}
