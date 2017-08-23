package com.user.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.user.model.*;
import com.rest.model.*;
import com.repi.model.*;
import mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UserRestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
				
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("user_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/user/adminUserQuery.jsp");
//					.getRequestDispatcher("/back-end/user/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer user_id = null;
				try {
					user_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/user/adminUserQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				UserService userSvc = new UserService();
				UserVO userVO = userSvc.getOneUser(user_id);
				if (userVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/user/adminUserQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("userVO", userVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/user/adminUserListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/user/adminUserQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer user_id = new Integer(req.getParameter("user_id"));
				
				/***************************2.開始查詢資料****************************************/
				UserService userSvc = new UserService();
				UserVO userVO = userSvc.getOneUser(user_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("userVO", userVO);         // 資料庫取出的empVO物件,存入req
				
				String url = "/back-end/user/adminUserUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/user/adminUserListAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer user_id = new Integer(req.getParameter("user_id"));
				
				
				String user_account = req.getParameter("user_account").trim();
				if ( user_account.isEmpty()) {
					errorMsgs.add("請填入帳號!");					
				}
												
				String user_passwd = req.getParameter("user_passwd").trim();
				
				Integer user_type = new Integer(req.getParameter("user_type"));
				String user_lastname = req.getParameter("user_lastname").trim();
				if ( user_account.isEmpty()) {
					errorMsgs.add("請填入姓!");										
				}
				
				String user_firstname = req.getParameter("user_firstname").trim();
				if ( user_firstname.isEmpty()) {
					errorMsgs.add("請填入名!");										
				}
				
				String user_phone = req.getParameter("user_phone").trim();
				String user_mobile = req.getParameter("user_mobile").trim();
				String user_address = req.getParameter("user_address").trim();
				
				String user_email = req.getParameter("user_email").trim();
				if ( user_email.isEmpty()) {
					errorMsgs.add("請填入Email!");										
				}
				
				java.sql.Date user_joindate = java.sql.Date.valueOf(req.getParameter("user_joindate").trim());
				
				UserService userSvc = new UserService();
				UserVO current_userVO = userSvc.getOneUser(user_id);
				Integer current_user_status=current_userVO.getUser_status();				
				
				Integer user_status = new Integer(req.getParameter("user_status"));
								
				UserVO userVO = new UserVO();
				userVO.setUser_account(user_account);
				userVO.setUser_passwd(user_passwd);
				userVO.setUser_lastname(user_lastname);
				userVO.setUser_firstname(user_firstname);
				userVO.setUser_phone(user_phone);
				userVO.setUser_mobile(user_mobile);
				userVO.setUser_address(user_address);
				userVO.setUser_email(user_email);
				userVO.setUser_joindate(user_joindate);
				userVO.setUser_status(user_status);
				
				byte[] user_img=null;
				String user_imgfmt=null;
				
				int updateImg=0;
				
				Part part=req.getPart("upfile1");				
				String filename = getFileNameFromPart(part).trim();								
				if ( filename.length() != 0 ){

					updateImg=1;
					
					InputStream in = part.getInputStream();
					user_img = new byte[in.available()];
					in.read(user_img);
					in.close();
					
					String temp[] = filename.split("[.]");				
					if(temp.length>1){
						user_imgfmt = temp[temp.length-1];
					} else {
						user_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}
					
					userVO.setUser_img(user_img);
					userVO.setUser_imgfmt(user_imgfmt);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("userVO", userVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/user/adminUserUpdate.jsp");
//					.getRequestDispatcher("/back-end/user/update_user_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
//				UserService userSvc = new UserService();
				
				userVO = userSvc.updateUser(user_id, user_account, user_passwd, user_type, user_lastname,
						user_firstname, user_phone, user_mobile, user_address, user_email, 
						user_joindate, user_status, user_img, user_imgfmt, updateImg);
				
				if (current_user_status!=1 && user_status==1){
					String[] args={user_account,user_passwd,user_lastname,user_firstname,user_email}; 
					MailService.main(args);
				}				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("userVO", userVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/user/adminUserListOne.jsp";
//				String url = "/back-end/user/listOneUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/user/adminUserUpdate.jsp");
//				.getRequestDispatcher("/back-end/user/update_user_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 新增  
        	
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String user_account = req.getParameter("user_account").trim();
				if ( user_account.isEmpty()) {
					errorMsgs.add("請輸入帳號");					
				}
												
				int min = 100000;
				int max = 999999;				
				Random rand = new Random(); 
				int randomPasswd = rand.nextInt(max-min)+min; 
				String user_passwd=Integer.toString(randomPasswd);
				System.out.println("UserRestServlet.java line.275 New User Password: "+user_passwd);
				
				Integer user_type = new Integer(req.getParameter("user_type"));
				
				String user_lastname = req.getParameter("user_lastname").trim();
				if ( user_account.isEmpty()) {
					errorMsgs.add("請輸入姓");										
				}
				
				String user_firstname = req.getParameter("user_firstname").trim();
				if ( user_firstname.isEmpty()) {
					errorMsgs.add("請輸入名");										
				}
				
				String user_phone = req.getParameter("user_phone").trim();
				String user_mobile = req.getParameter("user_mobile").trim();
				String user_address = req.getParameter("user_address").trim();
				
				String user_email = req.getParameter("user_email").trim();
				if ( user_email.isEmpty()) {
					errorMsgs.add("請輸入Email");										
				}
				
				java.sql.Date user_joindate=new java.sql.Date(System.currentTimeMillis());
				
				Integer user_status = new Integer(req.getParameter("user_status"));
				
				byte[] user_img =null;
				String user_imgfmt=null;
				Part part=req.getPart("upfile1");				
				String filename = getFileNameFromPart(part).trim();
								
				if ( filename.length() != 0 ){
					
					InputStream in = part.getInputStream();
					user_img = new byte[in.available()];
					in.read(user_img);
					in.close();
					
					String temp[] = filename.split("[.]");				
					if(temp.length>1){
						user_imgfmt = temp[temp.length-1];
					} else {
						user_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}					
				}
								
				UserVO userVO = new UserVO();
				userVO.setUser_account(user_account);
				userVO.setUser_passwd(user_passwd);
				userVO.setUser_lastname(user_lastname);
				userVO.setUser_firstname(user_firstname);
				userVO.setUser_phone(user_phone);
				userVO.setUser_mobile(user_mobile);
				userVO.setUser_address(user_address);
				userVO.setUser_email(user_email);
				userVO.setUser_joindate(user_joindate);
				userVO.setUser_status(user_status);
				userVO.setUser_img(user_img);
				userVO.setUser_imgfmt(user_imgfmt);
				
				System.out.println("pass user");
				
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("userVO", userVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/store/adminStoreAdd.jsp");
////							.getRequestDispatcher("/back-end/user/addUser.jsp");
//					failureView.forward(req, res);
//					return;
//				}
								
		//===========for Restaurant====================================================
			
				String rest_name = req.getParameter("rest_name").trim();
				String rest_phone = req.getParameter("rest_phone").trim();
				String rest_detail = req.getParameter("rest_detail").trim();
				String rest_hours = req.getParameter("rest_hours").trim();
				Integer rest_ter = new Integer(req.getParameter("rest_ter").trim());
				Integer rest_floor = new Integer(req.getParameter("rest_floor").trim());
				
				String rest_address= "T"+rest_ter+"-"+rest_floor+"F";
				String rest_trans=null;
				
				Double rest_lon = 0.0;
				try {
					rest_lon = new Double(req.getParameter("rest_lon").trim());
				} catch (NumberFormatException e) {
					rest_lon = 0.0;
//					errorMsgs.add("經緯度請填數字.");
				}
				
				Double rest_lat = 0.0;
				try {
					rest_lat = new Double(req.getParameter("rest_lat").trim());
				} catch (NumberFormatException e) {
					rest_lat = 0.0;
//					errorMsgs.add("經緯度請填數字.");
				}
				
				Integer rest_inout = new Integer(req.getParameter("rest_inout").trim());
				Integer rest_type = new Integer(req.getParameter("rest_type").trim());
								
				Integer rest_count=0;
				Integer rest_score=0;
//				Integer store_count = new Integer(req.getParameter("store_count").trim());
//				Integer store_score = new Integer(req.getParameter("store_score").trim());
				
				RestVO restVO = new RestVO();
				restVO.setRest_name(rest_name);
				restVO.setRest_address(rest_address);
				restVO.setRest_phone(rest_phone);
				restVO.setRest_trans(rest_trans);
				restVO.setRest_detail(rest_detail);
				restVO.setRest_hours(rest_hours);
				restVO.setRest_ter(rest_ter);
				restVO.setRest_floor(rest_floor);
				restVO.setRest_lon(rest_lon);
				restVO.setRest_lat(rest_lat);
				restVO.setRest_inout(rest_inout);
				restVO.setRest_inout(rest_type);
				restVO.setRest_count(rest_count);
				restVO.setRest_score(rest_score);
				
//----------------------------------------------
				String repi_name = req.getParameter("repi_name").trim();
				
				System.out.println("pass UserRestServlet.java line.404");
				
				byte[] repi_img =null;
				String repi_imgfmt=null;
				Part part1=req.getPart("upfileRest");				
				String filename1 = getFileNameFromPart(part1).trim();
				
				System.out.println("pass UserRestServlet line 409");
								
				if ( filename1.length() != 0 ){
					
					InputStream in = part1.getInputStream();
					repi_img = new byte[in.available()];
					in.read(repi_img);
					in.close();
					
					System.out.println(filename1);
					
					String temp[] = filename1.split("[.]");				
					if(temp.length>1){
						repi_imgfmt = temp[temp.length-1];
					} else {
						repi_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}					
				}
//----------------------------------------------								

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("userVO", userVO); // 含有輸入格式錯誤的empVO物件,也存入req
					req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
//							.getRequestDispatcher("/back-end/user/addUser.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				System.out.println("UserServlet line 442");
				UserService userSvc = new UserService();
				userVO = userSvc.addRest(user_account, user_passwd, user_type, user_lastname,
				user_firstname, user_phone, user_mobile, user_address, user_email,
				user_joindate, user_status, user_img, user_imgfmt,
				rest_name, rest_address, rest_phone, rest_trans, rest_detail, rest_hours, rest_ter,
				rest_floor,rest_lon,rest_lat,rest_inout,rest_type,rest_count,rest_score, repi_name, repi_img, repi_imgfmt
				);
				System.out.println("UserRestServlet line 450");
				
				if (user_status==1){
					String[] args={user_account,user_passwd,user_lastname,user_firstname,user_email}; 
					MailService.main(args);
				}
				
				req.setAttribute("successMsgs", "successMsgs");
				
//				userVO = userSvc.addUser(user_account, user_passwd, user_type, user_lastname,
//						user_firstname, user_phone, user_mobile, user_address, user_email,
//						user_joindate, user_status, user_img, user_imgfmt);
								
//				StoreService storeSvc = new StoreService();
//				storeVO = storeSvc.addStore(store_name, store_time, store_phone,store_describe,
//						store_ter,store_floor,store_lon,store_lat,store_inout,store_count,store_score);
																								
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/user/listAllUser.jsp";
				
				String url=requestURL;
				
				if (requestURL.equals("/front-end/user/newStoreMember.jsp")){
					url = "/front-end/user/newStoreMember.jsp";
				}
				
				if (requestURL.equals("/back-end/store/adminStoreAdd.jsp")){
					url = "/back-end/store/adminStoreListAll.jsp";
				}
				
				if (requestURL.equals("/back-end/rest/adminRestAdd.jsp")){
					url = "/back-end/rest/adminRestListAll.jsp";
				}				

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				String url = req.getParameter("requestURL");
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
//						.getRequestDispatcher("/back-end/user/addUser.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer user_id = new Integer(req.getParameter("user_id"));
				
				/***************************2.開始刪除資料***************************************/
				UserService userSvc = new UserService();
				userSvc.deleteUser(user_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/rest/adminRestListAll.jsp";
//				String url = "/back-end/user/listAllUser.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rest/adminRestListAll.jsp");
//  				.getRequestDispatcher("/back-end/user/listAllUser.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// 讀出檔名
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 輸出測試
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 輸出測試
		if (filename.length() == 0) {
//			return null;
		}
		return filename;
	}	

}
