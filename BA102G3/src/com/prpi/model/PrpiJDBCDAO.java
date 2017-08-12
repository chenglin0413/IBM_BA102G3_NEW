package com.prpi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prod.model.ProdVO;

public class PrpiJDBCDAO implements PrpiDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO prpi (prpi_id, prod_id, prpi_name, prpi_img) VALUES (prpi_seq.nextval, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT prpi_id,prod_id,prpi_name,prpi_img FROM prpi";
	private static final String GET_ONE_STMT = "SELECT prpi_id,prod_id,prpi_name,prpi_img FROM prpi where prpi_id = ? ";
	private static final String DELETE = "DELETE FROM prpi where prpi_id = ?";
	private static final String UPDATE = "UPDATE prpi set prod_id=?, prpi_name=?, prpi_img=? where prpi_id = ?";

	@Override
	public void insert(PrpiVO prpiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, prpiVO.getProd_id());
			pstmt.setString(2, prpiVO.getPrpi_name());
			pstmt.setBytes(3, prpiVO.getPrpi_img());
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
	public void update(PrpiVO prpiVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, prpiVO.getProd_id());
			pstmt.setString(2, prpiVO.getPrpi_name());
			pstmt.setBytes(3, prpiVO.getPrpi_img());
			pstmt.setInt(4, prpiVO.getPrpi_id());
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
	public void delete(Integer prpi_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prpi_id);

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
	public PrpiVO findByPrimaryKey(Integer prpi_id) {

		PrpiVO prpiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, prpi_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prpiVO = new PrpiVO();
				prpiVO.setPrpi_id(rs.getInt("prpi_id"));
				prpiVO.setProd_id(rs.getInt("prod_id"));
				prpiVO.setPrpi_name(rs.getString("prpi_name"));
				prpiVO.setPrpi_img(rs.getBytes("prpi_img")); //須注意  

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	

	public static void main(String[] args) {

		PrpiJDBCDAO dao = new PrpiJDBCDAO();

		// 新增
		
		for(int i = 63;i<=153;i++){
			try {
				PrpiVO prpiVO1 = new PrpiVO();
				prpiVO1.setProd_id(2200000+i);
				prpiVO1.setPrpi_name("商城產品");
				byte[] pic = getPictureByteArray("D://Dropbox//三組//Oracle建表格指令//商品圖片整理//"+i+".jpg");
				prpiVO1.setPrpi_img(pic);
				dao.insert(prpiVO1);
			} catch (IOException e) {
				System.out.println(e);
			}
		}



//		// 修改
//		try {
//			PrpiVO prpiVO2 = new PrpiVO();
//			prpiVO2.setPrpi_id(2300041);
//			prpiVO2.setProd_id(2200001);
//			prpiVO2.setPrpi_name("Girl");
//			byte[] pic = getPictureByteArray("C:\\Users\\User\\Desktop\\t1_store_台灣名品集.jpg");
//			prpiVO2.setPrpi_img(pic);
//			dao.update(prpiVO2);
//
//		} catch (IOException e) {
//			System.out.println(e);
//		}
//
//		// 刪除
//		dao.delete(2300107);
//
//		// 查詢
//		PrpiVO prpiVO3 = dao.findByPrimaryKey(2300041);
//		System.out.print(prpiVO3.getPrpi_id() + ",");
//		System.out.print(prpiVO3.getProd_id() + ",");
//		System.out.print(prpiVO3.getPrpi_name() + ",");
//		System.out.println(prpiVO3.getPrpi_img());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<PrpiVO> list = dao.getAll();
//		for (PrpiVO aPrpi : list) {
//			System.out.print(aPrpi.getPrpi_id() + ",");
//			System.out.print(aPrpi.getProd_id() + ",");
//			System.out.print(aPrpi.getPrpi_name() + ",");
//			System.out.print(aPrpi.getPrpi_img());
//
//			System.out.println();
//		}
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

	@Override
	public PrpiVO findByProd_id(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
