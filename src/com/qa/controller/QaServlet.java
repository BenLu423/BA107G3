package com.qa.controller;
import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.qa.model.*;
public class QaServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("qa_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String qa_no = null;
				try {
					qa_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
				if (qaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/qa/listOneQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/select_page1.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.開始查詢資料****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("qaVO", qaVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back_end/qa/update_qa_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/listAllQa.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String qa_no = new String(req.getParameter("qa_no").trim());
				
				
				java.sql.Date qa_date = null;
				try {
					qa_date = java.sql.Date.valueOf(req.getParameter("qa_date").trim());
				} catch (IllegalArgumentException e) {
					qa_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String qa_title= new String(req.getParameter("qa_title").trim());
				String qa_cnt= new String(req.getParameter("qa_cnt").trim());

				
				QaVO qaVO = new QaVO();
				qaVO.setQa_no(qa_no);
				qaVO.setQa_date(qa_date);
				qaVO.setQa_title(qa_title);
				qaVO.setQa_cnt(qa_cnt);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/update_qa_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.updateQa(qa_no,qa_date,qa_title,qa_cnt);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back_end/qa/listOneQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/update_qa_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String qa_no = req.getParameter("qa_no");
				
				java.sql.Date qa_date = null;
				try {
					qa_date = java.sql.Date.valueOf(req.getParameter("qa_date").trim());
				} catch (IllegalArgumentException e) {
					qa_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String qa_title = req.getParameter("qa_title");
				String qa_cnt = req.getParameter("qa_cnt");
				
				
				QaVO qaVO = new QaVO();
				qaVO.setQa_no(qa_no);
				qaVO.setQa_date(qa_date);
				qaVO.setQa_title(qa_title);
				qaVO.setQa_cnt(qa_cnt);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/addQa.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.addQa(qa_no,qa_date,qa_title,qa_cnt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/qa/listAllQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/addQa.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.開始刪除資料***************************************/
				QaService qaSvc = new QaService();
				qaSvc.deleteQa(qa_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back_end/qa/listAllQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/listAllQa.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
