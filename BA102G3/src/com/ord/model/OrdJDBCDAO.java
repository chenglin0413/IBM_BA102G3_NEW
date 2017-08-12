package com.ord.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.item.model.ItemVO;

public class OrdJDBCDAO implements OrdDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO ORD (ORD_ID,USER_ID,STORE_ID,ORD_DATE,ORD_TOTAL,ORD_BILL,ORD_GRANT,ORD_STATUS,ORD_SSCORE,ORD_RPDATE,ORD_RPCOMM,ORD_RPSTATUS)"
			+ "VALUES(ORD_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE ORD SET USER_ID= ?,STORE_ID=?,ORD_DATE=?,ORD_TOTAL=?,ORD_BILL=?,ORD_GRANT=?,ORD_STATUS=?,ORD_SSCORE=?,ORD_RPDATE=?,ORD_RPCOMM=?,ORD_RPSTATUS=? WHERE ORD_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM ORD WHERE ORD_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM ORD WHERE ORD_ID=?";
	private static final String GET_ALL = "SELECT * FROM ORD";
	

	@Override
	public void insert(OrdVO ordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setInt(1, ordVO.getUser_id());
			pstmt.setInt(2, ordVO.getStore_id());
			pstmt.setDate(3, ordVO.getOrd_date());
			pstmt.setInt(4, ordVO.getOrd_total());
			pstmt.setInt(5, ordVO.getOrd_bill());
			pstmt.setInt(6, ordVO.getOrd_grant());
			pstmt.setInt(7, ordVO.getOrd_status());
			pstmt.setInt(8, ordVO.getOrd_sscore());
			pstmt.setDate(9, ordVO.getOrd_rpdate());
			pstmt.setString(10, ordVO.getOrd_rpcomm());
			pstmt.setInt(11, ordVO.getOrd_rpstatus());
			
			
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
	public void update(OrdVO ordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, ordVO.getUser_id());
			pstmt.setInt(2, ordVO.getStore_id());
			pstmt.setDate(3, ordVO.getOrd_date());
			pstmt.setInt(4, ordVO.getOrd_total());
			pstmt.setInt(5, ordVO.getOrd_bill());
			pstmt.setInt(6, ordVO.getOrd_grant());
			pstmt.setInt(7, ordVO.getOrd_status());
			pstmt.setInt(8, ordVO.getOrd_sscore());
			pstmt.setDate(9, ordVO.getOrd_rpdate());
			pstmt.setString(10, ordVO.getOrd_rpcomm());
			pstmt.setInt(11, ordVO.getOrd_rpstatus());
			pstmt.setInt(12, ordVO.getOrd_id());
			

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
	public void delete(Integer ord_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, ord_id);
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
	public OrdVO findByPrimaryKey(Integer ord_id) {
		// TODO Auto-generated method stub
		OrdVO ordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ord_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ordVO = new OrdVO();
				ordVO.setOrd_id(rs.getInt("ord_id"));
				ordVO.setUser_id(rs.getInt("user_id"));
				ordVO.setStore_id(rs.getInt("store_id"));
				ordVO.setOrd_date(rs.getDate("ord_date"));
				ordVO.setOrd_total(rs.getInt("ord_total"));
				ordVO.setOrd_bill(rs.getInt("ord_bill"));
				ordVO.setOrd_grant(rs.getInt("ord_grant"));
				ordVO.setOrd_status(rs.getInt("ord_status"));
				ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
				ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
				ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
				ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
				

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

		return ordVO;
	}

	
	
	
	
	
	
	@Override
	public List<OrdVO> getAll() {
		// TODO Auto-generated method stub
		List<OrdVO> list = new ArrayList<OrdVO>();
		OrdVO ordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs=pstmt.executeQuery();

			while (rs.next()) {
				ordVO = new OrdVO();
				ordVO.setOrd_id(rs.getInt("ord_id"));
				ordVO.setUser_id(rs.getInt("user_id"));
				ordVO.setStore_id(rs.getInt("store_id"));
				ordVO.setOrd_date(rs.getDate("ord_date"));
				ordVO.setOrd_total(rs.getInt("ord_total"));
				ordVO.setOrd_bill(rs.getInt("ord_bill"));
				ordVO.setOrd_grant(rs.getInt("ord_grant"));
				ordVO.setOrd_status(rs.getInt("ord_status"));
				ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
				ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
				ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
				ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
				list.add(ordVO);
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

	public static void main(String[] args) {
		OrdJDBCDAO ordJDBC = new OrdJDBCDAO();

		// 新增
		OrdVO ordVO1 = new OrdVO();
		

		 ordVO1.setUser_id(1000001);
		 ordVO1.setStore_id(2000003);
		 ordVO1.setOrd_date(java.sql.Date.valueOf("2017-08-31"));
		 ordVO1.setOrd_total(500);
		 ordVO1.setOrd_bill(1);
		 ordVO1.setOrd_grant(2);
		 ordVO1.setOrd_status(1);
		 ordVO1.setOrd_sscore(5);
		 ordVO1.setOrd_rpdate(java.sql.Date.valueOf("2017-09-31"));
		 ordVO1.setOrd_rpcomm("有礙觀瞻");
		 ordVO1.setOrd_rpstatus(0);
		 
		 ordJDBC.insert(ordVO1);
		 System.out.println("---------新增一筆-----------");
//
//		// 修改
		 OrdVO ordVO2 = new OrdVO();
		 ordVO2.setOrd_id(2400001);
		 ordVO2.setUser_id(1000002);
		 ordVO2.setStore_id(2000003);
		 ordVO2.setOrd_date(java.sql.Date.valueOf("2017-08-31"));
		 ordVO2.setOrd_total(5000);
		 ordVO2.setOrd_bill(1);
		 ordVO2.setOrd_grant(1);
		 ordVO2.setOrd_status(1);
		 ordVO2.setOrd_sscore(4);
		 ordVO2.setOrd_rpdate(java.sql.Date.valueOf("2017-09-30"));
		 ordVO2.setOrd_rpcomm("JAVA口訣第三句，宣告、取值，拿去用");
		 ordVO2.setOrd_rpstatus(0);
		
		 
		 ordJDBC.update(ordVO2);
		 System.out.println("---------修改一筆-----------");
//		
		
		//刪除
//		ordJDBC.delete(2200001);
//		System.out.println("-------------刪除一筆-----------");
		
		// 查詢

		OrdVO ordVO3 = ordJDBC.findByPrimaryKey(2400002);

		System.out.print(ordVO3.getOrd_id() + ",");
		System.out.print(ordVO3.getUser_id() + ",");
		System.out.print(ordVO3.getStore_id() + ",");
		System.out.print(ordVO3.getOrd_date() + ",");
		System.out.print(ordVO3.getOrd_total() + ",");
		System.out.print(ordVO3.getOrd_bill() + ",\n");
		System.out.print(ordVO3.getOrd_grant() + ",");
		System.out.print(ordVO3.getOrd_status() + ",");
		System.out.print(ordVO3.getOrd_sscore() + ",");
		System.out.print(ordVO3.getOrd_rpdate() + ",");
		System.out.print(ordVO3.getOrd_rpcomm() + ",");
		System.out.print(ordVO3.getOrd_rpstatus() + ",\n");
		
		
		System.out.println("----------查詢單筆-----------");
//		
//		
//		
		List<OrdVO> list = ordJDBC.getAll();
		for (OrdVO aOrd : list) {
			System.out.print(aOrd.getOrd_id() + ",");
			System.out.print(aOrd.getUser_id() + ",");
			System.out.print(aOrd.getStore_id() + ",");
			System.out.print(aOrd.getOrd_date() + ",");
			System.out.print(aOrd.getOrd_total() + ",");
			System.out.print(aOrd.getOrd_bill() + ",");
			System.out.print(aOrd.getOrd_grant() + ",");
			System.out.print(aOrd.getOrd_status() + ",");
			System.out.print(aOrd.getOrd_sscore() + ",");
			System.out.print(aOrd.getOrd_rpdate() + ",");
			System.out.print(aOrd.getOrd_rpcomm() + ",");
			System.out.print(aOrd.getOrd_rpstatus() + ",\n");
			
		}
		System.out.println("\n----------------查詢總表-------------");

	}

	@Override
	public OrdVO select_One_ord_id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdVO> getOneStore_idAllOrd(Integer store_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdVO> getOneUser_idAllOrd(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update_sscore(Integer ord_sscore, Integer ord_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer insertWithItems(OrdVO ordVO, List<ItemVO> list) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrdVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_bill(Integer ord_bill, Integer ord_id) {
		// TODO Auto-generated method stub
		
	}




}
