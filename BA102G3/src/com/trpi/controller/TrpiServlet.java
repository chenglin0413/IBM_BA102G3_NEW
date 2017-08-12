package com.trpi.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.trpi.model.TrpiDAO;
import com.trpi.model.TrpiService;
import com.trpi.model.TrpiVO;
import com.trvl.model.TrvlDAO;
import com.trvl.model.TrvlVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TrpiServlet extends HttpServlet {
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
		response.setCharacterEncoding("text/html; charset=utf8");
		String action = request.getParameter("action");

		
		if ("insert".equals(action)) { // 來自addTrpi.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String str = request.getParameter("trvl_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trvl/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				Integer trvl_id = null;
				try {
					trvl_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("遊記編號格式不正確");
				}

				java.sql.Date trpi_date = null;
				try {
					trpi_date = java.sql.Date.valueOf(request.getParameter("trpi_date").trim());
				} catch (IllegalArgumentException e) {
					trpi_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Collection<Part> parts = request.getParts();
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType() != null) {
						// 這裡只有IE能查出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
						String contentType = part.getContentType();
						String trpi_imgfmt = contentType.substring(contentType.indexOf("/") + 1);
						System.out.println("副檔名 :" + trpi_imgfmt);

						InputStream data = part.getInputStream();
						byte[] trpi_img = new byte[data.available()];
						data.read(trpi_img);
						data.close();

						/*************************** 2.開始新增資料 ***************************************/
						TrpiService trpiSvc = new TrpiService();
						TrpiVO trpiVO = trpiSvc.addTrpi(trvl_id, trpi_img, trpi_imgfmt);
System.out.println(trpiVO.getTrpi_id());
						request.setAttribute("trpiVO", trpiVO);
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/trpi/addTrpi.jsp");
					failureView.forward(request, response);
					return;
				}

				/****************************3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/blog/trpi/listAllTrpi.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllTrpi.jsp
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add( e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/blog/trpi/addTrpi.jsp");
				failureView.forward(request, response);
			}

		}

		if ("getTrpisForTrvl".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("trvl_id");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸遊記編號");
				}
				Integer trvl_id = null;
				try {
					trvl_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("遊記編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				TrpiService trpiSvc = new TrpiService();
		
				List<TrpiVO> list = trpiSvc.getTrpiForOneTrvl(trvl_id);

				if ( list == null) {
					errorMsgs.add("查無資料");
				}	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
		
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/trpi/listAllForOneTrvl.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);

				try {
					/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
					String str = request.getParameter("trpi_id");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸遊記編號");
					}
					Integer trpi_id = null;
					try {
						trpi_id = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("遊記編號格式不正確");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = request
								.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
						failureView.forward(request, response);
						return;//程式中斷
					}

					
					/***************************2.開始查詢資料*****************************************/
					TrpiService trpiSvc = new TrpiService();
					TrpiVO trpiVO = trpiSvc.getOneTrpi(trpi_id);
					if (trpiVO == null) {
						errorMsgs.add("查無資料");
					}	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = request
								.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
						failureView.forward(request, response);
						return;//程式中斷
			
					}
					
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					request.setAttribute("trpiVO",trpiVO); // 資料庫取出的empVO物件,存入req
					String url = "/front-end/blog/trpi/listOneTrpi.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(request, response);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trpi/select_page.jsp");
					failureView.forward(request, response);
				}
			}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer trpi_id = new Integer(request.getParameter("trpi_id"));
			System.out.println("1111" + trpi_id);	
				/***************************2.開始查詢資料****************************************/
				TrpiService trpiSvc = new TrpiService();
				TrpiVO trpiVO = trpiSvc.getOneTrpi(trpi_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("trpiVO", trpiVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/trpi/updateTrpi.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/emp/listAllTrpi.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("update".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("trvl_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸遊記編號");
				}
				Integer trvl_id = null;
				try {
					trvl_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("遊記編號格式不正確");
				}
				
				String str2 = request.getParameter("trpi_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸照片編號");
				}
				Integer trpi_id = null;
				try {
					trpi_id = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("遊記編號格式不正確");
				}
				
				java.sql.Date trpi_date = null;
				try {
					trpi_date = java.sql.Date.valueOf(request.getParameter("trpi_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期");
				}
				Collection<Part> parts = request.getParts();
				TrpiVO trpiVO = null;
				
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						System.out.println("3");
						//out.println("<PRE>");

						String trpi_imgfmt = getFileNameFromPart(part);
				System.out.println("trpi_imgfmt :" + trpi_imgfmt);	
						//這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
						String ContentType = part.getContentType();
						TrpiService trpiSvc = new TrpiService();
						InputStream data = part.getInputStream();
						byte[] trpi_img = new byte[data.available()];
						data.read(trpi_img);
						data.close();		
						trpiVO = trpiSvc.updateTrpi(trpi_id, trvl_id, trpi_img, trpi_imgfmt);					
						
					}
				}							
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trpi/updateTrpi.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}

				
				/***************************2.開始查詢資料*****************************************/
				if (trpiVO == null) {
					errorMsgs.add("查無資料");
				}	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/blog/trpi/updateTrpi.jsp");
					failureView.forward(request, response);
					return;//程式中斷
		
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("trpiVO",trpiVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/blog/trpi/listOneTrpi.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/blog/trpi/updateTrpi.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer trpi_id = new Integer(request.getParameter("trpi_id"));
				
				/***************************2.開始刪除資料***************************************/
				TrpiService trpiSvc = new TrpiService();
				trpiSvc.deleteTrpi(trpi_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/blog/trpi/listAllTrpi.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/front-end/blog/trpi/listAllTrpi.jsp");
				failureView.forward(request, response);
			}
		}
					
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("檔案名稱  :" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
