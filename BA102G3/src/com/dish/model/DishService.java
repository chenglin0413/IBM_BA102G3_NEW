package com.dish.model;

import java.util.List;

public class DishService {
	private DishDAO_interface dao;
	public DishService() {
		dao = new DishDAO();
	}
	
	public String addDish(Integer rest_id,String dish_name,Integer dish_price,
			Integer dish_status,String dish_detail,String dish_note ) {
		DishVO dishVO = new DishVO();
		
		dishVO.setRest_id(rest_id);
		dishVO.setDish_name(dish_name);
		dishVO.setDish_price(dish_price);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_detail(dish_detail);
		dishVO.setDish_note(dish_note);
		String	dish_id = dao.insert(dishVO);
		
		return dish_id;
	}
	
	public DishVO updateDish(Integer rest_id,String dish_name,Integer dish_price,
			Integer dish_status,String dish_detail,String dish_note,Integer dish_id ) {
		DishVO dishVO = new DishVO();
		
		dishVO.setRest_id(rest_id);
		dishVO.setDish_name(dish_name);
		dishVO.setDish_price(dish_price);
		dishVO.setDish_status(dish_status);
		dishVO.setDish_detail(dish_detail);
		dishVO.setDish_note(dish_note);
		dishVO.setDish_id(dish_id);
		dao.update(dishVO);
		
		return dishVO;
	}
	
	public void deleteDish(Integer dish_id) {
		dao.delete(dish_id);
	}
	
	public DishVO getOneDish(Integer dish_id) {
		return dao.findByPrimaryKey(dish_id);
	}
	
	public List<DishVO> getAll(){
		return dao.getAll();
	}
	
	public List<DishVO> getDishsByRestId(Integer rest_id) {
		return dao.findByFk(rest_id);
	}

}
