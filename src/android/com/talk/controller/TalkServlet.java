package android.com.talk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.member.model.MemberVO;

import android.com.friends_list.model.FriendsListVO;
import android.com.talk.model.TalkService;
import android.com.talk.model.TalkVO;


public class TalkServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String outStr = "";
		String action = req.getParameter("action");
		if ("getTalk".equals(action)) {
			String self_no = req.getParameter("self_no");
			String fri_no = req.getParameter("fri_no");
			TalkService talkSvc = new TalkService();
			FriendsListVO friends = new FriendsListVO();
			TalkVO talkVO = new TalkVO();
			MemberVO memSelf = new MemberVO();
			memSelf.setMem_no(self_no);
			friends.setMemVO_self(memSelf);
			MemberVO memOther = new MemberVO();
			memOther.setMem_no(fri_no);
			friends.setMemVO_other(memOther);
			TalkVO talk=null;
			
			//雙方之間沒有talkVO時建一個
			try{
				talk = talkSvc.getOneTalk(friends);
				outStr = talk.getTalk_cnt().toString();
				
			}catch(Exception e ){
				TalkVO newTalk = new TalkVO();
				newTalk.setMem_no_send(fri_no);
				newTalk.setMem_no_get(self_no);
				java.util.Date today = new java.util.Date();
				long time = today.getTime();
				Timestamp date = new Timestamp(time);
				newTalk.setTalk_time(date);
				talkSvc.addTalk(newTalk);
				talk = talkSvc.getOneTalk(friends);
				
				outStr = "no message";
			}
		}
		
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		System.out.println(outStr);
		out.print(outStr);
		out.close();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}	

}
