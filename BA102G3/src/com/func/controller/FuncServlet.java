package com.func.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.func.model.*;

public class FuncServlet extends HttpServlet {

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
				String str = req.getParameter("func_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入功能編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/func/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer func_id = null;
				try {
					func_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("功能編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/func/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				FuncService funcSvc = new FuncService();
				FuncVO funcVO = funcSvc.getOneFunc(func_id);
				if (funcVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/func/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("funcVO", funcVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/func/listOneFunc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/func/select_page.jsp");
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
				Integer func_id = new Integer(req.getParameter("func_id"));
				
				/***************************2.開始查詢資料****************************************/
				FuncService funcSvc = new FuncService();
				FuncVO funcVO = funcSvc.getOneFunc(func_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("funcVO", funcVO);         // 資料庫取出的empVO物件,存入req
				
				System.out.println("pass: getOne_For_Update");
				
				String url = "/back-end/func/adminFuncUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/func/adminFuncListAll.jsp");
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
					
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer func_id = new Integer(req.getParameter("func_id"));
				
				String func_name = req.getParameter("func_name").trim();
				if ( func_name.isEmpty()) {
					errorMsgs.add("請填入功能名稱!");					
				}
												
				String func_path = req.getParameter("func_path").trim();
				if ( func_path.isEmpty()) {
					errorMsgs.add("請填入功能路徑!");										
				}
				
				Integer func_status = new Integer(req.getParameter("func_status"));
																
				FuncVO funcVO = new FuncVO();
				funcVO.setFunc_name(func_name);
				funcVO.setFunc_path(func_path);
				funcVO.setFunc_status(func_status);
							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funcVO", funcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/func/adminFuncUpdate.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				FuncService funcSvc = new FuncService();
				funcVO = funcSvc.updateFunc(func_id, func_name, func_path, func_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("funcVO", funcVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/func/adminFuncListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/func/adminFuncUpdate.jsp");
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

				String func_name = req.getParameter("func_name").trim();
				if ( func_name.isEmpty()) {
					errorMsgs.add("請輸入功能名稱");					
				}
																
				String func_path = req.getParameter("func_path").trim();
				if ( func_path.isEmpty()) {
					errorMsgs.add("請輸入功能路徑");										
				}
				
				Integer func_status = new Integer(req.getParameter("func_status"));
												
				FuncVO funcVO = new FuncVO();
				funcVO.setFunc_name(func_name);
				funcVO.setFunc_path(func_path);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("funcVO", funcVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/func/adminFuncAdd.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************2.開始新增資料***************************************/
				FuncService funcSvc = new FuncService();

				funcVO = funcSvc.addFunc(func_name, func_path, func_status);
																								
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/func/adminFuncListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/func/adminFuncAdd.jsp");
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
				Integer func_id = new Integer(req.getParameter("func_id"));
				
				/***************************2.開始刪除資料***************************************/
				FuncService funcSvc = new FuncService();
				funcSvc.deleteFunc(func_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/func/adminFuncListAll.jsp");
				failureView.forward(req, res);
			}
		}
	}

// 讀出檔名
//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
////		System.out.println("header=" + header); // 輸出測試
//		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
////		System.out.println("filename=" + filename); // 輸出測試
//		if (filename.length() == 0) {
////			return null;
//		}
//		return filename;
//	}	

}
