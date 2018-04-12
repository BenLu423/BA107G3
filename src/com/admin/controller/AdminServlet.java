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
import com.admin_auth.model.AdminAuthVO;
import com.admin_auth.model.AuthService;
import com.auth_feature.model.AuthFeatureService;
import com.auth_feature.model.AuthFeatureVO;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");

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
				

				String mail = req.getParameter("mail");
				if(mail==null || mail.trim().length()==0){
					errorMsgs.add("���u�H�c�G�ФŪť�");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("�]�w�v���G�Цܤֿ�ܤ@��");
				}
				
				AdminVO adminVO = new AdminVO();
				adminVO.setAdm_acct(account);
				adminVO.setAdm_name(adminName2);
				adminVO.setAdm_mail(mail);
				
				//�Ǧ^���~�T���H�Χt����O���~��adminVO����
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", adminVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/insert_admin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//�}�l�s�W���
				/*1.�üƱK�X*/
				StringBuilder pwd = new StringBuilder() ;
				int t;
				for(int i = 0; i < 5 ;i++){
					t = (int)(Math.random()*9)+1;
					pwd.append(t);
				}
				String pwds = pwd.toString();
				
				/*2.�s�W���u*/
				adminSvc.addAdmin(account, pwds, adminName2,mail);
				/*3.�[�J�v��*/
				AdminVO admin = adminSvc.getOneByAcct(account);
				AuthService authSvc = new AuthService();
				authSvc.addAuth(admin.getAdm_no(), auths);
				
				/*4.�H�e�K�X*/
				SendMail sendMail = new SendMail();
				sendMail.sendMail(mail, pwds, adminName2);
				
				/*5.�s�W���\�A�������u����*/
				res.sendRedirect(req.getContextPath() + "/back_end/admin/all_admin-catchTag.jsp");
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/insert_admin.jsp");
				failureView.forward(req, res);
			}
		}
		/************** �s�W��x���uend **************/
		/************** �R����x���ustart **************/
		if("delete".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			try{
				String adm_no = req.getParameter("adm_no");
				AdminService admSvc = new AdminService();
				AuthService authSvc = new AuthService();
				
				//���R�ӭ��u�v��
				List<AuthFeatureVO> auths = admSvc.getAdminAuths(adm_no);//���o���u�֦����v��
				for(AuthFeatureVO auth : auths){
					authSvc.deleteAuth(adm_no, auth.getAuth_no());
				}
				//�R���ӭ��u
				admSvc.deleteAdmin(adm_no);
				
				//���\�����^all_admin
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add("�R����ƥ���"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		/************** �R����x���uend **************/
		/************** �N��@���u�ǰe�ܭקﭶ��start **************/
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			String requestURL = req.getParameter("requestURL");
			try{
				String adm_no = req.getParameter("adm_no");
				//�H�s�������u����
				AdminService admSvc = new AdminService();
				AdminVO admin = admSvc.getOneAdmin(adm_no);
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/admin/update_admin.jsp");
				successView.forward(req, res);
			}catch(Exception e){
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
			
		}
		/************** �N��@���u�ǰe�ܭקﭶ��end **************/
		/******************* �ק���ustart *********************/
		if("update".equals(action)){

			AdminService adminSvc = new AdminService();
			String requestURL = req.getParameter("requestURL");
			
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
				String adm_no = req.getParameter("adm_no");
				
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
					errorMsgs.add("���u�K�X�G�u��O�^��r���M�Ʀr�A�B���ש�2��10����");
				}
				
				String mail = req.getParameter("mail");
				if(mail==null || mail.trim().length()==0){
					errorMsgs.add("���u�H�c�G�ФŪť�");
				}
				
				String []auths = req.getParameterValues("auth");
				if(auths == null){
					errorMsgs.add("�]�w�v���G�Цܤֿ�ܤ@��");
				}
				
				AdminVO admin = adminSvc.getOneAdmin(adm_no);
				admin.setAdm_name(adminName2);
				admin.setAdm_acct(account);
				admin.setAdm_mail(mail);
				
				//�Ǧ^���~�T���H�Χt����O���~��adminVO����
				if(!errorMsgs.isEmpty()){
					
					req.setAttribute("admin", admin);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/admin/update_admin.jsp");
					failureView.forward(req, res);
					return;
				}
				
				//�}�l�ק���
				
				
				adminSvc.updateAdmin(adm_no, account, mail, adminName2);
				
				AuthService authSvc = new AuthService();
				
				//���N�����v���R��
				List<AuthFeatureVO> adminAuths = adminSvc.getAdminAuths(adm_no);
				for(AuthFeatureVO auth : adminAuths){
					authSvc.deleteAuth(adm_no, auth.getAuth_no());
				}
				
				//�A�N�s���v���e�X�s�W
				authSvc.addAuth(adm_no, auths);
				
				/*3.�s�W���\�A�������u����*/
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		/******************* �ק���uend *********************/
		/******************* �d��@���ustart *********************/
		if("search_admin".equals(action)){
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String url = req.getParameter("requestURL");
			
			try{
				String search_no = req.getParameter("search_no");
				//�ˬd�O�_����J�s��
				if(search_no==null||(search_no.trim()).length()==0){
					errorMsgs.add("�п�J���u�s��");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//�ˬd���L���b��
				AdminService admSvc = new AdminService();
				AdminVO admin = null;
				try{
					admin = admSvc.getOneAdmin(search_no);
				}catch(Exception e){
					errorMsgs.add("�d�L�����u");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;
				}
				
				//�e�X���T���u
				req.setAttribute("admin", admin);
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/admin/one_admin.jsp");
				successView.forward(req, res);
				
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
			
		}
		
		
		/******************* �d��@���uend *********************/
		

	}
}
