package uploadImgToDB;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UploadImgPrpiTable {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "BA102G3";
	private static final String PASSWORD = "123456";
	private static final String SQL = "INSERT INTO prpi (prpi_id, prod_id, prpi_name, prpi_img) VALUES (prpi_seq.nextval, ?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			System.out.println("pass1");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
			System.out.println("pass2");
			
			pstmt = con.prepareStatement(SQL);
			
			System.out.println("pass3");
			
			for (int i=1; i<=153; i++){
						
			// 1. setBlob				
				Blob blob = con.createBlob();
				byte[] pic2 = getPictureByteArray("C:\\Users\\mjdtsay\\Dropbox\\三組\\Oracle建表格指令\\商品圖片整理\\"+String.valueOf(i)+".jpg");
						
				blob.setBytes(1, pic2);
						
				int prod_id=2200000+i;
				pstmt.setInt(1, prod_id);
				
				pstmt.setString(2, String.valueOf(i) );
			
				pstmt.setBlob(3, blob);
				
				System.out.println("pass5");
			
				pstmt.executeUpdate();
				
				System.out.println("pass6");

				pstmt.clearParameters();
				
				System.out.println("pass7");

				System.out.println("insert "+i+".jpg");
			
			}

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			// �����嚙踝�蕭�嚙踐乾嚙踝嚙踝嚙踝蕭 (���蕭謍圈���蕭���蕭嚙踐乾嚙踝蕭)
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	// �頛魂�nputStream��嚙踐�蕭蹓澗��蕭
	public static InputStream getPictureStream(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		return fis;
	}

	// �頛魂�yte[]嚙踐��蕭
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
}
