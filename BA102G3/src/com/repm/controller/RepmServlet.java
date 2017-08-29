package com.repm.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.repm.model.*;

public class RepmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Rest this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("repm_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入促銷編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/rest_interface/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer repm_id = null;
				try {
					repm_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("促銷編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/rest_interface/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RepmService repmSvc = new RepmService();
				RepmVO repmVO = repmSvc.getOneRepm(repm_id);
				if (repmVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/rest_interface/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("repmVO", repmVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/rest_interface/listAllRepm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rest_interface/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Rest this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer repm_id = new Integer(req.getParameter("repm_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				RepmService repmSvc = new RepmService();
				RepmVO repmVO = repmSvc.getOneRepm(repm_id);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("repmVO", repmVO);
				String url = "/front-end/rest_interface/update_repm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rest_interface/listAllRepm.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Rest this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				Integer repm_id = new Integer(req.getParameter("repm_id").trim());
				Integer rest_id = new Integer(req.getParameter("rest_id").trim());
				String repm_name = req.getParameter("repm_name").trim();
				String repm_desc = req.getParameter("repm_desc").trim();
				String repm_content = req.getParameter("repm_content").trim();
				java.sql.Date repm_startdate = null;
				
				System.out.println("aaaaa");

				try {
					repm_startdate = java.sql.Date.valueOf(req.getParameter("repm_startdate").trim());
				} catch (IllegalArgumentException e) {
					repm_startdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date repm_enddate = null;
				try {
					repm_enddate = java.sql.Date.valueOf(req.getParameter("repm_enddate").trim());
				} catch (IllegalArgumentException e) {
					repm_enddate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer repm_status = null;
				try {
					repm_status = new Integer(req.getParameter("repm_status").trim());
				} catch (NumberFormatException e) {
					repm_status = 0;
					errorMsgs.add("請填數字.");
				}

				RepmVO repmVO = new RepmVO();
				repmVO.setRepm_id(repm_id);
				repmVO.setRest_id(rest_id);
				repmVO.setRepm_name(repm_name);
				repmVO.setRepm_desc(repm_desc);
				repmVO.setRepm_content(repm_content);
				repmVO.setRepm_startdate(repm_startdate);
				repmVO.setRepm_enddate(repm_enddate);
				repmVO.setRepm_status(repm_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("repmVO", repmVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rest_interface/update_repm_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RepmService repmSvc = new RepmService();
				repmVO = repmSvc.updateRepm(repm_name, repm_desc, repm_content, repm_startdate, repm_enddate,
						repm_status, repm_id);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("repmVO", repmVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/rest_interface/listMyAllRepm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/rest_interface/update_repm_input.jsp");
				failureView.forward(req, res);
			}
		}
		//
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session = req.getSession(); // 防止重複新增

			Integer rest_id = null;

//			try {

				if (session.getAttribute("key") != null) {

					try {
						rest_id = new Integer(req.getParameter("rest_id").trim());
					} catch (NumberFormatException e) {
						rest_id = 0;
						errorMsgs.add("請填數字.");
					}

					String repm_name = req.getParameter("repm_name").trim();
					String repm_desc = req.getParameter("repm_desc").trim();
					String repm_content = req.getParameter("repm_content").trim();

					java.sql.Date repm_startdate = null;
					try {
						repm_startdate = java.sql.Date.valueOf(req.getParameter("repm_startdate").trim());
					} catch (IllegalArgumentException e) {
						repm_startdate = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					java.sql.Date repm_enddate = null;
					try {
						repm_enddate = java.sql.Date.valueOf(req.getParameter("repm_enddate").trim());
					} catch (IllegalArgumentException e) {
						repm_enddate = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					Integer repm_status = null;
					try {
						repm_status = new Integer(req.getParameter("repm_status").trim());
					} catch (NumberFormatException e) {
						repm_status = 0;
						errorMsgs.add("請填數字.");
					}

					RepmVO repmVO = new RepmVO();
					repmVO.setRest_id(rest_id);
					repmVO.setRepm_name(repm_name);
					repmVO.setRepm_desc(repm_desc);
					repmVO.setRepm_content(repm_content);
					repmVO.setRepm_startdate(repm_startdate);
					repmVO.setRepm_enddate(repm_enddate);
					repmVO.setRepm_status(repm_status);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("repmVO", repmVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/rest_interface/addRepm.jsp");
						failureView.forward(req, res);
						return;
					}

					RepmService repmSvc = new RepmService();
					repmVO = repmSvc.addRepm(rest_id, repm_name, repm_desc, repm_content, repm_startdate, repm_enddate,
							repm_status);

					req.setAttribute("rest_id", rest_id);

					session.removeAttribute("key");
					
				}
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/rest_interface/listMyAllRepm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/rest_interface/addRepm.jsp");
//				failureView.forward(req, res);
//			}
		}
	}
}
