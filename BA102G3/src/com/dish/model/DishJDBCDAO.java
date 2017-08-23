package com.dish.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.PasswdUserDatabase;


import java.sql.*;

public class DishJDBCDAO implements DishDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"insert into dish(dish_id,rest_id,dish_name,dish_price,dish_status,dish_detail,dish_note) "
			+ "values(dish_sq.nextval,?,?,?,?,?,?)";
	
	private static final String DELETE = 
			"DELETE FROM dish where dish_id = ?";
	
	private static final String UPDATE = 
			"UPDATE dish set rest_id=?, dish_name=?, dish_price=?, dish_status=?, dish_detail=?, dish_note=? where dish_id = ?";
	
	private static final String GET_ALL_STMT = 
			"select * from dish order by dish_id";
	
	private static final String GET_ONE_STMT = 
			"select * from dish where dish_id = ?";
	
	private static final String GET_BY_RESTID = "select * from dish where rest_id = ?";
	
	@Override
	public String insert(DishVO dishVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		String dish_id = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			String cols[] = { "dish_id" };
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setInt(1, dishVO.getRest_id());
			pstmt.setString(2, dishVO.getDish_name());
			pstmt.setInt(3, dishVO.getDish_price());
			pstmt.setInt(4, dishVO.getDish_status());
			pstmt.setString(5, dishVO.getDish_detail());
			pstmt.setString(6, dishVO.getDish_note());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						dish_id = rs.getString(i);
						System.out.println("(" + i + ") = " + dish_id + "(新增的自增主鍵值號碼)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return dish_id;
	}

	@Override
	public void update(DishVO dishVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, dishVO.getRest_id());
			pstmt.setString(2, dishVO.getDish_name());
			pstmt.setInt(3, dishVO.getDish_price());
			pstmt.setInt(4, dishVO.getDish_status());
			pstmt.setString(5, dishVO.getDish_detail());
			pstmt.setString(6, dishVO.getDish_note());
			pstmt.setInt(7, dishVO.getDish_id());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer dish_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, dish_id);
			
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public DishVO findByPrimaryKey(Integer dish_id) {
		// TODO Auto-generated method stub
		DishVO dishVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, dish_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDish_id(rs.getInt("dish_id"));
				dishVO.setRest_id(rs.getInt("rest_id"));
				dishVO.setDish_name(rs.getString("dish_name"));
				dishVO.setDish_price(rs.getInt("dish_price"));
				dishVO.setDish_status(rs.getInt("dish_status"));
				dishVO.setDish_detail(rs.getString("dish_detail"));
				dishVO.setDish_note(rs.getString("dish_note"));
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
		return dishVO;
	}

	@Override
	public List<DishVO> getAll() {
		// TODO Auto-generated method stub
		List<DishVO> list = new ArrayList<DishVO>();
		DishVO dishVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDish_id(rs.getInt("dish_id"));
				dishVO.setRest_id(rs.getInt("rest_id"));
				dishVO.setDish_name(rs.getString("dish_name"));
				dishVO.setDish_price(rs.getInt("dish_price"));
				dishVO.setDish_status(rs.getInt("dish_status"));
				dishVO.setDish_detail(rs.getString("dish_detail"));
				dishVO.setDish_note(rs.getString("dish_note"));
				list.add(dishVO); // Store the row in the list
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
	public List<DishVO> findByFk(Integer rest_id) {
		// TODO Auto-generated method stub
		List<DishVO> list = new ArrayList<DishVO>();
		DishVO dishVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_RESTID);
			
			pstmt.setInt(1, rest_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				dishVO = new DishVO();
				dishVO.setDish_id(rs.getInt("dish_id"));
				dishVO.setRest_id(rs.getInt("rest_id"));
				dishVO.setDish_name(rs.getString("dish_name"));
				dishVO.setDish_price(rs.getInt("dish_price"));
				dishVO.setDish_status(rs.getInt("dish_status"));
				dishVO.setDish_detail(rs.getString("dish_detail"));
				dishVO.setDish_note(rs.getString("dish_note"));
				list.add(dishVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main(String[] args) {

		DishJDBCDAO dao = new DishJDBCDAO();

		// 新增
//		DishVO dishVO1 = new DishVO();
//		dishVO1.setRest_id(3000001);
//		dishVO1.setDish_name("One piece");
//		dishVO1.setDish_price(9487);
//		dishVO1.setDish_status(1);
//		dishVO1.setDish_detail("傳說中的大秘寶");
//		dishVO1.setDish_note("5");
//		dao.insert(dishVO1);
//
//		// 修改
//		DishVO dishVO2 = new DishVO();
//		dishVO2.setRest_id(3000001);
//		dishVO2.setDish_name("美味蟹寶");
//		dishVO2.setDish_price(666);
//		dishVO2.setDish_status(1);
//		dishVO2.setDish_detail("海綿寶寶");
//		dishVO2.setDish_note("5");
//		dishVO2.setDish_id(3200070);
//		dao.update(dishVO2);
//
//		// 刪除
//		dao.delete(7014);
//
//		// 查詢
//		DishVO dishVO3 = dao.findByPrimaryKey(3200001);
//		System.out.print(dishVO3.getDish_id() + ",");
//		System.out.print(dishVO3.getRest_id() + ",");
//		System.out.print(dishVO3.getDish_name() + ",");
//		System.out.print(dishVO3.getDish_price() + ",");
//		System.out.print(dishVO3.getDish_status() + ",");
//		System.out.print(dishVO3.getDish_detail() + ",");
//		System.out.println(dishVO3.getDish_note());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<DishVO> list = dao.getAll();
//		for (DishVO aDish : list) {
//			System.out.print(aDish.getDish_id() + ",");
//			System.out.print(aDish.getRest_id() + ",");
//			System.out.print(aDish.getDish_name() + ",");
//			System.out.print(aDish.getDish_price() + ",");
//			System.out.print(aDish.getDish_status() + ",");
//			System.out.print(aDish.getDish_detail() + ",");
//			System.out.println(aDish.getDish_note());
//			System.out.println();
//		}
		
		
		// 查詢
		List<DishVO> list2 = dao.findByFk(3000001);
		for (DishVO aDish : list2) {
			System.out.print(aDish.getDish_id() + ",");
			System.out.print(aDish.getRest_id() + ",");
			System.out.print(aDish.getDish_name() + ",");
			System.out.print(aDish.getDish_price() + ",");
			System.out.print(aDish.getDish_status() + ",");
			System.out.print(aDish.getDish_detail() + ",");
			System.out.println(aDish.getDish_note());
			System.out.println();
		}
	}
	
}
