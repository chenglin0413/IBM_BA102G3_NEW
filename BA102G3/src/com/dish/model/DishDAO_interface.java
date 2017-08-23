package com.dish.model;

import java.util.List;

public interface DishDAO_interface {
	public void insert(DishVO dishVO);
	public void update(DishVO dishVO);
	public void delete(Integer dish_id);
	public DishVO findByPrimaryKey(Integer dish_id);
	public List<DishVO> getAll();
	
		
	
}
