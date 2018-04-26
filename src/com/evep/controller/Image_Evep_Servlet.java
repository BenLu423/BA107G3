package com.evep.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.model.EventService;
import com.evep.model.EvepService;

public class Image_Evep_Servlet extends HttpServlet{
	

public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	res.setContentType("image/gif");
	ServletOutputStream out = res.getOutputStream();
	String mem_no=req.getParameter("mem_no");
	//System.out.println(no);
	
		String eve_no =req.getParameter("eve_no");
		EvepService evep_sts =new EvepService();
		byte[] result=evep_sts.getEVEP_Pic(mem_no, eve_no);
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
	

