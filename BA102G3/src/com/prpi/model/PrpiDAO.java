package com.prpi.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import javax.sql.DataSource;

public class PrpiDAO implements PrpiDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO prpi (prpi_id, prod_id, prpi_name, prpi_img) VALUES (prpi_seq.nextval, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT prpi_id,prod_id,prpi_name,prpi_img FROM prpi";
	private static final String GET_ONE_STMT = "SELECT prpi_id,prod_id,prpi_name,prpi_img FROM prpi where prpi_id = ?";
	private static final String DELETE = "DELETE FROM prpi where prpi_id = ?";
	private static final String UPDATE = "UPDATE prpi set prod_id=?, prpi_name=?, prpi_img=? where prpi_id = ?";
	private static final String GET_ONE_PORDID = "SELECT prpi_id,prod_id,prpi_name,prpi_img FROM prpi where prod_id = ?";
	
	public void insert(PrpiVO prpiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prpiVO.getProd_id());
			pstmt.setString(2, prpiVO.getPrpi_name());
			pstmt.setBytes(3, prpiVO.getPrpi_img());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(PrpiVO prpiVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, prpiVO.getProd_id());
			pstmt.setString(2, prpiVO.getPrpi_name());
			pstmt.setBytes(3, prpiVO.getPrpi_img());
			pstmt.setInt(4, prpiVO.getPrpi_id());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer prpi_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, prpi_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public PrpiVO findByPrimaryKey(Integer prpi_id) {

		PrpiVO prpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prpi_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpiVO = new PrpiVO();
				prpiVO.setPrpi_id(rs.getInt("prpi_id"));
				prpiVO.setProd_id(rs.getInt("prod_id"));
				prpiVO.setPrpi_name(rs.getString("prpi_name"));
				prpiVO.setPrpi_img(rs.getBytes("prpi_img")); //須注意  

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
		return prpiVO;
	}

	@Override
	public List<PrpiVO> getAll() {
		List<PrpiVO> list = new ArrayList<PrpiVO>();
		PrpiVO prpiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				prpiVO = new PrpiVO();
				prpiVO.setPrpi_id(rs.getInt("prpi_id"));
				prpiVO.setProd_id(rs.getInt("prod_id"));
				prpiVO.setPrpi_name(rs.getString("prpi_name"));
				prpiVO.setPrpi_img(rs.getBytes("prpi_img"));
				list.add(prpiVO); // Store the row in the list
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
	public void insertImg(PrpiVO prpiVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prpiVO.getProd_id());
			pstmt.setString(2, prpiVO.getPrpi_name());
			pstmt.setBytes(3, prpiVO.getPrpi_img());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
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
		}
	}

	@Override
	public PrpiVO findByProd_id(Integer prod_id) {

		PrpiVO prpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PORDID);

			pstmt.setInt(1, prod_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpiVO = new PrpiVO();
				prpiVO.setPrpi_id(rs.getInt("prpi_id"));
				prpiVO.setProd_id(rs.getInt("prod_id"));
				prpiVO.setPrpi_name(rs.getString("prpi_name"));
				prpiVO.setPrpi_img(rs.getBytes("prpi_img")); // 須注意

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
		return prpiVO;
	}
}