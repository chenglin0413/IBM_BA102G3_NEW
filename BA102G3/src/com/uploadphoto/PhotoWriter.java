package com.uploadphoto;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhotoWriter {

	private static final String UPDATE = "update trpi set trpi_img=?,trpi_imgfmt=? where trpi_id=?";

	public static void main(String[] args) {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "BA102G3";
		String passwd = "123456";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i < 11; i++) {
				pstmt = con.prepareStatement(UPDATE);
				InputStream in = new FileInputStream(new File("C:/Users/cuser/Desktop/images/" + i + ".jpg"));
	//InputStream in = new FileInputStream(new File("C:/Users/idests/Dropbox/三組/Oracle建表格指令/遊記照片/" + i + ".jpg"));
				byte[] pic = new byte[in.available()];
				in.read(pic);
				in.close();
				pstmt.setBytes(1, pic);
				pstmt.setString(2, "jpg");
				pstmt.setString(3,String.valueOf(1200000 + i));
				System.out.println("---------------------------");
				pstmt.executeUpdate();
			}
			System.out.println("done");

		} catch (IOException se) {
			se.printStackTrace(System.err);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
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

}

