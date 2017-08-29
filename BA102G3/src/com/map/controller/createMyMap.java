package com.map.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.flsc.model.FlscService;
import com.flsc.model.FlscVO;
import com.ord.model.OrdService;
import com.ord.model.OrdVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.rest.model.RestService;
import com.rest.model.RestVO;
import com.reta.model.RetaService;
import com.reta.model.RetaVO;
import com.avtb.model.AvtbService;
import com.avtb.model.AvtbVO;
import com.path.model.PathService;
import com.path.model.PathVO;

//import com.reta.model.RetaService;
//import com.reta.model.RetaVO;

public class createMyMap extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");		    
			
			Integer user_id = new Integer(req.getParameter("user_id"));
		    		    
		    System.out.println("pass getMap.java line.37"+user_id);

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String flsc_sdate1 = req.getParameter("flsc_sdate1");
				if (flsc_sdate1 == null || (flsc_sdate1.trim().length() == 0)) {
					errorMsgs.add("請輸入表定時間-抵達");
				}
				
				String flsc_airlinecode1 = req.getParameter("flsc_airlinecode1");
				if (flsc_airlinecode1 == null || (flsc_airlinecode1.trim().length() == 0)) {
					errorMsgs.add("請輸入航空代碼-抵達");
				}
				
				String flsc_flno1 = req.getParameter("flsc_flno1");
				if (flsc_flno1 == null || (flsc_flno1.trim()).length() == 0) {
					errorMsgs.add("請輸入班次編號-抵達");
				}

				String flsc_sdate2 = req.getParameter("flsc_sdate2");
				if (flsc_sdate2 == null || (flsc_sdate2.trim().length() == 0)) {
					errorMsgs.add("請輸入表定時間-出發");
				}
				
				String flsc_airlinecode2 = req.getParameter("flsc_airlinecode2");
				if (flsc_airlinecode2 == null || (flsc_airlinecode2.trim().length() == 0)) {
					errorMsgs.add("請輸入航空代碼-出發");
				}
				
				String flsc_flno2 = req.getParameter("flsc_flno2");
				if (flsc_flno2 == null || (flsc_flno2.trim()).length() == 0) {
					errorMsgs.add("請輸入班次編號-出發");
				}
								
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/path/findPath.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/

				String flsc_sdate1a = flsc_sdate1.replace("-", "/");
				FlscService flscScv = new FlscService();
				FlscVO flscQuery1 = flscScv.flscSubQuery(flsc_airlinecode1, flsc_flno1, flsc_sdate1a);

				String flsc_sdate2a = flsc_sdate2.replace("-", "/");
				FlscVO flscQuery2 = flscScv.flscSubQuery(flsc_airlinecode2, flsc_flno2, flsc_sdate2a);
				
				OrdService ordSrv = new OrdService();
				List<OrdVO> ordQuery = ordSrv.getOneUser_idAllOrd(user_id);

				RetaService retaSrv = new RetaService();
				List<RetaVO> retaQuery = retaSrv.findByUserId(user_id);
								
			    System.out.println("pass getMap.java line.106 "+user_id);
								
/******Generate Map********************************************/
			    
			    String filePath1 = getServletContext().getRealPath("/WEB-INF/map/T2_3F_template.png");
			    String filePath2 = getServletContext().getRealPath("/WEB-INF/map/tagYellow.png");
			    String filePath3 = getServletContext().getRealPath("/WEB-INF/map/tagBlue.png");
			    String filePath4 = getServletContext().getRealPath("/WEB-INF/map/tagGreen.png");
			    String filePath5 = getServletContext().getRealPath("/WEB-INF/map/tagRed.png");
			    				
			    BufferedImage bi1 = null;
			    BufferedImage bi2 = null;
			    BufferedImage bi3 = null;
			    BufferedImage bi4 = null;
			    BufferedImage bi5 = null;
			    bi1 = javax.imageio.ImageIO.read(new File(filePath1));
			    bi2 = javax.imageio.ImageIO.read(new File(filePath2));
			    bi3 = javax.imageio.ImageIO.read(new File(filePath3));
			    bi4 = javax.imageio.ImageIO.read(new File(filePath4));
			    bi5 = javax.imageio.ImageIO.read(new File(filePath5));
			    
