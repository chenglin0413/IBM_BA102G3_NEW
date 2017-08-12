package com.rppr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RpprJDBCDAO implements RpprDAO_interface{
		
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO Rppr (rppr_id,prod_id,user_id,rppr_date,rppr_tittle,rppr_content)"
			+ "VALUES (RPPR_ID_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Rppr set prod_id=? ,user_id=?, rppr_date=?, rppr_status=?, rppr_tittle=?,RPPR_content=? where rppr_id = ?";
	private static final String DELETE = "DELETE FROM Rppr where rppr_id=?";
	private static final String GET_ONE_STMT = "SELECT * FROM Rppr where rppr_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Rppr order by rppr_date desc";
	
	private static final String UPDATE_STATUS = "UPDATE Rppr set rppr_status= ? where rppr_id = ?";
	
	@Override
	public void insert(RpprVO rpprVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,rpprVO.getProd_id());
			pstmt.setInt(2,rpprVO.getUser_id());
			pstmt.setTimestamp(3,rpprVO.getRppr_date());
			pstmt.setString(4,rpprVO.getRppr_tittle());
			pstmt.setString(5,rpprVO.getRppr_content());
			
			
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(RpprVO rpprVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,rpprVO.getProd_id());
			pstmt.setInt(2,rpprVO.getUser_id());
			pstmt.setTimestamp(3,rpprVO.getRppr_date());
			pstmt.setInt(4,rpprVO.getRppr_status());
			pstmt.setString(5,rpprVO.getRppr_tittle());
			pstmt.setString(6,rpprVO.getRppr_content());
			pstmt.setInt(7,rpprVO.getRppr_id());
			pstmt.executeUpdate();
			
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer rppr_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1,rppr_id);
			
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public RpprVO findByPrimaryKey(Integer rppr_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		RpprVO rpprVO = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,rppr_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rpprVO = new RpprVO();
				rpprVO.setRppr_id(rs.getInt("RPPR_ID"));
				rpprVO.setProd_id(rs.getInt("PROD_ID"));
				rpprVO.setUser_id(rs.getInt("USER_ID"));
				rpprVO.setRppr_date(rs.getTimestamp("RPPR_DATE"));
				rpprVO.setRppr_status(rs.getInt("RPPR_STATUS"));
				rpprVO.setRppr_tittle(rs.getString("RPPR_TITTLE"));
				rpprVO.setRppr_content(rs.getString("RPPR_content"));
			}			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return rpprVO;
	}

	@Override
	public List<RpprVO> getAll() {
		// TODO Auto-generated method stub
		List<RpprVO> list = new ArrayList<RpprVO>();
		RpprVO rpprVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				rpprVO = new RpprVO();
				rpprVO.setRppr_id(rs.getInt("RPPR_ID"));
				rpprVO.setProd_id(rs.getInt("PROD_ID"));
				rpprVO.setUser_id(rs.getInt("USER_ID"));
				rpprVO.setRppr_date(rs.getTimestamp("RPPR_DATE"));
				rpprVO.setRppr_status(rs.getInt("RPPR_STATUS"));
				rpprVO.setRppr_tittle(rs.getString("RPPR_TITTLE"));
				rpprVO.setRppr_content(rs.getString("RPPR_content"));
				
				list.add(rpprVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void updateStatus(Integer rppr_id,Integer rppr_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, rppr_id);
			pstmt.setInt(2, rppr_status);
			pstmt.executeUpdate();

			con.commit();
			// Handle any driver errors
		} catch (SQLException | ClassNotFoundException se) {
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
	
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		RpprJDBCDAO dao = new RpprJDBCDAO();
		//�s�W
		RpprVO rpprVO1 = new RpprVO();
		rpprVO1.setProd_id(2200001); 
		rpprVO1.setUser_id(1000001); 
		rpprVO1.setRppr_date(timestamp);
		rpprVO1.setRppr_status(1);
		rpprVO1.setRppr_tittle("好好好好");
		rpprVO1.setRppr_content("好好好好好好好好好好好好好好好好好好好好");
		
		dao.insert(rpprVO1);
		System.out.println("--------------------------------");
		
		//修改
		
		
		RpprVO rpprVO2 = new RpprVO();	
		rpprVO2.setProd_id(2200004); 
		rpprVO2.setUser_id(1000001); 
		rpprVO2.setRppr_date(timestamp);
		rpprVO2.setRppr_status(1);
		rpprVO2.setRppr_tittle("好好好好");
		rpprVO2.setRppr_content("XXX,XX");
		rpprVO2.setRppr_id(6100001);
		dao.update(rpprVO2);
		System.out.println("111111");
		//刪除
		
		dao.delete(6100007);
		System.out.println("XXXXXXX");
		
		//查詢
		RpprVO rpprVO3 = dao.findByPrimaryKey(6100006);
		System.out.print(rpprVO3.getRppr_id() + ",");
		System.out.print(rpprVO3.getUser_id()+ ",");
		System.out.print(rpprVO3.getProd_id()+ ",");
		System.out.print(rpprVO3.getRppr_status() + ",");
		System.out.print(rpprVO3.getRppr_date() + ",");
		System.out.print(rpprVO3.getRppr_tittle() + ",");
		System.out.print(rpprVO3.getRppr_content() + ",");
		System.out.println("---------------------------------");
		
		//查詢
		
		List<RpprVO> list = dao.getAll();
		for (RpprVO aRppr : list) {
			System.out.print(aRppr.getRppr_id()+ ",");
			System.out.print(aRppr.getUser_id() + ",");
			System.out.print(aRppr.getProd_id()+ ",");
			System.out.print(aRppr.getRppr_date() + ",");
			System.out.print(aRppr.getRppr_status()+ ",");
			System.out.print(aRppr.getRppr_tittle() + ",");
			System.out.print(aRppr.getRppr_content() + ",");
			System.out.println("...............................");
		}
	}
}
