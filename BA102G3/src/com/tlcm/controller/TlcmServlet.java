package com.tlcm.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tlcm.model.TlcmService;
import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiService;
import com.trpi.model.TrpiVO;
import com.trvl.model.TrvlService;
import com.trvl.model.TrvlVO;

public class TlcmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addTrvl.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String str = request.getParameter("user_id").trim();
				Integer user_id = null;
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請先登入");
				}else {
					user_id = new Integer(str);
				}
				user_id = new Integer(str);
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id"));
	
				java.sql.Date tlcm_date = null;
				try {
					tlcm_date = java.sql.Date.valueOf(request.getParameter("tlcm_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				}

				String tlcm_content = request.getParameter("tlcm_content").trim();
				if (tlcm_content == null || tlcm_content.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/login.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TlcmService tlcmSvc = new TlcmService();
				tlcmSvc.addTlcm(trvl_id, user_id, tlcm_date, tlcm_content); //新增留言
				TrvlService trvlSvc = new TrvlService();
				TrpiService trpiSvc = new TrpiService();
				TrvlVO trvlVO = trvlSvc.getOneTrvl(trvl_id);
				List<TlcmVO> listTlcms = tlcmSvc.getAllTlcm_For_OneTrvl(trvl_id); //取出所有留言
				List<TrpiVO> listTrpis = trpiSvc.getTrpiForOneTrvl(trvl_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				request.setAttribute("trvlVO", trvlVO); // 取出的物件,存入req
				request.setAttribute("listTrpis", listTrpis);
				request.setAttribute("listTlcms", listTlcms);
				
				String url = "/front-end/blog/listOneTrvl.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listOneTrvl.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception :"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/listOneTrvl.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("getAll_For_TrvlId".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TlcmService tlcmSvc = new TlcmService();
				List<TlcmVO> list = tlcmSvc.getAllTlcm_For_OneTrvl(trvl_id);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				request.setAttribute("list", list);
				String url = "/front-end/blog/tlcm/getTlcmsForTrvl.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交getTlcmsForTrvl.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception :"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
				failureView.forward(request, response);
			}

		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer tlcm_id = Integer.parseInt(request.getParameter("tlcm_id"));
System.out.println("tlcm_id : " + tlcm_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 刪除資料 ***************************************/
				TlcmService tlcmSvc = new TlcmService();
				tlcmSvc.deleteTlcm(tlcm_id);

				/***************************
				 * 準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/blog/tlcm/select_page.jsp";
	System.out.println(url);		
				RequestDispatcher successView = request.getRequestDispatcher(url); // 轉交select_page
				successView.forward(request, response);
		System.out.println("tlcm_id : " + tlcm_id);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception :"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
				failureView.forward(request, response);
			}

		}
		
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer tlcm_id = Integer.parseInt(request.getParameter("tlcm_id"));
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TlcmService tlcmSvc = new TlcmService();
				TlcmVO tlcmVO = tlcmSvc.getOneTlcm(tlcm_id);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				request.setAttribute("tlcmVO", tlcmVO);
				String url = "/front-end/blog/tlcm/updateTlcm_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交getTlcmsForTrvl.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception :"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer tlcm_id = Integer.parseInt(request.getParameter("tlcm_id"));
				Integer user_id = Integer.parseInt(request.getParameter("user_id"));
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id"));
	
				java.sql.Date tlcm_date = null;
				try {
					tlcm_date = java.sql.Date.valueOf(request.getParameter("tlcm_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				}

				String tlcm_content = request.getParameter("tlcm_content").trim();
				if (tlcm_content == null || tlcm_content.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/adddTlcm.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TlcmService tlcmSvc = new TlcmService();
				TlcmVO tlcmVO = tlcmSvc.updateTlcm(tlcm_id, trvl_id, user_id, tlcm_date, tlcm_content);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				request.setAttribute("tlcmVO", tlcmVO);
				String url = "/front-end/blog/tlcm/listAllTlcm.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交getTlcmsForTrvl.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("Exception :"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/tlcm/adddTlcm.jsp");
				failureView.forward(request, response);
			}

		}
	
	}

}
