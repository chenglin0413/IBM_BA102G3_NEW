package com.sysu.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.auth.model.AuthVO;
import com.sysu.model.SysuVO;

public class SysuDAO implements SysuDAO_Interface {

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
			"INSERT INTO SYSU("
			+ "SYSU_ID, "
			+ "SYSU_ACCOUNT, "
			+ "SYSU_PASSWD, "
			+ "SYSU_TYPE, "
			+ "SYSU_LASTNAME, "
			+ "SYSU_FIRSTNAME, "
			+ "SYSU_EMAIL, "
			+ "SYSU_JOINDATE, "
			+ "SYSU_STATUS)"
			+ " VALUES (SYSU_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";

	private static final String INSERT_AUTH_STMT=
			"INSERT INTO AUTH("
			+ "SYSU_ID, "
			+ "FUNC_ID)"
			+ " VALUES (?,?)";
		
	private static final String GET_ALL_STMT =
			"SELECT SYSU_ID,"
			+ "SYSU_ACCOUNT,"
			+ "SYSU_PASSWD,"
			+ "SYSU_TYPE,"
			+ "SYSU_LASTNAME,"
			+ "SYSU_FIRSTNAME,"
			+ "SYSU_EMAIL,"
			+ "SYSU_JOINDATE,"
			+ "SYSU_STATUS "
			+ "FROM SYSU order by SYSU_ID desc";
	private static final String GET_ONE_STMT =
			"SELECT SYSU_ID,"
			+ "SYSU_ACCOUNT,"
			+ "SYSU_PASSWD,"
			+ "SYSU_TYPE,"
			+ "SYSU_LASTNAME,"
			+ "SYSU_FIRSTNAME,"
			+ "SYSU_EMAIL,"
			+ "SYSU_JOINDATE,"
			+ "SYSU_STATUS "
			+ "FROM SYSU WHERE SYSU_ID=?";

	private static final String GET_AUTH_BY_ONESYSID_STMT =
			"SELECT FUNC_ID FROM AUTH WHERE SYSU_ID=?";
	
	private static final String GET_ONE_ACCOUNT_STMT =
			"SELECT SYSU_ID,"
			+ "SYSU_ACCOUNT,"
			+ "SYSU_PASSWD,"
			+ "SYSU_TYPE,"
			+ "SYSU_LASTNAME,"
			+ "SYSU_FIRSTNAME,"
			+ "SYSU_EMAIL,"
			+ "SYSU_JOINDATE,"
			+ "SYSU_STATUS "
			+ "FROM SYSU WHERE SYSU_ACCOUNT=?";
	private static final String DELETE=
			"DELETE FROM SYSU WHERE SYSU_ID = ?";
	private static final String DELETE_AUTH=
			"DELETE FROM AUTH WHERE SYSU_ID = ?";
	
	private static final String UPDATE=
			"UPDATE SYSU set "
			+ "SYSU_ACCOUNT=?, "
			+ "SYSU_PASSWD=?, "
			+ "SYSU_TYPE=?, "
			+ "SYSU_LASTNAME=?, "
			+ "SYSU_FIRSTNAME=?, "
			+ "SYSU_EMAIL=?, "
			+ "SYSU_JOINDATE=?, "
			+ "SYSU_STATUS=? "
			+ "WHERE SYSU_ID=?";
	
	@Override
	public void insert(SysuVO sysuVO, String[] funcs) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String next_user_id=null;
		
		try {
			con=ds.getConnection();
			
			con.setAutoCommit(false);
			
			String cols[]={"SYSU_ID"};
			pstmt=con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1,sysuVO.getSysu_account());
			pstmt.setString(2,sysuVO.getSysu_passwd());
			pstmt.setInt(3,sysuVO.getSysu_type());
			pstmt.setString(4,sysuVO.getSysu_lastname());
			pstmt.setString(5,sysuVO.getSysu_firstname());
			pstmt.setString(6,sysuVO.getSysu_email());
			pstmt.setDate(7,sysuVO.getSysu_joindate());
			pstmt.setInt(8,sysuVO.getSysu_status());
						
			pstmt.executeUpdate();
			
			ResultSet rs=pstmt.getGeneratedKeys();
			
			if (rs.next()){
				next_user_id=rs.getString(1);
			} else {
				System.out.println("fails to add user! ");
			}
			
