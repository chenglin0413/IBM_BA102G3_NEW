package com.store.model;

import java.util.List;

import com.prod.model.ProdVO;

public class StoreService {

	private StoreDAO_interface dao;

	public StoreService() {
		dao = new StoreDAO();
	}

	public StoreVO addStore(String store_name, String store_time ,String store_phone ,String store_describe ,Integer store_ter,String store_floor,Double store_lon,Double store_lat,Integer store_inout, Integer store_count,Integer store_score) {

		StoreVO storeVO = new StoreVO();

		storeVO.setStore_name(store_name);
		storeVO.setStore_time(store_time);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_describe(store_describe);
		storeVO.setStore_ter(store_ter);
		storeVO.setStore_floor(store_floor);
		storeVO.setStore_lon(store_lon);
		storeVO.setStore_lat(store_lat);
		storeVO.setStore_inout(store_inout);
		storeVO.setStore_count(store_count);
		storeVO.setStore_score(store_score);
		dao.insert(storeVO);

		return storeVO;
	}

	public StoreVO updateStore(Integer store_id,Integer user_id, String store_name, String store_time ,String store_phone ,String store_describe ,Integer store_ter,String store_floor,Double store_lon,Double store_lat,Integer store_inout, Integer store_count,Integer store_score, Integer user_status){
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_id(store_id);
		storeVO.setUser_id(user_id);
		storeVO.setStore_name(store_name);
		storeVO.setStore_time(store_time);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_describe(store_describe);
		storeVO.setStore_ter(store_ter);
		storeVO.setStore_floor(store_floor);
		storeVO.setStore_lon(store_lon);
		storeVO.setStore_lat(store_lat);
		storeVO.setStore_inout(store_inout);
		storeVO.setStore_count(store_count);
		storeVO.setStore_score(store_score);
		dao.update(storeVO, user_status);

		return storeVO;
	}

	public void deleteStore(Integer store_id) {
		dao.delete(store_id);
	}

	public StoreVO getOneStore(Integer store_id) {
		return dao.findByPrimaryKey(store_id);
	}
	
	public StoreVO getOneStoreByUsed_Id(Integer user_id) {
		return dao.findByUserId(user_id);
	}

	public List<StoreVO> getAll() {
		System.out.println("pass service");
		return dao.getAll();
	}
	
	public List<StoreVO> getAllbyStatus(Integer user_status) {
		System.out.println("pass service");
		return dao.getAllbyStatus(user_status);
	}
	//李浩，促銷商品
	public List<ProdVO> findByAllProd(Integer store_id) {
		System.out.println("pass service");
		return dao.findByAllProd(store_id);
	}
	///政成，更新商店分數
	public void update_count_score(Integer store_count,Integer store_score,Integer store_id){
		dao.update_count_score(store_count,store_score, store_id);
	}
	
}
