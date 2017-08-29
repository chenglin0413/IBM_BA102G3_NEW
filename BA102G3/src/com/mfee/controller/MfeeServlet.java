package com.mfee.controller;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.mfee.model.*;
import com.sysu.model.SysuVO;

public class MfeeServlet extends HttpServlet {

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
				String str = req.getParameter("mfee_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入付費編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mfee_id = null;
				try {
					mfee_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("付費編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MfeeService mfeeSvc = new MfeeService();
				MfeeVO mfeeVO = mfeeSvc.getOneMfee(mfee_id);
				if (mfeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mfeeVO", mfeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/mfee/adminMfeeListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
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
				Integer mfee_id = new Integer(req.getParameter("mfee_id"));
				
				/***************************2.開始查詢資料****************************************/
				MfeeService mfeeSvc = new MfeeService();
				MfeeVO mfeeVO = mfeeSvc.getOneMfee(mfee_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mfeeVO", mfeeVO);         // 資料庫取出的empVO物件,存入req 
				
				System.out.println("pass: getOne_For_Update");
				
				String url = "/back-end/mfee/adminMfeeUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeListAll.jsp");
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
				
				Integer mfee_id = new Integer(req.getParameter("mfee_id"));
				
				Integer user_id = new Integer(req.getParameter("user_id"));
				if ( user_id==null) {
					errorMsgs.add("請填入帳號!");					
				}
																
				java.sql.Date mfee_date = null;
				try {
					mfee_date = java.sql.Date.valueOf(req.getParameter("mfee_date").trim());
				} catch (IllegalArgumentException e) {
					mfee_date=new java.sql.Date(System.currentTimeMillis());				
					errorMsgs.add("請填入日期!");
				}

				java.sql.Date pay_date = null;
				try {
					pay_date = java.sql.Date.valueOf(req.getParameter("pay_date").trim());
				} catch (IllegalArgumentException e) {
					pay_date=new java.sql.Date(System.currentTimeMillis());				
					errorMsgs.add("請填入日期!");
				}
				
				MfeeVO mfeeVO = new MfeeVO();
				mfeeVO.setMfee_id(mfee_id);
				mfeeVO.setUser_id(user_id);
				mfeeVO.setMfee_date(mfee_date);
				mfeeVO.setPay_date(pay_date);
					
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mfeeVO", mfeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mfee/adminMfeeUpdate.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				MfeeService mfeeSvc = new MfeeService();
				mfeeVO = mfeeSvc.updateMfee(mfee_id, mfee_id, mfee_date, pay_date);
																								
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mfeeVO", mfeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				
				String url = requestURL;
				if ( requestURL.equals("/back-end/mfee/adminMfeeListAll.jsp") ) {
					url = "/back-end/mfee/adminMfeeListAll.jsp";
				} else if (requestURL.equals("/back-end/mfee/adminMfeeListOne.jsp")) {
					url = "/back-end/mfee/adminMfeeListOne.jsp";
				}
								
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeUpdate.jsp");
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
        	
        	String requestURL = req.getParameter("requestURL");

        	String mfee_year = req.getParameter("mfee_year");
        	String mfee_month = req.getParameter("mfee_month");
        	
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

				Integer user_id = new Integer(req.getParameter("user_id").trim());
				if ( user_id == null ) {
					errorMsgs.add("請輸入會員編號");					
				}
																						
				String date=mfee_year+"-"+mfee_month+"-"+"01";
				
				java.sql.Date mfee_date = java.sql.Date.valueOf(date.trim());
				
				java.sql.Date pay_date = null;
				pay_date = new java.sql.Date(System.currentTimeMillis());
				
				MfeeVO mfeeVO = new MfeeVO();
				mfeeVO.setUser_id(user_id);
				mfeeVO.setMfee_date(mfee_date);
				mfeeVO.setPay_date(pay_date);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mfeeVO", mfeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mfee/adminMfeeAdd.jsp");
//					.getRequestDispatcher("/back-end/sysu/addSysu.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************2.開始新增資料***************************************/
				MfeeService mfeeSvc = new MfeeService();
				mfeeVO = mfeeSvc.addMfee(user_id, mfee_date, pay_date);
								
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url=requestURL;
				if (requestURL.equals("/front-end/user/memberPayFee.jsp")){
					url = "/front-end/user/memberPayFeeSuccess.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/user/memberPayFee.jsp");
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
				Integer mfee_id = new Integer(req.getParameter("mfee_id"));
				
			    System.out.println(mfee_id);
				
				/***************************2.開始刪除資料***************************************/
				MfeeService mfeeSvc = new MfeeService();
				mfeeSvc.deleteMfee(mfee_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mfee/adminMfeeListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeListAll.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("Find_Unpay".equals(action)) {
					      
		    System.out.println("------------------------------------");
		    
		    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		    
		    Format formatterYear = new SimpleDateFormat("YYYY"); 
		    String currentYear = formatterYear.format(new java.util.Date());
		    System.out.println("Current year: "+currentYear);
		      
		    Format formatterMonth = new SimpleDateFormat("MM"); 
		    String currentMonth = formatterMonth.format(new java.util.Date());
		    System.out.println("Current month: "+currentMonth);
		    
		    System.out.println("------------------------------------");

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
						
			System.out.println("entry Find_Unpay");
			
			try {
				
				/***************************1.接收請求參數***************************************/
				String year = req.getParameter("mfee_year");
				String month = req.getParameter("mfee_month");
				
				if (year==null && month==null){
					year=currentYear;
					month=currentMonth;
				}
				
				System.out.println("MfeeServlet line 341: "+year);
				System.out.println("MfeeServlet line 342: "+month);
				
				/***************************2.開始查詢資料***************************************/
				MfeeService mfeeSvc = new MfeeService();
				
				System.out.println("MfeeServlet line344");
				List<MfeeVO> mfeeVOList =mfeeSvc.findUnpay(year,month);
				
				System.out.println("MfeeServlet line347");
				
				HttpSession session = req.getSession();
				session.setAttribute("mfeeVOList", mfeeVOList);
				session.setAttribute("mfee_year", year);
				session.setAttribute("mfee_month", month);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mfee/adminMfeeUnpay.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("查無資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
				failureView.forward(req, res);
			}

		}
				
		if ("LIST_USER_PAY_HISTORY".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
						
			System.out.println("entry LIST_USER_PAY_HISTORY");
			
			try {
				
				/***************************1.接收請求參數***************************************/
				Integer user_id = new Integer( req.getParameter("user_id").trim() );
				
				System.out.println("MfeeServlet line 386");
				
				/***************************2.開始查詢資料***************************************/
				MfeeService mfeeSvc = new MfeeService();
				
				System.out.println("MfeeServlet line390");
				
				List<MfeeVO> mfeeVOList =mfeeSvc.getOneByUser_id(user_id);
				
				System.out.println("MfeeServlet line394");
				
				HttpSession session = req.getSession();
				session.setAttribute("mfeeVOList", mfeeVOList);
				
				/***************************3.完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mfee/adminMfeeListOneHistory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
				
			} catch (Exception e) {
				errorMsgs.add("查無資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mfee/adminMfeeQuery.jsp");
				failureView.forward(req, res);
			}

		}
		
	}

}
