package com.init.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Crawler_Bus {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO BUSC(BUSC_ID,BUSC_END,BUSC_LINE,BUSC_TIME,BUSC_ROUTE)"
			+ "VALUES(BUSC_ID_SEQ.NEXTVAL,?,?,?,?)";
	
	public static void main(String[] args) {
		Crawler();
	}

	public static void Crawler() {
		
		List<String> ar1 = new ArrayList<String>();
		List<String> ar2 = new ArrayList<String>();
		List<String> ar3 = new ArrayList<String>();
		List<String> ar4 = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			URL url = new URL("http://ebus.taoyuan-airport.com/TYairport/LCDLayout01.aspx?output_type=1");
			Document xmlDoc = Jsoup.parse(url, 10000);

			Elements td = xmlDoc.select("td");
			FileWriter out = new FileWriter("./bus.txt");

			int tdSize = td.size() - 10;
			if (td != null) {
				for (int i = 7; i < tdSize; i++) {
					String[] tds = td.get(i).text().split(" ");
					for (String tdList : tds) {
						String l1 = tdList.replace("Minutes", "分鐘");
						String l2 = l1.replaceAll("[A-z]", ",");
						String l3 = l2.replace("1,", "");
						String l4 = l3.replace("2,", "");
						String l5 = l4.replace("(,,,,,)", "");
						String l6 = l5.replace("長榮客運", " － 長榮客運");
						String l7 = l6.replace("國光客運", " － 國光客運");
						String l8 = l7.replace("建明客運", " － 建明客運");
						String l9 = l8.replace("大有巴士", " － 大有巴士");
						String l0 = l9.replace("統聯客運", " － 統聯客運");
						String busList = l0.replace("葛瑪蘭客運", " － 葛瑪蘭客運");
						out.write(busList);
					}
				}
				out.close();
			}

			BufferedReader in = new BufferedReader(new FileReader("./bus.txt"));

			String b1 = in.readLine();
			String b2 = b1.replace("--", "營業時間結束");
			String b3 = b2.replace("-", ",");
			String b4 = b3.replace(".", ",");
			String b5 = b4.replace(",,,,,", ",");
			String b6 = b5.replace(",,,,", ",");
			String b7 = b6.replace(",,,", ",");
			String b8 = b7.replace(",,", ",");
			String b9 = b8.replace("行天宮", ",行天宮");
			String b10 = b9.replace("台茂、", ",台茂、");
			String b11 = b10.replace("榮星花園、忠孝", ",榮星花園、忠孝");
			String b12 = b11.replace("三峽恩主宮醫院、", ",三峽恩主宮醫院、");
			String b13 = b12.replace("永寧捷運站、", ",永寧捷運站、");
			String b14 = b13.replace("第一航廈、", ",第一航廈、");
			String b15 = b14.replace("中清、", ",中清、");
			String b16 = b15.replace("啟聰學校、", ",啟聰學校、");
			String b17 = b16.replace("圓山飯店、", ",圓山飯店、");
			String b18 = b17.replace("南崁、圓", ",南崁、圓");
			String b19 = b18.replace("轉乘至豐原、", ",轉乘至豐原、");
			String b20 = b19.replace("朝馬、長", ",朝馬、長");
			String b21 = b20.replace("朝陽橋、新光", ",朝陽橋、新光");
			String b22 = b21.replace("廣三,", "廣三");
			String b23 = b22.replace(",,", ",");
			String finalList = b23.replace(",", ",");
			
			in.close();

			String[] busSplit = finalList.split(",");

			for (int i = 0; i < busSplit.length; i++) {
				if (i % 4 == 0) {
					ar1.add(busSplit[i]);
				} else if (i % 4 == 1) {
					ar2.add(busSplit[i]);
				} else if (i % 4 == 2) {
					ar3.add(busSplit[i]);
				} else if (i % 4 == 3) {
					ar4.add(busSplit[i]);
				}
			}

			Iterator<String> i1 = ar1.iterator();
			Iterator<String> i2 = ar2.iterator();
			Iterator<String> i3 = ar3.iterator();
			Iterator<String> i4 = ar4.iterator();
			
			String[] cols = { "BUSC_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			for (int j = 0; j < 16; j++) {
				pstmt.setString(1, i1.next());
				pstmt.setString(2, i2.next());
				pstmt.setString(3, i3.next());
				pstmt.setString(4, i4.next());
				pstmt.executeUpdate();
			}

		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
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
}
