package com.wish.model;

import java.sql.Date;
import java.util.List;

public class WishService {

	private WishDAO_interface dao;

	public WishService() {
		dao = new WishDAO();
	}

	public WishVO addWish(Integer user_id,Integer prod_id,Date wish_date) {

		WishVO wishVO = new WishVO();

		wishVO.setUser_id(user_id);
		wishVO.setProd_id(prod_id);
		wishVO.setWish_date(wish_date);
		dao.insert(wishVO);

		return wishVO;
	}

	public WishVO updateWish(Integer user_id,Integer prod_id,Date wish_date) {

		WishVO wishVO = new WishVO();

		wishVO.setUser_id(user_id);
		wishVO.setProd_id(prod_id);
		wishVO.setWish_date(wish_date);
		dao.update(wishVO);

		return wishVO;
	}

	public void deleteWish(Integer user_id,Integer prod_id) {
		dao.delete(user_id,prod_id);
	}

	public WishVO getOneWish(Integer user_id,Integer prod_id) {
		return dao.findByPrimaryKey(user_id,prod_id);
	}

	public List<WishVO> getAll() {
		return dao.getAll();
	}
	public List<WishVO> getOneUser_idAllWish(Integer user_id) {
		return dao.getOneUser_idAllWish(user_id);
	}
}
