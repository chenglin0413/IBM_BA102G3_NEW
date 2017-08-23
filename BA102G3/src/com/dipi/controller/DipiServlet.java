package com.dipi.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.dipi.model.DipiVO;
import com.dish.model.DishService;
import com.dish.model.DishVO;
import com.dipi.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class DipiServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF8"); // 處理中文檔名
		res.setContentType("text/html; charset=utf8");
		
		String action = req.getParameter("action");

		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		
		if ("insert".equals(action)) {
			Integer dish_id = new Integer(req.getParameter("dish_id"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
					System.out.println("3");
					//out.println("<PRE>");
					String name = part.getName();
					String filename = getFileNameFromPart(part);
					//這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
					String ContentType = part.getContentType();
					long size = part.getSize();
					DipiService dao = new DipiService();
					InputStream data = part.getInputStream();
					byte[] buf = new byte[data.available()];
					data.read(buf);
					System.out.println(buf);
				
					DipiVO dipiVO = new DipiVO();
					
					dipiVO=dao.addDipi(dish_id, filename,buf, ContentType);
					
				}
				else if(part.getSize() == 0){
					
					errorMsgs.add("請選擇圖片上傳");
					//要傳給下一頁 一定要在一次req.setAttyibute
					req.setAttribute("dish_id", dish_id);
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/addDipi.jsp");
					failureView.forward(req, res);
					return;
					
				}
				else{
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/listAllDipi.jsp");
					failureView.forward(req, res);
					return;
				}

			}
		}
		
		if ("getOne_For_Update".equals(action)){
							
					Integer dish_id = new Integer(req.getParameter("dish_id"));
					
					Integer dipi_id = new Integer(req.getParameter("dipi_id"));
					req.setAttribute("dish_id", dish_id);
					
					req.setAttribute("dipi_id", dipi_id);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/update_dipi_input.jsp");
					
					failureView.forward(req, res);
					return;	
			
		}
			
		if ("update".equals(action)) {
			Integer dish_id = new Integer(req.getParameter("dish_id"));
			Integer dipi_id = new Integer(req.getParameter("dipi_id"));
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			for (Part part : parts) {
				if (getFileNameFromPart(part) != null && part.getContentType()!=null) {
					System.out.println("3");
					//out.println("<PRE>");
					String name = part.getName();
					String filename = getFileNameFromPart(part);
					//這裡只有IE能檢察出檔案實際內容，chrome檢查不出 只會秀出檔案的副檔名
					String ContentType = part.getContentType();
					long size = part.getSize();
					DipiService dao = new DipiService();
					InputStream data = part.getInputStream();
					byte[] buf = new byte[data.available()];
					data.read(buf);
					System.out.println(buf);
				
					DipiVO dipiVO = new DipiVO();
					
					dipiVO=dao.updateDipi(dish_id, filename,buf, ContentType,dipi_id);
					
				}
				else if(part.getSize() == 0){
					
					errorMsgs.add("請選擇圖片上傳");
					//要傳給下一頁 一定要在一次req.setAttyibute
					req.setAttribute("dish_id", dish_id);
					req.setAttribute("dipi_id", dipi_id);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/update_dipi_input.jsp");
					failureView.forward(req, res);
					return;
					
				}
				else{
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/listAllDipi.jsp");
					failureView.forward(req, res);
					return;
				}

			}
		}
		
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer dipi_id = new Integer(req.getParameter("dipi_id"));
				
				/***************************2.開始刪除資料***************************************/
				DipiService dipiSvc = new DipiService();
				dipiSvc.deleteDipi(dipi_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/restaurant/dipi/listAllDipi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dipi/listAllDipi.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Display".equals(action)){
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("dipi_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入料理圖片編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer dipi_id = null;
				try {
					String id = str.trim();
					if(!(id.matches("[3][3][0-9]{4}[0-9]"))){
						errorMsgs.add("請填數字3300001寫法");
						dipi_id = new Integer(id);
					}
				} catch (Exception e) {
					
					errorMsgs.add("請填數字3300001寫法");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				DipiService dipiSvc = new DipiService();
				dipi_id = new Integer(req.getParameter("dipi_id"));
				
				
				DipiVO dipiVO = dipiSvc.getOneDipi(dipi_id);
				
				if (dipiVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/dipi/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("dipiVO", dipiVO); // 資料庫取出的dishVO物件,存入req
				String url = "/front-end/restaurant/dipi/listOneDipi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dipi/select_page.jsp");
				failureView.forward(req, res);
			}
	
		}
		
		if ("getDish_For_Display".equals(action)){
			
			try {
				DipiService dipiSvc = new DipiService();
				List<DipiVO> listDipi = new ArrayList<DipiVO>();
				List<DipiVO> list = dipiSvc.getAll();
				Integer dish_id = new Integer(req.getParameter("dish_id"));
				int c = 0;		
				for(DipiVO aDipiVO:list){
					
					if(aDipiVO.getDish_id().equals(dish_id)){
						
						listDipi.add(aDipiVO);
						c++;
						System.out.println(c);
					}
				}
				HttpSession session = req.getSession();
				session.setAttribute("listDipi", listDipi);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/dipi/listDipiOnDish.jsp");
				failureView.forward(req, res);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
			
			
		}
		
	
	}

	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
