package com.wish.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class WishJDBCDAO implements WishDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO WISH (USER_ID,PROD_ID,WISH_DATE)"
			+ " VALUES(?,?,?)";
	private static final String UPDATE_STMT = "UPDATE WISH SET WISH_DATE=? where USER_ID = ? and PROD_ID= ?" ;
	private static final String DELETE_STMT = "DELETE FROM WISH WHERE USER_ID= ? and PROD_ID= ?";
	private static final String GET_ONE_STMT = "SELECT * FROM  WISH WHERE USER_ID = ? and PROD_ID= ?";
	private static final String GET_ALL = "SELECT * FROM WISH";

	@Override
	public void insert(WishVO wishVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, wishVO.getUser_id());
			pstmt.setInt(2, wishVO.getProd_id());
			pstmt.setDate(3, wishVO.getWish_date());
			
			
			pstmt.executeUpdate();

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
	public void update(WishVO wishVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setDate(1, wishVO.getWish_date());				
			pstmt.setInt(2, wishVO.getUser_id());
			pstmt.setInt(3, wishVO.getProd_id());
			pstmt.executeUpdate();

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
	public void delete(Integer user_id,Integer prod_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, prod_id);
			pstmt.executeUpdate();

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
	
	public WishVO findByPrimaryKey(Integer user_id,Integer prod_id) {
		// TODO Auto-generated method stub
		WishVO wishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, user_id);
			pstmt.setInt(2, prod_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				wishVO = new WishVO();
				wishVO.setUser_id(rs.getInt("user_id"));
				wishVO.setProd_id(rs.getInt("prod_id"));
				wishVO.setWish_date(rs.getDate("wish_date"));
				
				
			}

		} catch (SQLException | ClassNotFoundException se) {
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

		return wishVO;
	}

	@Override
	public List<WishVO> getAll() {
		// TODO Auto-generated method stub
		List<WishVO> list = new ArrayList<WishVO>();
		WishVO wishVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs=pstmt.executeQuery();

			while (rs.next()) {
				wishVO = new WishVO();
				wishVO.setUser_id(rs.getInt("user_id"));
				wishVO.setProd_id(rs.getInt("prod_id"));
				wishVO.setWish_date(rs.getDate("wish_date"));
				
				
				
				list.add(wishVO);
			}

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

	public static void main(String[] args) {
		WishJDBCDAO wishJDBC = new WishJDBCDAO();

		// 新增
		WishVO wishVO1 = new WishVO();
////
		 wishVO1.setUser_id(1000004);
		 wishVO1.setProd_id(2200005);
		 wishVO1.setWish_date(java.sql.Date.valueOf("2017-08-25"));
		 
		 
		 wishJDBC.insert(wishVO1);
		 
		 System.out.println("-------新增一筆*--------");
////
		// 修改
//		WishVO wishVO2 = new WishVO();
//		wishVO2.setUser_id(1000001);
//		wishVO2.setProd_id(2200008);
//		wishVO2.setWish_date(java.sql.Date.valueOf("2017-10-2"));
//		wishJDBC.update(wishVO2);
//		System.out.println("---------更新一筆------------");
		
		//刪除
//		wishJDBC.delete(1000004,2200005);
//		System.out.println("刪除一筆");
		
		
		// 查詢

		WishVO wishVO3 = wishJDBC.findByPrimaryKey(1000002,2200003);

		System.out.print(wishVO3.getUser_id() + ",");
		System.out.print(wishVO3.getProd_id() + ",");
		System.out.print(wishVO3.getWish_date() + ",\n");
		
		
		System.out.println("---------查詢單筆------------");
//		
//		
//		
		List<WishVO> list = wishJDBC.getAll();
		for (WishVO aWish : list) {
			System.out.print(aWish.getUser_id() + ",");
			System.out.print(aWish.getProd_id() + ",");
			System.out.print(aWish.getWish_date() + ",\n");
		}
		System.out.println("\n----------------查詢總表-------------");
	}

	@Override
	public List<WishVO> getOneUser_idAllWish(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}


}
