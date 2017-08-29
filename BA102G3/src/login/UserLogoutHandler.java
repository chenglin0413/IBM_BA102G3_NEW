package login;

import java.io.*;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.user.model.*;;

@WebServlet("/front-end/UserLogoutHandler")
public class UserLogoutHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
  public void doGet(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
      
    
    HttpSession session = req.getSession();
    session.invalidate();
    System.out.println("logout:line.23");
    //移除store_ids中，登出的商家的store_id
    ServletContext context=getServletContext();
    if(req.getParameter("store_id")!=null){
    	Integer store_id=new Integer(req.getParameter("store_id"));
    	Set<Integer> store_ids=(Set<Integer>) context.getAttribute("store_ids");
    	store_ids.remove(store_id);
    	System.out.println("logout:line.30"+store_id+"已經離線");
    }
    String url="/front-end/index.jsp";
    System.out.println("logout:line.33");
    RequestDispatcher successView = req.getRequestDispatcher(url);
	successView.forward(req, res);         
    return;
      
  }
}