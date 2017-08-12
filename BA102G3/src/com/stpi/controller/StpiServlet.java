package com.stpi.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.stpi.model.*;
import com.stpi.model.StpiService;
import com.stpi.model.StpiVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StpiServlet extends HttpServlet {

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
				String str = req.getParameter("stpi_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廠商圖片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/stpi/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer stpi_id = null;
				try {
					stpi_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廠商圖片格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/stpi/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				StpiService stpiSvc = new StpiService();
				StpiVO stpiVO = stpiSvc.getOneStpi(stpi_id);
				if (stpiVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/stpi/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("stpiVO", stpiVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/stpi/listOneStpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/stpi/select_page.jsp");
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
				Integer stpi_id = new Integer(req.getParameter("stpi_id"));
				
				/***************************2.開始查詢資料****************************************/
				StpiService stpiSvc = new StpiService();
				StpiVO stpiVO = stpiSvc.getOneStpi(stpi_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("stpiVO", stpiVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front-end/stpi/update_stpi_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/stpi/listAllStpi.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer stpi_id = new Integer(req.getParameter("stpi_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				Integer stpi_name = new Integer(req.getParameter("stpi_name").trim());
				String stpi_imgfmt = req.getParameter("stpi_imgfmt").trim();
				Part partpic=req.getPart("stpi_img");
				if(partpic==null){
					System.out.println("null");
				}
				BufferedInputStream bis= new BufferedInputStream(partpic.getInputStream());
				byte[] stpi_img = new byte[bis.available()];
				bis.read(stpi_img);				
				bis.close();
				
				StpiVO stpiVO = new StpiVO();
				stpiVO.setStpi_id(stpi_id);
				stpiVO.setStore_id(store_id);
				stpiVO.setStpi_name(stpi_name);
				stpiVO.setStpi_imgfmt(stpi_imgfmt);
				stpiVO.setStpi_img(stpi_img);

//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("stpiVO", stpiVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/stpi/update_stpi_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				StpiService stpiSvc = new StpiService();
				
				StpiVO sptiVO = stpiSvc.updateStpi(stpi_id, store_id, stpi_name, stpi_img, stpi_imgfmt);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("stpiVO", stpiVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/stpi/listOneStpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/stpi/update_stpi_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				Integer  stpi_name = new Integer(req.getParameter("stpi_name").trim());
				String stpi_imgfmt =req.getParameter("stpi_imgfmt").trim();
				Part partpic=req.getPart("stpi_img");
				if(partpic==null){
					System.out.println("null");
				}
				BufferedInputStream bis= new BufferedInputStream(partpic.getInputStream());
				byte[] stpi_img = new byte[bis.available()];
				bis.read(stpi_img);
				bis.close();
				
				
				
				StpiVO stpiVO = new StpiVO();
				stpiVO.setStore_id(store_id);
				stpiVO.setStpi_name(stpi_name);
				stpiVO.setStpi_imgfmt(stpi_imgfmt);
				stpiVO.setStpi_img(stpi_img);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("stpiVO", stpiVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/stpi/addStpi.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
			
				StpiService stpiSvc = new StpiService();
				stpiVO = stpiSvc.addStpi(store_id, stpi_name, stpi_img, stpi_imgfmt);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				System.out.println("成功");
				String url = "/front-end/stpi/listAllStpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/stpi/addStpi.jsp");
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
				Integer stpi_id = new Integer(req.getParameter("stpi_id"));
				
				/***************************2.開始刪除資料***************************************/
				StpiService stpiSvc = new StpiService();
				stpiSvc.deleteStpi(stpi_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/stpi/listAllStpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/stpi/listAllStpi.jsp");
				failureView.forward(req, res);
			}
		}
	}
	//顯示圖片
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
	//接上傳圖片
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println(header);
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
