package com.ord.model;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.item.model.ItemDAO;
import com.item.model.ItemVO;
import com.prod.model.ProdDAO;
import com.prod.model.ProdService;
import com.prod.model.ProdVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Ord;

public class OrdDAO implements OrdDAO_interface {
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
	
	
	
	
	
	
		private static final String INSERT_STMT = "INSERT INTO ORD (ORD_ID,USER_ID,STORE_ID,ORD_DATE,ORD_TOTAL,ORD_BILL,ORD_GRANT,ORD_STATUS,ORD_SSCORE,ORD_RPDATE,ORD_RPCOMM,ORD_RPSTATUS)"
				+ "VALUES(ORD_SEQ.nextval, ?, ?, ?, ?, ?, ?,?,?,?,?,?)";
		private static final String UPDATE_STMT = "UPDATE ORD SET USER_ID= ?,STORE_ID=?,ORD_DATE=?,ORD_TOTAL=?,ORD_BILL=?,ORD_GRANT=?,ORD_STATUS=?,ORD_SSCORE=?,ORD_RPDATE=?,ORD_RPCOMM=?,ORD_RPSTATUS=? WHERE ORD_ID = ?";
		private static final String DELETE_STMT = "DELETE FROM ORD WHERE ORD_ID=?";
		private static final String GET_ONE_STMT = "SELECT * FROM ORD WHERE ORD_ID=?";
		private static final String GET_ALL = "SELECT * FROM ORD ORDER BY ORD_ID DESC";
		private static final String GET_ONE_ORD_IDAND_STORE_ID= "select * from(SELECT ord_id,store_id from ord order by ord_id desc)where rownum<=1";
		private static final String GET_AllOrd_STORE_ID= "SELECT * FROM  ORD WHERE STORE_ID =? ORDER BY ORD_ID DESC";
		private static final String GET_ONE_USER_ID_AllOrd= "SELECT * FROM  ORD WHERE USER_ID=? ORDER BY ORD_ID DESC";
		private static final String UPDATE_SSCORE=" UPDATE ORD SET ORD_SSCORE=? WHERE ORD_ID=?";
		private static final String UPDATE_BILL=" UPDATE ORD SET ORD_BILL=? WHERE ORD_ID=?";
		
		
		@Override
		public void insert(OrdVO ordVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				con.setAutoCommit(false);
				pstmt.setInt(1, ordVO.getUser_id());
				pstmt.setInt(2, ordVO.getStore_id());
				pstmt.setDate(3, ordVO.getOrd_date());
				pstmt.setInt(4, ordVO.getOrd_total());
				pstmt.setInt(5, ordVO.getOrd_bill());
				pstmt.setInt(6, ordVO.getOrd_grant());
				pstmt.setInt(7, ordVO.getOrd_status());
				pstmt.setInt(8, ordVO.getOrd_sscore());
				pstmt.setDate(9, ordVO.getOrd_rpdate());
				pstmt.setString(10, ordVO.getOrd_rpcomm());
				pstmt.setInt(11, ordVO.getOrd_rpstatus());
				
				
				pstmt.executeUpdate();

				con.commit();
			} catch (SQLException se) {
				try {
					con.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
		public Integer insertWithItems(OrdVO ordVO , List<ItemVO> list) {

			Connection con = null;
			PreparedStatement pstmt = null;
			Integer ord_id=null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
				
				// 1●設定於 pstm.executeUpdate()之前
	    		con.setAutoCommit(false);
				
	    		// 先新增部門
				String cols[] = {"ord_id"};
				pstmt = con.prepareStatement(INSERT_STMT , cols);			
				pstmt.setInt(1, ordVO.getUser_id());
				pstmt.setInt(2, ordVO.getStore_id());
				pstmt.setDate(3, ordVO.getOrd_date());
				pstmt.setInt(4, ordVO.getOrd_total());
				pstmt.setInt(5, ordVO.getOrd_bill());
				pstmt.setInt(6, ordVO.getOrd_grant());
				pstmt.setInt(7, ordVO.getOrd_status());
				pstmt.setInt(8, ordVO.getOrd_sscore());
				pstmt.setDate(9, ordVO.getOrd_rpdate());
				pstmt.setString(10, ordVO.getOrd_rpcomm());
				pstmt.setInt(11, ordVO.getOrd_rpstatus());
				pstmt.executeUpdate();
				//掘取對應的自增主鍵值
				String next_ord_id = null;
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_ord_id = rs.getString(1);
					System.out.println("自增主鍵值= " + next_ord_id +"(剛新增成功的訂單編號)");
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				// 再同時新增員工
				ItemDAO dao = new ItemDAO();
				System.out.println("list.size()-A="+list.size());
				System.out.println(ordVO.getStore_id());
				ProdDAO prodDAO = new ProdDAO();
				int count=0;
				for(ItemVO aItemVO:list){
					
					System.out.println("我是count"+count);
					Integer aprod_id=aItemVO.getProd_id();
//					System.out.println("aprod_id="+aprod_id);
//					System.out.println("b1="+prodDAO.findByPrimaryKeyforStore_id(aprod_id));
//					System.out.println("b2="+ordVO.getStore_id());
					System.out.println("------------------------------------------------------------------------------------");
					if(prodDAO.findByPrimaryKeyforStore_id(aprod_id).equals(ordVO.getStore_id())){
						aItemVO.setOrd_id(new Integer(next_ord_id)) ;
						dao.insert2(aItemVO,con);
						count+=1;
					}
					
				}

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("list.size()-B="+list.size());
				System.out.println("新增訂單編號" + next_ord_id + "時,共有訂單明細" + list.size()
						+ "同時被新增");
				
				ord_id=new Integer(next_ord_id);
				// Handle any driver errors
				
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-ord");
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
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
			return ord_id;
		}

		
		public OrdVO select_One_ord_id() {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			OrdVO ordVO = null;	
				ResultSet rs = null;

				try {
					
					con = ds.getConnection();
					pstmt = con.prepareStatement(GET_ONE_ORD_IDAND_STORE_ID);
					rs = pstmt.executeQuery();

					rs=pstmt.executeQuery();

					while (rs.next()) {
						ordVO = new OrdVO();
						ordVO.setOrd_id(rs.getInt("ord_id"));
						ordVO.setStore_id(rs.getInt("store_id"));
					}
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
				return ordVO;

		}
		@Override
		public void update(OrdVO ordVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, ordVO.getUser_id());
				pstmt.setInt(2, ordVO.getStore_id());
				pstmt.setDate(3, ordVO.getOrd_date());
				pstmt.setInt(4, ordVO.getOrd_total());
				pstmt.setInt(5, ordVO.getOrd_bill());
				pstmt.setInt(6, ordVO.getOrd_grant());
				pstmt.setInt(7, ordVO.getOrd_status());
				pstmt.setInt(8, ordVO.getOrd_sscore());
				pstmt.setDate(9, ordVO.getOrd_rpdate());
				pstmt.setString(10, ordVO.getOrd_rpcomm());
				pstmt.setInt(11, ordVO.getOrd_rpstatus());
				pstmt.setInt(12, ordVO.getOrd_id());
				

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
		
		public void update_sscore(Integer ord_sscore,Integer ord_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_SSCORE);
				
				pstmt.setInt(1, ord_sscore);
				pstmt.setInt(2, ord_id);
				

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
		
		public void update_bill(Integer ord_bill,Integer ord_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_BILL);
				
				pstmt.setInt(1, ord_bill);
				pstmt.setInt(2, ord_id);
				

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
		public void delete(Integer ord_id) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, ord_id);
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
		public List<OrdVO> getAll() {
			// TODO Auto-generated method stub
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);

				rs=pstmt.executeQuery();

				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setOrd_id(rs.getInt("ord_id"));
					ordVO.setUser_id(rs.getInt("user_id"));
					ordVO.setStore_id(rs.getInt("store_id"));
					ordVO.setOrd_date(rs.getDate("ord_date"));
					ordVO.setOrd_total(rs.getInt("ord_total"));
					ordVO.setOrd_bill(rs.getInt("ord_bill"));
					ordVO.setOrd_grant(rs.getInt("ord_grant"));
					ordVO.setOrd_status(rs.getInt("ord_status"));
					ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
					ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
					ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
					ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
					list.add(ordVO);
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
		public OrdVO findByPrimaryKey(Integer ord_id) {
			// TODO Auto-generated method stub
			OrdVO ordVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				pstmt.setInt(1, ord_id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setOrd_id(rs.getInt("ord_id"));
					ordVO.setUser_id(rs.getInt("user_id"));
					ordVO.setStore_id(rs.getInt("store_id"));
					ordVO.setOrd_date(rs.getDate("ord_date"));
					ordVO.setOrd_total(rs.getInt("ord_total"));
					ordVO.setOrd_bill(rs.getInt("ord_bill"));
					ordVO.setOrd_grant(rs.getInt("ord_grant"));
					ordVO.setOrd_status(rs.getInt("ord_status"));
					ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
					ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
					ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
					ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
					

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

			return ordVO;
		}
		
		@Override
		public List<OrdVO> getOneStore_idAllOrd(Integer store_id) {
			// TODO Auto-generated method stub
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_AllOrd_STORE_ID);
			    pstmt.setInt(1, store_id);
				rs=pstmt.executeQuery();

				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setOrd_id(rs.getInt("ord_id"));
					ordVO.setUser_id(rs.getInt("user_id"));
					ordVO.setStore_id(rs.getInt("store_id"));
					ordVO.setOrd_date(rs.getDate("ord_date"));
					ordVO.setOrd_total(rs.getInt("ord_total"));
					ordVO.setOrd_bill(rs.getInt("ord_bill"));
					ordVO.setOrd_grant(rs.getInt("ord_grant"));
					ordVO.setOrd_status(rs.getInt("ord_status"));
					ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
					ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
					ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
					ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
					list.add(ordVO);
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
		public List<OrdVO> getOneUser_idAllOrd(Integer user_id) {
			// TODO Auto-generated method stub
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_USER_ID_AllOrd);
			    pstmt.setInt(1, user_id);
				rs=pstmt.executeQuery();

				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setOrd_id(rs.getInt("ord_id"));
					ordVO.setUser_id(rs.getInt("user_id"));
					ordVO.setStore_id(rs.getInt("store_id"));
					ordVO.setOrd_date(rs.getDate("ord_date"));
					ordVO.setOrd_total(rs.getInt("ord_total"));
					ordVO.setOrd_bill(rs.getInt("ord_bill"));
					ordVO.setOrd_grant(rs.getInt("ord_grant"));
					ordVO.setOrd_status(rs.getInt("ord_status"));
					ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
					ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
					ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
					ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
					list.add(ordVO);
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
		public List<OrdVO> getAll(Map<String, String[]> map) {
			List<OrdVO> list = new ArrayList<OrdVO>();
			OrdVO ordVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				
				con = ds.getConnection();
				String finalSQL = "select * from ord "
			          + jdbcUtil_CompositeQuery_Ord.get_WhereCondition(map)
			          + "order by ord_id desc";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					ordVO = new OrdVO();
					ordVO.setOrd_id(rs.getInt("ord_id"));
					ordVO.setUser_id(rs.getInt("user_id"));
					ordVO.setStore_id(rs.getInt("store_id"));
					ordVO.setOrd_date(rs.getDate("ord_date"));
					ordVO.setOrd_total(rs.getInt("ord_total"));
					ordVO.setOrd_bill(rs.getInt("ord_bill"));
					ordVO.setOrd_grant(rs.getInt("ord_grant"));
					ordVO.setOrd_status(rs.getInt("ord_status"));
					ordVO.setOrd_sscore(rs.getInt("ord_sscore"));
					ordVO.setOrd_rpdate(rs.getDate("ord_rpdate"));
					ordVO.setOrd_rpcomm(rs.getString("ord_rpcomm"));
					ordVO.setOrd_rpstatus(rs.getInt("ord_rpstatus"));
					list.add(ordVO); // Store the row in the List
				}
		
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
