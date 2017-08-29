package com.dish.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.dipi.model.DipiService;
import com.dipi.model.DipiVO;
import com.dish.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
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
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("dish_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//					
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
//					failureView.forward(req, res);
//					
//					return;//程式中斷
//				}
//				
//				Integer dish_id = null;
//				try {
//					dish_id = new Integer(str);
//					
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//					
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
//					failureView.forward(req, res);
//					
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				DishService dishSvc = new DishService();
//				DishVO dishVO = dishSvc.getOneDish(dish_id);
//				if (dishVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("dishVO", dishVO); // 資料庫取出的dishVO物件,存入req
//				String url = "/front-end/restaurant/dish/listOneDish.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDish.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/restaurant/dish/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllDish.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer dish_id = new Integer(req.getParameter("dish_id"));
				Integer dipi_id = new Integer(req.getParameter("dipi_id"));
				
				/***************************2.開始查詢資料****************************************/
				DishService dishSvc = new DishService();
				DipiService dipiSvc = new DipiService();
				
				DishVO dishVO = dishSvc.getOneDish(dish_id);
				DipiVO dipiVO = dipiSvc.getOneDipi(dipi_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("dishVO", dishVO);         // 資料庫取出的dishVO物件,存入req
				req.setAttribute("dipiVO", dipiVO);         // 資料庫取出的dipiVO物件,存入req
				String url = "/front-end/rest_interface/update_dish_input.jsp";
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
			Collection<Part> parts = req.getParts();
			
		
//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer dish_id = new Integer(req.getParameter("dish_id").trim());
				Integer dipi_id = new Integer(req.getParameter("dipi_id").trim());
				Integer rest_id = new Integer(req.getParameter("rest_id").trim());
				String dish_name = null;
				Integer dish_price = null;
				String dish_note = null;
				Integer dish_status = null;
				String dish_detail =  req.getParameter("dish_detail").trim();

				String str = req.getParameter("dish_price").trim();
				if(!(str.matches("^[0-9]*$"))){
					errorMsgs.add("請輸入料理單價");
				}
				dish_price= Integer.parseInt(str);
				  
				dish_status = new Integer(req.getParameter("dish_status").trim());
				if(!(dish_status==0||dish_status==1)){
						errorMsgs.add("請選擇(下架)或(上架)");
				}
				
				dish_note = req.getParameter("dish_note").trim();
				if(!(dish_note.matches("[1-5]"))){
					errorMsgs.add("請選擇料理供應時段");
				}

				dish_name = req.getParameter("dish_name").trim();
				if(dish_name.isEmpty()){
					errorMsgs.add("請輸入料理名稱");
				}
				DishVO dishVO = new DishVO();
				dishVO.setDish_id(dish_id);
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
							.getRequestDispatcher("/front-end/restaurant/dish/update_dish_input.jsp");
					
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						String filename = getFileNameFromPart(part);
						String ContentType = part.getContentType();
						DipiService dao = new DipiService();
						InputStream data = part.getInputStream();
						byte[] buf = new byte[data.available()];
						data.read(buf);
						data.close();
						
						dao.updateDipi(dish_id, filename,buf, ContentType,dipi_id);
					}
				}
				DishService dishSvc = new DishService();
				dishVO = dishSvc.updateDish(rest_id, dish_name, dish_price, dish_status, dish_detail, dish_note,dish_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("dishVO", dishVO); // 資料庫update成功後,正確的的dishVO物件,存入req
				String url ="/front-end/rest_interface/listOneRest_idAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllDish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/restaurant/dish/update_dish_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 來自addDish.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Collection<Part> parts = req.getParts(); 
			
//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String dish_name = null;
				Integer rest_id = null;
				Integer dish_price = null;
				String dish_note = null;
				Integer dish_status = null;
				
				dish_name = req.getParameter("dish_name");
				if (dish_name == null || (dish_name.trim()).length() == 0) {
					errorMsgs.add("請輸入料理名稱");
				}
				
				rest_id = new Integer(req.getParameter("rest_id").trim());
				System.out.println("248"+rest_id);
				try {
					dish_price = new Integer(req.getParameter("dish_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入料理價格");
				}
				
				dish_note = req.getParameter("dish_note").trim();
				if(!(dish_note.matches("[1-5]"))){
					errorMsgs.add("請選擇料理供應時段");
				}
				
				dish_status = new Integer(req.getParameter("dish_status").trim());
				if(!(dish_status==0||dish_status==1)){
					errorMsgs.add("請輸入選擇(下架)或(上架)");
				}

				String dish_detail = req.getParameter("dish_detail").trim();
	
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/restaurant/dish/addDish.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DishService dishSvc = new DishService();
				DipiService dipiSvc = new DipiService();
				Integer dish_id = Integer.parseInt(dishSvc.addDish(rest_id, dish_name, dish_price, dish_status, dish_detail, dish_note));
				
		System.out.println("dish_id :" + dish_id);		
				for (Part part : parts) {
					if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
						String filename = getFileNameFromPart(part);
		System.out.println("filename=" + filename); 
						String ContentType = part.getContentType();
						InputStream data = part.getInputStream();
						byte[] buf = new byte[data.available()];
						data.read(buf);
						data.close();
						dipiSvc.addDipi(dish_id, filename,buf, ContentType);
					} else if(getFileNameFromPart(part)==null) {
						errorMsgs.add("請選擇圖片上傳");
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("dishVO", dishVO); // 含有輸入格式錯誤的dishVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/front-end/restaurant/dish/addDish.jsp");
							failureView.forward(req, res);
							return;
						}
					}
					
				}
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/rest_interface/listOneRest_idAllDish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneRest_idAllDish.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/restaurant/dish/addDish.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("delete".equals(action)) { // 來自listAllDish.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer dish_id = new Integer(req.getParameter("dish_id"));
				Integer dipi_id = new Integer(req.getParameter("dipi_id"));
		System.out.println(dish_id);
		System.out.println(dipi_id);		
				/***************************2.開始刪除資料***************************************/
				DishService dishSvc = new DishService();
				DipiService dipiSvc = new DipiService();
				dipiSvc.deleteDipi(dipi_id);
				dishSvc.deleteDish(dish_id);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/		
				String url="/front-end/rest_interface/listOneRest_idAllDish.jsp";
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
