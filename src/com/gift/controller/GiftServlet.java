package com.gift.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.gift.model.*;
import com.giftLabel.model.*;
import com.giftLabelDetail.model.*;

@MultipartConfig
public class GiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1287527750058413992L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	private void show(HttpServletRequest req, GiftService giftSvc){
		HttpSession session = req.getSession();
		//取得查詢字串
		Map<String, String[]> giftStrQuery = (Map<String, String[]>)session.getAttribute("giftStrQuery");
		//取得查詢的禮物
		Map<GiftVO, List<GiftLabelVO>> gifts = null;
		if(giftStrQuery != null)
			gifts = giftSvc.getGiftAll(giftStrQuery);
		//取得欲修改的禮物
		Map<GiftVO, List<GiftLabelVO>> giftEdits = (Map<GiftVO, List<GiftLabelVO>>) session.getAttribute("giftEdits");
		if(giftEdits != null && !giftEdits.isEmpty()){
			gifts = giftSvc.deductEditGift(gifts, giftEdits);
		}	
		req.setAttribute("gifts", gifts);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println("G action: " + action);		
		
		//來自gift_add.jsp的新增請求
		if(("addGift").equals(action)){
			GiftService giftSvc = new GiftService();
			HttpSession session = req.getSession();
			String requestURL = "/back_end/gift/gift_add.jsp";
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***********************1.接收請求參數 , 輸入格式的錯誤處理*************************/
				Part gift_pic = req.getPart("gift_pic");
				if(gift_pic.getSize() != 0 && !"image/jpeg".equals(gift_pic.getContentType())){
					errorMsgs.put("gift_pic", "檔案非jpg格式");
				}else if(gift_pic.getSize() == 0 ){
					errorMsgs.put("gift_pic", "檔案未上傳");
				}
				
				String gift_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,7}$";
				String gift_name = req.getParameter("gift_name");
				if(gift_name == null || gift_name.trim().length() == 0){
					errorMsgs.put("gift_name", "名稱請勿空白");
				} else if (!gift_name.trim().matches(gift_nameReg)){
					errorMsgs.put("gift_name", "名稱只能是中、英文字母、數字和_ , 且長度必需在1到7之間");
				}
				
				String[] gift_labels = req.getParameterValues("gift_labels");
				if(gift_labels == null){
					errorMsgs.put("gift_labels", "至少選一個標籤");
				}
				
				String gift_cnt = req.getParameter("gift_cnt");
				if(gift_cnt == null || gift_cnt.trim().length() == 0){
					errorMsgs.put("gift_cnt", "內容請勿空白");
				} else if (gift_cnt.trim().length()>=200){
					errorMsgs.put("gift_cnt", "內容長度必需在1到200之間");
				}
				
