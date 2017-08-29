package com.rest.model;

import java.util.List;

import com.rest.model.RestDAO;
import com.rest.model.RestVO;
import com.store.model.StoreVO;

public class RestService {
	private RestDAO_Interface dao;
	public RestService() {
		dao = new RestDAO();
	}

	public RestVO addRest(String rest_name, String rest_address, String rest_phone,
			String rest_trans, String rest_detail, String rest_hours, Integer rest_ter, Integer rest_floor,
			Double rest_lon, Double rest_lat, Integer rest_inout, Integer rest_type, Integer rest_count,
			Integer rest_score) {

		RestVO restVO = new RestVO();

		restVO.setRest_name(rest_name);
		restVO.setRest_address(rest_address);
		restVO.setRest_phone(rest_phone);
		restVO.setRest_trans(rest_trans);
		restVO.setRest_detail(rest_detail);
		restVO.setRest_hours(rest_hours);
		restVO.setRest_ter(rest_ter);
		restVO.setRest_floor(rest_floor);
		restVO.setRest_lon(rest_lon);
		restVO.setRest_lat(rest_lat);
		restVO.setRest_inout(rest_inout);
		restVO.setRest_type(rest_type);
		restVO.setRest_count(rest_count);
		restVO.setRest_score(rest_score);
		dao.insert(restVO);

		return restVO;
	}

	public RestVO updateRest(Integer rest_id, Integer user_id, String rest_name, String rest_address, String rest_phone,
			String rest_trans, String rest_detail, String rest_hours, Integer rest_ter, Integer rest_floor,
			Double rest_lon, Double rest_lat, Integer rest_inout, Integer rest_type, Integer rest_count,
			Integer rest_score, Integer user_status) {

		RestVO restVO = new RestVO();

		restVO.setRest_id(rest_id);
		restVO.setUser_id(user_id);
		restVO.setRest_name(rest_name);
		restVO.setRest_address(rest_address);
		restVO.setRest_phone(rest_phone);
		restVO.setRest_trans(rest_trans);
		restVO.setRest_detail(rest_detail);
		restVO.setRest_hours(rest_hours);
		restVO.setRest_ter(rest_ter);
		restVO.setRest_floor(rest_floor);
		restVO.setRest_lon(rest_lon);
		restVO.setRest_lat(rest_lat);
		restVO.setRest_inout(rest_inout);
		restVO.setRest_type(rest_type);
		restVO.setRest_count(rest_count);
		restVO.setRest_score(rest_score);
		dao.update(restVO, user_status);

		return restVO;
	}

	public void deleteRest(Integer rest_id) {
		dao.delete(rest_id);
	}

	public RestVO getOneRest(Integer rest_id) {
		return dao.findByPrimaryKey(rest_id);
	}
	
	public RestVO getOneRestByUser_Id(Integer user_id) {
		return dao.findByUserId(user_id);
	}

	public List<RestVO> getAll() {
		return dao.getAll();
	}
	
	public List<RestVO> getAllbyStatus(Integer user_status) {
		System.out.println("pass service");
		return dao.getAllbyStatus(user_status);
	}
	//政成新增  首頁前三名餐廳
	public List<RestVO> getTopThree() {
		return dao.getTopThree();
	}
}
