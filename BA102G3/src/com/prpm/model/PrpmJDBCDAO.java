package com.prpm.model;

import java.util.*;

import com.stpm.model.StpmVO;

import java.sql.*;

public class PrpmJDBCDAO implements PrpmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO PRPM (stpm_id,prod_id,prpm_price,prpm_status) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT stpm_id,prod_id,prpm_price,prpm_status FROM PRPM";
	private static final String GET_ONE_STMT = "SELECT stpm_id,prod_id,prpm_price,prpm_status FROM PRPM where stpm_id = ? and prod_id = ?";
	private static final String UPDATE = "UPDATE PRPM set prpm_price=?,prpm_status=? where stpm_id = ? and prod_id = ?";
	private static final String GET_ONE = "SELECT * FROM PRPM WHERE STPM_ID = ?";
	private static final String GET_PRICE = "SELECT PRPM_PRICE FROM PRPM WHERE STPM_ID = ?";
	private static final String UPDATE_STATUS = "UPDATE PRPM set prpm_status=? where stpm_id = ?";
	private static final String DELETE_STMT = "DELETE FROM PRPM WHERE STPM_ID AND PROD_ID=?";

	@Override
	public void insert(PrpmVO prpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prpmVO.getStpm_id());
			pstmt.setInt(2, prpmVO.getProd_id());
			pstmt.setInt(3, prpmVO.getPrpm_price());
			pstmt.setInt(4, prpmVO.getPrpm_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(3, prpmVO.getStpm_id());
			pstmt.setInt(4, prpmVO.getProd_id());
			pstmt.setInt(1, prpmVO.getPrpm_price());
			pstmt.setInt(2, prpmVO.getPrpm_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public PrpmVO findByPrice(Integer stpm_id) {
		PrpmVO prpmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PRICE);

			pstmt.setInt(1, stpm_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				prpmVO = new PrpmVO();
				prpmVO.setPrpm_price(rs.getInt("prpm_price"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void updateStatus(PrpmVO prpmVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STATUS);
			
			pstmt.setInt(1, prpmVO.getPrpm_status());
			pstmt.setInt(2, prpmVO.getStpm_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public PrpmVO getOneRmPrice_prod(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {

		PrpmJDBCDAO dao = new PrpmJDBCDAO();

		// PrpmVO s = new PrpmVO();
		// s.setStpm_id(2500090);
		// s.setProd_id(2200006);
		// s.setPrpm_price(1000);
		// s.setPrpm_status(1);
		// dao.insert(s);

		// PrpmVO s = new PrpmVO();
		// s.setStpm_id(2500090);
		// s.setProd_id(2200006);
		// s.setPrpm_price(10);
		// s.setPrpm_status(0);
		// dao.update(s);
		
		PrpmVO s = new PrpmVO();
		s.setStpm_id(2500007);
		s.setPrpm_status(5);
		dao.updateStatus(s);

//		List<PrpmVO> list = dao.findByStpmID(2500007);
//		for (PrpmVO s : list) {
//			System.out.println(s.getStpm_id());
//			System.out.println(s.getProd_id());
//			System.out.println(s.getPrpm_price());
//			System.out.println(s.getPrpm_status());
//		}

		// List<PrpmVO> list = dao.getAll();
		// for (PrpmVO s : list) {
		// System.out.println(s.getStpm_id());
		// System.out.println(s.getProd_id());
		// System.out.println(s.getPrpm_price());
		// System.out.println(s.getPrpm_status());
		// }

	}

	@Override
	public PrpmVO findByPrimaryKey(Integer stpm_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer stpm_id, Integer prod_id) {
		// TODO Auto-generated method stub
		
	}

}