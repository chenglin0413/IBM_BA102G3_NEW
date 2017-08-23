package com.dipi.model;

import java.util.List;

import com.dish.model.DishDAO;
import com.dish.model.DishDAO_interface;
import com.dish.model.DishVO;

public class DipiService {
	private DipiDAO_interface dao;
	public DipiService() {
		dao = new DipiDAO();
	}
	
	public DipiVO addDipi(Integer dish_id,String dipi_name,byte[] dipi_img,
			String dipi_imgfmt) {
		
		DipiVO dipiVO = new DipiVO();
		
		dipiVO.setDish_id(dish_id);
		dipiVO.setDipi_name(dipi_name);
		dipiVO.setDipi_img(dipi_img);
		dipiVO.setDipi_imgfmt(dipi_imgfmt);
		
		dao.insert(dipiVO);
		
		return dipiVO;
	}
	
	public DipiVO updateDipi(Integer dish_id,String dipi_name,byte[] dipi_img,
			String dipi_imgfmt,Integer dipi_id ) {
		
		DipiVO dipiVO = new DipiVO();
		
		dipiVO.setDish_id(dish_id);
		dipiVO.setDipi_name(dipi_name);
		dipiVO.setDipi_img(dipi_img);
		dipiVO.setDipi_imgfmt(dipi_imgfmt);
		dipiVO.setDipi_id(dipi_id);
		
		dao.update(dipiVO);
		
		return dipiVO;
	}
	
	public void deleteDipi(Integer dipi_id) {
		dao.delete(dipi_id);
	}
	
	public DipiVO getOneDipi(Integer dipi_id) {
		return dao.findByPrimaryKey(dipi_id);
	}
	
	public List<DipiVO> getAll(){
		return dao.getAll();
	}
	
//	public byte[] download(Integer dipi_id){
//		return dao.download(dipi_id);
//	}
	
	public List<DipiVO> findDipisByDishId(Integer dish_id){
		return dao.getDipisByDishId(dish_id);
	}

}
