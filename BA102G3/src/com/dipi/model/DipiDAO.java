package com.dipi.model;

import java.io.BufferedInputStream;
import java.io.IOException;
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
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;

import com.dish.model.DishJDBCDAO;
import com.dish.model.DishVO;

public class DipiDAO implements DipiDAO_interface{
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
			"insert into dipi(dipi_id,dish_id,dipi_name,dipi_img,dipi_imgfmt) "
			+ "values(dipi_sq.nextval,?,?,?,?)";
	
	private static final String DELETE = 
			"DELETE FROM dipi where dipi_id = ?";
	
	private static final String UPDATE = 
			"UPDATE dipi set dish_id=?, dipi_name=?, dipi_img=?, dipi_imgfmt=? where dipi_id = ?";
	
	private static final String GET_ALL_STMT = 
			"select * from dipi order by dipi_id";
	
	private static final String GET_ONE_STMT = 
			"select * from dipi where dipi_id = ?";
	private static final String GET_ONE_DISH_STMT = 
			"select * from dipi where dish_id = ?";
	
	
	

	@Override
	public void insert(DipiVO dipiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, dipiVO.getDish_id());
			pstmt.setString(2, dipiVO.getDipi_name());
			//pstmt.setBytes(3, dipiVO.toPrimitives(dipiVO.getDipi_img()));
			pstmt.setBytes(3, dipiVO.getDipi_img());
			pstmt.setString(4, dipiVO.getDipi_imgfmt());
			
			
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
	public void update(DipiVO dipiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, dipiVO.getDish_id());
			pstmt.setString(2, dipiVO.getDipi_name());
			//pstmt.setBytes(3, dipiVO.toPrimitives(dipiVO.getDipi_img()));
			pstmt.setBytes(3, dipiVO.getDipi_img());
			pstmt.setString(4, dipiVO.getDipi_imgfmt());
			pstmt.setInt(5, dipiVO.getDipi_id());
			
			
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
	public void delete(Integer dipi_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, dipi_id);
			
			
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
	public DipiVO findByPrimaryKey(Integer dipi_id) {
		// TODO Auto-generated method stub
		DipiVO dipiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, dipi_id);
			

			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				dipiVO = new DipiVO();
				dipiVO.setDipi_id(rs.getInt("dipi_id"));
				dipiVO.setDish_id(rs.getInt("Dish_id"));
				dipiVO.setDipi_name(rs.getString("dipi_name"));
				dipiVO.setDipi_imgfmt(rs.getString("dipi_imgfmt"));
				//dipiVO.setDipi_img(dipiVO.toObjects(rs.getBytes("dipi_img")));
				dipiVO.setDipi_img(rs.getBytes("dipi_img"));
				
				
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
		return dipiVO;
	}

	@Override
	public List<DipiVO> getAll() {
		// TODO Auto-generated method stub
		List<DipiVO> list = new ArrayList<DipiVO>();
		DipiVO dipiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
				dipiVO = new DipiVO();
				dipiVO.setDipi_id(rs.getInt("dipi_id"));
				dipiVO.setDish_id(rs.getInt("Dish_id"));
				dipiVO.setDipi_name(rs.getString("dipi_name"));
				dipiVO.setDipi_imgfmt(rs.getString("dipi_imgfmt"));
				//dipiVO.setDipi_img(dipiVO.toObjects(rs.getBytes("dipi_img")));
				dipiVO.setDipi_img(rs.getBytes("dipi_img"));
				list.add(dipiVO); // Store the row in the list
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
	
	public byte[] download(Integer dipi_id){
		List list = new ArrayList();
		DipiVO dipiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] buf=null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, dipi_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("dipi_img"));
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
}
