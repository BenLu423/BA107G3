package com.evem.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.evem.model.*;
import com.member.model.MemberVO;
import com.qa.model.QaService;
import com.qa.model.QaVO;

public class EvemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/***************************開始查詢資料 ****************************************/
			EvemDAO dao = new EvemDAO();
			List<EvemVO> list = dao.getAll();

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/back_end/evem/listAllEvem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}


//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("evemes_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入留言編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String evemes_no = null;
//				try {
//					evemes_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("留言編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				EvemDAO dao = new EvemDAO();
//				EvemVO evemVO = dao.findByPrimaryKey(evemes_no);
//				if (evemVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("evemVO", evemVO); // 資料庫取出的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		if ("getOne_For_Display".equals(action)) { // 來自select_page1.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("qa_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				String qa_no = null;
//				try {
//					qa_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				QaService qaSvc = new QaService();
//				QaVO qaVO = qaSvc.getOneQa(qa_no);
//				if (qaVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("qaVO", qaVO); // 資料庫取出的qaVO物件,存入req
//				String url = "/back_end/qa/listOneQa.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneQa.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllQa.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String evemes_no = new String(req.getParameter("evemes_no"));
				
				/***************************2.開始查詢資料****************************************/
				EvemService evemSvc = new EvemService();
				EvemVO evemVO = evemSvc.getOneEvem(evemes_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("evemVO", evemVO);         // 資料庫取出的qaVO物件,存入req
				String url = "/back_end/evem/update_evem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_qa_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			EvemService evemSvc = new EvemService();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String evemes_no = new String(req.getParameter("evemes_no").trim());
//				String eve_no = new String(req.getParameter("eve_no").trim());
//				String mem_no = new String(req.getParameter("mem_no").trim());
//				System.out.println("inter first");
				EvemVO evemVO = evemSvc.getOneEvem(evemes_no);
				System.out.println("留言內容:"+evemVO.getEvemes_cnt());
//				java.sql.Timestamp evemes_time = null;
//				try {
//					evemes_time = java.sql.Timestamp.valueOf(req.getParameter("evemes_time").trim());
//					
//				} catch (IllegalArgumentException e) {
//					evemes_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
//				String evemes_cnt = new String(req.getParameter("evemes_cnt").trim());
				
//				java.sql.Timestamp evemes_restime = null;
//				
//				try {
//					
//					evemes_restime = java.sql.Timestamp.valueOf(req.getParameter("evemes_restime").trim());
//				} catch (IllegalArgumentException e) {
//					evemes_restime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				String evemes_rescnt = req.getParameter("evemes_rescnt");
				
				
				
//				EvemVO evemVO = new EvemVO();
//				evemVO.setEvemes_no(evemes_no);
				evemVO.setEvemes_rescnt(evemes_rescnt);
//				evemVO.setEvemes_no(evemes_no);
//				evemVO.setEve_no(eve_no);
//				evemVO.setMem_no(mem_no);
//				evemVO.setEvemes_time(evemes_time);
//				evemVO.setEvemes_cnt(evemes_cnt);
//				evemVO.setEvemes_restime(evemes_restime);
//				evemVO.setEvemes_rescnt(evemes_rescnt);
				System.out.println("inter set");

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("evemVO",evemVO); // 含有輸入格式錯誤的qaVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/evem/update_evem_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				evemSvc.updateEvem(evemVO);
				System.out.println("inter update");
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("evemVO",evemVO); // 資料庫update成功後,正確的的qaVO物件,存入req
				String url = "/back_end/evem/listOneEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/update_evem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert3".equals(action)) { // 來自addQa.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				//String qa_no =new String (req.getParameter("qa_no").trim());
//				String evemes_no = new String(req.getParameter("evemes_no").trim());
				HttpSession session = req.getSession();
				MemberVO memSelf = (MemberVO)session.getAttribute("memSelf");
				System.out.println(memSelf.getMem_no());
//				java.sql.Timestamp evemes_time = null;
//				try {
//					evemes_time=new java.sql.Timestamp(System.currentTimeMillis());
//					
//				} catch (IllegalArgumentException e) {
//					//evemes_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				String evemes_cnt = req.getParameter("evemes_cnt");
				String evemes_cnt2 = new String(evemes_cnt.getBytes("ISO-8859-1"),"Big5");
				System.out.println("evemes_cnt:"+evemes_cnt2);
//				java.sql.Timestamp evemes_restime = null;
//				
//				try {
//					
//					evemes_restime = java.sql.Timestamp.valueOf(req.getParameter("evemes_restime").trim());
//				} catch (IllegalArgumentException e) {
//					evemes_restime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				String evemes_rescnt = new String(req.getParameter("evemes_rescnt").trim());

				EvemVO evemVO = new EvemVO();
				evemVO.setEve_no("E001");
				evemVO.setMem_no(memSelf.getMem_no());
				evemVO.setEvemes_cnt(evemes_cnt);
//				evemVO.setEvemes_restime(evemes_restime);
//				evemVO.setEvemes_rescnt(evemes_rescnt);
				
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("evemVO", evemVO); // 含有輸入格式錯誤的qaVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/evem/listAllEvem.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				System.out.println("inter");
				/***************************2.開始新增資料***************************************/
				EvemService evemSvc = new EvemService();
				evemSvc.addEvem(evemVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/evem/listAllEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllQa.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllQa.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String evemes_no = new String(req.getParameter("evemes_no"));
				
				/***************************2.開始刪除資料***************************************/
				EvemService evemSvc = new EvemService();
				evemSvc.deleteEvem(evemes_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/evem/listAllEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
