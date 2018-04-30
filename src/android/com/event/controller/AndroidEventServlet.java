package android.com.event.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.event.model.EventService;
import android.com.event.model.EventVO;
import android.com.main.ImageUtil;
import android.com.member.model.MemberService;
import android.com.member.model.MemberVO;

@WebServlet("/AndroidEventServlet")
public class AndroidEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		EventService eSvc = new EventService();
		String outStr = "";
		String action = req.getParameter("action");
		System.out.println(action);
		List<EventVO> allEve;
		
		if ("getAll".equals(action)) {
			allEve = eSvc.getAll();
			for(EventVO eventVO : allEve){
				eventVO.setEve_pic(ImageUtil.shrink(eventVO.getEve_pic(), 150));
			}
			outStr = gson.toJson(allEve);
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
