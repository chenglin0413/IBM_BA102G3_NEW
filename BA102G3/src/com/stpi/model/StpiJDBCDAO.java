package com.stpi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class StpiJDBCDAO implements StpiDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, stpiVO.getStore_id());
			pstmt.setInt(2, stpiVO.getStpi_name());
			pstmt.setBytes(3, stpiVO.getStpi_img());
			pstmt.setString(4, stpiVO.getStpi_imgfmt());
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
	public void update(StpiVO stpiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, stpiVO.getStore_id());
			pstmt.setInt(2, stpiVO.getStpi_name());
			pstmt.setBytes(3, stpiVO.getStpi_img());
			pstmt.setString(4, stpiVO.getStpi_imgfmt());
			pstmt.setInt(5, stpiVO.getStpi_id());
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
	public void delete(Integer stpi_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, stpi_id);
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
	
	public StpiVO findByPrimaryKey(Integer stpi_id) {
		// TODO Auto-generated method stub
		StpiVO stpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		StpiVO stpiVO1 = new StpiVO();
//		
		for(int i=1;i<35;i++){
			int stid=2000000+i;
			stpiVO1.setStore_id(stid);
			int jpg=1000+i;
			 stpiVO1.setStpi_name(jpg);
			 byte [] pic=getPictureByteArray("D://images3//"+i+".jpg");
			 stpiVO1.setStpi_img(pic);
			 stpiVO1.setStpi_imgfmt("jpg");
			 
			 
			 stpiJDBC.insert(stpiVO1);
		}
		 
//		 stpiVO1.setStore_id(2000028);
//		 stpiVO1.setStpi_name(1027);
//		 stpiVO1.setStpi_img(stpi_img);
		 
		 System.out.println("-------新增一筆*--------");
////
		// 修改
//		StpiVO stpiVO2 = new StpiVO();
//		stpiVO2.setStpi_id(2100001);
//		stpiVO2.setStore_id(2000008);
//		stpiVO2.setStpi_name(1005);
//		byte [] pic=getPictureByteArray("D://images1//20.jpg");
//		stpiVO2.setStpi_img(pic);
//		stpiVO2.setStpi_imgfmt("jpg");
//		stpiJDBC.update(stpiVO2);
//		System.out.println("---------更新一筆------------");
//		
		//刪除
//		stpiJDBC.delete(2100036);
//		System.out.println("刪除一筆");
		
		
		// 查詢

//		StpiVO stpiVO3 = stpiJDBC.findByPrimaryKey(1000002,2200003);
//
//		System.out.print(stpiVO3.getUser_id() + ",");
//		System.out.print(stpiVO3.getProd_id() + ",");
//		System.out.print(stpiVO3.getWish_date() + ",\n");
//		
//		
//		System.out.println("---------查詢單筆------------");
////		
////		
////		
		List<StpiVO> list = stpiJDBC.getAll();
		for (StpiVO aStpi : list) {
			System.out.print(aStpi.getStpi_id() + ",");
			System.out.print(aStpi.getStore_id() + ",");
			System.out.print(aStpi.getStpi_name() + ",");
			System.out.print(aStpi.getStpi_img() + ",");
			System.out.print(aStpi.getStpi_imgfmt() + ",\n");
		}
		System.out.println("\n----------------查詢總表-------------");
	}

	@Override
	public StpiVO findByStoreId(Integer store_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
