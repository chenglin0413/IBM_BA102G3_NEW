package com.stpi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class StpiDAO implements StpiDAO_interface {
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
	
		private static final String INSERT_STMT = "INSERT INTO STPI (STPI_ID,STORE_ID,STPI_NAME,STPI_IMG,STPI_IMGFMT)"
				+ " VALUES(STPI_SEQ.nextval, ?, ?, ?,?)";
		private static final String UPDATE_STMT = "UPDATE STPI SET STORE_ID=?,STPI_NAME=?, STPI_IMG=?,STPI_IMGFMT=? where STPI_ID = ? " ;
		private static final String DELETE_STMT = "DELETE FROM STPI WHERE STPI_ID= ? ";
		private static final String GET_ONE_STMT = "SELECT * FROM  STPI WHERE STPI_ID = ?";
		private static final String GET_ALL = "SELECT * FROM STPI";

		@Override
		public void insert(StpiVO stpiVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, stpiVO.getStore_id());
				pstmt.setInt(2, stpiVO.getStpi_name());
				pstmt.setBytes(3, stpiVO.getStpi_img());
				pstmt.setString(4, stpiVO.getStpi_imgfmt());
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
		public void update(StpiVO stpiVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, stpiVO.getStore_id());
				pstmt.setInt(2, stpiVO.getStpi_name());
				pstmt.setBytes(3, stpiVO.getStpi_img());
				pstmt.setString(4, stpiVO.getStpi_imgfmt());
				pstmt.setInt(5, stpiVO.getStpi_id());
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
		public void delete(Integer stpi_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, stpi_id);
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
		
		public StpiVO findByPrimaryKey(Integer stpi_id) {
			// TODO Auto-generated method stub
			StpiVO stpiVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, stpi_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					stpiVO = new StpiVO();
					stpiVO.setStpi_id(rs.getInt("stpi_id"));
					stpiVO.setStore_id(rs.getInt("store_id"));
					stpiVO.setStpi_name(rs.getInt("stpi_name"));
					stpiVO.setStpi_img(rs.getBytes("stpi_img"));
					stpiVO.setStpi_imgfmt(rs.getString("stpi_imgfmt"));
					
					
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

			return stpiVO;
		}

		@Override
		public List<StpiVO> getAll() {
			// TODO Auto-generated method stub
			List<StpiVO> list = new ArrayList<StpiVO>();
			StpiVO stpiVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);

				rs=pstmt.executeQuery();

				while (rs.next()) {
					stpiVO = new StpiVO();
					stpiVO.setStpi_id(rs.getInt("stpi_id"));
					stpiVO.setStore_id(rs.getInt("store_id"));
					stpiVO.setStpi_name(rs.getInt("stpi_name"));
					stpiVO.setStpi_img(rs.getBytes("stpi_img"));
					stpiVO.setStpi_imgfmt(rs.getString("stpi_imgfmt"));
					
					
					
					
					list.add(stpiVO);
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
		public static byte[] getPictureByteArray(String path) throws IOException {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();

			return baos.toByteArray();
		}

		public static void main(String[] args) throws IOException {
			StpiJDBCDAO stpiJDBC = new StpiJDBCDAO();

			// 新增
//			StpiVO stpiVO1 = new StpiVO();
//			
//			for(int i=1;i<38;i++){
//				int stid=2000000+i;
//				stpiVO1.setStore_id(stid);
//				int jpg=1000+i;
//				 stpiVO1.setStpi_name(jpg);
//				 byte [] pic=getPictureByteArray("D://images1//"+i+".jpg");
//				 stpiVO1.setStpi_img(pic);
//				 stpiVO1.setStpi_imgfmt("jpg");
//				 
//				 
//				 stpiJDBC.insert(stpiVO1);
//			}
//			 
//			 
//			 System.out.println("-------新增一筆*--------");
	////
			// 修改
//			StpiVO stpiVO2 = new StpiVO();
//			stpiVO2.setStpi_id(2100001);
//			stpiVO2.setStore_id(2000008);
//			stpiVO2.setStpi_name(1005);
//			byte [] pic=getPictureByteArray("D://images1//20.jpg");
//			stpiVO2.setStpi_img(pic);
//			stpiVO2.setStpi_imgfmt("jpg");
//			stpiJDBC.update(stpiVO2);
//			System.out.println("---------更新一筆------------");
//			
			//刪除
//			stpiJDBC.delete(2100036);
//			System.out.println("刪除一筆");
			
			
			// 查詢

//			StpiVO wishVO3 = wishJDBC.findByPrimaryKey(1000002,2200003);
	//
//			System.out.print(wishVO3.getUser_id() + ",");
//			System.out.print(wishVO3.getProd_id() + ",");
//			System.out.print(wishVO3.getWish_date() + ",\n");
//			
//			
//			System.out.println("---------查詢單筆------------");
////			
////			
////			
//			List<StpiVO> list = wishJDBC.getAll();
//			for (StpiVO aWish : list) {
//				System.out.print(aWish.getUser_id() + ",");
//				System.out.print(aWish.getProd_id() + ",");
//				System.out.print(aWish.getWish_date() + ",\n");
//			}
//			System.out.println("\n----------------查詢總表-------------");
		}
}