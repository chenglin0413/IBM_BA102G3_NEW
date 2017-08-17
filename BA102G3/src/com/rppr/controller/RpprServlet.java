package com.rppr.controller;

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

import org.json.JSONException;
import org.json.JSONObject;

import com.rppr.model.RpprService;
import com.rppr.model.RpprVO;

public class RpprServlet extends HttpServlet {
       
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
			String requestURL = request.getParameter("requestURL"); 
			PrintWriter out=response.getWriter();
			System.out.println("rppr_40");
//			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer prod_id = Integer.parseInt(request.getParameter("prod_id").trim());
				Integer user_id = Integer.parseInt(request.getParameter("user_id").trim());
			System.out.println("產品編號"+ prod_id);
			System.out.println("使用者編號"+ user_id);
				
				Timestamp rppr_date = null;
				try {
					long date = Long.parseLong(request.getParameter("rppr_date"));
					rppr_date = new java.sql.Timestamp(date);	
			System.out.println("檢舉日期"+ rppr_date);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}

				String rppr_tittle = request.getParameter("rppr_tittle").trim();
			System.out.println("檢舉標題"+ rppr_tittle);	
				if (rppr_tittle == null || (rppr_tittle.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				
				String rppr_content = request.getParameter("rppr_content").trim();
			System.out.println("檢舉內容"+ rppr_content);	
				if (rppr_content == null || (rppr_content.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}

				RpprVO rpprVO = new RpprVO();
				rpprVO.setRppr_tittle(rppr_tittle);
				rpprVO.setRppr_content(rppr_content);


				/*************************** 2.開始新增資料 ***************************************/
				RpprService rpprSvc = new RpprService();
				rpprSvc.addRppr(prod_id, user_id, rppr_date, rppr_tittle, rppr_content);
				JSONObject jb=new JSONObject(rpprVO);
				System.out.println(jb);
				out.print(jb);
				out.close();
		}
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				/*************************** 其他可能的錯誤處理 **********************************/
		
		
		if ("getOne_For_Display".equals(action)) { // 來自listAllRppr.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rppr_id = new Integer(request.getParameter("rppr_id").trim());
				Integer prod_id = new Integer(request.getParameter("prod_id").trim());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/report/listAllRppr.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RpprService rpprSvc = new RpprService();

				RpprVO rpprVO = rpprSvc.getOneRppr(rppr_id);			
				
				if (rpprVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/report/listAllRppr.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("rpprVO", rpprVO); // 取出的物件,存入req

				String url = "/back-end/report/listOneRppr.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交
																					// listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/report/listAllRppr.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer rppr_id = new Integer(request.getParameter("rppr_id").trim());
				Integer rppr_status = new Integer(request.getParameter("rppr_status").trim());
			System.out.println("更新的rppr_id" + rppr_id);	
			System.out.println("更新的rppr_status" + rppr_status);	

				/***************************2.開始修改資料*****************************************/
				RpprService rpprSvc = new RpprService();
				rpprSvc.updateRpprStatus(rppr_id, rppr_status);
				
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
		
if ("ADDTOReport".equals(action)) { // 來自addRppr.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer prod_id = Integer.parseInt(request.getParameter("prod_id").trim());
				Integer user_id = Integer.parseInt(request.getParameter("user_id").trim());
			System.out.println("產品編號"+ prod_id);
			System.out.println("使用者編號"+ user_id);
				
				Timestamp rppr_date = null;
				try {
					long date = Long.parseLong(request.getParameter("rppr_date"));
					rppr_date = new java.sql.Timestamp(date);	
			System.out.println("檢舉日期"+ rppr_date);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}

				String rppr_tittle = request.getParameter("rppr_tittle").trim();
			System.out.println("檢舉標題"+ rppr_tittle);	
				if (rppr_tittle == null || (rppr_tittle.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				
				String rppr_content = request.getParameter("rppr_content").trim();
			System.out.println("檢舉日期"+ rppr_content);	
				if (rppr_content == null || (rppr_content.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉內容");
				}

				RpprVO rpprVO = new RpprVO();
				rpprVO.setRppr_tittle(rppr_tittle);
				rpprVO.setRppr_content(rppr_content);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("rpprVO", rpprVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = request.getRequestDispatcher("/back-end/report/addRppr.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				RpprService rpprSvc = new RpprService();
				rpprVO = rpprSvc.addRppr(prod_id, user_id, rppr_date, rppr_tittle, rppr_content);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				request.setAttribute("rpprVO", rpprVO);
				String url = "/front-end/member_interface/listAllProd.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交???????.jsp
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/member_interface/listAllProd.jsp");
				failureView.forward(request, response);
			}
		}
		
		
	}

}
