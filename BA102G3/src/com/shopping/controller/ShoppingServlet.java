package com.shopping.controller;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.ord.model.OrdVO;
import com.prod.model.ProdVO;

import java.sql.*;
import java.sql.Date;

public class ShoppingServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();

		HttpSession session = req.getSession();
		Vector<Integer> buyqty= (Vector<Integer>) session.getAttribute("shoppingqty");
		Vector<ProdVO> buylist = (Vector<ProdVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		String requestURL=req.getParameter("requestURL");
		Integer quantity=null;
		
		if (!action.equals("CHECKOUT")) {

			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
				buyqty.removeElementAt(d);
				
				
				session.setAttribute("shoppingcart", buylist);
				session.setAttribute("shoppingqty", buyqty);
				String url = "/front-end/eshop/Cart.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				boolean match = false;

				// 取得後來新增的書籍
				ProdVO aprod = getProd(req);

				// 新增第一本書籍至購物車時
				if (buylist == null) {
					quantity=new Integer(req.getParameter("quantity"));
					buylist = new Vector<ProdVO>();
					buylist.add(aprod);
					buyqty=new Vector<Integer>();
					buyqty.add(quantity);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProdVO prod = buylist.get(i);
						
						// 假若新增的書籍和購物車的書籍一樣時
						if (prod.getProd_id().equals(aprod.getProd_id())) {
							
							quantity=new Integer(req.getParameter("quantity").trim());
							buyqty.set(i,quantity+buyqty.get(i));
							match = true;
						} // end of if name matches
					} // end of for

					// 假若新增的書籍和購物車的書籍不一樣時
					if (!match){
						quantity=new Integer(req.getParameter("quantity"));
						buylist.add(aprod);
					    buyqty.add(quantity);
					    
					    }
				}
				session.setAttribute("shoppingcart", buylist);
				session.setAttribute("shoppingqty", buyqty);
				String url = requestURL;
				if(requestURL.equals("/front-end/eshop/EShop.jsp")){
					RequestDispatcher rd = req.getRequestDispatcher("/front-end/member_interface/listAllProd.jsp");
					rd.forward(req, res);
				}else{
					RequestDispatcher rd = req.getRequestDispatcher(url);
					rd.forward(req, res);
				}
				
			}

			//原本的位置
		}

		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("CHECKOUT")) {
			float total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProdVO prod = buylist.get(i);
				Integer price = prod.getProd_price();
				Integer count = buyqty.get(i);
				total += (price * count);
			}

			String amount = String.valueOf(total);
			req.setAttribute("amount", amount);
			
			String url = "/front-end/eshop/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private ProdVO getProd(HttpServletRequest req) {
		
		Integer prod_id=new Integer(req.getParameter("prod_id"));
		Integer store_id=new Integer(req.getParameter("store_id"));
		String prod_name=req.getParameter("prod_name");
		String prod_descript=req.getParameter("prod_descript");
		Integer prod_price=new Integer(req.getParameter("prod_price"));
		String prod_sort=req.getParameter("prod_sort");
		String prod_format=req.getParameter("prod_format");
		String prod_brand=req.getParameter("prod_brand");
//		java.sql.Date prod_updatetime=java.sql.Date.valueOf(req.getParameter("prod_updatetime"));
//		Integer prod_soldcount=new Integer(req.getParameter("prod_soldcount"));
//		Integer prod_status=new Integer(req.getParameter("prod_status"));
//		Integer prod_count=new Integer(req.getParameter("prod_count"));
//		Integer prod_score=new Integer(req.getParameter("prod_score"));
		
		ProdVO prodVO = new ProdVO();

		prodVO.setProd_id(prod_id);
		prodVO.setStore_id(store_id);
		prodVO.setProd_name(prod_name);
		prodVO.setProd_descript(prod_descript);
		prodVO.setProd_price(prod_price);
		prodVO.setProd_sort(prod_sort);
		prodVO.setProd_format(prod_format);
		prodVO.setProd_brand(prod_brand);
//		prodVO.setProd_updatetime(prod_updatetime);
//		prodVO.setProd_soldcount(prod_soldcount);
//		prodVO.setProd_status(prod_status);
//		prodVO.setProd_count(prod_count);
//		prodVO.setProd_score(prod_score);
		
		
		return prodVO;
	}
}
