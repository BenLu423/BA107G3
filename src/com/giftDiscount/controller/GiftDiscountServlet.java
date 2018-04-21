package com.giftDiscount.controller;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gift.model.*;
import com.gift.ws.GiftStatusWS;
import com.giftDiscount.model.GiftDiscountService;
import com.giftDiscount.model.GiftDiscountVO;

@MultipartConfig
public class GiftDiscountServlet extends HttpServlet {
	private static final long serialVersionUID = 1287527750058413992L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	private void show(HttpServletRequest req, GiftDiscountService giftDiscountSvc){
		HttpSession session = req.getSession();
		//取得查詢字串
		Map<String, String[]> giftDiscountQuery = (Map<String, String[]>)session.getAttribute("giftDiscountQuery");
		//取得查詢的禮物
		Map<GiftDiscountVO, GiftVO> giftDiscounts = null;
//		if(giftDiscountQuery != null)
			giftDiscounts = giftDiscountSvc.getGiftDiscountTotal();
		//取得欲修改的禮物
		Map<GiftDiscountVO, GiftVO> giftDiscountEdits = (Map<GiftDiscountVO, GiftVO>) session.getAttribute("giftDiscountEdits");
		if(giftDiscountEdits != null && !giftDiscountEdits.isEmpty()){
			giftDiscounts = giftDiscountSvc.deductEditGD(giftDiscounts, giftDiscountEdits);
		}	
		req.setAttribute("giftDiscounts", giftDiscounts);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println("GD action: " + action);		
		

		//來自giftdiscount_list.jsp的修改請求
		if(("editDiscountGift").equals(action)){
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			GiftDiscountService giftDiscountSvc = new GiftDiscountService();
			String requestURL = "/back_end/gift/gift_discount.jsp";
			String giftd_no = req.getParameter("giftd_no");
			String gift_no = req.getParameter("gift_no");
			Map<GiftDiscountVO, GiftVO> giftDiscountEdits = (Map<GiftDiscountVO, GiftVO>) session.getAttribute("giftDiscountEdits");
			Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***********************1.接收請求參數 , 輸入格式的錯誤處理*************************/
				Double giftd_percent = null;
				try {
					giftd_percent = new Double(req.getParameter("giftd_percent").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_percent","價格請填數字");
				}
				
				java.sql.Timestamp giftd_start = null;
				String strStart = req.getParameter("giftd_start");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				try{
					java.util.Date date = df.parse(strStart);
					giftd_start = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_start","開始日期輸入錯誤");
				}
				
				java.sql.Timestamp giftd_end = null;
				String strEnd = req.getParameter("giftd_end");
				try{
					java.util.Date date = df.parse(strEnd);
					giftd_end = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_end","結束日期輸入錯誤");
				}
				
				Integer giftd_amount = null;
				try {
					giftd_amount = new Integer(req.getParameter("giftd_amount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_amount","數量請填整數");
				}
				
				//產生param傳過來的GiftDiscountVO物件
				GiftDiscountVO giftDiscountVO = giftDiscountSvc.getOneGD(giftd_no);
				giftDiscountVO.setGiftd_percent(giftd_percent);
				giftDiscountVO.setGiftd_start(giftd_start);
				giftDiscountVO.setGiftd_end(giftd_end);
				giftDiscountVO.setGiftd_amount(giftd_amount);
				
				//產生param傳過來的GiftLable物件們
				GiftVO giftVO = giftSvc.getOneGift(gift_no);

				if (!errorMsgs.isEmpty()) {
					//用來建立新giftDiscountEdits用
					Map<GiftDiscountVO, GiftVO> newEdits = new LinkedHashMap<>();
					
					//deep copy (giftDiscountEdits)
					Map<GiftDiscountVO, GiftVO> oriEdits = new LinkedHashMap<>();
				    for (Map.Entry<GiftDiscountVO, GiftVO> entry : giftDiscountEdits.entrySet()){
				    	oriEdits.put(entry.getKey(),entry.getValue());
					}					
				    Set<GiftDiscountVO> oriSet = oriEdits.keySet();
				    //開始找尋修正的新vo位置，找到後則替代
				    for(GiftDiscountVO vo: oriSet){
				    	if(vo.equals(giftDiscountVO))
				    		newEdits.put(giftDiscountVO, giftVO);
				    	else
				    		newEdits.put(vo, oriEdits.get(vo));
				    }
				    //將更新後的map給予覆蓋
				    giftDiscountEdits = newEdits;
				    
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);
					giftEditMsgs.put(giftd_no, errorMsgs);
					session.setAttribute("giftEditMsgs",giftEditMsgs);
					show(req,giftDiscountSvc);
					
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始更新禮物資料***************************************/
				//建立標籤明細物件
				giftDiscountSvc.update(giftDiscountVO);
				//呼叫webSocket即時通知使用者，限時優惠數量已變更
				GiftStatusWS giftStatusWS = new GiftStatusWS();
				giftStatusWS.broadcast("updateGift", gift_no);
				//移除可修改的限時優惠VO
				giftDiscountEdits.remove(giftDiscountVO);
				session.setAttribute("giftDiscountEdits", giftDiscountEdits);

				show(req,giftDiscountSvc);				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);				
			}catch(Exception e){
System.out.println("Hen爆炸!!!");
				errorMsgs.put("Exception", e.getMessage());
				show(req,giftDiscountSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		
		
		//來自gift_index.jsp的查詢請求
		if("searchGiftDiscounts".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			GiftDiscountService giftDiscountSvc = new GiftDiscountService();
			String requestURL = "/back_end/gift/gift_discount.jsp";
			
			try {
				//判斷是否有存在欲修改的禮物
				Map<GiftDiscountVO, GiftVO> giftDiscountEdits = (Map<GiftDiscountVO, GiftVO>) session.getAttribute("giftDiscountEdits");
				Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");
				if(giftDiscountEdits == null){
					giftDiscountEdits = new LinkedHashMap<>();
					giftEditMsgs = new LinkedHashMap<>();
				}
				
				
				//增加某筆修改禮物
				String edit_giftd_no = req.getParameter("edit_giftd_no");
				if(edit_giftd_no != null){
					//取得欲修改的限時優惠物件
					Map<GiftDiscountVO, GiftVO> map = giftDiscountSvc.getOne(edit_giftd_no);
					//將該限時優惠加入修改列表內
					giftDiscountEdits.putAll(map);
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);

					Map<String,String> editMsg = new HashMap<>();
					giftEditMsgs.put(edit_giftd_no, editMsg);
					session.setAttribute("giftEditMsgs", giftEditMsgs);
				}
	
				//刪除某筆修改禮物
				String del_giftd_no = req.getParameter("del_giftd_no");
				if(del_giftd_no != null){
					//取得欲刪除的限時優惠禮物
					Map<GiftDiscountVO, GiftVO> gift = giftDiscountSvc.getOne(del_giftd_no);
					//取得該筆的key值
					Set<GiftDiscountVO> setGiftd = gift.keySet();
					GiftDiscountVO delGift = null;
					for(GiftDiscountVO giftDiscountVO: setGiftd){
						delGift = giftDiscountVO;
					}
					//取得修改列表內的所有key，並比對來刪除
					Set<GiftDiscountVO> set = giftDiscountEdits.keySet();
					for(GiftDiscountVO giftDiscountVO: set){
						if(giftDiscountVO.equals(delGift)){
							giftDiscountEdits.remove(giftDiscountVO);
							break;
						}
					}
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);
				}
				
				
				//取得查詢字串
				Map<String, String[]> giftDiscountQuery = (Map<String, String[]>)session.getAttribute("giftDiscountQuery");
				
				//如果為每次請求搜尋[即不是換頁]
				String whichPage = req.getParameter("whichPage");
				if( whichPage == null || giftDiscountQuery == null){
					Map<String, String[]> map = new HashMap<String, String[]> (req.getParameterMap());
					session.setAttribute("giftDiscountQuery", map);
					giftDiscountQuery = map;
				}
				Map<GiftDiscountVO, GiftVO> giftDiscounts = giftDiscountSvc.getGiftDiscountTotal();

				//將查詢的禮物扣除要修改的禮物，避免重複出現
				if(giftDiscountEdits!=null || !giftDiscountEdits.isEmpty()){
					giftDiscounts = giftDiscountSvc.deductEditGD(giftDiscounts, giftDiscountEdits);
				}
				//將查詢禮物(原先扣除修改)的結果，並set進req
				req.setAttribute("giftDiscounts", giftDiscounts);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e){
				
				errorMsgs.put("Exception","無法取得資料: "+e.getMessage());
				show(req,giftDiscountSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
			
		}
//		
	}

}
