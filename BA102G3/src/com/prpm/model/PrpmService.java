package com.prpm.model;

import java.util.List;

public class PrpmService {

	private PrpmDAO_interface dao;

	public PrpmService() {
		dao = new PrpmDAO();
	}

	public PrpmVO addPrpm(Integer stpm_id, Integer prod_id, Integer prpm_price, Integer prpm_status) {

		PrpmVO prpmVO = new PrpmVO();

		prpmVO.setStpm_id(stpm_id);
		prpmVO.setProd_id(prod_id);
		prpmVO.setPrpm_price(prpm_price);
		prpmVO.setPrpm_status(prpm_status);
		dao.insert(prpmVO);

		return prpmVO;
	}

	// 預留給 Struts 2 用的
	public void addPrpm(PrpmVO prpmVO) {
		dao.insert(prpmVO);
	}

	public PrpmVO updatePrpm(Integer stpm_id, Integer prod_id, Integer prpm_price, Integer prpm_status) {

		PrpmVO prpmVO = new PrpmVO();

		prpmVO.setPrpm_price(prpm_price);
		prpmVO.setPrpm_status(prpm_status);
		prpmVO.setStpm_id(stpm_id);
		prpmVO.setProd_id(prod_id);
		dao.update(prpmVO);

		return dao.findByStpmID_ProdID(stpm_id, prod_id);
	}

	// 預留給 Struts 2 用的
	public void updatePrpm(PrpmVO prpmVO) {
		dao.update(prpmVO);
	}

	public PrpmVO getOneStpm(Integer stpm_id, Integer prod_id) {
		return dao.findByStpmID_ProdID(stpm_id, prod_id);
	}

	public List<PrpmVO> getStpmID(Integer stpm_id) {
		return dao.findByStpmID(stpm_id);
	}

	public PrpmVO getPrice(Integer stpm_id) {
		return dao.findByPrice(stpm_id);
	}

	public List<PrpmVO> getAll() {
		return dao.getAll();
	}
	public PrpmVO getOneRmPrice_prod(Integer prod_id){
		return dao.getOneRmPrice_prod(prod_id);
	}
}
