package com.sysu.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.auth.model.AuthService;
import com.auth.model.AuthVO;
import com.sysu.model.*;

public class SysuServlet extends HttpServlet {

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
				String str = req.getParameter("sysu_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sysu/adminSysuQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer sysu_id = null;
				try {
					sysu_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sysu/adminSysuQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				SysuService sysuSvc = new SysuService();
				SysuVO sysuVO = sysuSvc.getOneSysu(sysu_id);
				if (sysuVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sysu/adminSysuQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sysuVO", sysuVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/sysu/adminSysuListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sysu/adminSysuQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			System.out.println("line 101 "+requestURL);
			
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				
				/***************************2.開始查詢資料****************************************/
				SysuService sysuSvc = new SysuService();
				SysuVO sysuVO = sysuSvc.getOneSysu(sysu_id);
				
				AuthService authSvc = new AuthService();
				List<AuthVO> authVOList = authSvc.getOneAuth(sysu_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("sysuVO", sysuVO);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("authVOList", authVOList); 
				
				System.out.println("pass: getOne_For_Update");
				
				String url = "/back-end/sysu/adminSysuUpdate.jsp";
//				String url = "/back-end/sysu/update_sysu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sysu/adminSysuListAll.jsp");
//				.getRequestDispatcher("/back-end/sysu/listAllSysu.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			System.out.println("entry update");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
					
			System.out.println("==================l.145 "+requestURL);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				
				String sysu_account = req.getParameter("sysu_account").trim();
				if ( sysu_account.isEmpty()) {
					errorMsgs.add("請填入帳號!");					
				}
												
				String sysu_passwd="ba102g3";
				
				Integer sysu_type = new Integer(req.getParameter("sysu_type"));
				
				String sysu_lastname = req.getParameter("sysu_lastname").trim();
				if ( sysu_account.isEmpty()) {
					errorMsgs.add("請填入姓!");										
				}
				
				String sysu_firstname = req.getParameter("sysu_firstname").trim();
				if ( sysu_firstname.isEmpty()) {
					errorMsgs.add("請填入名!");										
				}
				
				String sysu_email = req.getParameter("sysu_email").trim();
				if ( sysu_email.isEmpty()) {
					errorMsgs.add("請填入Email!");										
				}
				
				java.sql.Date sysu_joindate = null;
				try {
					sysu_joindate = java.sql.Date.valueOf(req.getParameter("sysu_joindate").trim());
				} catch (IllegalArgumentException e) {
					sysu_joindate=new java.sql.Date(System.currentTimeMillis());				
					errorMsgs.add("請填入日期!");
				}
				
				Integer sysu_status = new Integer(req.getParameter("sysu_status"));
							    			    
				SysuVO sysuVO = new SysuVO();
				sysuVO.setSysu_account(sysu_account);
				sysuVO.setSysu_passwd(sysu_passwd);
				sysuVO.setSysu_lastname(sysu_lastname);
				sysuVO.setSysu_firstname(sysu_firstname);
				sysuVO.setSysu_email(sysu_email);
				sysuVO.setSysu_joindate(sysu_joindate);
				sysuVO.setSysu_status(sysu_status);
							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sysuVO", sysuVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sysu/adminSysuUpdate.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				SysuService sysuSvc = new SysuService();
				sysuVO = sysuSvc.updateSysu(sysu_id, sysu_account, sysu_passwd, sysu_type, sysu_lastname,
						sysu_firstname, sysu_email,	sysu_joindate, sysu_status);
												
				String sysu_funcs[]=req.getParameterValues("sysu_func");
				System.out.println("====update Auth===================");
				AuthService authSvc = new AuthService();
				AuthVO authVO = new AuthVO();
								
				if(sysu_funcs != null){
					authSvc.deleteId(sysu_id);
					for(int i=0 ; i<sysu_funcs.length ; i++){
						System.out.println("update: "+sysu_funcs[i]);						
						authVO = authSvc.updateAuthById(sysu_id, new Integer(sysu_funcs[i]) );
						System.out.println("update done: "+sysu_funcs[i]);
					}					
				} else {
//					errorMsgs.add("沒有選擇權限:"+e.getMessage());
					System.out.println("no function selected");
				}				
				System.out.println("=================================");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("sysuVO", sysuVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = requestURL;
				if ( requestURL.equals("/back-end/sysu/adminSysuListAll.jsp") ) {
					url = "/back-end/sysu/adminSysuListAll.jsp";
				} else if (requestURL.equals("/back-end/sysu/adminSysuListOne.jsp")) {
					url = "/back-end/sysu/adminSysuListOne.jsp";
				}
								
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sysu/adminSysuUpdate.jsp");
				failureView.forward(req, res);
				
//				String url = requestURL;
//				if ( requestURL.equals("/back-end/sysu/adminSysuListAll.jsp") ) {
//					url = "/back-end/sysu/adminSysuListAll.jsp";
//				} else if (requestURL.equals("/back-end/sysu/adminSysuListOne.jsp")) {
//					url = "/back-end/sysu/adminSysuListOne.jsp";
//				}				
				
			}
		}

        if ("insert".equals(action)) { // 新增  
        	
        	System.out.println("entry insert");
        	
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				String sysu_account = req.getParameter("sysu_account").trim();
				if ( sysu_account.isEmpty()) {
					errorMsgs.add("請輸入帳號");					
				}
												
				String sysu_passwd="ba102g3";
				
				Integer sysu_type = new Integer(req.getParameter("sysu_type"));
				
				String sysu_lastname = req.getParameter("sysu_lastname").trim();
				if ( sysu_account.isEmpty()) {
					errorMsgs.add("請輸入姓");										
				}
				
				String sysu_firstname = req.getParameter("sysu_firstname").trim();
				if ( sysu_firstname.isEmpty()) {
					errorMsgs.add("請輸入名");										
				}
				
				String sysu_email = req.getParameter("sysu_email").trim();
				if ( sysu_email.isEmpty()) {
					errorMsgs.add("請輸入Email");										
				}
				
				System.out.println("pass 1");
				
				java.sql.Date sysu_joindate = null;
//				try {
//					sysu_joindate = java.sql.Date.valueOf(req.getParameter("sysu_joindate").trim());
//				} catch (IllegalArgumentException e) {
					sysu_joindate=new java.sql.Date(System.currentTimeMillis());				
//					errorMsgs.add("請輸入日期");
//				}
				
				System.out.println("pass 2");
				
				Integer sysu_status = new Integer(req.getParameter("sysu_status"));

				String sysu_funcs[]=req.getParameterValues("sysu_func");				
				
				SysuVO sysuVO = new SysuVO();
				sysuVO.setSysu_account(sysu_account);
				sysuVO.setSysu_passwd(sysu_passwd);
				sysuVO.setSysu_type(sysu_type);
				sysuVO.setSysu_lastname(sysu_lastname);
				sysuVO.setSysu_firstname(sysu_firstname);
				sysuVO.setSysu_email(sysu_email);
				sysuVO.setSysu_joindate(sysu_joindate);
				sysuVO.setSysu_status(sysu_status);
				
				System.out.println("pass 266");
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sysuVO", sysuVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sysu/adminSysuAdd.jsp");
//					.getRequestDispatcher("/back-end/sysu/addSysu.jsp");
					failureView.forward(req, res);
					return;
				}
				
				System.out.println("pass 276");
								
				/***************************2.開始新增資料***************************************/
				SysuService sysuSvc = new SysuService();
				
				System.out.println("pass 281");
				
				sysuVO = sysuSvc.addSysu(sysu_account, sysu_passwd, sysu_type, sysu_lastname,
						sysu_firstname, sysu_email, sysu_joindate, sysu_status, sysu_funcs);
				
				System.out.println("pass 286");
				
				
//				String sysu_funcs[]=req.getParameterValues("sysu_func");
//				System.out.println("====insert Auth===================");
//				AuthService authSvc = new AuthService();
//				AuthVO authVO = new AuthVO();
//				if(sysu_funcs != null){
//					for(int i=0 ; i<sysu_funcs.length ; i++){
//						System.out.println(sysu_funcs[i]);						
//						authVO = authSvc.updateAuthById(sysu_id, new Integer(sysu_funcs[i]) );
//					}					
//				} else {
//					System.out.println("no function selected");
//				}				
//				System.out.println("=================================");				

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/sysu/adminSysuListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sysu/adminSysuAdd.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				
			    System.out.println(sysu_id);
				
				/***************************2.開始刪除資料***************************************/
				SysuService sysuSvc = new SysuService();
				sysuSvc.deleteSysu(sysu_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/sysu/adminSysuListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/sysu/adminSysuListAll.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
