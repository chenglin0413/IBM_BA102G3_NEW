package com.busc.model;

import java.util.List;

public class BuscService {

	private BuscDAO_interface dao;

	public BuscService() {
		dao = new BuscDAO();
	}

	public void deleteTable(BuscVO buscVO) {
		dao.deleteTable(buscVO);
	}

	public List<BuscVO> getAll() {
		return dao.getAll();
	}
}
