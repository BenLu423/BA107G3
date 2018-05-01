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
			/***************************�}�l�d�߸�� ****************************************/
			EvemDAO dao = new EvemDAO();
			List<EvemVO> list = dao.getAll();

			/***************************�d�ߧ���,�ǳ����(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // ��Ʈw���X��list����,�s�Jsession
			// Send the Success view
			String url = "/back_end/evem/listAllEvem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}


//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("evemes_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J�d���s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				String evemes_no = null;
//				try {
//					evemes_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("�d���s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				EvemDAO dao = new EvemDAO();
//				EvemVO evemVO = dao.findByPrimaryKey(evemes_no);
//				if (evemVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/evem/select_page.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("evemVO", evemVO); // ��Ʈw���X��empVO����,�s�Jreq
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page1.jsp���ШD
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				String str = req.getParameter("qa_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("�п�J���u�s��");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				String qa_no = null;
//				try {
//					qa_no = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("���u�s���榡�����T");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************2.�}�l�d�߸��*****************************************/
//				QaService qaSvc = new QaService();
//				QaVO qaVO = qaSvc.getOneQa(qa_no);
//				if (qaVO == null) {
//					errorMsgs.add("�d�L���");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//					failureView.forward(req, res);
//					return;//�{�����_
//				}
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
//				req.setAttribute("qaVO", qaVO); // ��Ʈw���X��qaVO����,�s�Jreq
//				String url = "/back_end/qa/listOneQa.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneQa.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/qa/select_page1.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllQa.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String evemes_no = new String(req.getParameter("evemes_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				EvemService evemSvc = new EvemService();
				EvemVO evemVO = evemSvc.getOneEvem(evemes_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("evemVO", evemVO);         // ��Ʈw���X��qaVO����,�s�Jreq
				String url = "/back_end/evem/update_evem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_qa_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			EvemService evemSvc = new EvemService();
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String evemes_no = new String(req.getParameter("evemes_no").trim());
//				String eve_no = new String(req.getParameter("eve_no").trim());
//				String mem_no = new String(req.getParameter("mem_no").trim());
//				System.out.println("inter first");
				EvemVO evemVO = evemSvc.getOneEvem(evemes_no);
				System.out.println("�d�����e:"+evemVO.getEvemes_cnt());
//				java.sql.Timestamp evemes_time = null;
//				try {
//					evemes_time = java.sql.Timestamp.valueOf(req.getParameter("evemes_time").trim());
//					
//				} catch (IllegalArgumentException e) {
//					evemes_time=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
//				}
				
//				String evemes_cnt = new String(req.getParameter("evemes_cnt").trim());
				
//				java.sql.Timestamp evemes_restime = null;
//				
//				try {
//					
//					evemes_restime = java.sql.Timestamp.valueOf(req.getParameter("evemes_restime").trim());
//				} catch (IllegalArgumentException e) {
//					evemes_restime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
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
					req.setAttribute("evemVO",evemVO); // �t����J�榡���~��qaVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/evem/update_evem_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				evemSvc.updateEvem(evemVO);
				System.out.println("inter update");
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("evemVO",evemVO); // ��Ʈwupdate���\��,���T����qaVO����,�s�Jreq
				String url = "/back_end/evem/listOneEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/update_evem_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert3".equals(action)) { // �Ӧ�addQa.jsp���ШD  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
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
//					errorMsgs.add("�п�J���!");
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
//					errorMsgs.add("�п�J���!");
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
//					req.setAttribute("evemVO", evemVO); // �t����J�榡���~��qaVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front_end/evem/listAllEvem.jsp");
//					failureView.forward(req, res);
//					return;
//				}
				System.out.println("inter");
				/***************************2.�}�l�s�W���***************************************/
				EvemService evemSvc = new EvemService();
				evemSvc.addEvem(evemVO);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/evem/listAllEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllQa.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllQa.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String evemes_no = new String(req.getParameter("evemes_no"));
				
				/***************************2.�}�l�R�����***************************************/
				EvemService evemSvc = new EvemService();
				evemSvc.deleteEvem(evemes_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/evem/listAllEvem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/evem/listAllEvem.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
