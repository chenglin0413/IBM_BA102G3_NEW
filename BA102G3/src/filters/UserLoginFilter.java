package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserLoginFilter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// �i���o session�j
		HttpSession session = req.getSession();
		// �i�q session �P�_��user�O�_�n�J�L�j
		Object userVO = session.getAttribute("userVO");
		if (userVO == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/front-end/userLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}