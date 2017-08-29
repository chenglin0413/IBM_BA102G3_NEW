package com.init.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.prpm.model.*;
import com.repm.model.*;
import com.stpm.model.*;

public class CheckDate {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// static String driver = "oracle.jdbc.driver.OracleDriver";
	// static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	// static String userid = "BA102G3";
	// static String passwd = "123456";

	private static final String CHECK_STPM = "SELECT * FROM STPM";
	private static final String UPDATE_STPM = "UPDATE STPM set stpm_name=?,stpm_desc=?,stpm_content=?,stpm_startdate=?,stpm_enddate=?,stpm_status=? where stpm_id = ?";

	private static final String CHECK_PRPM = "SELECT * FROM PRPM";
	private static final String UPDATE_PRPM = "UPDATE PRPM set prpm_price=?,prpm_status=? where stpm_id =? and prod_id = ?";

	private static final String CHECK_REPM = "SELECT * FROM REPM";
	private static final String UPDATE_REPM = "UPDATE REPM set repm_name=?,repm_desc=?,repm_content=?,repm_startdate=?,repm_enddate=?,repm_status=? where repm_id = ?";

	public static void main(String[] args) {
	}

	// stpm_update
	public static List<StpmVO> updateSTPM() {

		List<StpmVO> list = new ArrayList<StpmVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_status = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STPM);

			List<StpmVO> checkDate = checkSTPM();
			for (StpmVO stpmVO : checkDate) {

				java.sql.Date day = new java.sql.Date(System.currentTimeMillis()); // 今天
				java.sql.Date stpm_enddate = stpmVO.getStpm_enddate(); // 結束天

				if (stpm_enddate.toString().equals(day.toString())) {

					pstmt.setString(1, stpmVO.getStpm_name());
					pstmt.setString(2, stpmVO.getStpm_desc());
					pstmt.setString(3, stpmVO.getStpm_content());
					pstmt.setDate(4, stpmVO.getStpm_startdate());
					pstmt.setDate(5, stpmVO.getStpm_enddate());
					pstmt.setInt(6, 0); // 狀態改0
					pstmt.setInt(7, stpmVO.getStpm_id());
					pstmt.executeUpdate();

					pstmt_status = con.prepareStatement(UPDATE_PRPM);

					List<PrpmVO> prpm = updatePRPM();
					for (PrpmVO prpmVO : prpm) {

						if (prpmVO.getStpm_id().equals(stpmVO.getStpm_id())) {

							pstmt_status.setInt(3, prpmVO.getStpm_id());
							pstmt_status.setInt(4, prpmVO.getProd_id());
							pstmt_status.setInt(1, prpmVO.getPrpm_price());
							pstmt_status.setInt(2, 0);

							pstmt_status.executeUpdate();
						}
					}

				} else {
					pstmt.setString(1, stpmVO.getStpm_name());
					pstmt.setString(2, stpmVO.getStpm_desc());
					pstmt.setString(3, stpmVO.getStpm_content());
					pstmt.setDate(4, stpmVO.getStpm_startdate());
					pstmt.setDate(5, stpmVO.getStpm_enddate());
					pstmt.setInt(6, stpmVO.getStpm_status()); // 原狀態
					pstmt.setInt(7, stpmVO.getStpm_id());
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || pstmt_status != null) {
				try {
					pstmt.close();
					pstmt_status.close();
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

	// select_stpm
	public static List<StpmVO> checkSTPM() {

		List<StpmVO> list = new ArrayList<StpmVO>();

		StpmVO stpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_STPM);

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
				list.add(stpmVO);
			}

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

	// select_prpm_status
	public static List<PrpmVO> updatePRPM() {

		List<PrpmVO> list = new ArrayList<PrpmVO>();

		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_PRPM);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prpmVO = new PrpmVO();
				prpmVO.setStpm_id(rs.getInt("stpm_id"));
				prpmVO.setProd_id(rs.getInt("prod_id"));
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));
				prpmVO.setPrpm_status(rs.getInt("prpm_status"));
				list.add(prpmVO);
			}

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

	// repm_update
	public static List<RepmVO> updateREPM() {

		List<RepmVO> list = new ArrayList<RepmVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_status = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REPM);

			List<RepmVO> checkDate = checkREPM();
			for (RepmVO repmVO : checkDate) {

				java.sql.Date day = new java.sql.Date(System.currentTimeMillis()); // 今天
				java.sql.Date repm_enddate = repmVO.getRepm_enddate(); // 結束天

				if (repm_enddate.toString().equals(day.toString())) {

					pstmt.setString(1, repmVO.getRepm_name());
					pstmt.setString(2, repmVO.getRepm_desc());
					pstmt.setString(3, repmVO.getRepm_content());
					pstmt.setDate(4, repmVO.getRepm_startdate());
					pstmt.setDate(5, repmVO.getRepm_enddate());
					pstmt.setInt(6, 0); // 狀態改0
					pstmt.setInt(7, repmVO.getRepm_id());
					pstmt.executeUpdate();

				} else {
					pstmt.setString(1, repmVO.getRepm_name());
					pstmt.setString(2, repmVO.getRepm_desc());
					pstmt.setString(3, repmVO.getRepm_content());
					pstmt.setDate(4, repmVO.getRepm_startdate());
					pstmt.setDate(5, repmVO.getRepm_enddate());
					pstmt.setInt(6, repmVO.getRepm_status()); // 原狀態
					pstmt.setInt(7, repmVO.getRepm_id());
					pstmt.executeUpdate();
				}
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || pstmt_status != null) {
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

	// select_prpm
	public static List<RepmVO> checkREPM() {

		List<RepmVO> list = new ArrayList<RepmVO>();

		RepmVO repmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_REPM);

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
				list.add(repmVO);
			}

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
}
