package com.store.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.store.model.*;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.user.model.UserService;
import com.user.model.UserVO;
import com.stpi.model.StpiService;
import com.stpi.model.StpiVO;


import mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println("line33");
		System.out.println(action);
		
		
		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			String successMsgs;
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("store_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓���振蝺刻��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/adminStoreQuery.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				Integer store_id = null;
				try {
					store_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��振蝺刻�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/adminStoreQuery.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_id);
				if (storeVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/adminStoreQuery.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("storeVO", storeVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/back-end/store/adminStoreListOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/adminStoreQuery.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 靘listAllEmp.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �靽格����雯��楝敺�: �����/emp/listAllEmp.jsp�� ���  ��/dept/listEmps_ByDeptno.jsp�� ��� �� /dept/listAllDept.jsp��
			
			try {
				/***************************1.��隢��****************************************/
				Integer store_id = new Integer(req.getParameter("store_id"));
				
				/***************************2.���閰Ｚ���****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_id);
								
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/
				req.setAttribute("storeVO", storeVO);         // 鞈�澈����mpVO�隞�,摮req
				String url = "/back-end/store/adminStoreUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_emp_input.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/adminStoreListAll.jsp");
				failureView.forward(req, res);
			}
		}
				
		if ("update".equals(action)) { // 靘update_emp_input.jsp�����
			
			System.out.println("entry update");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �靽格����雯��楝敺�: �����/emp/listAllEmp.jsp�� ���  ��/dept/listEmps_ByDeptno.jsp�� ��� �� /dept/listAllDept.jsp��
			
			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				String store_name = req.getParameter("store_name").trim();
				String store_time = req.getParameter("store_time").trim();
				String store_phone = req.getParameter("store_phone").trim();
				String store_describe = req.getParameter("store_describe").trim();
				Integer store_ter = new Integer(req.getParameter("store_ter").trim());
				String store_floor = req.getParameter("store_floor").trim();
				Double store_lon = null;
				try {
					store_lon = new Double(req.getParameter("store_lon").trim());
				} catch (NumberFormatException e) {
					store_lon = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				Double store_lat = null;
				try {
					store_lat = new Double(req.getParameter("store_lat").trim());
				} catch (NumberFormatException e) {
					store_lat = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				Integer store_inout = new Integer(req.getParameter("store_inout").trim());
				Integer store_count = new Integer(req.getParameter("store_count").trim());
				Integer store_score = new Integer(req.getParameter("store_score").trim());

				UserService userSvc = new UserService();
				UserVO current_userVO = userSvc.getOneUser(user_id);
				String user_passwd=current_userVO.getUser_passwd();
				Integer current_user_status=current_userVO.getUser_status();				
				Integer user_status = new Integer(req.getParameter("user_status").trim());
				
				StoreVO storeVO = new StoreVO();
				storeVO.setStore_id(store_id);
				storeVO.setUser_id(user_id);
				storeVO.setStore_name(store_name);
				storeVO.setStore_time(store_time);
				storeVO.setStore_phone(store_phone);
				storeVO.setStore_describe(store_describe);
				storeVO.setStore_ter(store_ter);
				storeVO.setStore_floor(store_floor);
				storeVO.setStore_lon(store_lon);
				storeVO.setStore_lat(store_lat);
				storeVO.setStore_inout(store_inout);
				storeVO.setStore_count(store_count);
				storeVO.setStore_score(store_score);
				
				byte[] stpi_img=null;
				String stpi_imgfmt=null;
				
				int updateImg=0;
				
				Part part=req.getPart("upfile1");
				
				String filename = getFileNameFromPart(part).trim();
				
				if ( filename.length() != 0 ){
					
					updateImg=1;
					
					InputStream in = part.getInputStream();
					stpi_img = new byte[in.available()];
					in.read(stpi_img);
					in.close();
					
					String temp[] = filename.split("[.]");				
					if(temp.length>1){
						stpi_imgfmt = temp[temp.length-1];
					} else {
						stpi_imgfmt=null; 
						errorMsgs.add("請輸入附檔名!");
					}
					
					System.out.println(stpi_imgfmt);
					
					StpiService stpiSvc=new StpiService();
					StpiVO stpiVO=stpiSvc.getOneStpiByStoreId(store_id);
					
					stpiVO.setStpi_img(stpi_img);
					stpiVO.setStpi_imgfmt(stpi_imgfmt);
					
					System.out.println(stpiVO.getStpi_id());
					System.out.println(stpiVO.getStpi_name());
					
					stpiSvc.updateStpi(stpiVO.getStpi_id(), store_id, stpiVO.getStpi_name(), stpi_img, stpi_imgfmt);
				}				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/store/adminStoreUpdate.jsp");
					failureView.forward(req, res);
					
					return; //蝔�葉�
				}
				
				/***************************2.���耨�鞈��*****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore(store_id, user_id, store_name, store_time, store_phone,store_describe,store_ter,store_floor,store_lon,store_lat,store_inout,store_count,store_score, user_status);
				
				req.setAttribute("successMsgs","successMsgs");
				
				String user_account=current_userVO.getUser_account();
				String user_lastname=current_userVO.getUser_lastname();
				String user_firstname=current_userVO.getUser_firstname();
				String user_email=current_userVO.getUser_email();				

				System.out.println("StoreServlet.java line.209 Store Member Password: "+user_passwd);				
								
				if (current_user_status!=1 && user_status==1){
					String[] args={user_account,user_passwd,user_lastname,user_firstname,user_email}; 
					MailService.main(args);
				}					
				
				
				/***************************3.靽格摰��,皞��漱(Send the Success view)*************/
				req.setAttribute("storeVO", storeVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
//				String url = "/back-end/store/adminStoreListOne.jsp";
//				String url = "/back-end/store/listOneStore.jsp";
				
				String url = requestURL;
				if ( requestURL.equals("/back-end/store/adminStoreUpdate.jsp") ) {
					url = "/back-end/store/adminStoreListAll.jsp";
				} else if (requestURL.equals("/back-end/store/adminStoreListOne.jsp")) {
					url = "/back-end/store/adminStoreListOne.jsp";
				}				
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				String url = requestURL;
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 靘addEmp.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���*************************/
				Integer user_id = new Integer(req.getParameter("user_id").trim());
				String store_name = req.getParameter("store_name").trim();
				String store_time = req.getParameter("store_time").trim();
				String store_phone = req.getParameter("store_phone").trim();
				String store_describe = req.getParameter("store_describe").trim();
				Integer store_ter = new Integer(req.getParameter("store_ter").trim());
				String store_floor = req.getParameter("store_floor").trim();
				
				Double store_lon = null;
				try {
					store_lon = new Double(req.getParameter("store_lon").trim());
				} catch (NumberFormatException e) {
					store_lon = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				
				Double store_lat = null;
				try {
					store_lat = new Double(req.getParameter("store_lat").trim());
				} catch (NumberFormatException e) {
					store_lat = 0.0;
					errorMsgs.add("經緯度請填數字.");
				}
				
				Integer store_inout = new Integer(req.getParameter("store_inout").trim());
				Integer store_count = new Integer(req.getParameter("store_count").trim());
				Integer store_score = new Integer(req.getParameter("store_score").trim());
				
				StoreVO storeVO = new StoreVO();
				storeVO.setUser_id(user_id);
				storeVO.setStore_name(store_name);
				storeVO.setStore_time(store_time);
				storeVO.setStore_phone(store_phone);
				storeVO.setStore_describe(store_describe);
				storeVO.setStore_ter(store_ter);
				storeVO.setStore_floor(store_floor);
				storeVO.setStore_lon(store_lon);
				storeVO.setStore_lat(store_lat);
				storeVO.setStore_inout(store_inout);
				storeVO.setStore_count(store_count);
				storeVO.setStore_score(store_score);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.addStore(store_name, store_time, store_phone,store_describe,store_ter,store_floor,store_lon,store_lat,store_inout,store_count,store_score);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				String url = "/back-end/store/adminStoreListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/adminStoreAdd.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 靘listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.��隢��***************************************/
				Integer store_id = new Integer(req.getParameter("store_id"));
				Integer user_id = new Integer(req.getParameter("user_id"));
				
				/***************************2.����鞈��***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.deleteStore(store_id);

				UserService userSvc = new UserService();
				userSvc.deleteUser(user_id);
				
				/***************************3.��摰��,皞��漱(Send the Success view)***********/								
				String url = "/back-end/store/adminStoreListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/store/adminStoreListAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("seeOneStoredetail".equals(action)) { // 靘select_page.jsp�����
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			HttpSession session=req.getSession();
//			try {
//				String str = req.getParameter("store_id");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("隢撓���振蝺刻��");
//				}
//				// Send the use back to the form, if there were errors
//				Integer store_id = null;
//				try {
//					store_id = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("��振蝺刻�撘�迤蝣�");
//				}
//				// Send the use back to the form, if there were errors
//				
//				/***************************2.���閰Ｚ���*****************************************/
//				StoreService storeSvc = new StoreService();
//				StoreVO storeVO = storeSvc.getOneStore(store_id);
//				
//				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
//				session.setAttribute("storeVO", storeVO); // 鞈�澈����mpVO�隞�,摮req
//				String url = "/front-end/member_interface/listOneStore_detail.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************�隞���隤方���*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�瘜�����:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/store/adminStoreQuery.jsp");
//				failureView.forward(req, res);
//			}
//		}
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
