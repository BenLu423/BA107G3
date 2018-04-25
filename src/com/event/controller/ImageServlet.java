package com.event.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventService;
@WebServlet("/imagesServlet")

public class ImageServlet extends HttpServlet{
	

public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	res.setContentType("image/gif");
	ServletOutputStream out = res.getOutputStream();
	String action =req.getParameter("action");
	String no=req.getParameter("no");
	//System.out.println(no);
	
	if("event".equals(action)){
		String eve_no =req.getParameter("no");
		EventService eve_pic =new EventService();
		byte[] result=eve_pic.select(eve_no);
		try{
			if(result !=null){
				out.write(result);
			}else{
				InputStream in =getServletContext().getResourceAsStream("/NoData/no.png");
				byte[]buf=new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
				}catch (Exception e) {
					//System.out.println(e);
					InputStream in =getServletContext().getResourceAsStream("/NoData/null.png");
					byte[]buf=new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
			}
		}	
	}
}
	

