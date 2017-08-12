package com.prod.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.prod.model.*;
import com.prpi.model.PrpiService;
import com.prpi.model.PrpiVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session=req.getSession();
			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String str = req.getParameter("prod_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入產品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer prod_id = null;
			try {
				prod_id = new Integer(str);
			} catch (Exception e) {
				errorMsgs.add("產品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prod_id);
			if (prodVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			session.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
			String url = "/front-end/eshop/EShop.jsp";// 顯示圖片的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																			// listOneProd.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/front-end/prod/select_page.jsp");
			// failureView.forward(req, res);
			// }
		}
		// 商家介面_顯示全部產品
		if ("getOne_For_Display_Store_interface".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String str = req.getParameter("prod_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入產品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer prod_id = null;
			try {
				prod_id = new Integer(str);
			} catch (Exception e) {
				errorMsgs.add("產品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prod_id);
			if (prodVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("prodVO", prodVO); // 資料庫取出的prodVO物件,存入req
			String url = "/front-end/store_interface/listOneProd_forStore.jsp";// 顯示圖片的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																			// listOneProd.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/front-end/prod/select_page.jsp");
			// failureView.forward(req, res);
			// }
		}

		// 會員介面_顯示其中一個分類
		if ("getOneSort_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer xx = new Integer(req.getParameter("prod_sort"));
			String prod_sort = null;
			// String prod_sort=new String(name.getBytes("ISO-8859-1"),"UTF-8");
			if (xx == 3) {
				prod_sort = "伴手禮";
			} else if (xx == 4) {
				prod_sort = "酒類";
			} else if (xx == 5) {
				prod_sort = "文具用品";
			} else if (xx == 6) {
				prod_sort = "生活用品";
			} else if (xx == 7) {
				prod_sort = "化妝品";
			} else if (xx == 8) {
				prod_sort = "精品";
			}
			// System.out.println(prod_sort);
			/*************************** 2.開始查詢資料 *****************************************/
			ProdService prodSvc = new ProdService();
			List<ProdVO> oneprodsortlist = prodSvc.getOneProdSort(prod_sort);

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			session.setAttribute("oneprodsortlist", oneprodsortlist); // 資料庫取出的prodVO物件,存入req
			String url = "/front-end/member_interface/listOneProdSort.jsp";// 顯示圖片的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																			// listOneProd.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		}
		// 顯示其中一個航廈,
		if ("getOneStoreTer_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			HttpSession session = req.getSession();
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer store_ter = new Integer(req.getParameter("store_ter"));
			/*************************** 2.開始查詢資料 *****************************************/

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			session.setAttribute("store_ter", store_ter);
			String url = "/front-end/member_interface/listOneStoreTer.jsp";// 顯示圖片的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																			// listOneProd.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				String prod_name = req.getParameter("prod_name").trim();
				String prod_descript = req.getParameter("prod_descript").trim();
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				String prod_sort = req.getParameter("prod_sort").trim();
				String prod_format = req.getParameter("prod_format").trim();
				String prod_brand = req.getParameter("prod_brand").trim();
				java.sql.Date prod_updatetime = null;
				try {
					prod_updatetime = java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
				} catch (IllegalArgumentException e) {
					prod_updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入產品更新日期!");
				}

				Integer prod_soldcount = new Integer(req.getParameter("prod_soldcount").trim());
				Integer prod_status = new Integer(req.getParameter("prod_status").trim());
				Integer prod_count = new Integer(req.getParameter("prod_count").trim());
				Integer prod_score = new Integer(req.getParameter("prod_score").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setProd_id(prod_id);
				prodVO.setStore_id(store_id);
				prodVO.setProd_name(prod_name);
				prodVO.setProd_descript(prod_descript);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_sort(prod_sort);
				prodVO.setProd_format(prod_format);
				prodVO.setProd_brand(prod_brand);
				prodVO.setProd_updatetime(prod_updatetime);
				prodVO.setProd_soldcount(prod_soldcount);
				prodVO.setProd_status(prod_status);
				prodVO.setProd_count(prod_count);
				prodVO.setProd_score(prod_score);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/update_prod_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.updateProd(prod_id, store_id, prod_name, prod_descript, prod_price, prod_sort,
						prod_format, prod_brand, prod_updatetime, prod_soldcount, prod_status, prod_count, prod_score);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("prodVO", prodVO); // 資料庫update成功後,正確的的prodVO物件,存入req
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/update_prod_input.jsp");
				failureView.forward(req, res);
			}
		}

		// store會員更新產品
		if ("getOne_prodChange".equals(action)) {

			/*************************** 1.接收請求參數 ****************************************/
			Integer prod_id = new Integer(req.getParameter("prod_id"));
			System.out.println(prod_id);
			/*************************** 2.開始查詢資料 ****************************************/
			ProdService prodSvc = new ProdService();
			ProdVO prodVO = prodSvc.getOneProd(prod_id);

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
			req.setAttribute("prodVO", prodVO); // 資料庫取出的ordVO物件,存入req
			String url = "/front-end/store_interface/update_prod_prodChange.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);//
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		}
		// if ("update_prodChange".equals(action)) { //
		// 來自update_ord_input.jsp的請求
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// // Store this set in the request scope, in case we need to
		// // send the ErrorPage view.
		// req.setAttribute("errorMsgs", errorMsgs);
		// String requestURL = req.getParameter("requestURL");
		//// try {
		// /***************************1.接收請求參數 -
		// 輸入格式的錯誤處理**********************/
		// Integer prod_id = new Integer(req.getParameter("prod_id").trim());
		// Integer store_id = new Integer(req.getParameter("store_id").trim());
		// String prod_name = req.getParameter("prod_name").trim();
		// String prod_descript = req.getParameter("prod_descript").trim();
		// Integer prod_price = new
		// Integer(req.getParameter("prod_price").trim());
		// String prod_sort = req.getParameter("prod_sort").trim();
		// String prod_format = req.getParameter("prod_format").trim();
		// String prod_brand = req.getParameter("prod_brand").trim();
		// java.sql.Date prod_updatetime=null;
		//
		// try {
		// prod_updatetime =
		// java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
		// } catch (IllegalArgumentException e) {
		// prod_updatetime=new java.sql.Date(System.currentTimeMillis());
		// errorMsgs.add("請輸入產品更新日期!");
		// }
		//
		// Integer prod_soldcount = new
		// Integer(req.getParameter("prod_soldcount").trim());
		// Integer prod_status = new
		// Integer(req.getParameter("prod_status").trim());
		// Integer prod_count = new
		// Integer(req.getParameter("prod_count").trim());
		// Integer prod_score = new
		// Integer(req.getParameter("prod_score").trim());
		//
		// ProdVO prodVO = new ProdVO();
		// prodVO.setProd_id(prod_id);
		// prodVO.setStore_id(store_id);
		// prodVO.setProd_name(prod_name);
		// prodVO.setProd_descript(prod_descript);
		// prodVO.setProd_price(prod_price);
		// prodVO.setProd_sort(prod_sort);
		// prodVO.setProd_format(prod_format);
		// prodVO.setProd_brand(prod_brand);
		// prodVO.setProd_updatetime(prod_updatetime);
		// prodVO.setProd_soldcount(prod_soldcount);
		// prodVO.setProd_status(prod_status);
		// prodVO.setProd_count(prod_count);
		// prodVO.setProd_score(prod_score);
		//
		// // Send the use back to the form, if there were errors
		// if (!errorMsgs.isEmpty()) {
		// req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/front-end/prod/update_prod_input.jsp");
		// failureView.forward(req, res);
		// return; //程式中斷
		// }
		//
		// /***************************2.開始修改資料*****************************************/
		// ProdService prodSvc = new ProdService();
		// prodVO = prodSvc.updateProd(prod_id, store_id, prod_name,
		// prod_descript,
		// prod_price,prod_sort,prod_format,prod_brand,prod_updatetime,prod_soldcount,prod_status,prod_count,prod_score);
		//
		// /***************************3.修改完成,準備轉交(Send the Success
		// view)*************/
		// req.setAttribute("prodVO", prodVO); //
		// 資料庫update成功後,正確的的prodVO物件,存入req
		// String url = "/front-end/store_interface/listOneProd_forStore.jsp";
		// RequestDispatcher successView = req.getRequestDispatcher(url); //
		// 修改成功後,轉交listOneProd.jsp
		// successView.forward(req, res);
		//
		// /***************************其他可能的錯誤處理*************************************/
		//// } catch (Exception e) {
		//// errorMsgs.add("修改資料失敗:"+e.getMessage());
		//// RequestDispatcher failureView = req
		//// .getRequestDispatcher("/front-end/store_interface/listOneStore_idAllProd.jsp");
		//// failureView.forward(req, res);
		//// }
		// }

		if ("insert".equals(action)) { // 來自addProd.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				Integer store_id = new Integer(req.getParameter("store_id").trim());
				String prod_name = req.getParameter("prod_name").trim();
				String prod_descript = req.getParameter("prod_descript").trim();
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				String prod_sort = req.getParameter("prod_sort").trim();
				String prod_format = req.getParameter("prod_format").trim();
				String prod_brand = req.getParameter("prod_brand").trim();
				java.sql.Date prod_updatetime = null;
				try {
					prod_updatetime = java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
				} catch (IllegalArgumentException e) {
					prod_updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				Integer prod_soldcount = new Integer(req.getParameter("prod_soldcount").trim());
				Integer prod_status = new Integer(req.getParameter("prod_status").trim());
				Integer prod_count = new Integer(req.getParameter("prod_count").trim());
				Integer prod_score = new Integer(req.getParameter("prod_score").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setStore_id(store_id);
				prodVO.setProd_name(prod_name);
				prodVO.setProd_descript(prod_descript);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_sort(prod_sort);
				prodVO.setProd_format(prod_format);
				prodVO.setProd_brand(prod_brand);
				prodVO.setProd_updatetime(prod_updatetime);
				prodVO.setProd_soldcount(prod_soldcount);
				prodVO.setProd_status(prod_status);
				prodVO.setProd_count(prod_count);
				prodVO.setProd_score(prod_score);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO); // 含有輸入格式錯誤的prodVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/addProd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.addProd(store_id, prod_name, prod_descript, prod_price, prod_sort, prod_format,
						prod_brand, prod_updatetime, prod_soldcount, prod_status, prod_count, prod_score);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/prod/listAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProd.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/addProd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllProd.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer prod_id = new Integer(req.getParameter("prod_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				ProdService prodSvc = new ProdService();
				prodSvc.deleteProd(prod_id);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/prod/listAllProd.jsp");
				failureView.forward(req, res);
			}
		}

		///////////////////////////////////////////////////// 修改產品

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				/*************************** 2.開始查詢資料 ****************************************/

				ProdService prodSvc = new ProdService();
				ProdVO prodVO = prodSvc.getOneProd(prod_id);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("prodVO", prodVO); //
				String url = "/front-end/store_interface/update_prod_prodChange.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/listOneStore_idAllProd.jsp");
				failureView.forward(req, res);
			}
		}

		///////////////////////////////////////////////////// 修改產品

		if ("update_prodChange".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			try {

				Integer prod_id = new Integer(req.getParameter("prod_id").trim());
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				String prod_name = req.getParameter("prod_name").trim();
				String prod_descript = req.getParameter("prod_descript").trim();
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				String prod_sort = req.getParameter("prod_sort").trim();
				String prod_format = req.getParameter("prod_format").trim();
				String prod_brand = req.getParameter("prod_brand").trim();
				java.sql.Date prod_updatetime = null;

				try {
					prod_updatetime = java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
				} catch (IllegalArgumentException e) {
					prod_updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入產品更新日期!");
				}

				Integer prod_soldcount = new Integer(req.getParameter("prod_soldcount").trim());
				Integer prod_count = new Integer(req.getParameter("prod_count").trim());
				Integer prod_score = new Integer(req.getParameter("prod_score").trim());
				Integer prod_status = new Integer(req.getParameter("prod_status").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setProd_id(prod_id);
				prodVO.setStore_id(store_id);
				prodVO.setProd_name(prod_name);
				prodVO.setProd_descript(prod_descript);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_sort(prod_sort);
				prodVO.setProd_format(prod_format);
				prodVO.setProd_brand(prod_brand);
				prodVO.setProd_updatetime(prod_updatetime);
				prodVO.setProd_soldcount(prod_soldcount);
				prodVO.setProd_status(prod_status);
				prodVO.setProd_count(prod_count);
				prodVO.setProd_score(prod_score);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/update_prod_prodChange.jsp");
					failureView.forward(req, res);
					return;
				}

				String prpi_name = req.getParameter("prpi_name").trim();

				Part partpic = req.getPart("prpi_img");
				BufferedInputStream bis = new BufferedInputStream(partpic.getInputStream());
				byte[] prpi_img = new byte[bis.available()];
				bis.read(prpi_img);
				bis.close();

				PrpiVO prpiVO = new PrpiVO();
				prpiVO.setPrpi_name(prpi_name);
				prpiVO.setPrpi_img(prpi_img);

				/*************************** 2.開始修改資料 *****************************************/
				ProdService prodSvc = new ProdService();
				prodVO = prodSvc.updateProd(prod_id, store_id, prod_name, prod_descript, prod_price, prod_sort,
						prod_format, prod_brand, prod_updatetime, prod_soldcount, prod_status, prod_count, prod_score);

				PrpiService prpiSvc = new PrpiService();
				PrpiVO getPrpiDate = prpiSvc.getOnePrpiByProd(prod_id);
				Integer prpi_id = getPrpiDate.getPrpi_id();
				prpiVO = prpiSvc.updatePrpi(prpi_id, prod_id, prpi_name, prpi_img);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/

				req.setAttribute("prodVO", prodVO);
				String url = "/front-end/store_interface/listOneStore_idAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/store_interface/update_prod_prodChange.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_prod".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				/*************************** 產品資料 ***************************************/

				Integer store_id = new Integer(req.getParameter("store_id").trim());
				String prod_name = req.getParameter("prod_name").trim();
				String prod_descript = req.getParameter("prod_descript").trim();
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				String prod_sort = req.getParameter("prod_sort").trim();
				String prod_format = req.getParameter("prod_format").trim();
				String prod_brand = req.getParameter("prod_brand").trim();
				java.sql.Date prod_updatetime = null;

				try {
					prod_updatetime = java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
				} catch (IllegalArgumentException e) {
					prod_updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				Integer prod_soldcount = new Integer(req.getParameter("prod_soldcount").trim());
				Integer prod_status = new Integer(req.getParameter("prod_status").trim());
				Integer prod_count = new Integer(req.getParameter("prod_count").trim());
				Integer prod_score = new Integer(req.getParameter("prod_score").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setStore_id(store_id);
				prodVO.setProd_name(prod_name);
				prodVO.setProd_descript(prod_descript);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_sort(prod_sort);
				prodVO.setProd_format(prod_format);
				prodVO.setProd_brand(prod_brand);
				prodVO.setProd_updatetime(prod_updatetime);
				prodVO.setProd_soldcount(prod_soldcount);
				prodVO.setProd_status(prod_status);
				prodVO.setProd_count(prod_count);
				prodVO.setProd_score(prod_score);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addProd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 圖片資料 ***************************************/

				List<PrpiVO> list = new ArrayList<PrpiVO>();

				System.out.println(req.getParameter("prpi_name").trim());
				
				String prpi_name = req.getParameter("prpi_name").trim();

				Part partpic = req.getPart("prpi_img");
				BufferedInputStream bis = new BufferedInputStream(partpic.getInputStream());
				byte[] img = new byte[bis.available()];
				bis.read(img);
				bis.close();

				PrpiVO prpiVO = new PrpiVO();
				prpiVO.setPrpi_name(prpi_name);
				prpiVO.setPrpi_img(img);
				list.add(prpiVO);

				/*************************** 2.開始新增資料 ***************************************/
				ProdService prodSvc = new ProdService();
				prodSvc.insertWithPrpi(prodVO, list);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/store_interface/listOneStore_idAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addProd.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_prod".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				/*************************** 產品資料 ***************************************/

				Integer store_id = new Integer(req.getParameter("store_id").trim());
				String prod_name = req.getParameter("prod_name").trim();
				String prod_descript = req.getParameter("prod_descript").trim();
				Integer prod_price = new Integer(req.getParameter("prod_price").trim());
				String prod_sort = req.getParameter("prod_sort").trim();
				String prod_format = req.getParameter("prod_format").trim();
				String prod_brand = req.getParameter("prod_brand").trim();
				java.sql.Date prod_updatetime = null;

				try {
					prod_updatetime = java.sql.Date.valueOf(req.getParameter("prod_updatetime").trim());
				} catch (IllegalArgumentException e) {
					prod_updatetime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架日期!");
				}

				Integer prod_soldcount = new Integer(req.getParameter("prod_soldcount").trim());
				Integer prod_status = new Integer(req.getParameter("prod_status").trim());
				Integer prod_count = new Integer(req.getParameter("prod_count").trim());
				Integer prod_score = new Integer(req.getParameter("prod_score").trim());

				ProdVO prodVO = new ProdVO();
				prodVO.setStore_id(store_id);
				prodVO.setProd_name(prod_name);
				prodVO.setProd_descript(prod_descript);
				prodVO.setProd_price(prod_price);
				prodVO.setProd_sort(prod_sort);
				prodVO.setProd_format(prod_format);
				prodVO.setProd_brand(prod_brand);
				prodVO.setProd_updatetime(prod_updatetime);
				prodVO.setProd_soldcount(prod_soldcount);
				prodVO.setProd_status(prod_status);
				prodVO.setProd_count(prod_count);
				prodVO.setProd_score(prod_score);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prodVO", prodVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addProd.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 圖片資料 ***************************************/

				List<PrpiVO> list = new ArrayList<PrpiVO>();

				System.out.println(req.getParameter("prpi_name").trim());
				
				String prpi_name = req.getParameter("prpi_name").trim();

				Part partpic = req.getPart("prpi_img");
				BufferedInputStream bis = new BufferedInputStream(partpic.getInputStream());
				byte[] img = new byte[bis.available()];
				bis.read(img);
				bis.close();

				PrpiVO prpiVO = new PrpiVO();
				prpiVO.setPrpi_name(prpi_name);
				prpiVO.setPrpi_img(img);
				list.add(prpiVO);

				/*************************** 2.開始新增資料 ***************************************/
				ProdService prodSvc = new ProdService();
				prodSvc.insertWithPrpi(prodVO, list);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front-end/store_interface/listOneStore_idAllProd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addProd.jsp");
				failureView.forward(req, res);
			}
	}
	}
}
