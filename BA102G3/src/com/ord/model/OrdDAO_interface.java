package com.ord.model;

import java.util.List;
import java.util.Map;

import com.item.model.ItemVO;

public interface OrdDAO_interface {
	public void insert(OrdVO ordVO);
	//給會員新增訂單_訂單明細用的
	public Integer insertWithItems(OrdVO ordVO , List<ItemVO> list);
	public void update(OrdVO ordVO);
	public void delete(Integer Ord_id);
	public OrdVO findByPrimaryKey(Integer ord_id);
	public OrdVO select_One_ord_id();//新增訂單用的(取得最新的ord_id和該訂單的store_id)
	public List<OrdVO> getAll();
	public List<OrdVO> getOneStore_idAllOrd(Integer store_id);//給商家會員用的
	public List<OrdVO> getOneUser_idAllOrd(Integer user_id);//給會員用的
	public void update_sscore(Integer ord_sscore,Integer ord_id);//給會員填入商品評價
	public void update_bill(Integer ord_bill,Integer ord_id);//給會員填入付款方式
	
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<OrdVO> getAll(Map<String, String[]> map);
    
}
