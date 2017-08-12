package com.prpm.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.prpm.model.PrpmService;
import com.prpm.model.PrpmVO;
import com.stpm.model.StpmService;
import com.stpm.model.StpmVO;

public class PrpmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAdd".equals(action)) { // 來自select_page.jsp的請求

			String str = req.getParameter("stpm_id");

			Integer stpm_id = new Integer(str);

			StpmService stpmSvc = new StpmService();
			StpmVO stpmVO = stpmSvc.getOneStpm(stpm_id);

			req.setAttribute("stpmVO", stpmVO);
			String url = "/front-end/store_interface/addPrpm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			List<String> ansMsgs = new LinkedList<String>();
			req.setAttribute("ansMsgs", ansMsgs);

			try {

				String stpmid = req.getParameter("stpm_id");
				String prodid = req.getParameter("prod_id");
				String prpmprice = req.getParameter("prpm_price").trim();
				String prpmstatus = req.getParameter("prpm_status");

				Integer stpm_id = null;
				Integer prod_id = null;
				Integer prpm_status = null;
				Integer prpm_price = null;

				stpm_id = Integer.parseInt(stpmid);
				prod_id = Integer.parseInt(prodid);
				prpm_status = Integer.parseInt(prpmstatus);

				StpmService stpmSvc = new StpmService();
				StpmVO stpmVO = stpmSvc.getOneStpm(stpm_id);

				PrpmService prpmSvc = new PrpmService();
				PrpmVO checkNull = prpmSvc.getOneStpm(stpm_id, prod_id);

				// System.out.println(checkNull);

				if (checkNull != null) {
					errorMsgs.add("請勿重複新增");
				}

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("stpmVO", stpmVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/update_stpm_input.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				try {
					prpm_price = new Integer(prpmprice); // 只判斷price,其餘值直接轉型
				} catch (Exception e) {
					errorMsgs.add("請輸入促銷價格");
				}

				PrpmVO prpmVO = new PrpmVO();
				prpmVO.setStpm_id(stpm_id);
				prpmVO.setProd_id(prod_id);
				prpmVO.setPrpm_price(prpm_price);
				prpmVO.setPrpm_status(prpm_status);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addPrpm.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				prpmVO = prpmSvc.addPrpm(stpm_id, prod_id, prpm_price, prpm_status); // insert
				ansMsgs.add("商品新增成功");
				
				if (!ansMsgs.isEmpty()) {
					req.setAttribute("stpmVO", stpmVO);
					// req.setAttribute("prpmVO", prpmVO);
					String url = "/front-end/store_interface/update_stpm_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/addPrpm.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer stpm_id = new Integer(req.getParameter("stpm_id"));

				Integer prod_id = new Integer(req.getParameter("prod_id"));

				PrpmService prpmSvc = new PrpmService();
				PrpmVO prpmVO = prpmSvc.getOneStpm(stpm_id, prod_id);

				req.setAttribute("prpmVO", prpmVO);
				String url = "/front-end/store_interface/update_prpm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/update_stpm_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer stpm_id = new Integer(req.getParameter("stpm_id"));

				Integer prod_id = new Integer(req.getParameter("prod_id"));

				Integer prpm_status = new Integer(req.getParameter("prpm_status"));
				
				String prpmprice = req.getParameter("prpm_price").trim();

				Integer prpm_price = null;

				try {
					prpm_price = new Integer(prpmprice); // 只判斷price,其餘值直接轉型
				} catch (Exception e) {
					errorMsgs.add("請輸入促銷價格");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/update_prpm_input.jsp");
					failureView.forward(req, res);
					return;
				}

				PrpmVO prpmVO = new PrpmVO();
				prpmVO.setStpm_id(stpm_id);
				prpmVO.setProd_id(prod_id);
				prpmVO.setPrpm_price(prpm_price);
				prpmVO.setPrpm_status(prpm_status);

				PrpmService prpmSvc = new PrpmService();
				prpmVO = prpmSvc.updatePrpm(stpm_id, prod_id, prpm_price, prpm_status);

				StpmService stpmSvc = new StpmService();
				StpmVO stpmVO = stpmSvc.getOneStpm(stpm_id);

				req.setAttribute("stpmVO", stpmVO);
				String url = "/front-end/store_interface/update_stpm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/store_interface/update_prpm_input.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
