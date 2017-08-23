package com.avtb.model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AvtbJDBCDAO implements AvtbDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO AVTB (AVTB_ID,REST_ID,AVTB_DATE_S,AVTB_DATE_E,AVTB_RESERVATION,AVTB_MAX_RESERVATION) "
			+ "VALUES (avtb_sq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE AVTB set AVTB_ID=?, REST_ID=?, AVTB_DATE_S=?, AVTB_DATE_E=?, AVTB_RESERVATION=?, AVTB_MAX_RESERVATION=? WHERE AVTB_ID = ?";
	private static final String UPDATE_RESERVATION = "UPDATE AVTB set AVTB_RESERVATION=? where avtb_ID = ? ";
	private static final String DELETE ="DELETE FROM avtb where avtb_ID = ?";	
	private static final String GET_ONE_STMT = "SELECT AVTB_ID, REST_ID, to_char(avtb_date_s, 'YYYY-MM-DD HH:mm:ss') avtb_date_s, AVTB_DATE_E,AVTB_RESERVATION,AVTB_MAX_RESERVATION"
			+ " FROM AVTB where AVTB_ID = ? ";
	private static final String GET_ONE_STMT_REST = "SELECT * FROM AVTB where REST_ID = ? ";
	private static final String GET_ALL_STMT = "SELECT * FROM AVTB";
	
	@Override
	public void insert(AvtbVO avtbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, avtbVO.getRest_id());
			pstmt.setTimestamp(2, avtbVO.getAvtb_date_s());
			pstmt.setTimestamp(3, avtbVO.getAvtb_date_e());
			pstmt.setInt(4, avtbVO.getAvtb_reservation());
			pstmt.setInt(5, avtbVO.getAvtb_max_reservation());
			pstmt.executeUpdate();

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, avtbVO.getAvtb_id());
			pstmt.setInt(2, avtbVO.getAvtb_id());
			pstmt.setTimestamp(3, avtbVO.getAvtb_date_s());
			pstmt.setTimestamp(4, avtbVO.getAvtb_date_e());
			pstmt.setInt(5, avtbVO.getAvtb_reservation());
			pstmt.setInt(6, avtbVO.getAvtb_max_reservation());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update_reservation(AvtbVO avtbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_RESERVATION);


			pstmt.setInt(1, avtbVO.getAvtb_reservation());
			pstmt.setInt(2, avtbVO.getAvtb_id());
		
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, avtb_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public AvtbVO findByPrimaryKey(Integer avtb_id) {
		AvtbVO avtbVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, avtb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setRest_id(rs.getInt("rest_id"));
				avtbVO.setAvtb_date_s(rs.getTimestamp("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getTimestamp("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
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
		return avtbVO;
	}

	@Override
	public List<AvtbVO> findByPrimaryKeyByRest(Integer rest_id) {
		List<AvtbVO> list = new ArrayList<AvtbVO>();
		AvtbVO avtbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_REST);

			pstmt.setInt(1, rest_id);

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setRest_id(rs.getInt("rest_id"));
				avtbVO.setAvtb_date_s(rs.getTimestamp("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getTimestamp("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
				list.add(avtbVO);
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<AvtbVO> getAll() {
		List<AvtbVO> list = new ArrayList<AvtbVO>();
		AvtbVO avtbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setRest_id(rs.getInt("rest_id"));
				avtbVO.setAvtb_date_s(rs.getTimestamp("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getTimestamp("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
				list.add(avtbVO); // Store the row in the list
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
	

	public static void main(String[] args){
		AvtbJDBCDAO dao = new AvtbJDBCDAO();
		SimpleDateFormat sdfs = new SimpleDateFormat("YYYY/MM/dd HH:mm");
		
//		SimpleDateFormat sdfe = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		//新增
//		AvtbVO avtbin = new AvtbVO();
//		avtbin.setRest_id(3000015);
//		avtbin.setAvtb_date_s(java.sql.Timestamp.valueOf("2017-08-08 04:00:00"));
//		avtbin.setAvtb_date_e(java.sql.Timestamp.valueOf("2017-08-08 04:00:00"));
//		avtbin.setAvtb_reservation(5);
//		avtbin.setAvtb_max_reservation(31);
//		dao.insert(avtbin);
		
		//刪除
//		dao.delete(3500017);
		
		//更新 
//		AvtbVO avtbup = new AvtbVO();
//		avtbup.setAvtb_reservation(26);
//		avtbup.setAvtb_id(3500016);
//		System.out.println("------------");
//		dao.update_reservation(avtbup);
//		System.out.println(avtbup.getAvtb_reservation());

		
		//查詢一筆
//		AvtbVO avtbVOPK = dao.findByPrimaryKey(3500001);
//		System.out.print("訂位編號:"+avtbVOPK.getAvtb_id() + "\n");
//		System.out.print("餐廳編號:"+avtbVOPK.getRest_id() + "\n");
////		String str_s = sdfs.format(avtbVOPK.getAvtb_date_s());
////		System.out.print("時段(s):"+str_s+"\n");
//		System.out.print("時段(s):"+avtbVOPK.getAvtb_date_s()+ "\n");		
//		String str_e = sdfs.format(avtbVOPK.getAvtb_date_e());
//		System.out.print("時段(e):"+str_e+"\n");
//		System.out.print("時段(e):"+avtbVOPK.getAvtb_date_e()+ "\n");		
//		System.out.print("訂位:"+avtbVOPK.getAvtb_reservation() + "\n");
//		System.out.print("最大可訂位數:"+avtbVOPK.getAvtb_max_reservation());
//		System.out.println();
		

		List<AvtbVO> list = dao.findByPrimaryKeyByRest(3000010);
		for (AvtbVO avtbVOPK : list) {
			System.out.print("訂位編號:"+avtbVOPK.getAvtb_id() + "\n");
			System.out.print("時段(s):"+avtbVOPK.getAvtb_date_s()+ "\n");		
			String str_e = sdfs.format(avtbVOPK.getAvtb_date_e());
			System.out.print("時段(e):"+str_e+"\n");
			System.out.print("時段(e):"+avtbVOPK.getAvtb_date_e()+ "\n");		
			System.out.println();
		}
		
		
		
		//查詢全部
//		List<AvtbVO> list = dao.getAll();
//		for (AvtbVO aavtb : list) {
//			System.out.print(":"+aavtb.getAvtb_id()+ "\n");
//			System.out.print("餐廳編號:"+aavtb.getRest_id() + ",");
//			System.out.print("時段(s):"+aavtb.getAvtb_date_s() + ",");
//			System.out.print("時段(e):"+aavtb.getAvtb_date_e() + ",");
//			System.out.print("訂位:"+aavtb.getAvtb_reservation() + ",");
//			System.out.print("最大可訂位:"+aavtb.getAvtb_max_reservation());
//			System.out.println();
//			System.out.println("-------------");
//		}
//		List<AvtbVO> list = dao.findByRestIDAndDate(3000007, "2017-08-07");
//		for (AvtbVO aavtb : list) {
//			System.out.print(":"+aavtb.getAvtb_id()+ "\n");
//			System.out.print("餐廳編號:"+aavtb.getRest_id() + ",");
//			System.out.print("時段(s):"+aavtb.getAvtb_date_s() + ",");
//			System.out.print("時段(e):"+aavtb.getAvtb_date_e() + ",");
//			System.out.print("訂位:"+aavtb.getAvtb_reservation() + ",");
//			System.out.print("最大可訂位:"+aavtb.getAvtb_max_reservation());
//			System.out.println();
//			System.out.println("-------------");
//		}
	}

	

	@Override
	public void update_max_reservation(AvtbVO avtbVO) {
		// TODO Auto-generated method stub
		
	}

	
}
