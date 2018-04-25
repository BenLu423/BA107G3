package com.event.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.event.model.EventService;
import com.event.model.EventVO;
import com.evep.model.EvepService;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class EventFrontServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if("joinEvent".equals(action)){
			
		}
		
		
		
		if("getEvent_No".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String eve_no = new String(req.getParameter("eve_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				
				EventService eveSvc = new EventService();
				EventVO eventVO = eveSvc.getOneEvent(eve_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("eventVO", eventVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front_end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/event/select_page2.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		if ("getEvent_apply".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				EventService eveSvc = new EventService();
				String eve_no = req.getParameter("eve_no");
				String mem_no = req.getParameter("member_no");
				EventVO event = eveSvc.getOneEvent(eve_no);
				System.out.println(event.getEve_name());
				req.setAttribute("eventVO", event);
				/***************************2.�}�l�s�W���***************************************/
				
				EvepService evepSvc = new EvepService();
				evepSvc.addEvep(mem_no,eve_no);
				MemberService memSvc = new MemberService();
				MemberVO member = memSvc.getOneMem(mem_no);
				SendApplyMail send = new SendApplyMail(); 
				send.sendMail(member.getMem_mail(),member.getMem_name());
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/front_end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/event/listAllEvent.jsp");
				failureView.forward(req, res);
				e.printStackTrace();

			}
	   }
	}
}
