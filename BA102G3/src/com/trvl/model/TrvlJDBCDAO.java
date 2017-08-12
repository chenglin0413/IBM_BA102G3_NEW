package com.trvl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiVO;

public class TrvlJDBCDAO implements TrvlDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO trvl (TRVL_ID,USER_ID,TRVL_DATE,TRVL_TITTLE,TRVL_LOC,TRVL_CONTENT) "
			+ "VALUES (TRVL_ID_SEQ.NEXTVAL, ?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE Trvl set user_id=?, trvl_date=?, trvl_tittle=?, trvl_loc=? ,trvl_content=?"
			+ "where trvl_id = ?";
	
	private static final String DELETE_TLCM = "DELETE FROM TLCM where trvl_id=?";
	private static final String DELETE_TRPI = "DELETE FROM TRPI where trvl_id=?";
	private static final String DELETE = "DELETE FROM Trvl where trvl_id=?";
	
	private static final String GET_ONE_STMT = "SELECT * FROM Trvl where trvl_id =?";
	private static final String GET_ALL_STMT = "SELECT * FROM Trvl order by trvl_date desc";
	
	private static final String Get_BY_USERID = "SELECT * FROM Trvl where user_id = ? order by trvl_date desc";
	
	private static final String Get_Tlcms = "SELECT * FROM Tlcm where trvl_id=?";
	private static final String Get_Trpis = "SELECT * FROM Trpi where trvl_id=?";
	
	private static final String add_trvl_count = "update trvl set trvl_count=(trvl_count+1) where trvl_id=?";
	
	private static final String Get_TOP_BLOGS = "SELECT * FROM (select * from trvl order by trvl_count desc) where rownum <=3";

	@Override
	public String insert(TrvlVO trvlVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String trvlid = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);

			String cols[] = { "TRVL_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, trvlVO.getUser_id());
			pstmt.setDate(2, trvlVO.getTrvl_date());
			pstmt.setString(3, trvlVO.getTrvl_tittle());
			pstmt.setString(4, trvlVO.getTrvl_loc());
			pstmt.setString(5, trvlVO.getTrvl_content());

			pstmt.executeUpdate();

			System.out.println("新增成功");

			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						trvlid = rs.getString(i);
						System.out.println("(" + i + ") = " + trvlid + "(新增的自增主鍵值號碼)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return trvlid;
	}

	@Override
	public void update(TrvlVO trvlVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trvlVO.getUser_id());
			pstmt.setDate(2, trvlVO.getTrvl_date());
			pstmt.setString(3, trvlVO.getTrvl_tittle());
			pstmt.setString(4, trvlVO.getTrvl_loc());
			pstmt.setString(5, trvlVO.getTrvl_content());
			pstmt.setInt(6, trvlVO.getTrvl_id());

			System.out.println("修改成功");
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer trvl_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateTlcm = 0;
		int updateTrpi = 0;
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			
			//先刪除留言
			pstmt = con.prepareStatement(DELETE_TLCM);
			pstmt.setInt(1, trvl_id);
			updateTlcm = pstmt.executeUpdate();
			
			//在刪除相片
			pstmt = con.prepareStatement(DELETE_TRPI);
			pstmt.setInt(1, trvl_id);
			updateTrpi = pstmt.executeUpdate();
		
			//刪除遊記	
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trvl_id);
			pstmt.executeUpdate();

			System.out.println("遊記被刪除時共有 :"+ updateTlcm + "筆留言被刪除  , " + updateTrpi + "張相片被刪除");
			
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public TrvlVO findByPrimaryKey(Integer trvl_id) {
		// TODO Auto-generated method stub
		TrvlVO trvlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trvl_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_TITTLE"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return trvlVO;
	}
	
	@Override
	public List<TrvlVO> findByForeignKey(Integer user_id) {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_BY_USERID);
			
			pstmt.setInt(1, user_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<TrvlVO> getAll() {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public List<TlcmVO> findTlcmsByTrvlId(Integer trvl_id) {
		// TODO Auto-generated method stub
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_Tlcms);
			
			pstmt.setInt(1,trvl_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("TLCM_ID"));
				tlcmVO.setTrvl_id(rs.getInt("TRVL_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("TLCM_DATE"));
				tlcmVO.setTlcm_content(rs.getString("TLCM_CONTENT"));
				list.add(tlcmVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	
	@Override
	public List<TrpiVO> findTrpisByTrvlId (Integer trvl_id) {
		List<TrpiVO> list = new ArrayList<TrpiVO>();
		TrpiVO trpiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_Trpis);
			
			pstmt.setInt(1,trvl_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trpiVO = new TrpiVO();
				trpiVO.setTrpi_id(rs.getInt("TRPI_ID"));
				trpiVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trpiVO.setTrpi_img(rs.getBytes("TRPI_IMG"));
				trpiVO.setTrpi_imgfmt(rs.getString("TRPI_IMGFMT"));
				list.add(trpiVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public void addPageView(Integer trvl_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(add_trvl_count);

			pstmt.setInt(1, trvl_id);

			System.out.println("遊記瀏覽次數+1");
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	
	@Override
	public List<TrvlVO> getTopOfBlogs() {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(Get_TOP_BLOGS);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	

	public static void main(String[] args) {

		TrvlJDBCDAO dao = new TrvlJDBCDAO();

		// 新增
//		TrvlVO trvlVO01 = new TrvlVO();
//		trvlVO01.setUser_id(1000002);
//		trvlVO01.setTrvl_date(java.sql.Date.valueOf("2017-07-11"));
//		;
//		trvlVO01.setTrvl_tittle("旅遊去");
//		;
//		trvlVO01.setTrvl_loc("日本");
//		trvlVO01.setTrvl_content("到去處去玩");
//
//		String trvlid = dao.insert(trvlVO01);
//		System.out.println(trvlid);

		// //修改
//		 TrvlVO trvlVO2 = new TrvlVO();
//		
//		 trvlVO2.setUser_id(1000005);
//		 trvlVO2.setTrvl_date(java.sql.Date.valueOf("2017-07-11"));;
//		 trvlVO2.setTrvl_tittle("擬好");;
//		 trvlVO2.setTrvl_loc("....");
//		 trvlVO2.setTrvl_content("餒文");
//		 trvlVO2.setTrvl_id(1100006);
//		 dao.update(trvlVO2);
//		 
		 dao.addPageView(1100005);
		
		 // 刪除
//		 dao.delete(1100006);
//		 System.out.println("已刪除");

		// 查詢
//		TrvlVO trvlVO3 = dao.findByPrimaryKey(1100004);
//		System.out.print(trvlVO3.getTrvl_id() + ",");
//		System.out.print(trvlVO3.getUser_id() + ",");
//		System.out.print(trvlVO3.getTrvl_date() + ",");
//		System.out.print(trvlVO3.getTrvl_tittle() + ",");
//		System.out.print(trvlVO3.getTrvl_loc() + ",");
//		System.out.print(trvlVO3.getTrvl_content() + ",");
//		System.out.print(trvlVO3.getTrvl_count() + ",");
//		System.out.println(trvlVO3.getTrvl_score() + ",");
//		System.out.println("---------------------------------");
		
//		List<TrvlVO> list = dao.findByForeignKey(1000002);
//		for (TrvlVO aTlcm : list) {
//			
//			System.out.print(aTlcm.getTrvl_id() + ",");
//			System.out.print(aTlcm.getUser_id() + ",");
//			System.out.print(aTlcm.getTrvl_tittle() + ",");
//			System.out.print(aTlcm.getTrvl_date() + ",");
//			System.out.print(aTlcm.getTrvl_loc() + ",");
//			System.out.print(aTlcm.getTrvl_content() + ",");
//			System.out.print(aTlcm.getTrvl_count() + ",");
//			System.out.print(aTlcm.getTrvl_score() + ",");
//			System.out.println();
//		}
//		System.out.println("---------------------------------");
		
		

		// 查詢
//		List<TrvlVO> list2 = dao.getAll();
//		for (TrvlVO aTrvl : list2) {
//			System.out.print(aTrvl.getTrvl_date() + ",");
//			System.out.print(aTrvl.getTrvl_id() + ",");
//			System.out.print(aTrvl.getUser_id() + ",");
//			System.out.print(aTrvl.getTrvl_tittle() + ",");
//			System.out.print(aTrvl.getTrvl_tittle() + ",");
//			System.out.print(aTrvl.getTrvl_loc() + ",");
//			System.out.print(aTrvl.getTrvl_content() + ",");
//			System.out.print(aTrvl.getTrvl_count() + ",");
//			System.out.print(aTrvl.getTrvl_score() + ",");
//			System.out.println();
//		}
		 
		 //找出單篇遊記所有留言
//		 
//		 List<TlcmVO> list3 = dao.findTlcmsByTrvlId(1100005);
//			for (TlcmVO aTlcm : list3) {
//				System.out.print(aTlcm.getTlcm_id() + ",");
//				System.out.print(aTlcm.getTrvl_id() + ",");
//				System.out.print(aTlcm.getUser_id() + ",");
//				System.out.print(aTlcm.getTlcm_date() + ",");
//				System.out.print(aTlcm.getTlcm_content() + ",");
//				System.out.println(".............................");
//			}
//		 
//		//找出單篇遊記所有照片
//			 
//		List<TrpiVO> list4 = dao.findTrpisByTrvlId(1100005);
//			for (TrpiVO aTrpi : list4) {
//				System.out.print(aTrpi.getTrpi_id() + ",");
//				System.out.print(aTrpi.getTrvl_id() + ",");
//				System.out.print(aTrpi.getTrpi_date() + ",");
//				System.out.print(aTrpi.getTrpi_img() + ",");
//				System.out.print(aTrpi.getTrpi_imgfmt() + ",");
//				System.out.println();
//		System.out.println("************************************");		
//			}
//			
		 
			List<TrvlVO> list2 = dao.getTopOfBlogs();
			for (TrvlVO aTrvl : list2) {
				System.out.print(aTrvl.getTrvl_date() + ",");
				System.out.print(aTrvl.getTrvl_id() + ",");
				System.out.print(aTrvl.getUser_id() + ",");
				System.out.print(aTrvl.getTrvl_tittle() + ",");
				System.out.print(aTrvl.getTrvl_tittle() + ",");
				System.out.print(aTrvl.getTrvl_loc() + ",");
				System.out.print(aTrvl.getTrvl_content() + ",");
				System.out.print(aTrvl.getTrvl_count() + ",");
				System.out.print(aTrvl.getTrvl_score() + ",");
				System.out.println("------------------------------");
			}
		 
	}
	
}