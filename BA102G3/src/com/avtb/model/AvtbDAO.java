package com.avtb.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AvtbDAO implements AvtbDAO_Interface {

	private static final String INSERT_STMT = "INSERT INTO AVTB "
			+ "(AVTB_ID,"
			+ "REST_ID,"
			+ "AVTB_DATE_S,"
			+ "AVTB_DATE_E,"
			+ "AVTB_RESERVATION,"
			+ "AVTB_MAX_RESERVATION) "
			+ "VALUES (avtb_sq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE AVTB set AVTB_DATE_S=?, WHERE AVTB_ID = ?";
	private static final String DELETE = "DELETE FROM AVTB WHERE AVTB_ID";
	private static final String GET_ONE_STMT ="SELECT * from AVTB where AVTB_ID = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM AVTB";
	
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
	public void insert(AvtbVO avtbVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, avtbVO.getAvtb_id());
			pstmt.setInt(2, avtbVO.getAvtb_id());
			pstmt.setDate(3, (Date) avtbVO.getAvtb_date_s());
			pstmt.setDate(4, (Date) avtbVO.getAvtb_date_e());
			pstmt.setInt(5, avtbVO.getAvtb_reservation());
			pstmt.setInt(6, avtbVO.getAvtb_max_reservation());
	
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
	public void update(AvtbVO avtbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, avtbVO.getAvtb_max_reservation());
		
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
	public void delete(Integer avtb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, avtb_id);

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
	public AvtbVO findByPrimaryKey(Integer avtb_id) {
		AvtbVO avtbVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("pass AvtbDAO.java line 167");
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, avtb_id);
			System.out.println(avtb_id);
			
			System.out.println("pass AvtbDAO.java line 176");

			rs = pstmt.executeQuery();
			
			System.out.println("pass AvtbDAO.java line 180");

			while (rs.next()) {
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setRest_id(rs.getInt("rest_id"));
				avtbVO.setAvtb_date_s(rs.getDate("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getDate("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
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
		return avtbVO;
	}

	@Override
	public List<AvtbVO> getAll() {
		List<AvtbVO> list = new ArrayList<AvtbVO>();
		AvtbVO avtbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				avtbVO = new AvtbVO();
				avtbVO.setAvtb_id(rs.getInt("avtb_id"));
				avtbVO.setRest_id(rs.getInt("rest_id"));
				avtbVO.setAvtb_date_s(rs.getDate("avtb_date_s"));
				avtbVO.setAvtb_date_e(rs.getDate("avtb_date_e"));
				avtbVO.setAvtb_reservation(rs.getInt("avtb_reservation"));
				avtbVO.setAvtb_max_reservation(rs.getInt("avtb_max_reservation"));
				list.add(avtbVO); // Store the row in the list
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
