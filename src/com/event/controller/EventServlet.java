package com.event.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.event.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EventServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action = " + action);

		if ("getOne_For_Display2".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("eve_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/event/select_page2.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String eve_no = null;
				try {
					eve_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/event/select_page2.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EventDAO dao = new EventDAO();
				EventVO eventVO = dao.findByPrimaryKey(eve_no);
				if (eventVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/event/select_page2.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("eventVO", eventVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/event/select_page2.jsp");
				failureView.forward(req, res);
			
			}
		}

		if ("getOne_For_Update2".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD�ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String eve_no = new String(req.getParameter("eve_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				EventService eventSvc = new EventService();
				EventVO eventVO = eventSvc.getOneEvent(eve_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("eventVO", eventVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back_end/event/update_event_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/event/listAllEvent.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update2".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			EventService eventSvc = new EventService();
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String eve_no = new String(req.getParameter("eve_no").trim());
				String evec_no = new String(req.getParameter("evec_no").trim());
				String eve_name = new String(req.getParameter("eve_name").trim());
				
				java.sql.Timestamp eve_start = null;
				
				System.out.println("eve_start = " + req.getParameter("eve_start"));
				try {
					String strDate = req.getParameter("eve_start");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_start = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				java.sql.Timestamp eve_end= null;
				try {
					String strDate = req.getParameter("eve_end");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_end = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_end=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				java.sql.Timestamp eve_time = null;
				try {
					String strDate = req.getParameter("eve_time");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_time = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				String eve_cnt = new String(req.getParameter("eve_cnt").trim());
				
				
				
//				Part eve_pic = req.getPart("eve_pic");
//				InputStream in;
//				ByteArrayOutputStream bao = null ;
//				try {
//					in = eve_pic.getInputStream();
//					bao = new ByteArrayOutputStream();
//					int i;
//					byte [] b = new byte[8182]; 
//					while((i=in.read(b))!=-1) {
//						bao.write(b,0,i);
//						bao.close();	
//					}	
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				} 	
//				byte [] eve_pic2 = bao.toByteArray();
				
				Part part = req.getPart("eve_pic");
				String eve_name1 = part.getName();
		        System.out.println("eve_name1"+eve_name1);
		        InputStream in = part.getInputStream();
		        byte[] eve_pic=new byte[in.available()];
		        if (eve_pic.length != 0){
		        	in.read(eve_pic);
		        	in.close();		
		        }else{
		        	EventVO eve_picVO = eventSvc.getOneEvent(eve_no);
		        	eve_pic = eve_picVO.getEve_pic();
		        };
				
				
				
//				Byte[] eve_pic = new Byte(req.getParameter("eve_pic").trim());
				Integer eve_quota= new Integer(req.getParameter("eve_quota").trim());
				String eve_site= new String(req.getParameter("eve_site").trim());
				Integer eve_regfee = new Integer(req.getParameter("eve_regfee").trim());
				String eve_sts = new String(req.getParameter("eve_sts").trim());
				
				EventVO eventVO = new EventVO();
				eventVO.setEve_no(eve_no);
				eventVO.setEvec_no(evec_no);
				eventVO.setEve_name(eve_name);
				eventVO.setEve_start(eve_start);
				eventVO.setEve_end(eve_end);
				eventVO.setEve_time(eve_time);
				eventVO.setEve_cnt(eve_cnt);
				eventVO.setEve_pic(eve_pic);
				eventVO.setEve_quota(eve_quota);
				eventVO.setEve_site(eve_site);
				eventVO.setEve_regfee(eve_regfee);
				eventVO.setEve_sts(eve_sts);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eventVO",eventVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/event/update_event_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				eventVO = eventSvc.updateEvent(eve_no,evec_no,eve_name,eve_start,eve_end,eve_time,eve_cnt,eve_pic,eve_quota,eve_site,eve_regfee,eve_sts);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("eventVO",eventVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back_end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req,res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back_end/event/update_event_input.jsp");
//				failureView.forward(req, res);
				e.printStackTrace();
			}
		}

		


        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				String evec_no = new String(req.getParameter("evec_no").trim());
				String eve_name = new String(req.getParameter("eve_name").trim());
				
				java.sql.Timestamp eve_start = null;
				System.out.println("eve_start = " + req.getParameter("eve_start"));
				try {
					String strDate = req.getParameter("eve_start");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_start = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				java.sql.Timestamp eve_end= null;
				try {
					String strDate = req.getParameter("eve_end");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_end = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_end=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				java.sql.Timestamp eve_time = null;
				try {
					String strDate = req.getParameter("eve_time");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(strDate);
					eve_time = new Timestamp(date.getTime());
				} catch (IllegalArgumentException e) {
					eve_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				String eve_cnt = new String(req.getParameter("eve_cnt").trim());

				Part eve_pic = req.getPart("eve_pic");
				InputStream in;
				ByteArrayOutputStream bao = null ;
				try {
					in = eve_pic.getInputStream();
					bao = new ByteArrayOutputStream();
					int i;
					byte [] b = new byte[8182]; 
					while((i=in.read(b))!=-1) {
						bao.write(b,0,i);
						bao.close();	
					}	
				} catch (IOException e1) {
					e1.printStackTrace();
				} 	
				
				byte [] eve_pic1 = bao.toByteArray();
				
				
				Integer eve_quota= new Integer(req.getParameter("eve_quota").trim());
				String eve_site= new String(req.getParameter("eve_site").trim());
				Integer eve_regfee = new Integer(req.getParameter("eve_regfee").trim());
				String eve_sts= new String(req.getParameter("eve_sts").trim());
				

				EventVO eventVO = new EventVO();
				
				eventVO.setEvec_no(evec_no);
				eventVO.setEve_name(eve_name);
				eventVO.setEve_start(eve_start);
				eventVO.setEve_end(eve_end);
				eventVO.setEve_time(eve_time);
				eventVO.setEve_cnt(eve_cnt);
				eventVO.setEve_pic(eve_pic1);
				eventVO.setEve_quota(eve_quota);
				eventVO.setEve_site(eve_site);
				eventVO.setEve_regfee(eve_regfee);
				eventVO.setEve_sts(eve_sts);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("eventVO", eventVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/event/addevent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				EventService eventSvc = new EventService();
				eventVO = eventSvc.addEvent(evec_no,eve_name,eve_start,eve_end,eve_time,eve_cnt,eve_pic1, eve_quota,eve_site,eve_regfee,eve_sts);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back_end/event/listAllEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/event/addEvent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("onToOff".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String eve_no = new String(req.getParameter("eve_no"));
				
				/***************************2.�}�l��s���***************************************/
				EventService eventSvc = new EventService();	
							
				
				eventSvc.updateEventSts("�U�[",eve_no);
				
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/event/listAllEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("��s��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/event/listAllEvent.jsp");
				failureView.forward(req, res);
			}
		}
		if ("offToOn".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String eve_no = new String(req.getParameter("eve_no"));
				
				/***************************2.�}�l��s���***************************************/
				EventService eventSvc = new EventService();					
			
				
				eventSvc.updateEventSts("�W�[",eve_no);
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back_end/event/listAllEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("��s��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/event/listAllEvent.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
}
