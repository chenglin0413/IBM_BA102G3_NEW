package com.tlcm.model;

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

public class TlcmJNDIDAO implements TlcmDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Tlcm (tlcm_id,trvl_id,user_id,tlcm_date,tlcm_content) "
			+ "VALUES (TLCM_ID_SEQ.NEXTVAL, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE Tlcm set trvl_id=?, user_id=?, tlcm_date=?, tlcm_content=? where tlcm_id=?";
	private static final String DELETE = "DELETE FROM Tlcm where tlcm_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Tlcm where tlcm_id = ?";
	private static final String GET_BY_FK = "SELECT * FROM Tlcm where trvl_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Tlcm";

	@Override
	public void insert(TlcmVO tlcmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, tlcmVO.getTrvl_id());
			pstmt.setInt(2, tlcmVO.getUser_id());
			pstmt.setDate(3, tlcmVO.getTlcm_date());
			pstmt.setString(4, tlcmVO.getTlcm_content());

			pstmt.executeUpdate();

			con.commit();
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
	public void update(TlcmVO tlcmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, tlcmVO.getTrvl_id());
			pstmt.setInt(2, tlcmVO.getUser_id());
			pstmt.setDate(3, tlcmVO.getTlcm_date());
			pstmt.setString(4, tlcmVO.getTlcm_content());
			pstmt.setInt(5, tlcmVO.getTlcm_id());

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
	public void delete(Integer tlcm_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tlcm_id);

			pstmt.executeUpdate();
			con.commit();
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
	public TlcmVO findByPrimaryKey(Integer tlcm_id) {

		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tlcm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));

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

		return tlcmVO;
	}

	@Override
	public List<TlcmVO> findByForeignKey(Integer trvl_id) {
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(GET_BY_FK);

			pstmt.setInt(1, trvl_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));
				list.add(tlcmVO);
			}
			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

				e.printStackTrace();
			}
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
	public List<TlcmVO> getAll() {
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("Tlcm_ID"));
				tlcmVO.setTrvl_id(rs.getInt("Trvl_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("Tlcm_DATE"));
				tlcmVO.setTlcm_content(rs.getString("Tlcm_CONTENT"));

				list.add(tlcmVO); // Store the row in the list
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
}