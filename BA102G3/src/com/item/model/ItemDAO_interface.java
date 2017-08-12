package com.item.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO_interface {
	public void insert(ItemVO itemVO);
	public void insert2 (ItemVO itemVO , Connection con);
	public void update(ItemVO itemVO);
	public void delete(Integer Ord_id, Integer prod_id);
	public ItemVO findByPrimaryKey(Integer Ord_id, Integer prod_id);
	public List<ItemVO> getAll();
	public List<ItemVO> getOneOrd_idAllItem(Integer ord_id);
}
