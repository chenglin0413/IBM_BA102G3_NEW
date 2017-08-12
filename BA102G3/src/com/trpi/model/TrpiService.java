package com.trpi.model;

import java.util.List;

public class TrpiService {

	private TrpiDAO_interface dao;

	public TrpiService() {
		dao = new TrpiDAO();
	}

	public TrpiVO addTrpi(Integer trvl_id, byte[] trpi_img,String trpi_imgfmt) {

		TrpiVO trpiVO = new TrpiVO();

		trpiVO.setTrvl_id(trvl_id);
		trpiVO.setTrpi_img(trpi_img);
		trpiVO.setTrpi_imgfmt(trpi_imgfmt);

		dao.insert(trpiVO);
		return trpiVO;
	}

	public TrpiVO updateTrpi(Integer trpi_id,Integer trvl_id, byte[] trpi_img,String trpi_imgfmt) {

		TrpiVO trpiVO = new TrpiVO();

		trpiVO.setTrpi_id(trpi_id);
		trpiVO.setTrvl_id(trvl_id);
		trpiVO.setTrpi_img(trpi_img);
		trpiVO.setTrpi_imgfmt(trpi_imgfmt);
		dao.update(trpiVO);

		return trpiVO;
	}

	public void deleteTrpi(Integer trpi_id) {
		dao.delete(trpi_id);
	}

	public TrpiVO getOneTrpi(Integer trpi_id) {
		return dao.findByPrimaryKey(trpi_id);
	}
	
	public List<TrpiVO> getTrpiForOneTrvl(Integer trvl_id) {
		return dao.findByForeignKey(trvl_id);
	}

	public List<TrpiVO> getAll() {
		return dao.getAll();
	}
	
	
	
}
