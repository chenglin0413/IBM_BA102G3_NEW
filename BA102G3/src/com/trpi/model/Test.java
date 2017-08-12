package com.trpi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		TrpiService trpiSvc = new TrpiService();
		TrpiDAO dao =new TrpiDAO();
		
		//新增
//		TrpiVO trpiVO1 = new TrpiVO();
//		
//		trpiVO1.setTrvl_id(1100002);
//		
//		File f = new File("C:\\Users\\cuser\\Desktop\\images\\20.jpg");
//		//File f = new File("C:\\Users\\idests\\Desktop\\Penguins.jpg");
//		
//		FileInputStream fis = new FileInputStream(f);		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();		
//		trpiVO1.setTrpi_img(baos.toByteArray());
//		trpiVO1.setTrpi_imgfmt("01.png");
//				
//		dao.insert(trpiVO1);
	
		
		//修改
//		TrpiVO trpiVO2 = new TrpiVO();
//		
//		trpiVO2.setTrvl_id(1100001);
		
		File f2 = new File("C:\\Users\\cuser\\Desktop\\mini.jpg");

		FileInputStream fis2 = new FileInputStream(f2);		
		ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
		byte[] buffer2 = new byte[8192];
		int i2;
		while ((i2 = fis2.read(buffer2)) != -1) {
			baos2.write(buffer2, 0, i2);
		}
			
//		trpiVO2.setTrpi_img(baos2.toByteArray());
//		System.out.println(baos2.toByteArray());
//		
//		trpiVO2.setTrpi_imgfmt("XXX.jpg");
//		trpiVO2.setTrpi_id(1200003);	
		//dao.update(trpiVO2);
		
		baos2.close();
		fis2.close();
		
		trpiSvc.updateTrpi(1200003,1100001, baos2.toByteArray(), "mini.jpg");
		
//		//刪除
//		dao.delete(1200001);
			
		//查詢
//		TrpiVO trpiVO3 = dao.findByPrimaryKey(1200001);
//		System.out.print(trpiVO3.getTrpi_id() + ",");
//		System.out.print(trpiVO3.getTrvl_id() + ",");
//		System.out.print(trpiVO3.getTrpi_img()+ ",");
//		System.out.println(trpiVO3.getTrpi_imgfmt());
//		System.out.println("------------------------------");
//		
//		//查詢
//		List<TrpiVO> list = dao.getAll();
//		for(TrpiVO aTrpi : list) {
//			System.out.println(aTrpi.getTrpi_id());
//			System.out.println(aTrpi.getTrvl_id());
//			System.out.println(aTrpi.getTrpi_img());
//			System.out.println(aTrpi.getTrpi_imgfmt());
//			System.out.println();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
