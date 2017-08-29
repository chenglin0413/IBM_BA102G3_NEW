package com.repm.model;

import java.util.*;
import java.sql.*;

public class RepmJDBCDAO implements RepmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO REPM (repm_id,rest_id,repm_name,repm_desc,repm_content,repm_startdate,repm_enddate,repm_status) VALUES (REPM_ID_seq.NEXTVAL, ?, ?, ?, ?, ?, ?,　?)";
	private static final String GET_ALL_STMT = "SELECT repm_id,rest_id,repm_name,repm_desc,repm_content,to_char(repm_startdate,'yyyy-mm-dd') repm_startdate,to_char(repm_enddate,'yyyy-mm-dd') repm_enddate,repm_status FROM repm order by repm_id";
	private static final String GET_ONE_STMT = "SELECT repm_id,rest_id,repm_name,repm_desc,repm_content,to_char(repm_startdate,'yyyy-mm-dd') repm_startdate,to_char(repm_enddate,'yyyy-mm-dd') repm_enddate,repm_status FROM repm where repm_id = ?";
	private static final String UPDATE = "UPDATE repm set repm_name=?,repm_desc=?,repm_content=?,repm_startdate=?,repm_enddate=?,repm_status=? where repm_id = ?";

	@Override
	public void insert(RepmVO repmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, repmVO.getRest_id());
			pstmt.setString(2, repmVO.getRepm_name());
			pstmt.setString(3, repmVO.getRepm_desc());
			pstmt.setString(4, repmVO.getRepm_content());
			pstmt.setDate(5, repmVO.getRepm_startdate());
			pstmt.setDate(6, repmVO.getRepm_enddate());
			pstmt.setInt(7, repmVO.getRepm_status());

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
	public void update(RepmVO repmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, repmVO.getRepm_name());
			pstmt.setString(2, repmVO.getRepm_desc());
			pstmt.setString(3, repmVO.getRepm_content());
			pstmt.setDate(4, repmVO.getRepm_startdate());
			pstmt.setDate(5, repmVO.getRepm_enddate());
			pstmt.setInt(6, repmVO.getRepm_status());
			pstmt.setInt(7, repmVO.getRepm_id());

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
	public RepmVO findByPrimaryKey(Integer repm_id) {

		RepmVO repmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, repm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				repmVO = new RepmVO();
				repmVO.setRepm_id(rs.getInt("repm_id"));
				repmVO.setRest_id(rs.getInt("rest_id"));
				repmVO.setRepm_name(rs.getString("repm_name"));
				repmVO.setRepm_desc(rs.getString("repm_desc"));
				repmVO.setRepm_content(rs.getString("repm_content"));
				repmVO.setRepm_startdate(rs.getDate("repm_startdate"));
				repmVO.setRepm_enddate(rs.getDate("repm_enddate"));
				repmVO.setRepm_status(rs.getInt("repm_status"));
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
		return repmVO;
	}

	@Override
	public List<RepmVO> getAll() {
		List<RepmVO> list = new ArrayList<RepmVO>();
		RepmVO repmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				repmVO = new RepmVO();
				repmVO.setRepm_id(rs.getInt("repm_id"));
				repmVO.setRest_id(rs.getInt("rest_id"));
				repmVO.setRepm_name(rs.getString("repm_name"));
				repmVO.setRepm_desc(rs.getString("repm_desc"));
				repmVO.setRepm_content(rs.getString("repm_content"));
				repmVO.setRepm_startdate(rs.getDate("repm_startdate"));
				repmVO.setRepm_enddate(rs.getDate("repm_enddate"));
				repmVO.setRepm_status(rs.getInt("repm_status"));
				list.add(repmVO); // Rest the row in the list
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

	public static void main(String[] args) {

		RepmJDBCDAO dao = new RepmJDBCDAO();

		RepmVO s = new RepmVO();
		s.setRest_id(3000005);
		s.setRepm_name("axcc");
		s.setRepm_desc("goo國國gl");
		s.setRepm_content("eee國nd");
		s.setRepm_startdate(java.sql.Date.valueOf("1911-01-01"));
		s.setRepm_enddate(java.sql.Date.valueOf("1999-01-01"));
		s.setRepm_status(1);
		dao.insert(s);

		RepmVO s2 = new RepmVO();
		s2.setRepm_name("11111");
		s2.setRepm_desc("1111國國國1");
		s2.setRepm_content("e221d");
		s2.setRepm_startdate(java.sql.Date.valueOf("1911-03-01"));
		s2.setRepm_enddate(java.sql.Date.valueOf("1999-03-01"));
		s2.setRepm_status(0);
		s2.setRepm_id(3600001);
		dao.update(s2);

		RepmVO s3 = dao.findByPrimaryKey(3600001);
		System.out.print(s3.getRepm_id() + ",");
		System.out.print(s3.getRest_id() + ",");
		System.out.print(s3.getRepm_name() + ",");
		System.out.print(s3.getRepm_desc() + ",");
		System.out.print(s3.getRepm_content() + ",");
		System.out.print(s3.getRepm_startdate() + ",");
		System.out.print(s3.getRepm_enddate() + ",");
		System.out.println(s3.getRepm_status());
		System.out.println("---------------------");

		List<RepmVO> list = dao.getAll();
		for (RepmVO s4 : list) {
			System.out.print(s4.getRepm_id() + ",");
			System.out.print(s4.getRest_id() + ",");
			System.out.print(s4.getRepm_name() + ",");
			System.out.print(s4.getRepm_desc() + ",");
			System.out.print(s4.getRepm_content() + ",");
			System.out.print(s4.getRepm_startdate() + ",");
			System.out.print(s4.getRepm_enddate() + ",");
			System.out.println(s4.getRepm_status());
			System.out.println("---------------------");
		}

	}

	@Override
	public List<RepmVO> findByRestID(Integer rest_id) {
		// TODO Auto-generated method stub
		return null;
	}
}