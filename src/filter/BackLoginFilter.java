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
		// �i���o session�j
		HttpSession session = req.getSession();
		// �i�q session �P�_��user�O�_�n�J�L�j
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
