package com.flsc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.flsc.model.FlscService;
import com.flsc.model.FlscVO;

public class FlscServlet extends HttpServlet {

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
				String flsc_flno = req.getParameter("flsc_flno");
				if (flsc_flno == null || (flsc_flno.trim()).length() == 0) {
					errorMsgs.add("請輸入班次編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/schedule/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				FlscService flscSvc = new FlscService();
				List<FlscVO> flscVO = flscSvc.findByFlno(flsc_flno);
				if (flscVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/schedule/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("flscVO", flscVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/schedule/listOneflsc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/schedule/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display_inlistAllFlsc".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String flsc_airlinecode = req.getParameter("flsc_airlinecode");
				if (flsc_airlinecode == null || (flsc_airlinecode.trim().length() == 0)) {
					errorMsgs.add("請輸入航空代碼");
				}
				String flsc_flno = req.getParameter("flsc_flno");
				if (flsc_flno == null || (flsc_flno.trim()).length() == 0) {
					errorMsgs.add("請輸入班次編號");
				}

				String flsc_sdate = req.getParameter("flsc_sdate");
				if (flsc_sdate == null || (flsc_airlinecode.trim().length() == 0)) {
					errorMsgs.add("請輸入表定時間");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_interface/listAllFlsc.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/

				// System.out.println(flsc_airlinecode);
				// System.out.println(flsc_flno);
				// System.out.println(flsc_sdate);

				String flsc_date = flsc_sdate.replace("-", "/");
				FlscService flscScv = new FlscService();
				FlscVO flscQuery = flscScv.flscSubQuery(flsc_airlinecode, flsc_flno, flsc_date);

				// System.out.println(flscQuery.getFlsc_airlinecode());
				// System.out.println(flscQuery.getFlsc_flno());
				// System.out.println(flscQuery.getFlsc_sdate());

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/

				req.setAttribute("flscQuery", flscQuery);
				String url = "/front-end/member_interface/listOneflsc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_interface/listAllFlsc.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
