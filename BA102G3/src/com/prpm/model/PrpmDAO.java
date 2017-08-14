package com.prpm.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class PrpmDAO implements PrpmDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PRPM (stpm_id,prod_id,prpm_price,prpm_status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT stpm_id,prod_id,prpm_price,prpm_status FROM PRPM";
	private static final String GET_ONE_STMT = "SELECT stpm_id,prod_id,prpm_price,prpm_status FROM PRPM where stpm_id = ? and prod_id = ?";
	private static final String UPDATE = "UPDATE PRPM set prpm_price=?,prpm_status=? where stpm_id = ? and prod_id = ?";
	private static final String GET_ONE = "SELECT * FROM PRPM WHERE STPM_ID = ?";
	private static final String GET_PRICE = "SELECT PRPM_PRICE FROM PRPM WHERE STPM_ID = ?";
	private static final String GET_RM_PRICE_FORPROD = "select stpm_id,prpm_price,prpm_status from(SELECT stpm_id,prpm_price,prpm_status from prpm where prod_id=? order by prod_id)where rownum<=1";
	private static final String UPDATE_STATUS = "UPDATE PRPM set prpm_status=? where stpm_id = ?";
	private static final String GET_ONE_PK = "SELECT stpm_id,prod_id,prpm_price,prpm_status FROM PRPM where stpm_id = ?";

	@Override
	public void insert(PrpmVO prpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prpmVO.getStpm_id());
			pstmt.setInt(2, prpmVO.getProd_id());
			pstmt.setInt(3, prpmVO.getPrpm_price());
			pstmt.setInt(4, prpmVO.getPrpm_status());

			pstmt.executeUpdate();

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
	public void update(PrpmVO prpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(3, prpmVO.getStpm_id());
			pstmt.setInt(4, prpmVO.getProd_id());
			pstmt.setInt(1, prpmVO.getPrpm_price());
			pstmt.setInt(2, prpmVO.getPrpm_status());

			pstmt.executeUpdate();

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
	public PrpmVO findByStpmID_ProdID(Integer stpm_id, Integer prod_id) {

		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, stpm_id);
			pstmt.setInt(2, prod_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpmVO = new PrpmVO();
				prpmVO.setStpm_id(rs.getInt("stpm_id"));
				prpmVO.setProd_id(rs.getInt("prod_id"));
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));
				prpmVO.setPrpm_status(rs.getInt("prpm_status"));
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
		return prpmVO;
	}

	@Override
	public List<PrpmVO> getAll() {
		List<PrpmVO> list = new ArrayList<PrpmVO>();
		PrpmVO prpmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prpmVO = new PrpmVO();
				prpmVO.setStpm_id(rs.getInt("stpm_id"));
				prpmVO.setProd_id(rs.getInt("prod_id"));
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));
				prpmVO.setPrpm_status(rs.getInt("prpm_status"));
				list.add(prpmVO); // Store the row in the list
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
	public List<PrpmVO> findByStpmID(Integer stpm_id) {
		List<PrpmVO> list = new ArrayList<PrpmVO>();
		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
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
	public PrpmVO findByPrice(Integer stpm_id) {
		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PRICE);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpmVO = new PrpmVO();
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));

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
		return prpmVO;
	}

	@Override
	public PrpmVO getOneRmPrice_prod(Integer prod_id) {
		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RM_PRICE_FORPROD);

			pstmt.setInt(1, prod_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpmVO = new PrpmVO();
				prpmVO.setStpm_id(rs.getInt("stpm_id"));
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));
				prpmVO.setPrpm_status(rs.getInt("prpm_status"));

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
		return prpmVO;
	}

	@Override
	public void updateStatus(PrpmVO prpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, prpmVO.getPrpm_status());
			pstmt.setInt(2, prpmVO.getStpm_id());

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
	public PrpmVO findByPrimaryKey(Integer stpm_id) {
		
		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PK);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpmVO = new PrpmVO();
				prpmVO.setStpm_id(rs.getInt("stpm_id"));
				prpmVO.setProd_id(rs.getInt("prod_id"));
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));
				prpmVO.setPrpm_status(rs.getInt("prpm_status"));
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
		return prpmVO;
	}
}
