package android.com.event_participants.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import android.com.event.model.EventVO;
import android.com.event_participants.model.Event_participantsService;
import android.com.event_participants.model.Event_participantsVO;
import android.com.main.ImageUtil;


@WebServlet("/AndroidEvent_participantsServlet")
public class AndroidEvent_participantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Event_participantsService epSvc = new Event_participantsService();
		String outStr = "";
		String action = req.getParameter("action");
		System.out.println(action);
		List<Event_participantsVO> allEve;
		
		if ("getOneEve".equals(action)) {
			String mem_no = req.getParameter("mem_no");
			allEve = epSvc.getOneEve(mem_no);
			for(Event_participantsVO event_participantsVO : allEve){
				event_participantsVO.setEvep_qr(ImageUtil.shrink(event_participantsVO.getEvep_qr(), 200));
			}
			outStr = gson.toJson(allEve);
		}
		
		else if("getOneEvep".equals(action)) {
			String eve_no = req.getParameter("eve_no");
			allEve = epSvc.getOneEvep(eve_no);
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