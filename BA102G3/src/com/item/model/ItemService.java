package com.item.model;

import java.sql.Date;
import java.util.List;

public class ItemService {

	private ItemDAO_interface dao;

	public ItemService() {
		dao = new ItemDAO();
	}

	public ItemVO addItem(Integer  ord_id, Integer prod_id, Integer item_qty,
			Integer item_score, String item_review, Date item_reviewdate) {

		ItemVO itemVO = new ItemVO();

		itemVO.setOrd_id(ord_id);
		itemVO.setProd_id(prod_id);
		itemVO.setItem_qty(item_qty);
		itemVO.setItem_score(item_score);
		itemVO.setItem_review(item_review);
		itemVO.setItem_reviewdate(item_reviewdate);
		dao.insert(itemVO);

		return itemVO;
	}

	public ItemVO updateItem(Integer  ord_id, Integer prod_id, Integer item_qty,
			Integer item_score, String item_review, Date item_reviewdate) {

		ItemVO itemVO = new ItemVO();

		itemVO.setItem_qty(item_qty);
		itemVO.setItem_score(item_score);
		itemVO.setItem_review(item_review);
		itemVO.setItem_reviewdate(item_reviewdate);
		itemVO.setOrd_id(ord_id);
		itemVO.setProd_id(prod_id);
		dao.update(itemVO);

		return itemVO;
	}

	public void deleteItem(Integer ord_id,Integer prod_id) {
		dao.delete(ord_id,prod_id);
	}

	public ItemVO getOneItem(Integer ord_id,Integer prod_id) {
		return dao.findByPrimaryKey( ord_id, prod_id);
	}

	public List<ItemVO> getAll() {
		return dao.getAll();
	}
	public List<ItemVO> getOneOrd_idAllItem(Integer ord_id) {
		return dao.getOneOrd_idAllItem(ord_id);
	}
}
