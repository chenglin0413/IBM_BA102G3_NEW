package com.prpi.model;

import java.util.List;


public class PrpiService {
	private PrpiDAO_interface dao;
	
	public PrpiService(){
		dao = new PrpiDAO();
	}
	
	public PrpiVO addPrpi(Integer prod_id, String prpi_name, byte[] prpi_img){
		PrpiVO prpiVO = new PrpiVO();

		prpiVO.setProd_id(prod_id);
		prpiVO.setPrpi_name(prpi_name);
		prpiVO.setPrpi_img(prpi_img);
		dao.insert(prpiVO);

		return prpiVO;
	}

	public PrpiVO updatePrpi(Integer prpi_id,Integer prod_id, String prpi_name, byte[] prpi_img) {

		PrpiVO prpiVO = new PrpiVO();
		prpiVO.setPrpi_id(prpi_id);
		prpiVO.setProd_id(prod_id);
		prpiVO.setPrpi_name(prpi_name);
		prpiVO.setPrpi_img(prpi_img);
		dao.update(prpiVO);

		return prpiVO;
	}

	public void deletePrpi(Integer prpi_id) {
		dao.delete(prpi_id);
	}

	public PrpiVO getOnePrpi(Integer prpi_id) {
		return dao.findByPrimaryKey(prpi_id);
	}

	public List<PrpiVO> getAll() {
		return dao.getAll();
	}
	
	public void insertImg(PrpiVO prpiVO, java.sql.Connection con) {
		dao.insertImg(prpiVO, con);
	}		
	
	public PrpiVO getOnePrpiByProd(Integer prod_id){
		return dao.findByProd_id(prod_id);
	}
}
