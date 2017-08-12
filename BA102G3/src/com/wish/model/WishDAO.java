package com.wish.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class WishDAO implements WishDAO_interface {
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
	
	
		private static final String INSERT_STMT = "INSERT INTO WISH (USER_ID,PROD_ID,WISH_DATE)"
				+ " VALUES(?,?,?)";
		private static final String UPDATE_STMT = "UPDATE WISH SET WISH_DATE=? where USER_ID = ? and PROD_ID= ?" ;
		private static final String DELETE_STMT = "DELETE FROM WISH WHERE USER_ID= ? and PROD_ID= ?";
		private static final String GET_ONE_STMT = "SELECT * FROM  WISH WHERE USER_ID = ? and PROD_ID= ?";
		private static final String GET_ALL = "SELECT * FROM WISH";
		private static final String GET_ONE_USER_ID_WISH = "SELECT * FROM  WISH WHERE USER_ID = ? ";
		@Override
		public void insert(WishVO wishVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, wishVO.getUser_id());
				pstmt.setInt(2, wishVO.getProd_id());
				pstmt.setDate(3, wishVO.getWish_date());
				
				
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
		public void update(WishVO wishVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setDate(1, wishVO.getWish_date());				
				pstmt.setInt(2, wishVO.getUser_id());
				pstmt.setInt(3, wishVO.getProd_id());
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
		public void delete(Integer user_id,Integer prod_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, user_id);
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
		
		public WishVO findByPrimaryKey(Integer user_id,Integer prod_id) {
			// TODO Auto-generated method stub
			WishVO wishVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				con = ds.getConnection();
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

			} catch (SQLException  se) {
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
		public List<WishVO> getOneUser_idAllWish(Integer user_id) {
			// TODO Auto-generated method stub
			List<WishVO> list = new ArrayList<WishVO>();
			WishVO wishVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_USER_ID_WISH);
				pstmt.setInt(1, user_id);
				rs=pstmt.executeQuery();

				while (rs.next()) {
					wishVO = new WishVO();
					wishVO.setUser_id(rs.getInt("user_id"));
					wishVO.setProd_id(rs.getInt("prod_id"));
					wishVO.setWish_date(rs.getDate("wish_date"));
					
					
					
					list.add(wishVO);
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
		public List<WishVO> getAll() {
			// TODO Auto-generated method stub
			List<WishVO> list = new ArrayList<WishVO>();
			WishVO wishVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);

				rs=pstmt.executeQuery();

				while (rs.next()) {
					wishVO = new WishVO();
					wishVO.setUser_id(rs.getInt("user_id"));
					wishVO.setProd_id(rs.getInt("prod_id"));
					wishVO.setWish_date(rs.getDate("wish_date"));
					
					
					
					list.add(wishVO);
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