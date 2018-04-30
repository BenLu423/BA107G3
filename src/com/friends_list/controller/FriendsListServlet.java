package com.friends_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.friends_list.model.FriendsListService;
import com.friends_list.model.FriendsService;



public class FriendsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("application/json;charset=UTF-8");
		JSONObject isFri = null;
		String action = req.getParameter("action");
		
		PrintWriter out = res.getWriter();
		if("getaddfri".equals(action)){
			String other = req.getParameter("other");
			String self = req.getParameter("self");
			String ismemfri = req.getParameter("ismemfri");
			System.out.println(ismemfri);
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			try{
			System.out.println(self);
			System.out.println(other);
			FriendsService fs = new FriendsService();

			if(self == null || self.trim().length() == 0){
				errorMsgs.put("self", "�[�J����");
			}else if(other == null || other.trim().length() == 0){
				errorMsgs.put("other", "�[�J����");
			}
				
			if("�O".equals(ismemfri) || "�ݼf��".equals(ismemfri) || "�Q�f��".equals(ismemfri)){
				errorMsgs.put("havebeenjoin", "�w�[�J�n��");
				isFri = new JSONObject(errorMsgs);
				System.out.println(isFri.toString());
				
			}
			if(self.equals(other)){
				errorMsgs.put("havebeenjoin", "���~");
				isFri = new JSONObject(errorMsgs);
			}
			if(!(errorMsgs.isEmpty())){
				out.print(isFri);
				return;
			}
			
			System.out.println(ismemfri);
			System.out.println("----------------------------------------------");
					
				fs.webAddFri(self, other);
				errorMsgs.put("havebeenjoin", "�[�J�n��");
				isFri = new JSONObject(errorMsgs);
				out.print(isFri);
				
			System.out.println("OK");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		/*�q���[�J*/
		if("fri".equals(action)){
			try{
			String memGet = req.getParameter("memGet_no");
			String memSend = req.getParameter("memSend_no");
			System.out.println(memSend);
			System.out.println(memGet);
			System.out.println("enter");
			
			FriendsService fs = new FriendsService();
			fs.webUpdateFri("�O", memGet, memSend);
			
			return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		/*�q���ڵ�*/
		if("dfri".equals(action)){
			try{
				String memGet = req.getParameter("memGet_no");
				String memSend = req.getParameter("memSend_no");
				System.out.println("�R��");
				System.out.println(memSend);
				System.out.println(memGet);
				FriendsService fs = new FriendsService();
				fs.webDelFri(memGet, memSend);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		/**/
		System.out.println(action);
		if("adatabasenotice".equals(action)){
			String memGet = req.getParameter("memGet_no");
			String memSend = req.getParameter("memSend_no");
			String statements = "�O";
		
			System.out.println(memSend);
			System.out.println(memGet);
			FriendsService fs = new FriendsService();
			fs.webUpdateFri(statements, memGet, memSend);
			
			return;
		}
		
		/**/
		if("ddatabasenotice".equals(action)){
			String memGet = req.getParameter("memGet_no");
			String memSend = req.getParameter("memSend_no");
			System.out.println(memSend);
			System.out.println(memGet);
			FriendsService fs = new FriendsService();
			fs.webDelFri(memGet, memSend);
			return;
			
		}
		/*�n�ͦC��[�J*/
		res.setCharacterEncoding("Big5");
			if("finaladdfri".equals(action)){
				try{
					String memself = req.getParameter("memself");
					String memother = req.getParameter("memother");
					String statements = "�O";
					FriendsService fs = new FriendsService();
					fs.webUpdateFri(statements, memself, memother);
					res.sendRedirect(req.getContextPath()+"/front_end/member/check_add_friends_list.jsp");
					return;
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			
			if("delfrilistwait".equals(action)){
				try{
					String self = req.getParameter("memself");
					String other = req.getParameter("memother");
					System.out.println(self);
					System.out.println(other);
					FriendsService fs = new FriendsService();
					fs.webDelFri(self, other);
					res.sendRedirect(req.getContextPath()+"/front_end/member/check_add_friends_list.jsp");
					return;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
			
			System.out.println(action);
		if("delfrilist".equals(action)){
			try{
				String self = req.getParameter("memself");
				String other = req.getParameter("memother");
				System.out.println(self);
				System.out.println(other);
				FriendsService fs = new FriendsService();
				fs.webDelFri(self, other);
				res.sendRedirect(req.getContextPath()+"/front_end/member/member_manage_friendslist.jsp");
				return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}



