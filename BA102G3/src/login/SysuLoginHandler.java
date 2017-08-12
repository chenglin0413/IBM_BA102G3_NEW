package login;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.sysu.model.*;
import com.auth.model.*;

@WebServlet("/SysuLoginHandler")
public class SysuLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  protected boolean allowUser(String account, String password) {
	  	  
	  SysuService sysuSVC=new SysuService();
	  SysuVO sysuVOLogin=sysuSVC.getOneAccountSysu(account);
	  
	  if (sysuVOLogin==null){
		  return false;
	  } else if ( ! password.equals( String.valueOf(sysuVOLogin.getSysu_passwd()) ) ) {
		  return false;
	  } else {
		  return true;
	  }
	  	  
  }
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
//    PrintWriter out = res.getWriter();

    String account = req.getParameter("account");
    String password = req.getParameter("password");

    if (!allowUser(account, password)) {          //�i�b�� , �K�X�L�Įɡj

    	List<String> errorMsgs = new LinkedList<String>();
    	
    	String IP=req.getRemoteAddr();
    	
		int min = 1;
		int max = 5;				
		Random rand = new Random(); 
		int random = rand.nextInt(max-min)+min; 

    	if (random==1) {
    		errorMsgs.add("連登入都會錯,等會老闆約談.");
    	} else if (random==2) {
    		errorMsgs.add("連登入都會錯,你人在嗎!!!");
    	} else if (random==3) {
    		errorMsgs.add("連登入都會錯,本月薪水扣1千元!!!");
    	} else {
    		errorMsgs.add("連登入都會錯,上班給我專心點!!!");    		
    	}
    	
    	errorMsgs.add(IP+"已被監控");
    	
    	req.setAttribute("errorMsgs", errorMsgs);
    	    	
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/sysuLogin.jsp");
//			.getRequestDispatcher("/back-end/user/select_page.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}    	
    	
    }else {                                  
      HttpSession session = req.getSession();
	  SysuService sysuSVC=new SysuService();
	  SysuVO sysuVOLogin=sysuSVC.getOneAccountSysu(account);      
	  session.setAttribute("sysuVOLogin", sysuVOLogin);

	  System.out.println(sysuVOLogin.getSysu_id());
	  
	  AuthService authSvc=new AuthService();
	  List<AuthVO> authVOLogin = authSvc.getOneAuth(sysuVOLogin.getSysu_id());
	  session.setAttribute("authVOLogin", authVOLogin);
	  	  
       try {                                                        
         String location = (String) session.getAttribute("location");
         if (location != null) {
           session.removeAttribute("location");
           res.sendRedirect(location);            
           return;
         }
       }catch (Exception ignored) { }

      res.sendRedirect(req.getContextPath()+"/back-end/index.jsp");  
    }
  }
}