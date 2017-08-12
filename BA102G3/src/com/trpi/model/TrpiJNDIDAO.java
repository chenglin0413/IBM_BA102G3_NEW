package com.trpi.model;

import java.sql.Blob;
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

public class TrpiJNDIDAO implements TrpiDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO TRPI (trpi_id,trvl_id,trpi_img,trpi_imgfmt) "
			+ "VALUES (TRPI_ID_SEQ.NEXTVAL, ?, ?,?)";
	private static final String UPDATE = "UPDATE TRPI set trvl_id=?, trpi_img=?, trpi_imgfmt=? where trpi_id=?";
	private static final String DELETE = "DELETE FROM TRPI where trpi_id = ?";
	
	private static final String GET_ONE_STMT = "SELECT * FROM TRPI where trpi_id = ?";
	private static final String GET_ALL_TRPI = "SELECT * FROM TRPI where trvl_id=?"; 
	
	private static final String GET_ALL_STMT = "SELECT * FROM TRPI";
	
	
	@Override
	public void insert(TrpiVO trpiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, trpiVO.getTrvl_id());

			Blob blob = con.createBlob();
			blob.setBytes(1, trpiVO.getTrpi_img());

			pstmt.setBlob(2, blob);
			pstmt.setString(3, trpiVO.getTrpi_imgfmt());

			pstmt.executeUpdate();

			System.out.println("新增成功");
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
	public void update(TrpiVO trpiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trpiVO.getTrvl_id());

			Blob blob = con.createBlob();
			blob.setBytes(1, trpiVO.getTrpi_img());

			pstmt.setBlob(2, blob);
			pstmt.setString(3, trpiVO.getTrpi_imgfmt());
			pstmt.setInt(4, trpiVO.getTrpi_id());

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
	public void delete(Integer trpi_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trpi_id);

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
	public TrpiVO findByPrimaryKey(Integer trpi_id) {
		TrpiVO trpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trpi_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				trpiVO = new TrpiVO();
				trpiVO.setTrpi_id(rs.getInt("TRPI_ID"));
				trpiVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trpiVO.setTrpi_img(rs.getBytes("TRPI_IMG"));
				trpiVO.setTrpi_imgfmt(rs.getString("TRPI_IMGFMT"));
			}
			// Handle any driver errors
		}catch (SQLException se) {
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

		return trpiVO;
	}

	@Override
	public List<TrpiVO>  findByForeignKey(Integer trvl_id) {
		List<TrpiVO> list = new ArrayList<TrpiVO>();
		TrpiVO trpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_TRPI);

			pstmt.setInt(1, trvl_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				trpiVO = new TrpiVO();
				trpiVO.setTrpi_id(rs.getInt("TRPI_ID"));
				trpiVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trpiVO.setTrpi_img(rs.getBytes("TRPI_IMG"));
				trpiVO.setTrpi_imgfmt(rs.getString("TRPI_IMGFMT"));

				list.add(trpiVO); // Store the row in the list
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
	public List<TrpiVO> getAll() {
		// TODO Auto-generated method stub
		List<TrpiVO> list = new ArrayList<TrpiVO>();
		TrpiVO trpiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				trpiVO = new TrpiVO();
				trpiVO.setTrpi_id(rs.getInt("TRPI_ID"));
				trpiVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trpiVO.setTrpi_img(rs.getBytes("TRPI_IMG"));
				trpiVO.setTrpi_imgfmt(rs.getString("TRPI_IMGFMT"));

				list.add(trpiVO); // Store the row in the list
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
