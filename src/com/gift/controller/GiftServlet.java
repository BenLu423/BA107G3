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
		//���o�d�ߦr��
		Map<String, String[]> giftStrQuery = (Map<String, String[]>)session.getAttribute("giftStrQuery");
		//���o�d�ߪ�§��
		Map<GiftVO, List<GiftLabelVO>> gifts = null;
		if(giftStrQuery != null)
			gifts = giftSvc.getGiftAll(giftStrQuery);
		//���o���ק諸§��
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
		
		//�Ӧ�gift_add.jsp���s�W�ШD
		if(("addGift").equals(action)){
			GiftService giftSvc = new GiftService();
			HttpSession session = req.getSession();
			String requestURL = "/back_end/gift/gift_add.jsp";
			Map<String,String> errorMsgs = new LinkedHashMap<>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***********************1.�����ШD�Ѽ� , ��J�榡�����~�B�z*************************/
				Part gift_pic = req.getPart("gift_pic");
				if(gift_pic.getSize() != 0 && !"image/jpeg".equals(gift_pic.getContentType())){
					errorMsgs.put("gift_pic", "�ɮ׫Djpg�榡");
				}else if(gift_pic.getSize() == 0 ){
					errorMsgs.put("gift_pic", "�ɮץ��W��");
				}
				
				String gift_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,7}$";
				String gift_name = req.getParameter("gift_name");
				if(gift_name == null || gift_name.trim().length() == 0){
					errorMsgs.put("gift_name", "�W�ٽФŪť�");
				} else if (!gift_name.trim().matches(gift_nameReg)){
					errorMsgs.put("gift_name", "�W�٥u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��7����");
				}
				
				String[] gift_labels = req.getParameterValues("gift_labels");
				if(gift_labels == null){
					errorMsgs.put("gift_labels", "�ܤֿ�@�Ӽ���");
				}
				
				String gift_cnt = req.getParameter("gift_cnt");
				if(gift_cnt == null || gift_cnt.trim().length() == 0){
					errorMsgs.put("gift_cnt", "���e�ФŪť�");
				} else if (gift_cnt.trim().length()>=200){
					errorMsgs.put("gift_cnt", "���e���ץ��ݦb1��200����");
				}
				
				Integer gift_price = null;
				try {
					gift_price = new Integer(req.getParameter("gift_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("gift_price","����ж�Ʀr");
				}

				//����param�ǹL�Ӫ�file�নbase64�^��
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
				//����param�ǹL�Ӫ�GiftLable�����
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
					//�Ψӫإ߷sgiftAdds��
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.�}�l�s�W§�����***************************************/
				//����GiftVO����
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

				
				//�إ߼��ҩ��Ӫ���
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
System.out.println("�w�w�S�z���S");
				errorMsgs.put("Exception","�s�W��ƥ���: "+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}		
		
		//�Ӧ�gift_list.jsp���R���ШD
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
				errorMsgs.put("Exception","�R����ƥ���: "+e.getMessage());
				show(req,giftSvc);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
			
		//�Ӧ�gift_list.jsp���ק�ШD
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
				/***********************1.�����ШD�Ѽ� , ��J�榡�����~�B�z*************************/
				Part gift_pic = req.getPart("gift_pic");
				if(gift_pic.getSize() != 0 && !"image/jpeg".equals(gift_pic.getContentType())){
					errorMsgs.put("gift_pic", "�ɮ׫Djpg�榡");
				}
				
				String gift_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,7}$";
				String gift_name = req.getParameter("gift_name");
				if(gift_name == null || gift_name.trim().length() == 0){
					errorMsgs.put("gift_name", "�W�ٽФŪť�");
				} else if (!gift_name.trim().matches(gift_nameReg)){
					errorMsgs.put("gift_name", "�W�٥u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��7����");
				}
				
				String[] gift_labels = req.getParameterValues("gift_labels");
				if(gift_labels == null){
					errorMsgs.put("gift_labels", "�ܤֿ�@�Ӽ���");
				}
				
				String gift_cnt = req.getParameter("gift_cnt");
				if(gift_cnt == null || gift_cnt.trim().length() == 0){
					errorMsgs.put("gift_cnt", "���e�ФŪť�");
				} else if (gift_name.trim().length()>=200){
					errorMsgs.put("gift_cnt", "���e���ץ��ݦb1��200����");
				}
				
				Integer gift_price = null;
				try {
					gift_price = new Integer(req.getParameter("gift_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.put("gift_price","����ж�Ʀr");
				}

				String[] gift_is_on = req.getParameterValues("gift_is_on");
				if(gift_is_on == null){
					errorMsgs.put("gift_is_on", "���ҦܤֿA�@");
				}else if(gift_is_on.length > 1){
					errorMsgs.put("gift_is_on", "���ҥu��A�@");
				}else if(!"�|���W�[".equals(gift_is_on[0]) && !"�W�[��".equals(gift_is_on[0]) && !"�w�U�[".equals(gift_is_on[0])){
					errorMsgs.put("gift_is_on", "§�����A�A�п�J[�|���W�[]��[�W�[��]��[�w�W�[]");
				}
				
				//����param�ǹL�Ӫ�GiftVO����
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
				
				//����param�ǹL�Ӫ�GiftLable�����
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
					//�Ψӫإ߷sgiftEdits��
					Map<GiftVO, List<GiftLabelVO>> newEdits = new LinkedHashMap<>();
					
					//deep copy (giftEdits)
					Map<GiftVO, List<GiftLabelVO>> oriEdits = new LinkedHashMap<>();
				    for (Map.Entry<GiftVO, List<GiftLabelVO>> entry : giftEdits.entrySet()){
				    	oriEdits.put(entry.getKey(),new ArrayList<GiftLabelVO>(entry.getValue()));
					}					
				    Set<GiftVO> oriSet = oriEdits.keySet();
				    //�}�l��M�ץ����svo��m�A����h���N
				    for(GiftVO vo: oriSet){
				    	if(vo.equals(giftVO))
				    		newEdits.put(giftVO, labelList);
				    	else
				    		newEdits.put(vo, oriEdits.get(vo));
				    }
				    //�N��s�᪺map�����л\
				    giftEdits = newEdits;
				    
					session.setAttribute("giftEdits", giftEdits);
					giftEditMsgs.put(gift_no, errorMsgs);
					session.setAttribute("giftEditMsgs",giftEditMsgs);
					show(req,giftSvc);
					
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l��s§�����***************************************/
				//�إ߼��ҩ��Ӫ���
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
System.out.println("Hen�z��!!!");
				errorMsgs.put("Exception", e.getMessage());
				show(req,giftSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		
		
		//�Ӧ�gift_index.jsp���d�߽ШD
		if("searchGifts".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			GiftService giftSvc = new GiftService();
			String requestURL = req.getParameter("requestURL");
			
			try {
				//�P�_�O�_���s�b���ק諸§��
				Map<GiftVO, List<GiftLabelVO>> giftEdits = (Map<GiftVO, List<GiftLabelVO>>) session.getAttribute("giftEdits");
				Map<String,Map<String,String>> giftEditMsgs = (Map<String, Map<String, String>>) session.getAttribute("giftEditMsgs");
				if(giftEdits == null){
					giftEdits = new LinkedHashMap<>();
					giftEditMsgs = new LinkedHashMap<>();
				}
				
				
				//�W�[�Y���ק�§��
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
	
				//�R���Y���ק�§��
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
				
				
				//���o�d�ߦr��
				Map<String, String[]> giftStrQuery = (Map<String, String[]>)session.getAttribute("giftStrQuery");
				
				//�p�G���C���ШD�j�M[�Y���O����]
				if(req.getParameter("whichPage") == null ){
					Map<String, String[]> map = new HashMap<String, String[]> (req.getParameterMap());
					session.setAttribute("giftStrQuery", map);
					giftStrQuery = map;
				}
				Map<GiftVO, List<GiftLabelVO>> gifts = giftSvc.getGiftAll(giftStrQuery);
				
				//�N�d�ߪ�§�������n�ק諸§���A�קK���ƥX�{
				if(giftEdits!=null || !giftEdits.isEmpty()){
				gifts = giftSvc.deductEditGift(gifts, giftEdits);
				}
				//�N�d��§��(��������ק�)�����G�A��set�ireq
				req.setAttribute("gifts", gifts);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
			} catch (Exception e){
				
				errorMsgs.put("Exception","�L�k���o���: "+e.getMessage());
				show(req,giftSvc);	
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
			
		}
		
		//�Ӧ�gift_index.jsp�ШD��s�W����
		if("gift_add".equals(action)){
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/gift/gift_add.jsp");
			successView.forward(req, res);
		}
		
		//�Ӧ�gift_index.jsp�ШD��§������
		if("gift_show".equals(action)){
			GiftService giftSvc = new GiftService();
			show(req,giftSvc);
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/gift/gift_index.jsp");
			successView.forward(req, res);
		}
	}

}
