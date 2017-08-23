package com.dish.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.dish.model.*;

public class DishServlet extends HttpServlet {

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
				String str = req.getParameter("dish_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer dish_id = null;
				try {
					dish_id = new Integer(str);
					
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DishService dishSvc = new DishService();
				DishVO dishVO = dishSvc.getOneDish(dish_id);
				if (dishVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("dishVO", dishVO); // 資料庫取出的dishVO物件,存入req
				String url = "/front-end/restaurant/dish/listOneDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllDish.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer dish_id = new Integer(req.getParameter("dish_id"));
				
				/***************************2.開始查詢資料****************************************/
				DishService dishSvc = new DishService();
				DishVO dishVO = dishSvc.getOneDish(dish_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("dishVO", dishVO);         // 資料庫取出的dishVO物件,存入req
				String url = "/front-end/restaurant/dish/update_dish_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_dish_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_dish_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer dish_id = new Integer(req.getParameter("dish_id").trim());
				String dish_name = null;
				Integer rest_id = null;
				Integer dish_price = null;
				String dish_note = null;
				Integer dish_status = null;
				String dish_detail =  req.getParameter("dish_detail").trim();
				
				try {
					dish_id = new Integer(req.getParameter("dish_id").trim());
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(3000001);
					errorMsgs.add("3200001格式的料理編號");
				}
				
				try {
					rest_id = new Integer(req.getParameter("rest_id").trim());
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(3000001);
					errorMsgs.add("3000001格式的餐廳編號");
				}
				
				
				try {
					dish_price = new Integer(req.getParameter("dish_price").trim());
					
				} catch (NumberFormatException e) {
					dish_price = 0;
					errorMsgs.add("請輸入數字");
				}
				
				try {
					dish_status = new Integer(req.getParameter("dish_status").trim());
					if(!(dish_status==0||dish_status==1)){
						errorMsgs.add("請輸入數字0(下架)或1(上架)");
					}
				} catch (NumberFormatException e) {
					dish_status = 1;
					errorMsgs.add("請輸入數字0(下架)或1(上架)");
				}
				
				
				try {
					dish_note = req.getParameter("dish_note").trim();
					if(!(dish_note.matches("[1-5]"))){
						errorMsgs.add("請填數字1:早餐 2:午餐 3:晚餐 4:下午茶 5:全天");
					}
				} catch (NumberFormatException e) {
					dish_note = "5";
					errorMsgs.add("請填數字1:早餐 2:午餐 3:晚餐 4:下午茶 5:全天.");
				}
				
				try {
					dish_name = req.getParameter("dish_name").trim();
					if(dish_name.isEmpty()){
						errorMsgs.add("不要為空");
					}
				} catch (NumberFormatException e) {
					dish_name = "炸鳳尾蝦";
					errorMsgs.add("不要為空");
				}
				
				
				

				DishVO dishVO = new DishVO();
				dishVO.setDish_id(dish_id);
				dishVO.setDish_name(dish_name);
				dishVO.setDish_price(dish_price);
				dishVO.setRest_id(rest_id);
				dishVO.setDish_status(dish_status);
				dishVO.setDish_note(dish_note);
				dishVO.setDish_detail(dish_detail);
				
				System.out.println(dish_id+"    "+dish_name+"   "+dish_price+"   "+rest_id+"   "+dish_status+"   "+dish_note+"   "+dish_detail);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的dishVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dish/update_dish_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				DishService dishSvc = new DishService();
				dishVO = dishSvc.updateDish(rest_id, dish_name, dish_price, dish_status, dish_detail, dish_note,dish_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("dishVO", dishVO); // 資料庫update成功後,正確的的dishVO物件,存入req
				String url = "/front-end/restaurant/dish/listOneDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dish/update_dish_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addDish.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String dish_name = null;
				Integer rest_id = null;
				Integer dish_price = null;
				String dish_note = null;
				Integer dish_status = null;
				String dish_detail =  req.getParameter("dish_detail").trim();
				try {
					rest_id = new Integer(req.getParameter("rest_id").trim());
				} catch (IllegalArgumentException e) {
					rest_id = new Integer(3000001);
					errorMsgs.add("3000001格式的餐廳編號");
				}
				
				
				try {
					dish_price = new Integer(req.getParameter("dish_price").trim());
					
				} catch (NumberFormatException e) {
					dish_price = 0;
					errorMsgs.add("請輸入數字");
				}
				
				try {
					dish_status = new Integer(req.getParameter("dish_status").trim());
					if(!(dish_status==0||dish_status==1)){
						errorMsgs.add("請輸入數字0(下架)或1(上架)");
					}
				} catch (NumberFormatException e) {
					dish_status = 1;
					errorMsgs.add("請輸入數字0(下架)或1(上架)");
				}
				
				
				try {
					dish_note = req.getParameter("dish_note").trim();
					if(!(dish_note.matches("[1-5]"))){
						errorMsgs.add("請填數字1:早餐 2:午餐 3:晚餐 4:下午茶 5:全天");
					}
				} catch (NumberFormatException e) {
					dish_note = "5";
					errorMsgs.add("請填數字1:早餐 2:午餐 3:晚餐 4:下午茶 5:全天.");
				}
				
				try {
					dish_name = req.getParameter("dish_name").trim();
					if(dish_name.isEmpty()){
						errorMsgs.add("不要為空");
					}
				} catch (NumberFormatException e) {
					dish_name = "炸鳳尾蝦";
					errorMsgs.add("不要為空");
				}
				
				
				

				DishVO dishVO = new DishVO();
				dishVO.setDish_name(dish_name);
				dishVO.setDish_price(dish_price);
				dishVO.setRest_id(rest_id);
				dishVO.setDish_status(dish_status);
				dishVO.setDish_note(dish_note);
				dishVO.setDish_detail(dish_detail);
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的dishVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dish/addDish.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				
				DishService dishSvc = new DishService();
				
				dishVO = dishSvc.addDish(rest_id, dish_name, dish_price, dish_status, dish_detail, dish_note);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/restaurant/dish/listAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDish.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} 
			catch (Exception e) {
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dish/addDish.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllDish.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer dish_id = new Integer(req.getParameter("dish_id"));
				
				/***************************2.開始刪除資料***************************************/
				DishService dishSvc = new DishService();
				dishSvc.deleteDish(dish_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/restaurant/dish/listAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dish/listAllDish.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