//			    bi1 = javax.imageio.ImageIO.read(new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/T2_3F_template.png"));
//			    bi2 = javax.imageio.ImageIO.read(new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/tagYellow.png"));
//			    bi3 = javax.imageio.ImageIO.read(new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/tagBlue.png"));
//			    bi4 = javax.imageio.ImageIO.read(new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/tagGreen.png"));
//			    bi5 = javax.imageio.ImageIO.read(new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/tagRed.png"));
			    
			    BufferedImage canvas = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_INT_ARGB);
			    Graphics g = canvas.getGraphics();
				
			    try {
				      // draw background image
				    
				      int shiftx=1139-1050;
				      int shifty=196-50;
				    		  
//base map-------------------------------------------------------------
				      
				      g.drawImage(bi1, 0, 0, null);
				      
//mark security check--------------------------------------------------				      
				      				      
				      g.drawImage(bi2, 1074-shiftx, 552-shifty, null);

//mark out bound flight-------------------------------------------------- 				      
				      
				      PathService pathSrv = new PathService();
				      PathVO pathVO=pathSrv.getOnePathFromTo( flscQuery2.getFlsc_gate(), flscQuery2.getFlsc_gate() );
				      g.drawImage(bi5, pathVO.getPath_fromlon().intValue()-shiftx, pathVO.getPath_fromlat().intValue()-shifty, null);
				      
//mark store-----------------------------------------------------test user_id: 1000003, store_id: 2000004
				      
				      StoreService storeSrv = new StoreService();
				      
				      for (OrdVO a: ordQuery){
						if (a.getOrd_status()==2 ){

							StoreVO storeVO=storeSrv.getOneStore(a.getStore_id());
							g.drawImage(bi3, storeVO.getStore_lon().intValue()-shiftx, storeVO.getStore_lat().intValue()-shifty, null);
														
						}
					  }
				      
//mark rest-----------------------------------------------------      

				      RestService restSrv = new RestService();
				      AvtbService avtbSrv = new AvtbService();
				      
				      for (RetaVO b: retaQuery){
						if (b.getReta_grant()==1 && b.getReta_status()==0){

							System.out.println("creatMyMap.java, pass line 177");
							
							System.out.println("b.getAvtb_id() "+b.getAvtb_id() );
							AvtbVO avtbVO=avtbSrv.getOneAvtb(b.getAvtb_id());
							
							System.out.println("avtbVO.getAvtb_id() "+avtbVO.getAvtb_id() );
							System.out.println("avtbVO.getRest_id() "+avtbVO.getRest_id() );
							RestVO restVO=restSrv.getOneRest(avtbVO.getRest_id());
							
							System.out.println("Rest lon: "+restVO.getRest_lon().intValue());
							System.out.println("Rest lat: "+restVO.getRest_lat().intValue());
							
							g.drawImage(bi4, restVO.getRest_lon().intValue()-shiftx, restVO.getRest_lat().intValue()-shifty, null);
							
						}
					  }
				      
//				      g.drawImage(bi4, 2221-shiftx, 520-shifty, null);
				      
//--------------------------------------------------------------				      
				      				      
				    }
				    finally {
				      g.dispose();
				    }
//				    final File outputFile = new File("C:/Users/mjdtsay/Dropbox/Project/Path/PNG/z_op.png");
//				    if (!ImageIO.write(canvas, "png", outputFile))
//				      System.out.printf("Output image to %s failed!!!%n", outputFile);
				    	
				    OutputStream os = res.getOutputStream();
				    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
				    
				    if (!ImageIO.write(canvas, "png", ios)) {
				        log("Boo hoo, failed to output PNG");
				    }
				    
				    ios.close();
				    os.close();
				
/**************************************************************/				
				
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/

//				req.setAttribute("flscQuery1", flscQuery1);
//				req.setAttribute("flscQuery2", flscQuery2);
//				
//				String url = "/front-end/path/indoorPathConnectionDisplay.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
//				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/path/indoorPathConnectionQuery.jsp");
//				failureView.forward(req, res);
			}

	}

	
	
	
}
