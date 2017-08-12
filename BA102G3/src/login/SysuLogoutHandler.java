package login;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.user.model.*;;

@WebServlet("/back-end/SysuLogoutHandler")
public class SysuLogoutHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
         
    HttpSession session = req.getSession();
    session.invalidate();
    
    String url="/sysuLogin.jsp";
    RequestDispatcher successView = req.getRequestDispatcher(url);
	successView.forward(req, res);         
    return;
      
  }
}