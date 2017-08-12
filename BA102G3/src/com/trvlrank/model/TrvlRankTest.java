package com.trvlrank.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TrvlRankTest
 */
@WebServlet("/TrvlRankTest")
public class TrvlRankTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrvlRankTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		TrvlRankJDBCDAO dao = new TrvlRankJDBCDAO();

		//新增
		TrvlRankVO trvlrankVO1 = new TrvlRankVO();
		trvlrankVO1.setUser_id(1000004);
		trvlrankVO1.setTrvl_id(1100004);;
		trvlrankVO1.setTrvlrank_score(5);

		dao.insert(trvlrankVO1);
	
		// 新增
		TrvlRankVO trvlrankVO2 = new TrvlRankVO();
		
		trvlrankVO2.setUser_id(1000003);
		trvlrankVO2.setTrvl_id(1100002);;
		trvlrankVO2.setTrvlrank_score(1);

		dao.update(trvlrankVO2);
		System.out.println("XXX");	
		// 修改
//		dao.delete();
//		System.out.println(" 修改成功");

		// 查詢
		Integer socre = dao.findByPrimaryKey(1100001,1000001);
		System.out.println(socre);

		System.out.println("---------------------------------");

		//查詢
		List<TrvlRankVO> list = dao.getAll();
		for (TrvlRankVO aTrvlRank : list) {				
			System.out.print(aTrvlRank.getTrvl_id() + ",");
			System.out.print(aTrvlRank.getUser_id()+ ",");
            System.out.print(aTrvlRank.getTrvlrank_score()+ ",");
			System.out.println();
		}
		System.out.println("����������");	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
