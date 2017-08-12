package com.prpi.controller;

import java.io.*;
import java.sql.Blob;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.prpi.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//�����之�fileSizeThreshold�潭��摰孵�◤撖怠蝤��
//銝���葉�隢���辣頞�axFileSize�潘������蜇��之�maxRequestSize �潮���IllegalStateException �撣�
public class PrpiServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("prpi_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓����楊���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/prpi/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				Integer prpi_id = null;
				try {
					prpi_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�����楊��撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/prpi/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				PrpiService prpiSvc = new PrpiService();
				PrpiVO prpiVO = prpiSvc.getOnePrpi(prpi_id);
				if (prpiVO == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/prpi/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("prpiVO", prpiVO); // 鞈�澈����mpVO�隞�,摮req
				String url = "/front-end/prpi/listOnePrpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOnePrpi1.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/prpi/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 靘listAllPrpi.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.��隢��****************************************/
				Integer prpi_id = new Integer(req.getParameter("prpi_id"));
				
				/***************************2.���閰Ｚ���****************************************/
				PrpiService prpiSvc = new PrpiService();
				PrpiVO prpiVO = prpiSvc.getOnePrpi(prpi_id);
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/
				req.setAttribute("prpiVO", prpiVO);         // 鞈�澈����rpiVO�隞�,摮req
				String url = "/front-end/prpi/update_prpi_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_prpi_input.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/prpi/listAllPrpi.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 靘update_emp_input.jsp�����
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				Integer prpi_id = new Integer(req.getParameter("prpi_id").trim());

				Integer prod_id = null;
				try {
					prod_id = new Integer(req.getParameter("prod_id").trim());
				} catch (NumberFormatException e) {
					prod_id = 0;
					errorMsgs.add("隢‵����楊���.");
				}

				String prpi_name = null;
				try {
					prpi_name = req.getParameter("prpi_name").trim();
				} catch (NumberFormatException e) {
					prpi_name = " ";
					errorMsgs.add("隢‵�������迂.");
				}

				PrpiVO prpiVO = new PrpiVO();
				prpiVO.setPrpi_name(prpi_name);
				prpiVO.setProd_id(prod_id);
				byte[] pic= null;
				
				try{
					String prpi_img = req.getParameter("prpi_img");
					System.out.println(prpi_img);
					pic = getPictureByteArray(prpi_img);
					prpiVO.setPrpi_img( pic);
				}catch (Exception e) {
					
					errorMsgs.add("隢�����!");
				}

				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prpiVO", prpiVO); // ���撓��撘隤斤�mpVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/prpi/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //蝔�葉�
				}
				
				/***************************2.���耨�鞈��*****************************************/
				PrpiService prpiSvc = new PrpiService();
				prpiVO = prpiSvc.updatePrpi(prpi_id, prod_id, prpi_name, pic);
				
				/***************************3.靽格摰��,皞��漱(Send the Success view)*************/
				req.setAttribute("prpiVO", prpiVO); // 鞈�澈update�����,甇�蝣箇��mpVO�隞�,摮req
				String url = "/front-end/prpi/listOnePrpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 靽格�����,頧漱listOneEmp.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("靽格鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/prpi/update_prpi_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 靘addPrpi.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���*************************/
				String prpi_name = req.getParameter("prpi_name").trim();
				
				if(prpi_name.equals("")){
					prpi_name = " ";
					errorMsgs.add("隢撓�������迂!");
				}
				Integer prod_id = new Integer(req.getParameter("prod_id").trim());

				Part partpic=req.getPart("prpi_img");
				BufferedInputStream bis= new BufferedInputStream(partpic.getInputStream());
				byte[] img = new byte[bis.available()];
				bis.read(img);				
				bis.close();
				
				
				PrpiVO prpiVO = new PrpiVO();
				prpiVO.setPrpi_name(prpi_name);
				prpiVO.setProd_id(prod_id);
				prpiVO.setPrpi_img(img);
				
//				PrpiVO prpiVO = new PrpiVO();
//				prpiVO.setPrpi_name(prpi_name);
//				prpiVO.setProd_id(prod_id);
//				byte[] pic= null;
//				
//				
//				try{
//					String prpi_img = req.getParameter("prpi_img");
//					System.out.println(prpi_img);
//					pic = getPictureByteArray(prpi_img);
//					prpiVO.setPrpi_img( pic);
//				}catch (Exception e) {
//					
//					errorMsgs.add("隢�����!");
//				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prpiVO", prpiVO); // ���撓��撘隤斤�rpiVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/prpi/addPrpi.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.���憓���***************************************/
				PrpiService prpiSvc = new PrpiService();
				prpiVO = prpiSvc.addPrpi(prod_id, prpi_name,  img);
				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				String url = "/front-end/prpi/listAllPrpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �憓����漱listAllPrpi.jsp
				successView.forward(req, res);				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/prpi/addPrpi.jsp");
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
				Integer prpi_id = new Integer(req.getParameter("prpi_id"));
				
				/***************************2.����鞈��***************************************/
				PrpiService prpiSvc = new PrpiService();
				prpiSvc.deletePrpi(prpi_id);
				
				/***************************3.��摰��,皞��漱(Send the Success view)***********/								
				String url = "/front-end/prpi/listAllPrpi.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/prpi/listAllPrpi.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[fis.available()];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}