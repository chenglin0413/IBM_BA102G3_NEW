package com.trvl.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tlcm.model.TlcmVO;
import com.trpi.model.TrpiDAO;
import com.trpi.model.TrpiVO;

public class TrvlDAO implements TrvlDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO trvl (TRVL_ID,USER_ID,TRVL_DATE,TRVL_TITTLE,TRVL_LOC,TRVL_CONTENT) "
			+ "VALUES (TRVL_ID_SEQ.NEXTVAL, ?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE Trvl set user_id=?, trvl_date=?, trvl_tittle=?, trvl_loc=? ,trvl_content=? "
			+"where trvl_id = ?";

	private static final String DELETE_TLCM = "DELETE FROM TLCM where trvl_id=?";
	private static final String DELETE_TRPI = "DELETE FROM TRPI where trvl_id=?";
	private static final String DELETE = "DELETE FROM Trvl where trvl_id=?";

	private static final String GET_ONE_STMT = "SELECT * FROM Trvl where trvl_id =?";
	private static final String GET_ALL_STMT = "SELECT * FROM Trvl WHERE trvl_id NOT IN (select trvl_id from rptl where rptl_status = 1) order by trvl_id desc";
	
	private static final String Get_BY_USERID = "SELECT * FROM Trvl where user_id = ? order by trvl_date desc";
	
	private static final String Get_Tlcms = "SELECT * FROM Tlcm where trvl_id=?";
	private static final String Get_Trpis = "SELECT * FROM Trpi where trvl_id=?";
	
	private static final String add_trvl_count = "update trvl set trvl_count=(trvl_count+1) where trvl_id=?";
	
	private static final String Get_TOP_BLOGS = "SELECT * FROM (select * from trvl order by trvl_count desc) where rownum <=3";

	@Override
	public String insert(TrvlVO trvlVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		String trvlid = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "TRVL_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			pstmt.setInt(1, trvlVO.getUser_id());
			pstmt.setDate(2, trvlVO.getTrvl_date());
			pstmt.setString(3, trvlVO.getTrvl_tittle());
			pstmt.setString(4, trvlVO.getTrvl_loc());
			pstmt.setString(5, trvlVO.getTrvl_content());

			pstmt.executeUpdate();
	
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						trvlid = rs.getString(i);
						System.out.println( "新增的自增主鍵值號碼 :" + trvlid );
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();
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
		return trvlid;
	}

	@Override
	public void update(TrvlVO trvlVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, trvlVO.getUser_id());
			pstmt.setDate(2, trvlVO.getTrvl_date());
			pstmt.setString(3, trvlVO.getTrvl_tittle());
			pstmt.setString(4, trvlVO.getTrvl_loc());
			pstmt.setString(5, trvlVO.getTrvl_content());
			pstmt.setInt(6, trvlVO.getTrvl_id());

			System.out.println("更新遊記標題 :"+ trvlVO.getTrvl_tittle());
			System.out.println("更新遊記地點:"+ trvlVO.getTrvl_loc());
			System.out.println("更新遊記內容 :"+ trvlVO.getTrvl_content());
			
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

	@Override
	public void delete(Integer trvl_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateTlcm = 0;
		int updateTrpi = 0;
		try {
	
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//先刪除留言
			pstmt = con.prepareStatement(DELETE_TLCM);
			pstmt.setInt(1, trvl_id);
			updateTlcm = pstmt.executeUpdate();
			
			//在刪除相片
			pstmt = con.prepareStatement(DELETE_TRPI);
			pstmt.setInt(1, trvl_id);
			updateTrpi = pstmt.executeUpdate();
		
			//刪除遊記	
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, trvl_id);
			pstmt.executeUpdate();

			System.out.println("遊記編號" + trvl_id +"遊記被刪除時共有 :"+ updateTlcm + "筆留言被刪除  , " + updateTrpi + "張相片被刪除");
			
			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
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

	@Override
	public TrvlVO findByPrimaryKey(Integer trvl_id) {
		// TODO Auto-generated method stub
		TrvlVO trvlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, trvl_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
			}

			// Handle any driver errors
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

		return trvlVO;
	}

	@Override
	public List<TrvlVO> findByForeignKey(Integer user_id) {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_BY_USERID);

			pstmt.setInt(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<TrvlVO> getAll() {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<TlcmVO> findTlcmsByTrvlId(Integer trvl_id) {
		// TODO Auto-generated method stub
		List<TlcmVO> list = new ArrayList<TlcmVO>();
		TlcmVO tlcmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Tlcms);
			
			pstmt.setInt(1,trvl_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				tlcmVO = new TlcmVO();
				tlcmVO.setTlcm_id(rs.getInt("TLCM_ID"));
				tlcmVO.setTrvl_id(rs.getInt("TRVL_ID"));
				tlcmVO.setUser_id(rs.getInt("USER_ID"));
				tlcmVO.setTlcm_date(rs.getDate("TLCM_DATE"));
				tlcmVO.setTlcm_content(rs.getString("TLCM_CONTENT"));
				list.add(tlcmVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	public List<TrpiVO> findTrpisByTrvlId (Integer trvl_id) {
		List<TrpiVO> list = new ArrayList<TrpiVO>();
		TrpiVO trpiVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_Trpis);
			
			pstmt.setInt(1,trvl_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trpiVO = new TrpiVO();
				trpiVO.setTrpi_id(rs.getInt("TRPI_ID"));
				trpiVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trpiVO.setTrpi_img(rs.getBytes("TRPI_IMG"));
				trpiVO.setTrpi_imgfmt(rs.getString("TRPI_IMGFMT"));
				list.add(trpiVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public void addPageView(Integer trvl_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(add_trvl_count);

			pstmt.setInt(1, trvl_id);

			System.out.println("遊記瀏覽次數+1");
			pstmt.executeUpdate();
			con.commit();
			// Handle any driver errors
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
	public List<TrvlVO> getTopOfBlogs() {
		// TODO Auto-generated method stub
		List<TrvlVO> list = new ArrayList<TrvlVO>();
		TrvlVO trvlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(Get_TOP_BLOGS);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				trvlVO = new TrvlVO();
				trvlVO.setTrvl_id(rs.getInt("TRVL_ID"));
				trvlVO.setUser_id(rs.getInt("USER_ID"));
				trvlVO.setTrvl_date(rs.getDate("TRVL_DATE"));
				trvlVO.setTrvl_tittle(rs.getString("TRVL_tittle"));
				trvlVO.setTrvl_loc(rs.getString("TRVL_LOC"));
				trvlVO.setTrvl_content(rs.getString("TRVL_CONTENT"));
				trvlVO.setTrvl_count(rs.getInt("TRVL_COUNT"));
				trvlVO.setTrvl_score(rs.getInt("TRVL_SCORE"));
				list.add(trvlVO);
			}

			// Handle any driver errors
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
	
//	@Override
//	public void insertWithTrpis(TrvlVO trvlVO , List<TrpiVO> list) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			// 1●設定於 pstm.executeUpdate()之前
//    		con.setAutoCommit(false);
//			
//    		// 先新增遊記
//			String cols[] = {"TRVL_ID"};
//			pstmt = con.prepareStatement(INSERT_STMT , cols);			
//			pstmt.setInt(1, trvlVO.getUser_id());
//			pstmt.setDate(2, trvlVO.getTrvl_date());
//			pstmt.setString(3, trvlVO.getTrvl_tittle());
//			pstmt.setString(4, trvlVO.getTrvl_loc());
//			pstmt.setString(5, trvlVO.getTrvl_content());
//			pstmt.executeUpdate();
//			//掘取對應的自增主鍵值
//			String next_trvlno = null;
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				next_trvlno = rs.getString(1);
//				System.out.println("自增主鍵值= " + next_trvlno +"(剛新增成功的遊記編號)");
//			} else {
//				System.out.println("未取得自增主鍵值");
//			}
//			rs.close();
//			// 再同時新增照片
//			TrpiDAO dao = new TrpiDAO();
//			System.out.println("list.size()-A="+list.size());
//			for (TrpiVO aTrpi : list) {
//				aTrpi.setTrvl_id(Integer.parseInt(next_trvlno));
//				dao.insert(aTrpi);
//			}
//
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			System.out.println("list.size()-B="+list.size());
//			System.out.println("新增遊記編號" + next_trvlno + "時,共有照片" + list.size()
//					+ "張同時被新增");
//			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-dept");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
	
}
