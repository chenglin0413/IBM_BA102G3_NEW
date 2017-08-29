package com.rest.model;

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

import com.store.model.StoreVO;

public class RestDAO implements RestDAO_Interface {
	
	private static final String INSERT_STMT = 
			"INSERT INTO rest (rest_id,"
			+ "user_id,"
			+ "rest_name,"
			+ "rest_address,"
			+ "rest_phone,"
			+ "rest_trans,"
			+ "rest_detail,"
			+ "rest_hours,"
			+ "rest_ter,"
			+ "rest_floor,"
			+ "rest_lon,"
			+ "rest_lat,"
			+ "rest_inout,"
			+ "rest_type,"
			+ "rest_count,"
			+ "rest_score) "
			+ "VALUES (rest_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE REST set "
			+ "rest_name=?, "
			+ "rest_address=?, "
			+ "rest_phone=?, "
			+ "rest_trans=?, "
			+ "rest_detail=?, "
			+ "rest_hours=?, "
			+ "rest_ter=?, "
			+ "rest_floor=?, "
			+ "rest_lon=?, "
			+ "rest_lat=?, "
			+ "rest_inout=?, "
			+ "rest_type=?, "
			+ "rest_count=?, "
			+ "rest_score=?"
			+ " where REST_ID = ?";
	private static final String UPDATE_USER_STATUS_STMT = "UPDATE USER_TABLE SET USER_STATUS=? WHERE USER_ID=?";
	private static final String DELETE = 
			"DELETE FROM REST WHERE REST_ID = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM REST WHERE REST_ID=?";
	private static final String GET_ALL_STMT = "SELECT * FROM REST order by rest_id desc";
	private static final String GET_ALL_BY_STATUS_STMT = "SELECT "
			+ "r.REST_ID,"
			+ "r.USER_ID,"
			+ "r.REST_NAME,"
			+ "r.REST_ADDRESS,"
			+ "r.REST_PHONE,"
			+ "r.REST_TRANS,"
			+ "r.REST_DETAIL,"
			+ "r.REST_HOURS,"
			+ "r.REST_TER,"
			+ "r.REST_FLOOR,"
			+ "r.REST_LON,"
			+ "r.REST_LAT,"
			+ "r.REST_INOUT,"
			+ "r.REST_TYPE,"
			+ "r.REST_COUNT,"
			+ "r.REST_SCORE "
			+ "from REST r join USER_TABLE u on (r.user_id=u.user_id) where u.user_status=? order by r.REST_ID desc";
		
	private static final String GET_ONE_BY_USERID_STMT = "SELECT * FROM REST WHERE USER_ID=?";
	private static final String GET_TOP_THREE = "select  * from(select * from rest order by rest_score desc) where rownum <4";
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
	public void insert(RestVO restVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, restVO.getUser_id());
			pstmt.setString(2, restVO.getRest_name());
			pstmt.setString(3, restVO.getRest_address());
			pstmt.setString(4, restVO.getRest_phone());
			pstmt.setString(5, restVO.getRest_trans());
			pstmt.setString(6, restVO.getRest_detail());
			pstmt.setString(7, restVO.getRest_hours());
			pstmt.setInt(8, restVO.getRest_ter());
			pstmt.setInt(9,restVO.getRest_floor());
			pstmt.setDouble(10, restVO.getRest_lon());
			pstmt.setDouble(11, restVO.getRest_lat());
			pstmt.setInt(12, restVO.getRest_inout());
			pstmt.setInt(13, restVO.getRest_type());
			pstmt.setInt(14,restVO.getRest_count());
			pstmt.setInt(15, restVO.getRest_score());

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
	public void update(RestVO restVO, Integer user_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, restVO.getRest_name());
			pstmt.setString(2, restVO.getRest_address());
			pstmt.setString(3, restVO.getRest_phone());
			pstmt.setString(4, restVO.getRest_trans());
			pstmt.setString(5, restVO.getRest_detail());
			pstmt.setString(6, restVO.getRest_hours());
			pstmt.setInt(7, restVO.getRest_ter());
			pstmt.setInt(8,restVO.getRest_floor());
			pstmt.setDouble(9, restVO.getRest_lon());
			pstmt.setDouble(10, restVO.getRest_lat());
			pstmt.setInt(11, restVO.getRest_inout());
			pstmt.setInt(12, restVO.getRest_type());
			pstmt.setInt(13,restVO.getRest_count());
			pstmt.setInt(14, restVO.getRest_score());
			pstmt.setInt(15, restVO.getUser_id());
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(UPDATE_USER_STATUS_STMT);
			pstmt.setInt(1, user_status);
			pstmt.setInt(2, restVO.getUser_id());
			pstmt.executeUpdate();
			
			con.commit();

			con.setAutoCommit(true);			

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
	public void delete(Integer rest_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, rest_id);

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
	public RestVO findByPrimaryKey(Integer rest_id) {
		RestVO restVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rest_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				restVO = new RestVO();				
				restVO.setRest_id(rs.getInt("rest_id"));
				restVO.setUser_id(rs.getInt("user_id"));
				restVO.setRest_name(rs.getString("rest_name"));
				restVO.setRest_address(rs.getString("rest_address"));
				restVO.setRest_phone(rs.getString("rest_phone"));
				restVO.setRest_trans(rs.getString("rest_trans"));
				restVO.setRest_detail(rs.getString("rest_detail"));
				restVO.setRest_hours(rs.getString("rest_hours"));
				restVO.setRest_ter(rs.getInt("rest_ter"));
				restVO.setRest_floor(rs.getInt("rest_floor"));
				restVO.setRest_lon(rs.getDouble("rest_lon"));
				restVO.setRest_lat(rs.getDouble("rest_lat"));
				restVO.setRest_inout(rs.getInt("rest_inout"));
				restVO.setRest_type(rs.getInt("rest_type"));
				restVO.setRest_count(rs.getInt("rest_count"));
				restVO.setRest_score(rs.getInt("rest_score"));
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
		return restVO;
	}

