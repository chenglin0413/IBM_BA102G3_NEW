package com.store.model;

import java.util.List;

import com.prod.model.ProdVO;

public interface StoreDAO_interface {
	public void insert(StoreVO storeVO);
	public void update(StoreVO storeVO, Integer user_status);
	public void delete(Integer store_id);
	public StoreVO findByPrimaryKey(Integer store_id);
	public StoreVO findByUserId(Integer user_id);
	public List<StoreVO> getAll();
	public List<StoreVO> getAllbyStatus(Integer user_status);
	public List<ProdVO> findByAllProd(Integer stroe_id); //store_id_getProd 李浩
	public void update_count_score(Integer store_count, Integer store_score, Integer store_id);//update storeCount_score 政成
}
