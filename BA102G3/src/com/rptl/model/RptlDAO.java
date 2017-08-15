package com.rptl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RptlDAO implements RptlDAO_interface  {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT = "INSERT INTO Rptl (rptl_id,trvl_id,user_id,rptl_date,rptl_tittle,rptl_content)"
			+ "VALUES (Rptl_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Rptl set trvl_id=? ,user_id=?, rptl_date=?, rptl_status=?, rptl_tittle=?,rptl_content=? where rptl_id = ?";
	private static final String DELETE = "DELETE FROM Rptl where rptl_id=?";
	private static final String GET_ONE_STMT = "SELECT * FROM Rptl where rptl_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Rptl order by rptl_status ";
	
	private static final String UPDATE_STATUS = "UPDATE Rptl set rptl_status= ? where rptl_id = ?";
	
	@Override
	public void insert(RptlVO rptlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,rptlVO.getTrvl_id());
			pstmt.setInt(2,rptlVO.getUser_id());
			pstmt.setTimestamp(3,rptlVO.getRptl_date());
			pstmt.setString(4,rptlVO.getRptl_tittle());
			pstmt.setString(5,rptlVO.getRptl_content());
			
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

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
	
	
	@Override
	public void update(RptlVO rptlVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,rptlVO.getTrvl_id());
			pstmt.setInt(2,rptlVO.getUser_id());
			pstmt.setTimestamp(3,rptlVO.getRptl_date());
			pstmt.setInt(4,rptlVO.getRptl_status());
			pstmt.setString(5,rptlVO.getRptl_tittle());
			pstmt.setString(6,rptlVO.getRptl_content());
			pstmt.setInt(7,rptlVO.getRptl_id());

			pstmt.executeUpdate();
			
			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

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
	

	@Override
	public void delete(Integer rptl_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1,rptl_id);
			
			pstmt.executeUpdate();

			
			con.commit();
			// Handle any driver errors
		}  catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

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
	
	@Override
	public RptlVO findByPrimaryKey(Integer rptl_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		RptlVO rptlVO = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,rptl_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rptlVO = new RptlVO();
				rptlVO.setRptl_id(rs.getInt("RPTL_ID"));
				rptlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				rptlVO.setUser_id(rs.getInt("USER_ID"));
				rptlVO.setRptl_date(rs.getTimestamp("RPTL_DATE"));
				rptlVO.setRptl_status(rs.getInt("RPTL_STATUS"));
				rptlVO.setRptl_tittle(rs.getString("RPTL_TITTLE"));
				rptlVO.setRptl_content(rs.getString("RPTL_CONTENT"));
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
		return rptlVO;
	}
	
	@Override
	public List<RptlVO> getAll() {
		// TODO Auto-generated method stub
		List<RptlVO> list = new ArrayList<RptlVO>();
		RptlVO rptlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				rptlVO = new RptlVO();
				rptlVO.setRptl_id(rs.getInt("RPTL_ID"));
				rptlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				rptlVO.setUser_id(rs.getInt("USER_ID"));
				rptlVO.setRptl_date(rs.getTimestamp("RPTL_DATE"));
				rptlVO.setRptl_status(rs.getInt("RPTL_STATUS"));
				rptlVO.setRptl_tittle(rs.getString("RPTL_TITTLE"));
				rptlVO.setRptl_content(rs.getString("RPTL_CONTENT"));
				
				list.add(rptlVO); // Store the row in the list
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
	public void updateStatus(Integer rptl_id,Integer rptl_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, rptl_status);
			pstmt.setInt(2, rptl_id);
			pstmt.executeUpdate();

			con.commit();
			// Handle any driver errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e) {

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
	
}
