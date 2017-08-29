package com.reta.controller;

import java.io.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.avtb.model.AvtbService;
import com.avtb.model.AvtbVO;
import com.reta.model.RetaService;
import com.reta.model.RetaVO;


@WebServlet("/RetaServlet")
public class RetaServlet extends HttpServlet {
       
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
				String str = req.getParameter("reta_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂位編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer reta_id = null;
				try {
					reta_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("訂位編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RetaService retaSvc = new RetaService();
				RetaVO retaVO = retaSvc.getOneReta(reta_id);
				if (retaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("retaVO", retaVO); // 資料庫取出的retaVO物件,存入req
				String url = "/front-end/restaurant/reta/listOneReta.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnereta.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllreta.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("reta_id");
				Integer reta_id = new Integer(str);
				/***************************2.開始查詢資料****************************************/
				RetaService retaSvc = new RetaService();
				RetaVO retaVO = retaSvc.getOneReta(reta_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("retaVO", retaVO);         // 資料庫取出的retaVO物件,存入req
				String url = "/front-end/rest_interface/update_reta_changeStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_reta_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/restaurant/reta/listAllReta.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("update_status".equals(action)) { // 來自update_reta_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer reta_id = new Integer(req.getParameter("reta_id").trim());
				Integer avtb_id = new Integer(req.getParameter("avtb_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer reta_number = new Integer(req.getParameter("reta_number").trim());
				Integer reta_status = new Integer(req.getParameter("reta_status").trim());
				Integer reta_grant = new Integer(req.getParameter("reta_grant").trim());
				Date reta_date = java.sql.Date.valueOf(req.getParameter("reta_date").trim());
				Integer reta_rank_res = new Integer(req.getParameter("reta_rank_res").trim());
				String reta_review = null;
				Date reta_reviewdate = null;
				Date rest_rpdate = null;
				String rest_rpcomm = null;
				Integer rest_rpstatus = 0;
				
//				try {
//					reta_id = new Integer(req.getParameter("reta_id").trim());
//				} catch (IllegalArgumentException e) {
//					reta_id = new Integer(3000001);
//					errorMsgs.add("3000001格式的餐廳編號");
//				}
//				
//				try {
//					user_id = new Integer(req.getParameter("user_id").trim());
//				} catch (IllegalArgumentException e) {
//					user_id = new Integer(1000044);
//					errorMsgs.add("1000044格式的會員編號");
//				}

			
				RetaVO retaVO = new RetaVO();
				retaVO.setReta_id(reta_id);
				retaVO.setAvtb_id(avtb_id);
				retaVO.setUser_id(user_id);
				retaVO.setReta_number(reta_number);
				retaVO.setReta_status(reta_status);
				retaVO.setReta_grant(reta_grant);
				retaVO.setReta_date(reta_date);
				retaVO.setReta_rank_res(reta_rank_res);
				retaVO.setReta_review(reta_review);
				retaVO.setReta_reviewdate(reta_reviewdate);
				retaVO.setRest_rpdate(rest_rpdate);
				retaVO.setRest_rpcomm(rest_rpcomm);
				retaVO.setRest_rpstatus(rest_rpstatus);
				
				System.out.println(reta_id+"    "+avtb_id+"   "+user_id+"   "+reta_number+"   "+reta_status+"   "+reta_grant+"   "+reta_date+"    "
						          +reta_rank_res+"    "+reta_review+"   "+reta_reviewdate+"   "+rest_rpdate+"   "+rest_rpcomm+"   "+rest_rpstatus);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("retaVO", retaVO); // 含有輸入格式錯誤的retaVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/update_reta_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RetaService retaSvc = new RetaService();
				retaVO = retaSvc.updateReta(reta_id,avtb_id,user_id,reta_number,reta_status,reta_grant,reta_date,
				         reta_rank_res,reta_review,reta_reviewdate,rest_rpdate,rest_rpcomm,rest_rpstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("retaVO", retaVO); // 資料庫update成功後,正確的的retaVO物件,存入req
				String url = "/front-end/rest_interface/listRest_idAllRetaselect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnereta.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/restaurant/reta/update_reta_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addreta.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer reta_id = new Integer(req.getParameter("reta_id").trim());
				Integer avtb_id = new Integer(req.getParameter("avtb_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer reta_number = new Integer(req.getParameter("reta_number").trim());
				Integer reta_status = new Integer(req.getParameter("reta_status").trim());
				Integer reta_grant = new Integer(req.getParameter("reta_grant").trim());
				Date reta_date = java.sql.Date.valueOf(req.getParameter("reta_date").trim()) ;
				Integer reta_rank_res = new Integer(req.getParameter("reta_rank_res").trim());
				String reta_review = null;
				Date reta_reviewdate = null;
				Date rest_rpdate = null;
				String rest_rpcomm = null;
				Integer rest_rpstatus = new Integer(req.getParameter("rest_rpstatus").trim());
				
				try {
					reta_id = new Integer(req.getParameter("reta_id").trim());
				} catch (IllegalArgumentException e) {
					reta_id = new Integer(3400001);
					errorMsgs.add("3400001格式的訂位編號");
				}
				
				try {
					user_id = new Integer(req.getParameter("user_id").trim());
				} catch (IllegalArgumentException e) {
					user_id = new Integer(1000044);
					errorMsgs.add("1000044格式的會員編號");
				}
				
				

				RetaVO retaVO = new RetaVO();
				retaVO.setReta_id(reta_id);
				retaVO.setAvtb_id(avtb_id);
				retaVO.setUser_id(user_id);
				retaVO.setReta_number(reta_number);
				retaVO.setReta_status(reta_status);
				retaVO.setReta_grant(reta_grant);
				retaVO.setReta_date(reta_date);
				retaVO.setReta_rank_res(reta_rank_res);
				retaVO.setReta_review(reta_review);
				retaVO.setReta_reviewdate(reta_reviewdate);
				retaVO.setRest_rpdate(rest_rpdate);
				retaVO.setRest_rpcomm(rest_rpcomm);
				retaVO.setRest_rpstatus(rest_rpstatus);
				
				System.out.println(reta_id+"    "+avtb_id+"   "+user_id+"   "+reta_number+"   "+reta_status+"   "+reta_grant+"   "+reta_date+"    "
				          +reta_rank_res+"    "+reta_review+"   "+reta_reviewdate+"   "+rest_rpdate+"   "+rest_rpcomm+"   "+rest_rpstatus);
		
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("retaVO", retaVO); // 含有輸入格式錯誤的retaVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/addReta.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				RetaService retaSvc = new RetaService();
				AvtbService avtbSvc = new AvtbService();
				AvtbVO avtbVO = new AvtbVO();
				
				avtbVO = avtbSvc.getOneAvtb(avtb_id);
				
				int freeseat = avtbVO.getAvtb_reservation();
				System.out.println("目前訂位人數:"+freeseat);
				System.out.println(avtbVO.getAvtb_max_reservation());
				
				if(avtbVO.getAvtb_max_reservation() >= freeseat+reta_number){
					freeseat =  avtbVO.getAvtb_reservation()+reta_number;
					avtbVO = avtbSvc.updateFreeSeat(avtb_id, freeseat);
				}else {
					freeseat = avtbVO.getAvtb_max_reservation()- avtbVO.getAvtb_reservation();
					errorMsgs.add("目前剩餘"+freeseat+"位");
			
					if (!errorMsgs.isEmpty()) {
						System.out.println("123");
						req.setAttribute("retaVO", retaVO); // 含有輸入格式錯誤的retaVO物件,也存入req
						req.setAttribute("avtbVO", avtbVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/rest/addReta.jsp");
						failureView.forward(req, res);
						
						return;
					}
				}
				
				retaVO = retaSvc.addReta(reta_id,avtb_id,user_id,reta_number,reta_status,reta_grant,reta_date,
										 reta_rank_res,reta_review,reta_reviewdate,rest_rpdate,rest_rpcomm,rest_rpstatus);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("retaVO", retaVO);
				String url = "/front-end/member_interface_rest/rest/resultReta.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllreta.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
//			catch (Exception e) {
//				
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/restaurant/reta/addReta.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("delete".equals(action)) { // 來自listAllreta.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer reta_id = new Integer(req.getParameter("reta_id"));
				
				/***************************2.開始刪除資料***************************************/
				RetaService retaSvc = new RetaService();
				retaSvc.deleteReta(reta_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/restaurant/reta/listAllReta.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/reta/listAllReta.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listReta_By_UserId".equals(action)) { // 來自listRest_idAllRetaselect.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("user_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				System.out.println(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer user_id = null;
				try {
					user_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("訂位編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RetaService retaSvc = new RetaService();
				RetaVO retaVO = (RetaVO) retaSvc.findByUserId(user_id);
				if (retaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("retaVO", retaVO); // 資料庫取出的retaVO物件,存入req
				String url = "/front-end/rest_interface/listRest_idAllRetaselect.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listRest_idAllRetaselect.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/reta/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update_By_Userid".equals(action)) { // 來自listAllreta.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String str = req.getParameter("user_id");
				Integer user_id = new Integer(str);
				System.out.println("userid"+ user_id);
				System.out.println("452");
				/***************************2.開始查詢資料****************************************/
				RetaService retaSvc = new RetaService();
				RetaVO retaVO = retaSvc.getOneRetaByUserID(user_id);
							

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("retaVO", retaVO);         // 資料庫取出的retaVO物件,存入req
				String url = "/front-end/rest_interface/update_reta_changeStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_reta_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/reta/listAllReta.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
