package android.com.giftbox.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import android.com.giftbox.model.GiftboxService;
import android.com.giftbox.model.GiftboxVO;

public class GiftboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String outStr = "";
		GiftboxService gbSvc = new GiftboxService();
		List<GiftboxVO> list = new ArrayList<>();
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getMemGift".equals(action)) {
			String mem_no = req.getParameter("mem_no");
			list = gbSvc.getByMemGift(mem_no);
			outStr = gson.toJson(list);
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