			pstmt=con.prepareStatement(INSERT_AUTH_STMT);
			
			for(int i=0 ; i<funcs.length ; i++){
				pstmt.setInt(1, new Integer(next_user_id));
				pstmt.setInt(2, new Integer(funcs[i]) );			
				pstmt.executeUpdate();				
			}
			
			con.commit();

			con.setAutoCommit(true);
			
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
	public void update(SysuVO sysuVO) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(UPDATE);		
			pstmt.setString(1,sysuVO.getSysu_account());
			pstmt.setString(2,sysuVO.getSysu_passwd());
			pstmt.setInt(3,sysuVO.getSysu_type());
			pstmt.setString(4,sysuVO.getSysu_lastname());
			pstmt.setString(5,sysuVO.getSysu_firstname());
			pstmt.setString(6,sysuVO.getSysu_email());
			pstmt.setDate(7,sysuVO.getSysu_joindate());
			pstmt.setInt(8,sysuVO.getSysu_status());
			pstmt.setInt(9,sysuVO.getSysu_id());
			
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
	public void delete(Integer sysu_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE_AUTH);
			pstmt.setInt(1, sysu_id);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, sysu_id);
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
	public SysuVO findByPrimaryKey(Integer sysu_id) {
		SysuVO sysuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, sysu_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				sysuVO = new SysuVO();
				sysuVO.setSysu_id(rs.getInt("sysu_id"));
				sysuVO.setSysu_account(rs.getString("sysu_account"));
				sysuVO.setSysu_passwd(rs.getString("sysu_passwd"));
				sysuVO.setSysu_type(rs.getInt("sysu_type"));
				sysuVO.setSysu_lastname(rs.getString("sysu_lastname"));
				sysuVO.setSysu_firstname(rs.getString("sysu_firstname"));
				sysuVO.setSysu_email(rs.getString("sysu_email"));
				sysuVO.setSysu_joindate(rs.getDate("sysu_joindate"));
				sysuVO.setSysu_status(rs.getInt("sysu_status"));
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
		return sysuVO;

	}

	@Override
	public SysuVO findByAccount(String sysu_account) {
		SysuVO sysuVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT_STMT);

			pstmt.setString(1, sysu_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				sysuVO = new SysuVO();
				sysuVO.setSysu_id(rs.getInt("sysu_id"));
				sysuVO.setSysu_account(rs.getString("sysu_account"));
				sysuVO.setSysu_passwd(rs.getString("sysu_passwd"));
				sysuVO.setSysu_type(rs.getInt("sysu_type"));
				sysuVO.setSysu_lastname(rs.getString("sysu_lastname"));
				sysuVO.setSysu_firstname(rs.getString("sysu_firstname"));
				sysuVO.setSysu_email(rs.getString("sysu_email"));
				sysuVO.setSysu_joindate(rs.getDate("sysu_joindate"));
				sysuVO.setSysu_status(rs.getInt("sysu_status"));
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
		return sysuVO;

	}	
	
	@Override
	public List<SysuVO> getAll() {

		List<SysuVO> list = new ArrayList<SysuVO>();
		SysuVO sysuVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				sysuVO = new SysuVO();
				sysuVO.setSysu_id(rs.getInt("sysu_id"));
				sysuVO.setSysu_account(rs.getString("sysu_account"));
				sysuVO.setSysu_passwd(rs.getString("sysu_passwd"));
				sysuVO.setSysu_type(rs.getInt("sysu_type"));
				sysuVO.setSysu_lastname(rs.getString("sysu_lastname"));
				sysuVO.setSysu_firstname(rs.getString("sysu_firstname"));
				sysuVO.setSysu_email(rs.getString("sysu_email"));
				sysuVO.setSysu_joindate(rs.getDate("sysu_joindate"));
				sysuVO.setSysu_status(rs.getInt("sysu_status"));
				list.add(sysuVO); // Store the row in the list
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
	public List<AuthVO> getAuthBySysuid(Integer sysu_id) {
		// TODO Auto-generated method stub
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTH_BY_ONESYSID_STMT);
			pstmt.setInt(1,sysu_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				authVO = new AuthVO();
				authVO.setFunc_id(rs.getInt("func_id"));
				list.add(authVO); // Store the row in the list
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
	
}
