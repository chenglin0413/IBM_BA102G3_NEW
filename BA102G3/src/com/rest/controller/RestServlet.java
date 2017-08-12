package com.rest.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.rest.model.*;
import com.user.model.*;

public class RestServlet extends HttpServlet {

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
				String str = req.getParameter("rest_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rest/adminRestQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer rest_id = null;
				try {
					rest_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐廳編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rest/adminRestQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RestService restSvc = new RestService();
				
				RestVO restVO = restSvc.getOneRest(rest_id);
				
				if (restVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rest/adminRestQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restVO", restVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rest/adminRestListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rest/adminRestQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer rest_id = new Integer(req.getParameter("rest_id"));
				
				/***************************2.開始查詢資料****************************************/
				RestService restSvc = new RestService();
				RestVO restVO = restSvc.getOneRest(rest_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("restVO", restVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/rest/adminRestUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rest/adminRestListAll.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			System.out.println("entry update");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rest_id = new Integer(req.getParameter("rest_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				String rest_name = req.getParameter("rest_name").trim();
				String rest_address = req.getParameter("rest_address").trim();
				String rest_phone = req.getParameter("rest_phone").trim();
				String rest_trans = req.getParameter("rest_trans").trim();
				String rest_detail = req.getParameter("rest_detail").trim();
				String rest_hours = req.getParameter("rest_hours").trim();
				Integer rest_ter = new Integer(req.getParameter("rest_ter").trim());
				Integer rest_floor = new Integer(req.getParameter("rest_floor").trim());
				Integer rest_inout = new Integer(req.getParameter("rest_inout").trim());
				Integer rest_type = new Integer(req.getParameter("rest_type").trim());
				Integer rest_count = new Integer(req.getParameter("rest_count").trim());
				Integer rest_score = new Integer(req.getParameter("rest_score").trim());

				Double rest_lon = null;
				try {
					rest_lon = new Double(req.getParameter("rest_lon").trim());
				} catch (NumberFormatException e) {
					rest_lon = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				Double rest_lat = null;
				try {
					rest_lat = new Double(req.getParameter("rest_lat").trim());
				} catch (NumberFormatException e) {
					rest_lat = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				
				Integer user_status = new Integer(req.getParameter("user_status").trim());
				
				RestVO restVO = new RestVO();
				restVO.setRest_id(rest_id);
				restVO.setUser_id(user_id);
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
				restVO.setRest_type(rest_type);
				restVO.setRest_count(rest_count);
				restVO.setRest_score(rest_score);
								
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rest/adminRestUpdate.jsp");
					failureView.forward(req, res);
					
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RestService restSvc = new RestService();
//				restVO = restSvc.updateRest(rest_id, user_id, rest_name, store_time, store_phone,store_describe,store_ter,store_floor,store_lon,store_lat,store_inout,store_count,store_score, user_status);

				restVO = restSvc.updateRest(rest_id, user_id, rest_name, rest_address, rest_phone, rest_trans,
						rest_detail, rest_hours, rest_ter, rest_floor, rest_lon, rest_lat, rest_inout, rest_type,
						rest_count, rest_score, user_status);
				
				req.setAttribute("successMsgs","successMsgs");
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("restVO", restVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/back-end/store/adminStoreListOne.jsp";
//				String url = "/back-end/store/listOneStore.jsp";
				
				String url = requestURL;
				if ( requestURL.equals("/back-end/rest/adminRestUpdate.jsp") ) {
					url = "/back-end/rest/adminRestListAll.jsp";
				} else if (requestURL.equals("/back-end/rest/adminRestListOne.jsp")) {
					url = "/back-end/rest/adminRestListOne.jsp";
				}				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String url = requestURL;
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/				
				
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				String rest_name = req.getParameter("rest_name").trim();
				String rest_address = req.getParameter("rest_address").trim();
				String rest_phone = req.getParameter("rest_phone").trim();
				String rest_trans = req.getParameter("rest_trans").trim();
				String rest_detail = req.getParameter("rest_detail").trim();
				String rest_hours = req.getParameter("rest_hours").trim();
				Integer rest_ter = new Integer(req.getParameter("rest_ter").trim());
				Integer rest_floor = new Integer(req.getParameter("rest_floor").trim());
				Integer rest_inout = new Integer(req.getParameter("rest_inout").trim());
				Integer rest_type = new Integer(req.getParameter("rest_type").trim());
				Integer rest_count = new Integer(req.getParameter("rest_count").trim());
				Integer rest_score = new Integer(req.getParameter("rest_score").trim());
				
				Double rest_lon = null;
				try {
					rest_lon = new Double(req.getParameter("rest_lon").trim());
				} catch (NumberFormatException e) {
					rest_lon = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				Double rest_lat = null;
				try {
					rest_lat = new Double(req.getParameter("rest_lat").trim());
				} catch (NumberFormatException e) {
					rest_lat = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
								
				RestVO restVO = new RestVO();
				restVO.setUser_id(user_id);
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
				restVO.setRest_type(rest_type);
				restVO.setRest_count(rest_count);
				restVO.setRest_score(rest_score);
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("restVO", restVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/rest/adminRestAdd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RestService restSvc = new RestService();
				restVO = restSvc.addRest(rest_name, rest_address, rest_phone,
						rest_trans, rest_detail, rest_hours, rest_ter, rest_floor,
						rest_lon, rest_lat, rest_inout, rest_type, rest_count,
						rest_score);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/rest/adminRestListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rest/adminRestAdd.jsp");
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
				Integer rest_id = new Integer(req.getParameter("rest_id"));
				Integer user_id = new Integer(req.getParameter("user_id"));
				
				/***************************2.開始刪除資料***************************************/
				RestService restSvc = new RestService();
				restSvc.deleteRest(rest_id);

				UserService userSvc = new UserService();
				userSvc.deleteUser(user_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/rest/adminRestListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/rest/adminRestListAll.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
