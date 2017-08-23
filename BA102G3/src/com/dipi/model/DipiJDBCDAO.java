package com.dipi.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dish.model.DishJDBCDAO;
import com.dish.model.DishVO;

import oracle.net.aso.k;

public class DipiJDBCDAO implements DipiDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA102G3";
	String passwd = "123456";
	
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
	
	
	

	@Override
	public void insert(DipiVO dipiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, dipiVO.getDish_id());
			pstmt.setString(2, dipiVO.getDipi_name());
			pstmt.setBytes(3, dipiVO.getDipi_img());
			pstmt.setString(4, dipiVO.getDipi_imgfmt());
			
			
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
	public void update(DipiVO dipiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, dipiVO.getDish_id());
			pstmt.setString(2, dipiVO.getDipi_name());
			pstmt.setBytes(3, dipiVO.getDipi_img());
			pstmt.setString(4, dipiVO.getDipi_imgfmt());
			pstmt.setInt(5, dipiVO.getDipi_id());
			
			
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
	public void delete(Integer dipi_id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, dipi_id);
			
			
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
	public DipiVO findByPrimaryKey(Integer dipi_id) {
		// TODO Auto-generated method stub
		DipiVO dipiVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
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
				dipiVO.setDipi_img(rs.getBytes("dipi_img"));
				
				
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
				dipiVO = new DipiVO();
				dipiVO.setDipi_id(rs.getInt("dipi_id"));
				dipiVO.setDish_id(rs.getInt("Dish_id"));
				dipiVO.setDipi_name(rs.getString("dipi_name"));
				dipiVO.setDipi_imgfmt(rs.getString("dipi_imgfmt"));
				dipiVO.setDipi_img(rs.getBytes("dipi_img"));
				list.add(dipiVO); // Store the row in the list
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
	
	public static void main(String[] args) {

		DipiJDBCDAO dao = new DipiJDBCDAO();

//		// 新增
//		DipiVO dipiVO1 = new DipiVO();
//		dipiVO1.setDish_id(3200001);
//		dipiVO1.setDipi_name("pi001");
//		byte[] img = {5,5,5,5,5,5};
//		dipiVO1.setDipi_img(img);
//		dipiVO1.setDipi_imgfmt("jpg");
//		
//		dao.insert(dipiVO1);
//
//		// 修改
//		DipiVO dipiVO2 = new DipiVO();
//		dipiVO2.setDish_id(3200001);
//		dipiVO2.setDipi_name("pi0010001");
//		dipiVO2.setDipi_img(null);
//		dipiVO2.setDipi_imgfmt("jpg");
//		dipiVO2.setDipi_id(3300002);
//		// 刪除
//		dao.delete(3300002);
//
//		// 查詢
//		
//		DipiVO dipiVO3 = dao.findByPrimaryKey(3300002);
//		System.out.println("2");
//		System.out.print(dipiVO3.getDipi_id() + ",");
//		System.out.print(dipiVO3.getDish_id() + ",");
//		System.out.print(dipiVO3.getDipi_name() + ",");
//		System.out.print(dipiVO3.getDipi_img() + ",");
//		System.out.print(dipiVO3.getDipi_imgfmt());
//		
//		System.out.println("---------------------");
//
//		// 查詢
//		List<DipiVO> list = dao.getAll();
//		for (DipiVO dipi : list) {
//			System.out.print(dipi.getDipi_id() + ",");
//			System.out.print(dipi.getDish_id() + ",");
//			System.out.print(dipi.getDipi_name() + ",");
//			System.out.print(dipi.getDipi_img() + ",");
//			System.out.print(dipi.getDipi_imgfmt());
//			System.out.println();
//		}
		
		String path = "C:\\Users\\mjdtsay\\Dropbox\\三組\\Oracle建表格指令\\新增餐廳端圖片\\newDishPic";
		File f = new File(path); //讀取"00這個資料夾"，要記得將此資料夾放置同個java file裡面
        ArrayList<String> fileList = new ArrayList<String>(); //宣告一的動態陣列為String的型態，目的用來儲存檔名
        
        if(f.isDirectory()) //如果f讀到的是資料夾，就會執行
        {
            System.out.println("filename : "+f.getName()); //印出我們所讀到的資料夾
            String []s=f.list(); //宣告一個list
            System.out.println("size : "+s.length); //印出資料夾裡的檔案個數
            for(int i=0;i<s.length;i++)
            {
                //System.out.println(s[i]);
                fileList.add(s[i]); //將檔名一一存到fileList動態陣列裡面
            }
        }
        for(int i=0;i<fileList.size();i++)
        {
            System.out.println(fileList.get(i)); //印出資料夾內的檔名
            System.out.println(fileList.get(i));
            String k = fileList.get(i);
//            int endIndex = k.lastIndexOf(".");
//            name= k.substring(0,(endIndex));
//            System.out.println(name);
        }
        
    
		
		
        int j = 3200001;
		for(int i = 0;i<=70;i++){
			try {
				
				DipiVO dipi = new DipiVO();
				
				String k = fileList.get(i);
	            int endIndex = k.lastIndexOf(".");
	            String name= k.substring(0,(endIndex));
								
				
					dipi.setDish_id(j);
					dipi.setDipi_name(name);;
					byte[] pic = getPictureByteArray(path+"\\"+fileList.get(i));
					dipi.setDipi_imgfmt("jpg");
					dipi.setDipi_img(pic);
				
				dao.insert(dipi);
				j++;
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
		
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	@Override
	public byte[] download(Integer dipi_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

