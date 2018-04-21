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
		//���o�d�ߦr��
		Map<String, String[]> giftDiscountQuery = (Map<String, String[]>)session.getAttribute("giftDiscountQuery");
		//���o�d�ߪ�§��
		Map<GiftDiscountVO, GiftVO> giftDiscounts = null;
//		if(giftDiscountQuery != null)
			giftDiscounts = giftDiscountSvc.getGiftDiscountTotal();
		//���o���ק諸§��
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
		

		//�Ӧ�giftdiscount_list.jsp���ק�ШD
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
				/***********************1.�����ШD�Ѽ� , ��J�榡�����~�B�z*************************/
				Double giftd_percent = null;
				try {
					giftd_percent = new Double(req.getParameter("giftd_percent").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_percent","����ж�Ʀr");
				}
				
				java.sql.Timestamp giftd_start = null;
				String strStart = req.getParameter("giftd_start");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				try{
					java.util.Date date = df.parse(strStart);
					giftd_start = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_start","�}�l�����J���~");
				}
				
				java.sql.Timestamp giftd_end = null;
				String strEnd = req.getParameter("giftd_end");
				try{
					java.util.Date date = df.parse(strEnd);
					giftd_end = new java.sql.Timestamp(date.getTime());
				} catch(ParseException e){
					errorMsgs.put("giftd_end","���������J���~");
				}
				
				Integer giftd_amount = null;
				try {
					giftd_amount = new Integer(req.getParameter("giftd_amount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("giftd_amount","�ƶq�ж���");
				}
				
				//����param�ǹL�Ӫ�GiftDiscountVO����
				GiftDiscountVO giftDiscountVO = giftDiscountSvc.getOneGD(giftd_no);
				giftDiscountVO.setGiftd_percent(giftd_percent);
				giftDiscountVO.setGiftd_start(giftd_start);
				giftDiscountVO.setGiftd_end(giftd_end);
				giftDiscountVO.setGiftd_amount(giftd_amount);
				
				//����param�ǹL�Ӫ�GiftLable�����
				GiftVO giftVO = giftSvc.getOneGift(gift_no);

				if (!errorMsgs.isEmpty()) {
					//�Ψӫإ߷sgiftDiscountEdits��
					Map<GiftDiscountVO, GiftVO> newEdits = new LinkedHashMap<>();
					
					//deep copy (giftDiscountEdits)
					Map<GiftDiscountVO, GiftVO> oriEdits = new LinkedHashMap<>();
				    for (Map.Entry<GiftDiscountVO, GiftVO> entry : giftDiscountEdits.entrySet()){
				    	oriEdits.put(entry.getKey(),entry.getValue());
					}					
				    Set<GiftDiscountVO> oriSet = oriEdits.keySet();
				    //�}�l��M�ץ����svo��m�A����h���N
				    for(GiftDiscountVO vo: oriSet){
				    	if(vo.equals(giftDiscountVO))
				    		newEdits.put(giftDiscountVO, giftVO);
				    	else
				    		newEdits.put(vo, oriEdits.get(vo));
				    }
				    //�N��s�᪺map�����л\
				    giftDiscountEdits = newEdits;
				    
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);
					giftEditMsgs.put(giftd_no, errorMsgs);
					session.setAttribute("giftEditMsgs",giftEditMsgs);
					show(req,giftDiscountSvc);
					
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l��s§�����***************************************/
				//�إ߼��ҩ��Ӫ���
				giftDiscountSvc.update(giftDiscountVO);
				//�I�swebSocket�Y�ɳq���ϥΪ̡A�����u�f�ƶq�w�ܧ�
				GiftStatusWS giftStatusWS = new GiftStatusWS();
				giftStatusWS.broadcast("updateGift", gift_no);
				//�����i�ק諸�����u�fVO
				giftDiscountEdits.remove(giftDiscountVO);
				session.setAttribute("giftDiscountEdits", giftDiscountEdits);

				show(req,giftDiscountSvc);				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);				
			}catch(Exception e){
System.out.println("Hen�z��!!!");
				errorMsgs.put("Exception", e.getMessage());
				show(req,giftDiscountSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		
		
		//�Ӧ�gift_index.jsp���d�߽ШD
		if("searchGiftDiscounts".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			GiftDiscountService giftDiscountSvc = new GiftDiscountService();
			String requestURL = "/back_end/gift/gift_discount.jsp";
			
			try {
				//�P�_�O�_���s�b���ק諸§��
				Map<GiftDiscountVO, GiftVO> giftDiscountEdits = (Map<GiftDiscountVO, GiftVO>) session.getAttribute("giftDiscountEdits");
				Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");
				if(giftDiscountEdits == null){
					giftDiscountEdits = new LinkedHashMap<>();
					giftEditMsgs = new LinkedHashMap<>();
				}
				
				
				//�W�[�Y���ק�§��
				String edit_giftd_no = req.getParameter("edit_giftd_no");
				if(edit_giftd_no != null){
					//���o���ק諸�����u�f����
					Map<GiftDiscountVO, GiftVO> map = giftDiscountSvc.getOne(edit_giftd_no);
					//�N�ӭ����u�f�[�J�ק�C��
					giftDiscountEdits.putAll(map);
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);

					Map<String,String> editMsg = new HashMap<>();
					giftEditMsgs.put(edit_giftd_no, editMsg);
					session.setAttribute("giftEditMsgs", giftEditMsgs);
				}
	
				//�R���Y���ק�§��
				String del_giftd_no = req.getParameter("del_giftd_no");
				if(del_giftd_no != null){
					//���o���R���������u�f§��
					Map<GiftDiscountVO, GiftVO> gift = giftDiscountSvc.getOne(del_giftd_no);
					//���o�ӵ���key��
					Set<GiftDiscountVO> setGiftd = gift.keySet();
					GiftDiscountVO delGift = null;
					for(GiftDiscountVO giftDiscountVO: setGiftd){
						delGift = giftDiscountVO;
					}
					//���o�ק�C�����Ҧ�key�A�ä��ӧR��
					Set<GiftDiscountVO> set = giftDiscountEdits.keySet();
					for(GiftDiscountVO giftDiscountVO: set){
						if(giftDiscountVO.equals(delGift)){
							giftDiscountEdits.remove(giftDiscountVO);
							break;
						}
					}
					session.setAttribute("giftDiscountEdits", giftDiscountEdits);
				}
				
				
				//���o�d�ߦr��
				Map<String, String[]> giftDiscountQuery = (Map<String, String[]>)session.getAttribute("giftDiscountQuery");
				
				//�p�G���C���ШD�j�M[�Y���O����]
				String whichPage = req.getParameter("whichPage");
				if( whichPage == null || giftDiscountQuery == null){
					Map<String, String[]> map = new HashMap<String, String[]> (req.getParameterMap());
					session.setAttribute("giftDiscountQuery", map);
					giftDiscountQuery = map;
				}
				Map<GiftDiscountVO, GiftVO> giftDiscounts = giftDiscountSvc.getGiftDiscountTotal();

				//�N�d�ߪ�§�������n�ק諸§���A�קK���ƥX�{
				if(giftDiscountEdits!=null || !giftDiscountEdits.isEmpty()){
					giftDiscounts = giftDiscountSvc.deductEditGD(giftDiscounts, giftDiscountEdits);
				}
				//�N�d��§��(��������ק�)�����G�A��set�ireq
				req.setAttribute("giftDiscounts", giftDiscounts);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e){
				
				errorMsgs.put("Exception","�L�k���o���: "+e.getMessage());
				show(req,giftDiscountSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
			
		}
//		
	}

}
