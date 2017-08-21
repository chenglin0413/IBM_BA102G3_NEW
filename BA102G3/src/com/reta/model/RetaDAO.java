package com.reta.model;

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

public class RetaDAO implements RetaDAO_Interface {

	private static final String INSERT_STMT = "INSERT INTO RETA "
			+ "(RETA_ID, AVTB_ID, USER_ID, RETA_NUMBER, RETA_STATUS, RETA_GRANT, RETA_DATE, RETA_RANK_RES, RETA_REVIEW, RETA_REVIEWDATE, REST_RPDATE, REST_RPCOMM, REST_RPSTATUS) "
			+ "VALUES (reta_sq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE RETA SET AVTB_ID=?, USER_ID=?, RETA_NUMBER=?, RETA_STATUS=?, RETA_GRANT=?, RETA_DATE=?, RETA_RANK_RES=?, RETA_REVIEW=?, RETA_REVIEWDATE=?, REST_RPDATE=?, REST_RPCOMM=?, REST_RPSTATUS=? WHERE RETA_ID = ?";
	private static final String DELETE = "DELETE FROM RETA WHERE RETA_ID=?";
	private static final String GET_ONE_STMT ="SELECT * FROM RETA WHERE RETA_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM RETA";
	private static final String GET_ALL_BY_USER_ID_STMT = "SELECT * FROM RETA WHERE USER_ID=?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(RetaVO retaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, reta_id);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
		RetaVO retaVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reta_id);
			
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
				retaVO.setRest_rpdate(rs.getDate("rest_rpdate"));
				retaVO.setRest_rpcomm(rs.getString("rest_rpcomm"));
				retaVO.setRest_rpstatus(rs.getInt("rest_rpstatus"));
			}

			// Handle any driver errors
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

			con = ds.getConnection();
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
		}catch (SQLException se) {
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

	@Override
	public List<RetaVO> findByUserId(Integer user_id) {
		List<RetaVO> list = new ArrayList<RetaVO>();
		RetaVO retaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_USER_ID_STMT);

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
