package com.ord.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.ord.model.*;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;
import com.user.model.UserService;
import com.user.model.UserVO;

import mailService.MailService;
import messageSend.Send;

public class OrdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
			if ("insert_ord_item".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Vector<OrdVO> addOrd = (Vector<OrdVO>) session.getAttribute("addOrd");
			Vector<ItemVO> addItem = (Vector<ItemVO>) session.getAttribute("addItem");
			List<Integer> ord_ids=new ArrayList<Integer>();
			int [] stores=new int[addOrd.size()];
				try {
					
					for(int i=0;i<addOrd.size();i++){
					
					Integer user_id = new Integer(addOrd.elementAt(i).getUser_id());
					Integer store_id = new Integer(addOrd.elementAt(i).getStore_id());
//					stores[i]=store_id;
					java.sql.Date ord_date = null;
					try {
						ord_date =addOrd.elementAt(i).getOrd_date();
					} catch (IllegalArgumentException e) {
						ord_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入訂單日期!");
					}
					Integer ord_total = new Integer(addOrd.elementAt(i).getOrd_total());
					Integer ord_bill = 1;
					Integer ord_grant = 1;
					Integer ord_status = 1;
					Integer ord_sscore = 0;
					java.sql.Date ord_rpdate = null;
					String ord_rpcomm= "";
					Integer ord_rpstatus = 0;
					
					OrdVO ordVO = new OrdVO();
					ordVO.setUser_id(user_id);
					ordVO.setStore_id(store_id);
					ordVO.setOrd_date(ord_date);
					ordVO.setOrd_total(ord_total);
					ordVO.setOrd_bill(ord_bill);
					ordVO.setOrd_grant(ord_grant);
					ordVO.setOrd_status(ord_status);
					ordVO.setOrd_sscore(ord_sscore);
					ordVO.setOrd_rpdate(ord_rpdate);
					ordVO.setOrd_rpcomm(ord_rpcomm);
					ordVO.setOrd_rpstatus(ord_rpstatus);
					/***************************2.開始新增資料***************************************/
					OrdService ordSvc=new OrdService();
					Integer ord_id=ordSvc.insertWithItems(ordVO, addItem);
					ord_ids.add(ord_id);
					}
				
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					session.setAttribute("ord_ids",ord_ids);
					String url = "/front-end/member_interface/easyCreditCard.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/addOrd_n.jsp");
					failureView.forward(req, res);
				}
			}
				
	
		
		
		
	
		
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
							.getRequestDispatcher("/front-end/ord/select_page.jsp");
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
							.getRequestDispatcher("/front-end/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_id);
				if (ordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫取出的ordVO物件,存入req
				String url = "/front-end/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				
				/***************************2.開始查詢資料****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ordVO", ordVO);         // 資料庫取出的ordVO物件,存入req
				String url = "/front-end/ord/update_ord_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_ord_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ord/listAllOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_UpdateReport".equals(action)) { // 來自listAllEmp.jsp的請求

				/***************************1.接收請求參數****************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				
				/***************************2.開始查詢資料****************************************/
				OrdService ordSvc = new OrdService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ordVO", ordVO);         // 資料庫取出的ordVO物件,存入req
				String url = "/front-end/store_interface/update_ord_report.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
		}
		if ("getOne_ChangeStatus".equals(action)) { // 來自listAllEmp.jsp的請求

				/***************************1.接收請求參數****************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				
				/***************************2.開始查詢資料****************************************/
				OrdService ordSvc = new OrdService();
				ItemService itemSvc = new ItemService();
				OrdVO ordVO = ordSvc.getOneOrd(ord_id);
				List<ItemVO>oneord_idAllItem=itemSvc.getOneOrd_idAllItem(ord_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("ordVO", ordVO);
				req.setAttribute("oneord_idAllItem", oneord_idAllItem);
				String url = "/front-end/store_interface/update_ord_changeStatus.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
		}
		if ("update_changeStatus".equals(action)) { // 來自update_ord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ord_id = new Integer(req.getParameter("ord_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				
				java.sql.Date ord_date = null;
				try {
					ord_date = java.sql.Date.valueOf(req.getParameter("ord_date").trim());
				} catch (IllegalArgumentException e) {
					ord_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer ord_total = new Integer(req.getParameter("ord_total").trim());
				Integer ord_bill = new Integer(req.getParameter("ord_bill").trim());
				Integer ord_grant = new Integer(req.getParameter("ord_grant").trim());
				Integer ord_status = new Integer(req.getParameter("ord_status").trim());
				Integer ord_sscore = new Integer(req.getParameter("ord_sscore").trim());
				java.sql.Date ord_rpdate =new java.sql.Date(System.currentTimeMillis());
				String ord_rpcomm=req.getParameter("ord_rpcomm").trim();
				Integer ord_rpstatus = new Integer(req.getParameter("ord_rpstatus").trim());

				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_id(ord_id);
				ordVO.setUser_id(user_id);
				ordVO.setStore_id(store_id);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_total(ord_total);
				ordVO.setOrd_bill(ord_bill);
				ordVO.setOrd_grant(ord_grant);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_sscore(ord_sscore);
				ordVO.setOrd_rpdate(ord_rpdate);
				ordVO.setOrd_rpcomm(ord_rpcomm);
				ordVO.setOrd_rpstatus(ord_rpstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/store_interface/update_ord_changeStatus.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdService ordSvc = new OrdService();
				ProdService prodSvc = new ProdService();
				ItemService itemSvc = new ItemService();
				ordVO = ordSvc.updateOrd(ord_id 
						,user_id
						,store_id
						,ord_date
						,ord_total
						,ord_bill
						,ord_grant
						,ord_status
						,ord_sscore
						,ord_rpdate
						,ord_rpcomm
			            ,ord_rpstatus);
				if(ord_bill==3&&ord_grant==2&&ord_status==3){//訂單已完成所有流程
					List<ItemVO> items=itemSvc.getOneOrd_idAllItem(ord_id);
					for(ItemVO itemVO:items){
						Integer prod_id=itemVO.getProd_id();//此訂單明細_產品編號
						Integer item_qty=itemVO.getItem_qty();//此訂單明細_產品數量
						Integer prod_soldcount=prodSvc.getOneProd(prod_id).getProd_soldcount();//原售出數量
						prodSvc.update_soldcount(prod_soldcount+item_qty, prod_id);
					}
				}
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的ordVO物件,存入req
				String url = "/front-end/store_interface/listOneOrd_id.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		}
		if ("update_report".equals(action)) { // 來自update_ord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ord_id = new Integer(req.getParameter("ord_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				
				java.sql.Date ord_date = null;
				try {
					ord_date = java.sql.Date.valueOf(req.getParameter("ord_date").trim());
				} catch (IllegalArgumentException e) {
					ord_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer ord_total = new Integer(req.getParameter("ord_total").trim());
				Integer ord_bill = new Integer(req.getParameter("ord_bill").trim());
				Integer ord_grant = new Integer(req.getParameter("ord_grant").trim());
				Integer ord_status = new Integer(req.getParameter("ord_status").trim());
				Integer ord_sscore = new Integer(req.getParameter("ord_sscore").trim());
				java.sql.Date ord_rpdate =new java.sql.Date(System.currentTimeMillis());
				String ord_rpcomm=req.getParameter("ord_rpcomm").trim();
				Integer ord_rpstatus = new Integer(req.getParameter("ord_rpstatus").trim());

				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_id(ord_id);
				ordVO.setUser_id(user_id);
				ordVO.setStore_id(store_id);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_total(ord_total);
				ordVO.setOrd_bill(ord_bill);
				ordVO.setOrd_grant(ord_grant);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_sscore(ord_sscore);
				ordVO.setOrd_rpdate(ord_rpdate);
				ordVO.setOrd_rpcomm(ord_rpcomm);
				ordVO.setOrd_rpstatus(ord_rpstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/store_interface/update_ord_report.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ord_id 
						,user_id
						,store_id
						,ord_date
						,ord_total
						,ord_bill
						,ord_grant
						,ord_status
						,ord_sscore
						,ord_rpdate
						,ord_rpcomm
			            ,ord_rpstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的ordVO物件,存入req
				String url = "/front-end/store_interface/listOneOrd_id.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
		}
		
		
		if ("update".equals(action)) { // 來自update_ord_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ord_id = new Integer(req.getParameter("ord_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				
				java.sql.Date ord_date = null;
				try {
					ord_date = java.sql.Date.valueOf(req.getParameter("ord_date").trim());
				} catch (IllegalArgumentException e) {
					ord_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer ord_total = new Integer(req.getParameter("ord_total").trim());
				Integer ord_bill = new Integer(req.getParameter("ord_bill").trim());
				Integer ord_grant = new Integer(req.getParameter("ord_grant").trim());
				Integer ord_status = new Integer(req.getParameter("ord_status").trim());
				Integer ord_sscore = new Integer(req.getParameter("ord_sscore").trim());
				java.sql.Date ord_rpdate =new java.sql.Date(System.currentTimeMillis());
				String ord_rpcomm=req.getParameter("ord_rpcomm").trim();
				Integer ord_rpstatus = new Integer(req.getParameter("ord_rpstatus").trim());

				OrdVO ordVO = new OrdVO();
				ordVO.setOrd_id(ord_id);
				ordVO.setUser_id(user_id);
				ordVO.setStore_id(store_id);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_total(ord_total);
				ordVO.setOrd_bill(ord_bill);
				ordVO.setOrd_grant(ord_grant);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_sscore(ord_sscore);
				ordVO.setOrd_rpdate(ord_rpdate);
				ordVO.setOrd_rpcomm(ord_rpcomm);
				ordVO.setOrd_rpstatus(ord_rpstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/update_ord_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.updateOrd(ord_id 
						,user_id
						,store_id
						,ord_date
						,ord_total
						,ord_bill
						,ord_grant
						,ord_status
						,ord_sscore
						,ord_rpdate
						,ord_rpcomm
			            ,ord_rpstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordVO", ordVO); // 資料庫update成功後,正確的的ordVO物件,存入req
				String url = "/front-end/ord/listOneOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/ord/update_ord_input.jsp");
//				failureView.forward(req, res);
//			}
		}
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				
				java.sql.Date ord_date = null;
				try {
					ord_date = java.sql.Date.valueOf(req.getParameter("ord_date").trim());
				} catch (IllegalArgumentException e) {
					ord_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入訂單日期!");
				}
				Integer ord_total = new Integer(req.getParameter("ord_total").trim());
				Integer ord_bill = new Integer(req.getParameter("ord_bill").trim());
				Integer ord_grant = new Integer(req.getParameter("ord_grant").trim());
				Integer ord_status = new Integer(req.getParameter("ord_status").trim());
				Integer ord_sscore = new Integer(req.getParameter("ord_sscore").trim());
				java.sql.Date ord_rpdate = java.sql.Date.valueOf(req.getParameter("ord_rpdate").trim());
				String ord_rpcomm=req.getParameter("ord_rpcomm").trim();
				Integer ord_rpstatus = new Integer(req.getParameter("ord_rpstatus").trim());

				OrdVO ordVO = new OrdVO();
				ordVO.setUser_id(user_id);
				ordVO.setStore_id(store_id);
				ordVO.setOrd_date(ord_date);
				ordVO.setOrd_total(ord_total);
				ordVO.setOrd_bill(ord_bill);
				ordVO.setOrd_grant(ord_grant);
				ordVO.setOrd_status(ord_status);
				ordVO.setOrd_sscore(ord_sscore);
				ordVO.setOrd_rpdate(ord_rpdate);
				ordVO.setOrd_rpcomm(ord_rpcomm);
				ordVO.setOrd_rpstatus(ord_rpstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/addOrd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				OrdService ordSvc = new OrdService();
				ordVO = ordSvc.addOrd( user_id, store_id, ord_date,ord_total,ord_bill, ord_grant,ord_status,ord_sscore,ord_rpdate,ord_rpcomm,ord_rpstatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ord/addOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				
				/***************************2.開始刪除資料***************************************/
				OrdService ordSvc = new OrdService();
				ordSvc.deleteOrd(ord_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/ord/listAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/ord/listAllOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update_ord_sscore".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ord_sscore = new Integer(req.getParameter("ord_sscore"));
				Integer ord_id = new Integer(req.getParameter("ord_id"));
				/***************************2.開始更新資料***************************************/
				OrdService ordSvc = new OrdService();
				ProdService prodSvc=new ProdService();
				ItemService itemSvc=new ItemService();
				List<ItemVO> itemList=itemSvc.getOneOrd_idAllItem(ord_id);
				List<ProdVO> prodList=prodSvc.getAll();
				
				ordSvc.update_sscore(ord_sscore, ord_id);
				
				/***************************3.更新完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/member_interface/listOneUser_idAllOrd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member_interface/listOneUser_idAllOrd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("listOrds_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				OrdService ordSvc = new OrdService();
				List<OrdVO> list  = ordSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listOrds_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/store_interface/listOneStore_idAllOrd.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("decide_Ord_bill".equals(action)) { 
			HttpSession session = req.getSession();
			//mail寄送
			MailService mail=new MailService();
			Send send=new Send();
//			-----------------------
			Vector<OrdVO> addOrd = (Vector<OrdVO>) session.getAttribute("addOrd");
			 List<Integer> ord_ids= (List<Integer>) session.getAttribute("ord_ids");
			StringBuilder ord_group= new StringBuilder("您的訂單編號組:。");
			OrdService ordSvc=new OrdService();
			UserService userSvc=new UserService();
			//mail content
			Integer user_id =ordSvc.getOneOrd(ord_ids.get(0)).getUser_id();//會員編號
			//訂單編號組
			for(Integer ord_id:ord_ids){
				ord_group.insert(8,ord_id.toString()+"、");
			}
			UserVO userVO=userSvc.getOneUser(user_id);
			System.out.println(ord_group);//印出訂單編號組
			String messageText=userVO.getUser_lastname()+userVO.getUser_lastname()+"您的訂單已於"+addOrd.get(0).getOrd_date()+"成立，\n"+ord_group+"\n請記得於2~7天後至指定桃機商店取貨。"
			+"\n=============================================="+
			"\n----------------------謝謝您的光臨---------------"+
			"\n=============================================="+
					"\n我們的網站:ba102g3xXXX.com\n 連絡電話:03 425 7387";
            mail.sendMail("chenglin0413@gmail.com","Anytime Grip: 訂單成立通知", messageText);	
					;
			//"userVO.getUser_mobile()"
			String[] tel={"0911258102"};
			String message="您的訂單已於"+addOrd.get(0).getOrd_date()+"成立，請記得於2~7後至指定桃機商店取貨";
			send.sendMessage(tel, message);
			
				/***************************3.更新完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/eshop/OrdFinishDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
		}
		
		
	}
}
