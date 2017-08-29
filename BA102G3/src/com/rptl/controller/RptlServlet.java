package com.rptl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rptl.model.*;
import com.user.model.UserVO;

public class RptlServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // 處理中文檔名
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if ("insert".equals(action)) { // 來自addRppr.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			PrintWriter out = response.getWriter();  
			
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				UserVO userVO = (UserVO)session.getAttribute("userVO");
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id").trim());
			
	System.out.println("檢舉遊記編號:"+ trvl_id);
	System.out.println("檢舉使用者編號:"+ userVO.getUser_id());
				
				Timestamp rptl_date = null;
				try {
					long date = Long.parseLong(request.getParameter("rptl_date"));
					rptl_date = new java.sql.Timestamp(date);	
	System.out.println("檢舉日期 :"+ rptl_date);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}

				String rptl_tittle = request.getParameter("rptl_tittle");
	System.out.println("檢舉標題 :"+ rptl_tittle);	
				if (rptl_tittle == null || (rptl_tittle.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉標題");
				}
				
				String rptl_content = request.getParameter("rptl_content");
	System.out.println("檢舉內容:"+ rptl_content);	
				if (rptl_content == null || (rptl_content.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/listAllTrvl.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.AJAX轉送資料 ***************************************/
				RptlService rptlSvc = new RptlService();
				rptlSvc.addRptl(trvl_id, userVO.getUser_id(), rptl_date, rptl_tittle, rptl_content);
				String msg="已成功檢舉,感謝您的協助";
				out.print(msg);
				out.close();  

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/listAllTrvl.jsp");
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
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
	}

}
