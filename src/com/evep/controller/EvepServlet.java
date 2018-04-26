package com.evep.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;

import javax.servlet.http.*;

import com.event.model.EventService;
import com.event.model.EventVO;
import com.evep.model.*;

public class EvepServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("BIG5");
		String action = req.getParameter("action");
System.out.println(action);
		
		//提供手機端掃描QRCode後進行更新報名狀態
		if(("checkin").equals(action)){
			String message = req.getParameter("message");
			String eve_no = null;
			String mem_no = null;
			String[] strArr = message.split(",");
			for(String str:strArr){
				String[] obj = str.split(":");
				if("eve_no".equals(obj[0]))
					eve_no = obj[1];
				else if ("mem_no".equals(obj[0]))
					mem_no = obj[1];
			}
			EvepService evepSvc = new EvepService();
			evepSvc.update(mem_no, eve_no, "已報到");
		}
	}
}
