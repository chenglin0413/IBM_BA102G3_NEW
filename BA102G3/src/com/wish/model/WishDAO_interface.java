package com.wish.model;

import java.util.List;

public interface WishDAO_interface {
	public void insert(WishVO wishVO);
	public void update(WishVO wishVO);
	public void delete(Integer user_id,Integer prod_id);
	public WishVO findByPrimaryKey(Integer user_id,Integer prod_id);
	public List<WishVO> getAll();
	public List<WishVO> getOneUser_idAllWish(Integer user_id);
}
