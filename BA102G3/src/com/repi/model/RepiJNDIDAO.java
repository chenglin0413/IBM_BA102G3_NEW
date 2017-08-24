package com.repi.model;

import java.io.BufferedInputStream;
import java.io.IOException;
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

public class RepiJNDIDAO implements RepiDAO_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"insert into repi(repi_id,rest_id,repi_name,repi_img,repi_imgfmt) "
			+ "values(repi_sq.nextval,?,?,?,?)";
	
	private static final String DELETE = 
			"DELETE FROM repi where repi_id = ?";
	
	private static final String UPDATE = 
			"UPDATE repi set rest_id=?, repi_name=?, repi_img=?, repi_imgfmt=? where repi_id = ?";
	
	private static final String GET_ALL_STMT = 
			"select * from repi order by repi_id";
	
	private static final String GET_ONE_STMT = 
			"select * from repi where repi_id = ?";
	private static final String GET_ONE_REST_STMT = 
			"select * from repi where rest_id = ?";
	
	
	

	@Override
	public void insert(RepiVO repiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, repiVO.getRest_id());
			pstmt.setString(2, repiVO.getRepi_name());
			//pstmt.setBytes(3, RepiVO.toPrimitives(repiVO.getRepi_img()));
			pstmt.setBytes(3, repiVO.getRepi_img());
			pstmt.setString(4, repiVO.getRepi_imgfmt());
			
			
			pstmt.executeUpdate();
			
		} 
			// Handle any SQL errors
		 catch (SQLException se) {
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
	public void update(RepiVO repiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, repiVO.getRest_id());
			pstmt.setString(2, repiVO.getRepi_name());
			//pstmt.setBytes(3, repiVO.toPrimitives(repiVO.getrepi_img()));
			pstmt.setBytes(3, repiVO.getRepi_img());
			pstmt.setString(4, repiVO.getRepi_imgfmt());
			pstmt.setInt(5, repiVO.getRepi_id());
			
			
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
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
	public void delete(Integer repi_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, repi_id);
			
			
			pstmt.executeUpdate();
			
		}  catch (SQLException se) {
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
	public RepiVO findByPrimaryKey(Integer repi_id) {
		// TODO Auto-generated method stub
		RepiVO repiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, repi_id);
			

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
			
				repiVO = new RepiVO();
				repiVO.setRepi_id(rs.getInt("repi_id"));
				repiVO.setRest_id(rs.getInt("rest_id"));
				repiVO.setRepi_name(rs.getString("repi_name"));
				repiVO.setRepi_imgfmt(rs.getString("repi_imgfmt"));
				//repiVO.setrepi_img(repiVO.toObjects(rs.getBytes("repi_img")));
				repiVO.setRepi_img(rs.getBytes("repi_img"));
				
				
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return repiVO;
	}

	@Override
	public List<RepiVO> getAll() {
		// TODO Auto-generated method stub
		List<RepiVO> list = new ArrayList<RepiVO>();
		RepiVO repiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				repiVO = new RepiVO();
				repiVO.setRepi_id(rs.getInt("repi_id"));
				repiVO.setRest_id(rs.getInt("rest_id"));
				repiVO.setRepi_name(rs.getString("repi_name"));
				repiVO.setRepi_imgfmt(rs.getString("repi_imgfmt"));
				//repiVO.setrepi_img(repiVO.toObjects(rs.getBytes("repi_img")));
				repiVO.setRepi_img(rs.getBytes("repi_img"));
				list.add(repiVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
	
	public byte[] download(Integer repi_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] buf=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, repi_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("repi_img"));
				buf = new byte[in.available()]; // 4K buffer
				in.read(buf);
				
				in.close();
			}
		
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (IOException e) {
			// TODO: handle exception
		} 
		finally {
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
		return buf;
	}

	@Override
	public RepiVO findByRestId(Integer rest_id) {
		// TODO Auto-generated method stub
		return null;
	}
}

