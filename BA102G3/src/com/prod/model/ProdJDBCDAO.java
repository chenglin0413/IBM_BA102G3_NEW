package com.prod.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.prpi.model.PrpiJDBCDAO;
import com.prpi.model.PrpiVO;

public class ProdJDBCDAO implements ProdDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO PROD (PROD_ID,STORE_ID,PROD_NAME,PROD_DESCRIPT,PROD_PRICE,PROD_SORT,PROD_FORMAT,PROD_BRAND,PROD_UPDATETIME,PROD_SOLDCOUNT,PROD_STATUS,PROD_COUNT,PROD_SCORE)"
			+ "VALUES(PROD_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PROD SET STORE_ID= ?,PROD_NAME=?,PROD_DESCRIPT=?,PROD_PRICE=?,PROD_SORT=?,PROD_FORMAT=?,PROD_BRAND=?,PROD_UPDATETIME=?,PROD_SOLDCOUNT=?,PROD_STATUS=?,PROD_COUNT=?,PROD_SCORE=? WHERE PROD_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM PROD WHERE PROD_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM PROD WHERE PROD_ID=?";
	private static final String GET_ALL = "SELECT * FROM PROD";

	@Override
	public void insert(ProdVO prodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setInt(1, prodVO.getStore_id());
			pstmt.setString(2, prodVO.getProd_name());
			pstmt.setString(3, prodVO.getProd_descript());
			pstmt.setInt(4, prodVO.getProd_price());
			pstmt.setString(5, prodVO.getProd_sort());
			pstmt.setString(6, prodVO.getProd_format());
			pstmt.setString(7, prodVO.getProd_brand());
			pstmt.setDate(8, prodVO.getProd_updatetime());
			pstmt.setInt(9, prodVO.getProd_soldcount());
			pstmt.setInt(10, prodVO.getProd_status());
			pstmt.setInt(11, prodVO.getProd_count());
			pstmt.setInt(12, prodVO.getProd_score());
			
			
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void update(ProdVO prodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, prodVO.getStore_id());
			pstmt.setString(2, prodVO.getProd_name());
			pstmt.setString(3, prodVO.getProd_descript());
			pstmt.setInt(4, prodVO.getProd_price());
			pstmt.setString(5, prodVO.getProd_sort());
			pstmt.setString(6, prodVO.getProd_format());
			pstmt.setString(7, prodVO.getProd_brand());
			pstmt.setDate(8, prodVO.getProd_updatetime());
			pstmt.setInt(9, prodVO.getProd_soldcount());
			pstmt.setInt(10, prodVO.getProd_status());
			pstmt.setInt(11, prodVO.getProd_count());
			pstmt.setInt(12, prodVO.getProd_score());
			pstmt.setInt(13, prodVO.getProd_id());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void delete(Integer prod_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, prod_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public ProdVO findByPrimaryKey(Integer prod_id) {
		// TODO Auto-generated method stub
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, prod_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProd_id(rs.getInt("prod_id"));
				prodVO.setStore_id(rs.getInt("store_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_descript(rs.getString("prod_descript"));
				prodVO.setProd_price(rs.getInt("prod_price"));
				prodVO.setProd_sort(rs.getString("prod_sort"));
				prodVO.setProd_format(rs.getString("prod_format"));
				prodVO.setProd_brand(rs.getString("prod_brand"));
				prodVO.setProd_updatetime(rs.getDate("prod_updatetime"));
				prodVO.setProd_soldcount(rs.getInt("prod_soldcount"));
				prodVO.setProd_status(rs.getInt("prod_status"));
				prodVO.setProd_count(rs.getInt("prod_count"));
				prodVO.setProd_score(rs.getInt("prod_score"));
				

			}

		} catch (SQLException | ClassNotFoundException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return prodVO;
	}

	@Override
	public List<ProdVO> getAll() {
		// TODO Auto-generated method stub
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs=pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProd_id(rs.getInt("prod_id"));
				prodVO.setStore_id(rs.getInt("store_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_descript(rs.getString("prod_descript"));
				prodVO.setProd_price(rs.getInt("prod_price"));
				prodVO.setProd_sort(rs.getString("prod_sort"));
				prodVO.setProd_format(rs.getString("prod_format"));
				prodVO.setProd_brand(rs.getString("prod_brand"));
				prodVO.setProd_updatetime(rs.getDate("prod_updatetime"));
				prodVO.setProd_soldcount(rs.getInt("prod_soldcount"));
				prodVO.setProd_status(rs.getInt("prod_status"));
				prodVO.setProd_count(rs.getInt("prod_count"));
				prodVO.setProd_score(rs.getInt("prod_score"));
				
				list.add(prodVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insertWithPrpi(ProdVO prodVO, List<PrpiVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			String cols[] = { "PROD_ID" };

			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, prodVO.getStore_id());
			pstmt.setString(2, prodVO.getProd_name());
			pstmt.setString(3, prodVO.getProd_descript());
			pstmt.setInt(4, prodVO.getProd_price());
			pstmt.setString(5, prodVO.getProd_sort());
			pstmt.setString(6, prodVO.getProd_format());
			pstmt.setString(7, prodVO.getProd_brand());
			pstmt.setDate(8, prodVO.getProd_updatetime());
			pstmt.setInt(9, prodVO.getProd_soldcount());
			pstmt.setInt(10, prodVO.getProd_status());
			pstmt.setInt(11, prodVO.getProd_count());
			pstmt.setInt(12, prodVO.getProd_score());

			pstmt.executeUpdate();

			String next_prod_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_prod_id = rs.getString(1);
				System.out.println("自增主鍵值= " + next_prod_id + "(剛新增成功的產品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			PrpiJDBCDAO dao = new PrpiJDBCDAO();
			System.out.println("list.size()-A=" + list.size());
			for (PrpiVO prpi_id : list) {
				prpi_id.setProd_id(new Integer(next_prod_id));
				dao.insertImg(prpi_id, con);
			}

			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增產品編號" + next_prod_id + "時,共有員工" + list.size() + "人同時被新增");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
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
		ProdJDBCDAO prodJDBC = new ProdJDBCDAO();

		// 新增
//		ProdVO prodVO1 = new ProdVO();
//
//		 prodVO1.setStore_id(2000014);
//		 prodVO1.setProd_name("麥客雞塊");
//		 prodVO1.setProd_descript("給你帶來滿滿的活力");
//		 prodVO1.setProd_price(500);
//		 prodVO1.setProd_sort("油炸類");
//		 prodVO1.setProd_format("雞塊");
//		 prodVO1.setProd_brand("麥當勞");
//		 prodVO1.setProd_updatetime(java.sql.Date.valueOf("2016-01-01"));
//		 prodVO1.setProd_soldcount(100);
//		 prodVO1.setProd_status(1);
//		 prodVO1.setProd_count(2);
//		 prodVO1.setProd_score(50);
//		 prodJDBC.insert(prodVO1);
		 System.out.println("---------新增一筆-----------");

		// 修改
		ProdVO prodVO2 = new ProdVO();
//		 prodVO2.setProd_id(2200010);
//		 
//		 prodVO2.setStore_id(2000016);
//		 prodVO2.setProd_name("麥客薯條");
//		 prodVO2.setProd_descript("本公司最新產品");
//		 prodVO2.setProd_price(5000);
//		 prodVO2.setProd_sort("油炸類");
//		 prodVO2.setProd_format("薯條");
//		 prodVO2.setProd_brand("麥當勞");
//		 prodVO2.setProd_updatetime(java.sql.Date.valueOf("2017-04-15"));
//		 prodVO2.setProd_soldcount(100);
//		 prodVO2.setProd_status(1);
//		 prodVO2.setProd_count(2);
//		 prodVO2.setProd_score(80);
//		 
		 
//		 prodJDBC.update(prodVO2);
		 System.out.println("---------修改一筆-----------");
		
		
		//刪除
//		prodJDBC.delete(2200007);
//		System.out.println("-------------刪除一筆-----------");
		
		// 查詢

//		ProdVO prodVO3 = prodJDBC.findByPrimaryKey(2200001);
//
//		System.out.print(prodVO3.getProd_id() + ",");
//		System.out.print(prodVO3.getStore_id() + ",");
//		System.out.print(prodVO3.getProd_name() + ",");
//		System.out.print(prodVO3.getProd_descript() + ",");
//		System.out.print(prodVO3.getProd_price() + ",");
//		System.out.print(prodVO3.getProd_sort() + ",");
//		System.out.print(prodVO3.getProd_format() + ",\n");
//		System.out.print(prodVO3.getProd_brand() + ",");
//		System.out.print(prodVO3.getProd_updatetime() + ",");
//		System.out.print(prodVO3.getProd_soldcount() + ",");
//		System.out.print(prodVO3.getProd_status() + ",");
//		System.out.print(prodVO3.getProd_count() + ",");
//		System.out.print(prodVO3.getProd_score() + ",\n");
		
		
//		System.out.println("----------查詢單筆-----------");
////		
////		
////		
//		List<ProdVO> list = prodJDBC.getAll();
//		for (ProdVO aProd : list) {
//			System.out.print(aProd.getProd_id() + ",");
//			System.out.print(aProd.getStore_id() + ",");
//			System.out.print(aProd.getProd_name() + ",");
//			System.out.print(aProd.getProd_descript() + ",");
//			System.out.print(aProd.getProd_price() + ",");
//			System.out.print(aProd.getProd_sort() + ",");
//			System.out.print(aProd.getProd_format() + ",");
//			System.out.print(aProd.getProd_brand() + ",");
//			System.out.print(aProd.getProd_updatetime() + ",");
//			System.out.print(aProd.getProd_soldcount() + ",");
//			System.out.print(aProd.getProd_status() + ",");
//			System.out.print(aProd.getProd_count() + ",");
//			System.out.print(aProd.getProd_score() + ",\n");
//			
//			
//		}
//		System.out.println("\n----------------查詢總表-------------");
//
//	}
	
	ProdVO prodVO1 = new ProdVO();

	prodVO1.setStore_id(2000014);
	prodVO1.setProd_name("麥客雞塊");
	prodVO1.setProd_descript("給你帶來滿滿的活力");
	prodVO1.setProd_price(500);
	prodVO1.setProd_sort("油炸類");
	prodVO1.setProd_format("雞塊");
	prodVO1.setProd_brand("麥當勞");
	prodVO1.setProd_updatetime(java.sql.Date.valueOf("2016-01-01"));
	prodVO1.setProd_soldcount(100);
	prodVO1.setProd_status(1);
	prodVO1.setProd_count(2);
	prodVO1.setProd_score(50);

	List<PrpiVO> list = new ArrayList<PrpiVO>();
	PrpiVO prpiVO = new PrpiVO();

	prpiVO.setPrpi_img(null);
	prpiVO.setPrpi_name("123");

	list.add(prpiVO);

	prodJDBC.insertWithPrpi(prodVO1, list);

}

	@Override
	public List<ProdVO> getOneProdSort(String prod_sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> getOneStoreTer(Integer store_ter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> getOneStore_idAllProd(Integer store_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> getTopThree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update_soldcount(Integer prod_soldcount, Integer prod_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update_count_score(Integer prod_count, Integer prod_score, Integer prod_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findByPrimaryKeyforStore_id(Integer prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> getAll4member() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdVO> getTopTwelve() {
		// TODO Auto-generated method stub
		return null;
	}






}
