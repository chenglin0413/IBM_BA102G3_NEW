package com.item.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ItemJDBCDAO implements ItemDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO ITEM (ORD_ID,PROD_ID,ITEM_QTY,ITEM_SCORE,ITEM_REVIEW,ITEM_REVIEWDATE)"
			+ "VALUES(?, ?, ?, ?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ITEM SET ITEM_QTY=?,ITEM_SCORE=?,ITEM_REVIEW=?,ITEM_REVIEWDATE=? WHERE ORD_ID = ? AND PROD_ID= ?";
	private static final String DELETE_STMT = "DELETE FROM ITEM WHERE ORD_ID=? AND PROD_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM ITEM WHERE ORD_ID=? AND PROD_ID=?";
	private static final String GET_ALL = "SELECT * FROM ITEM";

	@Override
	public void insert(ItemVO itemVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setInt(1, itemVO.getOrd_id());
			pstmt.setInt(2, itemVO.getProd_id());
			pstmt.setInt(3, itemVO.getItem_qty());
			pstmt.setInt(4, itemVO.getItem_score());
			pstmt.setString(5, itemVO.getItem_review());
			pstmt.setDate(6, itemVO.getItem_reviewdate());
			
			
			
			
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
	public void update(ItemVO itemVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, itemVO.getItem_qty());
			pstmt.setInt(2, itemVO.getItem_score());
			pstmt.setString(3, itemVO.getItem_review());
			pstmt.setDate(4, itemVO.getItem_reviewdate());
			pstmt.setInt(5, itemVO.getOrd_id());
			pstmt.setInt(6, itemVO.getProd_id());
			

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
	public void delete(Integer ord_id,Integer prod_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ord_id);
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

	@Override
	public ItemVO findByPrimaryKey(Integer ord_id, Integer prod_id) {
		// TODO Auto-generated method stub
		ItemVO itemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ord_id);
			pstmt.setInt(2, prod_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setOrd_id(rs.getInt("ord_id"));
				itemVO.setProd_id(rs.getInt("prod_id"));
				itemVO.setItem_qty(rs.getInt("item_qty"));
				itemVO.setItem_score(rs.getInt("item_score"));
				itemVO.setItem_review(rs.getString("item_review"));
				itemVO.setItem_reviewdate(rs.getDate("item_reviewdate"));
				
				

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

		return itemVO;
	}

	@Override
	public List<ItemVO> getAll() {
		// TODO Auto-generated method stub
		List<ItemVO> list = new ArrayList<ItemVO>();
		ItemVO itemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs=pstmt.executeQuery();

			while (rs.next()) {
				itemVO = new ItemVO();
				itemVO.setOrd_id(rs.getInt("ord_id"));
				itemVO.setProd_id(rs.getInt("prod_id"));
				itemVO.setItem_qty(rs.getInt("item_qty"));
				itemVO.setItem_score(rs.getInt("item_score"));
				itemVO.setItem_review(rs.getString("item_review"));
				itemVO.setItem_reviewdate(rs.getDate("item_reviewdate"));
				list.add(itemVO);
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
		ItemJDBCDAO itemJDBC = new ItemJDBCDAO();

		// 新增
		ItemVO itemVO1 = new ItemVO();
		

//		 itemVO1.setOrd_id(2400006);
//		 itemVO1.setProd_id(2200008);
//		 itemVO1.setItem_qty(5);
//		 itemVO1.setItem_score(5);
//		 itemVO1.setItem_review("好阿!");
//		 itemVO1.setItem_reviewdate(java.sql.Date.valueOf("2017-08-31"));
//		 
//		 
//		 itemJDBC.insert(itemVO1);
//		 System.out.println("---------新增一筆-----------");
//
//		// 修改
//		 ItemVO itemVO2 = new ItemVO();
//		 itemVO2.setOrd_id(2400001);
//		 itemVO2.setProd_id(2200003);
//		 itemVO2.setItem_qty(10);
//		 itemVO2.setItem_score(5);
//		 itemVO2.setItem_review("好阿!");
//		 itemVO2.setItem_reviewdate(java.sql.Date.valueOf("2017-08-31"));
//		
//		 
//		 itemJDBC.update(itemVO2);
//		 System.out.println("---------修改一筆-----------");
//		
		
		//刪除
//		itemJDBC.delete(2400001,2200003);
//		System.out.println("-------------刪除一筆-----------");
		
		// 查詢

		ItemVO itemVO3 = itemJDBC.findByPrimaryKey(2400005,2200007);

		System.out.print(itemVO3.getOrd_id() + ",");
		System.out.print(itemVO3.getProd_id() + ",");
		System.out.print(itemVO3.getItem_qty() + ",");
		System.out.print(itemVO3.getItem_score() + ",");
		System.out.print(itemVO3.getItem_review() + ",");
		System.out.print(itemVO3.getItem_reviewdate() + ",\n");
		
		System.out.println("----------查詢單筆-----------");
//		
//		
//		
		List<ItemVO> list = itemJDBC.getAll();
		for (ItemVO aItem : list) {
			System.out.print(aItem.getOrd_id() + ",");
			System.out.print(aItem.getProd_id() + ",");
			System.out.print(aItem.getItem_qty() + ",");
			System.out.print(aItem.getItem_score() + ",");
			System.out.print(aItem.getItem_review() + ",");
			System.out.print(aItem.getItem_reviewdate() + ",\n");
			
		}
		System.out.println("\n----------------查詢總表-------------");

	}

	@Override
	public List<ItemVO> getOneOrd_idAllItem(Integer ord_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert2(ItemVO itemVO, Connection con) {
		// TODO Auto-generated method stub
		
	}


}
