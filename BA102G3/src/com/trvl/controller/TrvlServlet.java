package com.trvl.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.tlcm.model.TlcmService;
import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiService;
import com.trpi.model.TrpiVO;
import com.trvl.model.TrvlService;
import com.trvl.model.TrvlVO;
import com.user.model.UserVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TrvlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html ; UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if ("insert".equals(action)) { // 來自addTrvl.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************ 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				UserVO userVO = (UserVO)session.getAttribute("userVO");

				java.sql.Date trvl_date = null;
				try {
					trvl_date = java.sql.Date.valueOf(request.getParameter("trvl_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				}

				String trvl_loc = request.getParameter("trvl_loc").trim();
				if (trvl_loc == null || trvl_loc.trim().length() == 0) {
					errorMsgs.add("地點請勿空白");
				}

				String trvl_tittle = request.getParameter("trvl_tittle").trim();
				if (trvl_tittle == null || trvl_tittle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String trvl_content = request.getParameter("trvl_content").trim();
				if (trvl_content == null || trvl_content.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				TrvlVO trvlVO = new TrvlVO();
				trvlVO.setUser_id(userVO.getUser_id());
				trvlVO.setTrvl_tittle(trvl_tittle);
				trvlVO.setTrvl_loc(trvl_loc);
				trvlVO.setTrvl_date(trvl_date);
				trvlVO.setTrvl_content(trvl_content);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("trvlVO", trvlVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/addTrvl.jsp");
					failureView.forward(request, response);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				TrvlService trvlSvc = new TrvlService();
				TrpiService trpiSvc = new TrpiService();
				
				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						String trpi_imgfmt = getFileNameFromPart(part);
						byte[] trpi_img = new byte[in.available()];
						in.read(trpi_img);
						
						//確認有圖片再新增遊記
						Integer trvl_id = Integer.parseInt(trvlSvc.addTrvl(userVO.getUser_id(), trvl_date, trvl_tittle, trvl_loc, trvl_content));
						trpiSvc.addTrpi(trvl_id, trpi_img, trpi_imgfmt);
						in.close();
					}else if (getFileNameFromPart(part) == null) {
						errorMsgs.add("請上傳一張圖片");
						
						if (!errorMsgs.isEmpty()) {
							request.setAttribute("trvlVO", trvlVO);
							RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/addTrvl.jsp");
							failureView.forward(request, response);
							return;
						}
					}
				}
				
				/****************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/blog/listAllByUser.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/addTrvl.jsp");
				failureView.forward(request, response);
			}

		}

		if ("getOne_For_Display".equals(action)) { // 來自listAllTrvl.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer trvl_id = new Integer(request.getParameter("trvl_id").trim());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/listAllTrvl.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TrvlService trvlSvc = new TrvlService();
				TlcmService tlcmSvc = new TlcmService();
				TrpiService trpiSvc = new TrpiService();
				trvlSvc.addTrvlCount(trvl_id); 								//瀏覽數+1
				TrvlVO trvlVO = trvlSvc.getOneTrvl(trvl_id);				
				List<TlcmVO> listTlcms= tlcmSvc.getAllTlcm_For_OneTrvl(trvl_id);
				List<TrpiVO> listTrpis = trpiSvc.getTrpiForOneTrvl(trvl_id);
				
				if (trvlVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/listAllTrvl.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				session.setAttribute("trvlVO", trvlVO); // 取出的物件,存入req
				session.setAttribute("listTrpis", listTrpis);
				session.setAttribute("listTlcms", listTlcms);

				String url = "/front-end/blog/listOneTrvl.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交
																					// listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/listAllTrvl.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getAll_For_UserId".equals(action)) { // 來自listAllByUser.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				UserVO userVO = (UserVO)session.getAttribute("userVO");
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/listAllByUser.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TrvlService trvlSvc = new TrvlService();
				List<TrvlVO> list = trvlSvc.getTrvlByUserId(userVO.getUser_id());
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/listAllByUser.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/listAllByUser.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交
																					// listAllByUser.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/select_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listOneTrvl.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			request.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑
			
			String whichPage = request.getParameter("whichPage");
			request.setAttribute("whichPage", whichPage);   // 送出修改的來源網頁的第幾頁
			
			
			try{	
				String str = request.getParameter("trvl_id").trim();
				Integer trvl_id=null;
				if (str == null ) {
					errorMsgs.add("查無資料");
				} else { 
					trvl_id = new Integer(str);
				}
			
			
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/select_page.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}
			
			/***************************查詢完成,準備轉交(Send the Success view)*************/
				TrvlService trvlSvc = new TrvlService();
				TrvlVO trvlVO = trvlSvc.getOneTrvl(trvl_id);
								
				List<TrpiVO> listTrpis = trvlSvc.getTrpisByTrvlId(trvl_id);
				
				request.setAttribute("trvlVO", trvlVO);
				request.setAttribute("listTrpis", listTrpis);
				String url = "/front-end/blog/updateTrvl_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 轉交updateTrvl_input.jsp
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/updateTrvl_input.jsp");
				failureView.forward(request, response);
			}
		}		
		if ("update".equals(action)) { // 來自updateTrvl_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

		try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
				UserVO userVO = (UserVO)session.getAttribute("userVO");
				
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id").trim());
				
				java.sql.Date trvl_date = null;
				try {
					trvl_date = java.sql.Date.valueOf(request.getParameter("trvl_date").trim());
		System.out.println(trvl_date);
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				}

				String trvl_loc = request.getParameter("trvl_loc").trim();
		System.out.println(trvl_loc);
				if (trvl_loc == null || trvl_loc.trim().length() == 0) {
					errorMsgs.add("地點請勿空白");
				}

				String trvl_tittle = request.getParameter("trvl_tittle").trim();
		System.out.println(trvl_tittle);
				if (trvl_tittle == null || trvl_tittle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}

				String trvl_content = request.getParameter("trvl_content").trim();
		System.out.println(trvl_content);
				if (trvl_content == null || trvl_content.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				TrvlVO trvlVO = new TrvlVO();
				trvlVO.setUser_id(userVO.getUser_id());
				trvlVO.setTrvl_tittle(trvl_tittle);
				trvlVO.setTrvl_loc(trvl_loc);
				trvlVO.setTrvl_date(trvl_date);
				trvlVO.setTrvl_content(trvl_content);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("trvlVO", trvlVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/updateTrvl_input.jsp");
					failureView.forward(request, response);
					return;
				}
				
				Integer trpi_id = Integer.parseInt(request.getParameter("trpi_id").trim());
				
				TrvlService trvlSvc = new TrvlService();
				TrpiService trpiSvc = new TrpiService(); 
				
				
				/*************************** 2.開始新增資料 ***************************************/		
				
				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						String trpi_imgfmt = getFileNameFromPart(part);
						InputStream in = part.getInputStream();
						byte[]trpi_img = new byte[in.available()];
						in.read(trpi_img);
						in.close();
						
						trvlVO = trvlSvc.updateTrvl(trvl_id, userVO.getUser_id(), trvl_date, trvl_tittle, trvl_loc, trvl_content);
						trpiSvc.updateTrpi(trpi_id,trvl_id, trpi_img, trpi_imgfmt);
						
					}else if (getFileNameFromPart(part)==null) { //無照片,只更新遊記
						
						trvlVO = trvlSvc.updateTrvl(trvl_id, userVO.getUser_id(), trvl_date, trvl_tittle, trvl_loc, trvl_content);
						
					}
				}
	
				/*************************** 3.新增完成,準備轉交(Send the Success view)***********/
				request.setAttribute("trvlVO", trvlVO);
				String url = "/front-end/blog/listAllByUser.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/blog/updateTrvl_input.jsp");
				failureView.forward(request, response);
			}

		}

		if ("delete".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer trvl_id = Integer.parseInt(request.getParameter("trvl_id").trim());

				/*************************** 2.開始查詢資料 *****************************************/
				TrvlService trvlSvc = new TrvlService();
				trvlSvc.deleteTrvl(trvl_id);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/listAllByUser.jsp");
					failureView.forward(request, response);
					return;// 程式中斷
				}

				/***************************跳轉頁面(Send the Success view)*************/
				String url = "/front-end/blog/listAllByUser.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交
																					// listOneEmp.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/listAllByUser.jsp");
				failureView.forward(request, response);
			}
		}

	}
	
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}

}