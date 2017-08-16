package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.user.model.UserService;
import com.user.model.UserVO;

@WebServlet("/front-end/UserLoginHandler")
public class UserLoginHandler extends HttpServlet {
     
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    	req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
    	
    	String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        System.out.println(username);
        System.out.println(password);
        
        String message = doLogin(username, password);
        System.out.println(message);
        
        if (message.equals("SUCCESS")){
        	
          HttpSession session = req.getSession();
          //把userVO放在Session裏
          UserService userSVC=new UserService();
          UserVO userVO=userSVC.getOneAccountUser(username);
          session.setAttribute("userVO", userVO);
        	
          try {
           	   
           	 if (userVO.getUser_type()==2){
                 StoreService storeSVC=new StoreService();
           	     StoreVO storeVO=storeSVC.getOneStoreByUsed_Id( userVO.getUser_id() );
           	     session.setAttribute("storeVO", storeVO);
           	     
           	     System.out.println("pass LoginServlet.java line.50");
           	     message = "SUCCESS_STORE";
             	 res.getWriter().write(message);

//           	     String url="/front-end/store/";
//           		 RequestDispatcher successView = req.getRequestDispatcher(url);
//       			 successView.forward(req, res);         
       			 return;
           		 
           	 } else if (userVO.getUser_type()==3) {
//           		 String url="/front-end/restaurant/";
//           		 RequestDispatcher successView = req.getRequestDispatcher(url);
//       			 successView.forward(req, res);         

           	     System.out.println("pass LoginServlet.java line.64");
           	     message = "SUCCESS_REST";
             	 res.getWriter().write(message);
       			        			 
       			 return;
           		 
           	 } else {
           		res.getWriter().write(message);

           	 }
           	 
          }catch (Exception ignored) { }
                
        } else {
        	res.getWriter().write(message);
        }
        
    }
	
    public String doLogin(String username, String password){

      String message = null;

  	  UserService userSVC=new UserService();
  	  UserVO userVO=userSVC.getOneAccountUser(username);
  	  
  	  if (userVO==null){
  		message = "FAILURE";
  	  } else if ( ! password.equals( String.valueOf(userVO.getUser_passwd()) ) ) {
  		message = "FAILURE";
  	  } else if (userVO.getUser_status()==0) {
  		message = "ACCOUNT SUSPENDED";
  	  } else {
  		message = "SUCCESS";  		
  	  }
        
      return message;
    
    }    
    
}
