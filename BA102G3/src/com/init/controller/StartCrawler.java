package com.init.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.busc.model.BuscDAO;
import com.flsc.model.FlscDAO;

public class StartCrawler extends HttpServlet {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA102G3DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	int delete = 1;
	int insert = 1;

	Timer timerDelete = new Timer();
	Timer timerInsert = new Timer();

	Timer dateCheck = new Timer();

	BuscDAO bus = new BuscDAO();
	FlscDAO air = new FlscDAO();

	public void destroy() {

		System.out.println("***** Crawlers Tables Clean *****");

		timerDelete.cancel();
		timerInsert.cancel();
		dateCheck.cancel();
		bus.deleteTable(null);
		air.deleteTable(null);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("Background Crawler Start");
	}

	public void init() throws ServletException {

		System.out.println("***** Server on CrawlerStart *****");

		// 爬蟲Delete
		TimerTask runDelete = new TimerTask() {

			@Override
			public void run() {

				System.out.println("Delete: " + delete++);

				bus.deleteTable(null);
				air.deleteTable(null);
			}
		};
		timerDelete.schedule(runDelete, 1000, 300000);

		// 爬蟲Insert
		TimerTask runInsert = new TimerTask() {

			@Override
			public void run() {

				System.out.println("Insert: " + insert++);

				Crawler_Bus.Crawler();
				Crawler_Air.Crawler();
			}
		};
		timerInsert.schedule(runInsert, 1100, 300000);

		// 促銷
		TimerTask checkDay = new TimerTask() {

			@Override
			public void run() {

				CheckDate.updateSTPM();
				CheckDate.updateREPM();
			}
		};
		dateCheck.schedule(checkDay, 1000, 86400000);
	}
}
