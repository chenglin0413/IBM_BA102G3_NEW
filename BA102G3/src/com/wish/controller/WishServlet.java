package com.wish.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ord.model.OrdService;
import com.prod.model.ProdDAO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;
import com.wish.model.WishService;
import com.wish.model.WishVO;



public class WishServlet extends HttpServlet {

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
				String str = req.getParameter("user_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer user_id = null;
				try {
					user_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String str2 = req.getParameter("prod_id");
				if (str2 == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer prod_id = null;
				try {
					prod_id = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				WishService wishSvc = new WishService();
				WishVO wishVO = wishSvc.getOneWish(user_id,prod_id);
				if (wishVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wishVO", wishVO); // 資料庫取出的wishVO物件,存入req
				String url = "/front-end/wish/listOneWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneWish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/wish/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_From05".equals(action)) {

			try {
				// Retrieve form parameters.
				Integer prod_id = new Integer(req.getParameter("prod_id"));

				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getOneProd(prod_id);
				

				req.setAttribute("prodVO", prodVO); // 資料庫取出的empVO物件,存入req
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front-end/eshop/EShop.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllWish.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數****************************************/
				Integer user_id = new Integer(req.getParameter("user_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				
				/***************************2.開始查詢資料****************************************/
				WishService wishSvc = new WishService();
				WishVO wishVO = wishSvc.getOneWish(user_id,prod_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("wishVO", wishVO);         // 資料庫取出的wishVO物件,存入req
				String url = "/front-end/wish/update_wish_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_wish_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/wish/listAllWish.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_wish_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				
				java.sql.Date wish_date = null;
				try {
					wish_date = java.sql.Date.valueOf(req.getParameter("wish_date").trim());
				} catch (IllegalArgumentException e) {
					wish_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請產品追蹤日期!");
				}



				WishVO wishVO = new WishVO();
				wishVO.setUser_id(user_id);
				wishVO.setProd_id(prod_id);
				wishVO.setWish_date(wish_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishVO", wishVO); // 含有輸入格式錯誤的wishVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/update_wish_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				WishService wishSvc = new WishService();
				wishVO = wishSvc.updateWish(user_id, prod_id, wish_date);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("wishVO", wishVO); // 資料庫update成功後,正確的的wishVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneWish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/wish/update_wish_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				
				java.sql.Date wish_date = null;
				
				try {
					wish_date = java.sql.Date.valueOf(req.getParameter("wish_date").trim());
				} catch (IllegalArgumentException e) {
					wish_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請產品追蹤日期!");
				}
				WishVO wishVO = new WishVO();
				wishVO.setUser_id(user_id);
				wishVO.setProd_id(prod_id);
				wishVO.setWish_date(wish_date);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("wishVO", wishVO); // 含有輸入格式錯誤的wishVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/wish/addWish.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				WishService wishSvc = new WishService();
				wishVO = wishSvc.addWish(user_id, prod_id, wish_date);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/wish/listAllWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllWish.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/wish/addWish.jsp");
				failureView.forward(req, res);
			}
		}
		 if ("ADDTOWish".equals(action)) { 
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL");
					
//					try {
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						WishService wishSvctest=new WishService();
						Integer user_id = new Integer(req.getParameter("user_id").trim());
						Integer prod_id = new Integer(req.getParameter("prod_id").trim());
						List<WishVO> wishlist=wishSvctest.getOneUser_idAllWish(user_id);
						for(WishVO wishVO:wishlist){
							if(wishVO.getProd_id().equals(prod_id)){
								errorMsgs.add("產品已在追蹤中瞜!");
							}
						}
						java.sql.Date wish_date = null;
						wish_date = new java.sql.Date(System.currentTimeMillis());
						
						WishVO wishVO = new WishVO();
						wishVO.setUser_id(user_id);//接上user後要修改
						wishVO.setProd_id(prod_id);
						wishVO.setWish_date(wish_date);
						
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("wishVO", wishVO); // 含有輸入格式錯誤的wishVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/member_interface/listOneUser_idAllWish.jsp");
							failureView.forward(req, res);
							return;
						}
						/***************************2.開始新增資料***************************************/
						WishService wishSvc = new WishService();
						wishVO = wishSvc.addWish(user_id, prod_id, wish_date);
						
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						String url = requestURL;
						if(requestURL.equals("/front-end/eshop/EShop.jsp")){
							RequestDispatcher successView = req.getRequestDispatcher("/front-end/member_interface/listAllProd.jsp");
							successView.forward(req, res);
						}else{
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
						}
						
						
						/***************************其他可能的錯誤處理**********************************/
//					} catch (Exception e) {
//						errorMsgs.add(e.getMessage());
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/front-end/wish/addWish.jsp");
//						failureView.forward(req, res);
//					}
				}
		
		if ("delete".equals(action)) { // 來自listAllWish.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數***************************************/
				Integer user_id = new Integer(req.getParameter("user_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				
				/***************************2.開始刪除資料***************************************/
				WishService wishSvc = new WishService();
				wishSvc.deleteWish(user_id,prod_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url ="/front-end/member_interface/listOneUser_idAllWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member_interface/listOneUser_idAllWish.jsp");
				failureView.forward(req, res);
			}
		}
	
	}
}
