package com.dish.model;

import java.util.List;

public interface DishDAO_interface {
	public String insert(DishVO dishVO);
	public void update(DishVO dishVO);
	public void delete(Integer dish_id);
	public DishVO findByPrimaryKey(Integer dish_id);
	public List<DishVO> getAll();
	
	//查詢一家餐廳所有料理
	public List<DishVO> findByFk(Integer rest_id);
	
}
