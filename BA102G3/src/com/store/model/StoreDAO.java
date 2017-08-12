package com.store.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.prod.model.ProdVO;

public class StoreDAO implements StoreDAO_interface {
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	
	private static final String INSERT_STMT = "INSERT INTO STORE (STORE_ID,USER_ID,STORE_NAME,STORE_TIME,STORE_PHONE,STORE_DESCRIBE,STORE_TER,STORE_FLOOR,STORE_LON,STORE_LAT,STORE_INOUT,STORE_COUNT,STORE_SCORE)"
			+ " VALUES(STORE_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE STORE SET USER_ID=?,STORE_NAME=?,STORE_TIME=?,STORE_PHONE=?,STORE_DESCRIBE=?,STORE_TER=?,STORE_FLOOR=?,STORE_LON=?,STORE_LAT=?,STORE_INOUT=?,STORE_COUNT=?,STORE_SCORE=? where STORE_ID = ?";
	private static final String UPDATE_USER_STATUS_STMT = "UPDATE USER_TABLE SET USER_STATUS=? WHERE USER_ID=?";
	private static final String DELETE_STMT = "DELETE FROM STORE WHERE STORE_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM STORE WHERE STORE_ID=?";
	private static final String GET_ONE_BY_USERID_STMT = "SELECT * FROM STORE WHERE USER_ID=?";
	private static final String GET_ALL_BY_STATUS_STMT = "SELECT s.STORE_ID,s.USER_ID,s.STORE_NAME,s.STORE_TIME,s.STORE_PHONE,s.STORE_DESCRIBE,s.STORE_TER,s.STORE_FLOOR,s.STORE_LON,s.STORE_LAT,s.STORE_INOUT,s.STORE_COUNT,s.STORE_SCORE from STORE s join USER_TABLE u on (s.user_id=u.user_id) where u.user_status=? order by s.STORE_ID desc";
	private static final String GET_ALL = "SELECT * FROM STORE order by STORE_ID desc";
	
	
	
	private static final String STORE_GET_ALLPORD = "SELECT * FROM PROD WHERE STORE_ID = ?";//李浩，促銷商品
	@Override
	public void insert(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
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
	public void update(StoreVO storeVO, Integer user_status) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
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
			
			pstmt = con.prepareStatement(UPDATE_USER_STATUS_STMT);
			pstmt.setInt(1, user_status);
			pstmt.setInt(2, storeVO.getUser_id());
			pstmt.executeUpdate();
			
			con.commit();

			con.setAutoCommit(true);

		
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
	public void delete(Integer store_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con =ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, store_id);
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
	
	public StoreVO findByPrimaryKey(Integer store_id) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
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

		} catch (SQLException  se) {
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
			
			con = ds.getConnection();
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
	public List<StoreVO> getAllbyStatus(Integer user_status) {
		// TODO Auto-generated method stub
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS_STMT);

			pstmt.setInt(1, user_status);
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
	public StoreVO findByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_USERID_STMT);
			pstmt.setInt(1, user_id);
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

		} catch (SQLException  se) {
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
	public List<ProdVO> findByAllProd(Integer store_id) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(STORE_GET_ALLPORD);

			pstmt.setInt(1, store_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProd_id(rs.getInt("prod_id"));
				prodVO.setStore_id(rs.getInt("store_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_descript(rs.getString("prod_descript"));
				prodVO.setProd_price(rs.getInt("prod_price"));
				prodVO.setProd_sort(rs.getString("prod_sort"));
				prodVO.setProd_format(rs.getString("prod_format"));
				prodVO.setProd_brand(rs.getString("prod_brand"));
				prodVO.setProd_updatetime(rs.getDate("prod_updatetime"));
				prodVO.setProd_soldcount(rs.getInt("prod_soldcount"));
				prodVO.setProd_status(rs.getInt("prod_status"));
				prodVO.setProd_count(rs.getInt("prod_count"));
				prodVO.setProd_score(rs.getInt("prod_score"));
				list.add(prodVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
}
