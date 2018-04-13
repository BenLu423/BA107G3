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


		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("qa_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String qa_no = null;
				try {
					qa_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
				if (qaVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/qa/listOneQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/select_page1.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("qaVO", qaVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/qa/update_qa_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/listAllQa.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String qa_no = new String(req.getParameter("qa_no").trim());
				
				
				java.sql.Date qa_date = null;
				try {
					qa_date = java.sql.Date.valueOf(req.getParameter("qa_date").trim());
				} catch (IllegalArgumentException e) {
					qa_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("qaVO", qaVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/update_qa_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.updateQa(qa_no,qa_date,qa_title,qa_cnt);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("qaVO", qaVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back_end/qa/listOneQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/update_qa_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String qa_no = req.getParameter("qa_no");
				
				java.sql.Date qa_date = null;
				try {
					qa_date = java.sql.Date.valueOf(req.getParameter("qa_date").trim());
				} catch (IllegalArgumentException e) {
					qa_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("qaVO", qaVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/qa/addQa.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.addQa(qa_no,qa_date,qa_title,qa_cnt);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back_end/qa/listAllQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/addQa.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String qa_no = new String(req.getParameter("qa_no"));
				
				/***************************2.�}�l�R�����***************************************/
				QaService qaSvc = new QaService();
				qaSvc.deleteQa(qa_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/qa/listAllQa.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/qa/listAllQa.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
