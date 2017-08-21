package com.flsc.model;

import java.util.*;
import java.sql.*;

public class FlscJDBCDAO implements FlscDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO FLSC(FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,FLSC_LOCATION_E,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN)"
			+ "VALUES(FLSC_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN FROM FLSC ORDER BY FLSC_ID";
	private static final String GET_ONE_FLNO = "SELECT FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN FROM FLSC WHERE FLSC_FLNO = ?";
	private static final String GET_ONE_AIRLINECODE = "SELECT FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN FROM FLSC WHERE FLSC_AIRLINECODE = ?";
	private static final String GET_SUBQUERY = "SELECT FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN FROM FLSC "
			+ "WHERE FLSC_AIRLINECODE=? AND FLSC_FLNO=? AND FLSC_SDATE=?";
	private static final String DELETETABLE = "DELETE FROM FLSC";

	@Override
	public void insert(FlscVO flscVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, flscVO.getFlsc_term());
			pstmt.setString(2, flscVO.getFlsc_airlinecode());
			pstmt.setString(3, flscVO.getFlsc_airline_c());
			pstmt.setString(4, flscVO.getFlsc_flno());
			pstmt.setString(5, flscVO.getFlsc_gate());
			pstmt.setString(6, flscVO.getFlsc_sdate());
			pstmt.setString(7, flscVO.getFlsc_stime());
			pstmt.setString(8, flscVO.getFlsc_pdate());
			pstmt.setString(9, flscVO.getFlsc_ptime());
			pstmt.setString(10, flscVO.getFlsc_location_code());
			pstmt.setString(11, flscVO.getFlsc_location_code());
			pstmt.setString(12, flscVO.getFlsc_location_c());
			pstmt.setString(13, flscVO.getFlsc_status());
			pstmt.setString(14, flscVO.getFlsc_bag());
			pstmt.setString(15, flscVO.getFlsc_checkin());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void deleteTable(FlscVO flscVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(DELETETABLE);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<FlscVO> findByFlno(String flsc_flno) {
		List<FlscVO> list = new ArrayList<FlscVO>();
		FlscVO flscVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FLNO);

			pstmt.setString(1, flsc_flno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				flscVO = new FlscVO();
				flscVO.setFlsc_id(rs.getInt("flsc_id"));
				flscVO.setFlsc_term(rs.getString("flsc_term"));
				flscVO.setFlsc_airlinecode(rs.getString("flsc_airlinecode"));
				flscVO.setFlsc_airline_c(rs.getString("flsc_airline_c"));
				flscVO.setFlsc_flno(rs.getString("flsc_flno"));
				flscVO.setFlsc_gate(rs.getString("flsc_gate"));
				flscVO.setFlsc_sdate(rs.getString("flsc_sdate"));
				flscVO.setFlsc_stime(rs.getString("flsc_stime"));
				flscVO.setFlsc_pdate(rs.getString("flsc_pdate"));
				flscVO.setFlsc_ptime(rs.getString("flsc_ptime"));
				flscVO.setFlsc_location_code(rs.getString("flsc_location_code"));
				flscVO.setFlsc_location_c(rs.getString("flsc_location_c"));
				flscVO.setFlsc_status(rs.getString("flsc_status"));
				flscVO.setFlsc_bag(rs.getString("flsc_bag"));
				flscVO.setFlsc_checkin(rs.getString("flsc_checkin"));
				list.add(flscVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<FlscVO> getAll() {

		List<FlscVO> list = new ArrayList<FlscVO>();
		FlscVO flscVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				flscVO = new FlscVO();
				flscVO.setFlsc_id(rs.getInt("flsc_id"));
				flscVO.setFlsc_term(rs.getString("flsc_term"));
				flscVO.setFlsc_airlinecode(rs.getString("flsc_airlinecode"));
				flscVO.setFlsc_airline_c(rs.getString("flsc_airline_c"));
				flscVO.setFlsc_flno(rs.getString("flsc_flno"));
				flscVO.setFlsc_gate(rs.getString("flsc_gate"));
				flscVO.setFlsc_sdate(rs.getString("flsc_sdate"));
				flscVO.setFlsc_stime(rs.getString("flsc_stime"));
				flscVO.setFlsc_pdate(rs.getString("flsc_pdate"));
				flscVO.setFlsc_ptime(rs.getString("flsc_ptime"));
				flscVO.setFlsc_location_code(rs.getString("flsc_location_code"));
				flscVO.setFlsc_location_c(rs.getString("flsc_location_c"));
				flscVO.setFlsc_status(rs.getString("flsc_status"));
				flscVO.setFlsc_bag(rs.getString("flsc_bag"));
				flscVO.setFlsc_checkin(rs.getString("flsc_checkin"));
				list.add(flscVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	@Override
	public List<FlscVO> findByflsc_airlinecode(String flsc_airlinecode) {
		List<FlscVO> list = new ArrayList<FlscVO>();
		FlscVO flscVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_AIRLINECODE);

			pstmt.setString(1, flsc_airlinecode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				flscVO = new FlscVO();
				flscVO.setFlsc_id(rs.getInt("flsc_id"));
				flscVO.setFlsc_term(rs.getString("flsc_term"));
				flscVO.setFlsc_airlinecode(rs.getString("flsc_airlinecode"));
				flscVO.setFlsc_airline_c(rs.getString("flsc_airline_c"));
				flscVO.setFlsc_flno(rs.getString("flsc_flno"));
				flscVO.setFlsc_gate(rs.getString("flsc_gate"));
				flscVO.setFlsc_sdate(rs.getString("flsc_sdate"));
				flscVO.setFlsc_stime(rs.getString("flsc_stime"));
				flscVO.setFlsc_pdate(rs.getString("flsc_pdate"));
				flscVO.setFlsc_ptime(rs.getString("flsc_ptime"));
				flscVO.setFlsc_location_code(rs.getString("flsc_location_code"));
				flscVO.setFlsc_location_c(rs.getString("flsc_location_c"));
				flscVO.setFlsc_status(rs.getString("flsc_status"));
				flscVO.setFlsc_bag(rs.getString("flsc_bag"));
				flscVO.setFlsc_checkin(rs.getString("flsc_checkin"));
				list.add(flscVO);
			}

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
	public FlscVO flscSubQuery(String flsc_airlinecode, String flsc_flno, String flsc_sdate) {

		FlscVO flscVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SUBQUERY);

			pstmt.setString(1, flsc_airlinecode);
			pstmt.setString(2, flsc_flno);
			pstmt.setString(3, flsc_sdate);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				flscVO = new FlscVO();
				flscVO.setFlsc_id(rs.getInt("flsc_id"));
				flscVO.setFlsc_term(rs.getString("flsc_term"));
				flscVO.setFlsc_airlinecode(rs.getString("flsc_airlinecode"));
				flscVO.setFlsc_airline_c(rs.getString("flsc_airline_c"));
				flscVO.setFlsc_flno(rs.getString("flsc_flno"));
				flscVO.setFlsc_gate(rs.getString("flsc_gate"));
				flscVO.setFlsc_sdate(rs.getString("flsc_sdate"));
				flscVO.setFlsc_stime(rs.getString("flsc_stime"));
				flscVO.setFlsc_pdate(rs.getString("flsc_pdate"));
				flscVO.setFlsc_ptime(rs.getString("flsc_ptime"));
				flscVO.setFlsc_location_code(rs.getString("flsc_location_code"));
				flscVO.setFlsc_location_c(rs.getString("flsc_location_c"));
				flscVO.setFlsc_status(rs.getString("flsc_status"));
				flscVO.setFlsc_bag(rs.getString("flsc_bag"));
				flscVO.setFlsc_checkin(rs.getString("flsc_checkin"));
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
		return flscVO;
	}

	public static void main(String[] args) {

		FlscJDBCDAO dao = new FlscJDBCDAO();

//		 List<FlscVO> list = dao.findByFlno("663");
//		 System.out.println(list);
//		 for (FlscVO f2 : list) {
//		 System.out.println(f2.getFlsc_id());
//		 System.out.println(f2.getFlsc_term());
//		 System.out.println(f2.getFlsc_airlinecode());
//		 System.out.println(f2.getFlsc_airline_c());
//		 System.out.println(f2.getFlsc_flno());
//		 System.out.println(f2.getFlsc_gate());
//		 System.out.println(f2.getFlsc_sdate());
//		 System.out.println(f2.getFlsc_stime());
//		 System.out.println(f2.getFlsc_pdate());
//		 System.out.println(f2.getFlsc_ptime());
//		 System.out.println(f2.getFlsc_location_code());
//		 System.out.println(f2.getFlsc_location_e());
//		 System.out.println(f2.getFlsc_location_c());
//		 System.out.println(f2.getFlsc_status());
//		 System.out.println(f2.getFlsc_bag());
//		 System.out.println(f2.getFlsc_checkin());
//		 System.out.println("---------------------");
//		 }

//		 List<FlscVO> f = dao.findByflsc_airlinecode("TW");
//		
//		 for (FlscVO f2 : f) {
//		 System.out.println(f2.getFlsc_id());
//		 System.out.println(f2.getFlsc_term());
//		 System.out.println(f2.getFlsc_airlinecode());
//		 System.out.println(f2.getFlsc_airline_c());
//		 System.out.println(f2.getFlsc_flno());
//		 System.out.println(f2.getFlsc_gate());
//		 System.out.println(f2.getFlsc_sdate());
//		 System.out.println(f2.getFlsc_stime());
//		 System.out.println(f2.getFlsc_pdate());
//		 System.out.println(f2.getFlsc_ptime());
//		 System.out.println(f2.getFlsc_location_code());
//		 System.out.println(f2.getFlsc_location_e());
//		 System.out.println(f2.getFlsc_location_c());
//		 System.out.println(f2.getFlsc_status());
//		 System.out.println(f2.getFlsc_bag());
//		 System.out.println(f2.getFlsc_checkin());
//		 }
//		 }

		FlscVO f2 = dao.flscSubQuery("TW", "663", "2017/07/30");
		System.out.println(f2.getFlsc_id());
		System.out.println(f2.getFlsc_term());
		System.out.println(f2.getFlsc_airlinecode());
		System.out.println(f2.getFlsc_airline_c());
		System.out.println(f2.getFlsc_flno());
		System.out.println(f2.getFlsc_gate());
		System.out.println(f2.getFlsc_sdate());
		System.out.println(f2.getFlsc_stime());
		System.out.println(f2.getFlsc_pdate());
		System.out.println(f2.getFlsc_ptime());
		System.out.println(f2.getFlsc_location_code());
		System.out.println(f2.getFlsc_location_e());
		System.out.println(f2.getFlsc_location_c());
		System.out.println(f2.getFlsc_status());
		System.out.println(f2.getFlsc_bag());
		System.out.println(f2.getFlsc_checkin());
	}

	@Override
	public List<FlscVO> findByArrive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlscVO findByPK(Integer flsc_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FlscVO> findByOut() {
		// TODO Auto-generated method stub
		return null;
	}
}
