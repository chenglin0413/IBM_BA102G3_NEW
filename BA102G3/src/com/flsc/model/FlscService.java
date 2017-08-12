package com.flsc.model;

import java.util.List;

public class FlscService {

	private FlscDAO_interface dao;

	public FlscService() {
		dao = new FlscDAO();
	}

	public void deleteTable(FlscVO flscVO) {
		dao.deleteTable(flscVO);
	}

	public List<FlscVO> getAll() {
		return dao.getAll();
	}

	public List<FlscVO> findByFlno(String flsc_flno) {
		return dao.findByFlno(flsc_flno);
	}

	public List<FlscVO> findByflsc_airlinecode(String flsc_airlinecode) {
		return dao.findByflsc_airlinecode(flsc_airlinecode);
	}

	public FlscVO flscSubQuery(String flsc_airlinecode, String flsc_flno, String flsc_sdate) {
		return dao.flscSubQuery(flsc_airlinecode, flsc_flno, flsc_sdate);
	}
}
