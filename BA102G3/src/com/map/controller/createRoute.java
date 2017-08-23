package com.map.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.path.model.PathService;
import com.path.model.PathVO;

//import com.reta.model.RetaService;
//import com.reta.model.RetaVO;

public class createRoute extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		    req.setCharacterEncoding("UTF-8");
		
		    List<Integer> pointx=new ArrayList();
		    List<Integer> pointy=new ArrayList();
		    
		    List<String> errorMsgs = new LinkedList<String>();
		    // Store this set in the request scope, in case we need to
		    // send the ErrorPage view.
		    req.setAttribute("errorMsgs", errorMsgs);

		    String fromPlace = req.getParameter("fromPlace");
			if (fromPlace == null || (fromPlace.trim().length() == 0)) {
				errorMsgs.add("請輸起點名稱");
			}
		    
		    String toPlace = req.getParameter("toPlace");
			if (toPlace == null || (toPlace.trim().length() == 0)) {
				errorMsgs.add("請輸終點名稱");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/path/findPath.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			PathService pathSvc=new PathService(); 
			PathVO pathVOfrom=pathSvc.getOnePathFromTo(fromPlace,fromPlace);
			PathVO pathVOto=pathSvc.getOnePathFromTo(toPlace,toPlace);
			
			PathVO pathVOout=pathSvc.getOnePathFromTo("out","out");
			PathVO pathVOpoint1=pathSvc.getOnePathFromTo("point1","point1");
			PathVO pathVOpoint2=pathSvc.getOnePathFromTo("point2","point2");
			
//			int condition=0;

// same side			
			if ( (pathVOfrom.getPath_fromlon() < pathVOout.getPath_fromlon()) && (pathVOto.getPath_fromlon() < pathVOout.getPath_fromlon()) || 
				 (pathVOfrom.getPath_fromlon() > pathVOout.getPath_fromlon()) && (pathVOto.getPath_fromlon() > pathVOout.getPath_fromlon())		
			   ) {				
//				condition=1;				
			}

// from west to east			
			if ( (pathVOfrom.getPath_fromlon() < pathVOout.getPath_fromlon()) && (pathVOto.getPath_fromlon() > pathVOout.getPath_fromlon())		
			   ) {				
//				condition=2;				
			}

// from east to west			
			if ( (pathVOfrom.getPath_fromlon() > pathVOout.getPath_fromlon()) && (pathVOto.getPath_fromlon() < pathVOout.getPath_fromlon())		
					) {				
//				condition=3;			
			}

// from out to west			
			if ( (pathVOto.getPath_fromlon() < pathVOout.getPath_fromlon()) && (pathVOfrom.getPath_fromplace().equals(pathVOout.getPath_fromplace()) )		
					) {				

			      pointx.add(pathVOout.getPath_fromlon().intValue());
			      pointy.add(pathVOout.getPath_fromlat().intValue());

			      pointx.add(pathVOpoint1.getPath_fromlon().intValue());
			      pointy.add(pathVOpoint1.getPath_fromlat().intValue());

			      pointx.add(pathVOto.getPath_fromlon().intValue());
			      pointy.add(pathVOto.getPath_fromlat().intValue());				
				
			}

// from out to east			
			if ( (pathVOto.getPath_fromlon() > pathVOout.getPath_fromlon()) && (pathVOfrom.getPath_fromplace().equals(pathVOout.getPath_fromplace()) )		
					) {				
			
			      pointx.add(pathVOout.getPath_fromlon().intValue());
			      pointy.add(pathVOout.getPath_fromlat().intValue());

			      pointx.add(pathVOpoint2.getPath_fromlon().intValue());
			      pointy.add(pathVOpoint2.getPath_fromlat().intValue());

			      pointx.add(pathVOto.getPath_fromlon().intValue());
			      pointy.add(pathVOto.getPath_fromlat().intValue());
						
			}

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
								
				// Send the use back to the form, if there were errors
				/*************************** 2.開始查詢資料 *****************************************/

								
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
			    Graphics2D g2d = (Graphics2D) g;
				
			    try {
				      // draw background image
				    
				      int shiftx=1139-1050;
				      int shifty=196-50;
				    		  
//base map-------------------------------------------------------------
				      
				      g2d.drawImage(bi1, 0, 0, null);
				      	      
//--------------------------------------------------------------				      
				      
				      int[] intPointx = new int[pointx.size()];

				      int[] intPointy = new int[pointy.size()];
				      
				      int count=0;
				      for (Integer ii:pointx){
				    	  intPointx[count]= ii;
				    	  count=count+1;
				      }
				      
				      count=0;
				      for (Integer ii:pointy){
				    	  intPointy[count]= ii;
				    	  count=count+1;
				      }

				      g2d.setColor(Color.red);
				      
				      g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{10.0f},0.0f));
				      				      
				      int length=intPointy.length;
				      
				      g2d.drawPolyline(intPointx,intPointy,length);
				      				      
				      g2d.drawImage(bi5, intPointx[intPointx.length-1]-shiftx, intPointy[intPointy.length-1]-shifty, null);
				      
				      g2d.setColor(Color.white);
				      int radius=15;
				      int diameter = radius * 2;
				      g2d.fillOval(pathVOout.getPath_fromlon().intValue()-radius, pathVOout.getPath_fromlat().intValue()-radius, diameter, diameter);

				      g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				      g2d.setColor(Color.black);
				      g2d.drawOval(pathVOout.getPath_fromlon().intValue()-radius, pathVOout.getPath_fromlat().intValue()-radius, diameter, diameter);
				      				      
//--------------------------------------------------------------				      				      
				      				      
				    }
				    finally {
				      g.dispose();
				      g2d.dispose();
				    }
				    	
				    OutputStream os = res.getOutputStream();
				    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
				    
				    if (!ImageIO.write(canvas, "png", ios)) {
				        log("Boo hoo, failed to output PNG");
				    }
				    
				    ios.close();
				    os.close();
				
/**************************************************************/				
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/path/indoorPathConnectionQuery.jsp");
//				failureView.forward(req, res);
			}

	}
	
}
