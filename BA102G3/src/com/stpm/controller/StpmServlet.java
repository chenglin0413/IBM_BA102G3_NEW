package com.stpm.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.stpm.model.StpmService;
import com.stpm.model.StpmVO;
import com.stpm.model.*;

public class StpmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("stpm_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入促銷編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer stpm_id = null;
				try {
					stpm_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("促銷編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StpmService stpmSvc = new StpmService();
				StpmVO stpmVO = stpmSvc.getOneStpm(stpm_id);
				if (stpmVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("stpmVO", stpmVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/store/listOneStpm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getStore_ID".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("store_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入促銷編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer store_id = null;
				try {
					store_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("促銷編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StpmService stpmSvc = new StpmService();
				List<StpmVO> stpmVO = stpmSvc.findByStoreID(store_id);
				if (stpmVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("stpmVO", stpmVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/store/listMyStpm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer stpm_id = new Integer(req.getParameter("stpm_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				StpmService stpmSvc = new StpmService();
				StpmVO stpmVO = stpmSvc.getOneStpm(stpm_id);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("stpmVO", stpmVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/store_interface/update_stpm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/addStpm.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> okMsgs = new LinkedList<String>();
			req.setAttribute("okMsgs", okMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/

				Integer stpm_id = new Integer(req.getParameter("stpm_id").trim());
				String stpm_name = req.getParameter("stpm_name").trim();
				String stpm_desc = req.getParameter("stpm_desc").trim();
				String stpm_content = req.getParameter("stpm_content").trim();

				java.sql.Date stpm_startdate = null;

				try {
					stpm_startdate = java.sql.Date.valueOf(req.getParameter("stpm_startdate").trim());
				} catch (IllegalArgumentException e) {
					stpm_startdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date stpm_enddate = null;
				try {
					stpm_enddate = java.sql.Date.valueOf(req.getParameter("stpm_enddate").trim());
				} catch (IllegalArgumentException e) {
					stpm_enddate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer stpm_status = null;
				try {
					stpm_status = new Integer(req.getParameter("stpm_status").trim());
				} catch (NumberFormatException e) {
					stpm_status = 0;
					errorMsgs.add("請填數字.");
				}

				StpmVO stpmVO = new StpmVO();
				// stpmVO.setStore_id(store_id);
				stpmVO.setStpm_name(stpm_name);
				stpmVO.setStpm_desc(stpm_desc);
				stpmVO.setStpm_content(stpm_content);
				stpmVO.setStpm_startdate(stpm_startdate);
				stpmVO.setStpm_enddate(stpm_enddate);
				stpmVO.setStpm_status(stpm_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("stpmVO", stpmVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/update_stpt_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				StpmService stpmSvc = new StpmService();
				stpmVO = stpmSvc.updateStpm(stpm_name, stpm_desc, stpm_content, stpm_startdate, stpm_enddate,
						stpm_status, stpm_id);
				okMsgs.add("促銷資訊修改成功");

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				if (!okMsgs.isEmpty()) {
					req.setAttribute("stpmVO", stpmVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/front-end/store_interface/update_stpm_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store/update_stpm_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			HttpSession session = req.getSession(); //防止重複新增

			Integer store_id = null;

			try {

				if (session.getAttribute("key") != null) {

					try {
						store_id = new Integer(req.getParameter("store_id"));
					} catch (NumberFormatException e) {
						store_id = 0;
						errorMsgs.add("代號請填數字");
					}

					String stpm_name = req.getParameter("stpm_name").trim();
					String stpm_desc = req.getParameter("stpm_desc").trim();
					String stpm_content = req.getParameter("stpm_content").trim();

					java.sql.Date stpm_startdate = null;
					try {
						stpm_startdate = java.sql.Date.valueOf(req.getParameter("stpm_startdate"));
					} catch (IllegalArgumentException e) {
						stpm_startdate = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					java.sql.Date stpm_enddate = null;
					try {
						stpm_enddate = java.sql.Date.valueOf(req.getParameter("stpm_enddate"));
					} catch (IllegalArgumentException e) {
						stpm_enddate = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入日期!");
					}

					Integer stpm_status = null;
					try {
						stpm_status = new Integer(req.getParameter("stpm_status"));
					} catch (NumberFormatException e) {
						stpm_status = 0;
						errorMsgs.add("狀態請填數字");
					}

					StpmVO stpmVO = new StpmVO();
					stpmVO.setStore_id(store_id);
					stpmVO.setStpm_name(stpm_name);
					stpmVO.setStpm_desc(stpm_desc);
					stpmVO.setStpm_content(stpm_content);
					stpmVO.setStpm_startdate(stpm_startdate);
					stpmVO.setStpm_enddate(stpm_enddate);
					stpmVO.setStpm_status(stpm_status);

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("stpmVO", stpmVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/store_interface/addStpm.jsp");
						failureView.forward(req, res);
						return;
					}

					StpmService stpmSvc = new StpmService();
					stpmVO = stpmSvc.addStpm(store_id, stpm_name, stpm_desc, stpm_content, stpm_startdate, stpm_enddate,
							stpm_status);

					req.setAttribute("store_id", store_id);
					
					session.removeAttribute("key");

				}

				String url = "/front-end/store_interface/listMyAllStpm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addStpm.jsp");
				failureView.forward(req, res);
			}

		}
	}
}
