package com.reta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RetaJNDIDAO implements RetaDAO_Interface {

	private static final String INSERT_STMT = "INSERT INTO RETA "
			+ "(RETA_ID,"
			+ "AVTB_ID,"
			+ "RETA_NUMBER,"
			+ "RETA_STATUS,"
			+ "RETA_GRANT,"
			+ "RETA_DATE,"
			+ "RETA_RANK_RES,"
			+ "RETA_REVIEW,"
			+ "RETA_REVIEWDATE,"
			+ "RETA_RPDATE,"
			+ "RETA_RPCOMM,"
			+ "RETA_RPSTATUS) "
			+ "VALUES (reta_sq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE RETA SET RETA_STATUS=? WHERE RETA_ID = ?";
	private static final String DELETE = "DELETE FROM RETA WHERE RETA_ID";
	private static final String GET_ONE_STMT ="SELECT RETA_ID,"
			+ "AVTB_ID"
			+ "FROM reta WHERE RETA_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM RETA";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(RetaVO retaVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, retaVO.getReta_id());
			
			pstmt.executeUpdate();

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
	public void update(RetaVO retaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, retaVO.getReta_id());
		
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer reta_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, reta_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}  finally {
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
	public RetaVO findByPrimaryKey(Integer reta_id) {
		RetaVO retaVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reta_id);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
			}

			// Handle any driver errors
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
		return retaVO;
	}

	@Override
	public List<RetaVO> getAll() {
		List<RetaVO> list = new ArrayList<RetaVO>();
		RetaVO retaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				retaVO = new RetaVO();
				retaVO.setReta_id(rs.getInt("reta_id"));
				retaVO.setAvtb_id(rs.getInt("avtb_id"));
				list.add(retaVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	
	
	public static void main(String[] args){
		RetaDAO dao = new RetaDAO();
		
		//新增
		RetaVO retain = new RetaVO();
		retain.setReta_id(1000047);
		
		//查詢
		List<RetaVO> list = dao.getAll();
		for (RetaVO reta : list) {
		System.out.print("訂位編號:"+reta.getReta_id() + "\n");
		System.out.print("時段編號"+reta.getAvtb_id() + ",");
		System.out.print("使用者編號"+reta + reta.getUser_id()+",");
		System.out.print("訂位人數"+reta.getReta_number()+ ",");
		System.out.print("到位狀況"+reta.getReta_status() + ",");
		System.out.print("餐廳審核定位狀態"+reta.getReta_grant() + ",");
		System.out.print("訂位成立日期"+reta.getReta_date() + ",");
		System.out.print("對餐廳評分"+reta.getReta_rank_res() + ",");
		System.out.print("用餐感想"+reta.getReta_review() + ",");
		System.out.print("填寫用餐感想日期"+reta.getReta_reviewdate() + ",");
		System.out.print("餐廳檢舉訂位日期"+reta.getRest_rpdate() + ",");
		System.out.print("餐廳檢舉訂位理由"+reta.getRest_rpcomm() + ",");
		System.out.print("餐廳檢舉訂位狀態"+reta.getRest_rpstatus());
		System.out.println();
		System.out.println("-------------");
		}
	}

	@Override
	public List<RetaVO> findByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
