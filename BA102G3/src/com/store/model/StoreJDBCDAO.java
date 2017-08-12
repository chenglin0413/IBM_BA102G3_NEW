package com.store.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.prod.model.ProdVO;



public class StoreJDBCDAO implements StoreDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO STORE (STORE_ID,USER_ID,STORE_NAME,STORE_TIME,STORE_PHONE,STORE_DESCRIBE,STORE_TER,STORE_FLOOR,STORE_LON,STORE_LAT,STORE_INOUT,STORE_COUNT,STORE_SCORE)"
			+ " VALUES(STORE_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE STORE SET USER_ID=?,STORE_NAME=?,STORE_TIME=?,STORE_PHONE=?,STORE_DESCRIBE=?,STORE_TER=?,STORE_FLOOR=?,STORE_LON=?,STORE_LAT=?,STORE_INOUT=?,STORE_COUNT=?,STORE_SCORE=? where STORE_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM STORE WHERE STORE_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM STORE WHERE STORE_ID=?";
	private static final String GET_ALL = "SELECT * FROM STORE ";

	@Override
	public void insert(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, storeVO.getUser_id());
			pstmt.setString(2, storeVO.getStore_name());
			pstmt.setString(3, storeVO.getStore_time());
			pstmt.setString(4, storeVO.getStore_phone());
			pstmt.setString(5, storeVO.getStore_describe());
			pstmt.setInt(6, storeVO.getStore_ter());
			pstmt.setString(7, storeVO.getStore_floor());
			pstmt.setDouble(8, storeVO.getStore_lon());
			pstmt.setDouble(9, storeVO.getStore_lat());
			pstmt.setInt(10, storeVO.getStore_inout());
			pstmt.setInt(11, storeVO.getStore_count());
			pstmt.setInt(12, storeVO.getStore_score());
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
	public void update(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, storeVO.getUser_id());
			pstmt.setString(2, storeVO.getStore_name());
			pstmt.setString(3, storeVO.getStore_time());
			pstmt.setString(4, storeVO.getStore_phone());
			pstmt.setString(5, storeVO.getStore_describe());
			pstmt.setInt(6, storeVO.getStore_ter());
			pstmt.setString(7, storeVO.getStore_floor());
			pstmt.setDouble(8, storeVO.getStore_lon());
			pstmt.setDouble(9, storeVO.getStore_lat());
			pstmt.setInt(10, storeVO.getStore_inout());
			pstmt.setInt(11, storeVO.getStore_count());
			pstmt.setInt(12, storeVO.getStore_score());
			pstmt.setInt(13, storeVO.getStore_id());
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
	public void delete(Integer store_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, store_id);
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
	
	public StoreVO findByPrimaryKey(Integer store_id) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, store_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_id(rs.getInt("store_id"));
				storeVO.setUser_id(rs.getInt("user_id"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_time(rs.getString("store_time"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_describe(rs.getString("store_describe"));
				storeVO.setStore_ter(rs.getInt("store_ter"));
				storeVO.setStore_floor(rs.getString("store_floor"));
				storeVO.setStore_lon(rs.getDouble("store_lon"));
				storeVO.setStore_lat(rs.getDouble("store_lat"));
				storeVO.setStore_inout(rs.getInt("store_inout"));
				storeVO.setStore_count(rs.getInt("store_count"));
				storeVO.setStore_score(rs.getInt("store_score"));
				
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

		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		// TODO Auto-generated method stub
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs=pstmt.executeQuery();

			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_id(rs.getInt("store_id"));
				storeVO.setUser_id(rs.getInt("user_id"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_time(rs.getString("store_time"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_describe(rs.getString("store_describe"));
				storeVO.setStore_ter(rs.getInt("store_ter"));
				storeVO.setStore_floor(rs.getString("store_floor"));
				storeVO.setStore_lon(rs.getDouble("store_lon"));
				storeVO.setStore_lat(rs.getDouble("store_lat"));
				storeVO.setStore_inout(rs.getInt("store_inout"));
				storeVO.setStore_count(rs.getInt("store_count"));
				storeVO.setStore_score(rs.getInt("store_score"));
				
				list.add(storeVO);
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
		StoreJDBCDAO storeJDBC = new StoreJDBCDAO();

		// �s�W
		StoreVO storeVO1 = new StoreVO();

//		 storeVO1.setUser_id(1000003);
//		 storeVO1.setStore_name("�W��è");
//		 storeVO1.setStore_time("���W�K�I~�ߤW�K�I");
//		 storeVO1.setStore_phone("091125551");
//		 storeVO1.setStore_describe("�u���n�Y�S�A�F!");
//		 storeVO1.setStore_ter(2);
//		 storeVO1.setStore_floor("2F");
//		 storeVO1.setStore_lon(25.079);
//		 storeVO1.setStore_lat(25.07);
//		 storeVO1.setStore_inout(2);
//		 storeVO1.setStore_count(5);
//		 storeVO1.setStore_score(50);
		 
//		 storeJDBC.insert(storeVO1);
		 
		 System.out.println("-------�s�W�@��*--------");
//
		// �ק�
//		StoreVO storeVO2 = new StoreVO();
//		storeVO2.setStore_id(2000004);
//		storeVO2.setUser_id(1000002);
//		storeVO2.setStore_name("�����Y");
//		storeVO2.setStore_time("���W�K�I~�ߤW�K�I");
//		storeVO2.setStore_phone("0910225321");
//		storeVO2.setStore_describe("�u���n�Y");
//		storeVO2.setStore_ter(2);
//		storeVO2.setStore_floor("2F");
//		storeVO2.setStore_lon(2.2);
//		storeVO2.setStore_lat(2.2);
//     	storeVO2.setStore_inout(1);
//		storeVO2.setStore_count(2);
//		storeVO2.setStore_score(50);
//		
//		
//		storeJDBC.update(storeVO2);
		System.out.println("---------��s�@��------------");
		
		//�R��
//		storeJDBC.delete(2000009);
//		System.out.println("�R���@��");
		
		
		// �d��

//		StoreVO storeVO3 = storeJDBC.findByPrimaryKey(2000002);
//
//		System.out.print(storeVO3.getStore_id() + ",\n");
//		System.out.print(storeVO3.getUser_id() + ",");
//		System.out.print(storeVO3.getStore_name() + ",");
//		System.out.print(storeVO3.getStore_time() + ",");
//		System.out.print(storeVO3.getStore_phone() + ",");
//		System.out.print(storeVO3.getStore_describe() + ",");
//		System.out.print(storeVO3.getStore_ter() + ",");
//		System.out.print(storeVO3.getStore_floor() + ",");
//		System.out.print(storeVO3.getStore_lon() + ",");
//		System.out.print(storeVO3.getStore_lat() + ",");
//		System.out.print(storeVO3.getStore_inout() + ",");
//		System.out.print(storeVO3.getStore_count() + ",");
//		System.out.print(storeVO3.getStore_score() + ",\n");
		
		System.out.println("---------�d�߳浧------------");
//		
//		
//		
		List<StoreVO> list = storeJDBC.getAll();
		for (StoreVO aStore : list) {
			System.out.print(aStore.getStore_id() + ",");
			System.out.print(aStore.getUser_id() + ",");
			System.out.print(aStore.getStore_name() + ",");
			System.out.print(aStore.getStore_time() + ",");
			System.out.print(aStore.getStore_phone() + ",");
			System.out.print(aStore.getStore_describe() + ",");
			System.out.print(aStore.getStore_ter() + ",");
			System.out.print(aStore.getStore_floor() + ",");
			System.out.print(aStore.getStore_lon() + ",");
			System.out.print(aStore.getStore_lat() + ",");
			System.out.print(aStore.getStore_inout() + ",");
			System.out.print(aStore.getStore_count() + ",");
			System.out.print(aStore.getStore_score() + ",\n");
			
			
		}
		System.out.println("\n----------------�d���`��-------------");
	}

	@Override
	public void update(StoreVO storeVO, Integer user_status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StoreVO findByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreVO> getAllbyStatus(Integer user_status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> findByAllProd(Integer stroe_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
