package com.user.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.store.model.*;

public class UserDAO implements UserDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT=
			"INSERT INTO USER_TABLE("
			+ "USER_ID,"
			+ "USER_ACCOUNT,"
			+ "USER_PASSWD,"
			+ "USER_TYPE,"
			+ "USER_LASTNAME,"
			+ "USER_FIRSTNAME,"
			+ "USER_PHONE,"
			+ "USER_MOBILE,"
			+ "USER_ADDRESS,"
			+ "USER_EMAIL,"
			+ "USER_JOINDATE,"
			+ "USER_STATUS,"
			+ "USER_IMG,"
			+ "USER_IMGFMT)"
			+ " VALUES (USER_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_SOTRE_STMT = 
			"INSERT INTO STORE ("
			+ "STORE_ID,"
			+ "USER_ID,"
			+ "STORE_NAME,"
			+ "STORE_TIME,"
			+ "STORE_PHONE,"
			+ "STORE_DESCRIBE,"
			+ "STORE_TER,"
			+ "STORE_FLOOR,"
			+ "STORE_LON,"
			+ "STORE_LAT,"
			+ "STORE_INOUT,"
			+ "STORE_COUNT,"
			+ "STORE_SCORE)"
			+ " VALUES(STORE_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String GET_ALL_STMT =
			"SELECT USER_ID,"
			+ "USER_ACCOUNT,"
			+ "USER_PASSWD,"
			+ "USER_TYPE,"
			+ "USER_LASTNAME,"
			+ "USER_FIRSTNAME,"
			+ "USER_PHONE,"
			+ "USER_MOBILE,"
			+ "USER_ADDRESS,"
			+ "USER_EMAIL,"
			+ "USER_JOINDATE,"
			+ "USER_STATUS,"
			+ "USER_IMG, "
			+ "USER_IMGFMT "
			+ "FROM USER_TABLE order by USER_ID desc";
	private static final String GET_ALLBYTYPE_STMT =
			"SELECT USER_ID,"
			+ "USER_ACCOUNT,"
			+ "USER_PASSWD,"
			+ "USER_TYPE,"
			+ "USER_LASTNAME,"
			+ "USER_FIRSTNAME,"
			+ "USER_PHONE,"
			+ "USER_MOBILE,"
			+ "USER_ADDRESS,"
			+ "USER_EMAIL,"
			+ "USER_JOINDATE,"
			+ "USER_STATUS,"
			+ "USER_IMG, "
			+ "USER_IMGFMT "
			+ "FROM USER_TABLE where USER_TYPE=? order by USER_ID desc";
		
	private static final String GET_ONE_STMT =
			"SELECT USER_ID,"
			+ "USER_ACCOUNT,"
			+ "USER_PASSWD,"
			+ "USER_TYPE,"
			+ "USER_LASTNAME,"
			+ "USER_FIRSTNAME,"
			+ "USER_PHONE,"
			+ "USER_MOBILE,"
			+ "USER_ADDRESS,"
			+ "USER_EMAIL,"
			+ "USER_JOINDATE,"
			+ "USER_STATUS,"
			+ "USER_IMG,"
			+ "USER_IMGFMT "
			+ "FROM USER_TABLE WHERE USER_ID=?";
	
	private static final String GET_ONE_ACCOUNT_STMT =
			"SELECT USER_ID,"
			+ "USER_ACCOUNT,"
			+ "USER_PASSWD,"
			+ "USER_TYPE,"
			+ "USER_LASTNAME,"
			+ "USER_FIRSTNAME,"
			+ "USER_PHONE,"
			+ "USER_MOBILE,"
			+ "USER_ADDRESS,"
			+ "USER_EMAIL,"
			+ "USER_JOINDATE,"
			+ "USER_STATUS,"
			+ "USER_IMG,"
			+ "USER_IMGFMT "
			+ "FROM USER_TABLE WHERE USER_ACCOUNT=?";
	private static final String DELETE=
			"DELETE FROM USER_TABLE WHERE USER_ID = ?";
	private static final String UPDATE=
			"UPDATE USER_TABLE set "
			+ "USER_ACCOUNT=?,"
			+ "USER_PASSWD=?,"
			+ "USER_TYPE=?,"
			+ "USER_LASTNAME=?,"
			+ "USER_FIRSTNAME=?,"
			+ "USER_PHONE=?,"
			+ "USER_MOBILE=?,"
			+ "USER_ADDRESS=?,"
			+ "USER_EMAIL=?,"
			+ "USER_JOINDATE=?,"
			+ "USER_STATUS=?,"
			+ "USER_IMG=?,"
			+ "USER_IMGFMT=?"
			+ "WHERE USER_ID=?";	
	private static final String UPDATENOIMG=
			"UPDATE USER_TABLE set "
			+ "USER_ACCOUNT=?,"
			+ "USER_PASSWD=?,"
			+ "USER_TYPE=?,"
			+ "USER_LASTNAME=?,"
			+ "USER_FIRSTNAME=?,"
			+ "USER_PHONE=?,"
			+ "USER_MOBILE=?,"
			+ "USER_ADDRESS=?,"
			+ "USER_EMAIL=?,"
			+ "USER_JOINDATE=?,"
			+ "USER_STATUS=?"
			+ "WHERE USER_ID=?";
		
	@Override
	public void insert(UserVO userVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);		
			pstmt.setString(1,userVO.getUser_account());
			pstmt.setString(2,userVO.getUser_passwd());
			pstmt.setInt(3,userVO.getUser_type());
			pstmt.setString(4,userVO.getUser_lastname());
			pstmt.setString(5,userVO.getUser_firstname());
			pstmt.setString(6,userVO.getUser_phone());
			pstmt.setString(7,userVO.getUser_mobile());
			pstmt.setString(8,userVO.getUser_address());
			pstmt.setString(9,userVO.getUser_email());
			pstmt.setDate(10,userVO.getUser_joindate());
			pstmt.setInt(11,userVO.getUser_status());
			pstmt.setBytes(12,userVO.getUser_img());			
			pstmt.setString(13,userVO.getUser_imgfmt());
			
			pstmt.executeUpdate();
			
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
	public void update(UserVO userVO, int updateImg) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
						
			con=ds.getConnection();
			
			if (updateImg==1){
				pstmt=con.prepareStatement(UPDATE);		
				pstmt.setString(1,userVO.getUser_account());
				pstmt.setString(2,userVO.getUser_passwd());
				pstmt.setInt(3,userVO.getUser_type());
				pstmt.setString(4,userVO.getUser_lastname());
				pstmt.setString(5,userVO.getUser_firstname());
				pstmt.setString(6,userVO.getUser_phone());
				pstmt.setString(7,userVO.getUser_mobile());
				pstmt.setString(8,userVO.getUser_address());
				pstmt.setString(9,userVO.getUser_email());
				pstmt.setDate(10,userVO.getUser_joindate());
				pstmt.setInt(11,userVO.getUser_status());
				pstmt.setBytes(12,userVO.getUser_img());
				pstmt.setString(13,userVO.getUser_imgfmt());
				pstmt.setInt(14,userVO.getUser_id());				
			}
			
			if (updateImg==0){
				pstmt=con.prepareStatement(UPDATENOIMG);		
				pstmt.setString(1,userVO.getUser_account());
				pstmt.setString(2,userVO.getUser_passwd());
				pstmt.setInt(3,userVO.getUser_type());
				pstmt.setString(4,userVO.getUser_lastname());
				pstmt.setString(5,userVO.getUser_firstname());
				pstmt.setString(6,userVO.getUser_phone());
				pstmt.setString(7,userVO.getUser_mobile());
				pstmt.setString(8,userVO.getUser_address());
				pstmt.setString(9,userVO.getUser_email());
				pstmt.setDate(10,userVO.getUser_joindate());
				pstmt.setInt(11,userVO.getUser_status());
				pstmt.setInt(12,userVO.getUser_id());				
			}			
			
			pstmt.executeUpdate();
			
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
	public void delete(Integer user_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, user_id);

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
	public UserVO findByPrimaryKey(Integer user_id) {
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				userVO = new UserVO();
				userVO.setUser_id(rs.getInt("user_id"));
				userVO.setUser_account(rs.getString("user_account"));
				userVO.setUser_passwd(rs.getString("user_passwd"));
				userVO.setUser_type(rs.getInt("user_type"));
				userVO.setUser_lastname(rs.getString("user_lastname"));
				userVO.setUser_firstname(rs.getString("user_firstname"));
				userVO.setUser_phone(rs.getString("user_phone"));
				userVO.setUser_mobile(rs.getString("user_mobile"));
				userVO.setUser_address(rs.getString("user_address"));
				userVO.setUser_email(rs.getString("user_email"));
				userVO.setUser_joindate(rs.getDate("user_joindate"));
				userVO.setUser_status(rs.getInt("user_status"));
				userVO.setUser_img(rs.getBytes("user_img"));
				userVO.setUser_imgfmt(rs.getString("user_imgfmt"));
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
		return userVO;

	}
	
	@Override
	public UserVO findByAccount(String user_account) {
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT_STMT);
		
			pstmt.setString(1, user_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				userVO = new UserVO();
				userVO.setUser_id(rs.getInt("user_id"));
				userVO.setUser_account(rs.getString("user_account"));
				userVO.setUser_passwd(rs.getString("user_passwd"));
				userVO.setUser_type(rs.getInt("user_type"));
				userVO.setUser_lastname(rs.getString("user_lastname"));
				userVO.setUser_firstname(rs.getString("user_firstname"));
				userVO.setUser_phone(rs.getString("user_phone"));
				userVO.setUser_mobile(rs.getString("user_mobile"));
				userVO.setUser_address(rs.getString("user_address"));
				userVO.setUser_email(rs.getString("user_email"));
				userVO.setUser_joindate(rs.getDate("user_joindate"));
				userVO.setUser_status(rs.getInt("user_status"));
				userVO.setUser_img(rs.getBytes("user_img"));
				userVO.setUser_imgfmt(rs.getString("user_imgfmt"));
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
		return userVO;

	}


	@Override
	public List<UserVO> getAll() {

		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				userVO = new UserVO();
				userVO.setUser_id(rs.getInt("user_id"));
				userVO.setUser_account(rs.getString("user_account"));
				userVO.setUser_passwd(rs.getString("user_passwd"));
				userVO.setUser_type(rs.getInt("user_type"));
				userVO.setUser_lastname(rs.getString("user_lastname"));
				userVO.setUser_firstname(rs.getString("user_firstname"));
				userVO.setUser_phone(rs.getString("user_phone"));
				userVO.setUser_mobile(rs.getString("user_mobile"));
				userVO.setUser_address(rs.getString("user_address"));
				userVO.setUser_email(rs.getString("user_email"));
				userVO.setUser_joindate(rs.getDate("user_joindate"));
				userVO.setUser_status(rs.getInt("user_status"));
				userVO.setUser_img(rs.getBytes("user_img"));
				userVO.setUser_imgfmt(rs.getString("user_imgfmt"));
				list.add(userVO); // Store the row in the list
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
		return list;

	}

	@Override
	public List<UserVO> getAllByType(int user_type) {
		// TODO Auto-generated method stub
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLBYTYPE_STMT);
			pstmt.setInt(1, user_type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				userVO = new UserVO();
				userVO.setUser_id(rs.getInt("user_id"));
				userVO.setUser_account(rs.getString("user_account"));
				userVO.setUser_passwd(rs.getString("user_passwd"));
				userVO.setUser_type(rs.getInt("user_type"));
				userVO.setUser_lastname(rs.getString("user_lastname"));
				userVO.setUser_firstname(rs.getString("user_firstname"));
				userVO.setUser_phone(rs.getString("user_phone"));
				userVO.setUser_mobile(rs.getString("user_mobile"));
				userVO.setUser_address(rs.getString("user_address"));
				userVO.setUser_email(rs.getString("user_email"));
				userVO.setUser_joindate(rs.getDate("user_joindate"));
				userVO.setUser_status(rs.getInt("user_status"));
				userVO.setUser_img(rs.getBytes("user_img"));
				userVO.setUser_imgfmt(rs.getString("user_imgfmt"));
				list.add(userVO); // Store the row in the list
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
		return list;

	}

	@Override
	public List<UserVO> getAllStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertStore(UserVO userVO, StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement pstmt=null;
		String next_user_id=null;
		
		try {
			con=ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[]={"USER_ID"};
			pstmt=con.prepareStatement(INSERT_STMT, cols);		
			pstmt.setString(1,userVO.getUser_account());
			pstmt.setString(2,userVO.getUser_passwd());
			pstmt.setInt(3,userVO.getUser_type());
			pstmt.setString(4,userVO.getUser_lastname());
			pstmt.setString(5,userVO.getUser_firstname());
			pstmt.setString(6,userVO.getUser_phone());
			pstmt.setString(7,userVO.getUser_mobile());
			pstmt.setString(8,userVO.getUser_address());
			pstmt.setString(9,userVO.getUser_email());
			pstmt.setDate(10,userVO.getUser_joindate());
			pstmt.setInt(11,userVO.getUser_status());
			pstmt.setBytes(12,userVO.getUser_img());			
			pstmt.setString(13,userVO.getUser_imgfmt());
			pstmt.executeUpdate();
			
			System.out.println("pass UserDAO l.618");
			
			ResultSet rs=pstmt.getGeneratedKeys();
			
			System.out.println("pass UserDAO l.622");
			
			if (rs.next()){
				next_user_id=rs.getString(1);
			} else {
				System.out.println("fails to add user! ");
			}
						
			pstmt=con.prepareStatement(INSERT_SOTRE_STMT);
			pstmt.setInt(1, new Integer(next_user_id));
			
			System.out.println(new Integer(next_user_id));
			
			pstmt.setString(2, storeVO.getStore_name());
			pstmt.setString(3, storeVO.getStore_time());
			pstmt.setString(4, storeVO.getStore_phone());
			pstmt.setString(5, storeVO.getStore_describe());
			pstmt.setInt(6, storeVO.getStore_ter());
			pstmt.setString(7, storeVO.getStore_floor());
			pstmt.setDouble(8, storeVO.getStore_lon());
			pstmt.setDouble(9, storeVO.getStore_lat());
			System.out.println("pass UserDAO 1");
			int a=storeVO.getStore_inout();
			System.out.println("storeVO.getStore_inout(): "+a);
			pstmt.setInt(10, storeVO.getStore_inout());
			pstmt.setInt(11, storeVO.getStore_count());
			System.out.println("pass UserDAO 2");
			pstmt.setInt(12, storeVO.getStore_score());
			
			pstmt.executeUpdate();
			
			System.out.println("pass UserDAO store executeUpdate");
			
			con.commit();

			con.setAutoCommit(true);
			
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
}
