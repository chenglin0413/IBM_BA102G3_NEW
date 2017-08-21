package com.avtb.controller;

import java.io.*;
import java.sql.Date;
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
		
		
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("avtb_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入時段編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/avtbaurant/avtb/select_page.jsp");
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
							.getRequestDispatcher("/front-end/avtbaurant/avtb/select_page.jsp");
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
							.getRequestDispatcher("/front-end/avtbaurant/avtb/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("avtbVO", avtbVO); // 資料庫取出的avtbVO物件,存入req
				String url = "/front-end/avtbaurant/avtb/listOneAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneavtb.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/avtbaurant/avtb/select_page.jsp");
				failureView.forward(req, res);
			}
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
				String url = "/front-end/avtbaurant/avtb/update_avtb_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_avtb_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/avtbaurant/avtb/listAllAvtb.jsp");
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
				Integer rest_id = null;
				Date avtb_date_s = null;
				Date avtb_date_e = null;
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
				
//				try {
//					avtb_date_s = req.getParameter("avtb_date_s").trim();
//					if(avtb_date_s.isEmpty()){
//						errorMsgs.add("");
//					}
//				} catch (NumberFormatException e) {
//					avtb_date_s = "";
//					errorMsgs.add("");
//				}
				
//				try {
//					avtb_date_e = req.getParameter("avtb_date_e").trim();
//					if(avtb_date_e.isEmpty()){
//						errorMsgs.add("不要為空");
//					}
//				} catch (NumberFormatException e) {
//					avtb_date_e = "2017-08-03";
//					errorMsgs.add("");
//				}
				
				try {
					avtb_reservation = new Integer("avtb_reservation");
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(6);
					errorMsgs.add("請輸入數字");
				}
				
				try {
					avtb_max_reservation = new Integer("avtb_max_reservation");
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(10);
					errorMsgs.add("請輸入數字");
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
							.getRequestDispatcher("/front-end/avtbaurant/avtb/update_avtb_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AvtbService avtbSvc = new AvtbService();
				avtbVO = avtbSvc.updateAvtb(avtb_id,rest_id,avtb_date_s,avtb_date_e,avtb_reservation,avtb_max_reservation);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("avtbVO", avtbVO); // 資料庫update成功後,正確的的avtbVO物件,存入req
				String url = "/front-end/avtbaurant/avtb/listOneAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneavtb.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/avtbaurant/avtb/update_avtb_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addavtb.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer avtb_id = new Integer(req.getParameter("avtb_id").trim());
				Integer rest_id = null;
				Date avtb_date_s = null;
				Date avtb_date_e = null;
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
				
//				try {
//					avtb_date_s = req.getParameter("avtb_date_s").trim();
//					if(avtb_date_s.isEmpty()){
//						errorMsgs.add("");
//					}
//				} catch (NumberFormatException e) {
//					avtb_date_s = "";
//					errorMsgs.add("");
//				}
				
//				try {
//					avtb_date_e = req.getParameter("avtb_date_e").trim();
//					if(avtb_date_e.isEmpty()){
//						errorMsgs.add("不要為空");
//					}
//				} catch (NumberFormatException e) {
//					avtb_date_e = "2017-08-03";
//					errorMsgs.add("");
//				}
				
				try {
					avtb_reservation = new Integer("avtb_reservation");
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(6);
					errorMsgs.add("請輸入數字");
				}
				
				try {
					avtb_max_reservation = new Integer("avtb_max_reservation");
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(10);
					errorMsgs.add("請輸入數字");
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
							.getRequestDispatcher("/front-end/avtbaurant/avtb/addAvtb.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				AvtbService avtbSvc = new AvtbService();
				
				avtbVO = avtbSvc.addAvtb(avtb_id, rest_id, avtb_date_s, avtb_date_e, avtb_reservation, avtb_max_reservation);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/avtbaurant/avtb/listAllAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllavtb.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
			catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/avtbaurant/avtb/addAvtb.jsp");
				failureView.forward(req, res);
			}
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
				String url = "/front-end/avtbaurant/avtb/listAllAvtb.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/avtbaurant/avtb/listAllAvtb.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
