package filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.AdminVO;

public class BackLoginFilter implements Filter{
	private FilterConfig config;

	@Override
	public void destroy() {
		config = null;
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		AdminVO admin = (AdminVO)session.getAttribute("admin");
		if (admin == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back_end/index.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = config;
		
	}

}
