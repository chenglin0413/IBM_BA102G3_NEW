package com.stpm.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class StpmDAO implements StpmDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO STPM (stpm_id,store_id,stpm_name,stpm_desc,"
			+ "stpm_content,stpm_startdate,stpm_enddate,stpm_status) VALUES (STPM_ID_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT stpm_id,store_id,stpm_name,stpm_desc,"
			+ "stpm_content,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm order by stpm_id desc";
	private static final String GET_ONE_STMT = "SELECT stpm_id,store_id,stpm_name,stpm_desc,"
			+ "stpm_content,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm where stpm_id = ?";
	private static final String UPDATE = "UPDATE stpm set stpm_name=?,stpm_desc=?,stpm_content=?,stpm_startdate=?,stpm_enddate=?,stpm_status= ? where stpm_id = ?";
	private static final String MYSTPM = "SELECT stpm_id,store_id,stpm_name,to_char(stpm_startdate,'yyyy-mm-dd') stpm_startdate,to_char(stpm_enddate,'yyyy-mm-dd') stpm_enddate,stpm_status FROM stpm WHERE store_id = ? order by stpm_id desc";

	@Override
	public void insert(StpmVO stpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, stpmVO.getStore_id());
			pstmt.setString(2, stpmVO.getStpm_name());
			pstmt.setString(3, stpmVO.getStpm_desc());
			pstmt.setString(4, stpmVO.getStpm_content());
			pstmt.setDate(5, stpmVO.getStpm_startdate());
			pstmt.setDate(6, stpmVO.getStpm_enddate());
			pstmt.setInt(7, stpmVO.getStpm_status());

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
	public void update(StpmVO stpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, stpmVO.getStpm_name());
			pstmt.setString(2, stpmVO.getStpm_desc());
			pstmt.setString(3, stpmVO.getStpm_content());
			pstmt.setDate(4, stpmVO.getStpm_startdate());
			pstmt.setDate(5, stpmVO.getStpm_enddate());
			pstmt.setInt(6, stpmVO.getStpm_status());
			pstmt.setInt(7, stpmVO.getStpm_id());

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
	public StpmVO findByPrimaryKey(Integer stpm_id) {

		StpmVO stpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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