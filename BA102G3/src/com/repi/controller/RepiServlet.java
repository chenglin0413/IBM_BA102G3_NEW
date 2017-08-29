package com.repi.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.repi.model.RepiService;
import com.repi.model.RepiVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

@WebServlet("/RepiServlet")
public class RepiServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF8"); // 處理中文檔名
		res.setContentType("text/html; charset=utf8");
		
		String action = req.getParameter("action");
		
		System.out.println(getServletContext().getServerInfo());
		System.out.println("ContentType="+req.getContentType()); // 測試用

		
		Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
		
		if ("insert".equals(action)) {
			Integer rest_id = new Integer(req.getParameter("rest_id"));
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
					RepiService dao = new RepiService();
					InputStream data = part.getInputStream();
					byte[] buf = new byte[data.available()];
					data.read(buf);
					System.out.println(buf);
				
					RepiVO repiVO = new RepiVO();
					
					repiVO=dao.addrepi(rest_id, filename,buf, ContentType);
					
				}
				else if(part.getSize() == 0){
					
					errorMsgs.add("請選擇圖片上傳");
					//要傳給下一頁 一定要在一次req.setAttyibute
					req.setAttribute("rest_id", rest_id);
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/addRepi.jsp");
					failureView.forward(req, res);
					return;
					
				}
				else{
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/listAllRepi.jsp");
					failureView.forward(req, res);
					return;
				}

			}
		}
		
		if ("getOne_For_Update".equals(action)){
							
					Integer rest_id = new Integer(req.getParameter("rest_id"));
					
					Integer repi_id = new Integer(req.getParameter("repi_id"));
					req.setAttribute("rest_id", rest_id);
					
					req.setAttribute("repi_id", repi_id);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/update_repi_input.jsp");
					
					failureView.forward(req, res);
					return;	
			
		}
			
		if ("update".equals(action)) {
			Integer rest_id = new Integer(req.getParameter("rest_id"));
			Integer repi_id = new Integer(req.getParameter("repi_id"));
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
					RepiService dao = new RepiService();
					InputStream data = part.getInputStream();
					byte[] buf = new byte[data.available()];
					data.read(buf);
					System.out.println(buf);
				
					RepiVO repiVO = new RepiVO();
					
					repiVO=dao.updaterepi(rest_id, filename,buf, ContentType,repi_id);
					
				}
				else if(part.getSize() == 0){
					
					errorMsgs.add("請選擇圖片上傳");
					//要傳給下一頁 一定要在一次req.setAttyibute
					req.setAttribute("rest_id", rest_id);
					req.setAttribute("repi_id", repi_id);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/update_repi_input.jsp");
					failureView.forward(req, res);
					return;
					
				}
				else{
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/listAllRepi.jsp");
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
				Integer repi_id = new Integer(req.getParameter("repi_id"));
				
				/***************************2.開始刪除資料***************************************/
				RepiService repiSvc = new RepiService();
				repiSvc.deleterepi(repi_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/restaurant/repi/listAllRepi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/repi/listAllRepi.jsp");
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
				String str = req.getParameter("repi_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳圖片編號");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				Integer repi_id = null;
				try {
					String id = str.trim();
					if(!(id.matches("[3][1][0-9]{4}[0-9]"))){
						errorMsgs.add("請填數字3100001寫法");
						repi_id = new Integer(id);
					}
				} catch (Exception e) {
					
					errorMsgs.add("請填數字3100001寫法");
					
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/select_page.jsp");
					failureView.forward(req, res);
					
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RepiService repiSvc = new RepiService();
				repi_id = new Integer(req.getParameter("repi_id"));
				
				
				RepiVO repiVO = repiSvc.getOnerepi(repi_id);
				
				if (repiVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/restaurant/repi/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				req.setAttribute("repiVO", repiVO); // 資料庫取出的dishVO物件,存入req
				String url = "/front-end/restaurant/repi/listOneRepi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneDish.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/repi/select_page.jsp");
				failureView.forward(req, res);
			}
	
		}
		
		if ("getDish_For_Display".equals(action)){
			
			try {
				RepiService repiSvc = new RepiService();
				List<RepiVO> listrepi = new ArrayList<RepiVO>();
				List<RepiVO> list = repiSvc.getAll();
				Integer rest_id = new Integer(req.getParameter("rest_id"));
				int c = 0;		
				for(RepiVO arepiVO:list){
					
					if(arepiVO.getRest_id().equals(rest_id)){
						
						listrepi.add(arepiVO);
						c++;
						System.out.println(c);
					}
				}
				HttpSession session = req.getSession();
				session.setAttribute("listrepi", listrepi);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/restaurant/repi/listRepiOnRest.jsp");
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
