package com.item.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.item.model.*;
import com.prod.model.*;

public class ItemServlet extends HttpServlet {

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
				String str = req.getParameter("ord_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/item/select_page.jsp");
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
							.getRequestDispatcher("/front-end/item/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ord_id = null;
				try {
					ord_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/item/select_page.jsp");
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
							.getRequestDispatcher("/front-end/item/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(ord_id,prod_id);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/item/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的itemVO物件,存入req
				String url = "/front-end/item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/item/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				/***************************2.開始查詢資料****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(ord_id,prod_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itemVO", itemVO);         // 資料庫取出的itemVO物件,存入req
				String url = "/front-end/item/update_item_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_item_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ord_id = new Integer(req.getParameter("ord_id").trim());
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				Integer item_qty = new Integer(req.getParameter("item_qty").trim());
				Integer item_score = new Integer(req.getParameter("item_score").trim());
				String item_review = req.getParameter("item_review").trim();
				
				java.sql.Date item_reviewdate = null;
				try {
					item_reviewdate = java.sql.Date.valueOf(req.getParameter("item_reviewdate").trim());
				} catch (IllegalArgumentException e) {
					item_reviewdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入檢舉日期!");
				}


				ItemVO itemVO = new ItemVO();
				itemVO.setOrd_id(ord_id);
				itemVO.setProd_id(prod_id);
				itemVO.setItem_qty(item_qty);
				itemVO.setItem_score(item_score);
				itemVO.setItem_review(item_review);
				itemVO.setItem_reviewdate(item_reviewdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的itemVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.updateItem(ord_id, prod_id, item_qty, item_score, item_review,item_reviewdate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的itemVO物件,存入req
				String url = "/front-end/item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/item/update_item_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addItem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer ord_id = new Integer(req.getParameter("ord_id").trim());
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				Integer item_qty = new Integer(req.getParameter("item_qty").trim());
				Integer item_score = new Integer(req.getParameter("item_score").trim());
				String item_review = req.getParameter("item_review").trim();
				
				java.sql.Date item_reviewdate = null;
				try {
					item_reviewdate = java.sql.Date.valueOf(req.getParameter("item_reviewdate").trim());
				} catch (IllegalArgumentException e) {
					item_reviewdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入檢舉日期!");
				}

				System.out.println(item_reviewdate);
				ItemVO itemVO = new ItemVO();
				itemVO.setOrd_id(ord_id);
				itemVO.setProd_id(prod_id);
				itemVO.setItem_qty(item_qty);
				itemVO.setItem_score(item_score);
				itemVO.setItem_review(item_review);
				itemVO.setItem_reviewdate(item_reviewdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的itemVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.addItem(ord_id, prod_id, item_qty, item_score, item_review,item_reviewdate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/item/listAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllItem.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/item/addItem.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllItem.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				/***************************2.開始刪除資料***************************************/
				ItemService itemSvc = new ItemService();
				itemSvc.deleteItem(ord_id,prod_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/item/listAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_Ord_id_AllItem".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ord_id");
				Integer ord_id=new Integer(str);
				
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> oneord_idAllItem = itemSvc.getOneOrd_idAllItem(ord_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("oneord_idAllItem", oneord_idAllItem); // 資料庫取出的itemVO物件,存入req
				String url = "/front-end/store_interface/listOneOrd_idAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		}
		
		if ("getOne_Ord_id_AllItem_formember".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ord_id");
				Integer ord_id=new Integer(str);
				
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> oneord_idAllItem = itemSvc.getOneOrd_idAllItem(ord_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("oneord_idAllItem", oneord_idAllItem);
				HttpSession session=req.getSession();
				session.setAttribute("oneord_idAllItem", oneord_idAllItem);
				// 資料庫取出的itemVO物件,存入req
				String url = "/front-end/member_interface/listOneOrd_idAllItem_formember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		}
		
		if ("getOne_prod_id_upate_count_score".equals(action)) { // 來自update_ord_input.jsp的請求

			/***************************1.接收請求參數****************************************/
			Integer prod_id = new Integer(req.getParameter("prod_id"));
			Integer ord_id = new Integer(req.getParameter("ord_id"));
			
			/***************************2.開始查詢資料****************************************/
			ItemService itemSvc = new ItemService();
			ItemVO itemVO = itemSvc.getOneItem(ord_id,prod_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("itemVO", itemVO);         // 資料庫取出的ordVO物件,存入req
			String url = "/front-end/member_interface/update_prod_count_score.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
	}
	if ("update_prodCount_Score".equals(action)) { // 來自update_ord_input.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		res.setHeader("content-type", "text/html;charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out=res.getWriter();
//		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
		Integer ord_id = new Integer(req.getParameter("ord_id").trim());
		Integer prod_id = new Integer(req.getParameter("prod_id").trim());
		Integer item_qty = new Integer(req.getParameter("item_qty").trim());
		Integer item_score = new Integer(req.getParameter("item_score").trim());
		System.out.println(item_score);
		String item_review = "";
		java.sql.Date item_reviewdate = null;
		ItemVO itemVO = new ItemVO();
		itemVO.setOrd_id(ord_id);
		itemVO.setProd_id(prod_id);
		itemVO.setItem_qty(item_qty);
		itemVO.setItem_score(item_score);
		itemVO.setItem_review(item_review);
		itemVO.setItem_reviewdate(item_reviewdate);

		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的itemVO物件,也存入req
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/item/update_item_input.jsp");
			failureView.forward(req, res);
			return; //程式中斷
		}
		
		/***************************2.開始修改資料*****************************************/
		ItemService itemSvc = new ItemService();
		itemVO = itemSvc.updateItem(ord_id, prod_id, item_qty, item_score, item_review,item_reviewdate);
		ProdService prodSvc= new ProdService();
		prodSvc.update_count_score(1+prodSvc.getOneProd(prod_id).getProd_count(), item_score+prodSvc.getOneProd(prod_id).getProd_score(), prod_id);
		
		
		/***************************3.修改完成,準備轉交(Send the Success view)*************/
		req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的itemVO物件,存入req
		String url = "/front-end/member_interface/update_prod_count_score.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneItem.jsp
		successView.forward(req, res);

		/***************************其他可能的錯誤處理*************************************/
//	} catch (Exception e) {
//		errorMsgs.add("修改資料失敗:"+e.getMessage());
//		RequestDispatcher failureView = req
//				.getRequestDispatcher("/front-end/item/update_item_input.jsp");
//		failureView.forward(req, res);
//	}
}
		
	}
}
