package com.trpi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrpiJDBCDAO implements TrpiDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, trpi_id);

			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<TrpiVO> getAll() {
		// TODO Auto-generated method stub
		List<TrpiVO> list = new ArrayList<TrpiVO>();
		TrpiVO trpiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws IOException {
		TrpiJDBCDAO dao = new TrpiJDBCDAO();

		// 新增
		 TrpiVO trpiVO1 = new TrpiVO();
//		
//		 trpiVO1.setTrvl_id(1100002);
		
		// File f = new File("C:\\Users\\cuser\\Desktop\\images\\01.png");
		// File f = new File("C:\\Users\\idests\\Desktop\\Penguins.jpg");

		// FileInputStream fis = new FileInputStream(f);
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// byte[] buffer = new byte[8192];
		// int i;
		// while ((i = fis.read(buffer)) != -1) {
		// baos.write(buffer, 0, i);
		// }
		// baos.close();
		// fis.close();
		// trpiVO1.setTrpi_img(baos.toByteArray());
		//
		for(int i=1;i<=10;i++){
			
		
		 FileInputStream fis = new
		 FileInputStream("D://Dropbox//三組//Oracle建表格指令//遊記照片//"+i+".jpg");
		 byte[] buffer = new byte[fis.available()];
		 fis.read(buffer);
		 trpiVO1.setTrpi_img(buffer);
		 trpiVO1.setTrvl_id(1100000+i);
		 trpiVO1.setTrpi_imgfmt("jpg");
		 fis.close();
		 dao.insert(trpiVO1);
		}
		// 修改
//		 TrpiVO trpiVO2 = new TrpiVO();
//		
//		 trpiVO2.setTrvl_id(1100001);
//		
//		 File f2 = new File("D:\\images3\\2.jpg");
//		 						
//		 FileInputStream fis2 = new FileInputStream(f2);
//		 ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
//		 byte[] buffer2 = new byte[8192];
//		 int i2;
//		 while ((i2 = fis2.read(buffer2)) != -1) {
//			 baos2.write(buffer2, 0, i2);
//		 }
//		
//		 trpiVO2.setTrpi_img(baos2.toByteArray());
//		 trpiVO2.setTrpi_imgfmt("mini.jpg");
//		 trpiVO2.setTrpi_id(1200001);
//		 baos2.close();
//		 fis2.close();
//		 dao.update(trpiVO2);

		// 刪除
		// dao.delete(1200011);

		// 查詢

//		 TrpiVO trpiVO3 = dao.findByPrimaryKey(1200007);
//		 System.out.print(trpiVO3.getTrpi_id() + ",");
//		 System.out.print(trpiVO3.getTrvl_id() + ",");
//		 System.out.print(trpiVO3.getTrpi_img()+ ",");
//		 System.out.println(trpiVO3.getTrpi_imgfmt()+ ",");
//		 System.out.println("---------------------");
		
		// 查詢一篇遊記裏所有照片
//		List<TrpiVO> list = dao.findByForeignKey(1100007);
//		for (TrpiVO aTrpi : list) {
//			System.out.println(aTrpi.getTrpi_id());
//			System.out.println(aTrpi.getTrvl_id());
//			System.out.println(aTrpi.getTrpi_img());
//			System.out.println(aTrpi.getTrpi_imgfmt());
//			System.out.println();
//		}
		
		
		// 查詢

//		List<TrpiVO> list = dao.getAll();
//		for (TrpiVO aTrpi : list) {
//			System.out.println(aTrpi.getTrpi_id());
//			System.out.println(aTrpi.getTrvl_id());
//			System.out.println(aTrpi.getTrpi_img());
//			System.out.println(aTrpi.getTrpi_imgfmt());
//			System.out.println();
//		}

	}
}
