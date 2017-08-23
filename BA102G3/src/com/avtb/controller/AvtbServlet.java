package com.avtb.controller;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.avtb.model.AvtbService;
import com.avtb.model.AvtbVO;


@WebServlet("/avtbServlet")
public class AvtbServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF8");
		String action = req.getParameter("action");
		
		res.setContentType("text/html; charset=utf8");
		
		if ("FFF".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("avtb_id");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer avtb_id = null;
				try {
					avtb_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("時段編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AvtbService avtbSvc = new AvtbService();
				AvtbVO avtbVO = avtbSvc.getOneAvtb(avtb_id);
				if (avtbVO == null) {
					errorMsgs.add("查無資料");
				}
				int number = avtbVO.getAvtb_max_reservation()-avtbVO.getAvtb_reservation();
				PrintWriter out = res.getWriter();
				
//				res.getWriter().print(number);
				out.print(number);  //Ajax 回傳值
				out.flush();
				out.close();
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("avtb_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入時段編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				
				Integer avtb_id = null;
				try {
					avtb_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("時段編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AvtbService avtbSvc = new AvtbService();
				AvtbVO avtbVO = avtbSvc.getOneAvtb(avtb_id);
				if (avtbVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("avtbVO", avtbVO); // 資料庫取出的avtbVO物件,存入req
				String url = "/rest_interface/avtb/listOneAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneavtb.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("getOne_For_Display_By_Restid".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("rest_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入時段編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				
				Integer rest_id = null;
				try {
					rest_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("時段編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AvtbService avtbSvc = new AvtbService();
				List<AvtbVO> avtbVO = avtbSvc.findByPrimaryKeyByRest(rest_id);
				if (avtbVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("avtbVO", avtbVO); // 資料庫取出的avtbVO物件,存入req
				String url = "/rest_interface/avtb/listOneAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneavtb.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rest_interface/avtb/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllavtb.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer avtb_id = new Integer(req.getParameter("avtb_id"));
				
				/***************************2.開始查詢資料****************************************/
				AvtbService avtbSvc = new AvtbService();
				AvtbVO avtbVO = avtbSvc.getOneAvtb(avtb_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("avtbVO", avtbVO);         // 資料庫取出的avtbVO物件,存入req
				String url = "/rest_interface/avtb/update_avtb_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_avtb_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rest_interface/avtb/listAllAvtb.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_avtb_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer avtb_id = new Integer(req.getParameter("avtb_id").trim());
//				Integer rest_id = new Integer(req.getParameter("rest_id").trim());
//				Timestamp avtb_date_s = null;
//				Timestamp avtb_date_e = null;
				Integer avtb_reservation = null;
				Integer avtb_max_reservation = null;

				
//				try {
//					rest_id = new Integer(req.getParameter("rest_id").trim());
//				} catch (IllegalArgumentException e) {
//					rest_id = new Integer(1000026);
//					errorMsgs.add("3000001格式的餐廳編號");
//				}
				
//				try {
//					 Timestamp ts_s = new Timestamp(System.currentTimeMillis());
//					 String tsStr = req.getParameter("avtb_date_s").trim();
//					 StringBuilder date_s = new StringBuilder();
//					 date_s.append(tsStr);
//					 date_s.append(":00.000");
//					 try {
//					  ts_s = Timestamp.valueOf(date_s.toString());
//					  System.out.println(date_s);
//					 } catch (Exception e) {
//					  e.printStackTrace();
//					 }
//					 avtb_date_s = ts_s;
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("請輸入日期");
//				}
//				
//				try {
//					 Timestamp ts_e = new Timestamp(System.currentTimeMillis());
//					 String tsStr = req.getParameter("avtb_date_e").trim();
//					 StringBuilder date_e = new StringBuilder();
//					 date_e.append(tsStr);
//					 date_e.append(":00.000");
//					 try {
//					  ts_e = Timestamp.valueOf(date_e.toString());
//					  System.out.println(ts_e);
//					 } catch (Exception e) {
//					  e.printStackTrace();
//					 }
//					
//					avtb_date_e = ts_e;
//				} catch (IllegalArgumentException e) {
//					errorMsgs.add("請輸入日期");
//				}
				
				try {
					avtb_reservation = new Integer(req.getParameter("avtb_reservation").trim());
				} catch (IllegalArgumentException e) {
					avtb_reservation = new Integer(6);
					errorMsgs.add("請輸入數字");
				}
				
				try {
					avtb_max_reservation = new Integer(req.getParameter("avtb_max_reservation").trim());
				} catch (IllegalArgumentException e) {
					avtb_max_reservation = new Integer(10);
					errorMsgs.add("請輸入數字");
				}
				
				
				
				
				AvtbVO avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(avtb_id);
//				avtbVO.setRest_id(rest_id);
//				avtbVO.setAvtb_date_s(avtb_date_s);
//				avtbVO.setAvtb_date_e(avtb_date_e);
				avtbVO.setAvtb_reservation(avtb_reservation);
				avtbVO.setAvtb_max_reservation(avtb_max_reservation);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("avtbVO", avtbVO); // 含有輸入格式錯誤的avtbVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/avtb/update_avtb_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AvtbService avtbSvc = new AvtbService();
//				avtbVO = avtbSvc.updateAvtb(avtb_id,rest_id,avtb_date_s,avtb_date_e,avtb_reservation,avtb_max_reservation);
				avtbVO = avtbSvc.updateAvtbReservation(avtb_id, avtb_max_reservation);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("avtbVO", avtbVO); // 資料庫update成功後,正確的的avtbVO物件,存入req
				String url = "/rest_interface/avtb/listAllAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneavtb.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rest_interface/avtb/update_avtb_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addavtb.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("387");
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
			for(int i=0;i<7;i++){
				Integer avtb_id = new Integer(req.getParameter("avtb_id").trim());
				Integer rest_id = null;
				Timestamp avtb_date_s = null;
				Timestamp avtb_date_e = null;
				Integer avtb_reservation = null;
				Integer avtb_max_reservation = null;
				
				try {
					avtb_id = new Integer(req.getParameter("avtb_id").trim());
				} catch (IllegalArgumentException e) {
					avtb_id = new Integer(3000001);
					errorMsgs.add("3500001格式的時段編號");
				}
				
				try {
					rest_id = new Integer(req.getParameter("rest_id").trim());
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(1000026);
					errorMsgs.add("3000001格式的餐廳編號");
				}
				
				try {
					avtb_reservation = new Integer(req.getParameter("avtb_reservation").trim());
				} catch (IllegalArgumentException e) {
					avtb_reservation = new Integer(6);
					errorMsgs.add("請輸入數字");
				}
				
				try {
					avtb_max_reservation = new Integer(req.getParameter("avtb_max_reservation").trim());
				} catch (IllegalArgumentException e) {
					avtb_max_reservation = new Integer(10);
					errorMsgs.add("請輸入數字");
				}
				
				try {
					 Timestamp ts_s = new Timestamp(System.currentTimeMillis());
					 
//					 Calendar c = Calendar.getInstance();
					 java.sql.Timestamp startdate = null;
					 
					 String tsStr_s = req.getParameter("avtb_date_s").trim();
					 System.out.println(tsStr_s);
					 StringBuilder date_s = new StringBuilder();
					 date_s.append(tsStr_s);
					 date_s.append(":00.000");
					 try {
//						 startDate = Timestamp.valueOf(date_s.toString());
//						 c.setTime(startDate);
//						 c.add(Calendar.DATE, 1);
//						 startDate = new java.sql.Timestamp(c.getTimeInMillis());
//						 System.out.println("start+1:"+startDate);
						 startdate = dateCount(date_s,i);
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 avtb_date_s = startdate;
					
					
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				} 
				
				
				try {
					Timestamp ts_e = new Timestamp(System.currentTimeMillis());
					 
//					 Calendar c = Calendar.getInstance();
					 java.sql.Timestamp EndDate = null;
					 
					 String tsStr_e = req.getParameter("avtb_date_e").trim();
					 StringBuilder date_e = new StringBuilder();
					 date_e.append(tsStr_e);
					 date_e.append(":00.000");
					 try {
							EndDate = dateCount(date_e,i);
							
					 } catch (Exception e) {
						 e.printStackTrace();
					 }
					 avtb_date_e = EndDate;
						
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				} 
				

				AvtbVO avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(avtb_id);
				avtbVO.setRest_id(rest_id);
				avtbVO.setAvtb_date_s(avtb_date_s);
				avtbVO.setAvtb_date_e(avtb_date_e);
				avtbVO.setAvtb_reservation(avtb_reservation);
				avtbVO.setAvtb_max_reservation(avtb_max_reservation);
				System.out.println(avtb_id+"    "+rest_id+"   "+avtb_date_s+"   "+avtb_date_e+"   "+avtb_reservation+"   "+avtb_max_reservation);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("avtbVO", avtbVO); // 含有輸入格式錯誤的avtbVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/rest_interface/addAvtb.jsp");
					failureView.forward(req, res);
					
					return;
				}
//				
				/***************************2.開始新增資料***************************************/
				
				AvtbService avtbSvc = new AvtbService();
					
				avtbVO = avtbSvc.addAvtb(rest_id, avtb_date_s, avtb_date_e, avtb_reservation, avtb_max_reservation);
			}
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/rest_interface/listRest_idAllAvtbselect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllavtb.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} 
//			catch (Exception e) {
//				
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/rest_interface/avtb/addAvtb.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllavtb.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer avtb_id = new Integer(req.getParameter("avtb_id"));
				
				/***************************2.開始刪除資料***************************************/
				AvtbService avtbSvc = new AvtbService();
				avtbSvc.deleteAvtb(avtb_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/rest_interface/avtb/listAllAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/rest_interface/avtb/listAllAvtb.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	public int count(int i){
		
		return i;
	}
	public Timestamp dateCount(StringBuilder date_s, int i){
		
		 Calendar c = Calendar.getInstance();
		 java.sql.Timestamp count = null;
		 
		 count = Timestamp.valueOf(date_s.toString());
		 c.setTime(count);
		 c.add(Calendar.DATE, i);
		 count = new java.sql.Timestamp(c.getTimeInMillis());
		return count;
	}
}
