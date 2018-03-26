package com.admin.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.*;
import com.admin_auth.model.AuthService;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		

		/**************** �n�J���start *****************/
		if ("backlogin".equals(action)) {
			// ���b�K
			try {

				AdminService adminSvc = new AdminService();
				List<AdminVO> admins = adminSvc.getAll();
				AdminVO admin;
				Map<String, String> map = new HashMap<String, String>();// �b�K����
				for (AdminVO adminvo : admins) {
					map.put(adminvo.getAdm_acct(), adminvo.getAdm_pwd());
				}
				
				String account = req.getParameter("account");
				String psw = req.getParameter("pwd");

				// �ˬd�O�_����b�K
				if (account == null || (account.trim().length()) == 0 || psw == null || (psw.trim().length()) == 0) {
					req.setAttribute("errorMsgs", "�п�J�b���K�X");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
					failureView.forward(req, res);
					return;
				}
				// �ˬd�O�_�����b��
				if (!map.containsKey(account)) {
					req.setAttribute("errorMsgs", "�L�����u");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
					failureView.forward(req, res);
					return;
				} else {
					
					// �����b�᪺�ܤ��K�X�O�_���T
					if (!psw.equals(map.get(account))) {
						req.setAttribute("errorMsgs", "�K�X���~");
						req.setAttribute("account", account);
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/back_login.jsp");
						failureView.forward(req, res);
						return;
					} else {
						admin = adminSvc.getOneAdmin(account, psw);
						HttpSession session = req.getSession();
						session.setAttribute("admin", admin);
						res.sendRedirect(req.getContextPath() + "/back_end/index.jsp");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**************** �n�J���end *****************/
		/************** �s�W��x���ustart **************/
		
		if("insert_admin".equals(action)){
			AdminService adminSvc = new AdminService();
			List<AdminVO> admins = adminSvc.getAll();
			Map<String, String> map = new HashMap<String, String>();// �b�K����
			for (AdminVO adminvo : admins) {
				map.put(adminvo.getAdm_acct(), adminvo.getAdm_pwd());
			}
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//�����ШD�ѼơA�ˬd�榡
				req.setCharacterEncoding("Big5");
				String adminName = req.getParameter("adminName");
				String adminName2 = new String(adminName.getBytes("ISO-8859-1"),"Big5");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,10}$";
				if(adminName2==null || adminName2.trim().length()==0){
					errorMsgs.add("���u�m�W�G�ФŪť�");
				}else if(!adminName2.trim().matches(nameReg)){
					errorMsgs.add("���u�m�W�G�u��O���B�^�r���M�Ʀr�A�B���ש�2��10����");
				}
				
				String account = req.getParameter("account");
				String reg = "^[a-zA-Z0-9]{2,10}$";
				if(account==null || account.trim().length()==0){
					errorMsgs.add("���u�b���G�ФŪť�");
				}else if(!account.trim().matches(reg)){
					errorMsgs.add("���u�b���G�u��O�^��r���M�Ʀr�A�B���ש�2��10����");
				}else if(map.containsKey(account)){
					errorMsgs.add("���u�b���G�b������");
				}
				
				String pwd = req.getParameter("pwd");
				if(pwd==null || pwd.trim().length()==0){
					errorMsgs.add("���u�K�X�G�ФŪť�");
				}else if(!pwd.trim().matches(reg)){
					errorMsgs.add("���u�K�X�G�u��O�^��r���M�Ʀr�A�B���ש�2��10����");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("�]�w�v���G�Цܤֿ�ܤ@��");
				}
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdm_acct(account);
				adminVO.setAdm_name(adminName2);
				
				//�Ǧ^���~�T���H�Χt����O���~��adminVO����
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adminInsert.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//�}�l�s�W���
				/*1.�s�W���u*/
				adminSvc.addAdmin(account, pwd, adminName2);
				/*2.���o�s���u��ơA�[�J�v��*/
				AdminVO admin = adminSvc.getOneAdmin(account, pwd);
				AuthService authSvc = new AuthService();
				authSvc.addAuths(admin.getAdm_no(), auths);
				/*3.�s�W���\�A�������u����*/
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/adminInsert.jsp");
				failureView.forward(req, res);
			}
		}
		/************** �s�W��x���uend **************/

	}
}
