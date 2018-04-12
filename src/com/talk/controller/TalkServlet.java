package com.talk.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;

import com.friends_list.model.*;
import com.member.model.MemberVO;
import com.talk.model.TalkService;

public class TalkServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		
		/**************取得好友聊天內容******************/
		if("getMessage".equals(action)){
			HttpSession session = req.getSession();
			MemberVO self = (MemberVO)session.getAttribute("memSelf");
			String memGet = req.getParameter("memGet");
			System.out.println(memGet);
			FriendsService friSvc = new FriendsService();
			FriendsListVO friends = friSvc.getOne(self.getMem_no(), memGet);
						
			TalkService talkSvc = new TalkService();
			String talk_cnt = talkSvc.getOneTalk(friends);
			JSONArray jsonarray = null ;
			
			try {
				jsonarray = new JSONArray(talk_cnt);
				System.out.println(jsonarray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			session.setAttribute("talk_cnt", jsonarray );
			
			
		}
		
		
		
	}
	

}
