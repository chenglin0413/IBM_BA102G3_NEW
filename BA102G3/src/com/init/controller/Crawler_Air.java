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
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class Crawler_Air {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO FLSC(FLSC_ID,FLSC_TERM,FLSC_AIRLINECODE,FLSC_AIRLINE_C,"
			+ "FLSC_FLNO,FLSC_GATE,FLSC_SDATE,FLSC_STIME,FLSC_PDATE,FLSC_PTIME,FLSC_LOCATION_CODE,"
			+ "FLSC_LOCATION_E,FLSC_LOCATION_C,FLSC_STATUS,FLSC_BAG,FLSC_CHECKIN)"
			+ "VALUES(FLSC_ID_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static void main(String[] args) {
		Crawler();
	}

	public static void Crawler() {

		List<String> a01 = new ArrayList<String>();
		List<String> a02 = new ArrayList<String>();
		List<String> a03 = new ArrayList<String>();
		List<String> a04 = new ArrayList<String>();
		List<String> a05 = new ArrayList<String>();
		List<String> a06 = new ArrayList<String>();
		List<String> a07 = new ArrayList<String>();
		List<String> a08 = new ArrayList<String>();
		List<String> a09 = new ArrayList<String>();
		List<String> a10 = new ArrayList<String>();
		List<String> a11 = new ArrayList<String>();
		List<String> a12 = new ArrayList<String>();
		List<String> a13 = new ArrayList<String>();
		List<String> a14 = new ArrayList<String>();
		List<String> a15 = new ArrayList<String>();
		List<String> a16 = new ArrayList<String>();
		List<String> a17 = new ArrayList<String>();
		List<String> a18 = new ArrayList<String>();
		List<String> a19 = new ArrayList<String>();
		List<String> a20 = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			URL url = new URL("http://www.taoyuan-airport.com/uploads/flightx/a_flight_v4.csv");

			String characterSet = "Big5"; // a_flight_v4.csv default(Big5)

			FileWriter txt_out = new FileWriter("./air.txt");

			Scanner inputStream = new Scanner(url.openStream(), characterSet); // Big5,inputStream
			if (inputStream != null) {
				while (inputStream.hasNext()) {
					String airData = inputStream.next();
					StringBuffer list = new StringBuffer(airData);
					String l1 = list.toString().replace("1,A,", ",1,A,");
					String l2 = l1.replace("2,A,", ",2,A,");
					String l3 = l2.replace("1,D,", ",1,D,");
					String finalLists = l3.replace("2,D,", ",2,D,");
					txt_out.write(finalLists);
				}
				txt_out.close(); // save
			}
			inputStream.close();

			BufferedReader txt_in = new BufferedReader(new FileReader("./air.txt"));

			StringBuffer str = new StringBuffer(txt_in.readLine());
			String source = str.append(",").toString(); // 補上最後的","

			txt_in.close();

			String[] array = source.split(",");

			for (int i = 0; i < array.length; i++) {
				if (i % 20 == 1) {
					a01.add(array[i]);
				} else if (i % 20 == 2) {
					a02.add(array[i]);
				} else if (i % 20 == 3) {
					a03.add(array[i]);
				} else if (i % 20 == 4) {
					a04.add(array[i]);
				} else if (i % 20 == 5) {
					a05.add(array[i]);
				} else if (i % 20 == 6) {
					a06.add(array[i]);
				} else if (i % 20 == 7) {
					a07.add(array[i]);
				} else if (i % 20 == 8) {
					a08.add(array[i]);
				} else if (i % 20 == 9) {
					a09.add(array[i]);
				} else if (i % 20 == 10) {
					a10.add(array[i]);
				} else if (i % 20 == 11) {
					a11.add(array[i]);
				} else if (i % 20 == 12) {
					a12.add(array[i]);
				} else if (i % 20 == 13) {
					a13.add(array[i]);
				} else if (i % 20 == 14) {
					a14.add(array[i]);
				} else if (i % 20 == 15) {
					a15.add(array[i]);
				} else if (i % 20 == 16) {
					a16.add(array[i]);
				} else if (i % 20 == 17) {
					a17.add(array[i]);
				} else if (i % 20 == 18) {
					a18.add(array[i]);
				} else if (i % 20 == 19) {
					a19.add(array[i]);
				} else if (i % 20 == 0) {
					a20.add(array[i]);
				}
			}

			Iterator<String> i01 = a01.iterator();
			Iterator<String> i03 = a03.iterator();
			Iterator<String> i04 = a04.iterator();
			Iterator<String> i05 = a05.iterator();
			Iterator<String> i06 = a06.iterator();
			Iterator<String> i07 = a07.iterator();
			Iterator<String> i08 = a08.iterator();
			Iterator<String> i09 = a09.iterator();
			Iterator<String> i10 = a10.iterator();
			Iterator<String> i11 = a11.iterator();
			Iterator<String> i12 = a12.iterator();
			Iterator<String> i13 = a13.iterator();
			Iterator<String> i14 = a14.iterator();
			Iterator<String> i19 = a19.iterator();
			Iterator<String> i20 = a20.iterator();

			int arrs = (array.length / 20);

			String[] cols = { "FLSC_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

			for (int j = 0; j < arrs; j++) {
				pstmt.setString(1, i01.next());
				pstmt.setString(2, i03.next());
				pstmt.setString(3, i04.next());
				pstmt.setString(4, i05.next());
				pstmt.setString(5, i06.next());
				pstmt.setString(6, i07.next());
				pstmt.setString(7, i08.next());
				pstmt.setString(8, i09.next());
				pstmt.setString(9, i10.next());
				pstmt.setString(10, i11.next());
				pstmt.setString(11, i12.next());
				pstmt.setString(12, i13.next());
				pstmt.setString(13, i14.next());
				pstmt.setString(14, i19.next());
				pstmt.setString(15, i20.next());
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
