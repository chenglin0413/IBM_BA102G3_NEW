package com.rptl.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rppr.model.RpprService;
import com.rptl.model.*;

public class RptlServlet1 extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF8"); // 處理中文檔名
		response.setContentType("text/html; charset=UTF8");
		
		String action = request.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addRppr.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id").trim());
				Integer user_id = Integer.parseInt(request.getParameter("user_id").trim());
			System.out.println("遊記編號"+ trvl_id);
			System.out.println("使用者編號"+ user_id);
				
				Timestamp rptl_date = null;
				try {
					long date = Long.parseLong(request.getParameter("rptl_date"));
					rptl_date = new java.sql.Timestamp(date);	
			System.out.println("檢舉日期"+ rptl_date);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}

				String rptl_tittle = request.getParameter("rptl_tittle").trim();
			System.out.println("檢舉標題"+ rptl_tittle);	
				if (rptl_tittle == null || (rptl_tittle.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				
				String rptl_content = request.getParameter("rptl_content").trim();
			System.out.println("檢舉日期"+ rptl_content);	
				if (rptl_content == null || (rptl_content.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}

				RptlVO rptlVO = new RptlVO();
				rptlVO.setRptl_tittle(rptl_tittle);
				rptlVO.setRptl_content(rptl_content);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("rptlVO", rptlVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/report/addRppr.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RptlService rptlSvc = new RptlService();
				rptlVO = rptlSvc.addRptl(trvl_id, user_id, rptl_date, rptl_tittle, rptl_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/report/listAllRppr.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交???????.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/report/addRppr.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("getOne_For_Display".equals(action)) { // 來自listAllRppr.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rptl_id = new Integer(request.getParameter("rptl_id").trim());
				Integer trvl_id = new Integer(request.getParameter("trvl_id").trim());
			System.out.println("檢舉編號"+rptl_id);
			System.out.println("遊記編號"+trvl_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/report/listAllReport.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RptlService rptlSvc = new RptlService();

				RptlVO rptlVO = rptlSvc.getOneRptl(rptl_id);			
				
				if (rptlVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/report/listAllReport.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("rptlVO", rptlVO); // 取出的物件,存入req

				String url = "/back-end/report/listOneRptl.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交
																					// listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rptl_id = new Integer(request.getParameter("rptl_id").trim());
				Integer rptl_status = new Integer(request.getParameter("rptl_status").trim());
			System.out.println("更新的rptl_id" + rptl_id);	
			System.out.println("更新的rptl_status" + rptl_status);	

				/***************************2.開始修改資料*****************************************/
				RptlService rptlSvc = new RptlService();
				rptlSvc.updateRpprStatus(rptl_id, rptl_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/report/listOneRptl.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
	}

}
