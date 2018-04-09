package com.member.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;



public class MemberGetPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("imge/png");
		ServletOutputStream out = res.getOutputStream();
		String mem_no = req.getParameter("mem_no");
		try{
				MemberService ms = new MemberService();
				MemberVO memvo = new MemberVO();
				memvo = ms.getOneMem(mem_no);
				byte[] pic = memvo.getMem_photo();
			if(pic != null && pic.length > 0){
				
				ByteArrayInputStream bais = new ByteArrayInputStream(pic);
				byte[] buff = new byte[8 * 1024];
				int count;
				while((count = bais.read(buff)) != -1){
					out.write(buff, 0, count);
				}
				bais.close();
			}else{
				InputStream is = getServletContext().getResourceAsStream("/front_end/res/img/noPic.jpg");
				byte[] buff = new byte[is.available()];
				is.read(buff);
				out.write(buff);
				is.close();
			
			}
		}catch(Exception e){
			InputStream is = getServletContext().getResourceAsStream("/front_end/res/img/noPic.jpg");
			byte[] buff = new byte[is.available()];
			is.read(buff);
			out.write(buff);
			is.close();
			
		}

	}

}
