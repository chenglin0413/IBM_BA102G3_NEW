package com.avtb.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AvtbJNDIDAO implements AvtbDAO_Interface {

	private static final String INSERT_STMT = "INSERT INTO AVTB "
			+ "(AVTB_ID,"
			+ "REST_ID,"
			+ "AVTB_DATE_S,"
			+ "AVTB_DATE_E,"
			+ "AVTB_RESERVATION,"
			+ "AVTB_MAX_RESERVATION) "
			+ "VALUES (avtb_sq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE AVTB set AVTB_DATE_S=?, WHERE AVTB_ID = ?";
	private static final String DELETE = "DELETE FROM AVTB WHERE AVTB_ID";
	private static final String GET_ONE_STMT ="SELECT AVTB_ID,"
			+ "REST_ID"
			+ "FROM AVTB WHERE AVTB_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AVTB";
	
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
	public void insert(AvtbVO avtbVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, avtbVO.getAvtb_id());
			pstmt.setInt(2, avtbVO.getAvtb_id());
			pstmt.setDate(3, (Date) avtbVO.getAvtb_date_s());
			pstmt.setDate(4, (Date) avtbVO.getAvtb_date_e());
			pstmt.setInt(5, avtbVO.getAvtb_reservation());
			pstmt.setInt(6, avtbVO.getAvtb_max_reservation());
	
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
	public void update(AvtbVO avtbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, avtbVO.getAvtb_max_reservation());
		
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
	public void delete(Integer avtb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, avtb_id);

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
	public AvtbVO findByPrimaryKey(Integer avtb_id) {
		AvtbVO avtbVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, avtb_id);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
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
		return avtbVO;
	}

	@Override
	public List<AvtbVO> getAll() {
		List<AvtbVO> list = new ArrayList<AvtbVO>();
		AvtbVO avtbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setAvtb_date_s(rs.getDate("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getDate("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
				list.add(avtbVO); // Store the row in the list
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
		AvtbDAO dao = new AvtbDAO();
		
		//新增
//		AvtbVO avtbin = new AvtbVO();
//		avtbin.setAvtb_id(3500015);
//		avtbin.setRest_id(3000008);
//		avtbin.setAvtb_date_s(java.sql.Date.valueOf("07-8月 -17"));
//		avtbin.setAvtb_date_e(java.sql.Date.valueOf("07-8月 -17"));
//		dao.insert(avtbin);
		
		//刪除
//		dao.delete();
		
		//更新
		AvtbVO avtbup = new AvtbVO();
		avtbup.setAvtb_id(3500014);
		avtbup.setAvtb_reservation(20);
		System.out.println("*************");
		dao.update(avtbup);
		
		//查詢一筆
//		AvtbVO avtbVOPK = dao.findByPrimaryKey(3500001);
//		System.out.print("訂位編號:"+avtbVOPK.getAvtb_id() + "\n");
//		System.out.print("餐廳編號:"+avtbVOPK.getRest_id() + ",");
//		System.out.print("時段(s):"+avtbVOPK.getAvtb_date_s() + ",");
//		System.out.print("時段(e):"+avtbVOPK.getAvtb_date_e() + ",");
//		System.out.print("訂位:"+avtbVOPK.getAvtb_reservation() + ",");
//		System.out.println();
		
		//查詢
		List<AvtbVO> list = dao.getAll();
		for (AvtbVO aavtb : list) {
			System.out.print(":"+aavtb.getAvtb_id()+ "\n");
			System.out.print("餐廳編號:"+aavtb.getRest_id() + ",");
			System.out.print("時段(s):"+aavtb.getAvtb_date_s() + ",");
			System.out.print("時段(e):"+aavtb.getAvtb_date_e() + ",");
			System.out.print("訂位:"+aavtb.getAvtb_reservation() + ",");
			System.out.print("最大可訂位:"+aavtb.getAvtb_max_reservation());
			System.out.println();
			System.out.println("-------------");
		}
	}
}