	@Override
	public List<RestVO> getAll() {
		List<RestVO> list = new ArrayList<RestVO>();
		RestVO restVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				restVO = new RestVO();
				restVO.setRest_id(rs.getInt("rest_id"));
				restVO.setUser_id(rs.getInt("user_id"));
				restVO.setRest_name(rs.getString("rest_name"));
				restVO.setRest_address(rs.getString("rest_address"));
				restVO.setRest_phone(rs.getString("rest_phone"));
				restVO.setRest_trans(rs.getString("rest_trans"));
				restVO.setRest_detail(rs.getString("rest_detail"));
				restVO.setRest_hours(rs.getString("rest_hours"));
				restVO.setRest_ter(rs.getInt("rest_ter"));
				restVO.setRest_floor(rs.getInt("rest_floor"));
				restVO.setRest_lon(rs.getDouble("rest_lon"));
				restVO.setRest_lat(rs.getDouble("rest_lat"));
				restVO.setRest_inout(rs.getInt("rest_inout"));
				restVO.setRest_type(rs.getInt("rest_type"));
				restVO.setRest_count(rs.getInt("rest_count"));
				restVO.setRest_score(rs.getInt("rest_score"));
				list.add(restVO); // Store the row in the list				
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

	@Override
	public List<RestVO> getAllbyStatus(Integer user_status) {
		// TODO Auto-generated method stub
		List<RestVO> list = new ArrayList<RestVO>();
		RestVO restVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS_STMT);

			pstmt.setInt(1, user_status);
			rs=pstmt.executeQuery();
			
			System.out.println("RestDAO.java line394");

			while (rs.next()) {
				
				restVO = new RestVO();
				restVO.setRest_id(rs.getInt("rest_id"));
				restVO.setUser_id(rs.getInt("user_id"));
				restVO.setRest_name(rs.getString("rest_name"));
				restVO.setRest_address(rs.getString("rest_address"));
				restVO.setRest_phone(rs.getString("rest_phone"));
				restVO.setRest_trans(rs.getString("rest_trans"));
				restVO.setRest_detail(rs.getString("rest_detail"));
				restVO.setRest_hours(rs.getString("rest_hours"));
				restVO.setRest_ter(rs.getInt("rest_ter"));
				restVO.setRest_floor(rs.getInt("rest_floor"));
				restVO.setRest_lon(rs.getDouble("rest_lon"));
				restVO.setRest_lat(rs.getDouble("rest_lat"));
				restVO.setRest_inout(rs.getInt("rest_inout"));
				restVO.setRest_type(rs.getInt("rest_type"));
				restVO.setRest_count(rs.getInt("rest_count"));
				restVO.setRest_score(rs.getInt("rest_score"));
				list.add(restVO); // Store the row in the list				
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
	public RestVO findByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		RestVO restVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_USERID_STMT);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				restVO = new RestVO();				
				restVO.setRest_id(rs.getInt("rest_id"));
				restVO.setUser_id(rs.getInt("user_id"));
				restVO.setRest_name(rs.getString("rest_name"));
				restVO.setRest_address(rs.getString("rest_address"));
				restVO.setRest_phone(rs.getString("rest_phone"));
				restVO.setRest_trans(rs.getString("rest_trans"));
				restVO.setRest_detail(rs.getString("rest_detail"));
				restVO.setRest_hours(rs.getString("rest_hours"));
				restVO.setRest_ter(rs.getInt("rest_ter"));
				restVO.setRest_floor(rs.getInt("rest_floor"));
				restVO.setRest_lon(rs.getDouble("rest_lon"));
				restVO.setRest_lat(rs.getDouble("rest_lat"));
				restVO.setRest_inout(rs.getInt("rest_inout"));
				restVO.setRest_type(rs.getInt("rest_type"));
				restVO.setRest_count(rs.getInt("rest_count"));
				restVO.setRest_score(rs.getInt("rest_score"));
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
		return restVO;
	}
	//政成新增  首頁前三名餐廳
	@Override
	public List<RestVO> getTopThree() {
		List<RestVO> list = new ArrayList<RestVO>();
		RestVO restVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_THREE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				restVO = new RestVO();
				restVO.setRest_id(rs.getInt("rest_id"));
				restVO.setUser_id(rs.getInt("user_id"));
				restVO.setRest_name(rs.getString("rest_name"));
				restVO.setRest_address(rs.getString("rest_address"));
				restVO.setRest_phone(rs.getString("rest_phone"));
				restVO.setRest_trans(rs.getString("rest_trans"));
				restVO.setRest_detail(rs.getString("rest_detail"));
				restVO.setRest_hours(rs.getString("rest_hours"));
				restVO.setRest_ter(rs.getInt("rest_ter"));
				restVO.setRest_floor(rs.getInt("rest_floor"));
				restVO.setRest_lon(rs.getDouble("rest_lon"));
				restVO.setRest_lat(rs.getDouble("rest_lat"));
				restVO.setRest_inout(rs.getInt("rest_inout"));
				restVO.setRest_type(rs.getInt("rest_type"));
				restVO.setRest_count(rs.getInt("rest_count"));
				restVO.setRest_score(rs.getInt("rest_score"));
				list.add(restVO); // Store the row in the list				
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

}
