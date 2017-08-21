package com.path.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.path.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PathServlet extends HttpServlet {

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
				String str = req.getParameter("path_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
//					.getRequestDispatcher("/back-end/user/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer path_id = null;
				try {
					path_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				PathService pathSvc = new PathService();
				PathVO pathVO = pathSvc.getOnePath(path_id);
				if (pathVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pathVO", pathVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/path/adminPathListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_From_To_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String path_fromplace = req.getParameter("path_fromplace").trim();
				String path_toplace = req.getParameter("path_toplace").trim();
				
				/***************************2.開始查詢資料*****************************************/
				PathService pathSvc = new PathService();
				PathVO pathVO = pathSvc.getOnePathFromTo(path_fromplace, path_toplace);
				
				if (pathVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pathVO", pathVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/path/adminPathListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/path/adminPathQuery.jsp");
				failureView.forward(req, res);
			}
		}		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer path_id = new Integer(req.getParameter("path_id"));
				
				/***************************2.開始查詢資料****************************************/
				PathService pathSvc = new PathService();
				PathVO pathVO = pathSvc.getOnePath(path_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pathVO", pathVO);         // 資料庫取出的empVO物件,存入req
				
				String url = "/back-end/path/adminPathUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("更新資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/path/adminPathListAll.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】
					
			System.out.println("PathServlet line.175: "+requestURL);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				Integer path_id = new Integer(req.getParameter("path_id"));

				Integer path_term = new Integer(req.getParameter("path_term"));
								
				Double path_fromlon = new Double(req.getParameter("path_fromlon"));
				
				Double path_fromlat = new Double(req.getParameter("path_fromlat"));
				
				Double path_tolon = new Double(req.getParameter("path_tolon"));
				
				Double path_tolat = new Double(req.getParameter("path_tolat"));
				
				String path_fromplace = req.getParameter("path_fromplace");
				
				String path_toplace = req.getParameter("path_toplace");
												
				PathVO pathVO = new PathVO();
				pathVO.setPath_id(path_id);
				pathVO.setPath_term(path_term);
				pathVO.setPath_fromlon(path_fromlon);
				pathVO.setPath_fromlat(path_fromlat);
				pathVO.setPath_tolon(path_tolon);
				pathVO.setPath_tolat(path_tolat);
				pathVO.setPath_fromplace(path_fromplace);
				pathVO.setPath_toplace(path_toplace);
				
				byte[] path_img=null;
				String path_imgfmt=null;
				
				int updateImg=0;
				
				Part part=req.getPart("upfile1");			
				String filename = getFileNameFromPart(part).trim();								
				if ( filename.length() != 0 ){

					updateImg=1;
					
					InputStream in = part.getInputStream();
					path_img = new byte[in.available()];
					in.read(path_img);
					in.close();
					
					String temp[] = filename.split("[.]");				
					if(temp.length>1){
						path_imgfmt = temp[temp.length-1];
					} else {
						path_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}
					
					pathVO.setPath_img(path_img);
					pathVO.setPath_imgfmt(path_imgfmt);
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pathVO", pathVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/path/adminPathUpdate.jsp");
//					.getRequestDispatcher("/back-end/user/update_user_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				PathService pathSvc = new PathService();
				
				pathVO = pathSvc.updatePath(path_id, path_term, path_fromlon, path_fromlat, path_tolon,
						path_tolat, path_fromplace, path_toplace, path_img, path_imgfmt, updateImg);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pathVO", pathVO); // 資料庫update成功後,正確的的empVO物件,存入req
								
				String url = requestURL;
				if ( requestURL.equals("/back-end/path/adminPathListAll.jsp") ) {
					url = "/back-end/path/adminPathListAll.jsp";
				} else if (requestURL.equals("/back-end/path/adminPathListOne.jsp")) {
					url = "/back-end/path/adminPathListOne.jsp";
				} else if (requestURL.equals("/back-end/path/adminPathUpdate.jsp")) {
					url = "/back-end/path/adminPathListAll.jsp";
				}
				
				req.setAttribute("successMsgs", "successMsgs");
				System.out.println("pass PathServlet.java line 219");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("更新失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/path/adminPathUpdate.jsp");
//				.getRequestDispatcher("/back-end/user/update_user_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 新增  
        	
        	List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");
			System.out.println("line 241: "+requestURL);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
																
				Integer path_term = new Integer(req.getParameter("path_term"));
				
				Double path_fromlon = new Double(req.getParameter("path_fromlon"));
				Double path_fromlat = new Double(req.getParameter("path_fromlat"));

				Double path_tolon = new Double(req.getParameter("path_tolon"));
				Double path_tolat = new Double(req.getParameter("path_tolat"));
				
				String path_fromplace = req.getParameter("path_fromplace").trim();
				String path_toplace = req.getParameter("path_toplace").trim();
								
				byte[] path_img =null;
				String path_imgfmt=null;
				Part part=req.getPart("upfile1");				
				String filename = getFileNameFromPart(part).trim();
								
				if ( filename.length() != 0 ){
					
					InputStream in = part.getInputStream();
					path_img = new byte[in.available()];
					in.read(path_img);
					in.close();
					
					String temp[] = filename.split("[.]");				
					if(temp.length>1){
						path_imgfmt = temp[temp.length-1];
					} else {
						path_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}					
				}
								
				PathVO pathVO = new PathVO();
				pathVO.setPath_term(path_term);
				pathVO.setPath_fromlon(path_fromlon);
				pathVO.setPath_fromlat(path_fromlat);
				pathVO.setPath_tolon(path_tolon);
				pathVO.setPath_tolat(path_tolat);
				pathVO.setPath_fromplace(path_fromplace);
				pathVO.setPath_toplace(path_toplace);
				pathVO.setPath_img(path_img);
				pathVO.setPath_imgfmt(path_imgfmt);			
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pathVO", pathVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
//							.getRequestDispatcher("/back-end/user/addUser.jsp");
					failureView.forward(req, res);
					return;
				}
								
				/***************************2.開始新增資料***************************************/
				PathService pathSvc = new PathService();
				pathVO = pathSvc.addPath(path_term, path_fromlon, path_fromlat, path_tolon, path_tolat,
						 path_fromplace, path_toplace, path_img, path_imgfmt);
																												
				/***************************3.新增完成,準備轉交(Send the Success view)***********/

				req.setAttribute("successMsgs", "successMsgs");
				
				String url=requestURL;
			
				System.out.println("line.309"+url);
				
				if (requestURL.equals("/front-end/path/newPath.jsp")){
					url = "/front-end/path/newPath.jsp";
				}
				
				if (requestURL.equals("/back-end/path/adminPathAdd.jsp")){
					url = "/back-end/path/adminPathListAll.jsp";
				} 
			
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				String url = req.getParameter("requestURL");
				System.out.println("line.326"+url);
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
//						.getRequestDispatcher("/back-end/user/addUser.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】			
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer path_id = new Integer(req.getParameter("path_id"));
				
				/***************************2.開始刪除資料***************************************/
				PathService pathSvc = new PathService();
//				UserVO empVO = userSvc.getOneUser(user_id);
				pathSvc.deletePath(path_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
								
//				String url = "/back-end/user/adminUserListAll.jsp";
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除錯誤:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/path/adminPathListAll.jsp");
//  				.getRequestDispatcher("/back-end/user/listAllUser.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// 讀出檔名
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 輸出測試
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 輸出測試
		if (filename.length() == 0) {
//			return null;
		}
		return filename;
	}	

}
