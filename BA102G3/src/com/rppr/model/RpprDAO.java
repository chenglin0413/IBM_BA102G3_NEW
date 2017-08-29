package com.rppr.model;

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

public class RpprDAO implements RpprDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Rppr (rppr_id,prod_id,user_id,rppr_date,rppr_tittle,rppr_content)"
			+ "VALUES (RPPR_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Rppr set prod_id=? ,user_id=?, rppr_date=?, rppr_status=?, rppr_tittle=?,RPPR_content=? where rppr_id = ?";
	private static final String DELETE = "DELETE FROM Rppr where rppr_id=?";
	private static final String GET_ONE_STMT = "SELECT * FROM Rppr where rppr_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Rppr order by rppr_status";
	private static final String GET_ALL_BY_STATUS_STMT = "SELECT * FROM Rppr where rppr_status=? order by rppr_date desc";
	
	private static final String UPDATE_STATUS = "UPDATE Rppr set rppr_status= ? where rppr_id = ?";

	@Override
	public void insert(RpprVO rpprVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rpprVO.getProd_id());
			pstmt.setInt(2, rpprVO.getUser_id());
			pstmt.setTimestamp(3, rpprVO.getRppr_date());
			pstmt.setString(4, rpprVO.getRppr_tittle());
			pstmt.setString(5, rpprVO.getRppr_content());

			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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
	public void update(RpprVO rpprVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rpprVO.getProd_id());
			pstmt.setInt(2, rpprVO.getUser_id());
			pstmt.setTimestamp(3, rpprVO.getRppr_date());
			pstmt.setInt(4, rpprVO.getRppr_status());
			pstmt.setString(5, rpprVO.getRppr_tittle());
			pstmt.setString(6, rpprVO.getRppr_content());
			pstmt.setInt(7, rpprVO.getRppr_id());
			pstmt.executeUpdate();

			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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
	public void delete(Integer rppr_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rppr_id);

			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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
	public RpprVO findByPrimaryKey(Integer rppr_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		RpprVO rpprVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rppr_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rpprVO = new RpprVO();
				rpprVO.setRppr_id(rs.getInt("RPPR_ID"));
				rpprVO.setProd_id(rs.getInt("PROD_ID"));
				rpprVO.setUser_id(rs.getInt("USER_ID"));
				rpprVO.setRppr_date(rs.getTimestamp("RPPR_DATE"));
				rpprVO.setRppr_status(rs.getInt("RPPR_STATUS"));
				rpprVO.setRppr_tittle(rs.getString("RPPR_TITTLE"));
				rpprVO.setRppr_content(rs.getString("RPPR_CONTENT"));
			}
			// Handle any driver errors
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
		return rpprVO;
	}

	@Override
	public List<RpprVO> getAll() {
		// TODO Auto-generated method stub
		List<RpprVO> list = new ArrayList<RpprVO>();
		RpprVO rpprVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rpprVO = new RpprVO();
				rpprVO.setRppr_id(rs.getInt("RPPR_ID"));
				rpprVO.setProd_id(rs.getInt("PROD_ID"));
				rpprVO.setUser_id(rs.getInt("USER_ID"));
				rpprVO.setRppr_date(rs.getTimestamp("RPPR_DATE"));
				rpprVO.setRppr_status(rs.getInt("RPPR_STATUS"));
				rpprVO.setRppr_tittle(rs.getString("RPPR_TITTLE"));
				rpprVO.setRppr_content(rs.getString("RPPR_CONTENT"));

				list.add(rpprVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<RpprVO> getAllByStatus(Integer rppr_status) {
		// TODO Auto-generated method stub
		List<RpprVO> list = new ArrayList<RpprVO>();
		RpprVO rpprVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS_STMT);
			pstmt.setInt(1, rppr_status);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				rpprVO = new RpprVO();
				rpprVO.setRppr_id(rs.getInt("RPPR_ID"));
				rpprVO.setProd_id(rs.getInt("PROD_ID"));
				rpprVO.setUser_id(rs.getInt("USER_ID"));
				rpprVO.setRppr_date(rs.getTimestamp("RPPR_DATE"));
				rpprVO.setRppr_status(rs.getInt("RPPR_STATUS"));
				rpprVO.setRppr_tittle(rs.getString("RPPR_TITTLE"));
				rpprVO.setRppr_content(rs.getString("RPPR_CONTENT"));

				list.add(rpprVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public void updateStatus(Integer rppr_id,Integer rppr_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, rppr_status);
			pstmt.setInt(2, rppr_id);
			pstmt.executeUpdate();

			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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

}
