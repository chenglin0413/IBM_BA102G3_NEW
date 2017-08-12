package com.user.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.store.model.StoreVO;

public class UserJDBCDAO implements UserDAO_Interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
	//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
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
			+ " value (USER_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			+ "FROM USER_TABLE order by USER_ID";
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
	private static final String DELETE=
			"DELETE FROM USER_TABLE WHERE USER_ID = ?";
	private static final String UPDATE=
			"UPDATE USER_TABLE set "
			+ "USER_ACCOUNT=?,"
			+ "USER_PASSWD=?,"
			+ "USER_TYPE=?, "
			+ "USER_LASTNAME=?, "
			+ "USER_FIRSTNAME=?, "
			+ "USER_PHONE=?, "
			+ "USER_MOBILE,"
			+ "USER_ADDRESS=?, "
			+ "USER_EMAIL=?, "
			+ "USER_JOINDATE=?,"
			+ "USER_STATUS=?, "
			+ "USER_IMG=?, "
			+ "USER_IMGFMT=? "
			+ "WHERE USER_ID=?";
	
	@Override
	public void insert(UserVO userVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);		
			pstmt.setInt(1, userVO.getUser_id());
			pstmt.setString(2,userVO.getUser_account());
			pstmt.setString(3,userVO.getUser_passwd());
			pstmt.setInt(4,userVO.getUser_type());
			pstmt.setString(5,userVO.getUser_lastname());
			pstmt.setString(6,userVO.getUser_firstname());
			pstmt.setString(7,userVO.getUser_phone());
			pstmt.setString(8,userVO.getUser_mobile());
			pstmt.setString(9,userVO.getUser_address());
			pstmt.setString(10,userVO.getUser_email());
			pstmt.setDate(11,userVO.getUser_joindate());
			pstmt.setInt(12,userVO.getUser_status());
			pstmt.setBytes(13,userVO.getUser_img());
			pstmt.setString(14,userVO.getUser_imgfmt());
		} catch (SQLException | ClassNotFoundException se) {
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
//			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);		
			pstmt.setString(2,userVO.getUser_account());
			pstmt.setString(3,userVO.getUser_passwd());
			pstmt.setInt(4,userVO.getUser_type());
			pstmt.setString(5,userVO.getUser_lastname());
			pstmt.setString(6,userVO.getUser_firstname());
			pstmt.setString(7,userVO.getUser_phone());
			pstmt.setString(8,userVO.getUser_mobile());
			pstmt.setString(9,userVO.getUser_address());
			pstmt.setString(10,userVO.getUser_email());
			pstmt.setDate(11,userVO.getUser_joindate());
			pstmt.setInt(12,userVO.getUser_status());
			pstmt.setBytes(13,userVO.getUser_img());
			pstmt.setString(14,userVO.getUser_imgfmt());
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, user_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				userVO = new UserVO();
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
		} catch (SQLException | ClassNotFoundException se) {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
//			con = ds.getConnection();
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
		} catch (SQLException | ClassNotFoundException se) {
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
	public UserVO findByAccount(String user_account) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insertStore(UserVO userVO, StoreVO storeVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UserVO> getAllByType(int user_type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserVO> getAllStore() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
