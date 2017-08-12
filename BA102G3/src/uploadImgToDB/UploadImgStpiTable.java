package uploadImgToDB;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.stpi.model.StpiDAO_interface;
import com.stpi.model.StpiVO;

import oracle.net.aso.i;



public class UploadImgStpiTable  {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String userid = "BA102G3";
	private static final String passwd = "123456";
	private static final String INSERT_STMT = "INSERT INTO STPI (STPI_ID,STORE_ID,STPI_NAME,STPI_IMG,STPI_IMGFMT)"
			+ " VALUES(STPI_SEQ.nextval, ?, ?, ?,?)";

	
	public void insert(StpiVO stpiVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, stpiVO.getStore_id());
			pstmt.setInt(2, stpiVO.getStpi_name());
			pstmt.setBytes(3, stpiVO.getStpi_img());
			pstmt.setString(4, stpiVO.getStpi_imgfmt());
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

	public static void main(String[] args) throws IOException {
		UploadImgStpiTable stpiJDBC = new UploadImgStpiTable();

		// 新增
		StpiVO stpiVO1 = new StpiVO();
		int i=1;
		for( i=1;i<35;i++){
			int stid=2000000+i;
			stpiVO1.setStore_id(stid);
			int stpi_name=1000+i;
			 stpiVO1.setStpi_name(stpi_name);
			 byte [] pic=getPictureByteArray("D://Dropbox//三組//Oracle建表格指令//store_icon//"+i+".jpg");
			 stpiVO1.setStpi_img(pic);
			 stpiVO1.setStpi_imgfmt("jpg");
			 
			 
			 stpiJDBC.insert(stpiVO1);
		}
		 
		 
		 System.out.println("-------新增"+(i-1)+"筆*--------");
	
////		
		
	}
}
