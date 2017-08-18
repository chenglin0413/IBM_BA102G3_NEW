package com.prod.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.json.JSONException;
import com.json.JSONObject;
import com.prpi.model.PrpiJDBCDAO;
import com.prpi.model.PrpiVO;

public class ProdDAO implements ProdDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO PROD (PROD_ID,STORE_ID,PROD_NAME,PROD_DESCRIPT,PROD_PRICE,PROD_SORT,PROD_FORMAT,PROD_BRAND,PROD_UPDATETIME,PROD_SOLDCOUNT,PROD_STATUS,PROD_COUNT,PROD_SCORE)"
			+ "VALUES(PROD_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE PROD SET STORE_ID= ?,PROD_NAME=?,PROD_DESCRIPT=?,PROD_PRICE=?,PROD_SORT=?,PROD_FORMAT=?,PROD_BRAND=?,PROD_UPDATETIME=?,PROD_SOLDCOUNT=?,PROD_STATUS=?,PROD_COUNT=?,PROD_SCORE=? WHERE PROD_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM PROD WHERE PROD_ID=?";
	private static final String GET_ONE_STMT = "SELECT * FROM PROD WHERE PROD_ID=?";
	private static final String GET_ALL = "SELECT * FROM PROD ORDER BY PROD_ID DESC";
	private static final String GET_ALL4member = "SELECT * FROM prod WHERE prod_id NOT IN (select prod_id from rppr where rppr_status = 1)and prod_status=1 order by PROD_UPDATETIME desc";
	private static final String GET_ONE_PROD_SORT = "SELECT * FROM PROD WHERE prod_id NOT IN (select prod_id from rppr where rppr_status = 1)and prod_status=1 and PROD_SORT=?";
	private static final String GET_ONE_STORE_TER = "SELECT p.prod_id,p.store_id,p.prod_name,p.prod_price,p.prod_sort,p.prod_descript,p.prod_count,p.prod_score FROM PROD P JOIN STORE S ON P.STORE_ID = S.STORE_ID WHERE prod_id NOT IN (select prod_id from rppr where rppr_status = 1) and STORE_TER = ? and PROD_STATUS='1' ORDER BY PROD_ID DESC";
	private static final String GET_ONE_STORE_IDALLPROD = "SELECT * FROM PROD WHERE STORE_ID=? ORDER BY PROD_ID DESC";
	private static final String GET_TOP_THREE = "select  * from(select * from prod order by prod_soldcount desc) where rownum <4";
	private static final String GET_TOP_TWELVE = "select  * from(select * from prod order by prod_score desc) where rownum <13";
	private static final String UPDATE_SOLDCOUNT = "UPDATE PROD SET PROD_SOLDCOUNT=? WHERE PROD_ID = ?";
	private static final String UPDATE_COUNT_SCORE = "UPDATE PROD SET PROD_COUNT=?,PROD_SCORE=? WHERE PROD_ID = ?";
	private static final String GET_ONE_STORE_ID = "SELECT STORE_ID FROM PROD WHERE PROD_ID=?";

	@Override
	public void insert(ProdVO prodVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public void update_soldcount(Integer prod_soldcount, Integer prod_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SOLDCOUNT);

			pstmt.setInt(1, prod_soldcount);
			pstmt.setInt(2, prod_id);
			pstmt.executeUpdate();

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

	public void update_count_score(Integer prod_count, Integer prod_score, Integer prod_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COUNT_SCORE);

			pstmt.setInt(1, prod_count);
			pstmt.setInt(2, prod_score);
			pstmt.setInt(3, prod_id);
			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, prod_id);
			pstmt.executeUpdate();

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

			con = ds.getConnection();
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

		return prodVO;
	}

	@Override
	public Integer findByPrimaryKeyforStore_id(Integer prod_id) {
		// TODO Auto-generated method stub
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer store_id = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STORE_ID);
			pstmt.setInt(1, prod_id);
			rs = pstmt.executeQuery();
			// System.out.println("1111111111111aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			rs.next();
			store_id = rs.getInt("store_id");
			// System.out.println("222222222222222222aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		} catch (Exception se) {
			se.fillInStackTrace();
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

		return store_id;
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

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

				list.add(prodVO);
			}

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
	public List<ProdVO> getAll4member() {
		// TODO Auto-generated method stub
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL4member);

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

				list.add(prodVO);
			}

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
	public List<ProdVO> getOneProdSort(String prod_sort) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_PROD_SORT);
			pstmt.setString(1, prod_sort);
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
				list.add(prodVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ProdVO> getOneStoreTer(Integer store_ter) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STORE_TER);
			pstmt.setInt(1, store_ter);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodVO = new ProdVO();
				prodVO.setProd_id(rs.getInt("prod_id"));
				prodVO.setStore_id(rs.getInt("store_id"));
				prodVO.setProd_name(rs.getString("prod_name"));
				prodVO.setProd_price(rs.getInt("prod_price"));
				prodVO.setProd_sort(rs.getString("prod_sort"));
				prodVO.setProd_score(rs.getInt("prod_score"));
				prodVO.setProd_count(rs.getInt("prod_count"));
				list.add(prodVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ProdVO> getOneStore_idAllProd(Integer store_id) {
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STORE_IDALLPROD);
			pstmt.setInt(1, store_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodVO = new ProdVO();
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
				list.add(prodVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ProdVO> getTopThree() {
		// TODO Auto-generated method stub
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_THREE);

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

				list.add(prodVO);
			}

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
	public List<ProdVO> getTopTwelve() {
		// TODO Auto-generated method stub
		List<ProdVO> list = new ArrayList<ProdVO>();
		ProdVO prodVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TOP_TWELVE);

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

				list.add(prodVO);
			}

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

			con = ds.getConnection();

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
			System.out.println("新增產品編號" + next_prod_id + "時,共有" + list.size() + "筆被新增");

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

}