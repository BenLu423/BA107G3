package jdbc.util;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.gift.model.GiftService;

@WebServlet("/DBGifReader4")
public class DBGifReader4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("BIG5");
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		byte[] pic = null;
		try{
		String table = req.getParameter("table").toUpperCase();
			switch(table){
				case "GIFT":
					GiftService giftSvc = new GiftService();
					String gift_no = (String)req.getParameter("gift_no");
					pic = giftSvc.getPic(gift_no);
					break;
			}
			
			ByteArrayInputStream bis = new ByteArrayInputStream(pic);
			byte[] buf = new byte[8 * 1024]; // 4K buffer
			int len;
			while( (len = bis.read(buf)) != -1){
				out.write(buf, 0, len);
			}
			bis.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/front_end/res/img/noPic.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

}