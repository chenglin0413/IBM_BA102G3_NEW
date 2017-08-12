package com.busc.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BuscDAO implements BuscDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO BUSC(BUSC_ID,BUSC_END,BUSC_LINE,BUSC_TIME,BUSC_ROUTE)"
			+ "VALUES(BUSC_ID_SEQ.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT BUSC_ID,BUSC_END,BUSC_LINE,BUSC_TIME,BUSC_ROUTE FROM BUSC ORDER BY BUSC_ID";
	private static final String GET_ONE_STMT = "SELECT BUSC_ID,BUSC_END,BUSC_LINE,BUSC_TIME,BUSC_ROUTE FROM BUSC WHERE BUSC_ID = ?";
	private static final String DELETETABLE = "DELETE FROM BUSC";

	@Override
	public void insert(BuscVO busVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, busVO.getBusc_id());
			pstmt.setString(2, busVO.getBusc_end());
			pstmt.setString(3, busVO.getBusc_line());
			pstmt.setString(4, busVO.getBusc_time());
			pstmt.setString(5, busVO.getBusc_route());

			pstmt.executeUpdate();

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
	public void deleteTable(BuscVO busVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(DELETETABLE);

			pstmt.executeUpdate();

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
	public BuscVO findByPrimaryKey(Integer bus_id) {

		BuscVO busVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, bus_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				busVO = new BuscVO();
				busVO.setBusc_id(rs.getInt("busc_id"));
				busVO.setBusc_end(rs.getString("busc_end"));
				busVO.setBusc_line(rs.getString("busc_line"));
				busVO.setBusc_time(rs.getString("busc_time"));
				busVO.setBusc_route(rs.getString("busc_route"));
			}

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
		return busVO;
	}

	@Override
	public List<BuscVO> getAll() {
		List<BuscVO> list = new ArrayList<BuscVO>();
		BuscVO busVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				busVO = new BuscVO();
				busVO.setBusc_end(rs.getString("busc_end"));
				busVO.setBusc_line(rs.getString("busc_line"));
				busVO.setBusc_time(rs.getString("busc_time"));
				busVO.setBusc_route(rs.getString("busc_route"));
				list.add(busVO); // Store the row in the list
			}

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

}