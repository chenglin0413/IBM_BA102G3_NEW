package com.init.controller;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public static void main(String[] args) {
		CheckDate();
	}

	public static List<StpmVO> CheckDate() {

		List<StpmVO> list = new ArrayList<StpmVO>();

		StpmVO stpmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_for_Stpmup = null;
		PreparedStatement pstmt_for_Prpmup = null;

		ResultSet rs = null;

		try {

			con = ds.getConnection();
			// Class.forName(driver);
			// con = DriverManager.getConnection(url, userid, passwd);
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

			java.sql.Date day = new java.sql.Date(System.currentTimeMillis()); // 今天

			for (StpmVO data : list) {

				java.sql.Date stpm_enddate = data.getStpm_enddate(); // 結束天

				if (stpm_enddate.toString().equals(day.toString())) {

					pstmt_for_Stpmup = con.prepareStatement(UPDATE_STPM);

					pstmt_for_Stpmup.setString(1, stpmVO.getStpm_name());
					pstmt_for_Stpmup.setString(2, stpmVO.getStpm_desc());
					pstmt_for_Stpmup.setString(3, stpmVO.getStpm_content());
					pstmt_for_Stpmup.setDate(4, stpmVO.getStpm_startdate());
					pstmt_for_Stpmup.setDate(5, stpmVO.getStpm_enddate());
					pstmt_for_Stpmup.setInt(6, 0); // 狀態改0
					pstmt_for_Stpmup.setInt(7, stpmVO.getStpm_id());

					pstmt_for_Stpmup.executeUpdate();
					pstmt_for_Stpmup.close();

					// 同時修改prpm

					List<PrpmVO> prpmVO = Update();
					for (PrpmVO prpm : prpmVO) {
						if (prpm.getStpm_id().equals(data.getStpm_id())) {
							System.out.println(prpm.getStpm_id() + " " + data.getStpm_id());

							pstmt_for_Prpmup = con.prepareStatement(UPDATE_PRPM);

							pstmt_for_Prpmup.setInt(3, prpm.getStpm_id());
							pstmt_for_Prpmup.setInt(4, prpm.getProd_id());
							pstmt_for_Prpmup.setInt(1, prpm.getPrpm_price());
							pstmt_for_Prpmup.setInt(2, 0);

							pstmt_for_Prpmup.executeUpdate();
							pstmt_for_Prpmup.close();

						}
					}
				}
			}

//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null ) {
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

	public static List<PrpmVO> Update() {

		List<PrpmVO> list = new ArrayList<PrpmVO>();
		PrpmVO prpmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;

		PreparedStatement pstmt_for_up = null;

		ResultSet rs = null;

		try {

			 con = ds.getConnection();
			// Class.forName(driver);
			// con = DriverManager.getConnection(url, userid, passwd);
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

			// } catch (ClassNotFoundException e) {
			// throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
