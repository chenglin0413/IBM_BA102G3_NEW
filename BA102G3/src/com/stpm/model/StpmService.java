package com.stpm.model;

import java.util.List;

public class StpmService {

	private StpmDAO_interface dao;

	public StpmService() {
		dao = new StpmDAO();
	}

	public StpmVO addStpm(Integer store_id, String stpm_name, String stpm_desc, String stpm_content,
			java.sql.Date stpm_startdate, java.sql.Date stpm_enddate, Integer stpm_status) {

		StpmVO stpmVO = new StpmVO();

		stpmVO.setStore_id(store_id);
		stpmVO.setStpm_name(stpm_name);
		stpmVO.setStpm_desc(stpm_desc);
		stpmVO.setStpm_content(stpm_content);
		stpmVO.setStpm_startdate(stpm_startdate);
		stpmVO.setStpm_enddate(stpm_enddate);
		stpmVO.setStpm_status(stpm_status);
		dao.insert(stpmVO);

		return stpmVO;
	}

	// 預留給 Struts 2 用的
	public void addStpm(StpmVO stpmVO) {
		dao.insert(stpmVO);
	}

	public StpmVO updateStpm(String stpm_name, String stpm_desc, String stpm_content, java.sql.Date stpm_startdate,
			java.sql.Date stpm_enddate, Integer stpm_status, Integer stpm_id) {

		StpmVO stpmVO = new StpmVO();

		stpmVO.setStpm_name(stpm_name);
		stpmVO.setStpm_desc(stpm_desc);
		stpmVO.setStpm_content(stpm_content);
		stpmVO.setStpm_startdate(stpm_startdate);
		stpmVO.setStpm_enddate(stpm_enddate);
		stpmVO.setStpm_status(stpm_status);
		stpmVO.setStpm_id(stpm_id);
		dao.update(stpmVO);

		return dao.findByPrimaryKey(stpm_id);
	}

	// 預留給 Struts 2 用的
	public void updateStpm(StpmVO stpmVO) {
		dao.update(stpmVO);
	}

	public StpmVO getOneStpm(Integer stpm_id) {
		return dao.findByPrimaryKey(stpm_id);
	}

	public List<StpmVO> getAll() {
		return dao.getAll();
	}
	
	public List<StpmVO> findByStoreID(Integer store_id){
		return dao.findByStoreID(store_id);
	}
}
