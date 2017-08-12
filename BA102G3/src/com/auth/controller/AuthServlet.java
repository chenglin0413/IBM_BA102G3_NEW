package com.auth.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.auth.model.*;

public class AuthServlet extends HttpServlet {

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
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
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
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AuthService authSvc = new AuthService();
				List<AuthVO> authVOList = authSvc.getOneAuth(sysu_id);
				
				System.out.println("AuthServlet.java line 63=================================");
				System.out.println(authVOList.size());
				System.out.println("=================================");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("authVOList", authVOList); // 資料庫取出的empVO物件,存入req
//				System.out.println("==========================authVOList");
				String url = "/back-end/auth/listOneAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/select_page.jsp");
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
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				
				/***************************2.開始查詢資料****************************************/
				AuthService authSvc = new AuthService();
				List<AuthVO> authVOList = authSvc.getOneAuth(sysu_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("authVO", authVOList);         // 資料庫取出的empVO物件,存入req
				
//				System.out.println("pass: getOne_For_Update");
				
				String url = "/back-end/auth/update_auth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
//			System.out.println("entry update");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
					
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				Integer func_id = new Integer(req.getParameter("func_id"));
																				
				AuthVO authVO = new AuthVO();
				authVO.setSysu_id(sysu_id);
				authVO.setFunc_id(func_id);
							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				AuthService authSvc = new AuthService();
				authVO = authSvc.updateAuth(sysu_id, func_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("authVO", authVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/auth/listOneAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/update_auth_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 新增  
        	
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				Integer func_id = new Integer(req.getParameter("func_id"));
												
				AuthVO authVO = new AuthVO();
				authVO.setSysu_id(sysu_id);
				authVO.setFunc_id(func_id);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authVO", authVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/auth/addAuth.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************2.開始新增資料***************************************/
				AuthService authSvc = new AuthService();

				authVO = authSvc.addAuth(sysu_id, func_id);
																								
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/addAuth.jsp");
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
				Integer sysu_id = new Integer(req.getParameter("sysu_id"));
				Integer func_id = new Integer(req.getParameter("func_id"));
				
				/***************************2.開始刪除資料***************************************/
				AuthService authSvc = new AuthService();
				authSvc.deleteAuth(sysu_id, func_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/auth/listAllAuth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/auth/listAllAuth.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
