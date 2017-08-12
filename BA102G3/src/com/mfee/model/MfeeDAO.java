package com.mfee.model;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MfeeDAO implements MfeeDAO_interface {

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
			"INSERT INTO MFEE("
			+ "MFEE_ID,"
			+ "USER_ID,"
			+ "MFEE_DATE,"
			+ "PAY_DATE)"
			+ " VALUES (MFEE_ID_SEQ.NEXTVAL,?,?,?)";
			
	private static final String GET_ALL_STMT =
			"SELECT MFEE_ID,"
			+ "USER_ID,"
			+ "MFEE_DATE,"
			+ "PAY_DATE "
			+ "FROM MFEE order by MFEE_ID desc";

	private static final String GET_ONE_STMT =
			"SELECT MFEE_ID,"
			+ "USER_ID,"
			+ "MFEE_DATE,"
			+ "PAY_DATE "
			+ "FROM MFEE WHERE MFEE_ID=?";
	
	private static final String GET_ONE_BY_USERID_STMT =
			"SELECT MFEE_ID,"
			+ "USER_ID,"
			+ "MFEE_DATE,"
			+ "PAY_DATE "
			+ "FROM MFEE WHERE USER_ID=?";


//	private static final String FIND_UNPAY_STMT =
//			"select u.user_id "
//			+ "from mfee m right outer join user_table u on m.user_id=u.user_id "
//			+ "where (u.user_joindate < '01-?-?' and u.user_type>=2 ) and (m.mfee_date is null);";
			
	private static final String DELETE=
			"DELETE FROM MFEE WHERE MFEE_ID = ?";

	private static final String UPDATE=
			"UPDATE MFEE set "
			+ "MFEE_ID=?,"
			+ "USED_ID=?,"
			+ "MFEE_DATE=?,"
			+ "PAY_DATE=?,"
			+ "WHERE MFEE_ID=?";	
		
	@Override
	public void insert(MfeeVO mfeeVO) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ds.getConnection();
			pstmt=con.prepareStatement(INSERT_STMT);		
			
			System.out.println("MfeeDAO line81");
			System.out.println(mfeeVO.getUser_id());
			System.out.println(mfeeVO.getMfee_date());
			System.out.println(mfeeVO.getPay_date());
						
			pstmt.setInt(1,mfeeVO.getUser_id());
			pstmt.setDate(2,mfeeVO.getMfee_date());
			pstmt.setDate(3,mfeeVO.getPay_date());
			
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
	public void update(MfeeVO mfeeVO) {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
						
			con=ds.getConnection();
			
				pstmt=con.prepareStatement(UPDATE);		
				pstmt.setInt(1,mfeeVO.getMfee_id());
				pstmt.setInt(2,mfeeVO.getUser_id());
				pstmt.setDate(3,mfeeVO.getMfee_date());
				pstmt.setDate(4,mfeeVO.getPay_date());
			
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
	public void delete(Integer mfee_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mfee_id);

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
	public MfeeVO findByPrimaryKey(Integer mfee_id) {
		MfeeVO mfeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mfee_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				mfeeVO = new MfeeVO();
				mfeeVO.setMfee_id(rs.getInt("mfee_id"));
				mfeeVO.setUser_id(rs.getInt("user_id"));
				mfeeVO.setMfee_date(rs.getDate("mfee_date"));
				mfeeVO.setPay_date(rs.getDate("pay_date"));
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
		return mfeeVO;

	}
	
	@Override
	public List<MfeeVO> getAll() {

		List<MfeeVO> list = new ArrayList<MfeeVO>();
		MfeeVO mfeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				mfeeVO = new MfeeVO();
				
				mfeeVO.setMfee_id(rs.getInt("mfee_id"));
				mfeeVO.setUser_id(rs.getInt("user_id"));
				mfeeVO.setMfee_date(rs.getDate("mfee_date"));
				mfeeVO.setPay_date(rs.getDate("pay_date"));
				list.add(mfeeVO); // Store the row in the list
				
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
	public List<MfeeVO> findUnpay(String year, String month) {
		
		List<MfeeVO> list = new ArrayList<MfeeVO>();
		MfeeVO mfeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String fIND_UNPAY_STMT =
				"select u.user_id "
			+ "from mfee m right outer join user_table u on m.user_id=u.user_id "
  		    + "where (u.user_joindate < '01-"+month+"-"+year+"' and u.user_type>=2 ) and (m.mfee_date is null) order by m.pay_date";
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(fIND_UNPAY_STMT);
						
//			pstmt.setString(1, month);
//			System.out.println("MfeeDAO line315 ");
//			pstmt.setString(2, year);
						
			rs = pstmt.executeQuery();
			
			System.out.println("MfeeDAO line322 ");

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				mfeeVO = new MfeeVO();
												
				mfeeVO.setUser_id(rs.getInt("user_id"));

				list.add(mfeeVO); // Store the row in the list
								
			}
			
			System.out.println("MfeeDAO line334");

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
	public List<MfeeVO> findByUserId(Integer user_id) {

		List<MfeeVO> list = new ArrayList<MfeeVO>();
		MfeeVO mfeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_USERID_STMT);
						
			pstmt.setInt(1, user_id);
			
			System.out.println(user_id);
						
			rs = pstmt.executeQuery();
			
			System.out.println("MfeeDAO line398");

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				mfeeVO = new MfeeVO();
												
				mfeeVO.setMfee_id(rs.getInt("mfee_id"));
				mfeeVO.setUser_id(rs.getInt("user_id"));
				mfeeVO.setMfee_date(rs.getDate("mfee_date"));
				mfeeVO.setPay_date(rs.getDate("pay_date"));
				
				list.add(mfeeVO); // Store the row in the list
								
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
