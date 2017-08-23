package com.repi.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.repi.model.RepiJDBC;
import com.repi.model.RepiVO;

public class RepiJDBC implements RepiDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"insert into repi(repi_id,rest_id,repi_name,repi_img,repi_imgfmt) "
			+ "values(repi_sq.nextval,?,?,?,?)";
	
	private static final String DELETE = 
			"DELETE FROM repi where repi_id = ?";
	
	private static final String UPDATE = 
			"UPDATE repi set rest_id=?, repi_name=?, repi_img=?, repi_imgfmt=? where repi_id = ?";
	
	private static final String GET_ALL_STMT = 
			"select * from repi order by repi_id";
	
	private static final String GET_ONE_STMT = 
			"select * from repi where repi_id = ?";
	
	@Override
	public void insert(RepiVO repiVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, repiVO.getRest_id());
			pstmt.setString(2, repiVO.getRepi_name());
			pstmt.setBytes(3, repiVO.getRepi_img());
			pstmt.setString(4, repiVO.getRepi_imgfmt());
			
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void update(RepiVO repiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, repiVO.getRest_id());
			pstmt.setString(2, repiVO.getRepi_name());
			pstmt.setBytes(3, repiVO.getRepi_img());
			pstmt.setString(4, repiVO.getRepi_imgfmt());
			pstmt.setInt(5, repiVO.getRepi_id());
			
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public void delete(Integer repi_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, repi_id);
			
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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
	public RepiVO findByPrimaryKey(Integer repi_id) {
		// TODO Auto-generated method stub
		RepiVO repiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, repi_id);
			

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				repiVO = new RepiVO();
				repiVO.setRepi_id(rs.getInt("repi_id"));
				repiVO.setRest_id(rs.getInt("rest_id"));
				repiVO.setRepi_name(rs.getString("repi_name"));
				repiVO.setRepi_imgfmt(rs.getString("repi_imgfmt"));
				repiVO.setRepi_img(rs.getBytes("repi_img"));
				
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return repiVO;
	}

	@Override
	public List<RepiVO> getAll() {
		// TODO Auto-generated method stub
		List<RepiVO> list = new ArrayList<RepiVO>();
		RepiVO repiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 銋迂� Domain objects
				
				repiVO = new RepiVO();
				repiVO.setRepi_id(rs.getInt("repi_id"));
				repiVO.setRest_id(rs.getInt("rest_id"));
				repiVO.setRepi_name(rs.getString("repi_name"));
				repiVO.setRepi_imgfmt(rs.getString("repi_imgfmt"));
				repiVO.setRepi_img(rs.getBytes("repi_img"));
				list.add(repiVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		RepiJDBC dao = new RepiJDBC();

		//
		RepiVO repiVO1 = new RepiVO();
		repiVO1.setRest_id(3000002);
		repiVO1.setRepi_name("pi001");
		byte[] img = {5,5,5,5,5,5};
		repiVO1.setRepi_img(img);
		repiVO1.setRepi_imgfmt("jpg");
		
		dao.insert(repiVO1);

		//
//		RepiVO repiVO2 = new RepiVO();
//		repiVO2.setRest_id(1000040);
//		repiVO2.setRepi_name("pi0010001");
//		repiVO2.setRepi_img(null);
//		repiVO2.setRepi_imgfmt("jpg");
//		repiVO2.setRepi_id(3300002);
//		//
//		dao.delete(3300002);

		// 
	
//		RepiVO repiVO3 = dao.findByPrimaryKey(3100002);
//		System.out.println("2");
//		System.out.print(repiVO3.getRepi_id() + ",");
//		System.out.print(repiVO3.getRest_id() + ",");
//		System.out.print(repiVO3.getRepi_name() + ",");
//		System.out.print(repiVO3.getRepi_img() + ",");
//		System.out.print(repiVO3.getRepi_imgfmt());
//		
//		System.out.println("---------------------");

		//
//		List<RepiVO> list = dao.getAll();
//		for (RepiVO repi : list) {
//			System.out.print(repi.getRepi_id() + ",");
//			System.out.print(repi.getRest_id() + ",");
//			System.out.print(repi.getRepi_name() + ",");
//			System.out.print(repi.getRepi_img() + ",");
//			System.out.print(repi.getRepi_imgfmt());
//			System.out.println();
//		}
	}
	
	
	@Override
	public byte[] download(Integer repi_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
