package com.reta.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetaJDBCDAO implements RetaDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO RETA "
			+ "(RETA_ID, AVTB_ID, USER_ID, RETA_NUMBER, RETA_STATUS, RETA_GRANT, RETA_DATE, RETA_RANK_RES, RETA_REVIEW, RETA_REVIEWDATE, REST_RPDATE, REST_RPCOMM, REST_RPSTATUS) "
			+ "VALUES (reta_sq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE RETA SET AVTB_ID=?, USER_ID=?, RETA_NUMBER=?, RETA_STATUS=?, RETA_GRANT=?, RETA_DATE=?, RETA_RANK_RES=?, RETA_REVIEW=?, RETA_REVIEWDATE=?, REST_RPDATE=?, REST_RPCOMM=?, REST_RPSTATUS=? WHERE RETA_ID = ?";
	private static final String DELETE = "DELETE FROM RETA WHERE RETA_ID=?";
	private static final String GET_ONE_STMT ="SELECT RETA_ID,AVTB_ID,USER_ID FROM RETA WHERE RETA_ID = ?";
	private static final String GET_ONE_STMT_USERID ="SELECT * FROM RETA WHERE USER_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM RETA";
	private static final String GET_ALL_BY_REST_ID_STMT = "SELECT * FROM RETA WHERE USER_ID=?";
	@Override
	public void insert(RetaVO retaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, retaVO.getAvtb_id());
			pstmt.setInt(2, retaVO.getUser_id());
			pstmt.setInt(3, retaVO.getReta_number());
			pstmt.setInt(4, retaVO.getReta_status());
			pstmt.setInt(5, retaVO.getReta_grant());
			pstmt.setDate(6, retaVO.getReta_date());
			pstmt.setInt(7, retaVO.getReta_rank_res());
			pstmt.setString(8, retaVO.getReta_review());
			pstmt.setDate(9, retaVO.getReta_reviewdate());
			pstmt.setDate(10, retaVO.getRest_rpdate());
			pstmt.setString(11,retaVO.getRest_rpcomm());
			pstmt.setDouble(12, retaVO.getRest_rpstatus());
			
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(RetaVO retaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, retaVO.getAvtb_id());
			pstmt.setInt(2, retaVO.getUser_id());
			pstmt.setInt(3, retaVO.getReta_number());
			pstmt.setInt(4, retaVO.getReta_status());
			pstmt.setInt(5, retaVO.getReta_grant());
			pstmt.setDate(6, retaVO.getReta_date());
			pstmt.setInt(7, retaVO.getReta_rank_res());
			pstmt.setString(8, retaVO.getReta_review());
			pstmt.setDate(9, retaVO.getReta_reviewdate());
			pstmt.setDate(10, retaVO.getRest_rpdate());
			pstmt.setString(11,retaVO.getRest_rpcomm());
			pstmt.setDouble(12, retaVO.getRest_rpstatus());
			pstmt.setDouble(13, retaVO.getReta_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer reta_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reta_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public RetaVO findByPrimaryKey(Integer reta_id) {
		RetaVO retaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reta_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				retaVO.setUser_id(rs.getInt("user_id"));
//				retaVO.setReta_number(rs.getInt("reta_number"));
//				retaVO.setReta_status(rs.getInt("reta_status"));
//				retaVO.setReta_grant(rs.getInt("reta_grant"));
//				retaVO.setReta_date(rs.getDate("reta_date"));
//				retaVO.setReta_rank_res(rs.getInt("reta_rank_res"));
//				retaVO.setReta_review(rs.getString("reta_review"));
//				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
//				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
//				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return retaVO;
	}

	@Override
	public List<RetaVO> getAll() {
		List<RetaVO> list = new ArrayList<RetaVO>();
		RetaVO retaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				retaVO.setUser_id(rs.getInt("user_id"));
				retaVO.setReta_number(rs.getInt("reta_number"));
				retaVO.setReta_status(rs.getInt("reta_status"));
				retaVO.setReta_grant(rs.getInt("reta_grant"));
				retaVO.setReta_date(rs.getDate("reta_date"));
				retaVO.setReta_rank_res(rs.getInt("reta_rank_res"));
				retaVO.setReta_review(rs.getString("reta_review"));
				retaVO.setReta_reviewdate(rs.getDate("reta_reviewdate"));
				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
				list.add(retaVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public static void main(String[] args){
		RetaJDBCDAO dao = new RetaJDBCDAO();
		
		//新增
//		RetaVO retain = new RetaVO();
//		retain.setAvtb_id(3500006);
//		retain.setUser_id(1000044);
//		retain.setReta_number(5);
//		retain.setReta_status(0);
//		retain.setReta_grant(1);
//		retain.setReta_date(java.sql.Date.valueOf("2017-08-07"));
//		retain.setReta_rank_res(2);
//		retain.setReta_review("3213213213212");
//		retain.setReta_reviewdate(java.sql.Date.valueOf("2017-08-09"));
//		retain.setRest_rpdate(java.sql.Date.valueOf("2017-08-10"));
//		retain.setRest_rpcomm("還好");
//		retain.setRest_rpstatus(2);
//		dao.insert(retain);
		
		//修改
//		RetaVO retaup = new RetaVO();
//		retaup.setAvtb_id(3500006);
//		retaup.setUser_id(1000044);
//		retaup.setReta_number(5);
//		retaup.setReta_status(0);
//		retaup.setReta_grant(1);
//		retaup.setReta_date(java.sql.Date.valueOf("2017-08-07"));
//		retaup.setReta_rank_res(2);
//		retaup.setReta_review("ok");
//		retaup.setReta_reviewdate(java.sql.Date.valueOf("2017-08-09"));
//		retaup.setRest_rpdate(java.sql.Date.valueOf("2017-08-10"));
//		retaup.setRest_rpcomm("soso");
//		retaup.setRest_rpstatus(2);
//		retaup.setReta_id(3400011);
//		dao.update(retaup);
//		
		//刪除
//		dao.delete(3400011);
		
		//查詢一筆
		RetaVO retaVOPK = dao.findRetaByUserId(1000033);
		System.out.print("訂位編號:"+retaVOPK.getReta_id() + "\n");
		System.out.print("時段編號:"+retaVOPK.getAvtb_id() + ",");
		System.out.print("使用者編號:"+retaVOPK.getUser_id() + "。");
		System.out.print("訂位人數:"+retaVOPK.getReta_number()+ ",");
		System.out.print("到位狀況:"+retaVOPK.getReta_status()+ ",");
		System.out.print("餐廳審核訂位狀態:"+retaVOPK.getReta_grant()+ ",");
		System.out.print("訂位成立日期:"+retaVOPK.getReta_date()+ ",");
		System.out.println();
		
		
		//查詢
//		List<RetaVO> list = dao.findByUserId(1000035);
//		for (RetaVO areta : list) {
//			System.out.print("訂位編號:"+areta.getReta_id()+ "\n");
//			System.out.print("時段編號:"+areta.getAvtb_id()+ ",");
//			System.out.print("使用者編號:"+areta.getUser_id()+ ",");
//			System.out.print("訂位人數:"+areta.getReta_number()+ ",");
//			System.out.print("到位狀況:"+areta.getReta_status()+ ",");
//			System.out.print("餐廳審核訂位狀態:"+areta.getReta_grant()+ ",");
//			System.out.print("訂位成立日期:"+areta.getReta_date()+ ",");
//			System.out.print("對餐廳評分:"+areta.getReta_rank_res()+ ",");
//			System.out.print("用餐感想:"+areta.getReta_review()+ ",");
//			System.out.print("填寫用餐感想日期:"+areta.getReta_reviewdate()+ ",");
//			System.out.print("餐廳檢舉訂位日期:"+areta.getRest_rpdate()+ ",");
//			System.out.print("檢舉訂位理由:"+areta.getRest_rpcomm()+ ",");
//			System.out.print("餐廳檢舉訂位狀態:"+areta.getRest_rpstatus()+ "");
//			System.out.println();
//			System.out.println("-------------");
//		}
	}

	@Override
	public List<RetaVO> findByUserId(Integer user_id) {
		List<RetaVO> list = new ArrayList<RetaVO>();
		RetaVO retaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_REST_ID_STMT);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				retaVO.setUser_id(rs.getInt("user_id"));
				retaVO.setReta_number(rs.getInt("reta_number"));
				retaVO.setReta_status(rs.getInt("reta_status"));
				retaVO.setReta_grant(rs.getInt("reta_grant"));
				retaVO.setReta_date(rs.getDate("reta_date"));
				retaVO.setReta_rank_res(rs.getInt("reta_rank_res"));
				retaVO.setReta_review(rs.getString("reta_review"));
				retaVO.setReta_reviewdate(rs.getDate("reta_reviewdate"));
				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
				list.add(retaVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<RetaVO> getAllRetaByRestID(Integer rest_id) {
		List<RetaVO> list = new ArrayList<RetaVO>();
		RetaVO retaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_REST_ID_STMT);
			pstmt.setInt(1, rest_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				retaVO.setUser_id(rs.getInt("user_id"));
				retaVO.setReta_number(rs.getInt("reta_number"));
				retaVO.setReta_status(rs.getInt("reta_status"));
				retaVO.setReta_grant(rs.getInt("reta_grant"));
				retaVO.setReta_date(rs.getDate("reta_date"));
				retaVO.setReta_rank_res(rs.getInt("reta_rank_res"));
				retaVO.setReta_review(rs.getString("reta_review"));
				retaVO.setReta_reviewdate(rs.getDate("reta_reviewdate"));
				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
				list.add(retaVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public RetaVO findRetaByUserId(Integer user_id) {
		RetaVO retaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_USERID);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				retaVO.setUser_id(rs.getInt("user_id"));
				retaVO.setReta_number(rs.getInt("reta_number"));
				retaVO.setReta_status(rs.getInt("reta_status"));
				retaVO.setReta_grant(rs.getInt("reta_grant"));
				retaVO.setReta_date(rs.getDate("reta_date"));
//				retaVO.setReta_rank_res(rs.getInt("reta_rank_res"));
//				retaVO.setReta_review(rs.getString("reta_review"));
//				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
//				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
//				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return retaVO;
	}

}
