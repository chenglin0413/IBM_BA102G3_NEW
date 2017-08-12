package com.item.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ItemDAO implements ItemDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	
	
	
	
	
		private static final String INSERT_STMT = "INSERT INTO ITEM (ORD_ID,PROD_ID,ITEM_QTY,ITEM_SCORE,ITEM_REVIEW,ITEM_REVIEWDATE)"
				+ "VALUES(?, ?, ?, ?,?,?)";
		private static final String UPDATE_STMT = "UPDATE ITEM SET ITEM_QTY=?,ITEM_SCORE=?,ITEM_REVIEW=?,ITEM_REVIEWDATE=? WHERE ORD_ID = ? AND PROD_ID= ?";
		private static final String DELETE_STMT = "DELETE FROM ITEM WHERE ORD_ID=? AND PROD_ID=?";
		private static final String GET_ONE_STMT = "SELECT * FROM ITEM WHERE ORD_ID=? AND PROD_ID=?";
		private static final String GET_ALL = "SELECT * FROM ITEM";
		private static final String GET_ONE_ORD_ID_ITEM = "SELECT * FROM ITEM WHERE ORD_ID=?";
		@Override
		public void insert(ItemVO itemVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				con.setAutoCommit(false);
				
				pstmt.setInt(1, itemVO.getOrd_id());
				pstmt.setInt(2, itemVO.getProd_id());
				pstmt.setInt(3, itemVO.getItem_qty());
				pstmt.setInt(4, itemVO.getItem_score());
				pstmt.setString(5, itemVO.getItem_review());
				pstmt.setDate(6, itemVO.getItem_reviewdate());
				
				
				
				
				pstmt.executeUpdate();
				con.commit();
			} catch (SQLException se) {
				try {
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
		public void insert2 (ItemVO itemVO , Connection con) {

			PreparedStatement pstmt = null;

			try {

	     		pstmt = con.prepareStatement(INSERT_STMT);

	     		pstmt.setInt(1, itemVO.getOrd_id());
				pstmt.setInt(2, itemVO.getProd_id());
				pstmt.setInt(3, itemVO.getItem_qty());
				pstmt.setInt(4, itemVO.getItem_score());
				pstmt.setString(5, itemVO.getItem_review());
				pstmt.setDate(6, itemVO.getItem_reviewdate());

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-item");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
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
			}

		}
		
		
		@Override
		public void update(ItemVO itemVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, itemVO.getItem_qty());
				pstmt.setInt(2, itemVO.getItem_score());
				pstmt.setString(3, itemVO.getItem_review());
				pstmt.setDate(4, itemVO.getItem_reviewdate());
				pstmt.setInt(5, itemVO.getOrd_id());
				pstmt.setInt(6, itemVO.getProd_id());
				

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
		public void delete(Integer ord_id,Integer prod_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con =ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, ord_id);
				pstmt.setInt(2, prod_id);
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
		public ItemVO findByPrimaryKey(Integer ord_id, Integer prod_id) {
			// TODO Auto-generated method stub
			ItemVO itemVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
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
				con = ds.getConnection();
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
		public List<ItemVO> getOneOrd_idAllItem(Integer ord_id) {
			// TODO Auto-generated method stub
			List<ItemVO> list = new ArrayList<ItemVO>();
			ItemVO itemVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_ORD_ID_ITEM);
				pstmt.setInt(1, ord_id);
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
