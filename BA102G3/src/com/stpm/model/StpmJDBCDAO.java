package com.stpm.model;

import java.util.*;
import java.sql.*;

public class StpmJDBCDAO implements StpmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO STPM (stpm_id,store_id,stpm_name,stpm_desc,stpm_content,stpm_startdate,stpm_enddate,stpm_status) VALUES (STPM_ID_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT stpm_id,store_id,stpm_name,stpm_desc,stpm_content,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm order by stpm_id";
	private static final String GET_ONE_STMT = "SELECT stpm_id,store_id,stpm_name,stpm_desc,stpm_content,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm where stpm_id = ?";
	private static final String UPDATE = "UPDATE stpm set stpm_name=?,stpm_desc=?,stpm_content=?,stpm_startdate=?,stpm_enddate=?,stpm_status=? where stpm_id = ?";
	private static final String MYSTPM = "SELECT stpm_id,store_id,stpm_name,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm WHERE store_id = ? order by stpm_id desc";

	@Override
	public void insert(StpmVO stpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, stpmVO.getStore_id());
			pstmt.setString(2, stpmVO.getStpm_name());
			pstmt.setString(3, stpmVO.getStpm_desc());
			pstmt.setString(4, stpmVO.getStpm_content());
			pstmt.setDate(5, stpmVO.getStpm_startdate());
			pstmt.setDate(6, stpmVO.getStpm_enddate());
			pstmt.setInt(7, stpmVO.getStpm_status());

			pstmt.executeUpdate();

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
	public void update(StpmVO stpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, stpmVO.getStpm_name());
			pstmt.setString(2, stpmVO.getStpm_desc());
			pstmt.setString(3, stpmVO.getStpm_content());
			pstmt.setDate(4, stpmVO.getStpm_startdate());
			pstmt.setDate(5, stpmVO.getStpm_enddate());
			pstmt.setInt(6, stpmVO.getStpm_status());
			pstmt.setInt(7, stpmVO.getStpm_id());

			pstmt.executeUpdate();

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
	public StpmVO findByPrimaryKey(Integer stpm_id) {

		StpmVO stpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				stpmVO = new StpmVO();
				stpmVO.setStpm_id(rs.getInt("stpm_id"));
				stpmVO.setStore_id(rs.getInt("store_id"));
				stpmVO.setStpm_name(rs.getString("stpm_name"));
				stpmVO.setStpm_desc(rs.getString("stpm_desc"));
				stpmVO.setStpm_content(rs.getString("stpm_content"));
				stpmVO.setStpm_startdate(rs.getDate("stpm_startdate"));
				stpmVO.setStpm_enddate(rs.getDate("stpm_enddate"));
				stpmVO.setStpm_status(rs.getInt("stpm_status"));
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
		return stpmVO;
	}

	@Override
	public List<StpmVO> getAll() {
		List<StpmVO> list = new ArrayList<StpmVO>();
		StpmVO stpmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stpmVO = new StpmVO();
				stpmVO.setStpm_id(rs.getInt("stpm_id"));
				stpmVO.setStore_id(rs.getInt("store_id"));
				stpmVO.setStpm_name(rs.getString("stpm_name"));
				stpmVO.setStpm_desc(rs.getString("stpm_desc"));
				stpmVO.setStpm_content(rs.getString("stpm_content"));
				stpmVO.setStpm_startdate(rs.getDate("stpm_startdate"));
				stpmVO.setStpm_enddate(rs.getDate("stpm_enddate"));
				stpmVO.setStpm_status(rs.getInt("stpm_status"));
				list.add(stpmVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public List<StpmVO> findByStoreID(Integer store_id) {
		List<StpmVO> list = new ArrayList<StpmVO>();
		StpmVO stpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(MYSTPM);

			pstmt.setInt(1, store_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				stpmVO = new StpmVO();
				stpmVO.setStpm_id(rs.getInt("stpm_id"));
				stpmVO.setStore_id(rs.getInt("store_id"));
				stpmVO.setStpm_name(rs.getString("stpm_name"));
				stpmVO.setStpm_startdate(rs.getDate("stpm_startdate"));
				stpmVO.setStpm_enddate(rs.getDate("stpm_enddate"));
				stpmVO.setStpm_status(rs.getInt("stpm_status"));
				list.add(stpmVO);
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

	public static void main(String[] args) {

		StpmJDBCDAO dao = new StpmJDBCDAO();

		// StpmVO s = new StpmVO();
		// s.setStore_id(2000025);
		// s.setStpm_name("axcc");
		// s.setStpm_desc("goo國國gl");
		// s.setStpm_content("eee國nd");
		// s.setStpm_startdate(java.sql.Date.valueOf("1911-01-01"));
		// s.setStpm_enddate(java.sql.Date.valueOf("1999-01-01"));
		// s.setStpm_status(1);
		// dao.insert(s);
		//
		// StpmVO s2 = new StpmVO();
		// s2.setStpm_name("11111");
		// s2.setStpm_desc("1111國國國1");
		// s2.setStpm_content("e221d");
		// s2.setStpm_startdate(java.sql.Date.valueOf("1911-03-01"));
		// s2.setStpm_enddate(java.sql.Date.valueOf("1999-03-01"));
		// s2.setStpm_status(0);
		// s2.setStpm_id(2500011);
		// dao.update(s2);

		// StpmVO s3 = dao.findByPrimaryKey(2500010);
		// System.out.print(s3.getStpm_id() + ",");
		// System.out.print(s3.getStore_id() + ",");
		// System.out.print(s3.getStpm_name() + ",");
		// System.out.print(s3.getStpm_desc() + ",");
		// System.out.print(s3.getStpm_content() + ",");
		// System.out.print(s3.getStpm_startdate() + ",");
		// System.out.print(s3.getStpm_enddate() + ",");
		// System.out.println(s3.getStpm_status());
		// System.out.println("---------------------");

		// List<StpmVO> list = dao.getAll();
		// for (StpmVO s4 : list) {
		// System.out.print(s4.getStpm_id() + ",");
		// System.out.print(s4.getStore_id() + ",");
		// System.out.print(s4.getStpm_name() + ",");
		// System.out.print(s4.getStpm_desc() + ",");
		// System.out.print(s4.getStpm_content() + ",");
		// System.out.print(s4.getStpm_startdate() + ",");
		// System.out.print(s4.getStpm_enddate() + ",");
		// System.out.println(s4.getStpm_status());
		// System.out.println("---------------------");
		// }

		List<StpmVO> list = dao.findByStoreID(2000001);
		for (StpmVO s4 : list) {
			System.out.print(s4.getStpm_id() + ",");
			System.out.print(s4.getStore_id() + ",");
			System.out.print(s4.getStpm_name() + ",");
			System.out.print(s4.getStpm_desc() + ",");
			System.out.print(s4.getStpm_content() + ",");
			System.out.print(s4.getStpm_startdate() + ",");
			System.out.print(s4.getStpm_enddate() + ",");
			System.out.println(s4.getStpm_status());
			System.out.println("---------------------");
		}

	}
}