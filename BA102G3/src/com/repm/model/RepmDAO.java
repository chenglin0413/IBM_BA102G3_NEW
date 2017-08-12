package com.repm.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class RepmDAO implements RepmDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO REPM (repm_id,rest_id,repm_name,repm_desc,"
			+ "repm_content,repm_startdate,repm_enddate,repm_status) VALUES (REPM_ID_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT repm_id,rest_id,repm_name,repm_desc,"
			+ "repm_content,to_char(repm_startdate,'yyyy-mm-dd') repm_startdate,to_char(repm_enddate,'yyyy-mm-dd') repm_enddate,repm_status FROM repm order by repm_id desc";
	private static final String GET_ONE_STMT = "SELECT repm_id,rest_id,repm_name,repm_desc,"
			+ "repm_content,to_char(repm_startdate,'yyyy-mm-dd') repm_startdate,to_char(repm_enddate,'yyyy-mm-dd') repm_enddate,repm_status FROM repm where repm_id = ?";
	private static final String UPDATE = "UPDATE repm set repm_name=?,repm_desc=?,repm_content=?,repm_startdate=?,repm_enddate=?,repm_status= ? where repm_id = ?";
	
	@Override
	public void insert(RepmVO repmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, repmVO.getRest_id());
			pstmt.setString(2, repmVO.getRepm_name());
			pstmt.setString(3, repmVO.getRepm_desc());
			pstmt.setString(4, repmVO.getRepm_content());
			pstmt.setDate(5, repmVO.getRepm_startdate());
			pstmt.setDate(6, repmVO.getRepm_enddate());
			pstmt.setInt(7, repmVO.getRepm_status());

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
	public void update(RepmVO repmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, repmVO.getRepm_name());
			pstmt.setString(2, repmVO.getRepm_desc());
			pstmt.setString(3, repmVO.getRepm_content());
			pstmt.setDate(4, repmVO.getRepm_startdate());
			pstmt.setDate(5, repmVO.getRepm_enddate());
			pstmt.setInt(6, repmVO.getRepm_status());
			pstmt.setInt(7, repmVO.getRepm_id());

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
	public RepmVO findByPrimaryKey(Integer repm_id) {

		RepmVO repmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, repm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ¤]ºÙ¬° Domain objects
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

			con = ds.getConnection();
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