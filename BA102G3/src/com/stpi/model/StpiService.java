package com.stpi.model;

import java.util.List;

public class StpiService {

	private StpiDAO_interface dao;

	public StpiService() {
		dao = new StpiDAO();
	}

	public StpiVO addStpi(Integer store_id, Integer stpi_name , byte[] stpi_img,String stpi_imgfmt) {

		StpiVO stpiVO = new StpiVO();

		stpiVO.setStore_id(store_id);
		stpiVO.setStpi_name(stpi_name);
		stpiVO.setStpi_img(stpi_img);
		stpiVO.setStpi_imgfmt(stpi_imgfmt);
		dao.insert(stpiVO);

		return stpiVO;
	}

	public StpiVO updateStpi(Integer stpi_id,Integer store_id, Integer stpi_name , byte[] stpi_img,String stpi_imgfmt) {

		StpiVO stpiVO = new StpiVO();

		stpiVO.setStpi_id(stpi_id);
		stpiVO.setStore_id(store_id);
		stpiVO.setStpi_name(stpi_name);
		stpiVO.setStpi_img(stpi_img);
		stpiVO.setStpi_imgfmt(stpi_imgfmt);
		dao.update(stpiVO);

		return stpiVO;
	}

	public void deleteStpi(Integer stpi_id) {
		dao.delete(stpi_id);
	}

	public StpiVO getOneStpi(Integer stpi_id) {
		return dao.findByPrimaryKey(stpi_id);
	}

	public List<StpiVO> getAll() {
		return dao.getAll();
	}
}
