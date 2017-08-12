package com.ord.controller;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.ord.model.*;
import com.prod.model.ProdDAO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;


public class OrdServlet_memberAddOrd extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
//		int count=0;
		

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Vector<OrdVO> addOrd = (Vector<OrdVO>) session.getAttribute("addOrd");
			Vector<ItemVO> addItem = (Vector<ItemVO>) session.getAttribute("addItem");
//			int [] stores=new int[addOrd.size()];
				try {
					
					for(int i=0;i<addOrd.size();i++){
					
					Integer user_id = new Integer(addOrd.elementAt(i).getUser_id());
					Integer store_id = new Integer(addOrd.elementAt(i).getStore_id());
//					stores[i]=store_id;
					java.sql.Date ord_date = null;
					try {
						ord_date =addOrd.elementAt(i).getOrd_date();
					} catch (IllegalArgumentException e) {
						ord_date=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入訂單日期!");
					}
					Integer ord_total = new Integer(addOrd.elementAt(i).getOrd_total());
					Integer ord_bill = 1;
					Integer ord_grant = 1;
					Integer ord_status = 1;
					Integer ord_sscore = 0;
					java.sql.Date ord_rpdate = null;
					String ord_rpcomm= "";
					Integer ord_rpstatus = 0;
					
					
					
					OrdVO ordVO = new OrdVO();
					ordVO.setUser_id(user_id);
					ordVO.setStore_id(store_id);
					ordVO.setOrd_date(ord_date);
					ordVO.setOrd_total(ord_total);
					ordVO.setOrd_bill(ord_bill);
					ordVO.setOrd_grant(ord_grant);
					ordVO.setOrd_status(ord_status);
					ordVO.setOrd_sscore(ord_sscore);
					ordVO.setOrd_rpdate(ord_rpdate);
					ordVO.setOrd_rpcomm(ord_rpcomm);
					ordVO.setOrd_rpstatus(ord_rpstatus);
					//
					
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("ordVO", ordVO); // 含有輸入格式錯誤的ordVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/ord/addOrd_n.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					//新增訂單資料
					OrdService ordSvc = new OrdService();
					ordVO = ordSvc.addOrd( user_id, store_id, ord_date,ord_total,ord_bill, ord_grant,ord_status,ord_sscore,ord_rpdate,ord_rpcomm,ord_rpstatus);
					//搜尋最新訂單ORD_ID和STORE_ID
					OrdVO aordVO=ordSvc.getOneOrd_idANDOneStore_id();
					Integer aord_id=aordVO.getOrd_id();
					Integer astore_id=aordVO.getStore_id();
					System.out.println(aord_id);
					System.out.println(astore_id);
					
					for(int j=0;j<addItem.size();j++){
						//每一筆產品比對對應的STORE_I_D，相同才會執行新增ITEM
						ProdService prodSvc = new ProdService();
						Integer aprod_id=addItem.elementAt(j).getProd_id();
						
						if((prodSvc.getOneProd(aprod_id).getStore_id().equals(astore_id))){
							Integer ord_id=aord_id;
							Integer prod_id=addItem.elementAt(j).getProd_id();
							Integer item_qty = addItem.elementAt(j).getItem_qty();
							Integer item_score = 0;
							String item_review = "";
							java.sql.Date item_reviewdate = null;
							ItemVO itemVO = new ItemVO();
							itemVO.setOrd_id(ord_id);
							itemVO.setProd_id(prod_id);
							itemVO.setItem_qty(item_qty);
							itemVO.setItem_score(item_score);
							itemVO.setItem_review(item_review);
							itemVO.setItem_reviewdate(item_reviewdate);
							ItemService itemSvc=new ItemService();
							itemVO = itemSvc.addItem(ord_id,prod_id,item_qty,item_score,item_review,item_reviewdate);
							
							
							}else{
							continue;
						}
						
					}
				}
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					
					
					
					String url = "/front-end/ord/listAllOrd.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					session.removeAttribute("addOrd");
					session.removeAttribute("addItem");
					session.removeAttribute("shoppingcart");
					session.removeAttribute("shoppingqty");
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/ord/addOrd_n.jsp");
					failureView.forward(req, res);
				}
			}
				
	
		
		
		
	}
}