				Integer gift_price = null;
				try {
					gift_price = new Integer(req.getParameter("gift_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("gift_price","價格請填數字");
				}

				//產生param傳過來的file轉成base64回傳
				if(gift_pic.getSize() != 0){
					InputStream is = gift_pic.getInputStream();
					ByteArrayOutputStream aos = new ByteArrayOutputStream();
					byte[] pic = new byte[8*1024];
					int len;
					while((len = is.read(pic))!=-1){
						aos.write(pic, 0, len);
					}
					Base64.Encoder encoder = Base64.getEncoder();
					String noAddPic = encoder.encodeToString(aos.toByteArray());
					System.out.println(noAddPic);
					req.setAttribute("noAddPic", aos.toByteArray());
				}
				//產生param傳過來的GiftLable物件們
				GiftLabelService giftLabelSvc = new GiftLabelService();
				List<GiftLabelVO> labelList = new ArrayList<>();
				GiftLabelVO labelVO = null;
				if(gift_labels != null){
					for(String giftl_no: gift_labels){
						labelVO = giftLabelSvc.getOneGiftLabelByNo(giftl_no);
						labelList.add(labelVO);
					}
					req.setAttribute("labelList", labelList);
				}
				
				if (!errorMsgs.isEmpty()) {
					//用來建立新giftAdds用
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.開始新增禮物資料***************************************/
				//產生GiftVO物件
				GiftVO giftVO = new GiftVO();
				giftVO.setGift_name(gift_name);
				giftVO.setGift_cnt(gift_cnt);
				giftVO.setGift_price(gift_price);
				if(gift_pic.getSize()!=0){
					InputStream is = gift_pic.getInputStream();
					ByteArrayOutputStream aos = new ByteArrayOutputStream();
					byte[] pic = new byte[8*1024];
					int len;
					while((len = is.read(pic))!=-1){
						aos.write(pic, 0, len);
					}
					giftVO.setGift_pic(aos.toByteArray());
				}

				
				//建立標籤明細物件
				List<GiftLabelDetailVO> deatilList = new ArrayList<GiftLabelDetailVO>();
				GiftLabelDetailVO detailVO = null;
				for(String str: gift_labels){
					detailVO = new GiftLabelDetailVO();
					detailVO.setGiftl_no(str);
					deatilList.add(detailVO);
				}
				giftSvc.addGift(giftVO, deatilList);
				
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);				
			}catch(Exception e){
System.out.println("安安又爆炸惹");
				errorMsgs.put("Exception","新增資料失敗: "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}		
		
		//來自gift_list.jsp的刪除請求
		if("deleteOneGift".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			GiftService giftSvc = new GiftService();
			
			try{
				String gift_no = req.getParameter("gift_no");
				giftSvc.deleteGift(gift_no);
				
				show(req,giftSvc);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);


			} catch (Exception e){
				errorMsgs.put("Exception","刪除資料失敗: "+e.getMessage());
				show(req,giftSvc);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
			
		//來自gift_list.jsp的修改請求
		if(("editGift").equals(action)){
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			String requestURL = "/back_end/gift/gift_index.jsp";
			String gift_no = req.getParameter("gift_no");
			Map<GiftVO, List<GiftLabelVO>> giftEdits = (Map<GiftVO, List<GiftLabelVO>>) session.getAttribute("giftEdits");
			Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");		
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***********************1.接收請求參數 , 輸入格式的錯誤處理*************************/
				Part gift_pic = req.getPart("gift_pic");
				if(gift_pic.getSize() != 0 && !"image/jpeg".equals(gift_pic.getContentType())){
					errorMsgs.put("gift_pic", "檔案非jpg格式");
				}
				
				String gift_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,7}$";
				String gift_name = req.getParameter("gift_name");
				if(gift_name == null || gift_name.trim().length() == 0){
					errorMsgs.put("gift_name", "名稱請勿空白");
				} else if (!gift_name.trim().matches(gift_nameReg)){
					errorMsgs.put("gift_name", "名稱只能是中、英文字母、數字和_ , 且長度必需在1到7之間");
				}
				
				String[] gift_labels = req.getParameterValues("gift_labels");
				if(gift_labels == null){
					errorMsgs.put("gift_labels", "至少選一個標籤");
				}
				
				String gift_cnt = req.getParameter("gift_cnt");
				if(gift_cnt == null || gift_cnt.trim().length() == 0){
					errorMsgs.put("gift_cnt", "內容請勿空白");
				} else if (gift_name.trim().length()>=200){
					errorMsgs.put("gift_cnt", "內容長度必需在1到200之間");
				}
				
				Integer gift_price = null;
				try {
					gift_price = new Integer(req.getParameter("gift_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("gift_price","價格請填數字");
				}

				String[] gift_is_on = req.getParameterValues("gift_is_on");
				if(gift_is_on == null){
					errorMsgs.put("gift_is_on", "標籤至少澤一");
				}else if(gift_is_on.length > 1){
					errorMsgs.put("gift_is_on", "標籤只能澤一");
				}else if(!"尚未上架".equals(gift_is_on[0]) && !"上架中".equals(gift_is_on[0]) && !"已下架".equals(gift_is_on[0])){
					errorMsgs.put("gift_is_on", "禮物狀態，請輸入[尚未上架]或[上架中]或[已上架]");
				}
				
				//產生param傳過來的GiftVO物件
				GiftVO giftVO = giftSvc.getOneGift(gift_no);
				giftVO.setGift_name(gift_name);
				giftVO.setGift_cnt(gift_cnt);
				giftVO.setGift_price(gift_price);
				if(gift_pic.getSize()!=0){
					InputStream is = gift_pic.getInputStream();
					ByteArrayOutputStream aos = new ByteArrayOutputStream();
					byte[] pic = new byte[8*1024];
					int len;
					while((len = is.read(pic))!=-1){
						aos.write(pic, 0, len);
					}
					giftVO.setGift_pic(aos.toByteArray());
				}
				giftVO.setGift_is_on(gift_is_on[0]);
				
				//產生param傳過來的GiftLable物件們
				GiftLabelService giftLabelSvc = new GiftLabelService();
				List<GiftLabelVO> labelList = new ArrayList<>();
				GiftLabelVO labelVO = null;
				if(gift_labels != null){
					for(String giftl_no: gift_labels){
						labelVO = giftLabelSvc.getOneGiftLabelByNo(giftl_no);
						labelList.add(labelVO);
					}
				}

				if (!errorMsgs.isEmpty()) {
					//用來建立新giftEdits用
					Map<GiftVO, List<GiftLabelVO>> newEdits = new LinkedHashMap<>();
					
					//deep copy (giftEdits)
					Map<GiftVO, List<GiftLabelVO>> oriEdits = new LinkedHashMap<>();
				    for (Map.Entry<GiftVO, List<GiftLabelVO>> entry : giftEdits.entrySet()){
				    	oriEdits.put(entry.getKey(),new ArrayList<GiftLabelVO>(entry.getValue()));
					}					
				    Set<GiftVO> oriSet = oriEdits.keySet();
				    //開始找尋修正的新vo位置，找到後則替代
				    for(GiftVO vo: oriSet){
				    	if(vo.equals(giftVO))
				    		newEdits.put(giftVO, labelList);
				    	else
				    		newEdits.put(vo, oriEdits.get(vo));
				    }
				    //將更新後的map給予覆蓋
				    giftEdits = newEdits;
				    
					session.setAttribute("giftEdits", giftEdits);
					giftEditMsgs.put(gift_no, errorMsgs);
					session.setAttribute("giftEditMsgs",giftEditMsgs);
					show(req,giftSvc);
					
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始更新禮物資料***************************************/
				//建立標籤明細物件
				List<GiftLabelDetailVO> deatilList = new ArrayList<GiftLabelDetailVO>();
				GiftLabelDetailVO detailVO = null;
				for(String giftl_no: gift_labels){
					detailVO = new GiftLabelDetailVO();
					detailVO.setGift_no(gift_no);
					detailVO.setGiftl_no(giftl_no);
					deatilList.add(detailVO);
					
				}
				giftSvc.updateGift(giftVO, deatilList);

				giftEdits.remove(giftVO);
				session.setAttribute("giftEdits", giftEdits);

				show(req,giftSvc);				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);				
			}catch(Exception e){
System.out.println("Hen爆炸!!!");
				errorMsgs.put("Exception", e.getMessage());
				show(req,giftSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		
		
		//來自gift_index.jsp的查詢請求
		if("searchGifts".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			String requestURL = req.getParameter("requestURL");
			
			try {
				//判斷是否有存在欲修改的禮物
				Map<GiftVO, List<GiftLabelVO>> giftEdits = (Map<GiftVO, List<GiftLabelVO>>) session.getAttribute("giftEdits");
				Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");
				if(giftEdits == null){
					giftEdits = new LinkedHashMap<>();
					giftEditMsgs = new LinkedHashMap<>();
				}
				
				
				//增加某筆修改禮物
				String edit_gift_no = req.getParameter("edit_gift_no");
				if(edit_gift_no != null){
					Map<GiftVO, List<GiftLabelVO>> map = giftSvc.getOne(edit_gift_no);
					Set<GiftVO> set = map.keySet();
					for(GiftVO giftVO: set){
						giftEdits.put(giftVO, map.get(giftVO));
					}
					session.setAttribute("giftEdits", giftEdits);

					Map<String,String> editMsg = new HashMap<>();
					giftEditMsgs.put(edit_gift_no, editMsg);
					session.setAttribute("giftEditMsgs", giftEditMsgs);
				}
	
				//刪除某筆修改禮物
				String del_gift_no = req.getParameter("del_gift_no");
				if(del_gift_no != null){
					Map<GiftVO, List<GiftLabelVO>> gift = giftSvc.getOne(del_gift_no);
					Set<GiftVO> setGift = gift.keySet();
					GiftVO delGift = null;
					for(GiftVO giftVO: setGift){
						delGift = giftVO;
					}
					Set<GiftVO> set = giftEdits.keySet();
					for(GiftVO giftVO: set){
						if(giftVO.equals(delGift)){
							giftEdits.remove(giftVO);
							break;
						}
					}
					session.setAttribute("giftEdits", giftEdits);
					
					
				}
				
				
				//取得查詢字串
				Map<String, String[]> giftStrQuery = (Map<String, String[]>)session.getAttribute("giftStrQuery");
				
				//如果為每次請求搜尋[即不是換頁]
				if(req.getParameter("whichPage") == null ){
					Map<String, String[]> map = new HashMap<String, String[]> (req.getParameterMap());
					session.setAttribute("giftStrQuery", map);
					giftStrQuery = map;
				}
				Map<GiftVO, List<GiftLabelVO>> gifts = giftSvc.getGiftAll(giftStrQuery);
				
				//將查詢的禮物扣除要修改的禮物，避免重複出現
				if(giftEdits!=null || !giftEdits.isEmpty()){
				gifts = giftSvc.deductEditGift(gifts, giftEdits);
				}
				//將查詢禮物(原先扣除修改)的結果，並set進req
				req.setAttribute("gifts", gifts);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e){
				
				errorMsgs.put("Exception","無法取得資料: "+e.getMessage());
				show(req,giftSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
			
		}
		
		//來自gift_index.jsp請求到新增網頁
		if("gift_add".equals(action)){
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/gift/gift_add.jsp");
			successView.forward(req, res);
		}
		
		//來自gift_index.jsp請求到禮物網頁
		if("gift_show".equals(action)){
			GiftService giftSvc = new GiftService();
			show(req,giftSvc);
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/gift/gift_index.jsp");
			successView.forward(req, res);
		}
	}

}
